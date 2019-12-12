--����Ԫ����
drop table D_T0_FORM_META cascade constraints;
create table D_T0_FORM_META
(
   FORM_NAME            VARCHAR2(32),   --����ģ�������
   FORM_TYPE            VARCHAR2(32),   --��������
   FORM_NOTE            VARCHAR2(200)   --�����·���ע����Ŀ
);

--����״̬��
drop table D_T0_FORM_STATUS cascade constraints;
create table D_T0_FORM_STATUS
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   STATUS               VARCHAR2(32),   --���ݵ�ǰ��״̬
   STATUS_TIME          DATE,           --���ݵ�ǰ״̬�ı��ʱ��
   OPERATOR             VARCHAR2(32),   --���ݵ�ǰ״̬��������Ա
   STATUS_ORDER         INT,            --״̬˳�򣨴�ǰ���������
   IS_CURRENT           INT             --�Ƿ�����״̬��1��0��
--������ᱣ���������е�״̬
);

--��������״̬��
drop table D_T0_FORM_LOCK cascade constraints;
create table D_T0_FORM_LOCK
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   LOCK_STATUS          INT,            --����״̬
   LOCK_TIME            DATE            --����ʱ��
);

--��ӡ������
drop table D_T0_PRINT_TIMES cascade constraints;
create table D_T0_PRINT_TIMES
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   TIMES			    INT ,   --��ӡ����
   CONSTRAINT FORM_ID_PK PRIMARY KEY (FORM_ID)
);

--����������Ӫҵʱ���
drop table D_T0_BUSINESS_DATE cascade constraints;
create table D_T0_BUSINESS_DATE
(
   RESTAURANT_ID        VARCHAR2(32) ,   --�������
   BUSINESS_DATE        DATE ,           --����Ӫҵʱ��
   CONSTRAINT BUSINESS_DATE_PK PRIMARY KEY (RESTAURANT_ID)
);

--������
--(CGRK:�ɹ����)
--(CGTH:�ɹ��˻�)
--(CPBS:��Ʒ����)
--(CPCK:��Ʒ����)
--(CPRK:��Ʒ���)
--(DB:����)
--(FGLL:�ǹ�������)
--(JSRK:�������)
--(LLKU:���ۿۿ�)
--(PD:�̵�)
--(PSCK:���ͳ���)
--(PSFSH:���ͷ����)
--(PSRK:�������)
--(PSTH:�����˻�)
--(SCCL:��������)
--(SCLL:��������)
--(SCTL:��������)
--(YKCK:Խ�����)
--(YLBS:ԭ�ϱ���)
drop table D_T0_STORAGE_IN_OUT cascade constraints;
create table D_T0_STORAGE_IN_OUT
(
   BRANCH_ID            VARCHAR2(32),  --���ű��
   STORAGE_ID           VARCHAR2(32),  --�ֿ���
   BUSINESS_DATE        DATE,          --Ӫҵ����
   OPERATION_TIME       DATE,          --����ʱ��
   ITEM_ID              VARCHAR2(32),  --ԭ�ϱ���
   ITEM_UNIT_PRICE      NUMBER(12,4),  --ԭ�ϵ���     
   ORGI_COUNT           NUMBER(12,4),  --�ڳ�����
   ITEM_COUNT_IN        NUMBER(12,4),  --�������
   ITEM_COUNT_OUT       NUMBER(12,4),  --��������
   RESULT_COUNT         NUMBER(12,4),  --�������
   FORM_ID              VARCHAR2(32),  --���ݱ��
   REASON               VARCHAR2(64),  --ҵ������(���͡��˻��������)
   MY_TIMESTAMP 		VARCHAR2(32)   --��������
);

--����Ҫ�����ⲿ�����̶�������ģ��Ԫ��Ϣ
drop table D_T1_TEMPLATE_META cascade constraints;
create table D_T1_TEMPLATE_META
(
   TEMPLATE_ID          VARCHAR2(32),   --ģ����
   TEMPLATE_NAME        VARCHAR2(64),   --ģ������
   TEMPLATE_TYPE        VARCHAR2(32),   --ģ�����
   BRANCH_ID            VARCHAR2(32),   --����ģ��Ĳ���
   CATEGORY_ID          VARCHAR2(100),  --ģ����Ҫ��Ʒ���(����г�ǰ����)
   TEMPLATE_NOTE        VARCHAR2(200),  --ģ�屸ע��Ϣ
   ARRIVE_PERIOD        INT,            --��������
   DELIVERY_REGION      VARCHAR2(32),   --��������
   DELIVERY_TYPE        VARCHAR2(16),    --����ģʽ
    CONSTRAINT D_T1_TEMPLATE_META_PK PRIMARY KEY (TEMPLATE_ID)
);

--����Ҫ�����ⲿ�����̶�������ģ����ϸ��Ϣ
drop table D_T1_TEMPLATE_ITEM cascade constraints;
create table D_T1_TEMPLATE_ITEM
(
   TEMPLATE_ID          VARCHAR2(32),    --ģ����
   ITEM_ID              VARCHAR2(32),    --��Ʒ����
   ITEM_ORDER           INT   ,           --��Ʒ˳��
    CONSTRAINT D_T1_TEMPLATE_ITEM_PK PRIMARY KEY (TEMPLATE_ID, ITEM_ID)
);

--����Ҫ�����ⲿ�����̶��������ͷ
drop table D_T1_REQUEST_HEADER cascade constraints;
create table D_T1_REQUEST_HEADER
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   FORM_TYPE            VARCHAR2(32),   --�������ͣ�����Ҫ��/�ⲿ������
   BUYER_ID             VARCHAR2(32),   --��������ID
   BUYER_NAME           VARCHAR2(64),   --��������
   STORAGE_ID           VARCHAR2(32),   --�����ֿ��ʶ(���Զ�ֿ���Ч)
   STORAGE              VARCHAR2(64),   --�����ֿ�����(���Զ�ֿ���Ч)
   BUYER_ADDRESS        VARCHAR2(100),  --������ַ
   PROVIDER_ID          VARCHAR2(32),   --��������ID
   PROVIDER             VARCHAR2(64),   --��������
   RECEIVE_TIME         DATE,           --����ʱ��
   FORM_MAKER           VARCHAR2(64),   --�Ƶ���Ա
   FORM_TIME            DATE,           --�Ƶ�ʱ��
   FORM_TIME_ACTUAL     DATE,           --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR              VARCHAR2(64),   --�����Ա
   AUDIT_TIME           DATE,           --���ʱ��
   PURCHASE_STATUS      VARCHAR2(32),   --�Ƿ���вɹ�����
   SHIPPING_STATUS      VARCHAR2(32),   --�Ƿ�������ʹ���
   FORM_NOTE            VARCHAR2(200),  --��ע��Ϣ
   REF_DATE_START1      DATE,           --�ο�ʱ���1��ʼ����
   REF_DATE_END1        DATE,           --�ο�ʱ���1��ʼ����
   REF_DATE_START2      DATE,           --�ο�ʱ���2��ʼ����
   REF_DATE_END2        DATE,           --�ο�ʱ���2��ʼ����
   REF_DATE_START3      DATE,           --�ο�ʱ���3��ʼ����
   REF_DATE_END3        DATE,           --�ο�ʱ���3��ʼ����
   DELAY_PREDICT        NUMBER(10,4),   --�����ڽ��
   PURCHASE_PREDICT     NUMBER(10,4),   --�������ڽ��
   SAFETY_STOCK         NUMBER(10,4),   --��ȫ����
   SELL_PREDICT         NUMBER(10,4),   --Ԥ�����۶�
   ALL_PAY_AMT          NUMBER(14,4),   --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64),   --���������Ʒ
   ARRIVE_PERIOD        INT,            --��������
   DELIVERY_TYPE        VARCHAR2(16),    --����ģʽ
   TEMPLATE_ID          VARCHAR2(32),   --ģ����
   TEMPLATE_NAME        VARCHAR2(64),   --ģ������
   ISADDFORM            VARCHAR2(1),   --�Ƿ�ӵ�
   CONSTRAINT REQUEST_HEADER_PK PRIMARY KEY (FORM_ID)
);
 
