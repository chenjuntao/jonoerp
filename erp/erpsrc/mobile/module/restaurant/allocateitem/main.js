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
	var params = "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val() + "&inBranchId="
			+ $("#inBranchId").val()+ "&outBranchId=" + $("#outBranchId").val();

	var _url = appRoot + "/m/restaurant/allocateitem/query/doQuery.action?formType=request";
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
		"leftColumns" : 2
	});
}

function doQuery() {
	tableInst.ajax.url(getQueryUrl()).load();
}

function codeFmt(data, type, row) {
	return "<a href='javascript: gotoURL(\"" + data + "\");'>" + data + "</a>";
}

function operateFmt(data, type, row) {
	if (row.formStatus == '未审核') {
		return "<a href='javascript: doAudit(\"" + data + "\");'>审核通过</a>";
	}
	return '';
}
function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑中！")
		return false;
	}
	if (status == '已删除') {
		alert("单据已删除！")
		return false;
	} else if (status == '已作废') {
		alert("单据已作废！")
		return false;
	}
	if (status == '未审核') {// 更保险的做法，上面的写法只能是排除法（当然更加友好些），这里是限定法
		return true;
	}
	alert("单据已审核！");
	return false;
}

function doAudit(_formId) {
	if (!checkStatus(_formId)) {
		return;
	}
	if (!confirm("确定审核通过吗？")) {
		return;
	}
	var _url = appRoot + "/restaurant/allocateitem/confirm/auditById.action?formId=" + _formId;
	$.ajax({
		type : "POST",
		url : _url,
		error : function(e) {
			alert(e);
		},
		success : function(data) {
			if (data.msg == 'ok') {
				alert('审核成功！');
				doQuery();
			}
		}
	});
}

var cols = [ {
	'display' : '序号',
	'mData' : 'rownumber'
}, {
	'display' : '单据编号',
	'mData' : 'formId',
	"render" : codeFmt
}, {
	'display' : '审核操作',
	'mData' : 'formId',
	"render" : operateFmt
},
{
	'display' : '单据状态',
	'mData' : 'formStatus'
},
{
	'display' : '调入部门',
	'mData' : 'inStore'
},
{
	'display' : '调出部门',
	'mData' : 'outStore'
},
{
	'display' : '制单人员',
	'mData' : 'fromMaker'
},
{
	'display' : '制单日期',
	'mData' : 'formTime'
},
{
	'display' : '审核人员',
	'mData' : 'auditor'
},
{
	'display' : '审核日期',
	'mData' : 'auditTime'
},
{
	'display' : '确认人员',
	'mData' : 'confirmer'
},
{
	'display' : '确认日期',
	'mData' : 'confirmTime'
},
{
	'display' : '调出人员',
	'mData' : 'outMan'
},
{
	'display' : '调出日期',
	'mData' : 'outTime'
},
{
	'display' : '备注',
	'mData' : 'formNote'
},
{
	'display' : '主要调拨品',
	'mData' : 'maxPayItem'
},
{
	'display' : '调拨总额',
	'mData' : 'allPayAmt'
}];

function gotoURL(_formId) {
	var _url = appRoot + "/restaurant/allocateitem/query/scanView.action?formId=" + _formId;
	window.open(_url);
}