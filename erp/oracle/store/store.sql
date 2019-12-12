--系统选项设置表
drop table D_T0_OPTION cascade constraints;
create table D_T0_OPTION
(
   OPTION_KEY            VARCHAR2(16),   --选项键
   OPTION_VALUE          VARCHAR2(128)   --选项值
); 


insert into D_T0_OPTION values('SAFE_STORAGE', 'DYNAMIC');
insert into D_T0_OPTION values('AMT_TTCNY', 'THEORY');
insert into D_T0_OPTION values('CLOSE_DATE', '25');
insert into D_T0_OPTION values('BOX_VOLUME', '200');

--工作流程前后步骤关系表
drop table D_T0_STATUS_FLOW cascade constraints;
create table D_T0_STATUS_FLOW
(
   WORK_FLOW            VARCHAR2(32),   --工作流程名称
   STEP                 VARCHAR2(32),   --当前工作步骤
   REF_STEP             VARCHAR2(32),   --关联的工作步骤(START:起始步骤;END:结束步骤)
   IS_PREV_NEXT         VARCHAR2(16)    --关联步骤是前置步骤还是后续步骤(PREV:前置步骤;NEXT:后续步骤)
);

--部门信息表
--(餐厅:RESTAURANT)
--(加盟店:FRANCHISEE)
--(中央工厂:CENTRALFACTORY)
--(物流中心:LOGISTICSCENTER)
--(供应商:SUPPLIER)
--(外部订货方:OUTERORDER)
--(总部:HEADQUATER)
--（进货价：PURCHASE）
drop table D_T2_BRANCH cascade constraints;
create table D_T2_BRANCH
(
   BRANCH_ID            VARCHAR2(32),  --部门编号
   BRANCH_NAME          VARCHAR2(150),  --部门名称
   QUERY_CODE           VARCHAR2(32),  --助记码(检索码)
   BRANCH_ADDRESS       VARCHAR2(100), --部门地址
   CONTACT_MAN          VARCHAR2(64),  --联系人
   EMAIL                VARCHAR2(64),  --电子邮箱
   TELEPHONE            VARCHAR2(32),  --联系电话
   FAX                  VARCHAR2(16),  --传真
   BRANCH_TYPE          VARCHAR2(16),  --部门类型(见上面详细分类)
   PRICE_TYPE          	VARCHAR2(32),  --价格类型，用于获取原料价格，餐厅对应标准价、加盟店对就加盟价，外部订货方对应批发价或零售价
   REMARK               VARCHAR2(200), --备注
   ENABLED              INTEGER, 	   --是否启用(0未启用，1已启用)
   PARK                 VARCHAR2(100), --停车场信息
   LON                  NUMBER(11,8),  --经度
   LAT                  NUMBER(11,8),  --纬度
   TAG                  VARCHAR2(50),  --标签
   BUSINESS_DATE        DATE default sysdate, --营业时间,默认为当前日期
   CONSTRAINT BRANCH_PK PRIMARY KEY (BRANCH_ID)
);

--部门类型表(暂定七大类)
--(餐厅:RESTAURANT)
--(加盟店:FRANCHISEE)
--(中央工厂:CENTRALFACTORY)
--(物流中心:LOGISTICSCENTER)
--(供应商:SUPPLIER)
--(外部订货方:OUTERORDER)
--(总部:HEADQUATER)
drop table D_T2_BRANCH_TYPE cascade constraints;
create table D_T2_BRANCH_TYPE
(
   TYPE_ID           VARCHAR2(32),  --类型编码
   TYPE_NAME         VARCHAR2(64),  --类型名称
   DESCRIPTION       VARCHAR2(200), --描述
   CONSTRAINT BRANCH_TYPE_PK PRIMARY KEY (TYPE_ID)
);

