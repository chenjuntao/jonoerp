--表单元数据
drop table D_T0_FORM_META cascade constraints;
create table D_T0_FORM_META
(
   FORM_NAME            VARCHAR2(32),   --单据模板的名称
   FORM_TYPE            VARCHAR2(32),   --表单类型
   FORM_NOTE            VARCHAR2(200)   --单据下方的注意条目
);

--单据状态表
drop table D_T0_FORM_STATUS cascade constraints;
create table D_T0_FORM_STATUS
(
   FORM_ID              VARCHAR2(32),   --单据编号
   STATUS               VARCHAR2(32),   --单据当前的状态
   STATUS_TIME          DATE,           --单据当前状态改变的时间
   OPERATOR             VARCHAR2(32),   --单据当前状态操作的人员
   STATUS_ORDER         INT,            --状态顺序（从前往后递增）
   IS_CURRENT           INT             --是否最新状态（1和0）
--本表格会保留单据所有的状态
);

--单据锁定状态表
drop table D_T0_FORM_LOCK cascade constraints;
create table D_T0_FORM_LOCK
(
   FORM_ID              VARCHAR2(32),   --单据编号
   LOCK_STATUS          INT,            --锁定状态
   LOCK_TIME            DATE            --锁定时间
);


--单据版本记录表
drop table D_T0_OPERATION_VERSION cascade constraints;
create table D_T0_OPERATION_VERSION
(
    FORM_ID VARCHAR2(32),
    OPERATION_ID VARCHAR2(32),
    OPERATION_NAME VARCHAR2(32),
    OPERATION_TIME DATE,
    VERSION INTEGER,
    OPERATION_CONTENT VARCHAR2(32)
);

--打印次数表
drop table D_T0_PRINT_TIMES cascade constraints;
create table D_T0_PRINT_TIMES
(
   FORM_ID              VARCHAR2(32),   --单据编号
   TIMES			    INT ,   --打印次数
   CONSTRAINT FORM_ID_PK PRIMARY KEY (FORM_ID)
);

--出入库表
--(CGRK:采购入库)
--(CGTH:采购退货)
--(CPBS:出品报损)
--(CPCK:产品出库)
--(CPRK:产品入库)
--(DB:调拨)
--(FGLL:非工单领料)
--(JSRK:拒收入库)
--(LLKU:理论扣库)
--(PD:盘点)
--(PSCK:配送出库)
--(PSFSH:配送反审核)
--(PSRK:配送入库)
--(PSTH:配送退货)
--(SCCL:生产超领)
--(SCLL:生产领料)
--(SCTL:生产退料)
--(YKCK:越库出库)
--(YLBS:原料报损)
drop table D_T0_STORAGE_IN_OUT cascade constraints;
create table D_T0_STORAGE_IN_OUT
(
   BRANCH_ID            VARCHAR2(32),  --部门编号
   STORAGE_ID           VARCHAR2(32),  --仓库编号
   BUSINESS_DATE        DATE,          --营业日期
   OPERATION_TIME       DATE,          --操作时间
   ITEM_ID              VARCHAR2(32),  --原料编码
   ITEM_UNIT_PRICE      NUMBER(12,4),  --原料单价     
   ORGI_COUNT           NUMBER(12,4),  --期初数量
   ITEM_COUNT_IN        NUMBER(12,4),  --入库数量
   ITEM_COUNT_OUT       NUMBER(12,4),  --出库数量
   RESULT_COUNT         NUMBER(12,4),  --结存数量
   FORM_ID              VARCHAR2(32),  --单据编号
   REASON               VARCHAR2(64)  NOT NULL,  --业务类型(配送、退货、报损等)
   MY_TIMESTAMP 		VARCHAR2(32)   --用于排序
);

CREATE UNIQUE INDEX
    D_T0_STORAGE_IN_OUT_UNIQUE
ON
    D_T0_STORAGE_IN_OUT
    (
        CASE
            WHEN REASON ='LLKU'
            THEN BRANCH_ID||BUSINESS_DATE||ITEM_ID
        END
    );

--餐厅要货、外部订货商订货需求模板元信息
drop table D_T1_TEMPLATE_META cascade constraints;
create table D_T1_TEMPLATE_META
(
   TEMPLATE_ID          VARCHAR2(32),   --模板编号
   TEMPLATE_NAME        VARCHAR2(64),   --模板名称
   TEMPLATE_TYPE        VARCHAR2(32),   --模板类别
   BRANCH_ID            VARCHAR2(32),   --制作模板的部门
   CATEGORY_ID          VARCHAR2(100),  --模板主要商品类别(最多列出前五种)
   TEMPLATE_NOTE        VARCHAR2(200),  --模板备注信息
   ARRIVE_PERIOD        INT,            --到货周期
   DELIVERY_REGION      VARCHAR2(32),   --配送区域
   DELIVERY_TYPE        VARCHAR2(16),   --配送模式
    CONSTRAINT D_T1_TEMPLATE_META_PK PRIMARY KEY (TEMPLATE_ID)
);

--餐厅要货、外部订货商订货需求模板详细信息
drop table D_T1_TEMPLATE_ITEM cascade constraints;
create table D_T1_TEMPLATE_ITEM
(
   TEMPLATE_ID          VARCHAR2(32),    --模板编号
   ITEM_ID              VARCHAR2(32),    --商品编码
   ITEM_ORDER           INT   ,          --商品顺序
    CONSTRAINT D_T1_TEMPLATE_ITEM_PK PRIMARY KEY (TEMPLATE_ID, ITEM_ID)
);

--餐厅要货、外部订货商订货需求表头
drop table D_T1_REQUEST_HEADER cascade constraints;
create table D_T1_REQUEST_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   FORM_TYPE            VARCHAR2(32),   --单据类型（餐厅要货/外部订货）
   BUYER_ID             VARCHAR2(32),   --订货部门ID
   BUYER_NAME           VARCHAR2(64),   --订货部门
   STORAGE_ID           VARCHAR2(32),   --订货仓库标识(仅对多仓库有效)
   STORAGE              VARCHAR2(64),   --订货仓库名称(仅对多仓库有效)
   BUYER_ADDRESS        VARCHAR2(100),  --订货地址
   PROVIDER_ID          VARCHAR2(32),   --供货部门ID
   PROVIDER             VARCHAR2(64),   --供货部门
   RECEIVE_TIME         DATE,           --到货时间
   FORM_MAKER           VARCHAR2(64),   --制单人员
   FORM_TIME            DATE,           --制单时间
   FORM_TIME_ACTUAL     DATE,           --制单日期(实际时间，包含时分秒)
   AUDITOR              VARCHAR2(64),   --审核人员
   AUDIT_TIME           DATE,           --审核时间
   PURCHASE_STATUS      VARCHAR2(32),   --是否进行采购汇总
   SHIPPING_STATUS      VARCHAR2(32),   --是否进行配送处理
   FORM_NOTE            VARCHAR2(200),  --备注信息
   REF_DATE_START1      DATE,           --参考时间段1开始日期
   REF_DATE_END1        DATE,           --参考时间段1开始日期
   REF_DATE_START2      DATE,           --参考时间段2开始日期
   REF_DATE_END2        DATE,           --参考时间段2开始日期
   REF_DATE_START3      DATE,           --参考时间段3开始日期
   REF_DATE_END3        DATE,           --参考时间段3开始日期
   DELAY_PREDICT        NUMBER(10,4),   --延滞期金额
   PURCHASE_PREDICT     NUMBER(10,4),   --进货周期金额
   SAFETY_STOCK         NUMBER(10,4),   --安全存量
   SELL_PREDICT         NUMBER(10,4),   --预估销售额
   ALL_PAY_AMT          NUMBER(14,4),   --总金额
   MAX_PAY_ITEM         VARCHAR2(64),   --金额最大的商品
   ARRIVE_PERIOD        INT,            --到货周期
   DELIVERY_TYPE        VARCHAR2(16),    --配送模式
   TEMPLATE_ID          VARCHAR2(32),   --模板编号
   TEMPLATE_NAME        VARCHAR2(64),   --模板名称
   ISADDFORM            VARCHAR2(1),   --是否加单
   CONSTRAINT REQUEST_HEADER_PK PRIMARY KEY (FORM_ID)
);
 
