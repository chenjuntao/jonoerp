--系统用户表
DROP TABLE D_T2_USER;

CREATE TABLE D_T2_USER (
	 USER_ID VARCHAR2(32), 		--编号
	 USER_NAME VARCHAR2(50),	--姓名
	 PASSWORD VARCHAR2(100),	--密码
	 WEIGHT INTEGER, 			--排序
	 EMAIL VARCHAR2(50), 		--电子邮件
	 TELEPHONE VARCHAR2(50),	--手机号
	 GENDER VARCHAR2(10), 		--性别
     CONSTRAINT D_T2_USER_PK PRIMARY KEY (USER_ID)
 );

--系统用户与部门关联表
DROP TABLE D_T0_USERTOBRANCH;

CREATE TABLE D_T0_USERTOBRANCH (
	 ID VARCHAR2(32), 			--主键
	 IS_PRIMARY VARCHAR2(1),	--是否主部门
	 WEIGHT INTEGER, 			--排序
	 USER_ID VARCHAR2(32), 		--用户ID
	 BRANCH_ID VARCHAR2(32) 	--部门ID
 );

--系统角色表
DROP TABLE D_T0_ROLE;

CREATE TABLE D_T0_ROLE (
	 ROLE_ID VARCHAR2(32), 		--编号
	 ROLE_NAME VARCHAR2(50), 	--名称
	 BRANCH_TYPE VARCHAR2(64), 	--部门类型
	 DESCRIPTION VARCHAR2(200) 	--描述
 ); 


--系统用户与角色关联表
DROP TABLE D_T0_USERTOROLE;

CREATE TABLE D_T0_USERTOROLE (
	 ID VARCHAR2(32), 			--主键
	 WEIGHT INTEGER, 			--排序
	 USER_ID VARCHAR2(32), 		--用户ID
	 BRANCH_ID VARCHAR2(32), 	--部门ID
	 ROLE_ID VARCHAR2(32) 		--角色ID(一个用户在某一个部门下会有一个角色)
 );

 /*
CREATE TABLE
    D_T2_GROUP
    (
        ID VARCHAR(32),         --主键
        NAME VARCHAR(50),       --名称
        PATH VARCHAR(1000),     --部门树路径
        WEIGHT INTEGER,         --排序
        PARENT_ID VARCHAR(32)   --父级部门ID
    );
*/ --系统菜单表
DROP TABLE D_T0_MENU;
CREATE TABLE D_T0_MENU ( 
 ID VARCHAR(32), 			--主键
 NAME VARCHAR(50), 			--名称
 IMAGE_NAME VARCHAR(30), 	--顶级菜单图片名称s
 PATH VARCHAR(255), 		--菜单树路径
 PRIORITY INTEGER, 			--排序
 URL VARCHAR(200), 			--菜单地址
 DESCRIPTION VARCHAR(200), 	--描述
 PARENT_ID VARCHAR(32),		--父级部门ID
 CONSTRAINT MENU_PK PRIMARY KEY (ID)
 );

 --系统权限表
DROP TABLE D_T0_PERMISSION;
CREATE TABLE D_T0_PERMISSION (
 ID VARCHAR2(32), 				--主键
 RESOURCE_TYPE VARCHAR2(50), 	--资源类型，菜单或其它
 PRINCIPAL_TYPE VARCHAR2(50), 	--权限主体类型，用户、部门、角色等
 OPERATE VARCHAR2(50), 			--操作类型，使用，授权等
 RESOURCE_ID VARCHAR2(32), 		--资源ID
 PRINCIPAL_ID VARCHAR2(32) 		--权限主体ID
 );

 --操作日志表
DROP TABLE D_T0_OPERATELOG;
CREATE TABLE D_T0_OPERATELOG (
 ID VARCHAR2(36), 				--主键，uuid
 MENU_ID VARCHAR2(32), 			--菜单id，可为空，如果能获取到最好
 MENU_NAME VARCHAR2(100), 		--菜单名称，查询数据库获取最准确的名称或者自定义（增加灵活性）
 URL VARCHAR2(200), 			--菜单地址，从请求中自动获取
 BUSINESS_ID VARCHAR2(32), 		--要记录日志的业务数据id，比如要货单的编号，可为空
 OPERATE_TYPE VARCHAR2(50), 	--操作类型：增加，删除，修改，查询等
 TITLE VARCHAR2(100), 			--简短说明
 OPERATE_DESC VARCHAR2(4000),	--详细说明
 OPERATOR_ID VARCHAR2(32), 		--操作人员编号
 OPERATOR_NAME VARCHAR2(100), 	--操作人员名称
 BRANCH_ID VARCHAR2(32),	 	--登录用户所属门店ID
 OPERATE_TIME DATE 				--操作时间，含时分秒
 );

 --quartz日志表
DROP TABLE D_T0_QUARTZLOG;
CREATE TABLE D_T0_QUARTZLOG (
 JOB_DESC VARCHAR2(200), 	--定时任务描述
 BEGIN_TIME DATE, 			--开始时间
 END_TIME DATE, 			--结束时间
 ELAPSED_TIME NUMBER(10,4), --耗时(以秒为单位，包含四位小数(即毫秒))
 JOB_RESULT VARCHAR2(32), 	--定时任务执行结果
 IP_ADDR VARCHAR2(32) 		--调用任务的WEB服务地址(eg:本地测试或公司服务器)
 );