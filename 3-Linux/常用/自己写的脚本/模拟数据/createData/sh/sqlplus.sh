#配置文件地址
proDir='./*.properties'
proFilePath=`ls ${proDir}`


#配置文件信息
prop(){
    grep "${1}" $proFilePath | cut -d'=' -f2 | sed 's/\r//'
}

#数据库账号
dbUser=$(prop jdbc.user)
dbPwd=$(prop jdbc.password)
dbUrl=$(prop jdbc.jdbcUrl | cut -d'@' -f2)

echo $dbUser $dbPwd $dbUrl

#登录oracle 执行sql脚本
sqlplus $dbUser/$dbPwd@$dbUrl <<EOF
  start "${1}";
  exit; 
EOF
