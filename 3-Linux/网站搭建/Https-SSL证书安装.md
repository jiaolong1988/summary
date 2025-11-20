```bash
#证书赋权限-可以不操作
cd /etc/nginx/ssl/goldlong.site
chmod 644 goldlong.site_bundle.crt
chmod 600 goldlong.site.key

#查找443端口
netstat -tulnp | grep :443
#通过 PID 查找docker容器名称
docker ps -q | xargs docker inspect --format '{{.State.Pid}} {{.Name}}' | grep 1234

#域名解析
nslookup goldlong.site
telnet goldlong.site 443
```





# Nginx配置SSL

- nginx.conf

```bash
worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include      /etc/nginx/mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    gzip on;
    gzip_min_length 1k;
    gzip_buffers 4 16k;
    gzip_http_version 1.1;
    gzip_comp_level 2;
    gzip_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss text/javascript;
    gzip_proxied any;
	
    server {
        listen 443 ssl;
        server_name goldlong.site;
        
        #证书路径配置
        ssl_certificate /etc/nginx/ssl/goldlong.site/goldlong.site_bundle.crt;
        ssl_certificate_key /etc/nginx/ssl/goldlong.site/goldlong.site.key;  
        ssl_session_timeout 5m;
		ssl_protocols TLSv1.2 TLSv1.3; 
		ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE; 
		ssl_prefer_server_ciphers on;
        
        location / {
            root   /usr/share/nginx/html;
            index  index.html index.htm;
            try_files $uri $uri/ /index.html;
        }
        
        # 日志配置（移动到server块内）
        access_log /var/log/nginx/goldlong.site.access.log;
        error_log /var/log/nginx/goldlong.site.error.log;
    }
	
    server {
        listen 80;
        server_name goldlong.site;
        return 301 https://$host$request_uri;
    }
}
```

# 服务器端测试

## SSL测试

```bash
#验证证书包含所有需要的域名：
openssl x509 -in /etc/nginx/ssl/goldlong.site/goldlong.site_bundle.crt -text | grep DNS

#检查证书是否配置正确
openssl s_client -connect localhost:443 -servername goldlong.site

#检查证书链
openssl s_client -showcerts -connect localhost:443

#如果异常说明证书的权限有问题
```

## 域名测试

- localhost测试

  > curl -vk https://localhost

- 域名测试

  > curl -vk https://goldlong.site 

如果localhost测试正常，域名测试失败，说明443端口异常。

# Docker代理占用443端口

Docker 容器监听 443 端口 导致你的 SSL 证书无法访问 时，通常是因为 端口冲突 或 Docker 代理干扰。

停止Docker。



# nginx 映射

```bash
worker_processes  1;

events {
    worker_connections  1024;
}

http {
    include      /etc/nginx/mime.types;
    default_type  application/octet-stream;
    sendfile        on;
    keepalive_timeout  65;

    gzip on;
    gzip_min_length 1k;     # 设置允许压缩的页面最小字节数
    gzip_buffers 4 16k;     # 用来存储 gzip 的压缩结果
    gzip_http_version 1.1;  # 识别 HTTP 协议版本
    gzip_comp_level 2;      # 设置 gzip 的压缩比 1-9。1 压缩比最小但最快，而 9 相反
    gzip_types gzip_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss text/javascript; # 指定压缩类型
    gzip_proxied any;       # 无论后端服务器的 headers 头返回什么信息，都无条件启用压缩

server {
    listen 80 default;
    server_name _;
    return 403;
}


    server {
        listen       80;
        server_name  www.goldlong.site; 

        location / { 
                        proxy_pass http://localhost:8888;
        }
    }

        server {
        listen       80;
        server_name  info.goldlong.site; 

        location / { 
                        proxy_pass http://localhost:3900;
        }
    }

```



# 参考

- [SSL 证书 Nginx 服务器 SSL 证书安装部署（Linux)](https://cloud.tencent.com/document/product/400/35244)

- [使用Nigix做映射 - 配置不同域名对应不同端口](https://www.cnblogs.com/goloving/p/9363490.html)
