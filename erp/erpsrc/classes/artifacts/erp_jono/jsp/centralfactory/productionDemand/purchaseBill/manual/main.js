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
function freshGrid() {

}

function validateGrid() {
	if (!validateColumn(grid, 'itemCount')) {
		return false;
	}
	return true;
}

function doSubmit() {
	if (!validateGrid()) {
		return;
	}
	grid.save().then(
			function(value) {
				var rows = dataStore.query();
				if (rows.length == 0) {
					alert("请选择原料！");
					return;
				}

				var providerName = [];

				var rowsSave = [];
				if (rows.length > 0) {
					var zeroItem = [];
					for (var i = 0; i < rows.length; i++) {
						if (rows[i].itemCount == 0) {
							zeroItem.push(rows[i].itemName);
						} else {
							if (rows[i].receivePrice == null) {
								alert(rows[i].itemName + '供应商价为空，请先设置供应商价！')
								return;
							}
							rows[i].payAmt = parseFloat((rows[i].itemCount * rows[i].itemUnitPrice).toFixed(2)); // 计算金额
							rows[i].receiveAmt = parseFloat((rows[i].itemCount * rows[i].receivePrice).toFixed(2)); // 计算金额
							rowsSave.push(rows[i]);
						}
						if (rows[i].provider == null) {
							providerName.push(rows[i].itemName);
						}
					}
					if (providerName.length != 0) {
						alert(providerName.join('、') + "供应商未设置，请先设置对应供应商");
						return;
					}
					if (rowsSave.length == 0) {
						alert("不允许所有物料的订货数为零！");
						return;
					}
					if (zeroItem.length > 0) {
						if (!confirm(zeroItem.join('、') + "输入为零，确定提交吗？")) {
							return;
						}
					}
				}
				dojo.byId('jsonData').value = JSON.stringify(rowsSave);
				var _url = appRoot
						+ "/centralfactory/productionDemand/purchaseBill/manual/checkView.action?parentTabId=" + tabId;
				_url = getUrl(_url);
				
				addPostTab('billForm', '生成采购单', _url);
			}, function(err) {
				console.log(err);
			});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require(
			[ "dojo/query", "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare",
					"dgrid/Keyboard", "dojo/keys", "dgrid/editor", "dijit/form/NumberTextBox",
					"dgrid/extensions/ColumnResizer" ], function(query, OnDemandGrid, Observable, Memory, declare,
					Keyboard, keys, editor, NumberTextBox, ColumnResizer) {
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

				grid.on("dgrid-datachange", dataChangeHandler);
				grid.on('keydown', function(e) {
					if (e.keyCode === keys.UP_ARROW) {
						Keyboard.moveFocusUp.call(grid, e);
					} else if (e.keyCode === keys.DOWN_ARROW) {
						Keyboard.moveFocusDown.call(grid, e);
					} else if (e.keyCode === keys.ENTER) {
						Keyboard.moveFocusDown.call(grid, e);
					}
				});

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
	// if (field == 'itemCount') {
	// grid.save();// very important
	// var row = dataStore.get(event.rowId);
	// row[field] = event.value;// necessary
	// // row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2));
	// // 计算金额
	// dataStore.put(row);
	// }
}

function loadData(data) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			if (row.itemCount == undefined) {
				row.itemCount = row.payAmt = 0;
			}
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
	}, {
		label : "供应商编码",
		field : "providerId",
		sortable : false
	}, {
		label : "供应商",
		field : "provider",
		sortable : false
	}, {
		label : "标准价",
		field : "itemUnitPrice",
		sortable : false
	}, {
		label : "采购价",
		field : "receivePrice",
		sortable : false
	}, editor({
		label : "订货数",
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
	}, NumberTextBox), /*
						 * { label : "金额", field : "payAmt", className :
						 * 'grid-number' },
						 */{
		label : "",
		field : "none",
		sortable : false
	} ];
}

var materialDlg = null;
var selectedRows = [];
function selMaterial() {
	var supplierId = dojo.byId('supplierId').value;
	if (supplierId == '') {
		alert('请选择供应商！');
		return;
	}
	// grid.save().then(function(value) {
	// selectedRows = dataStore.query();// 初始化被选择的记录
	// }, function(err) {
	// console.log(err);
	// });

	var frameId = 'ifr_selMaterial';
	var _url = appRoot + "/lc/request/purchase/manual/selmaterial/mainView.action";// 物流中心复用
	
	// var _url = appRoot +
	// "/centralfactory/productionDemand/purchaseBill/manual/selmaterial/mainView.action";
	
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
		ifrWindow.location = _url;
		// ifrWindow.loadData();
		materialDlg.show();
	}
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}
