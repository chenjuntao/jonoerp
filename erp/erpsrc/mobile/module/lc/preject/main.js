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

	var _url = appRoot + "/m/restaurant/preject/manage/doQuery.action?formType=request";
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
				d.branchType = "OUTERORDER",
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
	'display' : '退货单号',
	'mData' : 'formId',
	"render" : codeFmt
},
{
	'display' : '供应商',
	'mData' : 'provider'
},
{
	'display' : '入库单编号',
	'mData' : 'formRefId'
},
{
	'display' : '入库部门',
	'mData' : 'inputDepartment'
},
{
	'display' : '入库人员',
	'mData' : 'inputer'
},
{
	'display' : '入库日期',
	'mData' : 'inputTime'
},
{
	'display' : '退货人员',
	'mData' : 'returner'
},
{
	'display' : '退货日期',
	'mData' : 'returnTime'
},
{
	'display' : '备注',
	'mData' : 'formNote'
},
{
	'display' : '主要退货',
	'mData' : 'maxPayItem'
},
{
	'display' : '退货总额',
	'mData' : 'allPayAmt'
},
{
	'display' : '单据状态',
	'mData' : 'formStatus'
}];
