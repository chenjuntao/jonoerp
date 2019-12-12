/* by cjt,2014.7.11 */

--创建单据明细表（69——>22个域）---------------------------------------------
drop table d_t_food_bill cascade constraints;
create table d_t_food_bill(
cBill_c			varchar2(32),	--not null,	--单据编号---------------------
dBusiness		date,			--not null,	--营业日期---------------------
cTable_c		varchar2(12),	--not null,	--台位编号
cTable_n		varchar2(100),	--not null,	--台位名称---------------------
cPeriod_c		varchar2(12),	--not null,	--市别编号
cPeriod_n		varchar2(100),	--not null,	--市别名称---------------------
cShift_c		varchar2(12),	--not null,	--班次编号
cShift_n		varchar2(100),	--not null,	--班次名称---------------------
cVip_c			varchar2(32),				--会员卡号
cVip_n			varchar2(100),				--会员名字
--cTableBill	varchar2(36)	not null,
iGuestNum		number(3),		--not null,	--顾客人数---------------------
dtBillTime		date,			--not null,	--开台时间---------------------
dtSettleTime	date,			--not null,	--结账时间---------------------
--eStatus			varchar2(72)	not null,
--iPrintTimes		number(10)		not null,
nFoodAmt		number(8,2),	--not null,	--消费金额---------------------
eServicePay		varchar2(300),	--not null,	--服务费说明
nServiceRate	number(3,3),	--not null,	--服务费费率
nServiceFee		number(8,2),	--not null,	--服务费金额
eMinPay			varchar2(300),	--not null,	--最低消费说明
nMinPay			number(8,2),	--not null,	--最低消费额
nDisAmt			number(8,2),	--not null,	--折扣总金额---------------------
nDisRate		number(3,3),	--not null,	--折扣率
nPresentAmt		number(8,2),				--赠送金额---------------------
nRoundAmt		number(5,2),	--not null,	--舍尾金额---------------------
nOughtAmt		number(8,2),	--not null,	--应付款金额---------------------
nPayAmt			number(8,2),	--not null,	--实付款金额---------------------
bSettle			number(1),		--not null,	--是否结账
bUnSettle		number(1),		--not null,	--是否逆结
cCreateMan_c	varchar2(32),	--not null,	--开台人编号
cCreateMan		varchar2(100),	--not null,	--开台人名字---------------------
cSettleMan_c	varchar2(32),	--not null,	--结账人编号
cSettleMan		varchar2(100),	--not null,	--结账人名字---------------------
cUnSettleMan_c	varchar2(32),	--not null,	--逆结人编号
cUnSettleMan	varchar2(100),	--not null,	--逆结人名字
cDiscountMan_c	varchar2(32),	--not null,	--打折人编号
cDiscountMan	varchar2(100),	--not null,	--打折人名字
cDisManCur_n	varchar2(32),				--照单折扣人---------------------
cDisCurWhy		varchar2(300),				--照单折扣原因---------------------
sSendMan		varchar2(100),	--not null,	--送餐人---------------------
cBranch_c		varchar2(32),	--not null,	--门店编号---------------------
cBranch_n		varchar2(150),	--not null,	--门店名称---------------------
sRemark			varchar2(500),	--not null,	--备注信息---------------------

bUnFee			number(1),		--not null,	--免服务费
bUnMin			number(1),		--not null,	--免最低消费
eWholeType		varchar2(24),	--not null,	--舍入方式

--cLockMan_c		varchar2(32)	not null,	
--cLockMan		varchar2(20)	not null,
--sSex			varchar2(12),
--sAgeSegment		varchar2(24),
--sRemarkNew		varchar2(64)	not null,
--sAziMuth		varchar2(12)	not null,
--iFlowId			number(10)		not null,
sBillType		varchar2(12),	--not null,	--单据类型

cTimeFee_c		varchar2(100),				--计时费说明
nTimeAmt		number(8,2),				--计时费金额
bUnTea			number(1),					--免茶
bUnJie			number(1),					--免芥
--bOrderCode		varchar2(20),
--bMTable_c		number(1),
--sDataFlag		varchar2(36),
nDisAmtFull		number(8,2),				--全单折扣额
nDisRateCur		number(3,3),				--照单折扣率
nDisAmtCur		number(8,2),				--照单折扣额
cPos_c			varchar2(32),				--POS机编号
cFadeBill_c		varchar2(32),				--退单编号
--cTable_cadd		clob,
--cPrintDocMan	varchar2(32),
cSerMan			varchar2(32),				--服务员
--dPrintDocTime	date,
cFadeBillMan_c	varchar2(32),				--退单人编号
cFadeBillMan	varchar2(100),				--退单人名字
nDisAmtService	number(8,2),				--服务费照单折扣金额
nServiceFeeOld	number(8,2),				--服务费原币金额
--nOughtAmtAver	number(6,2),
--bTicket			number(1),
dtTimeBegin		date,						--计时开始
dtTimeEnd		date,						--计时结束
--GetAmt			number(9,2),
--nVipAmt			number(9,2),
--nCommonAmt		number(9,2),
bUnTime			number(1),					--免计时费
--iFadeType		number(1),

nVipFoodOriAmt	number(8,2),				--会员价
cFloor_n		varchar2(100),				--厅楼名称
cFloor_c		varchar2(3),				--厅楼编号

cDisFullWhy		varchar2(300),				--全单折扣原因
--cAcNobm			varchar2(60),
--nMinPayFill		number(9,2),
nFactAmt		number(8,2),				--实付款
--nEraseAmt		number(9,2),
--cEraseMan		varchar2(36),
--cTicket_c		varchar2(12),
--nTicketnQty		number(9,2),
--nRemindTime		number(10),
cArrear_c		varchar2(32),				--挂账编号
cArrear_n		varchar2(100)				--挂账名称
--cConsume_n		varchar2(20),
--cRemark			varchar2(100),		
--dtSendTime		date,
--com_no			number(10)		not null,
--nDisRateService	number(9,2),
--nDisAmtServ_hand	number(9,2),
--cDisServMan		varchar2(36),
--sDisServWhy		varchar2(60),
--cashPrint_num	number(10)		not null,
--dtChmatTime		date,
--cFreeBillRsn	varchar2(60),
--nInTicketAmt	number(14,4),
--bBanBill		number(1),
--nExtBanAmt		number(14,4),
--constraint d_t_food_bill_pk primary key(cBill_c) 
);

