[TOC]

### git提交命令

- git init  
- git add -A
- git commit -m "机器学习项目提交"
- git status

### 添加远程仓库地址
> git remote add origin https://github.com/jiaolong1988/AI-Base.git



### 文件上传至远程库

> git push -u origin master



### 获取远程库的最新信息

> git pull origin master



### 克隆项目到本地

> git clone https://github.com/jiaolong1988/test.git



### 提交出现异常

> fatal: remote origin already exists.  
>
> **解决方法**
>
> 1. git remote -v 查看远程库信息
> 2. git remote rm origin 删除远程库列表



### 删除文件

​	删除文件夹

> git rm -r 文件夹

​	删除文件

>  git rm  p0/pycharm-professional-2019.1.3.exe





#### 删除.git文件夹中最大的文件

1. 查询前5个最大的文件

   > git verify-pack -v .git/objects/pack/pack-*.idx | sort -k 3 -g | tail -5
   > 如果该目录没有信息，执行此命令：git gc

2. 查找文件名称

   > git rev-list --objects --all | grep 3ef8dfc43184

3. 通过索引删除文件

   > git filter-branch --index-filter 'git rm --cached --ignore-unmatch  p0/pycharm-professional-2019.1.3.exe'

4. 删除目录

   > rm -Rf .git/refs/original .git/logs/

5. 执行删除操作

   > git gc --prune=now



### git 提交命令

> 1. git add -A      提交所有变化.
> 2. git add -u      提交 被修改和被删除文件，新创建文件无法提交。
> 3. git add .        提交新文件和 被修改(modified)文件，被删除文件无法提交

### .gitignore忽略指定文件

1. .gitignore 文件用来告诉 git 不应跟踪的文件。该文件应该放在 .git 目录所在的目录。

2. .gitignore文件用来告诉Git那些文件不需要跟踪。

   	> 1.  在工作目录中创建该文件
    > 2.  echo "test" >> .gitignore 
> 3. 添加内容参考：
   >    https://github.com/MLEveryday/100-Days-Of-ML-Code/blob/master/.gitignore



### 注意

> **git 对文件夹大小写不敏感**