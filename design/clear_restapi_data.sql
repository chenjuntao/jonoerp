
--删除营业数据
truncate table d_t_food_bill;
truncate table d_t_food_bills;
truncate table d_t_bill_pay;

--删除系统日志
truncate table d_t0_conn_log;
truncate table d_t0_operatelog;
truncate table d_t0_quartzlog;

--删除传输日志
truncate table d_t_restapi_log;
truncate table d_t_restapi_err_json;
truncate table d_t_restapi_err_msg;

--删除传输过来的基础信息数据
delete from d_t0_business_date where business_date > to_date('2016-04-01', 'yyyy-mm-dd');
delete from d_t2_branch where remark = 'RESTAPI';
delete from d_t2_item_price where EFFITIVE_FORM_ID = 'RESTAPI';
delete from d_t2_item_category where PATH = 'RESTAPI';
delete from d_t2_item_meta where ITEM_SPECIFICATION = 'RESTAPI';



每个数据库中都要在菜单中增加逆日结的功能。