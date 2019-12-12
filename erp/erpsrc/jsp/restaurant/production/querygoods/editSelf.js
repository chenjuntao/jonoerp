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

function doClose() {
	closeTab(tabId);
}

function doDelete() {
	var _url = appRoot + "/restaurant/production/querySelf/doDelete.action?formId=" + formId;
	_url = getUrl(_url);

	if (confirm("确定删除单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				alert("删除成功！");
				closeSelfTab();
			}, function(err) {
			});
		});
	}
}

function doInvalidate() {
	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
		return;
	}

	var _url = appRoot + "/restaurant/goodsbill/query/doInvalid.action";
	_url = getUrl(_url);

	if (confirm("确定作废单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					formId : formId,
					cancelMsg : "作废半成品入库单"
				}
			}).then(function(data) {
				alert("操作成功！");
				closeSelfTab();
			}, function(err) {
			});
		});
	}
}

function doSave() {
	if (!validateGrid()) {
		return;
	}

	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);

	var _url = appRoot + "/restaurant/production/querySelf/doSave.action?formId=" + formId + "&branchType="
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("修改成功！");
				standby.hide();
				closeSelfTab();
			} else {
				standby.hide();
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
