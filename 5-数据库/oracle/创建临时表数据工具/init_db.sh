#�����ļ���ַ
proDir='./config/*.properties'
proFilePath=`ls ${targetDir}`

#�����ļ���Ϣ
prop(){
    grep "${1}" datadencrypt.properties | cut -d'=' -f2 | sed 's/\r//'
}

#��ѯ�������
systmeCode=$(prop sourceSystem)
encryptType=$(prop server_cfg.encryptMethod)
tmpTableName=$(prop tempTableName)

#���ݿ��˺�
dbUser=$(prop jdbc.user)
dbPwd=$(prop jdbc.password)
dbUrl=$(prop jdbc.jdbcUrl | cut -d'@' -f2)

echo "systmeCode: $systmeCode    encryptType: $encryptType    tmpTableName: $tmpTableName"
echo $dbUser $dbPwd $dbUrl

#sql�ű�ģ��
sqlFile=`ls ./sqlScript/*.sql`
cp  $sqlFile "$sqlFile.bak"

#�����Ŀ����е�sql�ű�
runSqlFile=`ls ./sqlScript/*.bak`
echo "bak: $runSqlFile" 

#�滻�ı�
#sed -i "s/#systmeCode#/$systmeCode/g" $runSqlFile 
#sed -i "s/#tmpTableName#/$tmpTableName/g" $runSqlFile
$(cat sqlScript/create_temp_table.sql | \
sed "s/#systmeCode#/$systmeCode/g" |  \
sed  "s/#tmpTableName#/$tmpTableName/g" > $runSqlFile) \


#��¼oracle ִ��sql�ű�
sqlplus $dbUser/$dbPwd@$dbUrl <<EOF
  start ${runSqlFile}
exit; 
EOF