--原材料/半成品/出品类别表
drop table D_T2_ITEM_CATEGORY cascade constraints;
create table D_T2_ITEM_CATEGORY
(
   CATEGORY_ID          VARCHAR2(32),  --类别ID
   CATEGORY_NAME        VARCHAR2(100),  --类别名称
   PARENT_ID            VARCHAR2(32),  --父类别ID
   CATEGORY_LEVEL       VARCHAR2(8),   --类别级别
   ITEM_TYPE            VARCHAR2(8),   --类型(原材料:RAW/中央工厂半成品:SEMIS/成品:PRODUCT/餐厅自制半成品:SELFSEMIS)
   PATH                 VARCHAR(255),  --类别树路径
   CONSTRAINT ITEM_CATEGORY_PK PRIMARY KEY (CATEGORY_ID)
);

--原材料/半成品/成品基础信息表
drop table D_T2_ITEM_META cascade constraints;
create table D_T2_ITEM_META
(
   ITEM_ID              VARCHAR2(32),  --编码
   ITEM_BAR_CODE        VARCHAR2(32),  --条码
   ITEM_NAME            VARCHAR2(150),  --名称
   ITEM_NAME_ENG        VARCHAR2(64),  --原料简称/出品英文名称
   ITEM_DIMENSION       VARCHAR2(32),  --原料库存单位/出品例牌
   QUERY_CODE           VARCHAR2(64),  --助记码(检索码)
   CATEGORY_ID          VARCHAR2(32),  --类别编码
   ITEM_SPECIFICATION   VARCHAR2(128),  --原材料规格/出品标志位（手持下载/出品折扣/照单折扣，比如：101，第一个1表示手持下载，第二个0表示出品折扣，第三个1表示照单折扣）
   ITEM_TYPE            VARCHAR2(8),   --类型(原材料:RAW/中央工厂半成品:SEMIS/成品:PRODUCT/餐厅自制半成品:SELFSEMIS)
   ITEM_PIC             VARCHAR2(32),  --对应的图片id(对应于图片表中的图片id)
   ITEM_NOTE            VARCHAR2(200), --备注信息
   BOX_TYPE				   VARCHAR2(32),  --原料箱子类型(冷藏/非冷藏)
   ENABLED				   INTEGER NOT NULL,	      --是否启用(0未启用，1已启用)
   EFFECT_DATE			   DATE,	   	   --生效日期(出品新增和修改定时生效)
   CONSTRAINT ITEM_META_PK PRIMARY KEY (ITEM_ID)
);

--存储出品套餐各个子项以及子项可替代品
drop table D_T2_ITEM_FOOD_SUIT cascade constraints;
create table D_T2_ITEM_FOOD_SUIT
(
   FOOD_SUIT_ID              VARCHAR2(32),  --套餐出品编码
   FOOD_ID                   VARCHAR2(32),  --套餐子项编码
   FOOD_OPTION_ID            VARCHAR2(32),  --套餐子项可换项编码（如果为必选项，则留空）
   ITEM_COUNT                NUMBER(10,5),  --子项或换品数量（如果为必选项，则留空）
   ITEM_PRICE                NUMBER(8,2),   --单价(套餐总价=∑子项单价×子项数量)
   IS_CURRENT                INT            --该套餐子项是否是当前所选(0表示非当前步骤，1表示当前步骤)
);


--箱子类型(冷藏、非冷藏等)
drop table D_T2_BOX_TYPE cascade constraints;
create table D_T2_BOX_TYPE
(
   TYPE_ID           VARCHAR2(32),  --编码
   TYPE_NAME         VARCHAR2(64),     --名称
   VOLUME            NUMBER(10,5),  --体积(升/L)，不能超限
   WEIGHT            NUMBER(10,5),     --重量(克/G)，不能超限)
   ENABLED           INTEGER   ,       --是否启用(0未启用，1已启用)
   CONSTRAINT BOX_TYPE_PK PRIMARY KEY (TYPE_ID)
);