--创建出品明细表（39——>16个域）---------------------------------------------
drop table d_t_food_bills cascade constraints;
create table d_t_food_bills(
cBill_c			varchar2(32),	--not null,	--单据编号----------------
cFood_c			varchar2(32),	--not null,	--出品编码----------------
cFood_n			varchar2(150),	--not null,	--出品名称----------------
sUnit			varchar2(50),	--not null,	--例牌----------------
nPrc			number(8,2),	--not null,	--单价----------------
nQty			number(8,2),	--not null,	--数量----------------
nAmt			number(8,2),	--not null,	--消费金额----------------
nDisAmt			number(8,2),	--not null,	--折扣金额----------------
eSuitFlag		varchar2(24),	--not null,	--套餐标志----------------
cSuitBill		varchar2(32),	--not null,	--套餐编号----------------
cSuitName		varchar2(300),	--not null,	--套餐名称----------------
eRetSendFlag	varchar2(12),	--not null,	--退品或赠送标志(正常、赠送、退品)----------------
sRetSendReMark	varchar2(300),	--not null,	--退品或赠送原因----------------
cPresentBackMan	varchar2(100),				--赠送或退品人----------------
cLitCls_c		varchar2(32),				--小类编号----------------
clitCls_n		varchar2(100),				--小类名称----------------

cFoodBill		varchar2(4),	--not null,	--点单号
nExtPrc			number(8,2),	--not null,	--加价
bDiscount		number(1),		--not null,	--是否允许照单折扣

sMade			varchar2(300),	--not null,	--（顾客要求）菜的制作方法
cServiceMan		varchar2(100),	--not null,	--服务员
nDisRate		number(3,3),	--not null,	--折扣率

cPos_c			varchar2(32),	--not null,	--点单POS号
cLogin			varchar2(100),	--not null,	--点单人
cRetSendBill	varchar2(32),	--not null,	--退品单号
--sTimeNumber		varchar2(120),

cNameEng		varchar2(150),				--英文名称

--nPrcBase		number(9,2),
--nPrcRoom		number(9,2),
--nDeduct			number(9,2),
--nCost			number(9,2),
--bHandFood		number(1),
cBigCls_c		varchar2(32),				--大类编号
cBigCls_n		varchar2(100),				--大类名称
--cSortBill		varchar2(90),
dtInputTime		date,						--点单输入时间
iPrcType		number(3),					--价格类型？
--sDataFlag		varchar2(36),
bServiceFee		number(1),					--是否有服务费
--bLimit			number(1),
nDisAmts		number(8,2),				--照单/单道折扣额
--cDiscountMan_n	varchar2(36),
--nDisRateFood	number(9,2),
--nDisAmtFood		number(9,2),
--cTable_cadd		clob,
--sReservedrmk	varchar2(300),
--sArea			varchar2(60),
--sCustRemark		varchar2(150),
cDep_c			varchar2(32),				--出品部门编号
cDep_n			varchar2(100),				--出品部门名称
--cPreBill		varchar2(12),
--cEnterDocMan	varchar2(36),
nServiceFees	number(8,2),				--服务费
--bPrintTotalBill	number(1),
--iSaleId			number(10),
--cSaleBill		varchar2(12),
--cSaleSortBill	varchar2(12),
--eSaleFlag		varchar2(72),
--cBarCode		varchar2(60),
--dtPullTime		date,
--cPullMan		varchar2(36),
nDoQty			number(8,2),				--实际数量
--bPullSpeed		number(1),
--bSetWeighted	number(1),
nPrcold			number(8,2),				--价格原币金额
--bModiPrced		number(1),
--nDeductPer		number(9,2),
--cDiscountWhy	varchar2(60),
cMainMade		varchar2(100),				--微辣、加急、打包、少盐等等
cBillQuick		varchar2(20)				--单品催菜
--bDis_food		number(1),
--eVoucherFlag	varchar2(60),
--cVoucherBill	varchar2(60),
--merge_table	varchar2(192),
--cTBill_c		varchar2(36),
--bTimeFee		number(10),
--bInTicket		number(1),
--bBanquet		number(1),
--constraint d_t_food_bills_fk foreign key(cBill_c) references d_t_bill_pay(cBill_c),
--constraint d_t_food_bills_pk primary key(cBill_c,cFoodBill,cFood_c)
); 

