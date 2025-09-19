# spring mvc 入门文章

- [4. 全局统一返回](http://www.iocoder.cn/Spring-Boot/SpringMVC/)
- [5. 全局异常处理](http://www.iocoder.cn/Spring-Boot/SpringMVC/)
- [6. HandlerInterceptor 拦截器](http://www.iocoder.cn/Spring-Boot/SpringMVC/)



# Spring Security

- [官网教程](https://docs.spring.io/spring-security/reference/5.8/servlet/authentication/architecture.html)
- [spring-security-docs 6.5.4 API](https://docs.spring.io/spring-security/reference/api/java/index.html)

**authentication [ɔ,θɛntɪ'keʃən]  认证**

> 认证解决“你是谁”的问题.

**authorization [,ɔθərɪ'zeʃən]  授权**

> 授权解决“你能做什么”的问题.



| 接口名称                   | 触发时机                   | 主要作用             | 典型使用场景                  |
| :------------------------- | :------------------------- | :------------------- | :---------------------------- |
| `OncePerRequestFilter`     | 每个请求生命周期内一次     | 自定义过滤逻辑       | 日志记录、权限检查            |
| `AccessDeniedHandler`      | 用户有权限但访问被拒绝时   | 处理访问被拒绝的响应 | 返回 403 错误信息             |
| `AuthenticationEntryPoint` | 用户未认证时访问受保护资源 | 处理未认证用户的请求 | 返回 401 错误或重定向到登录页 |