--原材料/半成品/成品价格表
--(进货/采购价:PURCHASE)
--(标准价:BENCHMARK)
--(加盟价:JOIN)
--(原料零售价:RETAIL)
--(批发价:WHOLESALE)
--(供应商价格:SUPPLIER)
--(出品售卖价:SALE)
drop table D_T2_ITEM_PRICE cascade constraints;
create table D_T2_ITEM_PRICE
(
   ITEM_ID              VARCHAR2(32),  --编码
   ITEM_PRICE           NUMBER(10,4),  --单价
   PRICE_TYPE           VARCHAR2(32),  --所属价格组(见上面详细分类)
   IS_CURRENT           INT,           --是否是当前生效价格
   EFFITIVE_FORM_ID     VARCHAR2(32),  --生效表单编号
   EFFECT_TIME          DATE,          --生效时间
   SUPPLIER_ID          VARCHAR2(32)   --供应商编码
);

--价格组
drop table D_T2_PRICE_GROUP cascade constraints;
create table D_T2_PRICE_GROUP
(
   PRICE_GROUP_ID             VARCHAR2(32),  --价格组编码
   PRICE_GROUP_NAME           VARCHAR2(32),  --价格组名称
   PRICE_GROUP_TYPE           VARCHAR2(32),  --价格组类型(物流中心销售价格组LC/门店多品牌价格组BRAND)
   PRICE_GROUP_NOTE           VARCHAR2(64),  --价格组备注信息
   OWNER                      VARCHAR2(32)   --价格组所属的父级编号(物流中心编号/门店多品牌分组编号)
);

--价格组与门店的对应关系
drop table D_T2_PRICE_GROUP_BRANCH cascade constraints;
create table D_T2_PRICE_GROUP_BRANCH
(
   PRICE_GROUP_ID       VARCHAR2(32),  --价格组编码
   BRANCH_ID            VARCHAR2(32)   --门店编码/外部订货方编码
);

-- --门店多品牌分组
-- drop table D_T2_BRAND_GROUP cascade constraints;
-- create table D_T2_BRAND_GROUP
-- (
--    BRAND_ID             VARCHAR2(32),  --品牌分组编码
--    BRAND_NAME           VARCHAR2(32),  --品牌分组名称
--    BRAND_NOTE           VARCHAR2(64)   --品牌分组备注信息
-- );

-- --多品牌分组与门店的对应关系
-- drop table D_T2_BRAND_BRANCH cascade constraints;
-- create table D_T2_BRAND_BRANCH
-- (
--    BRAND_ID             VARCHAR2(32),  --品牌分组编码
--    BRANCH_ID            VARCHAR2(32)   --门店编码
-- );

-- --多品牌出品信息表
-- drop table D_T2_BRAND_PRODUCT cascade constraints;
-- create table D_T2_BRAND_PRODUCT
-- (
--    BRAND_ID             VARCHAR2(32),  --品牌编码
--    ITEM_ID              VARCHAR2(32),  --出品编码
--    ENABLED           INTEGER,    --是否启用(0未启用，1已启用)
--    EFFECT_DATE       DATE        --生效日期(出品新增和修改定时生效)
-- );


/* 考虑多包装的问题而建立的表，根据具体需要再添加
--原材料的库存和用量单位换算表（适用于多包装）
drop table D_T2_ITEM_UNIT_CONVERSION cascade constraints;
create table D_T2_ITEM_UNIT_CONVERSION
(
   ITEM_ID              VARCHAR2(32),  --编码
   ITEM_DIMENSION       VARCHAR2(16),  --其他单位名称
   CONVERSION_FACTOR    NUMBER(19,4)
--对于原材料来说，表示其他单位相对于库存单位的换算因子
--例如，库存单位为斤，其他单位为克，换算因子则为500
);
*/