--创建付款明细表(19——>9个域)---------------------------------------------------
drop table d_t_bill_pay cascade constraints;
create table d_t_bill_pay(
cBill_c		varchar2(32),	--not null,	--单据单号----------------
cPay_c		varchar2(32),	--not null,	--付款方式编号----------------
cPay_n		varchar2(100),	--not null,	--付款方式名称----------------
sUnit		varchar2(16),	--not null,	--付款单位----------------
nPayAmt		number(8,2),	--not null,	--付款本币金额----------------
cVIP_c		varchar2(32),				--会员卡号----------------
cVIP_n		varchar2(100),				--会员名字----------------
cArrear_c	varchar2(32),				--挂账编号----------------
cArrear_n	varchar2(100),				--挂账名称----------------

--cBillNum	varchar2(12)	not null,

eInType		varchar2(24),	--not null,	--实收/虚收
nExchRate	number(6,5),	--not null,	--换算率
nOldAmt		number(8,2),	--not null,	--付款原币金额

cGrantMan_c	varchar2(32),				--授权人编号
cGrantMan	varchar2(100),				--授权人名字
cPayMan_c	varchar2(32),	--not null,	--收银员编号
cPayMan		varchar2(100),	--not null,	--收银员名字
ePayType	varchar2(24),				--付款类型


--bPreAmt		number(1),
--cConsume_n	varchar2(60),
--cOuterCode	varchar2(60),
--iother		number(3),

cShift_c	varchar2(3),				--班次编号
cShift_n	varchar2(100)				--班次名称
--nBankAmt	number(9,2),
--constraint d_t_bill_pay_pk primary key(cBill_c,cPay_c)
);