dojo.ready(function() {
	initGrid();
});

var grid = null;
var dataStore = null;
function initGrid() {
	var _url = appRoot + "/restaurant/putinstorage/outside/queryDetail.action?formId=" + formId + "&hasSum=true";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/parser", "dijit/form/Select", "dijit/form/Button", "dijit/Dialog",
			"dojo/domReady!" ], function(OnDemandGrid, Server, declare, Keyboard, ColumnResizer, parser) {
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

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	fillData(grid.get('store').getData());
}

function customPreview() {
	var times = dojo.byId('l_times').innerHTML;
	if (times.length == 0) {
		dojo.byId('l_times').innerHTML = '1';
	} else {
		times = parseInt(dojo.byId('l_times').innerHTML) + 1;
		dojo.byId('l_times').innerHTML = times;
	}
	// var formId = dojo.byId('formId').value;
	var _url = appRoot + "/lc/request/purchase/manage/preview.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/query" ], function(xhr, Memory, query) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			prn1_preview(grid.get('columns'), grid.get('store').data);
		}, function(err) {
		});
	});
}

function customPrint() {
	var times = dojo.byId('l_times').innerHTML;
	if (times.length == 0) {
		dojo.byId('l_times').innerHTML = '1';
	} else {
		times = parseInt(dojo.byId('l_times').innerHTML) + 1;
		dojo.byId('l_times').innerHTML = times;
	}
	// var formId = dojo.byId('formId').value;
	var _url = appRoot + "/lc/request/purchase/manage/preview.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/query" ], function(xhr, Memory, query) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			prn1_print(grid.get('columns'), grid.get('store').data);
		}, function(err) {
		});
	});
}

function customPrintDesign() {
	console.dir(grid.get('store').data);
	myDesign(grid.get('columns'), grid.get('store').data);
}
function doClose() {
	closeTab(tabId);
}
function getColumn() {
	var branchType = dojo.byId('branchType').value;
	var branchFlag = dojo.byId('branchFlag').value;
	if (!isEmpty(branchFlag)) {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "原料编码",
			field : "itemId",
			sortable : false
		}, {
			label : "原料名称",
			field : "itemName",
			sortable : false
		}, {
			label : "类别",
			field : "itemCategory",
			sortable : false
		}, {
			label : "单位",
			field : "itemDimension",
			sortable : false
		}, {
			label : "规格",
			field : "itemSpecification",
			sortable : false
		}, {
			label : "采购数量",
			field : "orderCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "已入库数量",
			field : "receivedCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "实收数量",
			field : "receiveCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "实收差异",
			field : "differentCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "进货单价",
			field : "receivePrice",
			className : 'grid-number',
			sortable : false
		}, {
			label : "进货金额",
			field : "receiveAmt",
			className : 'grid-number',
			sortable : false
		} ];
	} else if (branchType != 'RESTAURANT') {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "原料编码",
			field : "itemId",
			sortable : false
		}, {
			label : "原料名称",
			field : "itemName",
			sortable : false
		}, {
			label : "类别",
			field : "itemCategory",
			sortable : false
		}, {
			label : "单位",
			field : "itemDimension",
			sortable : false
		}, {
			label : "规格",
			field : "itemSpecification",
			sortable : false
		}, {
			label : "采购数量",
			field : "orderCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "已入库数量",
			field : "receivedCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "实收数量",
			field : "receiveCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "实收差异",
			field : "differentCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "标准单价",
			field : "itemUnitPrice",
			className : 'grid-number',
			sortable : false
		}, {
			label : "标准金额",
			field : "payAmt",
			className : 'grid-number',
			sortable : false
		}, {
			label : "进货单价",
			field : "receivePrice",
			className : 'grid-number',
			sortable : false
		}, {
			label : "进货金额",
			field : "receiveAmt",
			className : 'grid-number',
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
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "采购数量",
		field : "orderCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "已入库数量",
		field : "receivedCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "实收数量",
		field : "receiveCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "实收差异",
		field : "differentCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	} ];
}
