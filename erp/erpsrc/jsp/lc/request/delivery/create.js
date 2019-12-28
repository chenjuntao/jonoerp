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

var numReg = /^(-)?[0-9]{1,}\.{0,1}[0-9]{0,}$/g;
function validateNum(_id, _text) {
	var value = dojo.byId(_id).value.trim();
	if (value != '' && value.match(numReg) == null) {
		alert(_text + "只能为数字！");
		return false;
	}
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
	dojo.byId('jsonData').value = JSON.stringify(rows);

	var _url = appRoot + '/lc/request/delivery/checkView.action?parentTabId=' + tabId;
	_url = getUrl(_url);
	
	addPostTab('billForm', '出货单提交', _url);
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/Observable", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dojo/_base/lang", "dijit/form/NumberTextBox",
			"dojo/on", "dojo/keys", "dojo/query" ], function(OnDemandGrid, selector, Selection, Observable, Memory,
			declare, Keyboard, editor, lang, NumberTextBox, on, keys, query) {
		execute(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor, lang, NumberTextBox,
				on, keys, query);
	});

	function execute(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor, lang,
			NumberTextBox, on, keys, query) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard ]);
		grid = new CustomGrid({
			store : dataStore,
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			columns : getColumn(editor, selector, lang, NumberTextBox),
			cellNavigation : false,
			selectionMode : "toggle",
			allowSelectAll : true,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'deliveryCount') {
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
			if (e.parentType != undefined) { // 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
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
			});
			dataStore.setData(unifiedData);
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn(editor, selector, lang, NumberTextBox) {
	var numArgs = {
		style : 'width: 5em;text-align: right;',
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

	return [{
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
		label : "出货数",
		field : "deliveryCount",
		sortable : false
	}),{
		 label : "单价",
		 field : "itemUnitPrice",
		 className : 'grid-number',
		 sortable : false
	 }, {
		 label : "金额",
		 field : "payAmt",
		 className : 'grid-number',
		 sortable : false
	} ];
}
