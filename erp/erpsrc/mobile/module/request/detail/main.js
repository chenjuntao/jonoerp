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
	var startDate= $("#startDate").val();
	var endDate= $("#endDate").val();
	var append = "AND H.FORM_TYPE = 'request' AND H.BUYER_ID = '" + loginBranchId + "' ";
		if(!isEmpty(startDate)){
		append += "AND TO_CHAR(h.form_time,'YYYY-MM-DD') >= '" + startDate + "' ";
	}
	if(!isEmpty(endDate)){
		append += "  AND TO_CHAR(h.form_time,'YYYY-MM-DD') <= '" + endDate + "' "; 
	}

	return append;
}
function initTable() {
	var _url = getQueryUrl();
	var startDate= $("#startDate").val();
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
				d.reportFlag = "D_T1_REQUEST_DETAIL",
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
	'display' : '制单日期',
	'mData' : 'form_time',
}, {
	'display' : '单据编号',
	'mData' : 'form_id',
	"render" : codeFmt
}, {
	'display' : '审核日期',
	'mData' : 'audit_time'
}, {
	'display' : '部门编码',
	'mData' : 'buyer_id'
}, {
	'display' : '门店名称',
	'mData' : 'buyer_name'
}, {
	'display' : '仓库编码',
	'mData' : 'storage_id'
}, {
	'display' : '仓库名称',
	'mData' : 'storage'
}, {
	'display' : '商品编码',
	'mData' : 'item_id'
}, {
	'display' : '商品名称',
	'mData' : 'item_name'
}, {
	'display' : '类别编码',
	'mData' : 'item_category'
}, {
	'display' : '类别名称',
	'mData' : 'category_name'
}, {
	'display' : '单位',
	'mData' : 'item_dimension'
}, {
	'display' : '规格',
	'mData' : 'item_specification'
}, {
	'display' : '订货量',
	'mData' : 'order_count'
}, {
	'display' : '标准单价',
	'mData' : 'item_unit_price'
}, {
	'display' : '标准金额',
	'mData' : 'pay_amt'
} ];

