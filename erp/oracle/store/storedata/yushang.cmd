::::::::::::::::::::::::::::以下从御商数据库中查询:::::::::::::::::::::::::::::::

::在这里设置数据库机器的IP，如果是本机则设置为localhost
set svr=kmjxc

::在这里设置数据库的用户名与密码 
set usr=sa
set pwd=123

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set option=-c -U"%usr%" -P"%pwd%" -S"%svr%" -t"|~|"
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

::供应商与门店以及商品的对应关系
bcp "select b.supcust_no, right(c.branch_no,2), d.item_no from kmjxc.dbo.bi_t_item_sup a, kmjxc.dbo.bi_t_supcust_info b, kmjxc.dbo.bi_t_branch_info c, kmjxc.dbo.bi_t_item_i d where a.sup_id = b.supcust_id and a.branch_id = c.branch_id and a.item_id = d.item_id" queryout .\dat_sup_item %option%
::bcp "select b.supcust_no, d.item_no from kmjxc.dbo.bi_t_item_sup a, kmjxc.dbo.bi_t_supcust_info b, kmjxc.dbo.bi_t_item_i d where a.sup_id = b.supcust_id and a.item_id = d.item_id and a.branch_id='3'" queryout .\dat_sup_item %option%

::各种商品不同价格
bcp "select item_no,in_price,ps_price,pf_price,sale_price,vip_price from kmjxc.dbo.bi_t_item_i" queryout .\dat_item_price %option%

::各门店仓库的库存量
bcp "select right(b.branch_no,2), c.item_no, a.stock_qty, a.firstinout_date from kmjxc.dbo.ic_t_branch_stock a, kmjxc.dbo.bi_t_branch_info b, kmjxc.dbo.bi_t_item_i c where a.branch_id = b.branch_id and a.item_id = c.item_id and a.stock_qty <> 0" queryout .\dat_branch_stock %option%
