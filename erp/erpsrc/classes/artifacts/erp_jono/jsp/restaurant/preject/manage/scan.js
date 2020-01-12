require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
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
	fillData(grid.get('store').getData());
}
function doClose() {
	closeTab(tabId);
}

var grid = null;
var dataStore = null;

function initGrid() {
	var _url = appRoot + "/restaurant/preject/manage/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/parser", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, Server, parser, declare,
			Keyboard, ColumnResizer) {
		parser.parse();
		dataStore = new Server({
			target : _url,
			idProperty : 'rownumber'
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	var branchFlag = dojo.byId('branchFlag').value;
	if (!isEmpty(branchFlag)) {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "原料编码",
			field : "itemId",
			className : 'text-center',
			sortable : false
		}, {
			label : "原料名称",
			field : "itemName",
			sortable : false
		}, {
			label : "单位",
			field : "itemDimension",
			className : 'text-center',
			sortable : false
		}, {
			label : "规格",
			field : "itemSpecification",
			sortable : false
		}, {
			label : "要货数量",
			field : "orderCount",
			className : 'text-right',
			sortable : false
		}, {
			label : "实收数",
			field : "receiveCount",
			className : 'text-right',
			sortable : false
		}, {
			label : "退货数",
			field : "returnCount",
			className : 'text-right',
			sortable : false
		}, {
			label : "退货原因",
			field : "returnReason",
			sortable : false
		} ];
	}
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "要货数量",
		field : "orderCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "实收数",
		field : "receiveCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "退货数",
		field : "returnCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "单价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "金额",
		field : "returnPayAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "退货原因",
		field : "returnReason",
		sortable : false
	} ];
}