--餐厅要货、外部订货商订货需求表明细
drop table D_T1_REQUEST_DETAIL cascade constraints;
create table D_T1_REQUEST_DETAIL
(
   FORM_ID              VARCHAR2(32),   --单据编号
   ITEM_ID              VARCHAR2(32),   --商品编码
   INTL_BAR_CODE        VARCHAR2(32),   --国际条码
   ITEM_NAME            VARCHAR2(64),   --商品名称
   ITEM_CATEGORY        VARCHAR2(32),   --类别
   ITEM_DIMENSION       VARCHAR2(16),   --单位
   ITEM_SPECIFICATION   VARCHAR2(64),   --规格
   AMT_TT_CNY1          NUMBER(10,4),   --万元用量1
   AMT_TT_CNY2          NUMBER(10,4),   --万元用量2
   AMT_TT_CNY3          NUMBER(10,4),   --万元用量3
   REQUIRE_COUNT1       NUMBER(10,4),   --需求1
   REQUIRE_COUNT2       NUMBER(10,4),   --需求2
   REQUIRE_COUNT3       NUMBER(10,4),   --需求3
   INVENTORY            NUMBER(10,4),   --库存量
   SUGGEST_COUNT1       NUMBER(10,4),   --需求1
   SUGGEST_COUNT2       NUMBER(10,4),   --需求2
   SUGGEST_COUNT3       NUMBER(10,4),   --需求3
   ORDER_COUNT          NUMBER(14,4),   --订货量
   ITEM_UNIT_PRICE      NUMBER(10,4),   --单价
   PAY_AMT              NUMBER(14,4),    --金额
   DELIVERY_TYPE        VARCHAR2(16) ,  --配送模式(统配:UNIFIED/直配:DIRECT/越库:CROSS/自采:SELF)
   CONSTRAINT REQUEST_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--物流中心采购直配订单（大小单据关联）表
drop table D_T1_PURCHASING_MAPPING cascade constraints;
create table D_T1_PURCHASING_MAPPING
(
   BIG_FORM_ID          VARCHAR2(32),   --直配采购单大单编号
   SMALL_FORM_ID        VARCHAR2(32),    --对应的直配采购单小单编号
   CONSTRAINT PURCHASING_MAPPING_PK PRIMARY KEY (BIG_FORM_ID, SMALL_FORM_ID)
);

--物流中心采购单、中央工厂采购单表头
drop table D_T1_PURCHASING_HEADER cascade constraints;
create table D_T1_PURCHASING_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   FORM_REF_ID          VARCHAR2(32),   --原始单据编号(对于物流中心或中央工厂有效，对应前一个单据)
   PROVIDER_ID          VARCHAR2(32),   --供应商ID
   PROVIDER             VARCHAR2(150),   --供应商
   REQUESTER_ID         VARCHAR2(32),   --订货部门ID
   REQUESTER            VARCHAR2(150),   --订货部门
   RECEIVER_ID          VARCHAR2(32),   --收货部门ID(只针对直配小单有效,其他为空)
   RECEIVER             VARCHAR2(150),   --收货部门(只针对直配小单有效,其他为空)
   RECEIVE_ADDRESS      VARCHAR2(100),  --收货地址(对直配大单无效,为NULL)
   STORAGE_ID           VARCHAR2(32),   --收货仓库标识(仅对多仓库有效)
   STORAGE              VARCHAR2(64),   --收货仓库名称(仅对多仓库有效)
   RECEIVE_TIME         DATE,           --到货日期
   FORM_MAKER_ID        VARCHAR2(32),   --制单人员ID
   FORM_MAKER           VARCHAR2(64),   --制单人员
   FORM_TIME            DATE,           --制单日期
   FORM_TIME_ACTUAL     DATE,           --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),   --审核人员ID
   AUDITOR              VARCHAR2(64),   --审核人员
   AUDIT_TIME           DATE,           --审核日期
   AUDIT_TIME_ACTUAL    DATE,           --审核时间
   FORM_NOTE            VARCHAR2(200),  --备注说明
   DELIVERY_TYPE        VARCHAR2(64),   --配送方式
   PLAN_STATUS      	VARCHAR2(32),   --是否进行生产计划汇总
   OUT_STATUS      		VARCHAR2(32),   --是否出库
   ALL_PAY_AMT          NUMBER(10,4),   --总金额
   MAX_PAY_ITEM         VARCHAR2(64),   --金额最大的商品
   CONSTRAINT PURCHASING_HEADER_PK PRIMARY KEY (FORM_ID)
);

--物流中心采购单、中央工厂采购单明细
drop table D_T1_PURCHASING_DETAIL cascade constraints;
create table D_T1_PURCHASING_DETAIL
(
   FORM_ID              VARCHAR2(32),   --单据编号
   ITEM_ID              VARCHAR2(32),   --商品编码
   ITEM_NAME            VARCHAR2(150),   --商品名称
   ITEM_DIMENSION       VARCHAR2(32),   --单位
   ITEM_SPECIFICATION   VARCHAR2(128),   --规格
   ITEM_CATEGORY        VARCHAR2(32),   --类别
   ITEM_COUNT           NUMBER(14,4),   --数量
   ITEM_UNIT_PRICE      NUMBER(10,4),   --标准价
   PAY_AMT              NUMBER(14,4),   --标准金额
   RECEIVE_PRICE        NUMBER(10,4),   --进货价
   RECEIVE_AMT          NUMBER(14,4),   --进货金额
   RECEIVER_ID          VARCHAR2(32),   --收货部门ID(只针对越库有效,其他为空)
   RECEIVER             VARCHAR2(64),   --收货部门(只针对越库有效,其他为空)
   EXPIRED_TIME         DATE,            --原料有效期
   CONSTRAINT PURCHASING_DETAIL_UNIQUE UNIQUE (FORM_ID, ITEM_ID, RECEIVER_ID)
);

--物流中心、中央工厂要货汇总单表头
drop table D_T1_COLLECT_HEADER cascade constraints;
create table D_T1_COLLECT_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   BRANCH_ID            VARCHAR2(32),   --汇总部门ID
   BRANCH               VARCHAR2(64),   --汇总部门
   FORM_MAKER_ID        VARCHAR2(32),   --制单人员ID
   FORM_MAKER           VARCHAR2(64),   --制单人员
   FORM_TIME            DATE,           --制单日期
   AUDITOR_ID           VARCHAR2(32),   --审核人员ID
   AUDITOR              VARCHAR2(64),   --审核人员
   AUDIT_TIME           DATE,           --审核日期
   FORM_NOTE            VARCHAR2(200),  --备注说明
   ALL_PAY_AMT          NUMBER(10,4),   --总金额
   MAX_PAY_ITEM         VARCHAR2(64)    --金额最大的商品
);

