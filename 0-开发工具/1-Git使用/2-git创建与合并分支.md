[TOC]

### 1. 创建dev分支,并切换到dev分支

> git checkout -b dev

### 2. 查看当前分支(当前分支前面会标一个*号)

> git branch

**....修改文件**....

### 3.切换到master分支

> git checkout master

### 4.将指定分支 合并到 当前分支

> git merge dev（把dev分支内容合并到master分支上）

### 5.删除dev分支

> git branch -d dev



### 注意：

> Git中主分支为master,HEAD指向当前分支。当HEAD指向master时才指向提交。

