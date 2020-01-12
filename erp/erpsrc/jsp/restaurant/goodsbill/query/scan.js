require([ "dojo/ready", "dojo/parser", "dijit/form/Select", "dijit/form/Button", "dijit/Dialog" ], function(ready,
		parser) {
	ready(function() {
		parser.parse();
		initGrid();
	});
});

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	fillData(grid.get('store').data);
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/goodsbill/query/queryDetail.action?formId=" + formId + "&hasSum=true";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/request/xhr", "dojo/_base/declare", "dgrid/extensions/ColumnResizer",
			"dgrid/ColumnSet", "dgrid/extensions/Pagination", "dojo/store/Memory", "dojo/domReady!" ], function(
			OnDemandGrid, xhr, declare, ColumnResizer, ColumnSet, Pagination, Memory) {
		xhr.post(_url, {
			handleAs : "json",
		}).then(function(data) {
			var CustomGrid = declare([ OnDemandGrid, ColumnResizer, ColumnSet, Pagination ]);
			grid = new CustomGrid({
				store : new Memory({
					data : data,
					idProperty : 'rownumber'
				}),
				columnSets : cols,
				cellNavigation : false,
				loadingMessage : '加载中...'
			}, "dataGrid");

			grid.startup();
		}, function(err) {
			console.log(err);
		});

	});
}

function doClose() {
	closeTab(tabId);
}

var cols = [ [ [ {
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
	label : "原料编码",
	field : "itemId",
	className : 'text-center',
	sortable : false
}, {
	label : "原料名称",
	field : "itemName",
	sortable : false
} ] ], [ [ {
	label : "库存量",
	field : "inventory",
	className : 'text-right',
	sortable : false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable : false
}, {
	label : "订货量",
	field : "orderCount",
	className : 'text-right',
	sortable : false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}

		return field;
	}
}, {
	label : "单位",
	field : "itemDimension",
	sortable : false
}, {
	label : "类别",
	field : "itemCategory",
	sortable : false
}, {
	label : "单价",
	field : "itemUnitPrice",
	className : 'text-right',
	sortable : false
}, {
	label : "金额",
	field : "payAmt",
	className : 'text-right',
	sortable : false,
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}

		return field;
	}
}, {
	label : "建议1",
	field : "suggestCount1",
	className : 'text-right',
	sortable : false
}, {
	label : "建议2",
	field : "suggestCount2",
	className : 'text-right',
	sortable : false
}, {
	label : "建议3",
	field : "suggestCount3",
	className : 'text-right',
	sortable : false
}, {
	label : "需求1",
	field : "requireCount1",
	className : 'text-right',
	sortable : false
}, {
	label : "需求2",
	field : "requireCount2",
	className : 'text-right',
	sortable : false
}, {
	label : "需求3",
	field : "requireCount3",
	className : 'text-right',
	sortable : false
}, {
	label : "万用1",
	field : "amtTTCNY1",
	className : 'text-right',
	sortable : false
}, {
	label : "万用2",
	field : "amtTTCNY2",
	className : 'text-right',
	sortable : false
}, {
	label : "万用3",
	field : "amtTTCNY3",
	className : 'text-right',
	sortable : false
}, {
	label : "",
	field : "none",
	sortable : false
} ] ] ];
