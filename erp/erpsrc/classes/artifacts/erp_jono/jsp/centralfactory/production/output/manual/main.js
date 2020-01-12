require([ "dojo", "dojo/ready", "dojox/widget/Standby" ], function(dojo, ready, Standby) {
	ready(function() {
		addEvent();
		initGrid();

		if (dojo.byId('supplierId').value > 0) {
			dojo.byId('supplierId').value = '';
		}

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

function addEvent() {
}

function doClean() {
	dataStore.setData('');// 生成成功后清空数据
	grid.set("store", dataStore);
}

function validateGrid() {
	if (!validateColumn(grid, 'receiveCount')) {
		return false;
	}
	return true;
}

function freshGrid() {
}

function doSubmit() {
	if (!validateGrid()) {
		return;
	}
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}

	for (var i = 0; i < rows.length; i++) {
		if (rows[i].itemPrice == null) {
			alert(rows[i].itemName + '供应商价为空，请先设置供应商价！')
			return;
		}
		rows[i].receivePrice = rows[i].itemPrice;
		if (rows[i].receiveCount == 0) {
			alert("入库数量不能为零！");
			return;
		}
	}
	standby.show();
	dojo.byId('jsonData').value = JSON.stringify(rows);

	var _url = appRoot + "/lc/stock/in/manual/doSave.action";
	_url = getUrl(_url);
	
	grid.save().then(function(value) {
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
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
	}, function(err) {
		console.log(err);
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dojo/query", "dgrid/OnDemandGrid", "dojo/store/Observable", "dijit/form/DateTextBox",
			"dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dojo/keys", "dgrid/editor",
			"dijit/form/NumberTextBox", "dgrid/extensions/ColumnResizer" ], function(query, OnDemandGrid, Observable,
			DateTextBox, Memory, declare, Keyboard, keys, editor, NumberTextBox, ColumnResizer) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox, DateTextBox),
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.startup();

		grid.on('keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});

		grid.on("dgrid-datachange", dataChangeHandler);

		// 保证鼠标焦点与键盘焦点的连贯性
		grid.on('mousedown', function(e) {
			grid.focus(e.target);
		});
		grid.on('dgrid-cellfocusin', function(e) {
			var $input = query('.dijitInputField input[type=text]', e.target);
			if ($input.length > 0) {
				if (e.parentType != undefined) {// 鼠标事件不予处理
					$input[0].select();
				}
			}
		});
	});
}

function dataChangeHandler(event) {
	var field = event.cell.column.field;
	if (field == 'receiveCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		row[field] = event.value;// necessary
		row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		row.receiveAmt = parseFloat((event.value * row.itemPrice).toFixed(2)); // 计算金额
		dataStore.put(row);
	}
}

function loadData(data) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			if (row.receiveCount == undefined) {
				row.receiveCount = 0;
			}
		});
		dataStore.setData(data);
		grid.set("store", dataStore);
	});
}

function getColumn(editor, NumberTextBox, DateTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, editor({
		label : "入库数量",
		field : "receiveCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align:right',
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
		label : "商品编码",
		field : "itemId",
		sortable : true
	}, {
		label : "商品名称",
		field : "itemName",
		sortable : false
	}, {
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
		field : "itemPrice",
		sortable : false
	}, {
		label : "采购金额",
		field : "receiveAmt",
		className : 'grid-number',
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
		label : "生产车间",
		field : "workshop",
		sortable : true
	} ];
}

var productDlg = null;
function selMaterial() {
	var frameId = 'ifr_selProduct';
	if (productDlg == null) {
		var _url = appRoot + "/restaurant/reportdamage/selproduct/mainView.action?branchType="
				+ dojo.byId('branchType').value;
		_url = getUrl(_url);
		
		var option = {
			title : "选择出品",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		productDlg = createDialog(option);
	} else {
		productDlg.show();
	}
}

function closeProductDlg(data) {
	productDlg.hide();

	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/array" ], function(Observable, Memory, array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			row.receiveCount = 0;
			row.payAmt = 0;
			row.receiveAmt = 0;
		});
		dataStore = new Observable(new Memory({
			data : data,
			idProperty : "itemId"
		}));
		grid.set("store", dataStore);
	});
}