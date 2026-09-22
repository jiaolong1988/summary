# 参考

- [pinia](https://pinia.vuejs.org/zh/core-concepts/)

- [pinia-plugin-persistedstate](https://prazdevs.github.io/pinia-plugin-persistedstate/zh/guide/)



# 基础

## 1.localStorage VS sessionStorage

- 都属于 WebStorage

| 特性                      | localStorage                                                 | sessionStorage                                               |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **生命周期**              | 永久保存。除非：代码删除 / 用户手动清除浏览器缓存            | **会话级别**。关闭当前标签页 (Tab) 直接清空                  |
| **作用域（多 Tab 共享）** | **同源所有标签页共享**Tab1、Tab2 打开同一个网站，读取的是同一份数据 | **仅限当前标签页独立隔离**新开同源 Tab，是全新空白 sessionStorage，互不干扰 |
| 关闭浏览器窗口            | 数据保留                                                     | 数据直接清除                                                 |
| 刷新当前页面 (F5)         | ✅ 数据保留                                                   | ✅ 数据保留（刷新 Tab 不会丢，关闭 Tab 才丢                   |

**localStorage**

- Tab1 登录用户 A → 写入 localStorage
- 新开 Tab2，同网站登录用户 B → **覆盖 localStorage**
- 切回 Tab1，F5 刷新页面 → 读到 B 用户，**发生串号！** 👉 **多个 Tab 并行登录不同账号，不要用 localStorage**

**sessionStorage**

- Tab1 登录 A：本 Tab 的 sessionStorage 存 A
- Tab2 新开同网站登录 B：Tab2 独立 sessionStorage 存 B
- 两个 Tab 互不影响，刷新各自 Tab 数据保留；**关闭 Tab 直接清空** 👉 ✅ 适合：**一个浏览器多个 Tab 登录不同账号，防止串号**



## 2.pinia 原生存储

- Pinia 存储 完整梳理

  > Pinia **本身只存在浏览器内存**，刷新页面就清空。 我们平时说的「Pinia 持久化存储」，是**借助插件（pinia-plugin-persistedstate）把 state 同步到 WebStorage (localStorage/sessionStorage)**，不是 Pinia 自带磁盘存储能力。

✅ 存储位置：**JS 内存** ✅ 特点：

1. 响应式，state 修改自动更新所有组件

2. 页面**F5 刷新 / 关闭 Tab → 全部丢失**

3. **每个浏览器 Tab 拥有独立 Pinia 实例，内存互相隔离**

   > **每打开一个浏览器标签（Tab），就是一套完全独立运行的 JS 代码、独立的内存空间；因此每个 Tab 里面，会单独新建一份 Pinia 实例。TabA 的 Pinia 内存数据，TabB 完全看不到、改不到。**

4. 支持`state / getters / actions`，可以封装异步业务

5. 案例

   > 浏览器不同标签页，**JS 运行环境是互相隔离的**：
   >
   > - Tab1：打开 `http://localhost:5173`，浏览器分配一块独立内存，加载网站 JS，执行代码，创建 Pinia
   > - Tab2：同样打开 `http://localhost:5173`，浏览器**新开另一块独立内存**，重新加载整套 JS，**重新创建全新 Pinia**
   >
   > 👉 Tab1 的变量、对象、Pinia，全部存在 Tab1 这块内存。 👉 Tab2 的变量、对象、Pinia，全部存在 Tab2 另一块内存。
   >
   > 两块内存互不连通。**JS 不能跨 Tab 直接读写对方内存**。

   ```html
   浏览器
   ├─ Tab1（页面A）
   │   ├─ JS内存【独立】
   │   │   └─ Pinia实例 #1：user = A
   ├─ Tab2（页面A，同源网站）
   │   ├─ JS内存【独立】
   │   │   └─ Pinia实例 #2：user = B
   ```

## 3.pinia VS web-storage-cache

| 项目           | pinia-plugin-persistedstate                | web-storage-cache                              |
| -------------- | ------------------------------------------ | ---------------------------------------------- |
| 依赖           | 只依赖 pinia，**不依赖 web-storage-cache** | 独立包，和 pinia 无关                          |
| 自动同步 Pinia | ✅ 监听 state 变化，自动保存 / 恢复         | ❌ 完全不会自动同步，必须手动调用 set/get       |
| TTL 过期       | ❌ 原生不支持过期                           | ✅ 自带过期时间，过期读取返回 null              |
| 使用方式       | 在 defineStore 里配置，声明式              | 手动调用 wsCache.set () /wsCache.get () 命令式 |
| 适用范围       | 仅 Pinia 项目                              | Vue / 原生 JS/JQ 任何前端项目                  |
| 序列化         | 内部自带 JSON 序列化                       | 自带 JSON 序列化                               |
| SSR            | 支持（自动判断环境，Node 端跳过存储）      | 不支持 SSR，Node 无 window                     |

**1. pinia-plugin-persistedstate**

​	👉 **Pinia 的配套插件，目标：自动同步 Pinia state ↔ WebStorage**

- 底层：**直接调用浏览器原生 localStorage /sessionStorage**，**不依赖 web-storage-cache**

- 工作逻辑：

  1. 监听 Pinia state 的变化
  2. state 一变，自动把指定字段 JSON.stringify 存到 storage
  3. 页面刷新时，自动从 storage 读取、JSON.parse，回填到 Pinia 的 state

- 核心定位：**状态同步插件，为 Pinia 而生**

- 缺点：原生不自带 TTL（过期时间），没有缓存过期自动删除功能

  

**2.web-storage-cache**

​	👉 **独立通用缓存工具库，和 Pinia 无关**

- 底层：**也是封装原生 localStorage /sessionStorage**

- 额外增加能力：自动序列化 + **TTL 过期时间**、过期自动清理、缓存命中判断

- 工作逻辑：手动调用 `set/get` API 读写缓存；**不会自动监听 Pinia**，state 变了不会自动存

- 定位：通用浏览器缓存工具，**任何 JS 项目都能用，不限于 Vue/Pinia**

  

# 使用

### pinia

```ts
import { defineStore } from 'pinia'

//  `defineStore()` 的返回值的命名是自由的
// 但最好含有 store 的名字，且以 `use` 开头，以 `Store` 结尾。
// (比如 `useUserStore`，`useCartStore`，`useProductStore`)
// 第一个参数是你的应用中 Store 的唯一 ID。
export const useAlertsStore = defineStore('alerts', {
  // 其他配置...
})
```

alerts: 这个**名字** ，是必须传入的， Pinia 将用它来连接 store 和 devtools。主要作用是: 在使用devtools工具，**通过这个名称 定位到 使用的是那个store**



###  pinia-plugin-persistedstate

```ts
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useStore = defineStore(
  'main',
  () => {
    const someState = ref('hello pinia')
    return { someState }
  },
  {
    persist: true,   //如果不写，不会对该 Store 做任何持久化
  },
)
```



