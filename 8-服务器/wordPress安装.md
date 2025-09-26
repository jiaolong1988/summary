[wordpress 如何使用docker compose 安装文档 - 搜索](https://cn.bing.com/search?q=wordpress 如何使用docker compose  安装文档&qs=n&form=QBRE&sp=-1&lq=0&pq=wordpress 如何使用docker compose  安装wen'd&sc=12-37&sk=&cvid=448B8D2B6D4B4F13A04BE1C6BAFD8A0D)



- 创建目录

```bash
mkdir ~/my_wordpress
cd ~/my_wordpress
```



- 编写 Docker Compose 配置文件

> 在项目目录中创建一个名为 *docker-compose.yml* 的文件，并填入以下内容：

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
     - wordpress_data:/var/www/html
   ports:
     - "80:80"  
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

- 自定义访问端口号

  >  ports:
  >      - "8888:80"   访问的是8888端口,映射到容器的80端口

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
       - wordpress_data:/var/www/html
       - ./custom-php.ini:/usr/local/etc/php/conf.d/custom.ini
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