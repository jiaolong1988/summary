## 前提语法

```ts
// 调用
return request.get({ 
  url: '/infra/api-error-log/page', 
  params 
})
```

**完整等价写法**

```ts
//params 是对象简写,
return request.get({ 
  url: '/infra/api-error-log/page', 
  params: params 
})
```



## 封装方法

```ts
//封装请求方法
post: async <T = any>(option: any) => {
const res = await request({ method: 'POST', ...option })
return res.data as unknown as T
}    
```

## 调用axios实例

- 此时调用的service实例进行网络请求，参数为config对象，**这个config会与实例(service)的默认配置进行合并**，如有重复**以传入的config对象的参数为准**。

```ts
//使用axios实例service，语法axios(config)
const request = (option: any) => {
  const { headersType, headers, ...otherOption } = option
  return service({
    ...otherOption,
    headers: {
      'Content-Type': headersType || default_headers,
      ...headers
    }
  })
}
```

## 创建axios实例

```ts
//创建axios实例service，语法：axios.create([config])
const service: AxiosInstance = axios.create({
  baseURL: base_url, // api 的 base_url
  timeout: request_timeout, // 请求超时时间
  withCredentials: false, // 禁用 Cookie 等信息
    
  // 自定义参数序列化函数 用于处理get 请求的参数拼接。
  paramsSerializer: (params) => {
    return qs.stringify(params, { allowDots: true })
  }
})
```

