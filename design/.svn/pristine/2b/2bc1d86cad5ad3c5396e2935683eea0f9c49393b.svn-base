set dbname=kmcyV51
set username=sa
set userpass=123

set selectDate=%1


::d_t_food_Bill
bcp "select * from %dbname%.dbo.d_t_food_Bill where datediff(day,dbusiness,'%selectDate%')=0" queryout .\food_bill.txt -c -t` -U"%username%" -P"%userpass%"

::d_t_food_Bills
bcp "select db.cBill_C, db.cFoodBill, db.cFood_C, db.cFood_N, db.sUnit, db.nPrc, db.nQty, db.nAmt, db.nExtPrc, db.bDiscount, db.eSuitFlag, db.cSuitBill, db.eRetSendFlag, db.sRetSendRemark,replace( db.sMade,char(13)+char(10),''), db.cServiceMan, db.nDisRate, db.nDisAmt, db.cPos_C, db.cLogin, db.cRetSendBill, db.sTimeNumber, db.cSuitName, db.cNameEng, db.cLitCls_C, db.cLitCls_N, db.nPrcBase, db.nPrcRoom, db.nDeduct, db.nCost, db.bHandFood, db.cBigCls_c, db.cBigCls_N, db.cSortBill, db.dtinputtime, db.iPrcType, db.sDataFlag, db.bServiceFee, db.bLimit, db.nDisAmts, db.cDiscountMan_n, db.nDisRateFood, db.nDisAmtFood, db.ctable_cadd, db.sReservedRmk, db.sArea, db.ScustRemark, db.cDep_C, db.cDep_N, db.cPreBill, db.cEnterdocMan, db.nServiceFees, db.bprinttotalbill, db.isaleid, db.cSaleBill, db.cSaleSortBill, db.eSaleFlag, db.cBarcode, db.dtpulltime, db.cPullman, db.nDoQty, db.bPullSpeed, db.bsetweighted, db.cPresentbackman, db.nPrcold, db.bModiPrced, db.nDeductPer, db.cDiscountWhy, db.cMainmade, db.cbillquick, db.bdis_food, db.evoucherflag, db.cvoucherbill, db.merge_table, db.cTBill_C, db.bTimeFee, db.bInTicket, db.bbanquet from %dbname%.dbo.d_t_food_Bills db, %dbname%.dbo.d_t_food_Bill df where db.CBill_C =df.CBill_C and datediff(day,df.dbusiness,'%selectDate%')=0" queryout .\food_bills.txt -c  -t` -U"%username%" -P"%userpass%"

::d_t_bill_pay
bcp "select db.CBILL_C, db.CBILLNUM, db.CPAY_C, db.CPAY_N, db.SUNIT, db.EINTYPE, db.NEXCHRATE, db.NOLDAMT, db.NPAYAMT, db.CGRANTMAN_C, db.CGRANTMAN, db.CPAYMAN_C, db.CPAYMAN,db.EPAYTYPE, db.CARD_ID, db.CVIP_N, db.BPREAMT, db.CCONSUME_N, db.COUTERCODE, db.IOTHER, db.CARREAR_C, db.CARREAR_N, db.CSHIFT_C, db.CSHIFT_N, db.NBANKAMT from  %dbname%.dbo.d_t_bill_pay db, %dbname%.dbo.d_t_food_Bill df where db.CBill_C =df.CBill_C and datediff(day,df.dbusiness,'%selectDate%')=0" queryout .\bill_pay.txt -c -t` -U"%username%" -P"%userpass%"