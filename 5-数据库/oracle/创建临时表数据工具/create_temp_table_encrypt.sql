select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')timestr from dual;
     
declare
   exist_flag   number;
   systme_code  varchar2(10);
   create_table_sql  varchar2(500);
   create_index_sql  varchar2(500);
   tmp_table_name varchar2(20);
   
   check_temp_num number(10);
   check_flag_num number(10);
   
begin
  --设定初始值 
   systme_code := '#systmeCode#';
   tmp_table_name := '#tmpTableName#'; 
   create_table_sql := 'create table '||tmp_table_name||' nologging parallel 8 as select /*+ parallel(8)*/  t1.r_object_id,rownum as rnum from boc_page_metadata_s t1, (select distinct(t2.pkuuid)as pkuuid from BOC_PKUUID_METADATA_S t2 where t2.sourcesystem='''||systme_code||''') t2 where t1.pkuuid=t2.pkuuid and (t1.extend3 is null or t1.extend3='' '')'; 
   create_index_sql := 'create index idx_'||tmp_table_name||' on '||tmp_table_name||' (rnum) ';
   dbms_output.put_line(create_table_sql);  
   
   --检查临时表是否存在
   execute immediate 'SELECT COUNT(*) FROM user_tables WHERE table_name = upper('''||tmp_table_name||''')' into exist_flag;
   dbms_output.put_line('exist flag: '||exist_flag);
   
   --删除临时表 
   if exist_flag > 0 then  
      execute immediate 'drop table '||tmp_table_name;
      exist_flag := 0 ;
      dbms_output.put_line('del table');
   end if;
   
   --创建临时表
   if exist_flag = 0 then  
      execute immediate create_table_sql;
      execute immediate create_index_sql;
      commit;
      
      exist_flag := 1 ;
      dbms_output.put_line('add table ');
   end if;
   
   --检查
   execute immediate 'select /*+ parallel(8)*/ count(t1.r_object_id) from boc_page_metadata_s t1, (select distinct(t2.pkuuid)as pkuuid from BOC_PKUUID_METADATA_S t2 where t2.sourcesystem='''||systme_code||''') t2 where t1.pkuuid=t2.pkuuid and (t1.extend3 is null or t1.extend3='' '')' into check_flag_num; 
   execute immediate 'select /*+ parallel(8)*/ count(*) from '||tmp_table_name into check_temp_num; 
   dbms_output.put_line('flag_num:'||check_flag_num||' temp_num'||check_temp_num);
           
   if check_flag_num != check_temp_num then
     execute immediate 'drop table '||tmp_table_name;   
     dbms_output.put_line('create table fail ');
   end if;
  
end;

/
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')timestr, '#tmpTableName#' as table_name,tmp_num from dual,   
(SELECT COUNT(*) as tmp_num FROM user_tables WHERE table_name = upper('#tmpTableName#')) 
