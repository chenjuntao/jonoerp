require([ "dojo/domReady!" ], function() {
	addEvent();
	initGrid();
});

function addEvent() {
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query" ], function(OnDemandGrid, declare,
			Keyboard, editor, NumberTextBox, ColumnResizer, on, keys, query) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
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

		queryData();
	});
}

function queryData() {
	var _url = appRoot + "/restaurant/putinstorage/outside/queryReturnDetail.action?formId=" + formId;
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
		label : "实收数",
		field : "receiveCount",
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
	}, "text") ];
}

function validateGrid() {
	if (!validateColumn(grid, 'returnCount')) {
		return false;
	}
	return true;
}

function doClose() {
	closeTab(tabId, "doQuery");
}

function doSubmit() {
	grid.save();
	if (!validateGrid()) {
		return;
	}
	var formId = dojo.byId('formId').value;
	var rows = dataStore.query();

	var allBlank = true;
	for (var i = 0; i < rows.length; i++) {
		var item = rows[i];
		if (item.returnCount > 0) {
			allBlank = false;
		}
		// if (item.returnCount > item.receiveCount) {
		// alert(item.itemName + '退货数不能大于实收数！');
		// return;
		// }
	}
	if (allBlank) {
		alert('退货数不能都为零！');
		return;
	}

	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + '/restaurant/preject/create/checkView.action?branchType=' + dojo.byId('branchType').value
			+ "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addPostTab('billForm', '采购退货单生成确认', _url);
}