--����Ҫ�����ⲿ�����̶����������ϸ
drop table D_T1_REQUEST_DETAIL cascade constraints;
create table D_T1_REQUEST_DETAIL
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   ITEM_ID              VARCHAR2(32),   --��Ʒ����
   INTL_BAR_CODE        VARCHAR2(32),   --��������
   ITEM_NAME            VARCHAR2(64),   --��Ʒ����
   ITEM_CATEGORY        VARCHAR2(32),   --���
   ITEM_DIMENSION       VARCHAR2(32),   --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),   --���
   AMT_TT_CNY1          NUMBER(10,4),   --��Ԫ����1
   AMT_TT_CNY2          NUMBER(10,4),   --��Ԫ����2
   AMT_TT_CNY3          NUMBER(10,4),   --��Ԫ����3
   REQUIRE_COUNT1       NUMBER(10,4),   --����1
   REQUIRE_COUNT2       NUMBER(10,4),   --����2
   REQUIRE_COUNT3       NUMBER(10,4),   --����3
   INVENTORY            NUMBER(10,4),   --�����
   SUGGEST_COUNT1       NUMBER(10,4),   --����1
   SUGGEST_COUNT2       NUMBER(10,4),   --����2
   SUGGEST_COUNT3       NUMBER(10,4),   --����3
   ORDER_COUNT          NUMBER(14,4),   --������
   ITEM_UNIT_PRICE      NUMBER(10,4),   --����
   PAY_AMT              NUMBER(14,4),    --���
   DELIVERY_TYPE        VARCHAR2(16) ,  --����ģʽ(ͳ��:UNIFIED/ֱ��:DIRECT/Խ��:CROSS/�Բ�:SELF)
   CONSTRAINT REQUEST_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--�������Ĳɹ�ֱ�䶩������С���ݹ�������
drop table D_T1_PURCHASING_MAPPING cascade constraints;
create table D_T1_PURCHASING_MAPPING
(
   BIG_FORM_ID          VARCHAR2(32),   --ֱ��ɹ����󵥱��
   SMALL_FORM_ID        VARCHAR2(32),    --��Ӧ��ֱ��ɹ���С�����
   CONSTRAINT PURCHASING_MAPPING_PK PRIMARY KEY (BIG_FORM_ID, SMALL_FORM_ID)
);

--�������Ĳɹ��������빤���ɹ�����ͷ
drop table D_T1_PURCHASING_HEADER cascade constraints;
create table D_T1_PURCHASING_HEADER
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   FORM_REF_ID          VARCHAR2(32),   --ԭʼ���ݱ��(�����������Ļ����빤����Ч����Ӧǰһ������)
   PROVIDER_ID          VARCHAR2(32),   --��Ӧ��ID
   PROVIDER             VARCHAR2(96),   --��Ӧ��
   REQUESTER_ID         VARCHAR2(32),   --��������ID
   REQUESTER            VARCHAR2(64),   --��������
   RECEIVER_ID          VARCHAR2(32),   --�ջ�����ID(ֻ���ֱ��С����Ч,����Ϊ��)
   RECEIVER             VARCHAR2(64),   --�ջ�����(ֻ���ֱ��С����Ч,����Ϊ��)
   RECEIVE_ADDRESS      VARCHAR2(100),  --�ջ���ַ(��ֱ�����Ч,ΪNULL)
   STORAGE_ID           VARCHAR2(32),   --�ջ��ֿ��ʶ(���Զ�ֿ���Ч)
   STORAGE              VARCHAR2(64),   --�ջ��ֿ�����(���Զ�ֿ���Ч)
   RECEIVE_TIME         DATE,           --��������
   FORM_MAKER_ID        VARCHAR2(32),   --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),   --�Ƶ���Ա
   FORM_TIME            DATE,           --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,           --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR_ID           VARCHAR2(32),   --�����ԱID
   AUDITOR              VARCHAR2(64),   --�����Ա
   AUDIT_TIME           DATE,           --�������
   FORM_NOTE            VARCHAR2(200),  --��ע˵��
   DELIVERY_TYPE        VARCHAR2(64),   --���ͷ�ʽ
   PLAN_STATUS      	   VARCHAR2(32),   --�Ƿ���������ƻ�����
   OUT_STATUS      		VARCHAR2(32),   --�Ƿ����
   ALL_PAY_AMT          NUMBER(10,4),   --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64),    --���������Ʒ
   CONSTRAINT PURCHASING_HEADER_PK PRIMARY KEY (FORM_ID)
);

--�������Ĳɹ��������빤���ɹ�����ϸ
drop table D_T1_PURCHASING_DETAIL cascade constraints;
create table D_T1_PURCHASING_DETAIL
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   ITEM_ID              VARCHAR2(32),   --��Ʒ����
   ITEM_NAME            VARCHAR2(64),   --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),   --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),   --���
   ITEM_CATEGORY        VARCHAR2(32),   --���
   ITEM_COUNT           NUMBER(14,4),   --����
   ITEM_UNIT_PRICE      NUMBER(10,4),   --��׼��
   PAY_AMT              NUMBER(14,4),   --��׼���
   RECEIVE_PRICE        NUMBER(10,4),   --������
   RECEIVE_AMT          NUMBER(14,4),   --�������
   RECEIVER_ID          VARCHAR2(32),   --�ջ�����ID(ֻ���Խ����Ч,����Ϊ��)
   RECEIVER             VARCHAR2(64),   --�ջ�����(ֻ���Խ����Ч,����Ϊ��)
   EXPIRED_TIME         DATE,            --ԭ����Ч��
   CONSTRAINT PURCHASING_DETAIL_UNIQUE UNIQUE (FORM_ID, ITEM_ID, RECEIVER_ID)
);

