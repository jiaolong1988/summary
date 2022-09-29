#配置文件地址
proDir='./*.properties'
proFilePath=`ls ${proDir}`

#配置文件信息
prop(){
    grep "${1}" $proFilePath | cut -d'=' -f2 | sed 's/\r//'
}

#读取参数
startNum=$(prop startNum)
endNum=$(prop endNum)
commitNum=$(prop commitNum)
taskNum=$(prop taskNum)
echo "startNum: $startNum    endNum: $endNum   commitNum: $commitNum   taskNum: $taskNum" 

#数据库账号
dbUser=$(prop jdbc.user)
dbPwd=$(prop jdbc.password)
dbUrl=$(prop jdbc.jdbcUrl | cut -d'@' -f2)

echo $dbUser $dbPwd $dbUrl

#清理内存参数
clearMemory=$(prop clearMemory)
#数据类型
datatype=$(prop datatype)
echo "clearMemory: $clearMemory    datatype: $datatype " 

#sql脚本模板
sqlFile=`ls ./sql/create_data.sql`
#真正的可运行的sql脚本
runSqlFile="./sql/data_install.sql"
#赋予执行权限
chmod +x ./sh/sqlplus.sh	

#等待线程直线完毕
watiOver(){
	sleep 1
	
	while :
	do		
		if [[ -z $(ps -ef |grep "${1}" | grep -v grep) ]];then
			echo "over"
			break

		fi
	done
}


for ((taskId=1;taskId<=$taskNum;taskId++))
do	
    #计算执行数据量
	avg=$((($endNum-$startNum)/$taskNum))	
	sn=$(($startNum+($avg*($taskId-1))))
	if [ $taskId -eq $taskNum ];then
		en=$endNum
	else
		en=$(($startNum+$avg*$taskId))	
	fi
	
	echo "[*]pid: $$"
	echo "[*]avg: $avg  startNum: $sn   endNum: $en" 
	
	#替换文本 一次替换
	$(cat $sqlFile | sed "s/#startNum#/$sn/g" | \
	  sed "s/#endNum#/$en/g" |  \
	  sed "s/#commitNum#/$commitNum/g" |  \
	  sed "s/#taskId#/1/g" | \
	  sed "s/#datatype#/$datatype/g" |  \
	  sed 's/"//g' > "$runSqlFile" \
	) 	
		
    #清理内存 当前内存小于指定 值
	memory=`free -h| grep Mem| awk '{print $4}' | sed "s/M//"`
	echo "[*]current: $memory  clearMemory: $clearMemory " 
	if [ $memory -lt $clearMemory ];then
	    echo "[*]memory: $memory   clear Memory"
		echo 1 > /proc/sys/vm/drop_caches
		sleep 5
	fi
	
	#执行添加数据操作
	./sh/sqlplus.sh $runSqlFile 

	#./sh/sqlplus.sh $runSqlFile >>$outRes 2>&1 &
	#echo "☆☆pid：$(ps -ef |grep $! | grep -v grep)" >>$outRes
	#watiOver $!
		
    echo -e "====================================================================\n\r" 
	
done	

echo "[*]task over."





