#!/bin/bash

: '
if 语句语法格式： 
if condition 
then 
	command1 
	command2 ...
	commandN 
fi
写成一行（适用于终端命令提示符）： 
if [ $(ps -ef | grep -c "ssh") -gt 1 ]; then echo "true"; fi
'
a=1
b=2
if [ $a == $b ];then
	echo "a=b"
else
	echo "a!=b"
fi

if [ $a == $b ]; then echo "=="; else echo "!="; fi




: '
for 循环
for var in item1 item2 ... itemN do
	command1 
	command2 ...
	commandN 
done

写成一行： 
for var in item1 item2 ... itemN; do command1; command2… done;
'

for loop in 1 2 3 4 5
do
	echo "$loop"
done

echo "-------"

for loop in 1 2 3; do echo $loop; done



# while
n=1 
while(( $n<=10 )) 
do
	echo $n
	if (( $n==10 )); then break; fi
	
	((n += 1))
	 
done
