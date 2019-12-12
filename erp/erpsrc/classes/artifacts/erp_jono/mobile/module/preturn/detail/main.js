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
	var append = "AND i.input_department_id = '" + loginBranchId + "' ";
	var startDate= $("#startDate").val();
	var endDate= $("#endDate").val();
		if(!isEmpty(startDate)){
		append += "AND TO_CHAR(h.RETURN_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	}
	if(!isEmpty(endDate)){
		append += "  AND TO_CHAR(h.RETURN_TIME,'YYYY-MM-DD') <= '" + endDate + "' "; 
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
				d.reportFlag = "D_T1_RETURN_GOODS_DETAIL",
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
	'display' : '退货日期',
	'mData' : 'return_time',
}, {
	'display' : '退货单号',
	'mData' : 'form_id',
}, {
	'display' : '入库单号',
	'mData' : 'form_ref_id'
}, {
	'display' : '入库日期',
	'mData' : 'input_time'
}, {
	'display' : '入库部门',
	'mData' : 'input_department'
}, {
	'display' : '入库仓库',
	'mData' : 'storage'
}, {
	'display' : '退货人员',
	'mData' : 'returner'
}, {
	'display' : '商品编码',
	'mData' : 'item_id'
}, {
	'display' : '商品名称',
	'mData' : 'item_name'
}, {
	'display' : '类别名称',
	'mData' : 'category_name'
}, {
	'display' : '退货数量',
	'mData' : 'return_count'
}, {
	'display' : '退货金额',
	'mData' : 'return_pay_amt'
}, {
	'display' : '退货原因',
	'mData' : 'return_reason'
} ];