--物流中心、中央工厂要货汇总单表明细
drop table D_T1_COLLECT_DETAIL cascade constraints;
create table D_T1_COLLECT_DETAIL
(
   FORM_ID              VARCHAR2(32),   --单据编号
   ITEM_ID              VARCHAR2(32),   --商品编码
   ITEM_NAME            VARCHAR2(64),   --商品名称
   ITEM_DIMENSION       VARCHAR2(16),   --单位
   ITEM_SPECIFICATION   VARCHAR2(64),   --规格
   ITEM_CATEGORY        VARCHAR2(32),   --类别
   ITEM_COUNT           NUMBER(10,4),   --数量
   ITEM_UNIT_PRICE      NUMBER(10,4),   --单价
   PAY_AMT              NUMBER(10,4),   --金额
   FORM_REF_ID          VARCHAR2(512),  --（被汇总的）原始单据编号
   REQUESTER            VARCHAR2(512),  --订货部门
   PROVIDER_ID          VARCHAR2(32),   --供应商ID
   PROVIDER             VARCHAR2(64)    --供应商
);

--物流中心、中央工厂汇总单表与被汇总的原始单据关联对应表
drop table D_T1_COLLECT_REF_FORM cascade constraints;
create table D_T1_COLLECT_REF_FORM
(
   COLLECT_FORM_ID              VARCHAR2(32),   --单据编号
   REF_FORM_ID                  VARCHAR2(32),   --被汇总的原始单据编号
   ITEM_ID                      VARCHAR2(32),    --被汇总的物料编号
    CONSTRAINT COLL_UNIQUE UNIQUE (COLLECT_FORM_ID, REF_FORM_ID, ITEM_ID)
);

--餐厅、物流中心、中央工厂入库单表头
drop table D_T1_INPUT_HEADER cascade constraints;
create table D_T1_INPUT_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   FORM_REF_ID          VARCHAR2(32),   --前置单据，对应的采购单或生产工单
   FORM_TYPE            VARCHAR2(16),   --入库单类型(PURCHASING:采购入库,PRODUCE:生产入库,MANUAL:手动入库，SEMIS：半成品入库，OUTER:外部入库 ,JSRK :拒收入库)
   INPUT_DEPARTMENT_ID  VARCHAR2(32),   --入库部门ID
   INPUT_DEPARTMENT     VARCHAR2(150),   --入库部门
   INPUTER_ID           VARCHAR2(32),   --制单人员ID
   INPUTER              VARCHAR2(64),   --制单人员
   STORAGE_ID 			VARCHAR2(32) NOT NULL,   --入库仓库标识(仅对多仓库有效)
   STORAGE              VARCHAR2(64),   --入库仓库名称(仅对多仓库有效)
   INPUT_TIME           DATE,           --制单时间
   PROVIDER_ID          VARCHAR2(32),   --供应商ID
   PROVIDER             VARCHAR2(150),   --供应商
   FORM_NOTE            VARCHAR2(200),  --备注说明
   AUDITOR_ID           VARCHAR2(32),   --审核人员ID
   AUDITOR              VARCHAR2(64),   --审核人员
   AUDIT_TIME           DATE,           --审核时间
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间
   RETURN_STATUS        VARCHAR2(32),   --退货状态(未退货/已退货)
   ALL_PAY_AMT          NUMBER(10,4),   --总金额
   MAX_PAY_ITEM         VARCHAR2(150),    --金额最大的商品
   CONSTRAINT INPUT_HEADER_PK PRIMARY KEY (FORM_ID)
);

--餐厅、物流中心、中央工厂入库单明细
drop table D_T1_INPUT_DETAIL cascade constraints;
create table D_T1_INPUT_DETAIL
(
   FORM_ID              VARCHAR2(32),   --单据编号
   ITEM_ID              VARCHAR2(32),   --商品编码
   ITEM_NAME            VARCHAR2(150),   --商品名称
   ITEM_DIMENSION       VARCHAR2(32),   --单位
   ITEM_SPECIFICATION   VARCHAR2(128),   --规格
   ITEM_CATEGORY        VARCHAR2(32),   --类别
   ORDER_COUNT          NUMBER(14,4),   --订货量
   RECEIVED_COUNT       NUMBER(14,4),   --已入库数（前面多个入库单的累计数）
   RECEIVE_COUNT        NUMBER(14,4),   --实收数
   DIFFERENT_COUNT      NUMBER(14,4),   --实收差异
   ITEM_UNIT_PRICE      NUMBER(10,4),   --标准价
   PAY_AMT              NUMBER(14,4),   --标准金额
   RECEIVE_PRICE        NUMBER(10,4),   --进货价
   RECEIVE_AMT          NUMBER(14,4),   --进货金额
   BATCH                VARCHAR2(80),   --批次(仓库id-商品id-时间)
   EXPIRED_TIME         DATE,            --有效期
   CONSTRAINT INPUT_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--餐厅配送单、物流中心出货单、中央工厂出货单表头
drop table D_T1_SHIPPING_HEADER cascade constraints;
create table D_T1_SHIPPING_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   FORM_REF_ID          VARCHAR2(32),   --前置单据，对应的采购单
   FORM_TYPE            VARCHAR2(32),   --单据类型(统配配送单:INNER_UNIFIED,越库配送单:INNER_CROSS,出货单:OUTER)
   PROVIDER_ID          VARCHAR2(32),   --配送部门ID
   PROVIDER             VARCHAR2(150),   --配送部门
   OUT_STORAGE_ID       VARCHAR2(32),   --出库仓库标识(仅对多仓库有效)
   OUT_STORAGE          VARCHAR2(64),   --出库仓库名称(仅对多仓库有效)
   RECEIVE_TIME         DATE,           --配送日期
   REQUESTER_ID         VARCHAR2(32),   --订货部门ID
   REQUESTER            VARCHAR2(150),   --订货部门
   REQUEST_ADDRESS      VARCHAR2(100),  --订货地址
   IN_STORAGE_ID        VARCHAR2(32),   --入库仓库标识(仅对多仓库有效)
   IN_STORAGE           VARCHAR2(64),   --入库仓库名称(仅对多仓库有效)
   FORM_MAKER_ID        VARCHAR2(32),   --制单人员ID
   FORM_MAKER           VARCHAR2(64),   --制单人员
   FORM_TIME            DATE,           --制单日期
   FORM_TIME_ACTUAL     DATE,           --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),   --审核人员ID
   AUDITOR              VARCHAR2(64),   --审核人员
   AUDIT_TIME           DATE,           --审核日期
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间
   INPUTER_ID           VARCHAR2(32),   --入库人员ID(仅对餐厅配送单有效)
   INPUTER              VARCHAR2(64),   --入库人员(仅对餐厅配送单有效)
   INPUT_TIME           DATE,           --入库时间(仅对餐厅配送单有效)
   PICK_STATUS          VARCHAR2(32),   --捡货状态(未捡货/已捡货)
   INPUT_STATUS         VARCHAR2(32),   --入库状态(未入库/已入库)
   DIFFERENT_STATUS     VARCHAR2(32),   --配送差异处理状态(未处理/已处理)
   RETURN_STATUS        VARCHAR2(32),   --退货状态(退货中/已退货)
   ANTI_STATUS          VARCHAR2(32),   --反审核状态(反审核中/已反审核)
   ON_STATUS            VARCHAR2(32),   --在途状态(空/在途)
   FORM_NOTE            VARCHAR2(200),  --备注说明
   ALL_PAY_AMT          NUMBER(10,4),   --总金额
   MAX_PAY_ITEM         VARCHAR2(150),    --金额最大的商品
   CONSTRAINT SHIPPING_HEADER_PK PRIMARY KEY (FORM_ID)
);

