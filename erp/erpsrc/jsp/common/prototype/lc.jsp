<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/jsp/common/jsp/navigation.jsp"%>

	<script type="text/javascript">
		// 以jsp目录中tab.html的路径为基准
		addDetailsDiv("物流中心基本资源查询", 
		{
			"物流中心基本信息查询" : "lc/prototype/basicresource/basicinfo.jsp",
			"物流中心物资清单查询" : "lc/prototype/basicresource/inventories.jsp"
		});

		addDetailsDiv("物流中心需求管理 ", 
		{
			"餐厅要货模板管理" : appRoot + "/restaurant/goodsbill/template/mainView.action?templateType=request",
// 			"外部订货需求汇总" : appRoot + "/lc/request/outsummary/mainView.action",
			"内部万元需求量查询" : appRoot + "/lc/request/wydemand/mainView.action",
			"物流中心采购预计模板管理" : appRoot + "/lc/request/template/mainView.action?templateType=estimate",
			"物流中心采购预计" : appRoot + "/lc/request/estimate/mainView.action",
			"物流中心汇总生成采购单" : appRoot + "/lc/request/summary/mainView.action",
// 			"内部需求汇总" : appRoot + "/lc/request/insummary/mainView.action",
// 			"物流中心预计采购单生成" : appRoot + "/lc/request/purchase/mainView.action",
// 			"直配越库采购单生成" : appRoot + "/lc/request/purchase/directView.action",
			"补充单据采购单生成" : appRoot + "/lc/request/addition/mainView.action",
			"采购单查询" : appRoot + "/lc/request/purchase/queryView.action",
			"物流中心配送单生成" : appRoot + "/lc/request/distribution/mainView.action",
			"配送单查询" : appRoot + "/restaurant/inoutquery/shipping/mainView.action",
			"物流中心出货单生成" : appRoot + "/lc/request/delivery/mainView.action",
			"出货单查询" : appRoot + "/lc/request/delivery/manage/mainView.action"
		});
	
		addDetailsDiv("物流中心库存基础管理", 
		{
			"物流中心库存查询" : appRoot + "/restaurant/common/stock/category/mainView.action",
			"物流中心采购单查询" : "lc/prototype/storage/purchasebillquery.jsp",
			"物流中心入库管理" : "lc/prototype/storage/storagein.jsp",
			"物流中心入库单审核" : "lc/prototype/storage/storageinaudit.jsp",
			"内部配送出库管理" : "lc/prototype/storage/innerdistributionout.jsp",
			"外部配送出库管理" : "lc/prototype/storage/outtrdistributionout.jsp",
			"物流中心捡货单管理" : "lc/prototype/storage/pickingbill.jsp",
			"物流中心装箱单管理" : "lc/prototype/storage/packingbill.jsp"
		});
	
		addDetailsDiv("物流中心报损管理", 
		{
// 		"物流中心报损单生成" : "lc/prototype/reportdamage/createbill.jsp",
// 		"物流中心报损单审核" : "lc/prototype/reportdamage/auditbill.jsp",
// 		"物流中心报损处理" : "lc/prototype/reportdamage/processbill.jsp"
		"物流中心报损单生成" : appRoot + "/restaurant/reportdamage/createloss/mainView.action?branchType=LOGISTICSCENTER",
		"物流中心报损单审核" : appRoot + "/restaurant/reportdamage/confirmloss/mainView.action?branchType=LOGISTICSCENTER",
		"物流中心报损处理" : appRoot + "/restaurant/reportdamage/processloss/mainView.action?branchType=LOGISTICSCENTER",
		"物流中心报损单查询" : appRoot + "/restaurant/reportdamage/queryloss/mainView.action?branchType=LOGISTICSCENTER"
		});
	
		addDetailsDiv("物流中心审核与反审核管理", 
		{
		"物流中心配送审核" : "lc/prototype/antiaudit/restaurantbillaudit.jsp",
		"物流中心配送反审核" : "lc/prototype/antiaudit/distributionantiaudit.jsp",
		"物流中心配送反审核处理" : "lc/prototype/antiaudit/distributionantiauditprocess.jsp",
		"物流中心入库反审核" : "lc/prototype/antiaudit/storageantiaudit.jsp",
		"物流中心入库反审核处理" : "lc/prototype/antiaudit/storageantiauditprocess.jsp"
		});
	
		addDetailsDiv("物流中心退货管理", 
		{
// 		"物流中心采购退货单生成" : "lc/prototype/returngoods/lcreturnbillcreate.jsp",
// 		"物流中心采购退货单审核" : "lc/prototype/returngoods/lcreturnbillaudit.jsp",
// 		"物流中心采购退货" : "lc/prototype/returngoods/lcreturnbillprocess.jsp",
// 		"餐厅配送退货单确认" : "lc/prototype/returngoods/shopreturnbillaudit.jsp",
// 		"外部退货单生成" : "lc/prototype/returngoods/outreturnbillcreate.jsp",
// 		"外部退货单审核" : "lc/prototype/returngoods/outreturnbillaudit.jsp",
// 		"外部退货管理" : "lc/prototype/returngoods/outreturnbillprocess.jsp"

			"物流中心采购退货生成" : appRoot + "/restaurant/preject/create/mainView.action?branchType=LOGISTICSCENTER",
			"物流中心采购退货管理" : appRoot + "/restaurant/preject/manage/mainView.action?branchType=LOGISTICSCENTER",
			"物流中心采购退货审核" : appRoot + "/restaurant/preject/audit/mainView.action?branchType=LOGISTICSCENTER",
			"物流中心采购退货" : appRoot + "/restaurant/preject/reject/mainView.action?branchType=LOGISTICSCENTER",
			"餐厅配送退货单确认" : appRoot + "/restaurant/dreject/confirm/mainView.action",
			"外部采购退货生成" : appRoot + "/restaurant/preject/create/mainView.action?branchType=OUTERORDER",
			"外部采购退货管理" : appRoot + "/restaurant/preject/manage/mainView.action?branchType=OUTERORDER",
			"外部采购退货审核" : appRoot + "/restaurant/preject/audit/mainView.action?branchType=OUTERORDER",
			"外部采购退货" : appRoot + "/restaurant/preject/reject/mainView.action?branchType=OUTERORDER"
		});
	
		addDetailsDiv("物流中心盘点管理", 
		{
		"物流中心盘点锁库" : "lc/prototype/checkstorage/.jsp",
		"物流中心盘点清单模板管理" : "lc/prototype/checkstorage/.jsp",
		"物流中心盘点清单生成" : "lc/prototype/checkstorage/.jsp",
		"物流中心盘点信息输入" : "lc/prototype/checkstorage/.jsp",
		"物流中心盘点单计算生成" : "lc/prototype/checkstorage/.jsp",
		"物流中心盘点单审核" : "lc/prototype/checkstorage/.jsp"
		});
	
		// addDetailsDiv("物流中心运输资源管理", 
		// {
		// "运输单元基本信息管理" : "lc/prototype/transport/transportbasicinfo.jsp",
		// "运输单元运力状况管理" : "lc/prototype/transport/transportcapacity.jsp",
		// "其他预留接口" : "lc/prototype/transport/reserveinterface.jsp"
		// });

		addDetailsDiv("物流中心结算管理", 
		{
		"物流中心日结操作" : "lc/prototype/dailysettlement/dailysettlement.jsp",
		"物流中心工作开发计划" : "lc/prototype/lcplan.jsp"
		});
	
		addDetailsDiv("物流中心报表管理", 
		{
// 		"物流中心报损单查询" : "lc/prototype/reportquery/querybill.jsp",
// 		"物流中心反审核查询" : "lc/prototype/reportquery/billantiauditquery.jsp",
// 		"物流中心退货单查询" : "lc/prototype/reportquery/returnbillquery.jsp",
// 		"物流中心盘点信息查询" : "businessBasbusinessQueryByTime.jsp",
// 		"物流中心内部需求报表" : "businessBasbusinessQueryByTime.jsp",
// 		"物流中心外部订单报表" : "businessBasbusinessQueryByTime.jsp",
// 		"物流中心收入报表" : "businessBasbusinessQueryByTime.jsp",
// 		"物流中心盘点报表" : "businessBasbusinessQueryByTime.jsp",
// 		"物流中心成本报表" : "businessBasbusinessQueryByTime.jsp"
		"物流中心报损单查询" : appRoot + "/restaurant/inoutquery/reportdamage/queryloss/mainView.action?branchType=LOGISTICSCENTER",
		"物流中心采购退货查询" : appRoot + "/restaurant/inoutquery/preject/mainView.action?branchType=LOGISTICSCENTER",
		"外部采购退货查询" : appRoot + "/restaurant/inoutquery/preject/mainView.action?branchType=OUTERORDER",
		"配送退货单查询" : appRoot + "/restaurant/inoutquery/dreject/mainView.action?branchType=LOGISTICSCENTER"
		});

	 	widgets.ToggleAll();
	</script>
</body>
</html>