$(function() {
	addMenu();
});

function addMenu() {
	$(menuData).each(function(i, ele) {
		var level = ele.level;
		if (level == 2) {
			$('#mainMenu').append(formSubMenu(ele));
		} else {
			$('#mainMenu').append(formMenu(ele));
		}
	});
}

function formMenu(ele) {
	var html = ' <li><a class="ajax-link" href="' + ele.url
			+ '"><i class="glyphicon glyphicon-align-justify"></i><span> ' + ele.name + '</span></a></li>';
	return html;
}

function formSubMenu(ele) {
	var html = '<li class="accordion"> <a href="#"><i class="glyphicon glyphicon-plus"></i>' + '<span> ' + ele.name
			+ '</span></a> <ul class="nav nav-pills nav-stacked"> ';
	$(ele.children).each(function(j, item) {
		html += '<li><a href="' + item.url + '">' + item.name + '</a></li>';
	});
	html += ' </ul> </li>';
	return html;
}

var menuData = [ {
	level : 2,
	name : "营业信息查询",
	children : [ {
		name : "收入明细查询",
		url : appRoot + "/businessquery/shopListQuery/mobileView.action"
	}, {
		name : "付款方式查询",
		url : appRoot + "/businessquery/paymentquery/mobileView.action"
	}, {
		name : "人员操作查询",
		url : appRoot + "/jsp/businessquery/peopleBillList.action?fromMobile=true"
	}, {
		name : "单号查询",
		url : appRoot + "/businessquery/billCodeQuery/mainView?fromMobile=true"
	} ]
}, {
	level : 2,
	name : "餐厅进出报表查询",
	children : [ {
		name : "要货单查询",
		url : appRoot + "/m/reportmanage/request/restaurant/form/mainView.action"
	// url : appRoot + "/m/restaurant/inoutquery/goodsbill/mainView.action",
	}, {
		name : "入库单查询",
		url : appRoot + "/m/restaurant/inoutquery/putinstorage/mainView.action",
	}, {
		name : "配送单查询",
		url : appRoot + "/m/restaurant/inoutquery/shipping/mainView.action",
	}, {
		name : "配送退货单查询",
		url : appRoot + "/m/restaurant/inoutquery/dreject/mainView.action",
	}, {
		name : "采购退货单查询",
		url : appRoot + "/m/reportmanage/preturn/restaurant/form/mainView.action",
	}, {
		name : "原料报损查询",
		url : appRoot + "/m/restaurant/inoutquery/reportdamage/queryloss/mainView.action",
	}, {
		name : "出品报损查询",
		url : appRoot + "/m/restaurant/inoutquery/reportdamage/querydishloss/mainView.action",
	}, {
		name : "调拨查询",
		url : appRoot + "/m/restaurant/inoutquery/allocateitem/mainView.action"
	}, {
		name : "盘点单查询",
		url : appRoot + "/m/restaurant/inoutquery/checkstorage/mainView.action"
	} ]
}, {
	level : 2,
	name : "餐厅单据查询",
	children : [ {
		name : "餐厅仓库明细帐",
		url : appRoot + "/m/restaurant/inoutquery/inventory/detail/mainView.action"
	}, {
		name : "配送单查询",
		url : appRoot + "/m/reportmanage/shipping/restaurant/form/mainView.action"
	}, {
		name : "配送退货单查询",
		url : appRoot + "/m/reportmanage/dreturn/restaurant/form/mainView.action"
	}, {
		name : "配送反审核单查询",
		url : appRoot + "/m/reportmanage/dantiaudit/restaurant/form/mainView.action"
	}, {
		name : "采购退货单查询",
		url : appRoot + "/m/reportmanage/preturn/restaurant/form/mainView.action",
	}, {
		name : "报损单查询",
		url : appRoot + "/m/reportmanage/loss/restaurant/form/mainView.action",
	}, {
		name : "调拨查询",
		url : appRoot + "/m/reportmanage/transfer/restaurant/form/mainView.action"
	} ]
}, {
	level : 2,
	name : "餐厅报表查询",
	children : [ {
		name : "餐厅要货明细报表",
		url : appRoot + "/m/reportmanage/request/restaurant/detail/mainView.action"
	}, {
		name : "餐厅要货汇总报表",
		url : appRoot + "/m/reportmanage/request/restaurant/collect/mainView.action"
	}, {
		name : "餐厅报损明细报表",
		url : appRoot + "/m/reportmanage/loss/restaurant/detail/mainView.action"
	}, {
		name : "餐厅报损明汇总表",
		url : appRoot + "/m/reportmanage/loss/restaurant/collect/mainView.action"
	}, {
		name : "餐厅采购入库明细表",
		url : appRoot + "/m/reportmanage/input/restaurant/detail/mainView.action"
	}, {
		name : "餐厅采购入库汇总表",
		url : appRoot + "/m/reportmanage/input/restaurant/collect/mainView.action"
	}, {
		name : "餐厅调拨明细表",
		url : appRoot + "/m/reportmanage/transfer/restaurant/detail/mainView.action"
	}, {
		name : "餐厅调拨汇总表",
		url : appRoot + "/m/reportmanage/transfer/restaurant/collect/mainView.action"
	}, {
		name : "餐厅采购退货明细表",
		url : appRoot + "/m/reportmanage/preturn/restaurant/detail/mainView.action"
	}, {
		name : "餐厅采购退货汇总表",
		url : appRoot + "/m/reportmanage/preturn/restaurant/collect/mainView.action"
	}, {
		name : "餐厅配送退货明细表",
		url : appRoot + "/m/reportmanage/dreturn/restaurant/detail/mainView.action"
	}, {
		name : "餐厅配送退货汇总表",
		url : appRoot + "/m/reportmanage/dreturn/restaurant/collect/mainView.action"
	}, {
		name : "餐厅配送反审核明细表",
		url : appRoot + "/m/reportmanage/dantiaudit/restaurant/detail/mainView.action"
	}, {
		name : "餐厅配送反审核汇总表",
		url : appRoot + "/m/reportmanage/dantiaudit/restaurant/collect/mainView.action"
	}, {
		name : "餐厅配送明细表",
		url : appRoot + "/m/reportmanage/shipping/restaurant/detail/mainView.action"
	}, {
		name : "餐厅配送汇总表",
		url : appRoot + "/m/reportmanage/shipping/restaurant/collect/mainView.action"
	} ]
}, {
	level : 2,
	name : "物流中心单据查询",
	children : [ {
		name : "要货单查询",
		url : appRoot + "/m/reportmanage/request/lc/form/mainView.action"
	}, {
		name : "配送单查询",
		url : appRoot + "/m/reportmanage/shipping/lc/form/mainView.action"
	}, {
		name : "报损单查询",
		url : appRoot + "/m/reportmanage/loss/lc/form/mainView.action"
	}, {
		name : "调拨单查询",
		url : appRoot + "/m/reportmanage/transfer/lc/form/mainView.action"
	}, {
		name : "采购退货单查询",
		url : appRoot + "/m/reportmanage/preturn/lc/form/mainView.action"
	}, {
		name : "外部订货方退货单查询",
		url : appRoot + "/m/restaurant/inoutquery/preject/mainView.action"
	}, {
		name : "配送退货单查询",
		url : appRoot + "/m/reportmanage/dreturn/restaurant/form/mainView.action"
	}, {
		name : "配送反审核单查询",
		url : appRoot + "/m/reportmanage/dantiaudit/restaurant/form/mainView.action"
	} ]
}, {
	level : 2,
	name : "物流中心报表查询",
	children : [ {
		name : "配送明细报表",
		url : appRoot + "/m/reportmanage/shipping/lc/detail/mainView.action"
	}, {
		name : "配送汇总报表",
		url : appRoot + "/m/reportmanage/shipping/lc/collect/mainView.action"
	}, {
		name : "仓库细报表",
		url : appRoot + "/m/restaurant/inoutquery/inventory/detail/tologisticsCenter.action"
	}, {
		name : "仓库汇总表",
		url : appRoot + "/m/restaurant/inoutquery/inventory/summary/mainView.action"
	}, {
		name : "报损明细报表",
		url : appRoot + "/m/reportmanage/loss/lc/detail/mainView.action"
	}, {
		name : "报损明汇总表",
		url : appRoot + "/m/reportmanage/loss/lc/collect/mainView.action"
	}, {
		name : "采购入库明细表",
		url : appRoot + "/m/reportmanage/input/lc/detail/mainView.action"
	}, {
		name : "采购入库汇总表",
		url : appRoot + "/m/reportmanage/input/lc/collect/mainView.action"
	}, {
		name : "调拨明细表",
		url : appRoot + "/m/reportmanage/transfer/lc/detail/mainView.action"
	}, {
		name : "调拨汇总表",
		url : appRoot + "/m/reportmanage/transfer/restaurant/collect/mainView.action"
	}, {
		name : "采购明细表",
		url : appRoot + "/m/reportmanage/purchasing/lc/detail/mainView.action"
	}, {
		name : "采购汇总表",
		url : appRoot + "/m/reportmanage/purchasing/lc/collect/mainView.action"
	}, {
		name : "采购退货明细表",
		url : appRoot + "/m/reportmanage/preturn/lc/detail/mainView.action"
	}, {
		name : "采购退货汇总表",
		url : appRoot + "/m/reportmanage/preturn/lc/collect/mainView.action"
	}, {
		name : "配送退货明细表",
		url : appRoot + "/m/reportmanage/dreturn/restaurant/detail/mainView.action"
	}, {
		name : "配送退货汇总表",
		url : appRoot + "/m/reportmanage/dreturn/restaurant/collect/mainView.action"
	}, {
		name : "配送反审核明细表",
		url : appRoot + "/m/reportmanage/dantiaudit/restaurant/detail/mainView.action"
	}, {
		name : "配送反审核汇总表",
		url : appRoot + "/m/reportmanage/dantiaudit/restaurant/collect/mainView.action"
	} ]
}, {
	level : 2,
	name : "中央工厂单据查询",
	children : [ {
		name : "报损单查询",
		url : appRoot + "/m/reportmanage/loss/cf/form/mainView.action"
	}, {
		name : "采购退货单查询",
		url : appRoot + "/m/reportmanage/preturn/cf/form/mainView.action"
	}, {
		name : "生产计划单查询",
		url : appRoot + "/m//reportmanage/arrangement/cf/form/mainView.action"
	}, {
		name : "生产工单查询",
		url : appRoot + "/m/reportmanage/workorder/cf/form/mainView.action"
	} ]
}, {
	level : 2,
	name : "中央工厂报表查询",
	children : [ {
		name : "计划单明细查询",
		url : appRoot + "/m/reportmanage/arrangement/cf/detail/mainView.action"
	}, {
		name : "计划单汇总查询",
		url : appRoot + "/m/reportmanage/arrangement/cf/collect/mainView.action"
	}, {
		name : "报损单明细查询",
		url : appRoot + "/m/reportmanage/loss/cf/detail/mainView.action"
	}, {
		name : "报损单汇总查询",
		url : appRoot + "/m/reportmanage/loss/cf/collect/mainView.action"
	}, {
		name : "入库单明细查询",
		url : appRoot + "/m/reportmanage/input/cf/detail/mainView.action"
	}, {
		name : "入库单汇总查询",
		url : appRoot + "/m/reportmanage/input/cf/collect/mainView.action"
	}, {
		name : "采购单明细查询",
		url : appRoot + "/m/reportmanage/purchasing/cf/detail/mainView.action"
	}, {
		name : "采购单汇总查询",
		url : appRoot + "/m/reportmanage/purchasing/cf/collect/mainView.action"
	}, {
		name : "采购退货单明细查询",
		url : appRoot + "/m/reportmanage/preturn/cf/detail/mainView.action"
	}, {
		name : "采购退货单汇总查询",
		url : appRoot + "/m/reportmanage/preturn/cf/collect/mainView.action"
	}, {
		name : "生产工单制程明细查询",
		url : appRoot + "/m/reportmanage/workorder/cf/detail/mainView.action"
	}, {
		name : "非工单领料单明细查询",
		url : appRoot + "/m/reportmanage/manual/cf/detail/mainView.action"
	}, {
		name : "非工单领料单汇总查询",
		url : appRoot + "/m/reportmanage/manual/cf/collect/mainView.action"
	}, {
		name : "工单领料单明细查询",
		url : appRoot + "/m/reportmanage/produce/cf/detail/mainView.action"
	}, {
		name : "工单领料单汇总查询",
		url : appRoot + "/m/reportmanage/produce/cf/collect/mainView.action"
	} ]
}

];