--餐厅配送单、物流中心出货单、中央工厂出货单明细
drop table D_T1_SHIPPING_DETAIL cascade constraints;
create table D_T1_SHIPPING_DETAIL
(
   FORM_ID              VARCHAR2(32),   --单据编号
   ITEM_ID              VARCHAR2(32),   --商品编码
   ITEM_NAME            VARCHAR2(150),   --商品名称
   ITEM_DIMENSION       VARCHAR2(32),   --单位
   ITEM_SPECIFICATION   VARCHAR2(128),   --规格
   ITEM_CATEGORY        VARCHAR2(32),   --类别
   PACKAGING_FACTOR     NUMBER(8,4),    --包装因子
   PACKAGING_UNIT       VARCHAR2(8),    --包装单位
   PACKAGING_COUNT      NUMBER(10,4),   --包装数量
   REQUEST_COUNT        NUMBER(10,4),   --订货数
   SHIPPING_COUNT       NUMBER(10,4),   --配送数
   DELIVERY_COUNT       NUMBER(10,4),   --实发数
   RECEIVE_COUNT        NUMBER(10,4),   --实收数
   DIFFERENT_COUNT      NUMBER(10,4),   --差异数
   DIFFERENT_REASON     VARCHAR2(100),  --差异原因
   ITEM_UNIT_PRICE      NUMBER(10,4),   --单价
   PAY_AMT              NUMBER(10,4),   --金额
   EXPIRED_TIME         DATE,            --有效期
   CONSTRAINT SHIPPING_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--餐厅配送反审核单表头
drop table D_T1_SHIPPING_ANTIAUDIT_HEADER cascade constraints;
create table D_T1_SHIPPING_ANTIAUDIT_HEADER
(
   FORM_REF_ID          VARCHAR2(32),   --相关联的配送单编号
   ANTIAUDITOR_ID       VARCHAR2(32),   --反审核人员ID
   ANTIAUDITOR          VARCHAR2(64),   --反审核人员
   ANTIAUDIT_BRANCH_ID  VARCHAR2(32),   --反审核部门ID
   ANTIAUDIT_BRANCH     VARCHAR2(150),   --反审核部门
   ANTIAUDIT_TIME       DATE,           --反审核时间
   AUDIT_TIME          	DATE, 			--审核处理时间
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间
   FORM_NOTE            VARCHAR2(200)   --反审核备注
);

--餐厅配送反审核单表明细
drop table D_T1_SHIPPING_ANTIAUDIT_DETAIL cascade constraints;
create table D_T1_SHIPPING_ANTIAUDIT_DETAIL
(
   FORM_REF_ID             VARCHAR2(32),   --相关联的配送单编号
   ITEM_ID                 VARCHAR2(32),   --商品编码
   ANTIAUDIT_RECEIVE_COUNT NUMBER(10,4),   --反审核实收数
   ANTIAUDIT_PAY_AMT       NUMBER(10,4)    --反审核金额
);

--餐厅退货单表头
drop table D_T1_RETURN_GOODS_HEADER cascade constraints;
create table D_T1_RETURN_GOODS_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   FORM_REF_ID          VARCHAR2(32),   --相关联的配送单或采购单编号
   RETURNER_ID          VARCHAR2(32),   --退货人员ID
   RETURNER             VARCHAR2(64),   --退货人员
   RETURN_TIME          DATE,           --退货时间
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间	
   AUDIT_TIME          	DATE, 			--审核处理时间
   FORM_NOTE            VARCHAR2(200),  --退货备注说明
   ALL_PAY_AMT          NUMBER(10,4),   --总金额
   MAX_PAY_ITEM         VARCHAR2(150),    --金额最大的商品
   CONSTRAINT RETURN_GOODS_HEADER_PK PRIMARY KEY (FORM_ID)
);

--餐厅退货单表明细
drop table D_T1_RETURN_GOODS_DETAIL cascade constraints;
create table D_T1_RETURN_GOODS_DETAIL
(
   FORM_ID              VARCHAR2(32),   --退货单据编号
   FORM_REF_ID          VARCHAR2(32),   --相关联的配送单或采购单编号
   ITEM_ID              VARCHAR2(32),   --商品编码
   RETURN_COUNT         NUMBER(10,4),   --退货单退货数
   RETURN_PAY_AMT       NUMBER(10,4),   --退货单金额
   RETURN_REASON        VARCHAR2(200),   --退货原因
   CONSTRAINT RETURN_GOODS_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--餐厅、中央工厂、物流中心报损单表头
drop table D_T1_LOSS_HEADER cascade constraints;
create table D_T1_LOSS_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   LOSS_TYPE            VARCHAR2(8),    --报损单类型：RAWLOSS原料报损/DISHLOSS出品报损
   LOSS_BRANCH_ID       VARCHAR2(32),   --报损部门ID（多仓库的情况下是仓库ID）
   LOSS_BRANCH          VARCHAR2(150),   --报损部门
   STORAGE_ID           VARCHAR2(32),   --报损仓库标识(仅对多仓库有效)
   STORAGE              VARCHAR2(64),   --报损仓库名称(仅对多仓库有效)
   LOSS_MAN_ID          VARCHAR2(32),   --报损人员ID
   LOSS_MAN             VARCHAR2(64),   --报损人员
   LOSS_TIME            DATE,           --报损日期
   AUDITOR_ID           VARCHAR2(32),   --审核人员ID
   AUDITOR              VARCHAR2(64),   --审核人员
   AUDIT_TIME           DATE,           --审核日期
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间
   PROCESSOR_ID         VARCHAR2(32),   --处理人员ID
   PROCESSOR            VARCHAR2(64),   --处理人员
   PROCESS_TIME         DATE,           --审核日期
   FORM_NOTE            VARCHAR2(1024),  --备注说明
   ALL_PAY_AMT          NUMBER(10,4),   --总金额
   MAX_PAY_ITEM         VARCHAR2(150),    --金额最大的商品
   CONSTRAINT LOSS_HEADER_PK PRIMARY KEY (FORM_ID)
);

