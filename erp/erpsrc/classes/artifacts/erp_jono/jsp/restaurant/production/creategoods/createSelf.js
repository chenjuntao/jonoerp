dojo.ready(function() {
	initGrid();
});

var grid = null;
var dataStore = null;

/**
 * 根据选择的门店动态刷新仓库列表
 */
function refreshStorage() {
	var _url = appRoot + '/restaurant/reportdamage/queryloss/refreshStorage.action?branchType='
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dojo.byId('lossBranchId').value
			}
		}).then(function(data) {
			if (data.msg) {
				var storageSelector = dojo.byId('storageId');
				storageSelector.length = 0;
				for (var i = 0, length = data.msg.length; i < length; i++) {
					var item = data.msg[i];
					storageSelector.options.add(new Option(item.storageName, item.storageId));
				}
			} else {
			}
		}, function(err) {
		});
	});
}

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor",
			"dijit/form/NumberTextBox", "dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query" ],
			function(OnDemandGrid, Memory, declare, Keyboard, editor, NumberTextBox, ColumnResizer, on, keys, query) {
				dataStore = new Memory({
					data : [],
					idProperty : "rownumber"
				});

				var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
				grid = new CustomGrid({
					store : dataStore,
					columns : getColumn(editor, NumberTextBox),
					loadingMessage : '加载中...'
				}, "createLossGrid");

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
			});
}

function dataChangeHandler(event) {

	var field = event.cell.column.field;
	if (field == 'itemCountActual') {
		grid.save();
		var row = dataStore.get(event.rowId);
		row[field] = event.value;
		row.payAmt = row[field] * row.itemUnitPrice; // 计算金额
		row.payAmt = row.payAmt.toFixed(2); // 保留三位小数
		dataStore.put(row);
	}
}

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "半成品编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "半成品名称",
		field : "itemName",
		sortable : false
	}, {
		label : "例牌",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		className : 'text-center',
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, editor({
		label : "入库数量",
		field : "itemCountActual",
		editorArgs : {
			style : 'width: 100px;text-align: right;',
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
		label : "配方单价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "金额",
		field : "payAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "blank",
		sortable : false
	} ];
}

function getBranchId() {
	return dojo.byId('storageId').value;
}

var productDlg = null;
function selMaterial() {
	var frameId = 'ifr_selProduct';
	if (productDlg == null) {
		var _url = appRoot + "/restaurant/reportdamage/selproduct/mainView.action?branchType=CENTRALFACTORY";
		// _url = getUrl(_url);

		var option = {
			title : "选择半成品",
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
			row.itemCountActual = 0;
			row.payAmt = 0;
			row.reason = "";
		});
		dataStore = new Observable(new Memory({
			data : data,
			idProperty : "itemId"
		}));
		grid.set("store", dataStore);
	});
}

function validateGrid() {
	if (!validateColumn(grid, 'itemCountActual')) {
		return false;
	}
	return true;
}

function createLoss() {
	if (!validateGrid()) {
		return;
	}
	grid.save();
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择半成品！");
		return;
	}

	dojo.byId('jsonData').value = JSON.stringify(rows);

	var $branchId = dojo.byId('branchId');
	dojo.byId('branch').value = $branchId.options[$branchId.selectedIndex].text;

	// var $storageId = dojo.byId('storageId');
	// dojo.byId('storage').value =
	// $storageId.options[$storageId.selectedIndex].text;

	var _url = appRoot + "/restaurant/production/createself/commitView.action?branchType="
			+ dojo.byId('branchType').value;
	// _url = getUrl(_url);

	addPostTab("createLossForm", "半成品入库单生成确认", _url);
}
