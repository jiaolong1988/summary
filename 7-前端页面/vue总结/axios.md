# 参考

1. [axios官网](https://axios.rest/zh/pages/advanced/create-an-instance.html)
2. [axios文档](https://www.axios-http.cn/docs/req_config)
3. [qs-github](https://github.com/ljharb/qs)
4. [qs-中文文档](https://nodejs.cn/api/querystring.html)

# 1.axios

## 1.1 安装

- pnpm install axios



## 1.2 创建实例

- axios.create([config])

```ts
// 创建实例时配置默认值
const instance = axios.create({
  baseURL: 'https://api.example.com',
  timeout: 30000
})

1.项目启动、第一次导入这个文件时，代码从上到下执行一遍；
2.只会调用一次 axios.create()，生成唯一的 instance 对象；
3.后续任何页面、组件再 import request from '@/config/axios'，不会重新执行 axios.create，直接复用第一次生成好的 instance。

```

> **使用instance实例，进行网络请求**

```TS
// 类似于Axios API,语法：axios(config)
instance({
  url: '/users',
  method: 'get',
  timeout: 1000
});
```

**注意：**

- 此时的config为：**调用实例instance传入的的config值**  与 **创建实例instance的默认值**  合并后的config;
- timeout 以 传入的为准，参考：配置优先级

```ts
{
  baseURL: 'https://api.example.com'
  url: '/users',
  method: 'get',
  timeout: 1000,
}
```

## 1.3 默认配置优先级

文档：[默认配置优先级](https://www.axios-http.cn/docs/config_defaults)

> 配置将会按优先级进行合并。它的顺序是：
>
> 在[lib/defaults/index.js](https://github.com/axios/axios/blob/v1.x/lib/defaults/index.js)中找到的库默认值，**然后**是实例的 `defaults` 属性，**最后**是请求的 `config` 参数。
>
> ==后面的优先级要高于前面的==

``` ts
// 使用库提供的默认配置创建实例
// 此时超时配置的默认值是 `0`
const instance = axios.create(
  timeout: 1000
);

// 重写库的超时默认值
// 现在，所有使用此实例的请求都将等待2.5秒，然后才会超时
instance.defaults.timeout = 2500;

// 重写此请求的超时时间，因为该请求需要很长时间
instance.get('/longRequest', {
  timeout: 5000
});
```

- [请求配置文档](https://www.axios-http.cn/docs/req_config)

 params 和 data 区分

- `params`：GET/DELETE 使用，放在 URL `?` 后，由 `paramsSerializer` 自动序列化；
- `data`：POST/PUT 使用，放在请求体 body，表单格式需要手动 `qs.stringify`



# 2.qs

- 安装

  > pnpm install qs

- qs.stringify(params, { : true })

  ```ts
  const params = { user: { name: "李四" } }
  qs.stringify(params)
  // 输出：user[name]=李四
  
  const params = { user: { name: "李四" } }
  qs.stringify(params, { allowDots: true })
  // 输出：user.name=李四
  ```

