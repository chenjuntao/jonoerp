--ϵͳѡ�����ñ�
drop table D_T0_OPTION cascade constraints;
create table D_T0_OPTION
(
   OPTION_KEY            VARCHAR2(16),   --ѡ���
   OPTION_VALUE          VARCHAR2(128)   --ѡ��ֵ
); 

--��������ǰ�����ϵ��
drop table D_T0_STATUS_FLOW cascade constraints;
create table D_T0_STATUS_FLOW
(
   WORK_FLOW            VARCHAR2(32),   --������������
   STEP                 VARCHAR2(32),   --��ǰ��������
   REF_STEP             VARCHAR2(32),   --�����Ĺ�������(START:��ʼ����;END:��������)
   IS_PREV_NEXT         VARCHAR2(16)    --����������ǰ�ò��軹�Ǻ�������(PREV:ǰ�ò���;NEXT:��������)
);

--������Ϣ��
--(����:RESTAURANT)
--(���˵�:FRANCHISEE)
--(���빤��:CENTRALFACTORY)
--(��������:LOGISTICSCENTER)
--(��Ӧ��:SUPPLIER)
--(�ⲿ������:OUTERORDER)
--(�ܲ�:HEADQUATER)
--�������ۣ�PURCHASE��
drop table D_T2_BRANCH cascade constraints;
create table D_T2_BRANCH
(
   BRANCH_ID            VARCHAR2(32),  --���ű��
   BRANCH_NAME          VARCHAR2(96),  --��������
   QUERY_CODE           VARCHAR2(32),  --������(������)
   BRANCH_ADDRESS       VARCHAR2(100), --���ŵ�ַ
   CONTACT_MAN          VARCHAR2(64),  --��ϵ��
   EMAIL                VARCHAR2(32),  --��������
   TELEPHONE            VARCHAR2(16),  --��ϵ�绰
   FAX                  VARCHAR2(16),  --����
   BRANCH_TYPE          VARCHAR2(16),  --��������(��������ϸ����)
   PRICE_TYPE          	VARCHAR2(16),  --�۸����ͣ����ڻ�ȡԭ�ϼ۸񣬲�����Ӧ��׼�ۡ����˵�Ծͼ��˼ۣ��ⲿ��������Ӧ�����ۻ����ۼ�
   REMARK               VARCHAR2(200), --��ע
   ENABLED              INTEGER, 	   --�Ƿ�����(0δ���ã�1������)
   PARK                 VARCHAR2(100), --ͣ������Ϣ
   LON                  NUMBER(11,8),  --����
   LAT                  NUMBER(11,8),  --γ��
   TAG                  VARCHAR2(50),  --��ǩ
   CONSTRAINT BRANCH_PK PRIMARY KEY (BRANCH_ID)
);

--�������ͱ�(�ݶ��ߴ���)
--(����:RESTAURANT)
--(���˵�:FRANCHISEE)
--(���빤��:CENTRALFACTORY)
--(��������:LOGISTICSCENTER)
--(��Ӧ��:SUPPLIER)
--(�ⲿ������:OUTERORDER)
--(�ܲ�:HEADQUATER)
drop table D_T2_BRANCH_TYPE cascade constraints;
create table D_T2_BRANCH_TYPE
(
   TYPE_ID           VARCHAR2(32),  --���ͱ���
   TYPE_NAME         VARCHAR2(64),  --��������
   DESCRIPTION       VARCHAR2(200),  --����
   CONSTRAINT BRANCH_TYPE_PK PRIMARY KEY (TYPE_ID)
);

--ԭ����/���Ʒ/��Ʒ����
drop table D_T2_ITEM_CATEGORY cascade constraints;
create table D_T2_ITEM_CATEGORY
(
   CATEGORY_ID          VARCHAR2(32),  --���ID
   CATEGORY_NAME        VARCHAR2(80),  --�������
   PARENT_ID            VARCHAR2(32),  --�����ID
   CATEGORY_LEVEL       VARCHAR2(8),   --��𼶱�
   ITEM_TYPE            VARCHAR2(8),   --����(ԭ����:RAW/���Ʒ:SEMIS/��Ʒ:PRODUCT)
   PATH                 VARCHAR(255),   --�����·��
   CONSTRAINT ITEM_CATEGORY_PK PRIMARY KEY (CATEGORY_ID)
);

