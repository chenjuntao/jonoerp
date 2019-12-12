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
	var startDate= $("#startDate").val();
	var endDate= $("#endDate").val();
	var append = "AND H.IN_BRANCH_ID = '" + loginBranchId + "' ";
		if(null!=startDate && ""!=startDate){
		append += "AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	}
	if(null!=endDate && ""!=endDate){
		append += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') <= '" + endDate + "' "; 
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
				d.reportFlag = "D_T1_TRANSFER_HEADER",
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
	'display' : '调入部门',
	'mData' : 'in_branch'
}, {
	'display' : '调入仓库',
	'mData' : 'in_storage'
}, {
	'display' : '调出部门',
	'mData' : 'out_branch'
}, {
	'display' : '调出仓库',
	'mData' : 'out_storage'
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
	'display' : '申请数量',
	'mData' : 'apply_count'
}, {
	'display' : '实际数量',
	'mData' : 'actual_count'
}, {
	'display' : '调拨差异',
	'mData' : 'different_count'
}, {
	'display' : '金额',
	'mData' : 'pay_amt'
} ];

