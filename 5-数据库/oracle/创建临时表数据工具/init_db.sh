#配置文件地址
proDir='./config/*.properties'
proFilePath=`ls ${targetDir}`

#配置文件信息
prop(){
    grep "${1}" datadencrypt.properties | cut -d'=' -f2 | sed 's/\r//'
}

#查询任务参数
systmeCode=$(prop sourceSystem)
encryptType=$(prop server_cfg.encryptMethod)
tmpTableName=$(prop tempTableName)

#数据库账号
dbUser=$(prop jdbc.user)
dbPwd=$(prop jdbc.password)
dbUrl=$(prop jdbc.jdbcUrl | cut -d'@' -f2)

echo "systmeCode: $systmeCode    encryptType: $encryptType    tmpTableName: $tmpTableName"
echo $dbUser $dbPwd $dbUrl

#sql脚本模板
sqlFile=`ls ./sqlScript/*.sql`
cp  $sqlFile "$sqlFile.bak"

#真正的可运行的sql脚本
runSqlFile=`ls ./sqlScript/*.bak`
echo "bak: $runSqlFile" 

#替换文本
#sed -i "s/#systmeCode#/$systmeCode/g" $runSqlFile 
#sed -i "s/#tmpTableName#/$tmpTableName/g" $runSqlFile
$(cat sqlScript/create_temp_table.sql | \
sed "s/#systmeCode#/$systmeCode/g" |  \
sed  "s/#tmpTableName#/$tmpTableName/g" > $runSqlFile) \


#登录oracle 执行sql脚本
sqlplus $dbUser/$dbPwd@$dbUrl <<EOF
  start ${runSqlFile}
exit; 
EOF

