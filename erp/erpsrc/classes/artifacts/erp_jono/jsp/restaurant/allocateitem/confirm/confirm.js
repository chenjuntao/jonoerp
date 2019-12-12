dojo.ready(function() {
	initGrid();
});

var dataStore = null;
var grid = null;
var changeIdObj = {};
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dojo/_base/declare", "dgrid/Keyboard", "dijit/form/NumberTextBox",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, editor, declare, Keyboard,
			NumberTextBox, ColumnResizer) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "confirmGrid");

		grid.on("dgrid-datachange", function(event) {
			changeIdObj[event.rowId] = event.rowId;

			var field = event.cell.column.field;
			if (field == 'actualCount') {
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				row.differentCount = row.applyCount - row.actualCount; // 计算调拨差异
				dataStore.put(row);
				grid.set("store", dataStore);
			}
		});
		grid.startup();
		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot + "/restaurant/allocateitem/query/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr, Observable, Memory) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "itemId",
				data : data
			}));
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn(editor, NumberTextBox) {
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
		label : "调拨数量",
		field : "applyCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准单价",
		field : "unitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function validateGrid() {
	var store = grid.store;
	var cols = grid.columns;
	var _colId = '';
	for (key in cols) {
		var col = cols[key];
		if (col.field == 'actualCount') {
			_colId = key;
			break;
		}
	}
	var rows = dataStore.query();
	for (var i = 0; i < rows.length; i++) {
		var data = rows[i];
		var rowId = grid.store.getIdentity(data);
		var cell = grid.cell(rowId, _colId);
		if (isEmpty(cell.element)) {
			continue;
		}
		var valid = cell.element.widget.isValid();
		if (!valid) {
			alert('输入有误！');
			return false;
		}
	}
	return true;
}

function isValidDate() {
	var _url = appRoot + "/restaurant/allocateitem/confirm/isValidDate.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				confirmTime : dojo.byId("confirmTime").value
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				return true;
			} else {
				return false;
			}
		}, function(err) {
		});
	});
}

function doConfirm() {
	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
		return;
	}

	var confirmTime = dojo.byId("confirmTime").value;
	if (!confirmTime) {
		alert("请选择确认日期！");
		return;
	}
	dojo.byId('btn_submit').disabled = true;
	grid.save();

	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);

	var _url = appRoot + "/restaurant/allocateitem/confirm/doConfirm.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("审核成功！");
				doClose();
			} else {
				alert("审核失败！");
			}
		}, function(err) {
			alert("审核失败！");
		});
	});
}

function doClose() {
	closeTab(tabId, "doQuery");
}