--ԭ����/���Ʒ/��Ʒ������Ϣ��
drop table D_T2_ITEM_META cascade constraints;
create table D_T2_ITEM_META
(
   ITEM_ID              VARCHAR2(32),  --����
   ITEM_BAR_CODE        VARCHAR2(32),  --����
   ITEM_NAME            VARCHAR2(70),  --����
   ITEM_NAME_ENG        VARCHAR2(64),  --ԭ�ϼ�ƻ��ƷӢ������
   ITEM_DIMENSION       VARCHAR2(16),  --ԭ�Ͽ�浥λ���Ʒ����
   QUERY_CODE           VARCHAR2(64),  --������(������)
   CATEGORY_ID          VARCHAR2(32),  --������
   ITEM_SPECIFICATION   VARCHAR2(64),  --���
   ITEM_TYPE            VARCHAR2(8),   --����(ԭ����:RAW/���Ʒ:SEMIS/��Ʒ:PRODUCT)
   ITEM_PIC             VARCHAR2(32),  --��Ӧ��ͼƬid(��Ӧ��ͼƬ���е�ͼƬid)
   ITEM_NOTE            VARCHAR2(200), --��ע��Ϣ
   BOX_TYPE				VARCHAR2(32),  --��������(���/�����)
   ENABLED				INTEGER,	   --�Ƿ�����(0δ���ã�1������)
   EFFECT_DATE			DATE,	   	   --��Ч����(��Ʒ�������޸Ķ�ʱ��Ч)
   CONSTRAINT ITEM_META_PK PRIMARY KEY (ITEM_ID)
);

--��Ʒ�Ƴ�Ʒ��Ϣ��
drop table D_T2_BRAND_PRODUCT cascade constraints;
create table D_T2_BRAND_PRODUCT
(
   BRAND_ID             VARCHAR2(32),  --Ʒ�Ʊ���
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   ENABLED				INTEGER,	   --�Ƿ�����(0δ���ã�1������)
   EFFECT_DATE			DATE		   --��Ч����(��Ʒ�������޸Ķ�ʱ��Ч)
);

--��������(��ء�����ص�)
drop table D_T2_BOX_TYPE cascade constraints;
create table D_T2_BOX_TYPE
(
   TYPE_ID              VARCHAR2(32),  --����
   TYPE_NAME        	VARCHAR2(64),  --����
   VOLUME            	NUMBER(10,5),  --���(��/L)�����ܳ���
   WEIGHT        		NUMBER(10,5),  --����(��/G)�����ܳ���)
   ENABLED				INTEGER	 ,  	   --�Ƿ�����(0δ���ã�1������)
   CONSTRAINT BOX_TYPE_PK PRIMARY KEY (TYPE_ID)
);

--�洢��Ʒ�ײ͸��������Լ���������Ʒ
drop table D_T2_ITEM_FOOD_SUIT cascade constraints;
create table D_T2_ITEM_FOOD_SUIT
(
   FOOD_SUIT_ID              VARCHAR2(32),  --�ײͳ�Ʒ����
   FOOD_ID                   VARCHAR2(32),  --�ײ��������
   FOOD_OPTION_ID            VARCHAR2(32),  --�ײ�����ɻ�����루���Ϊ��ѡ������գ�
   ITEM_COUNT                NUMBER(10,5),  --�����Ʒ���������Ϊ��ѡ������գ�
   ITEM_PRICE                NUMBER(8,2),   --����(�ײ��ܼ�=������ۡ���������)
   IS_CURRENT                INT            --���ײ������Ƿ��ǵ�ǰ��ѡ(0��ʾ�ǵ�ǰ���裬1��ʾ��ǰ����)
);

/*--ԭ����������Ϣ��
drop table D_T2_ITEM_RAW cascade constraints;
create table D_T2_ITEM_RAW
(
   ITEM_ID              VARCHAR2(32),  --����
   ITEM_SPECIFICATION   VARCHAR2(64)   --���
);

--���Ʒ������Ϣ��
drop table D_T2_ITEM_SEMIS cascade constraints;
create table D_T2_ITEM_SEMIS
(
   ITEM_ID              VARCHAR2(32),  --����
   ITEM_SPECIFICATION   VARCHAR2(64)   --���
);

--��Ʒ������Ϣ��
drop table D_T2_ITEM_PRODUCT cascade constraints;
create table D_T2_ITEM_PRODUCT
(
   ITEM_ID              VARCHAR2(32)   --����
);*/

