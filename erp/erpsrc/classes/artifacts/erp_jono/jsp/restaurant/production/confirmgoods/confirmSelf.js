dojo.ready(function() {
	initGrid();
});

var dataStore = null;
var grid = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard",
			"dojo/on", "dojo/keys", "dojo/query", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(
			OnDemandGrid, editor, NumberTextBox, declare, Keyboard, on, keys, query, ColumnResizer) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, "selfGrid");

		grid.on("dgrid-datachange", dataChangeHandler);

		on(grid, 'keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});

		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});

		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		grid.startup();
		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot + "/restaurant/production/querySelf/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr, Observable, Memory) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "rownumber",
				data : data
			}));
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function dataChangeHandler(event) {
	grid.save();

	var field = event.cell.column.field;
	if (field == 'itemCountActual') {
		var row = dataStore.get(event.rowId);
		row[field] = event.value;
		// row.pay_amt = row[field] * row.unit_price; // 计算金额
		// row.pay_amt = parseFloat(row.pay_amt.toFixed(3)); // 保留三位小数
		dataStore.put(row);
	}
	// changeIdObj[event.rowId] = event.rowId;
}

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "半成品编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "半成品名称",
		field : "itemName",
		sortable : false
	}, {
		label : "例牌",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, editor({
		label : "入库数量",
		field : "itemCountActual",
		editorArgs : {
			style : 'width: 100px;text-align: right;',
			constraints : {
				min : 0,
				max : 99550,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), {
		label : "",
		field : "blank",
		sortable : false
	} ];
}

function validateGrid() {
	if (!validateColumn(grid, 'itemCountActual')) {
		return false;
	}
	return true;
}

function closeSelfTab() {
	closeTab(tabId, "doQuery");
}

function doAudit() {
	// if (!validateGrid()) {
	// return;
	// }

	grid.save();
	var bishRows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(bishRows);
	// dojo.byId('auditBtn').disabled = true;
	var _url = appRoot + "/restaurant/production/confirmSelf/auditCommitView.action?formId=" + formId;
	_url = getUrl(_url);

	addPostTab("billForm", "半成品入库单审核确认", _url);
}
