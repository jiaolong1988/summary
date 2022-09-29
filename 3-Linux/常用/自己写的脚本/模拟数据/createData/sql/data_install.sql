select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')timestr from dual;

DECLARE 
  --当前插入数据量  共插入数据量  每次提交数量  任务id
  current_num number;
  total_num number;
  commit_num number;
  task_id number;
  
BEGIN
  current_num:=60285714;
  total_num:=63074285;
  commit_num:=1000;
  task_id:=1;
  
  WHILE current_num < total_num LOOP
	current_num:=current_num+1;
	-- BOC_PAGE_METADATA_S 插入数据
	INSERT INTO BOC_PAGE_METADATA_S (R_OBJECT_ID,I_PARTITION,PKUUID,VERSIONLABEL,PAGEINDEX,DOCINDEX,PAGEFLAG,FILENAME,MODIFYTIME,HAVEPOSTIL,EXTEND1,EXTEND2,EXTEND3) 
	VALUES (current_num,'0','pkuuid'||current_num,'1.0','1','1','F','uflop6p0j77.jpg','2021-09-23 10:29:53',' ',' ','test'||task_id,' ');

	--BOC_PKUUID_METADATA_S 插入数据
	INSERT INTO BOC_PKUUID_METADATA_S (R_OBJECT_ID,PKUUID,BIZMETADATA1,SOURCESYSTEM,DATATYPE,UNIQMETADATA,INDEXMETADATA1,INDEXMETADATA2,INDEXMETADATA3,EXTMETADATA,SECURITY,ISARCHIEVED,EXPIREDATE,MODIFYTIME,VERSIONLABEL,ISCURRENTVERSION,EXTEND1,EXTEND2,EXTEND3,I_IS_REPLICA,I_VSTAMP) 
	VALUES ('0900ba'||current_num,'pkuuid'||current_num,'3ad52c5fa5b54974a1db9db7d008b506','15','1020','uni'||current_num,' ',' ',' ',' ','00',' ',' ','2021-09-23T17:05:38Z','1.0','Y','20210812170545','test'||task_id,' ','0','0');

	
	IF MOD(current_num, commit_num) = 0 THEN
	   commit;
	END IF;  
	
  END LOOP;

  commit;
   
END;

/
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')timestr from dual;