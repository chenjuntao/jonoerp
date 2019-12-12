
--删除营业数据
truncate table d_t_food_bill;
truncate table d_t_food_bills;
truncate table d_t_bill_pay;

--删除系统日志
truncate table d_t0_conn_log;
truncate table d_t0_operatelog;
truncate table d_t0_quartzlog;

--删除传输日志
truncate table com.d_t_restapi_log;
truncate table com.d_t_restapi_err_json;
truncate table com.d_t_restapi_err_msg;

--删除传输过来的基础信息数据
delete from d_t0_business_date where business_date > to_date('2016-04-01', 'yyyy-mm-dd');
delete from d_t2_branch where remark = 'RESTAPI';
delete from d_t2_item_price where EFFITIVE_FORM_ID = 'RESTAPI';
delete from d_t2_item_category where PATH = 'RESTAPI';
delete from d_t2_item_meta where ITEM_SPECIFICATION = 'RESTAPI';


金牛角的推送方式与API推送方式通过接口来统一。
symmetricds推送出品：只在启用出品时才会进行推送，新增、删除、修改类别、修改图片都不会推送。


菜单管理，将全部的菜单信息从数据库中移出来，放在Json配置文件中，更易于以后的更新维护管理。
每个数据库中都要在菜单中增加逆日结的功能菜单。

原先部分代码中，SQL语句直接写在Service中，这样是不合理的，多企业部分代码没法覆盖到Service，是隐藏的BUG，现将这部分对数据操作的SQL语句移动到DAO文件中去。
调价单(priceadjust)部分:BillManageService和BillManageDao;
配送分组(GroupManageService)部分:enableGroup()方法移动到DeliveryTypeBean中;
ProductManageService中有SQL语句没有加上多企业前缀;
quartz中涉及到对出品信息的推送，ProductTransform和JobBean中还有代码没有加上多企业前缀；


原先部分代码中，有的SQL语句直接写为“branch_id = 'L00'”这样的硬编码，都是隐藏的bug。
ImportRequestDao中:s.BRANCH_ID = 'L00'