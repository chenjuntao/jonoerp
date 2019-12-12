<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/jsp/navigation.jsp"%>

	<script type="text/javascript">
		// 以jsp目录中tab.html的路径为基准
		addDetailsDiv("餐厅基本资源查询", 
		{
		"餐厅基本信息查询" : "../restaurant/shopInfoQuery/mainView.action",
		"餐厅原料信息查询" : "../restaurant/shopSourceQuery/mainView.action",
		"餐厅出品信息查询" : appRoot + "/restaurant/shopdish/mainView.action",
		"餐厅半成品信息查询" : "../restaurant/shopSemisQuery/mainView.action",
		"选择出品测试" : appRoot + "/restaurant/common/test/mainView.action"
		});
	
		addDetailsDiv("餐厅需求管理原型", 
		{
			"餐厅万元出品查询" : "../businessquery/foodAmtQuery/mainView.action",
			"餐厅万元用量查询" : "../businessquery/foodRawQuery/mainView.action",
			"餐厅要货模板管理" : appRoot + "/jsp/restaurant/prototype/goodsbillprocess/modelmanager/listModels.jsp",
			"餐厅要货生成" : appRoot + "/jsp/restaurant/prototype/goodsbillprocess/creategoodsbill/createGoodsBill.jsp",
			"餐厅要货审核" : appRoot + "/jsp/restaurant/prototype/goodsbillprocess/confirmgoodsbill/listUnconfirmGoodsBills.jsp",
			"餐厅要货单管理" : appRoot + "/jsp/restaurant/prototype/goodsbillprocess/querygoodsbill/listGoodsBills.jsp"
		});
		
		addDetailsDiv("餐厅入库管理原型", 
		{
		"餐厅库存查询" : appRoot + "/jsp/restaurant/prototype/putinstorage/querycurrentstorage/queryCurrentStorage.jsp",
		"餐厅外部入库管理" : appRoot + "/jsp/restaurant/prototype/putinstorage/putinstoragefromoutside/listPurchaseOrders.jsp",
		"餐厅配送入库管理" : appRoot + "/jsp/restaurant/prototype/putinstorage/putinstoragebydistribution/listDistributionBills.jsp",
		"餐厅入库单审核" : appRoot + "/jsp/restaurant/prototype/putinstorage/confirmputinstoragebill/listPutInStorageBills.jsp",
		"餐厅入库单查询" : appRoot + "/jsp/restaurant/prototype/putinstorage/queryputinstoragebill/listPutInStorageBills.jsp"
		});

		addDetailsDiv("餐厅配送反审核管理原型", 
		{
		"餐厅配送反审核" : appRoot + "/jsp/restaurant/prototype/distributionantiaudit/createantiaudit/listDistributionBills.jsp",
		"餐厅配送反审核处理" :  appRoot + "/jsp/restaurant/prototype/distributionantiaudit/processantiaudit/listDistributionAntiAuditBills.jsp",
		"餐厅配送反审核查询" : appRoot + "/jsp/restaurant/prototype/distributionantiaudit/queryantiaudit/listDistributionAntiAuditBills.jsp"
		});
		
	    addDetailsDiv("餐厅配送退货管理原型", 
		{
		"餐厅配送退货单生成" : appRoot + "/jsp/restaurant/prototype/distributionreject/createdistributionrejectbill/listDistributionBills.jsp",
		"餐厅配送退货单审核" : appRoot + "/jsp/restaurant/prototype/distributionreject/confirmdistributionrejectbill/listDistributionRejectBills.jsp",
		"餐厅配送退货管理" : appRoot + "/jsp/restaurant/prototype/distributionreject/processdistributionrejectbill/listDistributionRejectBills.jsp",
		"餐厅配送退货单查询" : appRoot + "/jsp/restaurant/prototype/distributionreject/querydistributionrejectbill/listDistributionRejectBills.jsp"
		});
		
	    addDetailsDiv("餐厅采购退货管理原型", 
		{
		"餐厅采购退货单生成" : appRoot + "/jsp/restaurant/prototype/purchasereject/createpurchaserejectbill/listPutInStorageBills.jsp",
		"餐厅采购退货单审核" : appRoot + "/jsp/restaurant/prototype/purchasereject/confirmpurchaserejectbill/listPurchaseRejectBills.jsp",
		"餐厅采购退货管理" : appRoot + "/jsp/restaurant/prototype/purchasereject/processpurchaserejectbill/listPurchaseRejectBills.jsp",
		"餐厅采购退货单查询" : appRoot + "/jsp/restaurant/prototype/purchasereject/querypurchaserejectbill/listPurchaseRejectBills.jsp"
		});
		
		addDetailsDiv("餐厅报损管理", 
		{
		"餐厅原料报损单生成" : appRoot + "/restaurant/reportdamage/createloss/mainView.action",
		"餐厅原料报损单审核" : appRoot + "/restaurant/reportdamage/confirmloss/mainView.action",
		"餐厅原料报损处理" : appRoot + "/restaurant/reportdamage/processloss/mainView.action",
		"餐厅原料报损单查询" : appRoot + "/restaurant/reportdamage/queryloss/mainView.action",
		"餐厅出品报损单生成" : appRoot + "/jsp/restaurant/reportdamage/createdishloss/createDishDamageReportBill.jsp",
		"餐厅出品报损单审核" : appRoot + "/jsp/restaurant/reportdamage/confirmdishloss/listDishDamageReportBill.jsp",
		"餐厅出品报损处理" : appRoot + "/jsp/restaurant/reportdamage/processdishloss/listDishDamageReportBill.jsp",
		"餐厅出品报损单查询" : appRoot + "/jsp/restaurant/reportdamage/querydishloss/listDishDamageReportBill.jsp"
		});
	
	    addDetailsDiv("餐厅调拨管理", 
		{
			"餐厅调拨单生成" : appRoot + "/jsp/restaurant/prototype/allocateitem/createallocateitembill/createAllocateItemBill.jsp",
			"餐厅调拨单审核" : appRoot + "/jsp/restaurant/prototype/allocateitem/confirmallocateitembill/listAllocateItemBill.jsp",
			"餐厅调拨单确认" : appRoot + "/jsp/restaurant/prototype/allocateitem/agreeallocateitembill/listAllocateItemBill.jsp",
			"餐厅拨付管理" : appRoot + "/jsp/restaurant/prototype/allocateitem/processallocateitembill/listAllocateItemBill.jsp",
			"餐厅调拨单查询" : appRoot + "/jsp/restaurant/prototype/allocateitem/queryallocateitembill/listAllocateItemBill.jsp"
		});
	
		 addDetailsDiv("餐厅盘点管理原型", 
		{
		"餐厅盘点锁库" : appRoot + "/jsp/restaurant/prototype/checkstorage/lockstorage/listAllWaring.jsp",
		"餐厅盘点清单生成" : appRoot + "/jsp/restaurant/prototype/checkstorage/createcheckitembill/createCheckItemBill.jsp",
		"餐厅盘点清单信息输入" : appRoot + "/jsp/restaurant/prototype/checkstorage/inputcheckitembill/inputCheckItemBill.jsp",
		"餐厅盘点单计算生成" : appRoot + "/jsp/restaurant/prototype/checkstorage/managecheckbill/listCheckItemBill.jsp",
		"餐厅盘点单审核" : appRoot + "/jsp/restaurant/prototype/checkstorage/confirmcheckbill/listCheckBill.jsp"
		});
		 addDetailsDiv("餐厅日结管理", 
		{
		"餐厅日结管理" : appRoot + "/jsp/restaurant/prototype/checkstorage/lockstorage/listAllWaring.jsp"
		});
	
		 addDetailsDiv("餐厅进出报表查询", 
			{
			"当前库存查询-未完成" : "businessBasbusinessQueryByTime.jsp",
			"餐厅要货单查询" : appRoot + "/restaurant/inoutquery/goodsbill/mainView.action",
			"餐厅入库单查询" : appRoot + "/restaurant/inoutquery/putinstorage/mainView.action",
			"餐厅配送单查询-未完成" : appRoot + "/businessquery/cvariance/mainView.action",
			"餐厅配送退货单查询" : appRoot + "/restaurant/inoutquery/dreject/mainView.action",
			"餐厅采购退货查询-未完成" : appRoot + "/businessquery/cvariance/mainView.action",
			"餐厅原料报损查询-未完成" : appRoot + "/businessquery/cvariance/mainView.action",
			"餐厅出品报损查询-未完成" : appRoot + "/businessquery/cvariance/mainView.action",
			"餐厅调拨查询" : appRoot + "/restaurant/inoutquery/allocateitem/mainView.action",
			"餐厅盘点单查询-未完成" : appRoot + "/businessquery/cvariance/mainView.action"
			});
		
		  addDetailsDiv("餐厅报表管理", 
		{
		"前台收入报表" : "businessBasbusinessQueryByTime.jsp",
		"盘点信息报表" : "businessBasbusinessQueryByTime.jsp",
		"成本统计报表" : appRoot + "/businessquery/cvariance/mainView.action",
		"操作记录报表" : "businessBasbusinessQueryByTime.jsp"
		});
	
		
		addDetailsDiv("前台营业报表查询", {
			"时段营业分析表" : "../restaurant/periodbusiness/mainView.action",
            "单据情况表" : "../restaurant/billConditions/mainView.action",
            "单据原因汇总表" : appRoot + "/businessquery/billreason/mainView.action",
            "付款明细表" : appRoot + "/businessquery/billPayDetail/mainView.action",
            "营业统计表（按类型）" : appRoot + "/businessquery/businesscount/mainView.action",
            "出品销售统计" : appRoot + "/businessquery/foodSellCount/mainView.action",
            "出品销售统计汇总表" : appRoot + "/businessquery/foodSummary/mainView.action",
            "营业收入明细表" : appRoot + "/businessquery/incomeDetail/mainView.action"
		});

		widgets.ToggleAll();
	</script>

</body>
</html>