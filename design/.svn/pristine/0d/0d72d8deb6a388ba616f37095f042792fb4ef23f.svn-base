set serveroutput on

alter session set "_ORACLE_SCRIPT"=true;

declare usernum varchar2(10);
begin
	for i in 1..5
	loop
		usernum:=to_char(i);
		dbms_output.put_line(usernum);

		execute immediate 'drop user jono'||usernum||' cascade';
		
	end loop;
end;
/