## 一、response 拦截器的逻辑

[service.ts:98](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L98) 的 `response.use(成功回调, 失败回调)` 接收**两个回调**，分别处理两种层面的错误。

### 1. 成功回调（HTTP 2xx 时走这里，内部再做业务判断）

执行顺序：

1. 取 `data` 和 `config`，空响应直接抛错（[service.ts:102](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L102)）
2. 根据响应头判断是否**加密响应**，是则解密（[service.ts:108-120](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L108-L120)）
3. **blob / arraybuffer 下载特判**：非 JSON 直接返回二进制；JSON 说明导出失败，解析出来走报错流程（[service.ts:125-134](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L125-L134)）
4. 归一化 code（默认 result_code = 200）和 msg（data.msg → errorCode 映射 → 默认文案），然后按 code 分支：
   - **忽略列表**（`无效的刷新令牌` / `刷新令牌已过期`）→ 直接 reject，不弹任何提示（[service.ts:138](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L138)）
   - **401** → 走无感刷新 token（重点，见下）
   - **500** → `ElMessage` 报错 + reject（[service.ts:184](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L184)）
   - **901** → 特殊弹窗（HTTP 安全相关）+ reject（[service.ts:187](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L187)）
   - **其它非成功码** → `ElNotification` 弹出后端 msg，reject（[service.ts:197](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L197)）
   - **成功（0 / 200）** → `return data`，返回业务数据（[service.ts:206](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L206)）

### 2. 401 无感刷新 token（最复杂的部分）

核心思路：**访问令牌过期时，用一个 `refreshToken` 静默换新 token，然后自动重放所有被 401 打断的请求**。配合三个全局状态：`requestList`（被挂起的请求队列）、`isRefreshToken`（是否刷新中）、`isRelogin`（是否已弹登出框）。



```
收到 401
├─ 不在刷新中
│   ├─ 没有 refreshToken → handleAuthorized() 强制登出
│   └─ 有 → 调 POST /system/auth/refresh-token
│       ├─ 成功 → 存新token，回放队列中所有请求 + 重发当前请求（service(config)）
│       └─ 失败 → 只回放队列（不回放当前请求，避免递归），弹登出框
└─ 正在刷新中 → 把当前请求塞进 requestList，等新 token 出来后回放
```

`handleAuthorized()`（[service.ts:230](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L230)）：弹「登录超时，请重新登录」确认框，确认后重置路由、清用户缓存、删 token、刷新页面；用 `isRelogin.show` 防止多个 401 同时弹多个框，且已在登录页时直接跳过。

### 3. 失败回调（网络层错误）

把 `Network Error` / `timeout` / `Request failed with status code 4xx` 翻译成中文提示，`ElMessage` 报错后 reject（[service.ts:210-223](vscode-webview://10tb6dvl59bnqrerfg2sjfhba4491u08mesup1pnbn6ep9s5rjra/src/config/axios/service.ts#L210-L223)）。