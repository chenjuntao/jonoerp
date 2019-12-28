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
var dataStore = null;
function initGrid() {
	var _url = appRoot + "/restaurant/costcard/doQuery.action?foodId=" + g_foodId+"&hasSum=Y";
	
	require([ 
	          "custom/store/Server",
	          "dojo/_base/declare",
	          "dgrid/OnDemandGrid", 
	          "dgrid/Keyboard",
	          "dojo/parser",
	          "dgrid/extensions/ColumnResizer",
	          "dgrid/ColumnSet"
          ],function(Server,declare, OnDemandGrid, Keyboard,parser,ColumnResizer,ColumnSet) {
		parser.parse();
		dataStore = new Server({
			target : _url
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer,ColumnSet]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on("dgrid-refresh-complete", function(evt) {
			initTree();
		});

		grid.startup();
	});
}

var cols = [[ [ {
	label : "序号",
	field : "rownumber",
	sortable : false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "编码",
	field : "itemId",
	sortable : false
}, {
	label : "名称",
	field : "itemName",
	sortable : false
}] ], [ [  {
	label : "是否存在",
	field : "existFlag",
	sortable : false
},{
	label : "库存单位",
	field : "stockDimension",
	sortable : false
}, {
	label : "配方单位",
	field : "itemDimension",
	sortable : false
}, {
	label : "转换因子",
	field : "unitConvertFactor",
	sortable : false
}, {
	label : "净料耗量(配方单位)",
	field : "itemCount",
	sortable : false
}, {
	label : "净耗料率(%)",
	field : "itemUsageRate",
	sortable : false
}, {
	label : "毛耗料量(配方单位)",
	field : "itemGrossCount",
	sortable : false
}, {
	label : "进货价(库存单位)",
	field : "purchasePrice",
	sortable : false
}, {
	label : "配方金额(进货价)",
	field : "purchaseAmt",
	sortable : false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "标准价(库存单位)",
	field : "benchmarkPrice",
	sortable : false
}, {
	label : "配方金额(标准价)",
	field : "benchmarkAmt",
	sortable : false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		
		return field;
	}
}, {
	label : "",
	field : "blank",
	sortable : false
} ] ] ];