--餐厅、中央工厂、物流中心报损单明细
drop table D_T1_LOSS_DETAIL cascade constraints;
create table D_T1_LOSS_DETAIL
(
   FORM_ID              VARCHAR2(32),   --单据编号
   ITEM_ID              VARCHAR2(32),   --商品编码
   ITEM_NAME            VARCHAR2(150),   --商品名称
   ITEM_DIMENSION       VARCHAR2(32),   --单位(出品例牌)
   ITEM_SPECIFICATION   VARCHAR2(128),   --规格
   ITEM_ID2             VARCHAR2(32),   --出品商品编码
   ITEM_NAME2           VARCHAR2(64),   --出品商品名称
   ITEM_DIMENSION2      VARCHAR2(32),   --出品单位(出品例牌)
   ITEM_CATEGORY        VARCHAR2(32),   --类别
   ITEM_COUNT           NUMBER(10,4),   --报损数量
   ITEM_COUNT2          NUMBER(10,4),   --出品报损数量
   UNIT_PRICE           NUMBER(10,4),   --单价
   PAY_AMT              NUMBER(10,4),   --金额
   STORAGE_COUNT        NUMBER(12,4),   --库存数量
   EXPIRED_TIME         DATE,           --原料有效期
   REASON               VARCHAR2(200),   --报损原因
   CONSTRAINT ITEM_UNIQUE UNIQUE (FORM_ID, ITEM_ID, ITEM_ID2)
);

--餐厅调拨单表头
drop table D_T1_TRANSFER_HEADER cascade constraints;
create table D_T1_TRANSFER_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   IN_BRANCH_ID         VARCHAR2(32),   --调入部门ID
   IN_BRANCH            VARCHAR2(150),   --调入部门
   IN_STORAGE_ID        VARCHAR2(32),   --入库仓库标识(仅对多仓库有效)
   IN_STORAGE           VARCHAR2(64),   --入库仓库名称(仅对多仓库有效)
   OUT_BRANCH_ID        VARCHAR2(32),   --调出部门ID
   OUT_BRANCH           VARCHAR2(150),   --调出部门
   OUT_STORAGE_ID       VARCHAR2(32),   --出库仓库标识(仅对多仓库有效)
   OUT_STORAGE          VARCHAR2(64),   --出库仓库名称(仅对多仓库有效)
   FROM_MAKER_ID        VARCHAR2(32),   --制单人员ID
   FROM_MAKER           VARCHAR2(64),   --制单人员
   FORM_TIME            DATE,           --制单日期
   FORM_TIME_ACTUAL     DATE,           --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),   --审核人员ID
   AUDITOR              VARCHAR2(64),   --审核人员
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间
   AUDIT_TIME           DATE,           --审核日期
   CONFIRMER_ID         VARCHAR2(32),   --确认人员ID
   CONFIRMER            VARCHAR2(64),   --确认人员
   CONFIRM_TIME         DATE,           --确认日期
   OUT_MAN_ID           VARCHAR2(32),   --调出人员ID
   OUT_MAN              VARCHAR2(64),   --调出人员
   OUT_TIME             DATE,           --调出日期
   FORM_NOTE            VARCHAR2(200),  --备注说明
   ALL_PAY_AMT          NUMBER(10,4),   --总金额
   MAX_PAY_ITEM         VARCHAR2(150),    --金额最大的商品
   CONSTRAINT TRANSFER_HEADER_PK PRIMARY KEY (FORM_ID)
);

--餐厅调拨单明细
drop table D_T1_TRANSFER_DETAIL cascade constraints;
create table D_T1_TRANSFER_DETAIL
(
   FORM_ID              VARCHAR2(32),   --单据编号
   ITEM_ID              VARCHAR2(32),   --商品编码
   ITEM_NAME            VARCHAR2(150),   --商品名称
   ITEM_DIMENSION       VARCHAR2(32),   --单位
   ITEM_SPECIFICATION   VARCHAR2(128),   --规格
   ITEM_CATEGORY        VARCHAR2(32),   --类别
   APPLY_COUNT          NUMBER(10,4),   --申请调拨数量
   ACTUAL_COUNT         NUMBER(10,4),   --实际调拨数量
   DIFFERENT_COUNT      NUMBER(10,4),   --调拨差异
   UNIT_PRICE           NUMBER(10,4),   --单价
   PAY_AMT              NUMBER(10,4),   --金额
   EXPIRED_TIME         DATE  ,          --商品有效期
   CONSTRAINT TRANSFER_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--盘点锁库记录表
drop table D_T1_CHECK_LOCK cascade constraints;
create table D_T1_CHECK_LOCK
(
   CHECK_BATCH_ID       VARCHAR2(32),     --盘点批次号
   CHECK_BATCH_STATUS   VARCHAR2(32),     --盘点批次状态：未完成/已完成（是否由盘点清单生成盘点单）
   LOCK_BRANCH_ID       VARCHAR2(32),     --锁库部门ID
   LOCK_BRANCH          VARCHAR2(150),     --锁库部门
   LOCK_STORAGE_ID      VARCHAR2(32),     --锁库仓库标识(仅对多仓库有效)
   LOCK_STORAGE         VARCHAR2(64),     --锁库仓库名称(仅对多仓库有效)
   LOCK_MAN_ID          VARCHAR2(32),     --锁库人员ID
   LOCK_MAN             VARCHAR2(64),     --锁库人员
   LOCK_DATE            DATE,             --锁库日期（营业日期）
   LOCK_TIME            DATE,             --锁库时间（服务器的实际时间）
   LOCK_NOTE            VARCHAR2(200),    --备注说明
   ITEM_REPEATABLE      VARCHAR2(16),     --当前批次的多个盘点清单之间是否允许出现重复原料
   ITEM_CATEGORY        VARCHAR2(600),     --盘点原料类别（多个类别逗号分隔）
   CONSTRAINT CHECK_LOCK_PK PRIMARY KEY (CHECK_BATCH_ID)     
);

--盘点单/清单表头
drop table D_T1_CHECK_HEADER cascade constraints;
create table D_T1_CHECK_HEADER
(
   FORM_ID              VARCHAR2(32),     --单据编号
   FORM_TYPE            VARCHAR2(8)  NOT NULL,      --单据类型：盘点单/盘点清单
   CHECK_BATCH_ID       VARCHAR2(32),     --对应盘点批次
   CHECK_BRANCH_ID      VARCHAR2(32),     --盘点部门ID
   CHECK_BRANCH         VARCHAR2(150),     --盘点部门
   CHECK_STORAGE_ID     VARCHAR2(32),     --盘点仓库标识(仅对多仓库有效)
   CHECK_STORAGE        VARCHAR2(64),     --盘点仓库名称(仅对多仓库有效)
   FORM_MAKER_ID        VARCHAR2(32),     --制单人员ID
   FORM_MAKER           VARCHAR2(64),     --制单人员
   FORM_TIME            DATE,             --制单日期
   FORM_TIME_ACTUAL     DATE,          	  --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),     --审核人员ID
   AUDITOR              VARCHAR2(64),     --审核人员
   AUDIT_TIME           DATE,             --审核日期
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间
   FORM_NOTE            VARCHAR2(200),    --备注说明
   PRINT_COUNT          INT,              --打印次数
   ALL_PAY_AMT          NUMBER(14,4),     --总金额
   MAX_PAY_ITEM         VARCHAR2(150),      --金额最大的商品
   CONSTRAINT CHECK_HEADER_PK PRIMARY KEY (FORM_ID)
);

