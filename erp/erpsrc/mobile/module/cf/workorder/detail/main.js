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
	var append = "";
	if (!isEmpty(startDate)) {
		append += "AND TO_CHAR(h.PRODUCTION_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	}
	if (!isEmpty(endDate)) {
		append += "  AND TO_CHAR(h.PRODUCTION_TIME,'YYYY-MM-DD') <= '" + endDate + "' ";
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
								d.reportFlag = "D_T1_WORK_ORDER_DETAIL", d.withOutSum = true
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
},
{
	'mData' : "form_id",
	'display' : "生产工单编号",
	"render" : codeFmt
},
{
	'mData' : "item_id",
	'display' : "商品编码",
},{
	'mData' : "item_name",
	'display' : "商品名称",
},
{
	'mData' : "production_step",
	'display' : "制程顺序号",
},
{
	'mData' : "production_name",
	'display' : "制程",
},{
	'mData' : "production_time",
	'display' : "日期",
},
{
	'mData' : "production_count",
	'display' : "产量",
},
{
	'mData' : "production_man",
	'display' : "生产人员",
},
{
	'mData' : "production_note",
	'display' : "备注",
},
{
	'mData' : "is_completed",
	'display' : "是否完成",
//decorator : function(cellData, rowId, rowIndex) {
//	if('1' == cellData){
//		return '已完成';
//	}
//	return '未完成';
//}
}  ];
