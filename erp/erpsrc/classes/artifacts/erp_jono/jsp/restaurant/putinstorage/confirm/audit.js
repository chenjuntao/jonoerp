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
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query", "dojo/domReady!" ], function(
			OnDemandGrid, editor, NumberTextBox, declare, Keyboard, ColumnResizer, on, keys, query) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			loadingMessage : '加载中...'
		}, "dataGrid");

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

		grid.startup();
		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot + "/restaurant/putinstorage/outside/queryDetail.action?formId=" + formId;
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
	var field = event.cell.column.field;
	if (field == 'receiveCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		row[field] = event.value;
		row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		row.receiveAmt = parseFloat((event.value * row.receivePrice).toFixed(2)); // 计算金额
		row.differentCount = row.orderCount - (event.value + row.receivedCount); // 计算实收差异
		dataStore.put(row);
	}
	changeIdObj[event.rowId] = event.rowId;
}

function getColumn(editor, NumberTextBox) {
	var branchType = dojo.byId('branchType').value;
	if (branchType != "RESTAURANT") {
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
			label : "要货数量",
			field : "orderCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "已入库数量",
			field : "receivedCount",
			className : 'grid-number',
			sortable : false
		}, editor({
			label : "实收数量",
			field : "receiveCount",
			className : 'grid-number',
			editorArgs : {
				style : 'width: 5em;text-align: right;',
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
			label : "实收差异",
			field : "differentCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "超收率",
			field : "outReceiveRate",
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
		label : "要货数量",
		field : "orderCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "已入库数量",
		field : "receivedCount",
		className : 'grid-number',
		sortable : false
	}, editor({
		label : "实收数量",
		field : "receiveCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
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
		label : "实收差异",
		field : "differentCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "超收率",
		field : "outReceiveRate",
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
	// }, {
	// label : "进货单价",
	// field : "receivePrice",
	// className : 'grid-number',
	// sortable : false
	// }, {
	// label : "进货金额",
	// field : "receiveAmt",
	// className : 'grid-number',
	// sortable : false
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

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;

	if (status == '已结案') {
		alert("采购单已结案，该入库单不能进行入库！");
		return false;
	}
	return true;
}

function validateGrid() {
	if (!validateColumn(grid, 'receiveCount')) {
		return false;
	}
	return true;
}

function doAudit() {
	if (!validateGrid()) {
		return;
	}
	if (!checkStatus(dojo.byId('formRefId').value)) {
		return;
	}

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
	var rows = dataStore.query(); // 审核通过之后，进行扣库，库存表中增加实收数，所以必须传递所有记录
	dojo.byId('jsonData').value = JSON.stringify(rows);

	if (rows.length > 0) {
		calcAllPayAmt();// 如果有数据修改，则重新计算要货总额和主要要货
		for (var i = 0; i < rows.length; i++) {
			var item = rows[i];
			if ((item.receiveCount + item.receivedCount) > item.orderCount * item.outReceiveRate) {
				alert(item.itemName + "入库数不能超过限制比例！");
				return;
			}
		}
	}
	dojo.byId('auditBtn').disabled = true;
	var _url = appRoot + "/restaurant/putinstorage/confirm/doAudit.action";
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
