require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
}

var grid = null;
var dataStore = null;
var reasonStore = null;

function initGrid() {
	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore", "dgrid/OnDemandGrid",
			"dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox", "dijit/form/Select",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query" ],
			function(Observable, Memory, ObjectStore, OnDemandGrid, declare, Keyboard, editor, NumberTextBox, Select,
					ColumnResizer, on, keys, query) {
				dataStore = new Observable(new Memory({
					idProperty : "rownumber",
					data : []
				}));

				var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
				grid = new CustomGrid({
					store : dataStore,
					sort : [ {
						attribute : "rownumber",
						descending : false
					} ],
					columns : getColumn(editor, NumberTextBox, Select),
					cellNavigation : true,
					loadingMessage : '加载中...'
				}, "dataGrid");

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

				queryData();
			});
}

function queryData() {
	var _url = appRoot + "/restaurant/putinstorage/distribution/queryDetail.action?formId=" + dojo.byId('formId').value;
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				row.receiveCount = row.deliveryCount;
				dataStore.put(row);
			});
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
		row.differentCount = row.deliveryCount - event.value; // 计算差异数，实发数-实收数
		dataStore.put(row);
	}
}

function getColumn(editor, NumberTextBox, Select) {
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
		label : "实发数",
		field : "deliveryCount",
		className : 'grid-number',
		sortable : false
	},
	{
		label : "实收数",
		field : "receiveCount",
		className : 'grid-number edit-note',
	}, {
		label : "差异数",
		field : "differentCount",
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
		label : "",
		field : "none",
		sortable : false
	} ];
}

function validateGrid() {
	var store = grid.store;
	var cols = grid.columns;
	var _colId = '';
	for (key in cols) {
		var col = cols[key];
		if (col.field == 'receiveCount') {
			_colId = key;
			break;
		}
	}
	var rows = dataStore.query();
	for (var i = 0; i < rows.length; i++) {
		var data = rows[i];
		var rowId = grid.store.getIdentity(data);
		var cell = grid.cell(rowId, _colId);
		var column = cell.column;
		if (isEmpty(cell.element)) {
			continue;
		}
		var valid = cell.element.widget.isValid();
		if (!valid) {
			alert('输入有误！');
			return false;
		}
	}
	return true;
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已入库') {
		alert("配送单已入库！")
		return false;
	}
	return true;
}

function doCheck() {
	grid.save();
	if (!validateGrid()) {
		return;
	}
	var formId = dojo.byId('formId').value;
	if (!checkStatus(formId)) {
		return;
	}
	var rows = dataStore.query();

	var invalidItem = [];
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].receiveCount > rows[i].deliveryCount) {
			invalidItem.push(rows[i].itemName);
		}
	}
	if (invalidItem.length > 0) {
		alert(invalidItem.join('、') + "的实收数不能大于实发数！");
		return;
	}

	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + '/lc/stock/in/distribution/checkView.action?parentTabId=' + parentTabId;// 传递初始TAB的ID
	// addPostTab('billForm', '配送单填充确认', _url);
	_url = getUrl(_url);

	postTab('billForm', _url);
}

function doSubmit() {
	var formId = dojo.byId('formId').value;
	if (!checkStatus(formId)) {
		return;
	}

	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/lc/stock/in/distribution/inputBill.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form", "dojox/widget/Standby" ], function(xhr, domForm, Standby) {

		standby = new Standby({
			target : "commandTable"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
		// 显示遮罩层
//		standby.show();

		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();

			if (data.msg == 'ok') {
				alert("生成单据号为：" + data.formId + "，提交成功！");
				closeTab(tabId, 'doQuery');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			standby.hide();
			alert("操作失败！");
		});
	});
}
