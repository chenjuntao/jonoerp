dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
}

var dataStore = null;
var grid = null;
var changeIdObj = {};
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dojo/_base/declare", "dgrid/Keyboard", "dijit/form/NumberTextBox",
			"dojo/on", "dojo/keys", "dojo/query", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(
			OnDemandGrid, editor, declare, Keyboard, NumberTextBox, on, keys, query, ColumnResizer) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "showLossGrid");
		grid.startup();

		grid.on("dgrid-datachange", function(event) {
			grid.save();

			var field = event.cell.column.field;
			if (field == 'item_count') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				row.pay_amt = parseFloat((event.value * row.unit_price).toFixed(2)); // 计算金额
				dataStore.put(row);
			}
			changeIdObj[event.rowId] = event.rowId;
		});

		// TODO
		on(grid, 'keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});
		// 保证鼠标焦点与键盘焦点的连贯性
		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});

		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot + "/restaurant/reportdamage/queryloss/queryRawDetail.action?formId=" + formId;
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

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "item_id",
		className : 'text-center',
		sortable : false
	}, {
		label : "原料名称",
		field : "item_name",
		sortable : false
	}, {
		label : "单位",
		field : "item_dimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "item_specification",
		sortable : false
	}, {
		label : "类别",
		field : "item_category",
		sortable : false
	}, editor({
		label : "报损数量",
		field : "item_count",
		className : 'text-right',
		sortable : false,
		editorArgs : {
			style : 'width: 70px;text-align: right;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		}
	}, NumberTextBox), {
		label : "标准单价",
		field : "unit_price",
		className : 'text-right',
		sortable : false
	}, {
		label : "标准金额",
		field : "pay_amt",
		className : 'text-right',
		sortable : false
	}, {
		label : "库存数量",
		field : "storage_count",
		className : 'text-right',
		sortable : false
	}, editor({
		label : "报损原因",
		field : "reason",
		editor : "text",
		sortable : false
	}), {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function doDelete() {
	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
		return;
	}

	var _url = appRoot + "/restaurant/reportdamage/queryloss/doDelete.action?formId=" + formId;
	_url = getUrl(_url);
	
	if (confirm("确定删除单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json",
				data : {
					currentVersion : dojo.byId('currentVersion').value,
					formId : formId
				}
			}).then(function(data) {
				alert("刪除成功！");
				closeTab(tabId, 'doQuery');
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
					currentVersion : dojo.byId('currentVersion').value,
					formId : formId,
					cancelMsg : "作废原料报损单"
				}
			}).then(function(data) {
				alert("操作成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
			});
		});
	}
}

function validateGrid() {
	if (!validateColumn(grid, 'item_count')) {
		return false;
	}
	return true;
}

function doSave() {
	if (!validateGrid()) {
		return;
	}

	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
		return;
	}
	grid.save();
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/restaurant/reportdamage/queryloss/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("提交成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId);
}
