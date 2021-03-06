dojo.ready(function() {
	initGrid();
});

function showDialog(){
	dijit.byId('customDialog').show();
}

function hideDialog(){
	dijit.byId('customDialog').hide();
}

function customExport(){
	fillData(grid.get('store').getData());
}

function doClose() {
	closeTab(tabId);
}

var grid = null;
var myStore = null;

function initGrid() {
	require([ 
	          "dgrid/OnDemandGrid", 
	          "custom/store/Server",
	          "dojo/_base/declare", 
	          "dgrid/Keyboard", 
	          "dgrid/extensions/ColumnResizer" ,
	          "dojo/parser",
	          "dojo/domReady!" 
          ],function(OnDemandGrid, Server, declare, Keyboard,ColumnResizer,parser) {
		parser.parse();
		
		var _url = appRoot+ "/restaurant/reportdamage/queryloss/queryRawDetail.action?formId="	+ formId+"&hasSum=Y";
		_url = getUrl(_url);
		
		myStore = new Server({
			target : _url,
			idProperty: "rownumber"
		});
		 
		var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer]);
		grid = new CustomGrid({
			store : myStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "showLossGrid");

		grid.startup();
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable:false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		return field;
	}
},{
	label : "原料编码",
	field : "item_id",
	className:'text-center',
	sortable:false
}, {
	label : "原料名称",
	field : "item_name",
	sortable:false
}, {
	label : "单位",
	field : "item_dimension",
	className:'text-center',
	sortable:false
}, {
	label : "规格",
	field : "item_specification",
	sortable:false
}, {
	label : "类别",
	field : "item_category",
	sortable:false
}, {
	label : "报损数量",
	field : "item_count",
	className:'text-right',
	sortable:false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "标准单价",
	field : "unit_price",
	className:'text-right',
	sortable:false
}, {
	label : "标准金额",
	field : "pay_amt",
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		return field;
	},
	sortable:false
}, {
	label : "库存数量",
	field : "storage_count",
	className:'text-right',
	sortable:false
}
, {
	label : "报损原因",
	field : "reason",
	sortable:false
} ,{
	label : "",
	field : "none",
	sortable:false
} ];
