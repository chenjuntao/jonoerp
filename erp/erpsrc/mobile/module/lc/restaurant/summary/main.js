$(function() {
	initControl();
	initTable();
});

function initControl() {
	$('#startDate, #endDate').scroller('destroy').scroller({
		preset : 'date',
		theme : 'default',
		mode : 'mixed',
		lang : 'zh',
		animate : 'none'
	});
}

var tableInst = null;

function getQueryUrl() {
	var params = "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val();
	var _url = appRoot + "/m/restaurant/inoutquery/inventory/summary/doSummaryQuery.action?formType=request";
	return _url + params;
}

function isEmpty(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop))
			return false;
	}
	return true;
}

function getAppend(){
	var startDate= $("#startDate").val();
	var endDate= $("#endDate").val();
    var appendValue = " AND b.BRANCH_TYPE = 'LOGISTICSCENTER' ";
	
	if(!isEmpty(startDate)){
		appendValue += "  AND TO_CHAR(h.RETURN_TIME,'YYYY-MM-DD') >= '" + startDate + "' " ;
	}
	
	if(!isEmpty(endDate)){
		appendValue += "  AND TO_CHAR(h.RETURN_TIME,'YYYY-MM-DD') <= '" + endDate + "' " 
	}
	

	return appendValue;
}
function initTable() {
	var _url = getQueryUrl();
	tableInst = $('#dataTable').DataTable({
		// "scrollY": "300px",
		"scrollX" : "100%",
		"scrollCollapse" : true,
		language : {
			"info" : " _START_至_END_， 总共 _TOTAL_ 条记录",
			"lengthMenu" : "每页显示 _MENU_ 条记录",
			"infoEmpty" : "记录为空",
			"zeroRecords" : "记录为空",
			"processing" : "加载中...",
			"search" : "查找：",
			"paginate" : {
				"first" : "首页",
				"last" : "尾页",
				"next" : "下一页",
				"previous" : "上一页"
			}
		},
		"columns" : cols,
		"pagingType" : "full_numbers",
		"serverSide" : true,
		"processing" : true,
		bFilter : false,
		"bSort" : false,
		"ajax" : {
			type : "POST",
			url : _url,
			data: function(d){
				d.branchType = "LOGISTICSCENTER",
				d.withOutSum =true
			}
		}
	});
	new $.fn.dataTable.FixedColumns(tableInst, {
		"leftColumns" : 5
	});
}

function doQuery() {
	tableInst.ajax.url(getQueryUrl()).load();
}

var cols = [ {
	'display' : '序号',
	'mData' : 'rownumber'
}, {
	'display' : '仓库',
	'mData' : 'storageName'
}, {
	'display' : '原料编码',
	'mData' : 'itemId'
}, {
	'display' : '原料名称',
	'mData' : 'itemName'
}, {
	'display' : "单位",
	'mData' : "unit"
}, {
	'display' : "期初数量",
	'mData' : "orgicount"
}, {
	'display' : "期初金额",
	'mData' : "orgicountmoney"
}, {
	'display' : "入库数量合计",
	'mData' : "itemCountIn"
}, {
	'display' : "入库金额合计",
	'mData' : "itemCountInMoney"
}, {
	'display' : "出库数量合计",
	'mData' : "itemCountOut"
}, {
	'display' : "出库金额合计",
	'mData' : "itemCountOutMoney"
}, {
	'display' : "结存数量",
	'mData' : "resultcount"
}, {
	'display' : "结存金额",
	'mData' : "resultcountmoney"
}, {
	'display' : "采购入库数量",
	'mData' : "putinstorage"
}, {
	'display' : "配送入库数量",
	'mData' : "distribution"
}, {
	'display' : "配送反审核入库数量",
	'mData' : "antiauditIn"
}, {
	'display' : "配送反审核出库数量",
	'mData' : "antiauditOut"
}, {
	'display' : "配送退货数量",
	'mData' : "preject"
}, {
	'display' : "采购退货数量",
	'mData' : "preject"
}, {
	'display' : "原料报损数量",
	'mData' : "rawLoss"
}, {
	'display' : "出品报损数量",
	'mData' : "dishLoss"
}, {
	'display' : "调拨入库数量",
	'mData' : "allocateitemIn"
}, {
	'display' : "调拨出库数量",
	'mData' : "allocateitemOut"
}, {
	'display' : "盘盈数量",
	'mData' : "checkstorageIn"
}, {
	'display' : "盘亏数量",
	'mData' : "checkstorageOut"
}, {
	'display' : "理论扣库数量",
	'mData' : "theoryDeduct"
}, {
	'display' : "门店名称",
	'mData' : "branchName"
}, {
	'display' : "原料类别",
	'mData' : "itemCategory"
}];
