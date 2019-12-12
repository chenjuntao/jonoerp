::::::::::::::::::::::::::::以下从天天数据库中查询:::::::::::::::::::::::::::::::

::在这里设置数据库机器的IP，如果是本机则设置为localhost
set svr=localhost

::在这里设置数据库的用户名与密码 
set usr=sa
set pwd=123

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set option=-c -U"%usr%" -P"%pwd%" -S"%svr%" -t"|~|"
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

::出品类别
bcp "select cBigCls_C,cBigCls_N,'*','1','PRODUCT' from kmcyV51.dbo.c_t_food_bigCls union select cLitCls_C,cLitCls_N,cBigCls_C,'2','PRODUCT' from kmcyV51.dbo.c_t_food_litCls" queryout .\dat_cls_food %option%


::出品信息
bcp "select cFood_C,cFood_C,cFood_N,cNameEng,sUnit,sNameFast,cLitCls_C,'','PRODUCT','foodsamplepic','出品备注' from kmcyV51.dbo.c_t_food where cLitCls_C <> '9090'" queryout .\dat_item_food %option%

::出品套餐信息
bcp "select a.cSuit_C, a.cFood_C, b.cExchange_C,a.nQty, a.nPrc, 0 from kmcyV51.dbo.c_t_food_suit a left join kmcyV51.dbo.c_t_food_suitexchange b on a.cFood_C = b.cFood_C and a.cSuit_C=b.cSuit_C" queryout .\dat_item_food_suit %option%


::配方表
bcp "SELECT a.cFood_C,a.cFood_N,a.sUnit,a.cItem_C,a.cItem_N,a.sLastUnit,a.sLastUnitRate,a.nLastQty,a.nLastRate,a.nItemQty,a.nItemQty*b.price,a.eItemType,'RESTAURANT' FROM kmcyV51.dbo.s_t_food_directions a, kmcyV51.dbo.bi_t_item_info b WHERE a.cItem_C=b.item_no" queryout .\dat_food_directions %option%