# 参考文档

- [vite-config.ts配置手册](https://vitejs.cn/vite3-cn/config/build-options.html#build-sourcemap)

- [Vue Router接口文档](https://router.vuejs.org/zh/api/interfaces/Router.html#Methods-beforeEach)
- [Vue Router教程](https://router.vuejs.org/zh/guide/essentials/nested-routes.html)

# vue3原理

## 1.@ 是什么

>  别名路径 `@/views/layout/com`
>
>  Vue 脚手架默认配置：`@` 等价于 `src/`

```
@/views/layout/com
完整等价：src/views/layout/com
```



## 2. App.vue  与 index.html 是什么关系

先给核心结论：

> **`index.html` 是整个项目唯一的原生 HTML 入口文件（页面外壳）**
>
> **`App.vue` 是 Vue 的根组件，最终被渲染到 index.html 里面的挂载节点上**

### 1. 结构分层理解

**① index.html（原生 HTML，不属于 Vue 组件）**

```html
//文件里一定会有一个挂载容器：
<!-- index.html -->
<body>
  <div id="app"></div>
</body>
```

> - 它是浏览器直接加载的**第一个页面**
> - 里面没有任何 Vue 语法，就是普通 html
> - `<div id="app">` **只是一个空容器**，本身没有内容



**② main.js/main.ts（桥梁，关键！）**

```js
import { createApp } from 'vue'
import App from './App.vue'

// 创建App组件实例，挂载到 index.html #app 容器
createApp(App).mount('#app')

✅ `createApp(根组件选项)`：把 App 定义为应用的根组件
Vue 从这一个组件开始渲染，所有后续渲染的组件，都只能作为它的后代。
```

> **流程**：`App.vue` → 生成 Vue 组件实例 → 渲染内容 → **替换 / 填充 index.html 的 `<div id="app">`**



**③ App.vue（Vue 根组件）**

- 整个 Vue 应用的**根组件**，所有页面、子组件都是它的后代。

- 渲染完成后，App.vue 的模板内容会出现在 `<div id="app"></div>` 内部。



### 2. 渲染时序流程（浏览器加载顺序）

1. 浏览器请求服务器 → 返回 **index.html**
2. 解析 html，发现引入的 js（main.js）
3. 执行 main.js
4. 导入 `App.vue`（vue 文件经过编译器编译成 render 函数）
5. `createApp(App).mount('#app')`
6. Vue 将 App.vue 的模板渲染成 DOM
7. 把生成的 DOM **插入 index.html 的 #app 容器中**

最终浏览器看到的 DOM 结构简化：

```html
<body>
  <div id="app">
    <!-- 这里全部是 App.vue <template> 渲染出来的内容 -->
    <router-view/>
  </div>
</body>
```



## 3.router-view标签是干什么的

- `<router-view />` 是 **vue-router 提供的内置组件**。

- 作用：**路由页面的（渲染）【占位槽】**。

  > 当路由地址变化时，匹配到的页面组件，会自动渲染到 `<router-view>` 的位置。

App.vue

```vue
<template>
    <!-- 访问 /home ，Home.vue 就渲染在这里 -->
    <!-- 访问 /about ，About.vue 替换渲染在这里 -->
    <router-view />
</template>
```



## 4.常用标签

### 	4.1 [v-if](https://cn.vuejs.org/guide/essentials/conditional)

- 用于**条件性地**渲染一块内容。中有在指令的表达式返回true时 才被渲染

### 	4.2 [v-for](https://cn.vuejs.org/guide/essentials/list.html)

基于一个数组来渲染一个列表

- 数组：(item, index) in 数组      → 【元素，下标】 
- 对象：(value, key, index) in 对象 → 【属性值，属性名，序号】

```vue
const data = {a:1,b:2}
<div v-for="(v,k) in data">{{k}}</div>

答案："a"、"b"（字符串键名）
```







# vue3日常用法

## 0.ref 对象与普通对象的区别

### 	0.1 ref()是啥

- `ref()` 返回的是 **`RefImpl` 实例对象**（Ref Implement，响应式引用对象）

  > ref 就是给基础类型包一层带 getter/setter 的外壳，让 Vue 能够追踪读写、实现响应式。

### 	0.2 响应式数据 是什么

- ref()就是定义【响应式数据】

  > **响应式数据 = 数据变 → 视图自动跟着变；不用你手动操作 DOM。**

  

## 1.ref vs reactive 完整对比

### 1.1核心本质

- `ref()`

接收**任意类型**（基础类型、对象、数组都行）

返回 `RefImpl` 包装对象，**必须通过 `.value` 读写内部数据**

```ts
import { ref } from 'vue'
const count = ref(0)
count.value = 10

const list = ref([])
list.value.push({name: '测试'})
```

- `reactive()`

**只接收对象 / 数组（不能传 string、number、boolean）**

返回 Proxy 代理对象，**没有 `.value`**，直接访问属性

```
import { reactive } from 'vue'
const state = reactive({
  count: 0,
  name: ''
})
state.count = 10
```

### 1.2关键差异清单

|  对比项  |              ref              |          reactive           |
| :------: | :---------------------------: | :-------------------------: |
| 支持类型 |   基础类型、对象、数组均可    | 仅对象、数组；不支持原始值  |
| 访问方式 |           `.value`            | 直接访问属性，无需 `.value` |
| 重新赋值 |             ✅支持             |    ⚠️直接替换会丢失响应式    |
|   解构   |      直接解构丢失响应式       |     直接解构丢失响应式      |
| 模板使用 | 模板自动解包，不用写 `.value` |          直接使用           |

- ⚠️reactive 重大坑

```
const state = reactive({ count: 0 })
// ❌错误！state 指向新对象，切断响应式
state = { count: 99 }

// ✅正确，修改内部属性
state.count = 99
```

```
const count = ref(0)
count.value = 99 // ✅完全没问题
```

### 1.3解构响应式丢失问题

```
// reactive
const state = reactive({ name: '' })
const { name } = state 
name = 'xxx' // ❌不触发响应式

// ref
const info = ref({ name: '' })
const { name } = info.value
name = 'xxx' // ❌同样失效
```

- `ref()` 返回一个 **Ref 包装对象**。

  `.value` 是这个对象上的属性，用来存放**真正的数据**。

```ts
const list = ref([])
// list 是Ref包装对象
// list.value 才是原始数组 
// push 是组数的方法
list.value.push({name:'测试'})

```

```ts
// ref：任何类型都包一层，必须 .value
const obj = ref({name:'a'})
obj.value.name = 'b'

// reactive：只接收对象/数组，没有 .value
const obj2 = reactive({name:'a'})
obj2.name = 'b'
```





## 2.import

**`import App from './App.vue'` → 具名 / 默认导入：拿到导出的值，存到变量 `App`**

**`import './permission'` → 无接收变量，只执行模块代码**

> ES Module 规则：只要写 `import '路径'`
>
> **浏览器 / 打包工具会加载并执行这个 js 文件里面所有代码，但不获取任何导出变量。**





## 3.router.beforeEach

> 作用：在每次跳转连接时 **先执行** 此操作

```
router.beforeEach(async (to, from, next) => {
  // to：目标路由 RouteLocationNormalized（想去哪）
  // from：当前路由 RouteLocationNormalized（从哪来）
  // next：放行函数，**控制这次路由跳转走向**
})

👉 不调用 next ()，路由会卡住，页面白屏，永远不会跳转！
👉 一旦调用 next，导航流程继续执行；你传入不同参数，代表不同跳转指令。

next(false) 【终止本次导航，取消跳转】
next('/login') 【传入字符串路径：强制重定向到新地址】
next() 【无参数：正常放行】
```



假设浏览器地址：http://xxx.com/#/user/profile?id=100#info

> 路由地址部分：`/user/profile?id=100#info`

```js
to.path      // "/user/profile"
to.fullPath  // "/user/profile?id=100#info"

to.query     // { id: "100" }
to.hash      // "#info"
```



## 4.defineComponent

### **1.作用：以下三段代码是一个意思**

```vue
// setup返回渲染函数（h/JSX）
<script>
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'Layout',
  setup() {
    // setup 直接返回渲染函数，不再写 <template>,这是Vue JSX 语法。
    return () => (<section>来自渲染函数</section> )
  }
})
</script>


// 传统写法 A
<template>
  <section>模板内容</section>
</template>
<script>
    export default {
      name: 'Layout',
    }
</script>


//传统写法 B
<template>
  <section>{{ msg }}</section>
</template>
<script setup>
	const msg = 'hello'
</script>
```

| 维度     | template 模板写法                               | setup 返回渲染函数（渲染函数 / JSX）     |
  | -------- | ----------------------------------------------- | ---------------------------------------- |
  | 代码位置 | `<template>` 单独区块                           | 没有 template，模板写在 JS 里面          |
  | 编译阶段 | Vue Loader 单独编译 template → 生成 render 函数 | 直接手写 render 逻辑，编译器不再解析模板 |



### 2. 适用场景（渲染函数擅长解决的问题）

1. 组件结构高度动态，模板语法很难实现

   比如：根据后端返回配置，动态生成不同标签、不同层级组件；模板 v-if 嵌套会极其臃肿。

2. 封装高阶组件、通用容器、工具型组件

   很多 UI 库底层组件（Table、Form、递归菜单）大量使用渲染函数，方便程序化构造虚拟 DOM。

3. 大量重复 DOM 逻辑，想用原生 JS 方式循环、分支

   不需要记忆 Vue 指令，直接使用 `map`、三元表达式、if/else。
   
   

### 3.什么是渲染函数

#### 3.1一句话讲明白「渲染函数 render」

​	Vue 页面最终要生成真实 DOM，但是 Vue **不直接操作 DOM**，而是先产出 **虚拟 DOM（VNode）**。

​	**渲染函数 `render()`：就是一段用来生成虚拟 DOM（VNode）的函数。**

> 所有 `.vue` 的 `<template>`，最终都会被编译器**自动编译成 render 函数**。
>
> 你写模板 ≠ Vue 底层执行；
>
> render 函数 = Vue 真正执行用来构建页面的代码。

#### 3.2最简溯源

- **方式 1：写 template（我们日常写法）**

```vue
<template>
  <div>Hello</div>
</template>
```

​	Vue Loader 编译后，自动生成等价代码：

```js
render() {
  return h('div', null, 'Hello')
}
//h()全称 createVNode，作用：创建虚拟节点 VNode。
```



- **方式 2：自己手写 render（不写 template）**

```ts
defineComponent({
  setup() {
    // setup 返回一个函数，这个函数就充当 render
    return () => h('div', null, '来自渲染函数')
  }
})

//jsx写法-简单
defineComponent({
  setup() {
    // setup 返回一个函数，这个函数就充当 render
    return () =>(<div>来自渲染函数</div>)
  }
})

```



### 4.JSX原理

1. **渲染函数 = render 函数**
2. **h () = 创建虚拟 DOM 节点（VNode）的工具函数**
3. **JSX 只是 h () 的语法糖**

jsx 案例

```vue
// JSX写法（语法糖）
<div class="box">hello</div>

// 等价于原生h调用
h('div', { class: 'box' }, 'hello')
```

> 所以：你写的 `return () => (<div/>)`
>
> 本质就是：`return () => h('div',...)`

### 5.setup 返回函数 和 render () 的关系（Vue3 重点！）

Vue3 两种写法效果一致：

```js
// 写法A：标准render选项
defineComponent({
  render() {
    return h('div', null, 'test')
  }
})

// 写法B：setup返回函数（语法简化）
defineComponent({
  setup() {
    return () => h('div', null, 'test')
  }
})
```

> 规则：**setup 如果返回一个函数，这个函数会被当做组件的 render 渲染函数。**

⚠️ 重要限制：

一旦使用渲染函数（手写 render/setup 返回渲染函数），**该组件不能再写 `<template>`**，二选一。



## 5.路由

### 5.1 动态路由

```js
 {
    path: '/redirect',
    component: Layout,
    name: 'RedirectRoot',
    children: [
      {
        path: '/redirect/:path(.*)',
        name: 'Redirect',
        component: () => import('@/views/Redirect/Redirect.vue'),
        meta: {}
      }
    ],
    meta: {
      hidden: true,
      noTagsView: true
    }
  },    
```

> /redirect/:path(.*) 是 vue-router 的动态路由段 + 自定义正则语法，拆开看是两部分：
> /redirect/:path(.*)
>             ├───┬─┘
>             │   └── (.*)：给参数加的正则规则
>             └── :path：参数名，匹配到的值存在 route.params.path 里

 ### 5.2 子路由

```js
{
    path: '/',
    component: Layout,
    redirect: '/index',
    name: 'Home',
    meta: {},
    children: [
      {
        path: 'index',
        component: () => import('@/views/Home/Index.vue'),
        name: 'Index',
        meta: {
          title: 'router.home',
          icon: 'ep:home-filled',
          noCache: false,
          affix: true
        }
      }
    ]
  },
```

> redirect: '/index', 表示指向 /index组件。 
>
> 在children[{path: 'index'}]的路径全名为：**因为子路由 `path: 'index'` 是相对路径，会和父路由的 `path: '/'` 拼接，得到完整路径 `/index`**



### 5.3 query与params 区别

> 句话速记：
>
> **query = url 问号后面 `?id=1`，随便传；**
>
> **params = 路径里 `/about/1`，路由必须提前声明 `:id`**

**query（查询参数）**

```ts
router.push({ path: '/about', query: { id: 123 } })
// 浏览器地址：/about?id=123
```

**params（动态路径参数）**

```ts
// 路由必须预先配置 path: '/about/:id'
router.push({ name: 'about', params: { id: 123 } })
// 浏览器地址：/about/123
```

### 5.4router 和 route  区别

- **router = 路由实例（控制器，用来跳转页面）**

- **route = 当前路由信息（页面地址、参数，只读）**

5.5 router