--�������ġ����빤��Ҫ�����ܵ���ͷ
drop table D_T1_COLLECT_HEADER cascade constraints;
create table D_T1_COLLECT_HEADER
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   BRANCH_ID            VARCHAR2(32),   --���ܲ���ID
   BRANCH               VARCHAR2(64),   --���ܲ���
   FORM_MAKER_ID        VARCHAR2(32),   --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),   --�Ƶ���Ա
   FORM_TIME            DATE,           --�Ƶ�����
   AUDITOR_ID           VARCHAR2(32),   --�����ԱID
   AUDITOR              VARCHAR2(64),   --�����Ա
   AUDIT_TIME           DATE,           --�������
   FORM_NOTE            VARCHAR2(200),  --��ע˵��
   ALL_PAY_AMT          NUMBER(10,4),   --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64)    --���������Ʒ
);

--�������ġ����빤��Ҫ�����ܵ�����ϸ
drop table D_T1_COLLECT_DETAIL cascade constraints;
create table D_T1_COLLECT_DETAIL
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   ITEM_ID              VARCHAR2(32),   --��Ʒ����
   ITEM_NAME            VARCHAR2(32),   --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),   --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),   --���
   ITEM_CATEGORY        VARCHAR2(32),   --���
   ITEM_COUNT           NUMBER(10,4),   --����
   ITEM_UNIT_PRICE      NUMBER(10,4),   --����
   PAY_AMT              NUMBER(10,4),   --���
   FORM_REF_ID          VARCHAR2(512),  --�������ܵģ�ԭʼ���ݱ��
   REQUESTER            VARCHAR2(512),  --��������
   PROVIDER_ID          VARCHAR2(32),   --��Ӧ��ID
   PROVIDER             VARCHAR2(64)    --��Ӧ��
);

--�������ġ����빤�����ܵ����뱻���ܵ�ԭʼ���ݹ�����Ӧ��
drop table D_T1_COLLECT_REF_FORM cascade constraints;
create table D_T1_COLLECT_REF_FORM
(
   COLLECT_FORM_ID              VARCHAR2(32),   --���ݱ��
   REF_FORM_ID                  VARCHAR2(32),   --�����ܵ�ԭʼ���ݱ��
   ITEM_ID                      VARCHAR2(32),    --�����ܵ����ϱ��
    CONSTRAINT COLL_UNIQUE UNIQUE (COLLECT_FORM_ID, REF_FORM_ID, ITEM_ID)
);

--�������������ġ����빤����ⵥ��ͷ
drop table D_T1_INPUT_HEADER cascade constraints;
create table D_T1_INPUT_HEADER
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   FORM_REF_ID          VARCHAR2(32),   --ǰ�õ��ݣ���Ӧ�Ĳɹ�������������
   FORM_TYPE            VARCHAR2(16),   --��ⵥ����(PURCHASING:�ɹ����,PRODUCE:�������,MANUAL:�ֶ����)
   INPUT_DEPARTMENT_ID  VARCHAR2(32),   --��ⲿ��ID
   INPUT_DEPARTMENT     VARCHAR2(64),   --��ⲿ��
   INPUTER_ID           VARCHAR2(32),   --�����ԱID
   INPUTER              VARCHAR2(64),   --�����Ա
   STORAGE_ID           VARCHAR2(32),   --���ֿ��ʶ(���Զ�ֿ���Ч)
   STORAGE              VARCHAR2(64),   --���ֿ�����(���Զ�ֿ���Ч)
   INPUT_TIME           DATE,           --���ʱ��
   PROVIDER_ID          VARCHAR2(32),   --��Ӧ��ID
   PROVIDER             VARCHAR2(96),   --��Ӧ��
   FORM_NOTE            VARCHAR2(200),  --��ע˵��
   AUDITOR_ID           VARCHAR2(32),   --�����ԱID
   AUDITOR              VARCHAR2(64),   --�����Ա
   AUDIT_TIME           DATE,           --���ʱ��
   RETURN_STATUS        VARCHAR2(32),   --�˻�״̬(δ�˻�/���˻�)
   ALL_PAY_AMT          NUMBER(10,4),   --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64),    --���������Ʒ
   CONSTRAINT INPUT_HEADER_PK PRIMARY KEY (FORM_ID)
);

--�������������ġ����빤����ⵥ��ϸ
drop table D_T1_INPUT_DETAIL cascade constraints;
create table D_T1_INPUT_DETAIL
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   ITEM_ID              VARCHAR2(32),   --��Ʒ����
   ITEM_NAME            VARCHAR2(64),   --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),   --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),   --���
   ITEM_CATEGORY        VARCHAR2(32),   --���
   ORDER_COUNT          NUMBER(14,4),   --������
   RECEIVED_COUNT       NUMBER(14,4),   --���������ǰ������ⵥ���ۼ�����
   RECEIVE_COUNT        NUMBER(14,4),   --ʵ����
   DIFFERENT_COUNT      NUMBER(14,4),   --ʵ�ղ���
   ITEM_UNIT_PRICE      NUMBER(10,4),   --��׼��
   PAY_AMT              NUMBER(14,4),   --��׼���
   RECEIVE_PRICE        NUMBER(10,4),   --������
   RECEIVE_AMT          NUMBER(14,4),   --�������
   BATCH                VARCHAR2(80),   --����(�ֿ�id-��Ʒid-ʱ��)
   EXPIRED_TIME         DATE,            --��Ч��
   CONSTRAINT INPUT_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--�������͵����������ĳ����������빤����������ͷ
