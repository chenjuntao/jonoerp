require([], function() {
});

var grid = null;
var dataStore = null;
var reasonStore = null;
var reasonData = [ {
	"key" : "10",
	"value" : "损坏"
}, {
	"key" : "20",
	"value" : "过期"
}, {
	"key" : "30",
	"value" : "无"
} ];

require([ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox",
		"dijit/form/Select", "dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query",
		"dojo/request/xhr", "dojo/store/Observable", "dojo/data/ObjectStore", "dojo/store/Memory", "dojo/_base/array",
		"dojo/dom", "dojo/domReady!" ], function(OnDemandGrid, declare, Keyboard, editor, NumberTextBox, Select,
		ColumnResizer, on, keys, query, xhr, Observable, ObjectStore, Memory, array, dom) {
	initGrid();

	function initGrid() {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
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
	}

	function queryData() {
		var _url = appRoot + "/restaurant/putinstorage/distribution/queryDetail.action?formId="
				+ dojo.byId('formId').value;
		_url = getUrl(_url);
		
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "rownumber",
				data : data
			}));

			// 退货数默认为零
			var arr = dataStore.query();
			for (var i = 0; i < arr.length; i++) {
				arr[i].returnCount = 0;
			}

			grid.set("store", dataStore);
		}, function(err) {
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

		calcMoney(dataStore.query());
	}

	// 计算总金额
	function calcMoney(data) {
		var sumMoney = 0;

		array.forEach(data, function(item) {
			if (!isNaN(sumMoney)) {
				sumMoney += parseFloat(item.returnPayAmt);
			} else {
				sumMoney += 0;
			}
		});

		dom.byId("sumMoney").innerHTML = sumMoney.toFixed(2);
	}

	function getColumn() {
		reasonStore = new ObjectStore({
			objectStore : new Observable(new Memory({
				idProperty : "value",
				data : reasonData
			})),
			labelProperty : "value"
		});
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
			label : "单位",
			field : "itemDimension",
			className : 'text-center',
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
			label : "实收数",
			field : "receiveCount",
			className : 'text-right',
			sortable : false
		}, editor({
			label : "退货数",
			field : "returnCount",
			className : 'grid-number edit-note',
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
			className : 'text-right',
			sortable : false
		}, {
			label : "标准金额",
			field : "returnPayAmt",
			className : 'text-right',
			sortable : false
		}, editor({
			label : "退货原因",
			field : "returnReason",
			className : 'edit-note',
			id : "1",
			editorArgs : {
				store : reasonStore,
				maxHeight : 150,
				style : "width:100px;"
			},
			sortable : false
		}, Select, "click") ];
	}
});

function validateGrid() {
	if (!validateColumn(grid, 'returnCount')) {
		return false;
	}
	return true;
}

function numValid() {
	var info = '';
	var flag = false;
	var rows = dataStore.query();
	for (var i = 0; i < rows.length; i++) {
		var data = rows[i];
		if (data.returnCount != 0) {
			flag = true;
		}
		if (data.returnCount > data.receiveCount) {
			info += '[' + data.itemId + ']' + data.itemName + '\n';
		}
	}

	if (!isEmpty(info)) {
		info += "退货数不能大于实收数！";
	}

	if (!flag) {
		info = "所有原料退货数为0，不能生成配送退货单！";
	}
	return info;
}

function doClose() {
	closeTab(tabId, 'doQuery');
}

function doSubmit() {
	grid.save();
	if (!validateGrid()) {
		return;
	}

	// 放开退货数不能大于实收数的限制
	// var info = numValid();
	// if(!isEmpty(info)){
	// alert(info);
	// return;
	// }

	var formId = dojo.byId('formId').value;
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + '/restaurant/dreject/create/checkView.action?parentTabId=' + tabId;
	_url = getUrl(_url);
	
	addPostTab('billForm', '配送退货单生成确认', _url);
}
