dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

var dataStore = null;
var grid = null;
var changeIdObj = {};
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dijit/form/NumberTextBox", "dojo/_base/lang", "dojo/on", "dojo/keys",
			"dojo/query", "dojo/domReady!" ], function(OnDemandGrid, editor, declare, Keyboard, ColumnResizer,
			NumberTextBox, lang, on, keys, query) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox, lang),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.on("dgrid-datachange", function(event) {
			changeIdObj[event.rowId] = event.rowId;

			var field = event.cell.column.field;
			if (field == 'orderCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
				dataStore.put(row);
			}
		});
		grid.startup();

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
			if (e.parentType != undefined) { // 鼠标事件不予处理
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
	var _url = appRoot + "/restaurant/goodsbill/query/queryDetail.action?formId=" + formId;
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

function getColumn(editor, NumberTextBox, lang) {
	var numArgs = {
		style : 'width: 5em;text-align: right;',
		constraints : {
			min : 0,
			max : 1999999999,
			places : '0,3'
		},
		required : true,
		invalidMessage : '请输入不多于三位小数的数字。'
	};

	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox);
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
		label : "库存量",
		field : "inventory",
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "订货量",
		field : "orderCount",
		sortable : false
	}), {
		label : "零售单价",
		field : "itemUnitPrice",
		sortable : false
	}, {
		label : "零售金额",
		field : "payAmt",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function doValidate() {
	var auditTime = dojo.byId('auditTime').value;
	if (auditTime.trim() == '') {
		alert("审核日期不能为空！");
		return;
	}
	return true;
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
			var payAmt = item.payAmt;
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + item.itemId + "]" + item.itemName;
			}
		});
	});
	dojo.byId('allPayAmt').value = allPayAmt;
	dojo.byId('maxPayItem').value = maxPayItem;
}

function doAudit() {
	if (!doValidate()) {
		return;
	}

	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
		return;
	}

	grid.save();
	// var rows = [];
	// for ( var id in changeIdObj) {// 只获取修改过的记录
	// rows.push(dataStore.get(id));
	// }
	var rows = dataStore.query();// 后台重新计算要货总额和主要要货
	var rowsSave = [];
	if (rows.length > 0) {
		var zeroItem = [];
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].orderCount == 0) {
				zeroItem.push(rows[i].itemName);
			} else {
				rows[i].payAmt = parseFloat((rows[i].orderCount * rows[i].itemUnitPrice).toFixed(4)); // 计算金额
				rowsSave.push(rows[i]);
			}
		}
		if (rowsSave.length == 0) {
			alert("不允许所有物料的订货量为零！");
			return;
		}
		if (zeroItem.length > 0) {
			if (!confirm(zeroItem.join('、') + "输入为零，确定提交吗？")) {
				return;
			}
		}
	}

	dojo.byId('jsonData').value = JSON.stringify(rows);
	// if (rows.length > 0) { // 如果有数据修改，则重新计算要货总额和主要要货
	// calcAllPayAmt();
	// }
	dojo.byId('btn_submit').disabled = true;
	var _url = appRoot + "/restaurant/goodsbill/confirm/doAudit.action";
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
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
