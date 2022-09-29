#!/bin/bash
#
#表达式和运算符之间要有空格，例如 2+2 是不对的，必须写成 2 + 2
#
n=`expr 1 + 2`
echo "简单数据计算：${n}"

a=10
b=20
echo "`expr $a + $b`"
echo "`expr $a - $b`"
echo "`expr $a \* $b`"  #注意乘法计算
echo "`expr $a / $b`"
echo "`expr $a % $b`"

echo 测试