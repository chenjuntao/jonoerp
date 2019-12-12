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

function getAppend() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var append = " AND b.BRANCH_TYPE = 'CENTRALFACTORY' ";
	if (!isEmpty(startDate)) {
		append += "AND TO_CHAR(h.loss_time,'YYYY-MM-DD') >= '" + startDate + "' ";
	}
	if (!isEmpty(endDate)) {
		append += "  AND TO_CHAR(h.loss_time,'YYYY-MM-DD') <= '" + endDate + "' ";
	}

	return append;
}
function initTable() {
	var _url = getQueryUrl();
	var startDate = $("#startDate").val();
	tableInst = $('#dataTable').DataTable(
			{
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

					data : function(d) {
						d.append = getAppend(), d.branchType = "CENTRALFACTORY",
								d.reportFlag = "D_T1_LOSS_DETAIL", d.withOutSum = true
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
},{
	'mData' : "loss_time",
	'display' : "报损日期",
},
{
	'mData' : "form_id",
	'display' : "单据编号",
	"render" : codeFmt
},
{
	'mData' : "audit_time",
	'display' : "审核日期",
},{
	'mData' : "loss_branch_id",
	'display' : "门店编码",
},{
	'mData' : "loss_branch",
	'display' : "门店名称",
},{
	'mData' : "storage_id",
	'display' : "仓库编码",
},{
	'mData' : "storage",
	'display' : "仓库名称",
},
{
	'mData' : "item_id",
	'display' : "商品编码",
},
{
	'mData' : "item_name",
	'display' : "商品名称",
},
{
	'mData' : "item_category",
	'display' : "类别编码",
},
{
	'mData' : "category_name",
	'display' : "类别名称",
},
{
	'mData' : "item_dimension",
	'display' : "单位",
},
{
	'mData' : "item_specification",
	'display' : "规格",
},
{
	'mData' : "item_count",
	'display' : "报损数量",
},
{
	'mData' : "unit_price",
	'display' : "标准单价",
},
{
	'mData' : "pay_amt",
	'display' : "报损金额",
},
{
	'mData' : "storage_count",
	'display' : "库存数量",
},
{
	'mData' : "reason",
	'display' : "报损原因",
}];
