--品牌/企业信息表
drop table D_T_COMPANY cascade constraints;
create table D_T_COMPANY
(
   COMPANY_ID        VARCHAR2(32),  --企业编号
   COMPANY_NAME      VARCHAR2(64),  --企业名称
   DB_NAME			 VARCHAR2(32),  --对应的数据库(用户)名
   CREATOR			 VARCHAR2(32),	--数据库的创建者
   REMARK            VARCHAR2(200)  --备注
);

insert into D_T_COMPANY values('1', '吞云小莳', 'erp1', 'cjt', '');
insert into D_T_COMPANY values('2', '阿扎家年糕店', 'erp2', 'cjt', '');
insert into D_T_COMPANY values('3', '祈苑精品素食', 'erp3', 'cjt', '');
insert into D_T_COMPANY values('4', '云玖创作面', 'erp4', 'cjt', '');
insert into D_T_COMPANY values('5', '和黄记粤菜馆', 'erp5', 'cjt', '');
insert into D_T_COMPANY values('6', 'troy餐厅', 'erp6', 'cjt', '');
insert into D_T_COMPANY values('8', '椰居椰子鸡', 'erp8', 'cjt', '');
insert into D_T_COMPANY values('9', '四海游龙', 'erp9', 'cjt', '');
insert into D_T_COMPANY values('10', '吞云小莳（佛罗伦萨）', 'erp10', 'cjt', '');
insert into D_T_COMPANY values('11', '屋企汤+', 'erp11', 'cjt', '');
insert into D_T_COMPANY values('12', '道包菽炁', 'erp12', 'cjt', '');
insert into D_T_COMPANY values('13', '吉亨面馆', 'erp13', 'cjt', '');

--传递数据日志表
drop table D_T_RESTAPI_LOG cascade constraints;
create table D_T_RESTAPI_LOG
(
	LOG_ID 		VARCHAR2(32),
	API			VARCHAR2(16),
	CODE 		VARCHAR2(3),
	RECEIVE		NUMBER(5),
	SUCCESS		NUMBER(5),
	FAIL		NUMBER(5),
	LOG_TIME	DATE,
	SENDER		VARCHAR2(32)
); 


--传递数据出现错误的JSON数据表
drop table D_T_RESTAPI_ERR_JSON cascade constraints;
create table D_T_RESTAPI_ERR_JSON
(
	LOG_ID 		VARCHAR2(32),
	ERR_ID		VARCHAR2(5),	--实际上就是错误的JSON对象在JSON数组中的索引数
	JSON_STR	VARCHAR2(800)
);

--传递数据出现错误的具体出错信息表
drop table D_T_RESTAPI_ERR_MSG cascade constraints;
create table D_T_RESTAPI_ERR_MSG
(
	LOG_ID 		VARCHAR2(32),
	ERR_ID		VARCHAR2(5),
	MSG_ID		VARCHAR2(32),	--实际上就是JSON对象中产生错误的的键
	CODE 		VARCHAR2(3),
	MESSAGE 	VARCHAR2(200)
);
