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
    var appendValue = " AND b.BRANCH_TYPE = 'LOGISTICSCENTER' ";
	
	if(!isEmpty(startDate)){
		appendValue += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') >= '" + startDate + "' " ;
	}
	
	if(!isEmpty(endDate)){
		appendValue += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') <= '" + endDate + "' " 
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
				d.append =  getAppend(),
				d.reportFlag = "D_T1_TRANSFER_FORM",
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
	'display' : '审核操作',
	'mData' : 'form_id',
	"render" : operateFmt
}, {
	'display' : '确认日期',
	'mData' : 'confirm_time'
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
	'display' : '总金额',
	'mData' : 'all_pay_amt'
}, {
	'display' : '主要调拨品',
	'mData' : 'max_pay_item'
}, {
	'display' : '单据状态',
	'mData' : 'status'
}];

