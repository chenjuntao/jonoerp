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
	var params = "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val() +  "&reason=" + $("#reason").val() +"&branchType=" + 'RESTAURANT'+ "&branchId="
	+ $("#branchId").val();

	var _url = appRoot + "/m/restaurant/inoutquery/inventory/detail/doQuery.action?formType=request";

	return _url + params;
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
			url : _url
		}
	});
	new $.fn.dataTable.FixedColumns(tableInst, {
		"leftColumns" : 4
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
	'mData' : 'storageName',
}, {
	'display' : '原料编码',
	'mData' : 'itemId',
}, {
	'display' : '原料名称',
	'mData' : 'itemName'
}, {
	'display' : '单位',
	'mData' : 'unit'
}, {
	'display' : '门店',
	'mData' : 'branchName'
}, {
	'display' : '操作时间',
	'mData' : 'operationTime'
}, {
	'display' : '单据日期',
	'mData' : 'businessDate'
}, {
	'display' : '业务类型',
	'mData' : 'reason'
}, {
	'display' : '期初数量',
	'mData' : 'orgiCount'
}, {
	'display' : '入库数量（盘盈）',
	'mData' : 'itemCountIn'
}, {
	'display' : '入库金额',
	'mData' : 'itemCountInMoney'
}, {
	'display' : '出库数量（盘亏）',
	'mData' : 'itemCountOut'
}, {
	'display' : '出库金额',
	'mData' : 'itemCountOutMoney'
}, {
	'display' : '结存数量',
	'mData' : 'resultCount'
}, {
	'display' : '结存单价',
	'mData' : 'itemUnitPrice'
}, {
	'display' : '结存金额',
	'mData' : 'resultCountMoney'
} ];

