#!/bin/bash
# $*   	为"1 2 3"      （一起被引号包住）	test.sh 1 2 3
# $@	为"1" "2" "3"  （分别被引号包住）	
# $# 	为3             (表示参数的数量）	
# $0 表示脚本名称
# $1 表示第一个参数 以此类推

echo "运行脚本的名称：$0"
echo "运行脚本的当前进程ID：$$"
echo "脚本执行完毕后状态(0没有错误)：$?"

echo "-- \$@ 演示 ---" 
for i in "$@"; do 
	echo $i
done