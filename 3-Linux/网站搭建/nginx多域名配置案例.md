# 多域名配置案例

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
        # 启用HTTP/2
        listen 443 ssl;
        server_name goldlong.site www.goldlong.site;
        
        # 修复证书路径问题（移动到安全目录）
        ssl_certificate /etc/nginx/ssl/goldlong.site/goldlong.site_bundle.crt;
        ssl_certificate_key /etc/nginx/ssl/goldlong.site/goldlong.site.key;
        
        ssl_session_timeout 5m;
		ssl_protocols TLSv1.2 TLSv1.3; 
		ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE; 
		ssl_prefer_server_ciphers on;
        		   
				   
		location / {
			proxy_pass http://127.0.0.1:8888/;
		   
			proxy_set_header Host $host;
			proxy_set_header X-Real-IP $remote_addr;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_set_header X-Forwarded-Proto $scheme;

			proxy_connect_timeout 30s;
			proxy_send_timeout 60s;
			proxy_read_timeout 60s;
		}
  
        # 日志配置（移动到server块内）
        access_log /var/log/nginx/goldlong.site.access.log;
        error_log /var/log/nginx/goldlong.site.error.log;
    }
	
	 server {
        # 启用HTTP/2
        listen 443 ssl;
        server_name info.goldlong.site;
        
        # 修复证书路径问题（移动到安全目录）
        ssl_certificate /etc/nginx/ssl/info.goldlong.site/info.goldlong.site_bundle.crt;
        ssl_certificate_key /etc/nginx/ssl/info.goldlong.site/info.goldlong.site.key;
        
        ssl_session_timeout 5m;
		ssl_protocols TLSv1.2 TLSv1.3; 
		ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE; 
		ssl_prefer_server_ciphers on;
        		   
				   
		location / {
			proxy_pass http://127.0.0.1:3900/;
		   
			proxy_set_header Host $host;
			proxy_set_header X-Real-IP $remote_addr;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			proxy_set_header X-Forwarded-Proto $scheme;

			proxy_connect_timeout 30s;
			proxy_send_timeout 60s;
			proxy_read_timeout 60s;
		}
  
        # 日志配置（移动到server块内）
        access_log /var/log/nginx/info.access.log;
        error_log /var/log/nginx/info.error.log;
    }
	
	
    server {
        listen 80;
        server_name goldlong.site www.goldlong.site info.goldlong.site;
        return 301 https://$host$request_uri;
    }
}

```

