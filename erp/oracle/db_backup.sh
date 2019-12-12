#!/bin/sh

export ORACLE_BASE=/u01/app 
export ORACLE_HOME=/u01/app/oracle 
export ORACLE_SID=xe
export PATH=$ORACLE_HOME/bin:$PATH 


date='date +%Y_%m_%d'   #��ȡϵͳ��ǰ����ʱ��
days=7  #����ɾ��7��֮ǰ�ı����ļ�
bakdir=/home/oracle/backup  #�����ļ�·������Ҫ��ǰ������


orowner=jono  #���ݴ��û����������-----------------------------------------------

bakdat=$orowner"_"$date.dmp #�������ݿ�����
baklog=$orowner"_"$date.log #����ִ��ʱ�����ɵ���־�ļ�����
bakzip=$orowner"_"$date.tar.gz #��󱣴��Oracle���ݿⱸ���ļ�

cd $bakdir #���뱸��Ŀ¼
mkdir -p $orowner #����Ҫ���ݵ�Oracle�û�����Ŀ¼
cd $orowner #����Ŀ¼

echo "Starting bakup exp operation..."  
exp jono/jono@xe grants=y owner=$orowner file=$bakdir/$orowner/$bakdat log=$bakdir/$orowner/$baklog #ִ�б���

echo "Starting compress the file backup..."  
tar -zcvf $bakzip $bakdat  $baklog  #ѹ�������ļ�����־�ļ�

echo "Delete the file bakup yesterday..."  
find $bakdir/$orowner -type f -name "*.log" -exec rm {} \; #ɾ�������ļ�
find $bakdir/$orowner -type f -name "*.dmp" -exec rm {} \; #ɾ����־�ļ�
echo "Delete the zip file bakup before 7 days..."  
find $bakdir/$orowner -type f -name "*.tar.gz" -mtime +$days -exec rm -rf {} \;  #ɾ��7��ǰ�ı��ݣ�ע�⣺{} \�м��пո�
echo "Delete the file bakup successfully. "  

chmod +x /backup/oracledata/ordatabak.sh   #��ӽű�ִ��Ȩ��

# vi /etc/crontab  #�༭ϵͳ����ƻ���������´��뵽���һ��
# 30 2 * * * oracle  /backup/oracledata/ordatabak.sh  #ÿ���賿2��30�֣���oracle�û�ִ��ordatabak.sh�����ļ�
# :wq! #�����˳�

# service crond restart #����crond

# ÿ���賿2��30�֣����Զ��������ݿ⵽/backup/oracledata/OSYUNWEIĿ¼�У�����Ϊ.tar.gzѹ���ļ�������ɾ��7��ǰ�ı��ݣ���ֻ�������7������ݡ�




# export DATA_DIR=/home/bakup/data  
# export LOGS_DIR=/home/bakup/logs  
# export DELTIME='date -d "7 days ago" +%Y%m%d'  
# export BAKUPTIME='date +%Y%m%d%H%M%S'
  
# mkdir -p $DATA_DIR  
# mkdir -p $LOGS_DIR  

# echo "Starting bakup..."  
# echo "Bakup file path $DATA_DIR/$BAKUPTIME.dmp"  
# exp jono/jono@xe file=$DATA_DIR/$BAKUPTIME.dmp log=$LOGS_DIR/$BAKUPTIME.log  
 
# echo "Delete the file bakup before 7 days..."  
# rm -rf $DATA_DIR/$DELTIME*.dmp  
# rm -rf $LOGS_DIR/$DELTIME*.log  
# echo "Delete the file bakup successfully. "  
 
# echo "Bakup completed."  


# dak=$(date '+%Y%m%d')
# del=$(date '')
# exp jono/jono@xe file=/home/oracle/backup/$d.dmp log=/home/oracle/backup/$d.log owner=jono
# zip -m /home/oracle/backup/$d.zip /home/oracle/backup/$d.dmp /home/oracle/backup/$d.log