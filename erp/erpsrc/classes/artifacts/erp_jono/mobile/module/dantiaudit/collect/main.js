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
	var _url = appRoot + "/m/reportmanage/common/doQuery.action?formType=request";
	return _url;
}

function isEmpty(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop))
			return false;
	}
	return true;
}

function getAppend(){
	var append = "";
	var startDate= $("#startDate").val();
	var endDate= $("#endDate").val();
		if(null!=startDate && ""!=startDate){
		append += "AND TO_CHAR(h.antiaudit_time,'YYYY-MM-DD') >= '" + startDate + "' ";
	}
	if(null!=endDate && ""!=endDate){
		append += "  AND TO_CHAR(h.antiaudit_time,'YYYY-MM-DD') <= '" + endDate + "' "; 
	}

	return append;
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
				d.append =  getAppend(),
				d.branchType = "RESTAURANT",
				d.reportFlag = "D_T1_SHIPPING_ANTIAUDIT_HEADER",
				d.withOutSum =true
			}
		}
	});
	new $.fn.dataTable.FixedColumns(tableInst, {
		"leftColumns" : 3
	});
}

function doQuery() {
	tableInst.ajax.url(getQueryUrl()).load();
}

var cols = [ {
	'display' : '序号',
	'mData' : 'rownumber'
}, {
	'display' : '反审核部门编码',
	'mData' : 'antiaudit_branch_id',
	"render" : codeFmt
}, {
	'display' : '反审核部门',
	'mData' : 'antiaudit_branch'
}, {
	'display' : '配送部门',
	'mData' : 'provider'
}, {
	'display' : '出货仓库',
	'mData' : 'out_storage'
}, {
	'display' : '订货部门',
	'mData' : 'requester'
}, {
	'display' : '入货仓库',
	'mData' : 'in_storage'
}, {
	'display' : '商品编码',
	'mData' : 'item_id',
    "render" : codeFmt
}, {
	'display' : '商品名称',
	'mData' : 'item_name'
}, {
	'display' : '反审核实收数',
	'mData' : 'antiaudit_receive_count'
}, {
	'display' : '反审核金额',
	'mData' : 'antiaudit_pay_amt'
} ];

