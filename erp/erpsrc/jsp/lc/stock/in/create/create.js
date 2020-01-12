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

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query" ], function(OnDemandGrid, declare,
			Keyboard, editor, NumberTextBox, ColumnResizer, on, keys, query) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
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
	var supplierId = dojo.byId('supplierId').value;
	var _url = appRoot + "/lc/stock/in/create/queryUnifiedDetail.action?formId=" + dojo.byId('formId').value
			+ "&supplierId=" + supplierId;
	// if (deliveryType == 'CROSS') {// 越库单汇总入库
	// _url = appRoot + "/lc/stock/in/create/queryCrossDetail.action?formId=" +
	// dojo.byId('formId').value
	// + "&supplierId=" + supplierId;
	// }
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/_base/array", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr,
			array, Observable, Memory) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			var rows = data.rows;
			array.forEach(rows, function(item, i) {
				item.payAmt = parseFloat((item.receiveCount * item.itemUnitPrice).toFixed(2)); // 计算金额
				item.receiveAmt = parseFloat((item.receiveCount * item.receivePrice).toFixed(2)); // 计算金额
			});
			dataStore = new Observable(new Memory({
				idProperty : "rownumber",
				data : rows
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
		var oldCount = row.receiveCount;
		row[field] = event.value;
		row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		row.receiveAmt = parseFloat((event.value * row.receivePrice).toFixed(2)); // 计算金额
		row.differentCount = row.orderCount - (event.value + row.receivedCount); // 计算实收差异
		var rows = dataStore.query();
		for (var i = 0; i < rows.length; i++) {
			if (row.itemId == rows[i].itemId) {
				rows[i].sumItemCount += event.value - oldCount;
				dataStore.put(rows[i]);
			}
		}
		// dataStore.put(row);
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
		label : "收货部门",
		field : "receive",
		sortable : false
	}, {
		label : "供货周期",
		field : "supplyCycle",
		className : 'grid-number',
		sortable : true
	}, {
		label : "订货数量",
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
			style : 'width: 5em;text-align: right;color:red	',
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
		label : "实收合计",
		field : "sumItemCount",
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
	}, {
		label : "有效期",
		field : "expiredTime",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function validateGrid() {
	if (!validateColumn(grid, 'receiveCount')) {
		return false;
	}
	return true;
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已结案') {
		alert("单据已结案！");
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
	var oldrows = dataStore.query();
	var oldItemId = "";
	var rows = [];
	if (oldrows.length > 0) {
		for (var i = 0; i < oldrows.length; i++) {
			var item = oldrows[i];
			if ((item.receiveCount + item.receivedCount) > item.orderCount * item.outReceiveRate) {
				alert(item.itemName + "入库数不能超过限制比例！");
				return;
			}
			var itemId = item.itemId;
			if (itemId != oldItemId) {
				item.receiveCount = item.sumItemCount;
				item.orderCount = item.receivedCount + item.sumItemCount + item.differentCount;
				item.payAmt = item.receiveCount * item.itemUnitPrice;
				item.receiveAmt = item.receiveCount * item.receivePrice;
				rows.push(item);
				oldItemId = itemId;
			}
		}
	} else {
		alert("请选择物料！");
		return;
	}
	var $storageId = dojo.byId('storageId');
	dojo.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;

	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + '/lc/stock/in/create/checkView.action?parentTabId=' + tabId;
	_url = getUrl(_url);
	
	addPostTab('billForm', '入库单生成确认', _url);
}

function doClose() {
	closeTab(tabId);
}