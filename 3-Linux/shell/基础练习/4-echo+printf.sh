#!/bin/bash

echo "请输入名称"
read name
echo "请输入密码"
read pwd

echo 姓名：$name , 密码：$pwd


#换行
echo -e "OK! \n" # -e 开启转义 
echo "I want to learn shell"


#
echo "I want to learn shell" > myfile

#原样输出字符串，不进行转义或取变量(用单引号)
echo -e 原样输出：'$name\" \n'   #输出结果：$name"

echo printf 格式化显示
printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg 
printf "%-10s %-8s %-4.2f\n" 张三 男 66.1234 
printf "%-10s %-8s %-4.2f\n" 李四 男 48.6543 
printf "%-10s %-8s %-4.2f\n" 王五 女 47.9876