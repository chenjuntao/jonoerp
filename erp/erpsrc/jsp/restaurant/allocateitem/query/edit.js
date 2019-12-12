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
			"dojo/on", "dojo/keys", "dojo/query", "dojo/domReady!" ], function(OnDemandGrid, editor, declare, Keyboard,
			NumberTextBox, on, keys, query) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			loadingMessage : '加载中...'
		}, "dataGrid ");
		grid.on("dgrid-datachange", function(event) {
			grid.save();

			changeIdObj[event.rowId] = event.rowId;

			var field = event.cell.column.field;
			if (field == 'applyCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				row.payAmt = reserve2Decimal(event.value * row.unitPrice); // 计算金额
				dataStore.put(row);
			}
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
	}, editor({
		label : "申请调拨数量",
		field : "applyCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 100px;	text-align: right;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), {
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
	if (!validateColumn(grid, 'applyCount')) {
		return false;
	}
	return true;
}

var numReg = /^(-)?[0-9]{1,}\.{0,1}[0-9]{0,}$/g;
function validateNum(_id, _text) {
	var value = dojo.byId(_id).value.trim();
	if (value != '' && value.match(numReg) == null) {
		alert(_text + "只能为数字！");
		return false;
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

function reserve2Decimal(number) {
	return Math.round(number * 100) / 100;
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
	var rows = [];
	for ( var id in changeIdObj) {// 只获取修改过的记录
		rows.push(dataStore.get(id));
	}
	// var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	if (rows.length > 0) { // 如果有数据修改，则重新计算要货总额和主要要货
		calcAllPayAmt();
	}
	var _url = appRoot + "/restaurant/allocateitem/query/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("修改成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId, "doQuery");
}

function doDelete() {
	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
		return;
	}

	var _url = appRoot + "/restaurant/allocateitem/query/doDelete.action?formId=" + formId;
	_url = getUrl(_url);
	
	if (confirm("确定删除单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				alert("删除成功！");
				closeTab(tabId, "doQuery");
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
					cancelMsg : "作废调拨单"
				}
			}).then(function(data) {
				alert("操作成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
			});
		});
	}
}
