create table bak_food_bill as select * from d_t_food_bill;
drop table d_t_food_bill;
create table d_t_food_bill as select CBILL_C, DBUSINESS, CTABLE_C, CTABLE_N, CPERIOD_C, CPERIOD_N, CSHIFT_C, CSHIFT_N, 
            CVIP_C, CVIP_N, IGUESTNUM, DTBILLTIME, DTSETTLETIME, NFOODAMT, ESERVICEPAY, 
            NSERVICERATE, NSERVICEFEE, EMINPAY, NMINPAY, NDISAMT, NDISRATE, NROUNDAMT,
            NOUGHTAMT, BSETTLE, BUNSETTLE, CCREATEMAN_C, CCREATEMAN, CSETTLEMAN_C, 
            CSETTLEMAN, CUNSETTLEMAN_C, CUNSETTLEMAN, CDISCOUNTMAN_C, CDISCOUNTMAN, 
            SREMARK, BUNFEE, BUNMIN, EWHOLETYPE, NPAYAMT, SBILLTYPE, SSENDMAN, CBRANCH_C, 
            CBRANCH_N, CTIMEFEE_C, NTIMEAMT, BUNTEA, BUNJIE, NDISAMTFULL, NDISRATECUR, 
            NDISAMTCUR, CPOS_C, CFADEBILL_C, CDISMANCUR_N, CSERMAN, CFADEBILLMAN_C, 
            CFADEBILLMAN, NDISAMTSERVICE, NSERVICEFEEOLD, DTTIMEBEGIN, DTTIMEEND, BUNTIME,
            NPRESENTAMT, NVIPFOODORIAMT, CFLOOR_N, CFLOOR_C, CDISCURWHY, CDISFULLWHY, 
            NFACTAMT, CARREAR_C, CARREAR_N
from bak_food_bill;

create table bak_food_bills as select * from d_t_food_bills;
drop table d_t_food_bills;
create table d_t_food_bills as select cBill_c, cFoodBill, cFood_c, cFood_n, sUnit, nPrc, nQty, nAmt, nExtPrc, bDiscount,
                        eSuitFlag, cSuitBill, eRetSendFlag, sRetSendReMark, sMade, cServiceMan,
                        nDisRate, nDisAmt, cPos_c, cLogin, cRetSendBill, cSuitName, cNameEng, 
                        cLitCls_c, clitCls_n,   cBigCls_c, cBigCls_n, dtInputTime, iPrcType,
                        bServiceFee, nDisAmts, cDep_c, cDep_n, nServiceFees, nDoQty, 
                        cPresentBackMan, nPrcold, cMainMade, cBillQuick 
from bak_food_bills;

create table bak_bill_pay as select * from d_t_bill_pay;
drop table d_t_bill_pay;
create table d_t_bill_pay as select cBill_c, cPay_c, cPay_n, sUnit, eInType, nExchRate, nOldAmt, nPayAmt, cGrantMan_c,
                        cGrantMan, cPayMan_c, cPayMan, ePayType, card_id, cVIP_n, 
                        cArrear_c, cArrear_n, cShift_c, cShift_n
from bak_bill_pay;