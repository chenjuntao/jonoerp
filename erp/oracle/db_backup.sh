#!/bin/sh

export ORACLE_BASE=/u01/app 
export ORACLE_HOME=/u01/app/oracle 
export ORACLE_SID=xe
export PATH=$ORACLE_HOME/bin:$PATH 


date='date +%Y_%m_%d'   #获取系统当前日期时间
days=7  #设置删除7天之前的备份文件
bakdir=/home/oracle/backup  #备份文件路径，需要提前创建好


orowner=jono  #备份此用户下面的数据-----------------------------------------------

bakdat=$orowner"_"$date.dmp #备份数据库名称
baklog=$orowner"_"$date.log #备份执行时候生成的日志文件名称
bakzip=$orowner"_"$date.tar.gz #最后保存的Oracle数据库备份文件

cd $bakdir #进入备份目录
mkdir -p $orowner #按需要备份的Oracle用户创建目录
cd $orowner #进入目录

echo "Starting bakup exp operation..."  
exp jono/jono@xe grants=y owner=$orowner file=$bakdir/$orowner/$bakdat log=$bakdir/$orowner/$baklog #执行备份

echo "Starting compress the file backup..."  
tar -zcvf $bakzip $bakdat  $baklog  #压缩备份文件和日志文件

echo "Delete the file bakup yesterday..."  
find $bakdir/$orowner -type f -name "*.log" -exec rm {} \; #删除备份文件
find $bakdir/$orowner -type f -name "*.dmp" -exec rm {} \; #删除日志文件
echo "Delete the zip file bakup before 7 days..."  
find $bakdir/$orowner -type f -name "*.tar.gz" -mtime +$days -exec rm -rf {} \;  #删除7天前的备份（注意：{} \中间有空格）
echo "Delete the file bakup successfully. "  

chmod +x /backup/oracledata/ordatabak.sh   #添加脚本执行权限

# vi /etc/crontab  #编辑系统任务计划，添加以下代码到最后一行
# 30 2 * * * oracle  /backup/oracledata/ordatabak.sh  #每天凌晨2点30分，以oracle用户执行ordatabak.sh备份文件
# :wq! #保存退出

# service crond restart #重启crond

# 每天凌晨2点30分，会自动备份数据库到/backup/oracledata/OSYUNWEI目录中，保存为.tar.gz压缩文件，并且删除7天前的备份，即只保留最近7天的数据。




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