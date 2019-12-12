-- --创建数据表空间 
-- create tablespace jonodat
-- datafile '/opt/oracle/oradata/orcle/jonodat.dbf' 
-- size 50m 
-- autoextend on 
-- next 50m maxsize 10480m
-- extent management local;

alter session set "_ORACLE_SCRIPT"=true;

-- --创建用户并指定表空间 
-- create user usrname identified by pwd
-- default tablespace jonodat;

-- --给用户授予权限
-- grant connect, resource to usrname;
-- grant create any sequence to usrname;
-- grant create any table to usrname;
-- grant delete any table to usrname;
-- grant insert any table to usrname;
-- grant select any table to usrname;
-- grant unlimited tablespace to usrname;
-- grant execute any procedure to usrname;
-- grant update any table to usrname;
-- grant create any view to usrname;

-- --删除用户以及用户所有的对象 
-- drop user usrname cascade; 

set serveroutput on

declare usernum varchar2(10);
begin
	for i in 1..5
	loop
		usernum:=to_char(i);
		dbms_output.put_line(usernum);
		--创建数据表空间
		--execute immediate 'create tablespace jonodat_'||usernum||' datafile "/opt/oracle/oradata/orcle/jonodat'||usernum||'_dat.dbf" size 10m autoextend on next 10m maxsize 1048m extent management local';
		--创建用户并指定表空间 
		execute immediate 'create user jono'||usernum||' identified by jono default tablespace jonodat';
		execute immediate 'grant connect,resource to jono'||usernum;

		-- execute immediate 'conn jono'||usernum||'/jono';
		-- execute immediate 'show user';
		-- execute immediate '@store';
		-- execute immediate '@form';
		-- execute immediate '@user';

	end loop;
end;
/