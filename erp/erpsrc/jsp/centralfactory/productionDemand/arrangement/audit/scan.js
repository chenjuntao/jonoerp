dojo.ready(function() {
	initGrid();
});

function customExport() {
	fillData(grid.get('store').data);
}

function print() {
	// prn1_print();
	prn1_preview();
}

function customPrint() {
	rows = grid.get('store').data;
	rows.sort(function(a, b) {
		return b.productionCycle - a.productionCycle;
	});
	var lastRow = {
		productionCycle : 1000
	};
	rows.push(lastRow);
	var oldCycle = -1.0;
	var newRows = [];
	var length = rows.length;
	for (var i = 0; i < length - 1; i++) {
		var row = rows[i];
		var nextRow = rows[i + 1];
		if (row.productionCycle != oldCycle) {
			oldCycle = row.productionCycle;
			newRows = [];
		}
		newRows.push(row);
		if (row.productionCycle != nextRow.productionCycle) {
			prn1_print(grid.get('columns'), newRows);
		}
	}
}

function directPrint() {
	rows = grid.get('store').data;
	prn1_print(grid.get('columns'), rows);
}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

function customPrint1() {
	var times = dojo.byId('l_times').innerHTML;
	if (times.length == 0) {
		dojo.byId('l_times').innerHTML = '1';
	} else {
		times = parseInt(dojo.byId('l_times').innerHTML) + 1;
		dojo.byId('l_times').innerHTML = times;
	}
	var formId = dojo.byId('formId').value;
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

var dataStore = null;
var grid = null;
var changeIdObj = {};
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/extensions/ColumnResizer",
			"dgrid/ColumnSet", "dojo/parser", "dojo/domReady!" ], function(OnDemandGrid, declare, Keyboard,
			ColumnResizer, ColumnSet, parser) {
		parser.parse();
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "scanDataGrid");

		grid.startup();
		loadGridData();
	});
}

var workshop = "";
function loadGridData() {
	var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr, Observable, Memory) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "rownumber",
				data : data
			}));
			workshop = data[0].workshop;
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn(editor) {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "商品编码",
		field : "itemId",
		sortable : false
	}, {
		label : "商品名称",
		field : "itemName",
		sortable : false
	} ] ], [ [ {
		label : "单位",
		field : "itemDimension2",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "生产周期",
		field : "productionCycle",
		sortable : false
	}, {
		label : "主库存数量(物)",
		field : "storageCount",
		sortable : false
	}, {
		label : "计划生产量",
		field : "produceCount2",
		sortable : false
	}, {
		label : "主原材料",
		field : "mainName",
		sortable : false
	}, {
		label : "主料计划领料数",
		field : "grossCount",
		options : {
			precision : 2
		},
		sortable : false
	}, {
		label : "工单编号",
		field : "workOrderId",
		sortable : false
	}, {
		label : "生产车间",
		field : "workshop",
		sortable : false
	}, {
		label : "生产日期",
		field : "produceTime",
		sortable : false
	}, {
		label : "完工日期",
		field : "completeTime",
		sortable : false
	}, {
		label : "备注",
		field : "note",
		sortable : false
	} ] ] ];
}

function doClose() {
	closeTab(tabId);
}
