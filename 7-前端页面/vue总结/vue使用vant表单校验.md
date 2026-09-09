```ts
<template>
  <van-form ref="formRef" class="login-form">
    <!-- 表单项 -->
    <van-field v-model="form.username" label="账号"/>
    <van-field v-model="form.password" label="密码"/>
  </van-form>
</template>

<script setup lang="ts">
import { ref } from 'vue'

// ① 脚本定义同名变量 formRef，变量名必须和模板 ref="formRef" 完全一致
const formRef = ref(null)

// 表单校验，调用 van‑form 组件自带的 validate() 方法
async function handleLogin(){
  // ② formRef.value 获取组件实例，调用Vant表单组件内置方法 validate()
  const valid = await formRef.value?.validate()
  // validate()：校验所有表单项，返回 true/false；校验失败自动弹出提示
  if(!valid){
    return // 校验不通过直接退出，不发登录请求
  }

  // 校验通过，执行登录接口
}
</script>
```

## ref 到底是什么

> ref 作用：**把 DOM / 组件实例，绑定到脚本变量上，JS 代码拿到组件，调用组件内部方法**。

- <van‑form>`：Vant 的表单组件，等价 ElementPlus 的 `<el‑form>

- `ref="formRef"`：**Vue 模板 ref 语法**

### 关键点拆解

1. 模板：`ref="formRef"`
2. script：必须声明 `const formRef = ref(null)`
3. 获取实例一定要加 `.value` → `formRef.value`
   - `formRef.value` 就是 `<van‑form>` 这个组件实例对象
   - 可以调用组件提供的内置 API：`validate()`、`resetValidation()` 重置校验等

### van‑form 常用实例方法（通过 ref 调用）

```ts
// 全部表单校验，返回 boolean
formRef.value.validate()

// 重置校验提示（清空红色报错）
formRef.value.resetValidation()

// 校验部分字段
formRef.value.validate(['username'])
```

## ref 和 v-model 的区别（容易混淆）

|    语法     |                 作用                  |             使用场景             |
| :---------: | :-----------------------------------: | :------------------------------: |
|  `v-model`  |       **绑定数据**，拿表单的值        |     拿到输入框输入的账号密码     |
| `ref="xxx"` | 拿到**组件 / DOM 实例**，调用组件方法 | 触发表单校验、重置校验、操作 DOM |

> `v-model` 管**数据**；`ref` 管**调用组件的方法**。

## 常见坑

1. ❌忘记写 `.value`，直接写 `formRef.validate()` → 报错，`formRef.value.validate()` 才对
2. ❌script 没有定义 `const formRef = ref(null)` → `formRef.value` 是 `undefined`
3. ❌组件使用 v-if 渲染，v-if 为 false 时，`formRef.value = null`，拿不到实例；改用 v‑show