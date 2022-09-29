#!/bin/bash

: '
shell中函数的定义格式如下： 
[ function ] funname() {
	action; 
    [return int;]
}
说明： 
1、可以带function fun() 定义，也可以直接fun() 定义,不带任何参数。 2、参数返回，可以显示加：return 返回，如果不加，将以最后一条命令运行结果，作为返回值。 return后跟数值n(0-255）
'
myFun()
{ 
	echo $1 "这是我的第一个 shell 函数!";
}

echo "-----函数开始执行-----" 
myFun 1
echo "-----函数执行完毕-----"