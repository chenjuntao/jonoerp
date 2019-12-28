dojo.ready(function() {
	initGrid();
});

var dataStore = null;
var grid = null;
var changeIdObj = {};

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query", "dojo/domReady!" ], function(
			OnDemandGrid, editor, NumberTextBox, declare, Keyboard, ColumnResizer, on, keys, query) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, "confirmLossGrid");

		grid.on("dgrid-datachange", dataChangeHandler);

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

			calcLossMoney();

		}, function(err) {
		});
	});
}

function calcLossMoney() {
	var sum = 0;
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(dataStore.query(), function(item) {
			sum += Number(item.pay_amt);
		});

		dojo.byId("lossMoney").innerHTML = sum.toFixed(2);
	});
}

function dataChangeHandler(event) {
	grid.save();

	var field = event.cell.column.field;
	if (field == 'item_count') {
		var row = dataStore.get(event.rowId);
		row[field] = event.value;

		row.pay_amt = row[field] * row.unit_price; // 计算金额
		dataStore.put(row);

		calcLossMoney();
	}
	changeIdObj[event.rowId] = event.rowId;
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
		editorArgs : {
			style : 'width: 70px;text-align: right;',
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

/**
 * 保存之前计算要货总额和主要要货
 */
function calcAllPayAmt() {
	var rows = dataStore.query();
	var allPayAmt = 0.0, maxPayAmt = 0.0;
	var maxPayItem = "";
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(rows, function(item, i) {
			var payAmt = item.pay_amt;
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + item.item_id + "]" + item.item_name;
			}
		});
	});
	dojo.byId('allPayAmt').value = allPayAmt;
	dojo.byId('maxPayItem').value = maxPayItem;
}

function validateGrid() {
	if (!validateColumn(grid, 'item_count')) {
		return false;
	}
	return true;
}

function doAudit() {
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
	var rows = [];
	for ( var id in changeIdObj) {// 只获取修改过的记录
		rows.push(dataStore.get(id));
	}

	dojo.byId('jsonData').value = JSON.stringify(rows);
	if (rows.length > 0) { // 如果有数据修改，则重新计算要货总额和主要要货
		calcAllPayAmt();
	}
	dojo.byId('auditBtn').disabled = true;
	var _url = appRoot + "/restaurant/reportdamage/confirmloss/doAudit.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("confirmLossForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("审核成功！");
				closeTab(tabId, "doQuery");
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}
