require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
	dojo.byId('receiveTime').onfocus = function(e) {
		WdatePicker();
	};
}

function doQuery(_formId, _deliveryType) {
	if (grid == null) {
		initGrid();
	}
	var _url = appRoot + "/lc/request/purchase/manage/queryBill.action?formId=" + _formId;
	if (_deliveryType == 'DIRECT') {
		_url = appRoot + "/lc/request/purchase/manage/queryDirect.action?formId=" + _formId;
	}
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			loadData(data);
		}, function(err) {
			alert("load error");
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare", "dgrid/editor",
			"dijit/form/NumberTextBox", "dgrid/Keyboard", "dojo/on", "dojo/keys", "dgrid/extensions/ColumnResizer",
			"dojo/query", "dojo/domReady!" ], function(OnDemandGrid, Observable, Memory, declare, editor,
			NumberTextBox, Keyboard, on, keys, ColumnResizer, query) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'itemCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
				row.receiveAmt = parseFloat((event.value * row.receivePrice).toFixed(2));
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

		doQuery(formId, deliveryType);
	});
}

function loadData(data) {
	var header = data.header;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_provider').innerHTML = '[' + header.providerId + ']' + header.provider;
	dojo.byId('receiveTime').value = header.receiveTime;
	dojo.byId('l_requester').innerHTML = header.requester;
	dojo.byId('l_receiveAddress').innerHTML = header.receiveAddress;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('formNote').value = header.formNote;

	var deliveryType = header.deliveryType;
	var dType = '直配';
	if (deliveryType == 'CROSS') {
		dType = '越库';
		hideCols([ "receiveAddress" ], [ "receiver" ]);
	} else if (deliveryType == 'UNIFIED') {
		dType = '统配';
		hideCols([ "receiver", "receiveAddress" ], []);
	} else {
		hideCols([], [ "receiver", "receiveAddress" ]);
	}

	// 兼容中央工厂采购单
	if (!deliveryType) {
		dType = "";
	}

	dojo.byId('l_deliveryType').innerHTML = dType;

	dataStore.setData([]);
	grid.set("store", dataStore);
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data.detailLst, function(row, i) {
			dataStore.put(row);
		});
	});
}

function hideCols(hideArr, showArr) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(hideArr, function(columnId, i) {
			// to hide column with id="name"
			grid.styleColumn(columnId, "display: none;");
		});
		array.forEach(showArr, function(columnId, i) {
			// to show it
			grid.styleColumn(columnId, "display: table-cell;");
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
	}, {
		id : 'receiver',
		label : "收货部门",
		field : "receiver",
		sortable : false
	}, {
		id : 'receiveAddress',
		label : "收货地址",
		field : "receiveAddress",
		sortable : false
	}, editor({
		label : "订货量",
		field : "itemCount",
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
		label : "进货价",
		field : "receivePrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "进货金额",
		field : "receiveAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "原料有效期",
		field : "expiredTime",
		sortable : false
	} ];
}

function validateGrid() {
	if (!validateColumn(grid, 'itemCount')) {
		return false;
	}
	return true;
}
/**
 * 保存之前计算主要采购品
 */
function calcAllPayAmt() {
	var rows = dataStore.query();
	var maxPayAmt = -1;
	var maxPayItem = "";

	for (var int = 0; int < rows.length; int++) {
		var item = rows[int];

		var payAmt = item.payAmt;
		if (payAmt > maxPayAmt) {
			maxPayAmt = payAmt;
			maxPayItem = "[" + item.itemId + "]" + item.itemName;
		}
	}

	dojo.byId('maxPayItem').value = maxPayItem;

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
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);

	// 如果有数据修改，则重新计算主要采购品
	calcAllPayAmt();

	dojo.byId('auditBtn').disabled = true;

	var _url = appRoot + "/lc/request/purchase/audit/doAudit.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId, 'doQuery');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
