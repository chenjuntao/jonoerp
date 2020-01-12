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

		queryData();
	});
}

function queryData() {
	var _url = appRoot + "/restaurant/putinstorage/distribution/queryDetail.action?formId=" + dojo.byId('formId').value;
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
	if (field == 'antiauditReceiveCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		row[field] = event.value;// necessary
		row.antiauditPayAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
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
		className : 'text-center',
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
		label : "配送数",
		field : "shippingCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "实发数",
		field : "deliveryCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "原实收数",
		field : "receiveCount",
		className : 'text-right',
		sortable : false
	}, editor({
		label : "反审核实收数",
		field : "antiauditReceiveCount",
		className : 'text-right',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
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
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "原金额",
		field : "payAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "反审核金额",
		field : "antiauditPayAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "反审核金额",
		field : "antiauditPayAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

/**
 * 根据row进行数据验证示例，修改了dgrid的源代码
 * 
 * @param value
 * @param constraints
 * @returns {Boolean}
 */
function validateCount(value, constraints) {
	var row = this.row;
	if (row != undefined) {
		if (this.parse(value, constraints) == row.itemId) {
			return true;
		}
	}
	return false;
}

function validateGrid() {
	if (!validateColumn(grid, 'antiauditReceiveCount')) {
		return false;
	}
	return true;
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '未处理') {
		alert("不能重复进行反审核！")
		return false;
	}
	return true;
}

function doSubmit() {
	grid.save();
	if (!validateGrid()) {
		return;
	}
	var formId = dojo.byId('formId').value;
	if (!checkStatus(formId)) {
		return;
	}
	var rows = dataStore.query();

	var allSame = true;
	for (var i = 0; i < rows.length; i++) {
		var item = rows[i];
		if (item.antiauditReceiveCount != item.receiveCount) {
			allSame = false;
			break;
		}
	}
	if (allSame) {
		alert('反审核实收数没有任何变化！');
		return;
	}

	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + '/restaurant/antiaudit/create/checkView.action?branchType=' + dojo.byId('branchType').value
			+ "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addPostTab('billForm', '配送反审核录入确认', _url);
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
