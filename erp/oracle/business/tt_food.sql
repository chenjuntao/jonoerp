
--出品大类表
DROP TABLE C_T_FOOD_BIGCLS;
CREATE TABLE C_T_FOOD_BIGCLS
    (
        CBIGCLS_C VARCHAR(4) NOT NULL,
        CBIGCLS_N VARCHAR(30) NOT NULL,
        SAREA NUMBER(20) NOT NULL,
        CBRANCH_C VARCHAR(4) NOT NULL,
        CONSTRAINT C_T_FOOD_BIGCLS PRIMARY KEY (CBIGCLS_C)
    );
--出品小类表
DROP TABLE C_T_FOOD_LITCLS;
CREATE TABLE C_T_FOOD_LITCLS
    (
        CLITCLS_C VARCHAR(4) NOT NULL,
        CLITCLS_N VARCHAR(30) NOT NULL,
        BLIMIT NUMBER(1) NOT NULL,
        BSERVICEFEE NUMBER(1) NOT NULL,
        BDISCOUNT NUMBER(1) NOT NULL,
        CBIGCLS_C VARCHAR(4) NOT NULL,
        CBIGCLS_N VARCHAR(30) NOT NULL,
        BPRINTLABEL NUMBER(1),
        ICOLORINDEX NUMBER(10),
        BILL_DISPLAY CHAR(1),
        CBRANCH_C VARCHAR(4),
        TABLEBILL_DISPLAY VARCHAR(5),
        CONSTRAINT C_T_FOOD_LITCLS_PK PRIMARY KEY (CLITCLS_C)
    );

--餐厅出品信息表
drop table C_T_FOOD cascade constraints;
create table C_T_FOOD
(
   ITEM_ID              VARCHAR2(32),  --编码
   ITEM_BAR_CODE        VARCHAR2(32),  --条码
   ITEM_NAME            VARCHAR2(70),  --名称
   ITEM_NAME_ENG        VARCHAR2(64),  --出品英文名称
   ITEM_DIMENSION       VARCHAR2(16),  --出品例牌
   QUERY_CODE           VARCHAR2(64),  --助记码(检索码)
   CATEGORY_ID          VARCHAR2(32),  --类别编码
   ITEM_NOTE            VARCHAR2(200), --备注信息
   ENABLED              INTEGER,       --是否启用(0未启用，1已启用)
   BINHANDPRO           INTEGER,       --手持下载
   BDISFOOD             INTEGER,       --出品折扣
   BDISCOUNT            INTEGER,       --照单折扣
   CONSTRAINT C_T_FOOD_PK PRIMARY KEY (ITEM_ID)
);

--餐厅出品信息表
drop table C_T_FOOD_PRICE cascade constraints;
create table C_T_FOOD_PRICE
(
   BRANCH_ID            VARCHAR2(32),  --餐厅编码
   ITEM_ID              VARCHAR2(32),  --出品编码
   ITEM_PRICE           NUMBER(10,4),  --价格
   CONSTRAINT C_T_FOOD_PRICE_PK PRIMARY KEY (BRANCH_ID,ITEM_ID)
);

-------------------------------------------------------------------------------------
--插入数据
truncate table C_T_FOOD;
insert into C_T_FOOD
(
select 
    ITEM_ID,
    ITEM_BAR_CODE,
    ITEM_NAME,
    ITEM_NAME_ENG,
    ITEM_DIMENSION,
    QUERY_CODE,
    CATEGORY_ID,
    ITEM_NOTE, 
    ENABLED,
    0,0,0
from d_t2_item_meta 
where item_type='PRODUCT'
);

truncate table C_T_FOOD_BIGCLS;
insert into C_T_FOOD_BIGCLS
(
select 
    CATEGORY_ID,
    CATEGORY_NAME,
    0,' '
from D_T2_ITEM_CATEGORY
where ITEM_TYPE='PRODUCT'
and CATEGORY_LEVEL=1
);

truncate table C_T_FOOD_LITCLS;
insert into C_T_FOOD_LITCLS
(
select 
    CATEGORY_ID,
    CATEGORY_NAME,
    0,0,0,
    PARENT_ID,
    ' ',0,0,
    ' ',' ',' '
from D_T2_ITEM_CATEGORY
where ITEM_TYPE='PRODUCT'
and CATEGORY_LEVEL=2
);