drop table D_T1_SHIPPING_HEADER cascade constraints;
create table D_T1_SHIPPING_HEADER
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   FORM_REF_ID          VARCHAR2(32),   --ǰ�õ��ݣ���Ӧ�Ĳɹ���
   FORM_TYPE            VARCHAR2(32),   --��������(ͳ�����͵�:INNER_UNIFIED,Խ�����͵�:INNER_CROSS,������:OUTER)
   PROVIDER_ID          VARCHAR2(32),   --���Ͳ���ID
   PROVIDER             VARCHAR2(64),   --���Ͳ���
   OUT_STORAGE_ID       VARCHAR2(32),   --����ֿ��ʶ(���Զ�ֿ���Ч)
   OUT_STORAGE          VARCHAR2(64),   --����ֿ�����(���Զ�ֿ���Ч)
   RECEIVE_TIME         DATE,           --��������
   REQUESTER_ID         VARCHAR2(32),   --��������ID
   REQUESTER            VARCHAR2(64),   --��������
   REQUEST_ADDRESS      VARCHAR2(100),  --������ַ
   IN_STORAGE_ID        VARCHAR2(32),   --���ֿ��ʶ(���Զ�ֿ���Ч)
   IN_STORAGE           VARCHAR2(64),   --���ֿ�����(���Զ�ֿ���Ч)
   FORM_MAKER_ID        VARCHAR2(32),   --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),   --�Ƶ���Ա
   FORM_TIME            DATE,           --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,           --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR_ID           VARCHAR2(32),   --�����ԱID
   AUDITOR              VARCHAR2(64),   --�����Ա
   AUDIT_TIME           DATE,           --�������
   INPUTER_ID           VARCHAR2(32),   --�����ԱID(���Բ������͵���Ч)
   INPUTER              VARCHAR2(64),   --�����Ա(���Բ������͵���Ч)
   INPUT_TIME           DATE,           --���ʱ��(���Բ������͵���Ч)
   PICK_STATUS          VARCHAR2(32),   --���״̬(δ���/�Ѽ��)
   INPUT_STATUS         VARCHAR2(32),   --���״̬(δ���/�����)
   DIFFERENT_STATUS     VARCHAR2(32),   --���Ͳ��촦��״̬(δ����/�Ѵ���)
   RETURN_STATUS        VARCHAR2(32),   --�˻�״̬(�˻���/���˻�)
   ANTI_STATUS          VARCHAR2(32),   --�����״̬(�������/�ѷ����)
   ON_STATUS            VARCHAR2(32),   --��;״̬(��/��;)
   FORM_NOTE            VARCHAR2(200),  --��ע˵��
   ALL_PAY_AMT          NUMBER(10,4),   --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64),    --���������Ʒ
   CONSTRAINT SHIPPING_HEADER_PK PRIMARY KEY (FORM_ID)
);

--�������͵����������ĳ����������빤����������ϸ
drop table D_T1_SHIPPING_DETAIL cascade constraints;
create table D_T1_SHIPPING_DETAIL
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   ITEM_ID              VARCHAR2(32),   --��Ʒ����
   ITEM_NAME            VARCHAR2(64),   --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),   --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),   --���
   ITEM_CATEGORY        VARCHAR2(32),   --���
   PACKAGING_FACTOR     NUMBER(8,4),    --��װ����
   PACKAGING_UNIT       VARCHAR2(8),    --��װ��λ
   PACKAGING_COUNT      NUMBER(10,4),   --��װ����
   REQUEST_COUNT        NUMBER(10,4),   --������
   SHIPPING_COUNT       NUMBER(10,4),   --������
   DELIVERY_COUNT       NUMBER(10,4),   --ʵ����
   RECEIVE_COUNT        NUMBER(10,4),   --ʵ����
   DIFFERENT_COUNT      NUMBER(10,4),   --������
   DIFFERENT_REASON     VARCHAR2(100),  --����ԭ��
   ITEM_UNIT_PRICE      NUMBER(10,4),   --����
   PAY_AMT              NUMBER(10,4),   --���
   EXPIRED_TIME         DATE,            --��Ч��
   CONSTRAINT SHIPPING_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--�������ͷ���˵���ͷ
drop table D_T1_SHIPPING_ANTIAUDIT_HEADER cascade constraints;
create table D_T1_SHIPPING_ANTIAUDIT_HEADER
(
   FORM_REF_ID          VARCHAR2(32),   --����������͵����
   ANTIAUDITOR_ID       VARCHAR2(32),   --�������ԱID
   ANTIAUDITOR          VARCHAR2(64),   --�������Ա
   ANTIAUDIT_BRANCH_ID  VARCHAR2(32),   --����˲���ID
   ANTIAUDIT_BRANCH     VARCHAR2(64),   --����˲���
   ANTIAUDIT_TIME       DATE,           --�����ʱ��
   FORM_NOTE            VARCHAR2(200)   --����˱�ע
);

--�������ͷ���˵�����ϸ
drop table D_T1_SHIPPING_ANTIAUDIT_DETAIL cascade constraints;
create table D_T1_SHIPPING_ANTIAUDIT_DETAIL
(
   FORM_REF_ID             VARCHAR2(32),   --����������͵����
   ITEM_ID                 VARCHAR2(32),   --��Ʒ����
   ANTIAUDIT_RECEIVE_COUNT NUMBER(10,4),   --�����ʵ����
   ANTIAUDIT_PAY_AMT       NUMBER(10,4)    --����˽��
);

--�����˻�����ͷ
drop table D_T1_RETURN_GOODS_HEADER cascade constraints;
create table D_T1_RETURN_GOODS_HEADER
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   FORM_REF_ID          VARCHAR2(32),   --����������͵���ɹ������
   RETURNER_ID          VARCHAR2(32),   --�˻���ԱID
   RETURNER             VARCHAR2(64),   --�˻���Ա
   RETURN_TIME          DATE,           --�˻�ʱ��
   FORM_NOTE            VARCHAR2(200),  --�˻���ע˵��
   ALL_PAY_AMT          NUMBER(10,4),   --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64),    --���������Ʒ
   CONSTRAINT RETURN_GOODS_HEADER_PK PRIMARY KEY (FORM_ID)
);

--�����˻�������ϸ
drop table D_T1_RETURN_GOODS_DETAIL cascade constraints;
create table D_T1_RETURN_GOODS_DETAIL
(
   FORM_ID              VARCHAR2(32),   --�˻����ݱ��
   FORM_REF_ID          VARCHAR2(32),   --����������͵���ɹ������
   ITEM_ID              VARCHAR2(32),   --��Ʒ����
   RETURN_COUNT         NUMBER(10,4),   --�˻����˻���
   RETURN_PAY_AMT       NUMBER(10,4),   --�˻������
   RETURN_REASON        VARCHAR2(200),   --�˻�ԭ��
   CONSTRAINT RETURN_GOODS_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--���������빤�����������ı��𵥱�ͷ
drop table D_T1_LOSS_HEADER cascade constraints;
create table D_T1_LOSS_HEADER
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   LOSS_TYPE            VARCHAR2(8),    --�������ͣ�RAWLOSSԭ�ϱ���/DISHLOSS��Ʒ����
   LOSS_BRANCH_ID       VARCHAR2(32),   --������ID����ֿ��������ǲֿ�ID��
   LOSS_BRANCH          VARCHAR2(64),   --������
   STORAGE_ID           VARCHAR2(32),   --����ֿ��ʶ(���Զ�ֿ���Ч)
   STORAGE              VARCHAR2(64),   --����ֿ�����(���Զ�ֿ���Ч)
   LOSS_MAN_ID          VARCHAR2(32),   --������ԱID
   LOSS_MAN             VARCHAR2(64),   --������Ա
   LOSS_TIME            DATE,           --��������
   AUDITOR_ID           VARCHAR2(32),   --�����ԱID
   AUDITOR              VARCHAR2(64),   --�����Ա
   AUDIT_TIME           DATE,           --�������
   PROCESSOR_ID         VARCHAR2(32),   --������ԱID
   PROCESSOR            VARCHAR2(64),   --������Ա
   PROCESS_TIME         DATE,           --�������
   FORM_NOTE            VARCHAR2(1024),  --��ע˵��
   ALL_PAY_AMT          NUMBER(10,4),   --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64),    --���������Ʒ
   CONSTRAINT LOSS_HEADER_PK PRIMARY KEY (FORM_ID)
);

