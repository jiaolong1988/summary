[wordpress 如何使用docker compose 安装文档 ](https://cn.bing.com/search?q=wordpress 如何使用docker compose  安装文档&qs=n&form=QBRE&sp=-1&lq=0&pq=wordpress 如何使用docker compose  安装wen'd&sc=12-37&sk=&cvid=448B8D2B6D4B4F13A04BE1C6BAFD8A0D)



## 1.wordpress安装

- 创建目录

```bash
mkdir ~/my_wordpress
cd ~/my_wordpress
```

- 编写 Docker Compose 配置文件

  > 在项目目录中创建一个名为 *docker-compose.yml* 的文件，并填入以下内容：
  >
  > - 自定义访问端口号
  >
  >   ​	ports:
  >
  >   - "8888:80"   访问的是8888端口,映射到容器的80端口

```yml
services:
 db:
   image: mysql:5.7
   volumes:
     - db_data:/var/lib/mysql
   restart: always
   environment:
     MYSQL_ROOT_PASSWORD: somewordpress
     MYSQL_DATABASE: wordpress
     MYSQL_USER: wordpress
     MYSQL_PASSWORD: wordpress
 wordpress:
   depends_on:
     - db
   image: wordpress:latest
   volumes:
     - ./wordpress_data:/var/www/html   #本地路径:docker容器文件路径
     - ./custom-php.ini:/usr/local/etc/php/conf.d/custom.ini #上传文件大小配置
   ports:
     - "8888:80"  
     - "443:443"
   restart: always
   environment:
     WORDPRESS_DB_HOST: db
     WORDPRESS_DB_USER: wordpress
     WORDPRESS_DB_PASSWORD: wordpress
     WORDPRESS_DB_NAME: wordpress
volumes:
 db_data: {}
 wordpress_data: {}
 
```

### mysql8

```yml
services:
  wordpress:
    image: wordpress:latest
    container_name: wordpress
    restart: always
    ports:
      - "8080:80"  # WordPress 访问端口
    environment:
      WORDPRESS_DB_HOST: db:3306
      WORDPRESS_DB_USER: wpuser
      WORDPRESS_DB_PASSWORD: securepassword
      WORDPRESS_DB_NAME: wordpress
    volumes:
      - wordpress_data:/var/www/html
    networks:
      - wp-network
    depends_on:
      - db

  db:
    image: mysql:8.0
    container_name: mysql8
    restart: always
    ports:
      - "3306:3306"  # 指定 MySQL 端口，便于外部连接测试
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: wordpress
      MYSQL_USER: wpuser
      MYSQL_PASSWORD: securepassword
    command: 
      - --default-authentication-plugin=mysql_native_password
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    volumes:
      - mysql_data:/var/lib/mysql
    networks:
      - wp-network

volumes:
  wordpress_data:
  mysql_data:

networks:
  wp-network:
    driver: bridge
```





### 设置上传视频大小


- ==创建 `custom-php.ini` 文件 **设置上传文件大小**==

  > 在与 `docker-compose.yml` 同一目录下创建一个名为 `custom-php.ini` 的文件，内容如下：
  >
  > ```ini
  > upload_max_filesize = 256M
  > post_max_size = 256M
  > memory_limit = 512M
  > max_execution_time = 300
  > max_input_time = 300
  > ```

- 启动容器

  > sudo docker-compose up -d

启动完成后，可以通过 *http://ip:port* 访问 WordPress（将 *ip* 替换为服务器的 IP 地址，*port* 替换为使用的端口，如果是 80 端口则可以省略）。



## 2.视频转码

- 浏览器只支持有限的视频编码格式：

  ```
  常见兼容：MP4 (H.264 + AAC)
  
  不支持：MP4 (HEVC/H.265, VP9, AV1) → Chrome/Firefox 上常见黑屏但有声音
  
  ✅ 解决方法：把视频转码成浏览器通用的 H.264 + AAC
  ```

- [下载视频转码软件ffmpeg]( https://www.gyan.dev/ffmpeg/builds/)

  > 下载文件名：ffmpeg-git-essentials.7z，解压后将安装目录放到win系统的path系统变量中，到bin目录那层。

- 解压命令

  > ffmpeg -i test.mp4 -vcodec libx264 -acodec aac output.mp4



## 3.安装插件

- Simple Local Avatars		设置头像

