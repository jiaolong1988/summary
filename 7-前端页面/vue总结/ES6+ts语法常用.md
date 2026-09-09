# ES6

## 1. ES6 对象字面量属性简写

- 原始完整写法

```ts
const pageParams = { pageNum: 1, pageSize: 10 }

// 完整写法：key 和变量名重复，必须写两遍
request.get({
  url: '/api/page',
  params: pageParams 
})
```

- ES6 简写写法，当**对象 key 名称 和 变量名称完全一致**时，可以只写一次：

```ts
const params = { pageNum: 1, pageSize: 10 }

// 简写：params 等价于 params: params
request.get({
  url: '/api/page',
  params 
})
```



## 2.模板字符串

- 语法 ``

```ts
`Count changed from ${oldP} to ${newP}`

//watch 监听数据变化- 必须有参数
watch([price, count], ([newP, newC], [oldP, oldC]) => {

    //模板字符串
    console.log("watch ",`Count changed from ${oldP} to ${newP}`)
})   

```



## 3.对象字面量

- 用 `{ 键: 值 }` 这种花括号直接手写、当场定义对象的语法

  > // ✅ 这就是【对象字面量】
  > const user = { name: "张三", age: 18 }



## 4.import 与export

| 形式     | 导出写法               | 导入写法                  | 名称规则         |
| -------- | ---------------------- | ------------------------- | ---------------- |
| 默认导出 | `export default xxx`   | `import A from 'mod'`     | A 名字随便自定义 |
| 命名导出 | `export const A = xxx` | `import { A } from 'mod'` | 名称必须完全一致 |



## 5. import副作用导入

> 【不导入仅执行】

- **`import './permission'` → 不接收任何变量，不提取导出内容，仅仅 加载这个文件，并执行文件内部所有代码**

  > ES Module 规则：只要写 `import '路径'`
  >
  > - **浏览器 / 打包工具会加载并执行这个 js 文件里面所有代码，但不获取任何导出变量。**
  >
  > - 只要模块被 `import`，代码就会自上而下执行一次。 哪怕你不拿任何导出，代码依旧运行。

- **总结**

  > 1. **所有 import 都会执行目标模块（首次加载）；**
  > 2. `import { ref } from 'vue'`：执行模块 + **提取导出变量到当前文件**；
  > 3. `import 'vue'`：只执行模块，**不提取任何变量，当前文件一无所有**。



## 6.import type 类型导入

- 只导入【类型】，不导入运行时代码，仅给 TypeScript 做类型校验，打包后这行直接消失。

```ts
import {xxx} from 'vue-router'       // 导入值（运行时能用）
import type { xxx } from 'vue-router'  // 只导入类型，仅 TS 用
```

| 场景                                           | 应该用           |
| ---------------------------------------------- | ---------------- |
| 导入函数、常量、class，页面要执行运行          | `import {}`      |
| 导入 type /interface，只做类型标注，不参与运行 | `import type {}` |

###  案例

```ts
// 👉 纯类型：编译之后直接消失，JS里没有任何痕迹
export type TokenType = {
  accessToken: string
  refreshToken: string
  expiresTime: number
}

// 👉真实JS函数：运行时存在，打包后会进入dist产物
export function saveToken(token: TokenType) {
  localStorage.setItem('token', JSON.stringify(token))
}
```

普通导入 与类型导入

> import { saveToken, **type** TokenType } from '@/types/token'

```ts
// 函数(运行代码)普通import；
import { saveToken } from '@/types/token'

//类型用 import type
import type { TokenType } from '@/types/token'

// 仅用来做类型标注，不会生成JS代码
const myToken: TokenType = {
  accessToken: 'abc123',
  refreshToken: 'rrr456',
  expiresTime: 1788888888
}

saveToken(myToken)
```





## 7.import 函数形式的导入

| 写法                    | 执行时机                  | 打包产物           | 用途                         |
| ----------------------- | ------------------------- | ------------------ | ---------------------------- |
| `import xxx from 'xxx'` | **编译 / 初始化立刻加载** | 打进主 bundle      | 全局组件、公共工具           |
| `import('xxx')`         | **运行时调用才加载**      | 独立 chunk 分片 js | 路由懒加载、条件动态加载组件 |

