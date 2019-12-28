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
	var params = "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val() + "&branchId="
			+ $("#branchId").val();

	var _url = appRoot + "/m/restaurant/inoutquery/shipping/doQuery.action?formType=request";
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
	'display' : '配送部门',
	'mData' : 'providerId'
},
{
	'display' : '配送日期',
	'mData' : 'receiveTime'
},
{
	'display' : '订货部门',
	'mData' : 'requester'
},
{
	'display' : '订货地址',
	'mData' : 'requestAddress'
},
{
	'display' : '制单人员',
	'mData' : 'formMaker'
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
	'display' : '入库人员',
	'mData' : 'inputer'
},
{
	'display' : '入库时间',
	'mData' : 'inputTime'
},
{
	'display' : '备注',
	'mData' : 'formNote'
},
{
	'display' : '金额最大的商品',
	'mData' : 'maxPayItem'
},
{
	'display' : '调拨总额',
	'mData' : 'allPayAmt'
}];

function gotoURL(_formId) {
	var _url = appRoot + "/restaurant/inoutquery/shipping/scanView.action?formId=" + _formId;
	window.open(_url);
}