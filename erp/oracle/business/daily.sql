--以下为与餐厅的日结有关的表
drop table D_T3_DAILY_SETTLE_RECORD cascade constraints;
create table D_T3_DAILY_SETTLE_RECORD
(
   BRANCH_ID            VARCHAR2(32),  --部门编号
   BRANCH_NAME          VARCHAR2(32),  --部门名称
   BUSINESS_DATE        DATE,          --营业日期
   OPERATOR_ID          VARCHAR2(32),  --日结人员编码
   OPERATOR_NAME        VARCHAR2(32),  --日结人员名字
   OPERATING_TIME       DATE,          --实际操作时间
   SETTLE_STATUS        VARCHAR2(64),  --日结状态（系统记录的信息）
   SETTLE_NOTE          VARCHAR2(200),  --日结备注信息（人工补充的信息）
   IS_CURRENT           INT,           --是否生效
   CONSTRAINT DAILY_SETTLE_RECORD_PK PRIMARY KEY (BRANCH_ID,BUSINESS_DATE,IS_CURRENT)
);

--存储每个门店每天的库存量历史数据
drop table D_T3_DAILY_STORAGE cascade constraints;
create table D_T3_DAILY_STORAGE
(
   BRANCH_ID            VARCHAR2(32),  --部门编号
   BUSINESS_DATE        DATE,          --营业日期
   ITEM_ID              VARCHAR2(32),  --原料编码
   ITEM_COUNT_THEORY    NUMBER(12,4),  --(根据出品和成本卡)理论库存量
   ITEM_COUNT_ACTUAL    NUMBER(12,4)   --(盘点得到的)实际库存量
);

--以下三个数据表可以联合使用来查询餐厅的万元用量和万元出品量
--存储每个门店每天的原料用量
drop table D_T3_DAILY_RAW_AMT cascade constraints;
create table D_T3_DAILY_RAW_AMT
(
   BRANCH_ID            VARCHAR2(32),  --部门编号
   BUSINESS_DATE        DATE,          --营业日期
   ITEM_PRICE           NUMBER(8,2),   --单价
   PRICE_TYPE           VARCHAR2(32),  --价格类型
   ITEM_ID              VARCHAR2(32),  --原料编码
   AMT_THEORY           NUMBER(10,4),  --原料的理论用量
   AMT_ACTUAL           NUMBER(10,4)   --原料的实际用量
);

--存储每个门店每天的出品量
drop table D_T3_DAILY_FOOD_AMT cascade constraints;
create table D_T3_DAILY_FOOD_AMT
(
   BRANCH_ID            VARCHAR2(32),  --部门编号
   BUSINESS_DATE        DATE,          --营业日期
   ITEM_ID              VARCHAR2(32),  --出品编码
   FOOD_AMT             NUMBER(10,4)   --出品量
);

--存储每个门店每天的营业额
drop table D_T3_DAILY_BUSINESS_AMT cascade constraints;
create table D_T3_DAILY_BUSINESS_AMT
(
   BRANCH_ID            VARCHAR2(32),  --部门编号
   BUSINESS_DATE        DATE,          --营业日期
   BUSINESS_AMT         NUMBER(12,4)   --营业额
);

-----------------------------------------------------------------
--以下为与月结有关的表
-----------------------------------------------------------------
--存储财务对账时每月的月结记录
drop table D_T3_MONTHLY_SETTLE_RECORD cascade constraints;
create table D_T3_MONTHLY_SETTLE_RECORD
(
   BRANCH_ID            VARCHAR2(32),  --部门编号（账务对账月结时与部门无关）
   BRANCH_NAME          VARCHAR2(32),  --部门名称
   MONTH_DATE           DATE,          --月结所在的月份（存储当月的关账日期）
   OPERATOR_ID          VARCHAR2(32),  --月结人员编码
   OPERATOR_NAME        VARCHAR2(32),  --月结人员名字
   OPERATING_TIME       DATE,          --实际操作时间
   SETTLE_STATUS        VARCHAR2(64),  --月结状态（系统记录的信息）
   SETTLE_NOTE          VARCHAR2(200)  --月结备注信息（人工补充的信息）
);