--���������빤�����������ı�����ϸ
drop table D_T1_LOSS_DETAIL cascade constraints;
create table D_T1_LOSS_DETAIL
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   ITEM_ID              VARCHAR2(32),   --��Ʒ����
   ITEM_NAME            VARCHAR2(64),   --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),   --��λ(��Ʒ����)
   ITEM_SPECIFICATION   VARCHAR2(32),   --���
   ITEM_ID2             VARCHAR2(32),   --��Ʒ��Ʒ����
   ITEM_NAME2           VARCHAR2(64),   --��Ʒ��Ʒ����
   ITEM_DIMENSION2      VARCHAR2(32),   --��Ʒ��λ(��Ʒ����)
   ITEM_CATEGORY        VARCHAR2(32),   --���
   ITEM_COUNT           NUMBER(10,4),   --��������
   ITEM_COUNT2          NUMBER(10,4),   --��Ʒ��������
   UNIT_PRICE           NUMBER(10,4),   --����
   PAY_AMT              NUMBER(10,4),   --���
   STORAGE_COUNT        NUMBER(12,4),   --�������
   EXPIRED_TIME         DATE,           --ԭ����Ч��
   REASON               VARCHAR2(200),   --����ԭ��
   CONSTRAINT ITEM_UNIQUE UNIQUE (FORM_ID, ITEM_ID, ITEM_ID2)
);

--������������ͷ
drop table D_T1_TRANSFER_HEADER cascade constraints;
create table D_T1_TRANSFER_HEADER
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   IN_BRANCH_ID         VARCHAR2(32),   --���벿��ID
   IN_BRANCH            VARCHAR2(64),   --���벿��
   IN_STORAGE_ID        VARCHAR2(32),   --���ֿ��ʶ(���Զ�ֿ���Ч)
   IN_STORAGE           VARCHAR2(64),   --���ֿ�����(���Զ�ֿ���Ч)
   OUT_BRANCH_ID        VARCHAR2(32),   --��������ID
   OUT_BRANCH           VARCHAR2(64),   --��������
   OUT_STORAGE_ID       VARCHAR2(32),   --����ֿ��ʶ(���Զ�ֿ���Ч)
   OUT_STORAGE          VARCHAR2(64),   --����ֿ�����(���Զ�ֿ���Ч)
   FROM_MAKER_ID        VARCHAR2(32),   --�Ƶ���ԱID
   FROM_MAKER           VARCHAR2(64),   --�Ƶ���Ա
   FORM_TIME            DATE,           --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,           --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR_ID           VARCHAR2(32),   --�����ԱID
   AUDITOR              VARCHAR2(64),   --�����Ա
   AUDIT_TIME           DATE,           --�������
   CONFIRMER_ID         VARCHAR2(32),   --ȷ����ԱID
   CONFIRMER            VARCHAR2(64),   --ȷ����Ա
   CONFIRM_TIME         DATE,           --ȷ������
   OUT_MAN_ID           VARCHAR2(32),   --������ԱID
   OUT_MAN              VARCHAR2(64),   --������Ա
   OUT_TIME             DATE,           --��������
   FORM_NOTE            VARCHAR2(200),  --��ע˵��
   ALL_PAY_AMT          NUMBER(10,4),   --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64),    --���������Ʒ
   CONSTRAINT TRANSFER_HEADER_PK PRIMARY KEY (FORM_ID)
);

--������������ϸ
drop table D_T1_TRANSFER_DETAIL cascade constraints;
create table D_T1_TRANSFER_DETAIL
(
   FORM_ID              VARCHAR2(32),   --���ݱ��
   ITEM_ID              VARCHAR2(32),   --��Ʒ����
   ITEM_NAME            VARCHAR2(64),   --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),   --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),   --���
   ITEM_CATEGORY        VARCHAR2(32),   --���
   APPLY_COUNT          NUMBER(10,4),   --�����������
   ACTUAL_COUNT         NUMBER(10,4),   --ʵ�ʵ�������
   DIFFERENT_COUNT      NUMBER(10,4),   --��������
   UNIT_PRICE           NUMBER(10,4),   --����
   PAY_AMT              NUMBER(10,4),   --���
   EXPIRED_TIME         DATE  ,          --��Ʒ��Ч��
   CONSTRAINT TRANSFER_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--�̵������¼��
drop table D_T1_CHECK_LOCK cascade constraints;
create table D_T1_CHECK_LOCK
(
   CHECK_BATCH_ID       VARCHAR2(32),     --�̵����κ�
   CHECK_BATCH_STATUS   VARCHAR2(32),     --�̵�����״̬��δ���/����ɣ��Ƿ����̵��嵥�����̵㵥��
   LOCK_BRANCH_ID       VARCHAR2(32),     --���ⲿ��ID
   LOCK_BRANCH          VARCHAR2(64),     --���ⲿ��
   LOCK_STORAGE_ID      VARCHAR2(32),     --����ֿ��ʶ(���Զ�ֿ���Ч)
   LOCK_STORAGE         VARCHAR2(64),     --����ֿ�����(���Զ�ֿ���Ч)
   LOCK_MAN_ID          VARCHAR2(32),     --������ԱID
   LOCK_MAN             VARCHAR2(64),     --������Ա
   LOCK_DATE            DATE,             --�������ڣ�Ӫҵ���ڣ�
   LOCK_TIME            DATE,             --����ʱ�䣨��������ʵ��ʱ�䣩
   LOCK_NOTE            VARCHAR2(200),    --��ע˵��
   ITEM_REPEATABLE      VARCHAR2(16),     --��ǰ���εĶ���̵��嵥֮���Ƿ����������ظ�ԭ��
   ITEM_CATEGORY        VARCHAR2(600),     --�̵�ԭ����𣨶����𶺺ŷָ���
   CONSTRAINT CHECK_LOCK_PK PRIMARY KEY (CHECK_BATCH_ID)     
);

