# MYSQL安装

```
docker run \
-p 3306:3306 \
--name mysql5.7 \
--network app-network \
-v /root/mysql5.7_docker/log:/var/log/mysql \
-v /root/mysql5.7_docker/data:/var/lib/mysql \
-v /root/mysql5.7_docker/conf:/etc/mysql/conf.d \
--restart=always \
-e MYSQL_ROOT_PASSWORD=root@123456 \
-d mysql:5.7		
```



```
docker run -d \
  -p 3305:3306 \
  --name mysql5.7-wp \
  --network app-network \
  -e MYSQL_ROOT_PASSWORD=root@123456 \
  -v /root/mysql5.7-wp/log:/var/log/mysql \
  -v /root/mysql5.7-wp/data:/var/lib/mysql \
  -v /root/mysql5.7-wp/conf:/etc/mysql/conf.d \
  --restart=always \
  mysql:5.7
```

​																																

##  设置字段存储的最大值

- vim /root/mysql5.7_docker/conf/my.cnf

```
[mysqld]
# 基础配置（可选，补充上更稳定）
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci
default-time_zone = '+8:00'

# 设置最大数据包大小为 128M
max_allowed_packet = 128M

[mysql]
max_allowed_packet = 128M
```

## 创建账号密码

```
docker exec -it mysql5.7 mysql -u root -p

CREATE DATABASE wordpress_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'wp_user'@'%' IDENTIFIED BY 'wp_pass@123';  
GRANT ALL PRIVILEGES ON wordpress_db.* TO 'wp_user'@'%';  


CREATE DATABASE easyappointments CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'easy_user'@'%' IDENTIFIED BY 'easy_pass@123';
GRANT ALL PRIVILEGES ON easyappointments.* TO 'easy_user'@'%';

-- 刷新权限生效
FLUSH PRIVILEGES;

-- 退出 MySQL 终端
EXIT;
```



## 验证
> SELECT VERSION();
> SHOW VARIABLES LIKE 'max_allowed_packet';



# wordpress安装

```
docker run -d \
--name wordpress \
--network app-network \
-p 8888:80 \
-e WORDPRESS_DB_HOST=mysql5.7 \
-e WORDPRESS_DB_NAME=wordpress_db \
-e WORDPRESS_DB_USER=wp_user \
-e WORDPRESS_DB_PASSWORD=wp_pass@123 \
-v /root/wordpress/data:/var/www/html \
-v /root/wordpress/custom-php.ini:/usr/local/etc/php/conf.d/custom.ini \
--restart=always \
wordpress:latest
```

