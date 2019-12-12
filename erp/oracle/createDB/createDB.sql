
--该脚本创建一个新的企业，
--具体流程如下：com表中插入数据—>创建表空间—>创建用户—>创建—>创建各个表—>插入必要的基础数据

--oracle12中要创建本地用户要先执行以下语句：
--alter session set "_ORACLE_SCRIPT"=true;


--窗口里显示服务器输出信息
set serveroutput on

declare

	com_id	varchar2(32)	:='zoo';
	name	varchar2(64)	:='客户zoo';
	com_db	varchar2(32)	:='db_zoo';
	remark	varchar2(200)	:='上海客户测试zoo';

	--表空间数据文件存放位置
	ts_dir 	varchar2(60)	:='/u01/app/oracle/oradata/XE';


	--动态拼接SQL语句
	v_sql varchar2(200);

begin

	dbms_output.put_line('开始在Company表中添加多企业信息###############');
	v_sql:='insert into com.D_T_COMPANY values(:1,:2,:3,:4,:5)';
	dbms_output.put_line(v_sql);
	execute immediate v_sql using com_id, name, com_db, 'cjt', remark; 

	--创建数据表空间，默认文件大小伟10M，每次自动增长5M，最大1G
	dbms_output.put_line('开始创建表空间###############');
	v_sql:='create tablespace '||com_db||' datafile '''||ts_dir||'/'||com_db||'.dbf'' size 10m autoextend on next 5m maxsize 1024m';
	dbms_output.put_line(v_sql);
	execute immediate v_sql;

	--创建用户，默认密码为jono
	dbms_output.put_line('开始创建用户###############');
	v_sql:='create user '||com_db||' identified by jono default tablespace '||com_db||'';
	dbms_output.put_line(v_sql);
	execute immediate v_sql;
	v_sql:='grant connect,resource to '||com_db||'';
	dbms_output.put_line(v_sql);
	execute immediate v_sql;

	dbms_output.put_line('开始以创建的用户登录###############');
	execute immediate 'conn '||com_db||'/jono';
	execute immediate 'show user';

	dbms_output.put_line('开始创建Store各表###############');
	execute immediate '@store';

	dbms_output.put_line('开始创建Form各表###############');
	execute immediate '@form';

	dbms_output.put_line('开始创建User各表###############');
	execute immediate '@user';
end;
/