--盘点单/清单明细
drop table D_T1_CHECK_DETAIL cascade constraints;
create table D_T1_CHECK_DETAIL
(
   FORM_ID              VARCHAR2(32),     --单据编号
   ITEM_ID              VARCHAR2(32),     --商品编码
   ITEM_NAME            VARCHAR2(150),     --商品名称
   ITEM_DIMENSION       VARCHAR2(32),     --单位
   ITEM_SPECIFICATION   VARCHAR2(128),     --规格
   ITEM_CATEGORY        VARCHAR2(32),     --类别
   SHELF_ID             VARCHAR2(32),     --库位
   CHECK_COUNT          NUMBER(14, 4),    --盘点数量
   THEORY_COUNT         NUMBER(14, 4),    --理论数量
   EXPIRED_TIME         DATE,             --商品有效期
   ITEM_ORDER           INT,              --商品顺序
   ITEM_STATUS          VARCHAR2(32),      --商品盘点状态（漏盘:LEAK,正常:NORMAL）
   CONSTRAINT CHECK_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--中央工厂生产计划/排程单表头
drop table D_T1_ARRENGMENT_HEADER cascade constraints;
create table D_T1_ARRENGMENT_HEADER
(
   FORM_ID              VARCHAR2(32),  --单据编号
   FORM_REF_ID          VARCHAR2(32),  --相关联的原始单据编号
   FORM_BRANCH_ID       VARCHAR2(32),  --制单部门ID
   FORM_MAKER_ID        VARCHAR2(32),  --制单人员ID
   FORM_MAKER           VARCHAR2(64),  --制单人员
   FORM_TIME            DATE,          --制单日期
   FORM_TIME_ACTUAL     DATE,          --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),  --审核人员ID
   AUDITOR              VARCHAR2(64),  --审核人员
   AUDIT_TIME           DATE,          --审核日期
   FORM_NOTE            VARCHAR2(200), --备注说明
   PURCHASE_STATUS      VARCHAR2(32),   --是否下发采购单
   CONSTRAINT ARRENGMENT_HEADER_PK PRIMARY KEY (FORM_ID)
);

--中央工厂生产计划/排程单明细
drop table D_T1_ARRENGMENT_DETAIL cascade constraints;
create table D_T1_ARRENGMENT_DETAIL
(
   FORM_ID              VARCHAR2(32),  --单据编号
   ITEM_ID              VARCHAR2(32),  --商品编码
   ITEM_NAME            VARCHAR2(64),  --商品名称
   ITEM_DIMENSION       VARCHAR2(16),  --库存单位
   ITEM_DIMENSION2      VARCHAR2(16),  --包装单位
   ITEM_SPECIFICATION   VARCHAR2(64),  --规格
   PRODUCE_COUNT        NUMBER(10,4),  --计划生产量（库存单位）
   PRODUCE_COUNT2       NUMBER(10,4),  --计划生产量（包装单位）
   WORK_ORDER_ID        VARCHAR2(32),  --工单编号
   WORKSHOP             VARCHAR2(64),  --生产车间
   PRODUCE_TIME         DATE,          --生产日期
   PRODUCTION_CYCLE     INT,           --生产周期
   COMPLETE_TIME        DATE,          --完工日期(完工日期=生产日期+生产周期)
   NOTE                 VARCHAR2(200),  --备注
   CONSTRAINT ARRENGMENT_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--中央工厂生产工单表头
drop table D_T1_WORK_ORDER_HEADER cascade constraints;
create table D_T1_WORK_ORDER_HEADER
(
   FORM_ID              VARCHAR2(32),  --单据编号
   ITEM_ID              VARCHAR2(32),  --半成品编码
   ITEM_NAME            VARCHAR2(64),  --半成品名称
   ITEM_DIMENSION       VARCHAR2(16),  --库存单位
   ITEM_DIMENSION2      VARCHAR2(16),  --包装单位
   ITEM_SPECIFICATION   VARCHAR2(64),  --规格
   ITEM_COUNT           NUMBER(10,4),  --计划生产数量（库存单位）
   ITEM_COUNT2          NUMBER(10,4),  --计划生产数量（包装单位）
   ACTUAL_COUNT         NUMBER(10,4),  --实际生产数量(已入库数)
   WORKSHOP             VARCHAR2(64),  --生产车间
   FORM_BRANCH_ID       VARCHAR2(32),  --制单部门ID
   FORM_MAKER_ID        VARCHAR2(32),  --制单人员ID
   FORM_MAKER           VARCHAR2(64),  --制单人员
   FORM_TIME            DATE,          --制单日期
   FORM_TIME_ACTUAL     DATE,          --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),  --审核人员ID
   AUDITOR              VARCHAR2(64),  --审核人员
   AUDIT_TIME           DATE,          --审核日期
   RECEIVED_STATUS		VARCHAR2(32),  --领料状态(初始状态 default,部分原料可领料 receiving,所有原料不能领料 received)
   INPUTED_COUNT        NUMBER(14,4),  --已入库数量
   --COMPLETE_TIME        DATE,         --实际完工日期(结案)
   BATCH_FLAG           VARCHAR2(1),   --批量生成标志
   FORM_NOTE            VARCHAR2(200), --备注说明
    CONSTRAINT WORK_ORDER_HEADER_PK PRIMARY KEY (FORM_ID)
);

--中央工厂生产工单制程明细
drop table D_T1_WORK_ORDER_DETAIL cascade constraints;
create table D_T1_WORK_ORDER_DETAIL
(
   FORM_ID              VARCHAR2(32),  --单据编号
   PRODUCTION_NAME      VARCHAR2(32),  --制程
   PRODUCTION_STEP      INT,           --制程顺序号
   PRODUCTION_TIME      DATE,          --日期
   PRODUCTION_COUNT     NUMBER(10,4),  --产量
   PRODUCTION_MAN       VARCHAR2(64),  --生产人员
   PRODUCTION_NOTE      VARCHAR2(200), --备注
   IS_COMPLETED         INT            --该步骤是否完成,0表示未完成,1表示完成 
);

--中央工厂生产工单原料明细
drop table D_T1_WORKORDER_ITEM cascade constraints;
create table D_T1_WORKORDER_ITEM
(
   FORM_ID              VARCHAR2(32),  --单据编号
   ITEM_ID              VARCHAR2(32),  --原料编码
   ITEM_NAME            VARCHAR2(64),  --原料名称
   ITEM_DIMENSION       VARCHAR2(16),  --库存单位
   ITEM_SPECIFICATION   VARCHAR2(64),  --规格
   ITEM_COUNT           NUMBER(14,4),  --计划领料数量
   RECEIVED_COUNT       NUMBER(14,4),  --已领料数量
   RETURNED_COUNT       NUMBER(14,4),   --已退料数量
   CONSTRAINT WORKORDER_ITEM_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--中央工厂生产领料单、中央工厂超领单、中央工厂非工单领料单、中央工厂生产退料单表头
--生产领料单PRODUCE_REQUEST_MATERIAL/produce
--超领单EXTRA_REQUEST_MATERIAL/extra
--非工单领料单NOTWORK_REQUEST_MATERIAL/manual
--生产退料单PRODUCE_RETURN_MATERIAL/return
drop table D_T1_REQUISITION_HEADER cascade constraints;
create table D_T1_REQUISITION_HEADER
(
   FORM_ID              VARCHAR2(32),  --单据编号
   FORM_TYPE            VARCHAR2(32),  --单据类型(生产领料单/超领单/非工单领料单/生产退料单)
   FORM_REF_ID          VARCHAR2(32),  --原始单据编号
   WORKSHOP             VARCHAR2(64),  --（领料/退料）车间
   STORAGE_ID           VARCHAR2(32),  --（出货/收货）仓库ID
   STORAGE              VARCHAR2(64),  --（出货/收货）仓库
   FORM_BRANCH_ID       VARCHAR2(32),  --制单部门ID
   FORM_MAKER_ID        VARCHAR2(32),  --制单人员ID
   FORM_MAKER           VARCHAR2(64),  --制单人员
   FORM_TIME            DATE,          --制单日期
   FORM_TIME_ACTUAL     DATE,          --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),  --审核人员ID
   AUDITOR              VARCHAR2(64),  --审核人员
   AUDIT_TIME           DATE,          --审核日期
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间
   FORM_NOTE            VARCHAR2(200),  --备注说明
   CONSTRAINT REQUISITION_HEADER_PK PRIMARY KEY (FORM_ID)
);