--�̵㵥/�嵥��ͷ
drop table D_T1_CHECK_HEADER cascade constraints;
create table D_T1_CHECK_HEADER
(
   FORM_ID              VARCHAR2(32),     --���ݱ��
   FORM_TYPE            VARCHAR2(8)  NOT NULL,      --�������ͣ��̵㵥/�̵��嵥
   CHECK_BATCH_ID       VARCHAR2(32),     --��Ӧ�̵�����
   CHECK_BRANCH_ID      VARCHAR2(32),     --�̵㲿��ID
   CHECK_BRANCH         VARCHAR2(64),     --�̵㲿��
   CHECK_STORAGE_ID     VARCHAR2(32),     --�̵�ֿ��ʶ(���Զ�ֿ���Ч)
   CHECK_STORAGE        VARCHAR2(64),     --�̵�ֿ�����(���Զ�ֿ���Ч)
   FORM_MAKER_ID        VARCHAR2(32),     --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),     --�Ƶ���Ա
   FORM_TIME            DATE,             --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,          	  --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR_ID           VARCHAR2(32),     --�����ԱID
   AUDITOR              VARCHAR2(64),     --�����Ա
   AUDIT_TIME           DATE,             --�������
   FORM_NOTE            VARCHAR2(200),    --��ע˵��
   PRINT_COUNT          INT,              --��ӡ����
   ALL_PAY_AMT          NUMBER(14,4),     --�ܽ��
   MAX_PAY_ITEM         VARCHAR2(64),      --���������Ʒ
   CONSTRAINT CHECK_HEADER_PK PRIMARY KEY (FORM_ID)
);

--�̵㵥/�嵥��ϸ
drop table D_T1_CHECK_DETAIL cascade constraints;
create table D_T1_CHECK_DETAIL
(
   FORM_ID              VARCHAR2(32),     --���ݱ��
   ITEM_ID              VARCHAR2(32),     --��Ʒ����
   ITEM_NAME            VARCHAR2(64),     --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),     --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),     --���
   ITEM_CATEGORY        VARCHAR2(32),     --���
   SHELF_ID             VARCHAR2(32),     --��λ
   CHECK_COUNT          NUMBER(14, 4),    --�̵�����
   THEORY_COUNT         NUMBER(14, 4),    --��������
   EXPIRED_TIME         DATE,             --��Ʒ��Ч��
   ITEM_ORDER           INT,              --��Ʒ˳��
   ITEM_STATUS          VARCHAR2(32),      --��Ʒ�̵�״̬��©��:LEAK,����:NORMAL��
   CONSTRAINT CHECK_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--���빤�������ƻ�/�ų̵���ͷ
drop table D_T1_ARRENGMENT_HEADER cascade constraints;
create table D_T1_ARRENGMENT_HEADER
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   FORM_REF_ID          VARCHAR2(32),  --�������ԭʼ���ݱ��
   FORM_BRANCH_ID       VARCHAR2(32),  --�Ƶ�����ID
   FORM_MAKER_ID        VARCHAR2(32),  --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),  --�Ƶ���Ա
   FORM_TIME            DATE,          --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,          --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR_ID           VARCHAR2(32),  --�����ԱID
   AUDITOR              VARCHAR2(64),  --�����Ա
   AUDIT_TIME           DATE,          --�������
   FORM_NOTE            VARCHAR2(200), --��ע˵��
   PURCHASE_STATUS      VARCHAR2(32),   --�Ƿ��·��ɹ���
   CONSTRAINT ARRENGMENT_HEADER_PK PRIMARY KEY (FORM_ID)
);

--���빤�������ƻ�/�ų̵���ϸ
drop table D_T1_ARRENGMENT_DETAIL cascade constraints;
create table D_T1_ARRENGMENT_DETAIL
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   ITEM_NAME            VARCHAR2(64),  --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),  --��浥λ
   ITEM_DIMENSION2      VARCHAR2(32),  --��װ��λ
   ITEM_SPECIFICATION   VARCHAR2(32),  --���
   PRODUCE_COUNT        NUMBER(10,4),  --�ƻ�����������浥λ��
   PRODUCE_COUNT2       NUMBER(10,4),  --�ƻ�����������װ��λ��
   WORK_ORDER_ID        VARCHAR2(32),  --�������
   WORKSHOP             VARCHAR2(64),  --��������
   PRODUCE_TIME         DATE,          --��������
   PRODUCTION_CYCLE     INT,           --��������
   COMPLETE_TIME        DATE,          --�깤����(�깤����=��������+��������)
   NOTE                 VARCHAR2(200),  --��ע
   CONSTRAINT ARRENGMENT_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--���빤������������ͷ
drop table D_T1_WORK_ORDER_HEADER cascade constraints;
create table D_T1_WORK_ORDER_HEADER
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   ITEM_ID              VARCHAR2(32),  --���Ʒ����
   ITEM_NAME            VARCHAR2(64),  --���Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),  --��浥λ
   ITEM_DIMENSION2      VARCHAR2(32),  --��װ��λ
   ITEM_SPECIFICATION   VARCHAR2(32),  --���
   ITEM_COUNT           NUMBER(10,4),  --�ƻ�������������浥λ��
   ITEM_COUNT2          NUMBER(10,4),  --�ƻ�������������װ��λ��
   ACTUAL_COUNT         NUMBER(10,4),  --ʵ����������(�������)
   WORKSHOP             VARCHAR2(64),  --��������
   FORM_BRANCH_ID       VARCHAR2(32),  --�Ƶ�����ID
   FORM_MAKER_ID        VARCHAR2(32),  --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),  --�Ƶ���Ա
   FORM_TIME            DATE,          --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,          --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR_ID           VARCHAR2(32),  --�����ԱID
   AUDITOR              VARCHAR2(64),  --�����Ա
   AUDIT_TIME           DATE,          --�������
   RECEIVED_STATUS		VARCHAR2(32),  --����״̬(��ʼ״̬ default,����ԭ�Ͽ����� receiving,����ԭ�ϲ������� received)
   INPUTED_COUNT        NUMBER(14,4),   --���������
   --COMPLETE_TIME        DATE,          --ʵ���깤����(�᰸)
   BATCH_FLAG           VARCHAR2(1),   --�������ɱ�־
   FORM_NOTE            VARCHAR2(200),  --��ע˵��
    CONSTRAINT WORK_ORDER_HEADER_PK PRIMARY KEY (FORM_ID)
);

--���빤�����������Ƴ���ϸ
drop table D_T1_WORK_ORDER_DETAIL cascade constraints;
create table D_T1_WORK_ORDER_DETAIL
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   PRODUCTION_NAME      VARCHAR2(32),  --�Ƴ�
   PRODUCTION_STEP      INT,           --�Ƴ�˳���
   PRODUCTION_TIME      DATE,          --����
   PRODUCTION_COUNT     NUMBER(10,4),  --����
   PRODUCTION_MAN       VARCHAR2(64),  --������Ա
   PRODUCTION_NOTE      VARCHAR2(200), --��ע
   IS_COMPLETED         INT            --�ò����Ƿ����,0��ʾδ���,1��ʾ��� 
);

