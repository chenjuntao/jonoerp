require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
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
	var _url = appRoot + "/restaurant/preject/manage/queryOutDetail.action?formId=" + formId;
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
	if (field == 'returnCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		row[field] = event.value;// necessary
		row.returnPayAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
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
		label : "订货数",
		field : "requestCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "配送数",
		field : "shippingCount",
		className : 'grid-number',
		sortable : false
	}, editor({
		label : "退货数",
		field : "returnCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3',
				row : this
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), {
		label : "单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "金额",
		field : "returnPayAmt",
		className : 'grid-number',
		sortable : false
	}, editor({
		label : "退货原因",
		field : "returnReason",
		sortable : false
	}, "text"), {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function validateGrid() {
	if (!validateColumn(grid, 'returnCount')) {
		return false;
	}
	return true;
}

function doSave() {
	if (!validateGrid()) {
		return;
	}
	grid.save();
	var rows = [];
	for ( var id in changeIdObj) {// 只获取修改过的记录
		rows.push(dataStore.get(id));
	}
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/restaurant/dreject/manage/doSave.action";

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

function doDelete() {
	var _url = appRoot + "/restaurant/dreject/manage/doDelete.action?formId=" + formId;

	_url = getUrl(_url);
	if (confirm("确定删除单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				alert("删除成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
			});
		});
	}
}

function doClose() {
	closeTab(tabId);
}