--中央工厂生产领料单、中央工厂超领单、中央工厂非工单领料单、中央工厂生产退料单表明细
drop table D_T1_REQUISITION_DETAIL cascade constraints;
create table D_T1_REQUISITION_DETAIL
(
   FORM_ID              VARCHAR2(32),  --单据编号
   ITEM_ID              VARCHAR2(32),  --商品编码
   ITEM_NAME            VARCHAR2(64),  --商品名称
   ITEM_DIMENSION       VARCHAR2(16),  --单位
   ITEM_SPECIFICATION   VARCHAR2(64),  --规格
   ITEM_COUNT           NUMBER(14,4),  --计划领料数（对于退料单来说就是实际退料数）
   RECEIVE_COUNT        NUMBER(14,4),  --实际领料数（对于退料单无效）
   DIFFERENT_COUNT      NUMBER(14,4),  --实领差异（对于退料单无效）
   EXPIRED_TIME         DATE   ,        --商品有效期
   CONSTRAINT REQUISITION_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--捡货单表头
drop table D_T1_PICKING_HEADER cascade constraints;
create table D_T1_PICKING_HEADER
(
   FORM_ID              VARCHAR2(32),  --单据编号
   PICKING_BRANCH_ID	VARCHAR2(32),  --捡货部门ID
   STORAGE_ID           VARCHAR2(32),  --捡货仓库ID
   STORAGE              VARCHAR2(64),  --捡货仓库
   FORM_MAKER_ID        VARCHAR2(32),  --制单人员ID
   FORM_MAKER           VARCHAR2(64),  --制单人员
   FORM_TIME            DATE,          --制单日期
   FORM_TIME_ACTUAL     DATE,          --制单日期(实际时间，包含时分秒)
   PICKING_MAN_ID       VARCHAR2(32),  --捡货人员ID
   PICKING_MAN          VARCHAR2(64),  --捡货人员
   PICKING_TIME         DATE,          --捡货日期
   AUDITOR_ID           VARCHAR2(32),  --审核人员ID
   AUDITOR              VARCHAR2(64),  --审核人员
   AUDIT_TIME           DATE,          --审核日期
   PACK_STATUS          VARCHAR2(32),  --装箱状态(未生成/已生成)
   FORM_NOTE            VARCHAR2(200), --单据下方的说明
   CONSTRAINT PICKING_HEADER_PK PRIMARY KEY (FORM_ID)
);

--捡货单表明细
drop table D_T1_PICKING_DETAIL cascade constraints;
create table D_T1_PICKING_DETAIL
(
   FORM_ID              VARCHAR2(32),  --单据编号
   ITEM_ID              VARCHAR2(32),  --商品编码
   ITEM_NAME            VARCHAR2(150),  --商品名称
   ITEM_DIMENSION       VARCHAR2(32),  --单位
   ITEM_CATEGORY        VARCHAR2(32),  --商品类别
   ITEM_COUNT           NUMBER(10,4),  --捡货数量
   BRANCH_ID            VARCHAR2(32),   --店名
   CONSTRAINT PICKING_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID, BRANCH_ID)
);

--捡货单表明细对应配送单的关系
drop table D_T1_PICKING_REF cascade constraints;
create table D_T1_PICKING_REF
(
   FORM_ID              VARCHAR2(32),  --单据编号
   FORM_REF_ID          VARCHAR2(32),  --相关联的配送单编号
   ITEM_ID              VARCHAR2(32),  --商品编码
   BRANCH_ID            VARCHAR2(32),  --店名
   ITEM_COUNT           NUMBER(14,4) , --配送单的包装数量
   CONSTRAINT PICKING_REF_PK PRIMARY KEY (FORM_ID, FORM_REF_ID, ITEM_ID, BRANCH_ID)
);

--装箱单表头
drop table D_T1_PACKING_HEADER cascade constraints;
create table D_T1_PACKING_HEADER
(
   FORM_ID              VARCHAR2(32),  --单据编号
   PACKING_BRANCH_ID	   VARCHAR2(32),  --装箱部门ID
   FORM_REF_ID          VARCHAR2(32),  --相关联的原始单据编号(捡货单)
   FORM_MAKER_ID        VARCHAR2(32),  --制单人员ID
   FORM_MAKER           VARCHAR2(64),  --制单人员
   FORM_TIME            DATE,          --制单日期
   FORM_TIME_ACTUAL     DATE,          --制单日期(实际时间，包含时分秒)
   PACKING_TIME         DATE,          --装箱日期
   FORM_NOTE            VARCHAR2(200),  --单据下方的说明
   CONSTRAINT PACKING_HEADER_PK PRIMARY KEY (FORM_ID)
);

--装箱单明细
drop table D_T1_PACKING_DETAIL cascade constraints;
create table D_T1_PACKING_DETAIL
(
   FORM_ID              VARCHAR2(32),  --单据编号
   BRANCH_ID            VARCHAR2(32),  --送货门店
   BOX_ID           	   VARCHAR2(32),  --箱子编号
   BOX_NAME           	VARCHAR2(64),  --箱子名称(eg:海东青1号箱)
   ITEM_ID              VARCHAR2(32),  --商品编码
   ITEM_NAME            VARCHAR2(64),  --商品名称
   ITEM_DIMENSION  		VARCHAR2(16),  --包装单位
   UNIT_VOLUME     		NUMBER(10,4),  --单位体积
   UNIT_WEIGHT     		NUMBER(10,4),  --单位重量
   ITEM_COUNT      		NUMBER(14,4),  --包装数量
   VOLUME               NUMBER(10,4),  --体积
   WEIGHT               NUMBER(10,4),  --重量
   ITEM_CATEGORY        VARCHAR2(32) ,  --类别
   CONSTRAINT PACKING_DETAIL_PK PRIMARY KEY (FORM_ID, BOX_ID, ITEM_ID, BRANCH_ID)
);

--中央工厂售价调价单、中央工厂采购调价单、物流中心采购调价单、物流中心标准调价单、物流中心加盟调价单、物流中心零售调价单表头
drop table D_T1_PRICE_ADJUST_HEADER cascade constraints;
create table D_T1_PRICE_ADJUST_HEADER
(
   FORM_ID              VARCHAR2(32),  --单据编号
   FORM_TIME            DATE,          --单据日期
   ADJUST_TYPE          VARCHAR2(32),  --调价类型
   ADJUST_SCOPE         VARCHAR2(32),  --调价范围
   SUPPLIER_ID          VARCHAR2(32),  --供应商编号，采购调价单需要使用
   EFFECT_TYPE          VARCHAR2(32),  --生效方式
   EFFECT_TIME          DATE,          --生效时间
   NOTE                 VARCHAR2(32),  --备注
   FORM_MAKER           VARCHAR2(32),  --制表人员
   MAKE_TIME            DATE,          --制表日期
   AUDITOR              VARCHAR2(32),  --审核人员
   AUDIT_TIME           DATE,          --审核日期
   FORM_NOTE            VARCHAR2(200),  --单据下方的备注说明
   CONSTRAINT PRICE_ADJUST_HEADER_PK PRIMARY KEY (FORM_ID)
);

