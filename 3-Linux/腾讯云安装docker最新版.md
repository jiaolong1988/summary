## ✅ 步骤一：卸载旧版 Docker（可选）

如果你之前安装过旧版 Docker，建议先卸载：

```bash
sudo yum remove docker docker-common docker-selinux docker-engine
```

------

## ✅ 步骤二：安装依赖包

```bash
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```

------

## ✅ 步骤三：更新软件包索引

```bash
sudo yum makecache fast
```

------

## ✅ 步骤四：安装 Docker CE（社区版）

```bash
sudo yum install -y docker-ce docker-ce-cli containerd.io

```

> 如果你希望安装特定版本的 Docker，可以使用如下命令查看可用版本：

```bash
yum list docker-ce --showduplicates | sort -r
```

然后指定版本安装：

```bash
sudo yum install -y docker-ce-<version> docker-ce-cli-<version> containerd.io
```

例如：

```bash
sudo yum install -y docker-ce-26.1.0 docker-ce-cli-26.1.0 containerd.io
```



------

## ✅ 步骤五：验证安装

```bash
docker --version
```

输出示例：

```
Docker version 26.1.0, build 893e75a
```

------

## ✅ 步骤六：启动并启用 Docker 服务

```bash
sudo systemctl start docker
sudo systemctl enable docker
```

 

------

## ✅ 步骤七：测试 Docker 是否正常运行

```bash
sudo docker run hello-world
```

如果看到如下输出，说明安装成功：

```
Hello from Docker!
This message shows that your installation appears to be working correctly.
```



最新版docker 已经默认集成 docker compose，验证方式如下：

> docker compose  version



docker-compose 安装

步骤 1：下载文件
sudo curl -L "https://github.com/docker/compose/releases/download/v2.23.0/docker-compose-Linux-x86_64" -o /usr/local/bin/docker-compose
Bash

步骤 2：设置可执行权限
sudo chmod +x /usr/local/bin/docker-compose
Bash

步骤 3：验证安装
docker-compose --version