::::::::::::::::::::::::::::以下从天天数据库中查询:::::::::::::::::::::::::::::::

::在这里设置数据库机器的IP，如果是本机则设置为localhost
set svr=localhost

::在这里设置数据库的用户名与密码 
set usr=sa
set pwd=123

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set option=-c -U"%usr%" -P"%pwd%" -S"%svr%" -t"|~|"
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

::机构部门表
bcp "select branch_no,branch_name,py_str,address,branch_man,branch_email,branch_tel,branch_fax,'RESTAURANT',branch_name from kmcyV51.dbo.bi_t_branch_info" queryout .\dat_info_branch %option%

::原料类别
bcp "select rtrim(item_clsno),item_clsname,up_clsno,level_num,'RAW' from kmcyV51.dbo.bi_t_item_cls where item_clsname not like '%%半成品'" queryout .\dat_cls_raw %option%

::半成品类别
bcp "select rtrim(item_clsno),item_clsname,'*', '1','SEMIS' from kmcyV51.dbo.bi_t_item_cls where item_clsname like '%%半成品'" queryout .\dat_cls_semis %option%

::出品类别
bcp "select cBigCls_C,cBigCls_N,'*','1','PRODUCT' from kmcyV51.dbo.c_t_food_bigCls union select cLitCls_C,cLitCls_N,cBigCls_C,'2','PRODUCT' from kmcyV51.dbo.c_t_food_litCls" queryout .\dat_cls_food %option%

::原料信息
bcp "select rtrim(a.item_no),rtrim(a.item_no),a.item_name,a.item_subname,a.unit_no,a.py_str,rtrim(a.item_clsno),a.item_size,'RAW','rawsamplepic','原料备注' from kmcyV51.dbo.bi_t_item_info a, kmcyV51.dbo.bi_t_item_cls b where a.item_clsno = b.item_clsno and b.item_clsname not like '%%半成品' and b.item_clsname <> '期刊'" queryout .\dat_item_raw %option%

::半成品信息
bcp "select rtrim(a.item_no),rtrim(a.item_no),a.item_name,a.item_subname,a.unit_no,a.py_str,rtrim(a.item_clsno),a.item_size,'SEMIS','semissamplepic','半成品备注' from kmcyV51.dbo.bi_t_item_info a, kmcyV51.dbo.bi_t_item_cls b where a.item_clsno = b.item_clsno and b.item_clsname like '%%半成品'" queryout .\dat_item_semis %option%

::出品信息
bcp "select cFood_C,cFood_C,cFood_N,cNameEng,sUnit,sNameFast,cLitCls_C,'','PRODUCT','foodsamplepic','出品备注' from kmcyV51.dbo.c_t_food where cLitCls_C <> '9090'" queryout .\dat_item_food %option%

::出品套餐信息
bcp "select a.cSuit_C, a.cFood_C, b.cExchange_C, a.nPrc, 0 from kmcyV51.dbo.c_t_food_suit a left join kmcyV51.dbo.c_t_food_suitexchange b on a.cFood_C = b.cFood_C and a.cSuit_C=b.cSuit_C" queryout .\dat_item_food_suit %option%

::出品销售价
bcp "select cFood_C,nPrc,'SALE',1,'efId1' from kmcyV51.dbo.c_t_food where cLitCls_C <> '9090'" queryout .\dat_food_price %option%

::配方表
bcp "SELECT a.cFood_C,a.cFood_N,a.sUnit,a.cItem_C,a.cItem_N,a.sLastUnit,a.sLastUnitRate,a.nLastQty,a.nLastRate,a.nItemQty,a.nItemQty*b.price,a.eItemType,'RESTAURANT' FROM kmcyV51.dbo.s_t_food_directions a, kmcyV51.dbo.bi_t_item_info b WHERE a.cItem_C=b.item_no" queryout .\dat_food_directions %option%

::供应商信息表
bcp "select rtrim(supcust_no),sup_name,py_str,sup_addr,sup_man,sup_email,sup_tel,sup_fax,'SUPPLIER',other2 from kmcyV51.dbo.bi_t_supcust_info" queryout .\dat_info_supcust %option%