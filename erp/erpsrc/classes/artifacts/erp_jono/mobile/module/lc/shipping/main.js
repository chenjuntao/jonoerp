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
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
//	var branchCondition = dijit.byId('branchCondition').get('value');
//	var storageCondition = dijit.byId('storageCondition').get('value');
	
//	var requestCondition = dijit.byId('requestCondition').get('value');
	
	var appendValue = " AND h.FORM_TYPE in ('INNER_UNIFIED','INNER_CROSS') AND h.PROVIDER_ID = '" + loginBranchId + "' ";
	
	if(!isEmpty(startDate)){
		appendValue += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') >= '" + startDate + "' " ;
	}
	
	if(!isEmpty(endDate)){
		appendValue += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') <= '" + endDate + "' " 
	}
	
//	if(!isEmpty(branchCondition)){
//		appendValue += " AND h.PROVIDER_ID = '" + branchCondition+ "' "
//	}
//	
//	if(!isEmpty(storageCondition)){
//		appendValue += " AND h.OUT_STORAGE_ID = '" + storageCondition+ "' "
//	}
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
				d.branchType = "LOGISTICSCENTER",
				d.reportFlag = "D_T1_SHIPPING_FORM",
				d.withOutSum =true
			}
		}
	});
	new $.fn.dataTable.FixedColumns(tableInst, {
		"leftColumns" : 2
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
	'display' : '配送单号',
	'mData' : 'form_id',
}, {
	'display' : '审核操作',
	'mData' : 'form_id',
	"render" : operateFmt
}, {
	'display' : '要货单号',
	'mData' : 'form_ref_id'
}, {
	'display' : '单据状态',
	'mData' : 'status'
}, {
	'display' : '是否捡货',
	'mData' : 'pick_status'
}, {
	'display' : '入库状态',
	'mData' : 'input_status'
}, {
	'display' : '配送日期',
	'mData' : 'receive_time'
}, {
	'display' : '配送部门',
	'mData' : 'provider'
}, {
	'display' : '出货仓库',
	'mData' : 'out_storage'
}, {
	'display' : '订货部门',
	'mData' : 'requester',
}, {
	'display' : '入库仓库',
	'mData' : 'in_storage',
}, {
	'display' : '制单人员',
	'mData' : 'form_maker',
}, {
	'display' : '审核人员',
	'mData' : 'auditor',
}, {
	'display' : '审核时间',
	'mData' : 'audit_time',
}, {
	'display' : '入库人员',
	'mData' : 'inputer',
}, {
	'display' : '入库时间',
	'mData' : 'input_time',
}, {
	'display' : '总金额',
	'mData' : 'all_pay_amt',
} ];
