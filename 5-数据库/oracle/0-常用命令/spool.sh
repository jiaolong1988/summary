#! /bin/sh

sqlplus -s imagesys/imagesys<<EOF
 set head off
 set linesize 20000
 set echo off
 set feedback off
 set pagesize 0
 set termout off
 set trimout on
 set trimspool on
  spool sqool-data.txt
   select t.bizmetadata1 from ks06_xdb_metadata2 t;
  spool off
  exit;
EOF
