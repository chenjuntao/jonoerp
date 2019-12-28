require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doValidate() {
	return true;
}

function doSubmit() {
	if (!doValidate()) {
		return;
	}
	grid.save();
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}
	var zeroItem = [];
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].shippingCount == 0) {
			zeroItem.push(rows[i].itemName);
		}
	}
	if (zeroItem.length > 0) {
		if (!confirm(zeroItem.join('、') + "输入为零，确定提交吗？")) {
			return;
		}
	}
	dojo.byId('jsonData').value = JSON.stringify(rows);

	var $storageId = dojo.byId('storageId');
	dojo.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;

	var _url = appRoot + '/lc/request/distribution/checkView.action?parentTabId=' + parentTabId;// 传递初始TAB的ID
	_url = getUrl(_url);
	
	postTab('billForm', _url);
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dojo/store/Observable", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dgrid/extensions/ColumnResizer",
			"dijit/form/NumberTextBox", "dojo/_base/lang", "dojo/on", "dojo/keys", "dojo/query" ], function(
			OnDemandGrid, Selection, Observable, Memory, declare, Keyboard, editor, ColumnResizer, NumberTextBox, lang,
			on, keys, query) {
		execute(OnDemandGrid, Selection, Observable, Memory, declare, Keyboard, editor, ColumnResizer, NumberTextBox,
				lang, on, keys, query);
	});

	function execute(OnDemandGrid, Selection, Observable, Memory, declare, Keyboard, editor, ColumnResizer,
			NumberTextBox, lang, on, keys, query) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(editor, NumberTextBox, lang),
			loadingMessage : '加载中...',
		}, "dataGrid");

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

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'shippingCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				// 修改包装数
				row.packagingCount = Math.ceil(event.value / row.packagingFactor) // 默认向上取整，以后可能修改为参数可配置
				// 修改金额
				row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
				row.differentCount = row.requestCount - event.value;
				dataStore.put(row);
			}
		});

		grid.startup();

		loadGridData();
	}
}

function loadGridData() {
	var _url = appRoot + "/lc/request/distribution/queryDetail.action?formId=" + formRefId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			var unifiedData = data.uarr;
			array.forEach(unifiedData, function(row, i) {
				row.requestCount = row.orderCount;
				row.deliveryCount = row.shippingCount;
				row.differentCount = row.requestCount - row.deliveryCount;
			});
			dataStore.setData(unifiedData);
			grid.set("store", dataStore);

		}, function(err) {
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
		className : 'text-center',
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}
	, {
		label : "单位",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "包装因子",
		field : "packagingFactor",
		className : 'text-right',
		sortable : false
	}, {
		label : "包装单位",
		field : "packagingUnit",
		className : 'text-center',
		sortable : false
	}, {
		label : "包装数量",
		field : "packagingCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "订货数",
		field : "requestCount",
		className : 'text-right',
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "配送数",
		field : "shippingCount",
		className : 'text-right',
		sortable : false
	}), {
		label : "配送差异数",
		field : "differentCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "单价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "金额",
		field : "payAmt",
		className : 'text-right',
		sortable : false
	} ];
}

var dcols = [ {
	label : "序号",
	field : "rownumber"
}, {
	label : "原料编码",
	field : "itemId"
}, {
	label : "原料名称",
	field : "itemName"
}
, {
	label : "单位",
	field : "itemDimension"
}, {
	label : "规格",
	field : "itemSpecification"
}, {
	label : "订货数",
	field : "requestCount",
	className : 'grid-number'
}, {
	label : "标准单价",
	field : "itemUnitPrice",
	className : 'grid-number'
}, {
	label : "标准金额",
	field : "payAmt",
	className : 'grid-number'
}, {
	label : "",
	field : "none"
} ];