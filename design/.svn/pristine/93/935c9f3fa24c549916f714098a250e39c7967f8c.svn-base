::::::::::::::::::::::::::::以下从中央工厂数据库中查询:::::::::::::::::::::::::::::::

::在这里设置数据库机器的IP，如果是本机则设置为localhost
set svr=localhost

::在这里设置数据库的用户名与密码 
set usr=sa
set pwd=123

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set option=-c -U"%usr%" -P"%pwd%" -S"%svr%" -t"|~|"
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


::配方表
bcp "SELECT a.cFood_C,a.cFood_N,a.sUnit,a.cItem_C,a.cItem_N,a.sLastUnit,a.sLastUnitRate,a.nLastQty,a.nLastRate,a.nItemQty,a.nItemQty*b.price,a.eItemType,'CENTRALFACTORY' FROM zycf_tt.dbo.s_t_food_directions a, zycf_tt.dbo.bi_t_item_info b WHERE a.cItem_C=b.item_no" queryout .\dat_food_directions1 %option%
