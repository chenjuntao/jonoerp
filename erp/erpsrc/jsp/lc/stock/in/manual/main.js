require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
}

function validateGrid() {
	if (!validateColumn(grid, 'receiveCount')) {
		return false;
	}
	return true;
}

// 入库数为0，不能生成入库单
function hasContainZero() {
	var arr = dataStore.query();
	for (var i = 0; i < arr.length; i++) {
		if (arr[i].receiveCount == 0) {
			return true;
		}
	}

	return false;
}

function freshGrid() {
	for (;;) {
		var data = dataStore.data;

		if (data.length > 0) {
			dataStore.remove(data[0].itemId);
		} else {
			break;
		}
	}

}

function doSubmit() {
	if (!validateGrid()) {
		return;
	}
	var supplierId = dojo.byId('supplierId').value;
	if (isEmpty(supplierId)) {
		alert("请选择供应商！");
		return;
	}

	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}

	for (var i = 0; i < rows.length; i++) {
		if (rows[i].receivePrice == null) {
			alert(rows[i].itemName + '供应商价为空，请先设置供应商价！')
			return;
		}
		if (rows[i].receiveCount == 0) {
			alert("入库数量不能为零！");
			return;
		}
	}

	standby.show();
	dojo.byId('jsonData').value = JSON.stringify(rows);

	var _url = appRoot + "/lc/stock/in/manual/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			if (data.msg == 'ok') {
				var formId = data.formId;
				changeIdObj = {};
				alert("生成单据号为：" + formId + "，操作成功！");
				location.reload();// 刷新页面
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox", "dgrid/extensions/ColumnResizer", "dojo/on",
			"dojox/widget/Standby", "dojo/keys", "dojo/query" ], function(OnDemandGrid, Observable, Memory, declare,
			Keyboard, editor, NumberTextBox, ColumnResizer, on, Standby, keys, query) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.startup();

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();

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

		grid.on("dgrid-datachange", dataChangeHandler);
	});
}

function dataChangeHandler(event) {
	var field = event.cell.column.field;
	if (field == 'receiveCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		row[field] = event.value;// necessary
		row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		row.receiveAmt = parseFloat((event.value * row.receivePrice).toFixed(2)); // 计算金额
		dataStore.put(row);
	}
}

function loadData(data) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			row.receiveCount = row.payAmt = row.receiveAmt = 0;
		});
		dataStore.setData(data);
		grid.set("store", dataStore);
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
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "类别编码",
		field : "itemCategory",
		sortable : false
	}, editor({
		label : "入库数量",
		field : "receiveCount",
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
		label : "标准价",
		field : "itemUnitPrice",
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "采购价",
		field : "receivePrice",
		sortable : false
	}, {
		label : "采购金额",
		field : "receiveAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var materialDlg = null;
function selMaterial() {
	var supplierId = dojo.byId('supplierId').value;
	if (supplierId == '') {
		alert('请选择供应商！');
		return;
	}

	var frameId = 'ifr_selMaterial';
	var _url = appRoot + "/lc/request/purchase/manual/selmaterial/mainView.action";
	_url += "?adjustType=" + g_adjustType + "&supplierId=" + supplierId;
	_url = getUrl(_url);
	
	if (materialDlg == null) {
		var option = {
			title : "自选原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			materialDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		// ifrWindow.loadData();
		ifrWindow.location = _url;
		materialDlg.show();
	}
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}