--成本卡
drop table D_T2_THERAPY cascade constraints;
create table D_T2_THERAPY
(
   THERAPY_ID           VARCHAR2(32),  --配方编码(成品或半成品编码)
   THERAPY_NAME         VARCHAR2(64),  --成品或半成品名称
   THERAPY_DIMENSION    VARCHAR2(16),  --成品例牌或半成品单位
   ITEM_ID              VARCHAR2(32),  --原材料编码
   ITEM_NAME            VARCHAR2(150),  --原材料名称
   ITEM_DIMENSION       VARCHAR2(32),  --原材料配方单位
   UNIT_CONVERT_FACTOR  NUMBER(19,5),  --配方单位相对于库存单位的转换因子(配方单位=库存单位*转换因子)
   ITEM_COUNT           NUMBER(10,5),  --净料耗量(单位为配方单位)
   ITEM_USEAGE_RATE     NUMBER(10,5),  --净料耗率
   ITEM_GROSS_COUNT     NUMBER(10,5),  --原料耗量(单位为库存单位)(原料耗量=(净料耗量/(净料耗率/100)/转换因子)
   ITEM_AMT             NUMBER(10,5),  --原料成本(金额=原料耗量/转换因子*进货价)
   ITEM_TYPE            VARCHAR2(16),  --原料类型(主料/辅料/配料)
   OWNER                VARCHAR2(32),  --成本卡所属分组(中央工厂编号/门店多品牌分组编号)
   CONSTRAINT D_T2_THERAPY_PK PRIMARY KEY (THERAPY_ID, ITEM_ID, OWNER)
);

--仓库-部门关联表
drop table D_T2_BRANCH_STORAGE cascade constraints;
create table D_T2_BRANCH_STORAGE
(
   BRANCH_ID            VARCHAR2(32),  --部门标识
   STORAGE_ID           VARCHAR2(32),  --仓库标识
   STORAGE_NAME         VARCHAR2(64),  --仓库名称
   PRIORITY             INT            --仓库优先级（0最高级，1，2，3...依此降低）
);

--仓库存储信息表
drop table D_T2_STORAGE cascade constraints;
create table D_T2_STORAGE
(
   STORAGE_ID           VARCHAR2(32),  --仓库标识
   ITEM_ID              VARCHAR2(32),  --商品编码
   ITEM_COUNT           NUMBER(12,4),  --商品数量
   SHELF_ID             VARCHAR2(32),  --库位
   LAST_IN_TIME         DATE,          --最近入库时间
   EXPIRED_TIME         DATE           --原料有效期
);

--仓库-车间关联表
drop table D_T2_STORAGE_WORKSHOP cascade constraints;
create table D_T2_STORAGE_WORKSHOP
(
   STORAGE_ID           VARCHAR2(32),  --仓库标识
   WORK_ORDER_ID        VARCHAR2(32),  --车间编号
   WORKSHOP             VARCHAR2(64),  --车间名称
   PRIORITY             INT            --车间优先级（0最高级，1，2，3...依此降低）
);

--车间存储信息表
drop table D_T2_WORKSHOP cascade constraints;
create table D_T2_WORKSHOP
(
   WORK_ORDER_ID        VARCHAR2(32),  --车间编号
   WORKSHOP             VARCHAR2(64)  --车间名称
);

--库位信息表，主要供物流中心捡货使用
drop table D_T2_SHELF cascade constraints;
create table D_T2_SHELF
(
   SHELF_ID             VARCHAR2(32),  --编号
   SHELF_NAME           VARCHAR2(32),  --名称
   DESCRIPTION          VARCHAR2(120),  --描述
   CONSTRAINT SHELF_PK PRIMARY KEY (SHELF_ID)
);

--库位与物料关系表，主要供物流中心捡货使用
drop table D_T2_SHELF_ITEM cascade constraints;
create table D_T2_SHELF_ITEM
(
   SHELF_ID             VARCHAR2(32),  --库位
   ITEM_ID              VARCHAR2(32),  --商品编码
   PRIORITY             INT           --库位优先级（0最高级，1，2，3...依此降低）
);

--一个物料只能有一个主库位，但是可以有多个非主库位
CREATE UNIQUE INDEX
    D_T2_SHELF_ITEM_UNIQUE
ON
    D_T2_SHELF_ITEM
    (
        CASE
            WHEN PRIORITY ='0'
            THEN ITEM_ID
            ELSE ITEM_ID||SHELF_ID
        END
    );


--供应商与门店以及商品的对应关系
drop table D_T2_SUPPLIER_BRANCH_ITEM cascade constraints;
create table D_T2_SUPPLIER_BRANCH_ITEM
(
   SUPPLIER_ID          VARCHAR2(32),  --供应商编号
   BRANCH_ID            VARCHAR2(32),  --门店编号
   ITEM_ID              VARCHAR2(32),  --商品编号
   PRIORITY             INT            --供应商优先级（0最高级主供应商，1，2，3...依此降低）
);

--供应商供应的原材料的供货周期表
drop table D_T2_SUPPLY_CYCLE cascade constraints;
create table D_T2_SUPPLY_CYCLE
(
   BRANCH_ID            VARCHAR2(32),  --订货方ID(物流中心或中央工厂)
   ITEM_ID              VARCHAR2(32),  --商品编号
   SUPPLY_CYCLE         NUMBER(6,2),   --供货周期(天)
   INVENTORYS_CYCLE    	NUMBER(6,2),    --库存周期（天）
  CONSTRAINT SUPPLY_CYCLE_PK PRIMARY KEY (BRANCH_ID,ITEM_ID)
);

--半成品生产周期表
drop table D_T2_PRODUCTION_CYCLE cascade constraints;
create table D_T2_PRODUCTION_CYCLE
(
   BRANCH_ID            VARCHAR2(32),  --中央工厂ID
   ITEM_ID              VARCHAR2(32),  --编码
   PRODUCTION_CYCLE     NUMBER(6,2),   --生产周期
   CONSTRAINT MY_CONSTRAINT UNIQUE (BRANCH_ID, ITEM_ID)
);


--配送（分组）区域表
drop table D_T2_DELIVERY_REGION cascade constraints;
create table D_T2_DELIVERY_REGION
(
   REGION_ID            VARCHAR2(32),  --配送区域ID
   REGION_NAME          VARCHAR2(32),  --配送区域名称
   BRANCH_ID            VARCHAR2(32),  --区域所属物流中心ID
   DELIVERY_CYCLE       INT,           --配送周期(天)
   ENABLED				   INTEGER	   	--是否启用(0未启用，1已启用)
);

--配送区域关联的门店表
drop table D_T2_DELIVERY_REGION_BRANCH cascade constraints;
create table D_T2_DELIVERY_REGION_BRANCH
(
   REGION_ID            VARCHAR2(32),  --配送区域ID
   BRANCH_ID            VARCHAR2(32),  --区域所关联的门店ID
   CONSTRAINT D_T2_DELIVERY_REGION_BRANCH_PK PRIMARY KEY (REGION_ID, BRANCH_ID)
);

--配送模式表
drop table D_T2_DELIVERY_TYPE cascade constraints;
create table D_T2_DELIVERY_TYPE
(
   REGION_ID            VARCHAR2(32),  --配送区域ID
   ITEM_ID              VARCHAR2(32),  --物料ID
   DELIVERY_TYPE        VARCHAR2(16) , --配送模式(统配:UNIFIED/直配:DIRECT/越库:CROSS/自采:SELF)
   CONSTRAINT DELIVERY_TYPE_PK PRIMARY KEY (REGION_ID,ITEM_ID)
);

--原料其他基础信息表
drop table D_T2_DELIVERY_UNIT cascade constraints;
create table D_T2_DELIVERY_UNIT
(
   ITEM_ID              VARCHAR2(32),  --物料ID
   DELIVERY_UNIT        VARCHAR2(16),  --配送单位
   DELIVERY_FACTOR  	   NUMBER(19,5),  --转换因子(库存单位=配送单位*转换因子)
   RECIPE_UNIT        	VARCHAR2(16),  --配方单位
   RECIPE_FACTOR  		NUMBER(19,5),  --转换因子(配方单位=库存单位*转换因子)
   UNIT_VOLUME 			NUMBER(10,4),  --(配送单位)单位体积(立方分米，即升)
   UNIT_WEIGHT 			NUMBER(10,4),  --(配送单位)单位重量(克G)
   MIN_ORDER_COUNT     	NUMBER(14,2),  --最小订货量(库存单位)
   OUT_RECEIVE_RATE     NUMBER(4,2),    --超收率(实收差异超过此百分比率,不予接收)
    CONSTRAINT CODE_UNIQUE UNIQUE (ITEM_ID),
    CONSTRAINT UN_ZERO CHECK (DELIVERY_FACTOR!=0  AND RECIPE_FACTOR!=0)
);

--图片表
drop table D_T2_PICS cascade constraints;create table D_T2_PICS
(
   PIC_ID               VARCHAR2(32),  --编号
   PIC_BLOB             BLOB           --图片
);

--半成品工序/制程表
drop table D_T2_MAKING_PROCESS cascade constraints;
create table D_T2_MAKING_PROCESS
(
   ITEM_ID              VARCHAR2(32),  --半成品编号
   STEP                 INT,           --制作顺序步骤
   STEP_OPERATION       VARCHAR2(200), --该步骤具体操作
   STEP_NOTE            VARCHAR2(200)  --该步骤备注item
);


--出品岗位观察表头
drop table D_T2_OBSERVATION_HEADER cascade constraints;
create table D_T2_OBSERVATION_HEADER
(
   ITEM_ID              VARCHAR2(32),  --出品编号
   PART_ID			    VARCHAR2(32),   --部分编码
   PART_NAME            VARCHAR2(300), --部分名称
   PART_REMARK          VARCHAR2(4000),--部分说明
   IS_TEMPLATE          VARCHAR2(1)    --是否为模板
);

--出品岗位观察明细
drop table D_T2_OBSERVATION_DETAIL cascade constraints;
create table D_T2_OBSERVATION_DETAIL
(
   ITEM_ID              VARCHAR2(32),  -- 出品编号
   PART_ID			    VARCHAR2(32),  -- 部分编码
   PROCESS_ID		    VARCHAR2(32),  -- 加工编码
   PROCESSED_NAME		VARCHAR2(64),  -- 加工名称
   STEP                 INT ,          -- 操作步骤
   STEP_PIC_BLOB        BLOB, 		   -- 操作图片
   STEP_REMARK			VARCHAR2(500)  -- 操作说明
);

--央厂与车间对应关系表
drop table D_T2_FACTORY_WORKSHOP cascade constraints;
CREATE TABLE D_T2_FACTORY_WORKSHOP
(
    FACTORY_ID VARCHAR2(32) NOT NULL,-- 央厂编码
    WORK_ORDER_ID VARCHAR2(32) NOT NULL,--生产车间编码
    WORKSHOP VARCHAR2(64),-- 生产车间名称
    PRIORITY NUMBER(1), -- 优先级，零的优先级最高，即默认车间
    CONSTRAINT D_T2_STORAGE_WORKSHOP PRIMARY KEY (FACTORY_ID,WORK_ORDER_ID) -- 联合主键
);

--物料与车间对应关系表
drop table D_T2_ITEM_WORKSHOP cascade constraints;
CREATE TABLE D_T2_ITEM_WORKSHOP
(
    FACTORY_ID VARCHAR2(32) NOT NULL,-- 央厂编码
    WORK_ORDER_ID VARCHAR2(32) NOT NULL,--生产车间编码
    ITEM_ID VARCHAR2(32) NOT NULL, --物料编码
    CONSTRAINT D_T2_ITEM_WORKSHOP PRIMARY KEY (FACTORY_ID ,WORK_ORDER_ID, ITEM_ID)
);

-- 标签头表
drop table D_T2_TAG_HEADER cascade constraints;
create table D_T2_TAG_HEADER
(
   TAG_ID               VARCHAR2(32),  -- 标签ID
   TAG_NAME			    VARCHAR2(32),  -- 标签名称
   TAG_NOTE			    VARCHAR2(128), --标签备注
   TYPE_CODE			VARCHAR2(12),  -- 标签类型(ITEM 基础物料)
   CONSTRAINT D_T2_TAG_HEADER_PK PRIMARY KEY (TAG_ID) -- 联合主键
);

-- 标签明细表
drop table D_T2_TAG_DETAIL cascade constraints;
create table D_T2_TAG_DETAIL
(
   TAG_ID              VARCHAR2(32),  -- 标签ID
   CODE			       VARCHAR2(32),  -- 物料编码/其他编码
   CONSTRAINT D_T2_TAG_DETAIL_PK PRIMARY KEY (TAG_ID,CODE) -- 联合主键
);