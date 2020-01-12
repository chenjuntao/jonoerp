dojo.ready(function() {
	initGrid();
});

var dataStore = null;
var grid = null;
var changeIdObj = {};

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard",
			"dojo/on", "dojo/keys", "dojo/query", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(
			OnDemandGrid, editor, NumberTextBox, declare, Keyboard, on, keys, query, ColumnResizer) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, "confirmLossGrid");

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
	var _url = appRoot + "/restaurant/reportdamage/querydishloss/queryDish.action?formId=" + formId;
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
	if (field == 'item_count') {
		var row = dataStore.get(event.rowId);
		row[field] = event.value;
		row.pay_amt = row[field] * row.unit_price; // 计算金额
		row.pay_amt = parseFloat(row.pay_amt.toFixed(3)); // 保留三位小数
		dataStore.put(row);
	}
	changeIdObj[event.rowId] = event.rowId;
}

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "出品编码",
		field : "item_id",
		className : 'text-center',
		sortable : false
	}, {
		label : "出品名称",
		field : "item_name",
		sortable : false
	}, {
		label : "例牌",
		field : "item_dimension",
		className : 'text-center',
		sortable : false
	}, editor({
		label : "报损数量",
		field : "item_count",
		editorArgs : {
			style : 'width: 75px;text-align: right;',
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
	}, editor({
		label : "报损原因",
		field : "reason",
		editor : "text",
		sortable : false
	}), {
		label : "",
		field : "blank",
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

function closeSelfTab() {
	closeTab(tabId, "doQuery");
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

	var doshRows = dataStore.query();
	dojo.byId('dishJsonData').value = JSON.stringify(doshRows);

	dojo.byId('auditBtn').disabled = true;
	var _url = appRoot + "/restaurant/reportdamage/confirmdishloss/auditCommitView.action?formId=" + formId
			+ "&branchType=" + dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	addPostTab("confirmLossForm", brandWord + "出品报损单审核确认", _url);
}
