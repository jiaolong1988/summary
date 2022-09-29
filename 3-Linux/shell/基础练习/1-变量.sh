#!/bin/bash 
echo 'hello world!'

course="hadoop" 
echo "${course}字符串截取：${course:1:1}"
echo "${course}字符串长度：${#course}"
echo "${course}字符串o的位置:" `expr index "$course" h`

unset course
echo "删除变量course $course"

#只读变量不可修改
readonly myUrl="http://www.dajiangtai.com/" 
echo "$myUrl"

#数组
array_name=(value0 value1 value2 value3)
echo "数组第一个字符：${array_name[0]}"
echo "数组所有元素：${array_name[@]}"

length=${#array_name[@]} #或者length=${#course[*]}
echo "数组长度： ${length}"
echo "数组第一个元素长度：${#array_name[0]}"