--中央工厂售价调价单、中央工厂采购调价单、物流中心采购调价单、物流中心标准调价单、物流中心加盟调价单、物流中心零售调价单明细
drop table D_T1_PRICE_ADJUST_DETAIL cascade constraints;
create table D_T1_PRICE_ADJUST_DETAIL
(
   FORM_ID              VARCHAR2(32),  --单据编号
   ITEM_ID              VARCHAR2(32),  --商品编码
   ITEM_NAME            VARCHAR2(150),  --商品名称
   ITEM_DIMENSION       VARCHAR2(32),  --单位
   ITEM_SPECIFICATION   VARCHAR2(128),  --规格
   ITEM_PACKAGER        NUMBER(8,4),   --包装因子
   OLD_PRICE            NUMBER(10,4),  --原价
   NEW_PRICE            NUMBER(10,4),   --新价
   CONSTRAINT PRICE_ADJUST_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--总部-研发样品信息表
DROP TABLE D_T1_SAMPLE_INFO cascade constraints;
CREATE TABLE D_T1_SAMPLE_INFO
 (
     ID 						VARCHAR2(36),
     PRODUCT_NAME 			VARCHAR2(64), 	--产品名称
     MINIMUM_ORDER_QUANTITY 	INTEGER, 		--起订量
     PRODUCE_PLACE 			VARCHAR2(100), 	--产地
     ORDER_CYCLE 			INTEGER, 		--订货周期(天)
     USE_CYCLE 				INTEGER, 		--使用周期(天，季节性因素)
     QUALIFICATION_PIC 		VARCHAR2(32) ,	--食品安全资格，资格证照片
     SAMPLE_PIC 				VARCHAR2(32) , 	--照片
     PRICE 					NUMBER(6,2),	--价格(元)
     GROSS_PRICE 			NUMBER(6,2),	--毛价(元)
     GROSS_WEIGHT 			NUMBER(6,2),	--毛重(克)
     NET_WEIGHT 				NUMBER(6,2),	--净重(克)
     NET_RATIO 				NUMBER(5,2),	--出净率(净菜/毛菜*100=出净率)
     SHELF_LIFE 				INTEGER,		--保质期(月)
     DELIVERY_TIME 			INTEGER, 		--送样时间(天)
     ACCPTANCE_CRITERIA 		VARCHAR2(300),	--验收标准
     SUPPLIER 				VARCHAR2(100),	--供应商
     CREATE_USER_ID 			VARCHAR2(32),
     CREATE_TIME 			DATE
 );

--供应商总部财务 外部订货方总部财务 对账单表头
drop table D_T1_STATEMENT_HEADER cascade constraints;
create table D_T1_STATEMENT_HEADER
(
   FORM_ID              VARCHAR2(32),  --单据编号
   FORM_TYPE            VARCHAR2(10),  --对账单据类型（供应商总部财务对账G，外部总部财务对账W）
   PROVIDER_ID          VARCHAR2(32),  --供应商ID
   PROVIDER             VARCHAR2(96),  --供应商
   BRANCH_ID            VARCHAR2(32),  --部门编号
   BRANCH_NAME          VARCHAR2(64),  --部门名称
   FORM_MAKER_ID        VARCHAR2(32),  --制单人员ID
   FORM_MAKER           VARCHAR2(64),  --制单人员
   FORM_TIME            DATE,          --制单日期
   FORM_TIME_ACTUAL     DATE,          --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),  --审核人员ID
   AUDITOR              VARCHAR2(64),  --审核人员
   AUDIT_TIME           DATE,          --审核日期
   ALL_PAY_AMT         NUMBER(10,4),   --总金额
   START_DATE           DATE,          --对账开始日期
   END_DATE             DATE,          --对账结束日期
   FORM_NOTE            VARCHAR2(200)  --备注说明
);

--供应商总部财务 外部订货方总部财务 对账单表明细
drop table D_T1_STATEMENT_DETAIL cascade constraints;
create table D_T1_STATEMENT_DETAIL
(
   FORM_ID              VARCHAR2(32),  --表头单据编号
   SUB_FORM_ID          VARCHAR2(32),  --入库单/采购订货退货单/出货单/外部订货退货单
   FORM_REF_ID          VARCHAR2(32),  --相关联的配送单编号
   ALL_PAY_AMT          NUMBER(10,4),  --总金额
   FORM_OPERATE_ID      VARCHAR2(32),  --操作人员ID
   FORM_OPERATE         VARCHAR2(64),  --操作人员
   FORM_OPERATE_TIME    DATE,          --操作日期
   FORM_NOTE            VARCHAR2(200)  --备注说明
);

--餐厅半成品加工单表头
drop table D_T1_SELF_SEMIS_HEADER cascade constraints;
create table D_T1_SELF_SEMIS_HEADER
(
   FORM_ID              VARCHAR2(32),   --单据编号
   BRANCH_ID            VARCHAR2(32),   --加工部门ID（多仓库的情况下是仓库ID）
   BRANCH               VARCHAR2(150),   --加工部门名称
   CREATOR_MAN_ID       VARCHAR2(32),   --制单人员ID
   FORM_MAKER           VARCHAR2(64),   --制单人员
   FORM_TIME            DATE,           --制单时间
   FORM_TIME_ACTUAL     DATE,           --制单日期(实际时间，包含时分秒)
   AUDITOR_ID           VARCHAR2(32),   --审核人员ID
   AUDITOR              VARCHAR2(64),   --审核人员
   AUDIT_TIME           DATE,           --审核日期
   AUDIT_TIME_ACTUAL    DATE,			--审核实际时间
   FORM_NOTE            VARCHAR2(1024), --备注说明
   MAIN_ITEM            VARCHAR2(200),  --主要加工的产品（列出前5种即可）
   CONSTRAINT SELF_SEMIS_HEADER_PK PRIMARY KEY (FORM_ID)
);

--餐厅半成品加工单明细
drop table D_T1_SELF_SEMIS_DETAIL cascade constraints;
create table D_T1_SELF_SEMIS_DETAIL
(
   FORM_ID              VARCHAR2(32),   --单据编号
   ITEM_ID              VARCHAR2(32),   --出品/原料编码
   ITEM_NAME            VARCHAR2(150),   --出品/原料名称
   ITEM_DIMENSION       VARCHAR2(32),   --出品例牌/原料单位
   ITEM_SPECIFICATION   VARCHAR2(128),   --出品统一设置为"FOOD"/原料规格
   ITEM_CATEGORY        VARCHAR2(32),   --出品/原料类别名称
   ITEM_COUNT_PLAN      NUMBER(10,4),   --出品计划生产数量/原料计划消耗数量
   ITEM_COUNT_ACTUAL    NUMBER(10,4),   --出品实际生产数量/原料实际消耗数量
   CONSTRAINT SELF_SEMIS_UNIQUE UNIQUE (FORM_ID, ITEM_ID)
);