--���빤����������ԭ����ϸ
drop table D_T1_WORKORDER_ITEM cascade constraints;
create table D_T1_WORKORDER_ITEM
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   ITEM_ID              VARCHAR2(32),  --ԭ�ϱ���
   ITEM_NAME            VARCHAR2(64),  --ԭ������
   ITEM_DIMENSION       VARCHAR2(32),  --��浥λ
   ITEM_SPECIFICATION   VARCHAR2(32),  --���
   ITEM_COUNT           NUMBER(14,4),  --�ƻ���������
   RECEIVED_COUNT       NUMBER(14,4),  --����������
   RETURNED_COUNT       NUMBER(14,4),   --����������
   CONSTRAINT WORKORDER_ITEM_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--���빤���������ϵ������빤�����쵥�����빤���ǹ������ϵ������빤���������ϵ���ͷ
--�������ϵ�PRODUCE_REQUEST_MATERIAL/produce
--���쵥EXTRA_REQUEST_MATERIAL/extra
--�ǹ������ϵ�NOTWORK_REQUEST_MATERIAL/manual
--�������ϵ�PRODUCE_RETURN_MATERIAL/return
drop table D_T1_REQUISITION_HEADER cascade constraints;
create table D_T1_REQUISITION_HEADER
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   FORM_TYPE            VARCHAR2(32),  --��������(�������ϵ�/���쵥/�ǹ������ϵ�/�������ϵ�)
   FORM_REF_ID          VARCHAR2(32),  --ԭʼ���ݱ��
   WORKSHOP             VARCHAR2(64),  --������/���ϣ�����
   STORAGE_ID           VARCHAR2(32),  --������/�ջ����ֿ�ID
   STORAGE              VARCHAR2(64),  --������/�ջ����ֿ�
   FORM_BRANCH_ID       VARCHAR2(32),  --�Ƶ�����ID
   FORM_MAKER_ID        VARCHAR2(32),  --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),  --�Ƶ���Ա
   FORM_TIME            DATE,          --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,          --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR_ID           VARCHAR2(32),  --�����ԱID
   AUDITOR              VARCHAR2(64),  --�����Ա
   AUDIT_TIME           DATE,          --�������
   FORM_NOTE            VARCHAR2(200),  --��ע˵��
   CONSTRAINT REQUISITION_HEADER_PK PRIMARY KEY (FORM_ID)
);

--���빤���������ϵ������빤�����쵥�����빤���ǹ������ϵ������빤���������ϵ�����ϸ
drop table D_T1_REQUISITION_DETAIL cascade constraints;
create table D_T1_REQUISITION_DETAIL
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   ITEM_NAME            VARCHAR2(64),  --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),  --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),  --���
   ITEM_COUNT           NUMBER(14,4),  --�ƻ����������������ϵ���˵����ʵ����������
   RECEIVE_COUNT        NUMBER(14,4),  --ʵ�����������������ϵ���Ч��
   DIFFERENT_COUNT      NUMBER(14,4),  --ʵ����죨�������ϵ���Ч��
   EXPIRED_TIME         DATE   ,        --��Ʒ��Ч��
   CONSTRAINT REQUISITION_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--�������ͷ
drop table D_T1_PICKING_HEADER cascade constraints;
create table D_T1_PICKING_HEADER
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   PICKING_BRANCH_ID	VARCHAR2(32),  --�������ID
   STORAGE_ID           VARCHAR2(32),  --����ֿ�ID
   STORAGE              VARCHAR2(64),  --����ֿ�
   FORM_MAKER_ID        VARCHAR2(32),  --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),  --�Ƶ���Ա
   FORM_TIME            DATE,          --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,          --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   PICKING_MAN_ID       VARCHAR2(32),  --�����ԱID
   PICKING_MAN          VARCHAR2(64),  --�����Ա
   PICKING_TIME         DATE,          --�������
   AUDITOR_ID           VARCHAR2(32),  --�����ԱID
   AUDITOR              VARCHAR2(64),  --�����Ա
   AUDIT_TIME           DATE,          --�������
   PACK_STATUS          VARCHAR2(32),  --װ��״̬(δ����/������)
   FORM_NOTE            VARCHAR2(200) , --�����·���˵��
   CONSTRAINT PICKING_HEADER_PK PRIMARY KEY (FORM_ID)
);

--���������ϸ
drop table D_T1_PICKING_DETAIL cascade constraints;
create table D_T1_PICKING_DETAIL
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   ITEM_NAME            VARCHAR2(64),  --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),  --��λ
   ITEM_CATEGORY        VARCHAR2(32),  --��Ʒ���
   ITEM_COUNT           NUMBER(10,4),  --�������
   BRANCH_ID            VARCHAR2(32),   --����
   CONSTRAINT PICKING_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID, BRANCH_ID)
);

--���������ϸ��Ӧ���͵��Ĺ�ϵ
drop table D_T1_PICKING_REF cascade constraints;
create table D_T1_PICKING_REF
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   FORM_REF_ID          VARCHAR2(32),  --����������͵����
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   BRANCH_ID            VARCHAR2(32),  --����
   ITEM_COUNT           NUMBER(14,4) ,  --���͵��İ�װ����
   CONSTRAINT PICKING_REF_PK PRIMARY KEY (FORM_ID, FORM_REF_ID, ITEM_ID, BRANCH_ID)
);

--װ�䵥��ͷ
drop table D_T1_PACKING_HEADER cascade constraints;
create table D_T1_PACKING_HEADER
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   PACKING_BRANCH_ID	VARCHAR2(32),  --װ�䲿��ID
   FORM_REF_ID          VARCHAR2(32),  --�������ԭʼ���ݱ��(�����)
   FORM_MAKER_ID        VARCHAR2(32),  --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),  --�Ƶ���Ա
   FORM_TIME            DATE,          --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,          --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   PACKING_TIME         DATE,          --װ������
   FORM_NOTE            VARCHAR2(200),  --�����·���˵��
   CONSTRAINT PACKING_HEADER_PK PRIMARY KEY (FORM_ID)
);

--װ�䵥��ϸ
drop table D_T1_PACKING_DETAIL cascade constraints;
create table D_T1_PACKING_DETAIL
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   BRANCH_ID            VARCHAR2(32),  --�ͻ��ŵ�
   BOX_ID           	VARCHAR2(32),  --���ӱ��
   BOX_NAME           	VARCHAR2(64),  --��������(eg:������1����)
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   ITEM_NAME            VARCHAR2(64),  --��Ʒ����
   ITEM_DIMENSION  		VARCHAR2(32),  --��װ��λ
   UNIT_VOLUME     		NUMBER(10,4),  --��λ���
   UNIT_WEIGHT     		NUMBER(10,4),  --��λ����
   ITEM_COUNT      		NUMBER(14,4),  --��װ����
   VOLUME               NUMBER(10,4),  --���
   WEIGHT               NUMBER(10,4),  --����
   ITEM_CATEGORY        VARCHAR2(32) ,  --���
   CONSTRAINT PACKING_DETAIL_PK PRIMARY KEY (FORM_ID, BOX_ID, ITEM_ID, BRANCH_ID)
);