--ԭ����/���Ʒ/��Ʒ�۸��
--(����/�ɹ���:PURCHASE)
--(��׼��:BENCHMARK)
--(���˼�:JOIN)
--(ԭ�����ۼ�:RETAIL)
--(������:WHOLESALE)
--(��Ӧ�̼۸�:SUPPLIER)
--(��Ʒ������:SALE)
drop table D_T2_ITEM_PRICE cascade constraints;
create table D_T2_ITEM_PRICE
(
   ITEM_ID              VARCHAR2(32),  --����
   ITEM_PRICE           NUMBER(10,4),  --����
   PRICE_TYPE           VARCHAR2(32),  --�����۸���(��������ϸ����)
   IS_CURRENT           INT,           --�Ƿ��ǵ�ǰ��Ч�۸�
   EFFITIVE_FORM_ID     VARCHAR2(32),  --��Ч�������
   EFFECT_TIME          DATE,          --��Чʱ��
   SUPPLIER_ID          VARCHAR2(32)   --��Ӧ�̱���
);

--�ɱ���
drop table D_T2_THERAPY cascade constraints;
create table D_T2_THERAPY
(
   THERAPY_ID           VARCHAR2(32),  --�䷽����(��Ʒ����Ʒ����)
   THERAPY_NAME         VARCHAR2(64),  --��Ʒ����Ʒ����
   THERAPY_DIMENSION    VARCHAR2(16),  --��Ʒ���ƻ���Ʒ��λ
   ITEM_ID              VARCHAR2(32),  --ԭ���ϱ���
   ITEM_NAME            VARCHAR2(64),  --ԭ��������
   ITEM_DIMENSION       VARCHAR2(16),  --ԭ�����䷽��λ
   UNIT_CONVERT_FACTOR  NUMBER(19,5),  --�䷽��λ����ڿ�浥λ��ת������(�䷽��λ=��浥λ*ת������)
   ITEM_COUNT           NUMBER(10,5),  --���Ϻ���(��λΪ�䷽��λ)
   ITEM_USEAGE_RATE     NUMBER(10,5),  --���Ϻ���
   ITEM_GROSS_COUNT     NUMBER(10,5),  --ԭ�Ϻ���(��λΪ��浥λ)(ԭ�Ϻ���=(���Ϻ���/(���Ϻ���/100)/ת������)
   ITEM_AMT             NUMBER(10,5),  --ԭ�ϳɱ�(���=ԭ�Ϻ���/ת������*������)
   ITEM_TYPE            VARCHAR2(16),  --ԭ������(����/����/����)
   OWNER                VARCHAR2(32),   --�ɱ�����������(���빤�����/�ŵ��Ʒ�Ʒ�����)
    CONSTRAINT D_T2_THERAPY_PK PRIMARY KEY (THERAPY_ID, ITEM_ID, OWNER)
);

--�ŵ��Ʒ�Ʒ���
drop table D_T2_BRAND_GROUP cascade constraints;
create table D_T2_BRAND_GROUP
(
   BRAND_ID             VARCHAR2(32),  --Ʒ�Ʒ������
   BRAND_NAME           VARCHAR2(32),  --Ʒ�Ʒ�������
   BRAND_NOTE           VARCHAR2(64)   --Ʒ�Ʒ��鱸ע��Ϣ
);

--��Ʒ�Ʒ������ŵ�Ķ�Ӧ��ϵ
drop table D_T2_BRAND_BRANCH cascade constraints;
create table D_T2_BRAND_BRANCH
(
   BRAND_ID             VARCHAR2(32),  --Ʒ�Ʒ������
   BRANCH_ID            VARCHAR2(32)   --�ŵ����
);

--�۸���
drop table D_T2_PRICE_GROUP cascade constraints;
create table D_T2_PRICE_GROUP
(
   PRICE_GROUP_ID             VARCHAR2(32),  --�۸������
   PRICE_GROUP_NAME           VARCHAR2(32),  --�۸�������
   PRICE_GROUP_TYPE           VARCHAR2(32),  --�۸�������(�����������ۼ۸���LC/�ŵ��Ʒ�Ƽ۸���BRAND)
   PRICE_GROUP_NOTE           VARCHAR2(64),  --�۸��鱸ע��Ϣ
   OWNER                      VARCHAR2(32)   --�۸��������ĸ������(�������ı��/�ŵ��Ʒ�Ʒ�����)
);

--�۸������ŵ�Ķ�Ӧ��ϵ
drop table D_T2_PRICE_GROUP_BRANCH cascade constraints;
create table D_T2_PRICE_GROUP_BRANCH
(
   PRICE_GROUP_ID       VARCHAR2(32),  --�۸������
   BRANCH_ID            VARCHAR2(32)   --�ŵ����/�ⲿ����������
);

/* ���Ƕ��װ������������ı������ݾ�����Ҫ������
--ԭ���ϵĿ���������λ������������ڶ��װ��
drop table D_T2_ITEM_UNIT_CONVERSION cascade constraints;
create table D_T2_ITEM_UNIT_CONVERSION
(
   ITEM_ID              VARCHAR2(32),  --����
   ITEM_DIMENSION       VARCHAR2(16),  --������λ����
   CONVERSION_FACTOR    NUMBER(19,4)
--����ԭ������˵����ʾ������λ����ڿ�浥λ�Ļ�������
--���磬��浥λΪ�������λΪ�ˣ�����������Ϊ500
);
*/

--�ֿ�-���Ź�����
drop table D_T2_BRANCH_STORAGE cascade constraints;
create table D_T2_BRANCH_STORAGE
(
   BRANCH_ID            VARCHAR2(32),  --���ű�ʶ
   STORAGE_ID           VARCHAR2(32),  --�ֿ��ʶ
   STORAGE_NAME         VARCHAR2(64),  --�ֿ�����
   PRIORITY             INT            --�ֿ����ȼ���0��߼���1��2��3...���˽��ͣ�
);

--�ֿ�洢��Ϣ��
drop table D_T2_STORAGE cascade constraints;
create table D_T2_STORAGE
(
   STORAGE_ID           VARCHAR2(32),  --�ֿ��ʶ
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   ITEM_COUNT           NUMBER(12,4),  --��Ʒ����
   SHELF_ID             VARCHAR2(32),  --��λ
   LAST_IN_TIME         DATE,          --������ʱ��
   EXPIRED_TIME         DATE           --ԭ����Ч��
);

--�ֿ�-���������
drop table D_T2_STORAGE_WORKSHOP cascade constraints;
create table D_T2_STORAGE_WORKSHOP
(
   STORAGE_ID           VARCHAR2(32),  --�ֿ��ʶ
   WORK_ORDER_ID        VARCHAR2(32),  --������
   WORKSHOP             VARCHAR2(64),  --��������
   PRIORITY             INT            --�������ȼ���0��߼���1��2��3...���˽��ͣ�
);

--����洢��Ϣ��
drop table D_T2_WORKSHOP cascade constraints;
create table D_T2_WORKSHOP
(
   WORK_ORDER_ID        VARCHAR2(32),  --������
   WORKSHOP             VARCHAR2(64)  --��������
);

--��λ��Ϣ������Ҫ���������ļ��ʹ��
drop table D_T2_SHELF cascade constraints;
create table D_T2_SHELF
(
   SHELF_ID             VARCHAR2(32),  --���
   SHELF_NAME           VARCHAR2(32),  --����
   DESCRIPTION          VARCHAR2(120),  --����
   CONSTRAINT SHELF_PK PRIMARY KEY (SHELF_ID)
);

--��λ�����Ϲ�ϵ������Ҫ���������ļ��ʹ��
drop table D_T2_SHELF_ITEM cascade constraints;
create table D_T2_SHELF_ITEM
(
   SHELF_ID             VARCHAR2(32),  --��λ
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   PRIORITY             INT           --��λ���ȼ���0��߼���1��2��3...���˽��ͣ�
);

--һ������ֻ����һ������λ�����ǿ����ж��������λ
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


--MRP�����õ��������
drop table D_T2_MRP_AMOUNT cascade constraints;
create table D_T2_MRP_AMOUNT
(
   BRANCH_ID            VARCHAR2(32),  --���ű�ʶ
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   ALLOCATION           NUMBER(12,4),  --�ѷ�����
   ON_THE_WAY           NUMBER(12,4),   --��;��
   CONSTRAINT MRP_AMOUNT_PK PRIMARY KEY (BRANCH_ID, ITEM_ID),
   CONSTRAINT MRP_AMOUNT_CHECK_ALLOCATION CHECK (ALLOCATION>=0),
   CONSTRAINT MRP_AMOUNT_CHECK_ON_THE_WAY CHECK (ON_THE_WAY>=0)
);


--��Ӧ�����ŵ��Լ���Ʒ�Ķ�Ӧ��ϵ
drop table D_T2_SUPPLIER_BRANCH_ITEM cascade constraints;
create table D_T2_SUPPLIER_BRANCH_ITEM
(
   SUPPLIER_ID          VARCHAR2(32),  --��Ӧ�̱��
   BRANCH_ID            VARCHAR2(32),  --�ŵ���
   ITEM_ID              VARCHAR2(32),  --��Ʒ���
   PRIORITY             INT            --��Ӧ�����ȼ���0��߼�����Ӧ�̣�1��2��3...���˽��ͣ�
);

--��Ӧ�̹�Ӧ��ԭ���ϵĹ������ڱ�
drop table D_T2_SUPPLY_CYCLE cascade constraints;
create table D_T2_SUPPLY_CYCLE
(
   BRANCH_ID            VARCHAR2(32),  --������ID(�������Ļ����빤��)
   ITEM_ID              VARCHAR2(32),  --��Ʒ���
   SUPPLY_CYCLE         NUMBER(6,2),   --��������(��)
   INVENTORYS_CYCLE    	NUMBER(6,2)    --������ڣ��죩
);

--���Ʒ�������ڱ�
drop table D_T2_PRODUCTION_CYCLE cascade constraints;
create table D_T2_PRODUCTION_CYCLE
(
   BRANCH_ID            VARCHAR2(32),  --���빤��ID
   ITEM_ID              VARCHAR2(32),  --����
   PRODUCTION_CYCLE     NUMBER(6,2),   --��������
   CONSTRAINT MY_CONSTRAINT UNIQUE (BRANCH_ID, ITEM_ID)
);


--���ͣ����飩�����
drop table D_T2_DELIVERY_REGION cascade constraints;
create table D_T2_DELIVERY_REGION
(
   REGION_ID            VARCHAR2(32),  --��������ID
   REGION_NAME          VARCHAR2(32),  --������������
   BRANCH_ID            VARCHAR2(32),  --����������������ID
   DELIVERY_CYCLE       INT,           --��������(��)
   ENABLED				INTEGER	   	   --�Ƿ�����(0δ���ã�1������)
);

--��������������ŵ��
drop table D_T2_DELIVERY_REGION_BRANCH cascade constraints;
create table D_T2_DELIVERY_REGION_BRANCH
(
   REGION_ID            VARCHAR2(32),  --��������ID
   BRANCH_ID            VARCHAR2(32),   --�������������ŵ�ID
   CONSTRAINT D_T2_DELIVERY_REGION_BRANCH_PK PRIMARY KEY (REGION_ID, BRANCH_ID)
);

--����ģʽ��
drop table D_T2_DELIVERY_TYPE cascade constraints;
create table D_T2_DELIVERY_TYPE
(
   REGION_ID            VARCHAR2(32),  --��������ID
   ITEM_ID              VARCHAR2(32),  --����ID
   DELIVERY_TYPE        VARCHAR2(16) ,  --����ģʽ(ͳ��:UNIFIED/ֱ��:DIRECT/Խ��:CROSS/�Բ�:SELF)
   CONSTRAINT DELIVERY_TYPE_PK PRIMARY KEY (REGION_ID,ITEM_ID)
);

--ԭ������������Ϣ��
drop table D_T2_DELIVERY_UNIT cascade constraints;
create table D_T2_DELIVERY_UNIT
(
   ITEM_ID              VARCHAR2(32),  --����ID
   DELIVERY_UNIT        VARCHAR2(16),  --���͵�λ
   DELIVERY_FACTOR  	   NUMBER(19,5),  --ת������(��浥λ=���͵�λ*ת������)
   RECIPE_UNIT        	VARCHAR2(16),  --�䷽��λ
   RECIPE_FACTOR  		NUMBER(19,5),  --ת������(�䷽��λ=��浥λ*ת������)
   UNIT_VOLUME 			NUMBER(10,4),  --(���͵�λ)��λ���(�������ף�����)
   UNIT_WEIGHT 			NUMBER(10,4),  --(���͵�λ)��λ����(��G)
   MIN_ORDER_COUNT     	NUMBER(14,2),  --��С������(��浥λ)
   OUT_RECEIVE_RATE     NUMBER(4,2),    --������(ʵ�ղ��쳬���˰ٷֱ���,�������)
    CONSTRAINT CODE_UNIQUE UNIQUE (ITEM_ID),
    CONSTRAINT UN_ZERO CHECK (DELIVERY_FACTOR!=0  AND RECIPE_FACTOR!=0)
);

--ͼƬ��
drop table D_T2_PICS cascade constraints;
create table D_T2_PICS
(
   PIC_ID               VARCHAR2(32),  --���
   PIC_BLOB             BLOB           --ͼƬ
);

--���Ʒ����/�Ƴ̱�
drop table D_T2_MAKING_PROCESS cascade constraints;
create table D_T2_MAKING_PROCESS
(
   ITEM_ID              VARCHAR2(32),  --���Ʒ���
   STEP                 INT,           --����˳����
   STEP_OPERATION       VARCHAR2(200), --�ò���������
   STEP_NOTE            VARCHAR2(200)  --�ò��豸עitem
);


--��Ʒ��λ�۲��ͷ
drop table D_T2_OBSERVATION_HEADER cascade constraints;
create table D_T2_OBSERVATION_HEADER
(
   ITEM_ID              VARCHAR2(32),  --��Ʒ���
   PART_ID			    VARCHAR2(32),  --���ֱ���
   PART_NAME            VARCHAR2(300), --��������
   PART_REMARK          VARCHAR2(4000),--����˵��
   IS_TEMPLATE          VARCHAR2(1)    --�Ƿ�Ϊģ��
);

--��Ʒ��λ�۲���ϸ
drop table D_T2_OBSERVATION_DETAIL cascade constraints;
create table D_T2_OBSERVATION_DETAIL
(
   ITEM_ID              VARCHAR2(32),  -- ��Ʒ���
   PART_ID			    VARCHAR2(32),  -- ���ֱ���
   PROCESS_ID		    VARCHAR2(32),  -- �ӹ�����
   PROCESSED_NAME		VARCHAR2(64),  -- �ӹ�����
   STEP                 INT ,          -- ��������
   STEP_PIC_BLOB        BLOB, 		   -- ����ͼƬ
   STEP_REMARK			VARCHAR2(500)  -- ����˵��
);

--�볧�복���Ӧ��ϵ��
drop table D_T2_FACTORY_WORKSHOP cascade constraints;
CREATE TABLE D_T2_FACTORY_WORKSHOP
(
    FACTORY_ID VARCHAR2(32) NOT NULL,-- �볧����
    WORK_ORDER_ID VARCHAR2(32) NOT NULL,--�����������
    WORKSHOP VARCHAR2(64),-- ������������
    PRIORITY NUMBER(1), -- ���ȼ���������ȼ���ߣ���Ĭ�ϳ���
    CONSTRAINT D_T2_STORAGE_WORKSHOP PRIMARY KEY (FACTORY_ID,WORK_ORDER_ID) -- ��������
);

--�����복���Ӧ��ϵ��
drop table D_T2_ITEM_WORKSHOP cascade constraints;
CREATE TABLE D_T2_ITEM_WORKSHOP
(
    FACTORY_ID VARCHAR2(32) NOT NULL,-- �볧����
    WORK_ORDER_ID VARCHAR2(32) NOT NULL,--�����������
    ITEM_ID VARCHAR2(32) NOT NULL, --���ϱ���
    CONSTRAINT D_T2_ITEM_WORKSHOP PRIMARY KEY (FACTORY_ID ,WORK_ORDER_ID,ITEM_ID)
);