require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});
var tableData;
function doQuery(_formId) {
	if (grid == null) {
		initGrid();
	}
	var _url = appRoot + "/restaurant/inoutquery/shipping/queryDetail.action?formId=" + _formId;
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (tableData) {
				tableData.splice(0, tableData.length);
				tableData.concat(data.detailLst);
			} else {
				tableData = data.detailLst;
			}
			loadData(data);
		}, function(err) {
			alert("load error");
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	var _url = appRoot + "/restaurant/inoutquery/shipping/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare", "dgrid/editor",
			"dijit/form/NumberTextBox", "dgrid/Keyboard", "dojo/on", "dojo/keys", "dgrid/extensions/ColumnResizer",
			"dojo/query", "dojo/_base/lang", "dojo/domReady!" ], function(OnDemandGrid, Observable, Memory, declare,
			editor, NumberTextBox, Keyboard, on, keys, ColumnResizer, query, lang) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox, lang),
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'shippingCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
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

		doQuery(formId);
	});
}

function loadData(data) {
	dataStore.setData([]);
	grid.set("store", dataStore);
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			dataStore.put(row);
		});
	});
	console.dir(dataStore);
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

function getColumn(editor, NumberTextBox, lang) {
	var numArgs = {
		style : 'width: 5em; text-align: right;',
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
		label : "包装因子",
		field : "packagingFactor",
		className : 'grid-number',
		sortable : false
	}, {
		label : "包装单位",
		field : "packagingUnit",
		sortable : false
	}, {
		label : "包装数量",
		field : "packagingCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "订货数",
		field : "requestCount",
		className : 'grid-number',
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "配送数",
		field : "shippingCount",
		className : 'grid-number',
		sortable : false
	}), {
		// label : "实发数",
		// field : "deliveryCount",
		// className : 'grid-number'
		// }, {
		// label : "差异数",
		// field : "differentCount",
		// className : 'grid-number'
		// }, {
		label : "单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "金额",
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
	if (!validateColumn(grid, 'itemCount')) {
		return false;
	}
	return true;
}

function doClose() {
	closeTab(tabId);
}

function doInvalid() {

	var _url = appRoot + "/lc/request/distribution/doInvalid.action";
	_url = getUrl(_url);

	if (confirm("确定作废单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					formId : formId,
					currentVersion : dojo.byId('currentVersion').value
				}
			}).then(function(data) {
				alert("操作成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
			});
		});
	}
}

function doDelete() {

	var _url = appRoot + "/lc/request/distribution/doDelete.action";
	_url = getUrl(_url);

	if (confirm("确定删除单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					formId : formId,
					currentVersion : dojo.byId('currentVersion').value
				}
			}).then(function(data) {
				alert("刪除成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
			});
		});
	}
}

function doUpdate() {
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
	var receiveTime = dojo.byId("receiveTime").value;
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/lc/request/distribution/doUpdate.action?receiveTime=" + receiveTime;
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
