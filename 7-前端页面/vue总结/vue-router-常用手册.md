### 路由

- [嵌套路由 | Vue Router](https://router.vuejs.org/zh/guide/essentials/nested-routes.html)

## 1 动态路由

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
>          ├───┬─┘
>          │   └── (.*)：给参数加的正则规则
>          └── :path：参数名，匹配到的值存在 route.params.path 里



## 2.子路由

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



## 3. query与params 区别

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

## 4.router 和 route  区别

- **router = 路由实例（控制器，用来跳转页面）**
- **route = 当前路由信息（页面地址、参数，只读）**



## 5.RouterView 嵌套 与子路由

- App.vue

```vue
<template>
  <router-view /> //根路由选在到这里： path: '/user/:id',
</template>
```

- User.vue

```vue
<template>
  <div class="user">
    <h2>User {{ $route.params.id }}</h2>
    <router-view />  //[children]的所有子路由 都、渲染到这里
  </div>
</template>
```

- router.ts

```ts
const routes = [
  {
    path: '/user/:id',
    component: User,
    children: [
      {
        // 当 /user/:id/profile 匹配成功
        // UserProfile 将被渲染到 User 的 <router-view> 内部
        path: 'profile',
        component: UserProfile,
      },
      {
        // 当 /user/:id/posts 匹配成功
        // UserPosts 将被渲染到 User 的 <router-view> 内部
        path: 'posts',
        component: UserPosts,
      },
    ],
  },
]
```