```ts
// 静态导入：编译阶段就加载，打包时直接打进主bundle
import UserLogin from '@/views/Login/UserLogin.vue'

// 动态 import()：运行时才加载，返回 Promise
import('@/views/Login/UserLogin.vue')
```



```ts
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/login',
    // 直接写 import()，router 内部会处理这个 Promise
    component: () => import('@/views/Login/UserLogin.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router
```



> `() => import(...)` 是箭头函数，**访问路由的时候才执行 import ()**，实现懒加载；没访问该路由，不会下载这个组件 js 文件。

> ❗不要直接写 `component: import('xxx')`，会在初始化就执行加载，失去懒加载意义，必须包一层函数。







### 8.export default {} 导出

`export default {}`：**ES6 默认导出，导出一个 JS 对象**。

> 一个文件只能有**一个** `export default`；导出的是一整个对象，不是分别导出里面的属性



- 基础写法

```ts
// http.ts
export default {
  get() {},
  post() {}
}


//导入

// main.ts
// 变量名可以叫 http、api、req，随便取
import http from './http.ts'

// 使用：对象.方法()
http.get()
http.post()

//解构
import http from './http.ts'
const { get, post } = http

get()
post()

// ❌报错！这是命名导出的语法，export default {}不是命名导出
import { get } from './http.ts'
```



- TypeScript + 泛型

```ts
// request.ts
type RequestOption = { url: string; data?: any }

export default {
  get: async <T = unknown> (option: RequestOption) => {
    const res = await request({ method: 'GET', ...option })
    return res.data as unknown as T 
  },
  post: async <T = unknown> (option: RequestOption) => {
    const res = await request({ method: 'POST', ...option })
    
    //表示返回的时泛型
    return res.data as unknown as T 
  }
}

```

调用

```ts
import http from './request'

interface User {
  id: number
}

// 指定泛型T，获得返回值类型提示
const users = await http.get<User[]>({ url: '/api/user' })
```



# TS语法

**==解构 会导致 响应 的丢失==**



## 基础

### **components.d.ts**是什么

它是 **unplugin‑vue‑components** 自动生成的类型声明文件，**只服务于 TypeScript + Vue 模板，不参与项目打包运行**。

> 你项目里开启了 VantResolver，实现模板里直接写 `<van-button />`，不用手动 `import VanButton from 'vant'`。
>
> 虽然代码运行没问题，但 TS 本身不知道 `<van‑xxx>` 这些全局组件存在，就会报红色波浪、丢失 props 提示。**这个文件就是解决这个问题的**。

- 修改此文件存储路径

```
```





### 6 个假值列表

1. `false` —— 布尔假本身
2. `0` —— 数字零（包含 `-0` 负零）
3. `''` / `""` —— **空字符串**（字符串长度为 0）
4. `null` —— 空对象引用
5. `undefined` —— 变量未定义
6. `NaN` —— 非数字（计算出错的值，比如 `0/0`）

```ts
// 全部取反验证，!xxx === true
console.log(!false)      // true
console.log(!0)          // true
console.log(!'')         // true
console.log(!null)       // true
console.log(!undefined)  // true
console.log(!NaN)        // true
```



## 1. 语法 ?. 与 ？？

### 1.1 ?.可选链运算符

> ?. **是可选链运算符**
>
> 只有一种情况：**aa 是一个 ref，并且 ref 内部保存的值，又是另一个 ref！（ref 嵌套 ref）**

```ts
import { ref } from 'vue'
// ref 里面套了一个 ref
const innerRef = ref(100)
const aa = ref(innerRef)

//结构
aa → Ref对象
aa.value → innerRef（又是一个Ref）
aa.value.value → 100

aa.value?.value
```

- aa.value?.value

等价判断逻辑：

```ts
// 如果 aa.value 不为 null / undefined，才继续访问 .value
aa.value == null ? undefined : aa.value.value
```

