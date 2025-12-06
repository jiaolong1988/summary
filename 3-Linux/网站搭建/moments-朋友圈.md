

# docker-compose.yml

```
services:
  moments:
    image: kingwrcy/moments:latest  # 使用最新版本镜像
    container_name: moments  # 容器名称
    restart: always  # 自动重启
    environment:
      PORT: 3000  # 容器内部端口
      JWT_KEY: 3a2b3df1-8395-4d4a-b6c2-11fd655f4fb2
    ports:
      - 3900:3000  # 主机3900端口映射到容器3000端口
    volumes:
      - /root/moments/data:/app/data  # 数据持久化到主机目录
```



# docker

```
docker run -d \
  -e PORT=3000 \
  -e JWT_KEY=3a2b3df1-8395-4d4a-b6c2-11fd655f4fb2 \
  -p 3900:3000 \
  -v /root/moments:/app/data \
  --name moments \
  kingwrcy/moments:latest
```

