[TOC]

### git add

命令将 文件 添加到暂存区：
git add css/app.css js/app.js
git add .
	

### git rm --cached <file>...

不会破坏任何属于你的工作区文件，它只是从暂存区删掉了文件。

> git rm --cached index.html 
> 将文件从工作目录移到暂存区叫做"staging"（暂存）。

> 如果已移动文件，则叫做"staged"（已暂存）。
> 从暂存区将文件移回工作目录将"unstage"（撤消暂存）。
> 如果你阅读的文档中提示“stage the following files”，则表明你应该使用 git add 命令。

### git commit 
​	git commit -m"内容"
​	
### git diff
	1. 命令会显示：
		已经修改的文件
		添加/删除的行所在的位置
		执行的实际更改

2. git log -p 其实就是在后台使用了 git diff，所以和运行 git log -p 的结果一样