> **aa.value.value**
>
> - 一旦 `aa.value` 是 `undefined`，代码直接抛出报错：Cannot read properties of undefined (reading 'value')
>
> - 加上 `?.` 就不会报错，直接返回 `undefined`。
>
>   

### 1.2 ?? 空值合并运算符

```ts
aa?.value ?? ''
- 如果 aa 不是 null/undefined，就去读取 aa.value；
- 如果最终拿到的值是 null 或 undefined，返回兜底空字符串 ''。
```

### 1.3 ?? 与|| 区别

- **`||` 碰到「所有假值」都会走右侧默认值；`??` 只在 `null / undefined` 的时候才走右侧。**

  > JS 里的假值列表
  >
  > `0`、`''`、`false`、`null`、`undefined`、`NaN

#### 1.3.1 逻辑或 `||`

规则：左边为**任意假值**，就返回右边

```js
console.log(0 || '默认')          // '默认'
console.log('' || '默认')         // '默认'
console.log(false || '默认')      // '默认'
console.log(null || '默认')       // '默认'
console.log(undefined || '默认')  // '默认'
```

#### 1.3.2 空值合并 `??`

- 规则：**仅仅 `null`、`undefined` 才返回右边**

  > `0、''、false、NaN` 全都视为有效数据，直接返回左边

```js
console.log(0 ?? '默认')      	// 0
console.log('' ?? '默认')    	    // ''
console.log(false ?? '默认')      // false

console.log(null ?? '默认')       // '默认'
console.log(undefined ?? '默认')  // '默认'
```



## 2.箭头函数

遍历数组，**找到第一个满足条件的元素，直接返回这个对象**。找不到 → 返回 `undefined`。

- **tem.id === 1** 判断条件：这条数据的 id 属性严格等于数字 1。

```ts
const arr = [
  { id: 1, name: '康复记录' },
  { id: 2, name: '体检表单' }
]

const result = arr.find(item => item.id === 1)
console.log(result)
// { id: 1, name: '康复记录' }
```

### 2.1箭头函数-参数()规则

只有单个参数，可以省略外层 `()`

```ts
item => xxx
//等价
(item) => xxx
```

多个参数、或者没有参数，括号不能省

``` 
// 无参数，必须带括号
() => 123

// 两个参数，必须带括号
(a,b) => a+b
```



### 2.2箭头函数-方法{}规则

- `item => xxx`：单行表达式，**自动 return**
- `item => { ... }`：代码块，**必须手动写 return**

```ts
// ❌错误！没有return
list.find(item => { item.id === 1 })

// ✅正确，大括号需要手动return
list.find(item => { 
  return item.id === 1 
})
```



### 2.3 箭头函数-返回【字面量】

- 对象字面量 `{...}`  例如： { name: "张三", age: 18 }

  >  语法：() => (对象字面量)
  >
  >  () => ({ count:0 })

- / 以下两个函数 是一样的效果

```ts
// 写法A：箭头函数「隐式返回对象字面量」
state: () => ({
  count: 0
})

// 写法B：箭头函数「函数体 + 显式return」
state: () => {
  return { count: 0 }
}
```



## 3 xx.d.ts 

### declare

- `declare global {}` → 扩充**浏览器 / Node 顶层全局**（window、全局变量、全局类型），单词固定 global

- `declare module "xxx" {}` → 给第三方包补充类型，引号内的包名可以随便改

  > export {} // 强制变成模块文件
  > declare global {
  >   // 这里才能挂载全局类型
  > }

- **aa.d.ts完成解释**

  > `.ts`：TypeScript 源码文件
  >
  > `.d.ts` = **Declaration TypeScript**，**类型声明文件**
  >
  > `aa.d.ts` 就是名为 `aa` 的类型声明文件。

**自动识别**

- ==tsconfig.json== 中 `include` 包含该文件路径，TS 会自动读取 `.d.ts` 类型。

**declare 关键字**

- `.d.ts` 标配关键字，用来声明不存在实际源码的变量、模块、全局对象。



## 4 ...是什么-剩余运算符

### 4.1在解构里

> `...otherOption` 是**剩余运算符**

例如：`const { headersType, headers, ...otherOption } = option`

- `option` 是完整请求配置对象，包含：`url、method、data、params、headersType、headers`
- 先单独取出 `headersType`、`headers` 两个属性
- `...otherOption`：把剩下**所有没提取的属性**全部打包放进 `otherOption` 对象

```ts
const option = {
  url: "/api/list",
  method: "GET",
  params: { page: 1 },
  headersType: "application/json",
  headers: { token: "123" }
}

