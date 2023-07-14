[TOC]

### 自报家门，对代码来源进行标识

> git config --global user.name "jiaolong1988"
> git config --global user.email "jiaolong1234567@126"

### 查看设置的信息

> git config user.name
> git config user.emal

### 创建版本库

1. 初始化创建仓库

   > git init

   

2. 创建一个文件

   > vi readme.txt
   > 	Git is a version control system.
   > 	Git is free software.

3. 把文件 添加 到仓库
    
> git add readme.txt
    
4. 把文件 提交 到仓库
    
> git commit -m "wrote a readme file"
    
5. 查看当前仓库的状态
    
> git status
    
6. 查看文件是否被修改
    
    > git diff 文件名

### 版本回退

1. 查看提交历史
   git log --oneline

2. 恢复的上一个版本
   git reset --hard HEAD^

   > 注意：在Git中，用HEAD表示当前版本，上一个版本HEAD^，上上一个版本就是HEAD^^，当然往上100个版本写成HEAD~100。

3. 根据id号码进行恢复
   git reset --hard f59cf87

4. 查看命令历史
   git reflog

### 工作区和暂存区

1. 工作区是一个目录，在该目录中 有一个隐藏目录.git，这就是git的版本库。
2. Git版本库中最重要的就stage的暂存区，所有的修改必须 git add到 stage中，否则 修改 无法被commit。
3. Git自动创建一个分支master，以及指向master的一个指针叫HEAD。
4. [参考](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/0013745374151782eb658c5a5ca454eaa451661275886c6000)



### git管理与修改

​	1、Git跟踪管理的是修改，而非文件.
​	2、每次修改，如果不用git add到暂存区，那就不会加入到commit中。
​	3、git diff HEAD -- readme.txt（查看 工作区和版本库里面 最新版本的区别）
​	

### 撤销修改
 1. 修改的文件 还未被 git add 到暂存区

    >  **git checkout -- readme.txt**

    

     **把readme.txt文件在工作区的修改全部撤销，这里有两种情况：**

    >	1. 在readme.txt自修改后还没有被放到暂存区，现在，撤销修改就回到和版本库一模一样的状态；
    >	2. 在readme.txt已经添加到暂存区后，又作了修改，现在，撤销修改就回到添加到暂存区后的状态。

​		      **总之，就是让这个文件回到最近一次git commit或git add时的状态。**



2. 修改的文件已 git add 到暂存区
   	> 1、git reset HEAD readme.txt（把暂存区的修改撤销掉（unstage），重新放回工作区）
      	2、git checkout -- readme.txt（删除掉所有修改，恢复到上一版本）

3. 总结：

   > 场景1：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令git checkout -- file。

   > 场景2：已经提交了不合适的修改到版本库时，想要撤销本次提交，参考版本回退一节，不过前提是没有推送到远程库。

   > 场景3：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，

   ​	1. 第一步用命令git reset HEAD <file>，就回到了场景1，

   ​	2. 第二步按场景1操作。

   

### 删除文件
​	git rm test.txt（从版本库中删除 文件）

> ​	注意：当删除文件后，想恢复删除的文件时可执行：
> ​	git checkout -- test.txt

### 远程仓库

0. github创建远程仓库
1. 本地生成key
   
   > ssh-keygen -t rsa -C "jiaolong1234567@126.com"
2. 添加ssh-key(将id_rsa.pub 的key信息添加到 github中)
   
   > [参考地址](https://help.github.com/articles/adding-a-new-ssh-key-to-your-github-account/)
3. 测试
   
   > ssh -T git@github.com
4. 与 远程仓库 关联
   
   > git remote add origin https://github.com/jiaolong1988/Machine_Learning.git
5. 本地库的所有内容推送到远程库上	
   
   > git push -u origin master

 **注意**

1. 由于远程仓库中的README.md文件不在本地代码目录中，因此需代码合并：
2. git pull --rebase origin master
3. [参考](https://jingyan.baidu.com/article/f3e34a12a25bc8f5ea65354a.html)



### 克隆远程库

 - git clone git@github.com:jiaolong1988/gitskills.git

   