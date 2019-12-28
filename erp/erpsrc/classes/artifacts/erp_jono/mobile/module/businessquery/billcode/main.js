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
	var params = "?startDate=" + $("#startDate").val() + "&endDate="
			+ $("#endDate").val() + "&billCode=" + $("#billCode").val()
			+ "&shopC=" + $("#shopC").val();
	return appRoot + "/m/businessquery/billCodeQuery/listBill.action" + params;
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
		// ,
		// data : {
		// startDate : $('#startDate').val(),
		// endDate : $('#endDate').val(),
		// billCode : $('#billCode').val(),
		// shopC : $('#shopC').val()
		// }
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

var cols = [ {
	'display' : '单据编号',
	"mData" : "billC",
	"render" : codeFmt
}, {
	'display' : '市别',// 仅用作字段匹配时方便，自定义的属性
	"mData" : "period"
}, {
	'display' : '班次',
	"mData" : "shift"
}, {
	'display' : '人数',
	"mData" : "guestNum"
}, {
	'display' : '开台时间',
	"mData" : "billTime"
}, {
	'display' : '结账时间',
	"mData" : "settleTime"
}, {
	'display' : '开台人',
	"mData" : "createMan"
}, {
	'display' : '结账人',
	"mData" : "settleMan"
}, {
	'display' : '消费金额',
	"mData" : "foodAmt"
}, {
	'display' : '舍尾金额',
	"mData" : "roundAmt"
}, {
	'display' : '赠送金额',
	"mData" : "presentAmt"
}, {
	'display' : '付款金额',
	"mData" : "payAmt"
}, {
	'display' : '总折扣额',
	"mData" : "disAmt"
} ];

function gotoURL(_billC) {
	var _url = appRoot + "/jsp/businessquery/foodlist.action?billC=" + _billC;
	window.open(_url);
}