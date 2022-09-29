#!/bin/bash


m=10
n=20
#数字判断变量必须为$[] -ne:不相等
if test $[m] -eq $[n]
then 
	echo "相等"
else
	echo "数字不相等"
fi


a="hadoop"
b="spark"
if test $a = $b # !=
then
	echo "相等"
else
	echo -e "字符串不相等 \n"
fi