--���빤���ۼ۵��۵������빤���ɹ����۵����������Ĳɹ����۵����������ı�׼���۵����������ļ��˵��۵��������������۵��۵���ͷ
drop table D_T1_PRICE_ADJUST_HEADER cascade constraints;
create table D_T1_PRICE_ADJUST_HEADER
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   FORM_TIME            DATE,          --��������
   ADJUST_TYPE          VARCHAR2(32),  --��������
   ADJUST_SCOPE         VARCHAR2(32),  --���۷�Χ
   SUPPLIER_ID          VARCHAR2(32),  --��Ӧ�̱�ţ��ɹ����۵���Ҫʹ��
   EFFECT_TYPE          VARCHAR2(32),  --��Ч��ʽ
   EFFECT_TIME          DATE,          --��Чʱ��
   NOTE                 VARCHAR2(32),  --��ע
   FORM_MAKER           VARCHAR2(32),  --�Ʊ���Ա
   MAKE_TIME            DATE,          --�Ʊ�����
   AUDITOR              VARCHAR2(32),  --�����Ա
   AUDIT_TIME           DATE,          --�������
   FORM_NOTE            VARCHAR2(200),  --�����·��ı�ע˵��
   CONSTRAINT PRICE_ADJUST_HEADER_PK PRIMARY KEY (FORM_ID)
);

--���빤���ۼ۵��۵������빤���ɹ����۵����������Ĳɹ����۵����������ı�׼���۵����������ļ��˵��۵��������������۵��۵���ϸ
drop table D_T1_PRICE_ADJUST_DETAIL cascade constraints;
create table D_T1_PRICE_ADJUST_DETAIL
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   ITEM_ID              VARCHAR2(32),  --��Ʒ����
   ITEM_NAME            VARCHAR2(64),  --��Ʒ����
   ITEM_DIMENSION       VARCHAR2(32),  --��λ
   ITEM_SPECIFICATION   VARCHAR2(32),  --���
   ITEM_PACKAGER        NUMBER(8,4),   --��װ����
   OLD_PRICE            NUMBER(10,4),  --ԭ��
   NEW_PRICE            NUMBER(10,4),   --�¼�
   CONSTRAINT PRICE_ADJUST_DETAIL_PK PRIMARY KEY (FORM_ID, ITEM_ID)
);

--�ܲ�-�з���Ʒ��Ϣ��
DROP TABLE D_T1_SAMPLE_INFO cascade constraints;
CREATE TABLE D_T1_SAMPLE_INFO
    (
        ID 						VARCHAR2(36),
        PRODUCT_NAME 			VARCHAR2(64), 	--��Ʒ����
        MINIMUM_ORDER_QUANTITY 	INTEGER, 		--����
        PRODUCE_PLACE 			VARCHAR2(100), 	--����
        ORDER_CYCLE 			INTEGER, 		--��������(��)
        USE_CYCLE 				INTEGER, 		--ʹ������(�죬����������)
        QUALIFICATION_PIC 		VARCHAR2(32) ,	--ʳƷ��ȫ�ʸ��ʸ�֤��Ƭ
        SAMPLE_PIC 				VARCHAR2(32) , 	--��Ƭ
        PRICE 					NUMBER(6,2),	--�۸�(Ԫ)
        GROSS_PRICE 			NUMBER(6,2),	--ë��(Ԫ)
        GROSS_WEIGHT 			NUMBER(6,2),	--ë��(��)
        NET_WEIGHT 				NUMBER(6,2),	--����(��)
        NET_RATIO 				NUMBER(5,2),	--������(����/ë��*100=������)
        SHELF_LIFE 				INTEGER,		--������(��)
        DELIVERY_TIME 			INTEGER, 		--����ʱ��(��)
        ACCPTANCE_CRITERIA 		VARCHAR2(300),	--���ձ�׼
        SUPPLIER 				VARCHAR2(100),	--��Ӧ��
        CREATE_USER_ID 			VARCHAR2(32),
        CREATE_TIME 			DATE
    );

--��Ӧ���ܲ����� �ⲿ�������ܲ����� ���˵���ͷ
drop table D_T1_STATEMENT_HEADER cascade constraints;
create table D_T1_STATEMENT_HEADER
(
   FORM_ID              VARCHAR2(32),  --���ݱ��
   FORM_TYPE            VARCHAR2(10),  --���˵������ͣ���Ӧ���ܲ��������G���ⲿ�ܲ��������W��
   PROVIDER_ID          VARCHAR2(32),  --��Ӧ��ID
   PROVIDER             VARCHAR2(96),  --��Ӧ��
   BRANCH_ID            VARCHAR2(32),  --���ű��
   BRANCH_NAME          VARCHAR2(64),  --��������
   FORM_MAKER_ID        VARCHAR2(32),  --�Ƶ���ԱID
   FORM_MAKER           VARCHAR2(64),  --�Ƶ���Ա
   FORM_TIME            DATE,          --�Ƶ�����
   FORM_TIME_ACTUAL     DATE,          --�Ƶ�����(ʵ��ʱ�䣬����ʱ����)
   AUDITOR_ID           VARCHAR2(32),  --�����ԱID
   AUDITOR              VARCHAR2(64),  --�����Ա
   AUDIT_TIME           DATE,          --�������
   ALL_PAY_AMT         NUMBER(10,4),   --�ܽ��
   START_DATE           DATE,          --���˿�ʼ����
   END_DATE             DATE,          --���˽�������
   FORM_NOTE            VARCHAR2(200)  --��ע˵��
);

--��Ӧ���ܲ����� �ⲿ�������ܲ����� ���˵�����ϸ
drop table D_T1_STATEMENT_DETAIL cascade constraints;
create table D_T1_STATEMENT_DETAIL
(
   FORM_ID              VARCHAR2(32),  --��ͷ���ݱ��
   SUB_FORM_ID          VARCHAR2(32),  --��ⵥ/�ɹ������˻���/������/�ⲿ�����˻���
   FORM_REF_ID          VARCHAR2(32),   --����������͵����
   ALL_PAY_AMT          NUMBER(10,4),  --�ܽ��
   FORM_OPERATE_ID      VARCHAR2(32),  --������ԱID
   FORM_OPERATE         VARCHAR2(64),  --������Ա
   FORM_OPERATE_TIME    DATE,          --��������
   FORM_NOTE            VARCHAR2(200)  --��ע˵��
);

--���ݰ汾��¼��
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