const { headersType, headers, ...otherOption } = option
// headersType = "application/json"
// headers = { token: "123" }
// otherOption = { url: "/api/list", method: "GET", params: { page: 1 } }
```



### 4.2 在对象里：

> `...otherOption` 是 **展开运算符**

```ts
service({
  ...otherOption,
  headers: {
    'Content-Type': headersType || default_headers
  }
})
```

- `...otherOption`是把 `otherOption` 内部所有键值，**平铺展开**到新对象中，等价于手动写：

```
url: otherOption.url,
method: otherOption.method,
params: otherOption.params,
```



## 5.type与interfact

- interface 是**对象专用模板**，支持继承、自动合并、约束类；

- type 是通用类型别名，能写联合、基础类型，但不能合并。

  >  通用规范：**数据模型用 interface，工具类型用 type**

```ts
// interface 写法
interface User {
  id: number
  name: string
  age?: number   			 // 年龄可选
  readonly createTime: Date  //只能赋值一次，后面不能修改
}

// type 完全等价写法
type User = {
  id: number
  name: string
  age?: number
  readonly createTime: Date
}

// 使用方式一模一样
const user: User = { id: 1, name: "张三", createTime: new Date() }
```

type常用法

```ts
//定义基础类型、联合、元组
// interface 不支持，type 专属
type Num = number
type Status = "success" | "error"
type Point = [number, number]

```



- &` 和（交叉）：**全部都要满足，一个都不能少**

>  你写的 `A & B & {c:boolean}` 就是：同时拥有 A 所有属性 + B 所有属性 + 自定义对象的 c 属性
>
> 就是**集合的并集，并 去重**

```
//同时交叉多个类型
type A = { a: number }
type B = { b: string }
type C = A & B & { c: boolean }

{
  a: number
  b: string
  c: boolean
}
```

```
type A = { id: number; name: string }
type B = { id: number; age: number }
type C = A & B

{ id: number; name: string; age: number }
```



## 6.类型守卫（Type Guard）

- 当函数返回 `true` 的时候，**TS 会把变量 `val` 的类型收窄为 `string`**。

```ts
type Value = string | number | boolean

//TS 会把变量 `val` 的类型收窄为 `string
function isString(val: Value): val is string {
  //如果传入的值是字符串，返回 true，否则返回 false
  return typeof val === 'string'
}

function test(x: Value) {
  if(isString(x)){
    // 这里 x → string
    console.log(x.length)
  }else{
    // 这里 x → number | boolean
  }
}
```



## 7.keyof

- 字面量联合类型

  ```ts
  // 定义一个对象类型
  interface Person {
    name: string
    age: number
    address: string
  }
  
  // keyof Person 得到： "name" | "age" | "address"
  type PersonKeys = keyof Person/
  ```

  

- K extends keyof T

  ```ts
  // 定义一个对象类型
  interface Person {
    name: string
    age: number
    address: string
  }
  
  // keyof Person 得到： "name" | "age" | "address"
  type PersonKeys = keyof Person
  
  /**
   * T：传入对象类型
   * K extends keyof T：K 必须是 T 的属性名（只能是 name/age/address）
   */
  function getProperty<T, K extends keyof T>(obj: T, key: K): T[K] {
    return obj[key]
  }
  
  const p: Person = {
    name: "小明",
    age: 18,
    address: "武汉"
  }
  
  // ✅ 合法，key是Person存在的字段
  const n = getProperty(p, "name")
  const a = getProperty(p, "age")
  
  // ❌ 报错！"phone" 不在 keyof Person 联合类型里面，TS直接拦截
  const phone = getProperty(p, "phone")
  ```

