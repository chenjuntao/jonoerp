dojo.ready(function() {
	addEvent();
	initGrid();
});

function refreshStorage1(branchId, storageId) {
	var _url = appRoot + '/restaurant/reportdamage/queryloss/refreshStorage.action?branchType='
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dojo.byId(branchId).value
			}
		}).then(function(data) {
			if (data.msg) {
				var storageSelector = dojo.byId(storageId);
				storageSelector.length = 0;
				for (var i = 0, length = data.msg.length; i < length; i++) {
					var item = data.msg[i];
					storageSelector.options.add(new Option(item.storageName, item.storageId));
				}
			} else {
				// do something
			}
		}, function(err) {
		});
	});
}

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
	dojo.byId('btn_submit').onclick = doSubmit;
}

function closeMainTab() {
	closeTab(tabId);
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

function getNodeValue(node) {
	return dojo.byId(node).value;
}

function doSubmit() {
	grid.save();

	if (!validateGrid()) {
		return;
	}

	// 查询所有数据对象（数组）
	var rows = dataStore.query();

	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}

	if (getNodeValue("inStorageId") == getNodeValue("outStorageId")) {
		alert("请选择不同仓库！");
		return;
	}

	console.log("getZeroCount()", getZeroCount());
	if (getZeroCount() > 0) {
		alert("物料的调拨数量为零，不能调拨！");
		return;
	}

	dojo.byId('jsonData').value = JSON.stringify(rows);

	// 调入门店
	var $inBranchId = dojo.byId('inBranchId');
	dojo.byId('inBranch').value = $inBranchId.options[$inBranchId.selectedIndex].text;

	// 调入仓库
	var $inStorageId = dojo.byId('inStorageId');
	dojo.byId('inStorage').value = $inStorageId.options[$inStorageId.selectedIndex].text;

	// 调出门店
	var $outBranchId = dojo.byId('outBranchId');
	dojo.byId('outBranch').value = $outBranchId.options[$outBranchId.selectedIndex].text;

	// 调出仓库
	var $outStorageId = dojo.byId('outStorageId');
	dojo.byId('outStorage').value = $outStorageId.options[$outStorageId.selectedIndex].text;

	var _url = appRoot + '/restaurant/allocateitem/create/commitView.action?parentTabId=' + tabId;
	_url = getUrl(_url);

	addPostTab('billForm', '提交调拨单', _url);
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/Observable", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/array",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query", "dojo/domReady!" ], function(
			OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor, NumberTextBox, array,
			ColumnResizer, on, keys, query) {
		dataStore = new Observable(new Memory({
			// data : gridData
			idProperty : "rownumber",
			data : []
		}));

		window.getZeroCount = function() {
			array.forEach(dataStore.query(), function(item) {
				if (item.applyCount == 0) {
					return 1;
				}
			});

			return 0;
		}

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(editor, NumberTextBox, selector),
			cellNavigation : false,
			selectionMode : "toggle",
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

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
			if (field == 'applyCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				row.payAmt = reserve2Decimal(event.value * row.itemUnitPrice); // 计算金额
				dataStore.put(row);
			}
		});

		grid.startup();
	});
}

function reserve2Decimal(number) {
	return Math.round(number * 100) / 100;
}

function loadData(data) {
	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/array" ], function(Observable, Memory, array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;

			if (row.applyCount == undefined) {
				row.applyCount = 0;
			}
			if (row.payAmt == undefined) {
				row.payAmt = 0;
			}
		});
		dataStore = new Observable(new Memory({
			data : data,
			idProperty : "itemId"
		}));
		grid.set("store", dataStore);
	});
}

function delMaterial() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
			dataStore.remove(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
}

function getColumn(editor, NumberTextBox, selector) {
	return [ selector({
		label : "序号",
		field : "rownumber",
		sortable : false
	}), {
		label : "原料编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		className : 'text-center',
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
	}, editor({
		label : "申请调拨数量",
		field : "applyCount",
		editorArgs : {
			style : 'width: 100px;text-align: right;',
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
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}
function validateGrid() {
	if (!validateColumn(grid, 'applyCount')) {
		return false;
	}
	return true;
}

var materialDlg = null;
function selMaterial() {
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var branchType = dojo.byId('branchType').value;
		var transferType = dojo.byId('transferType').value;
		var _url = appRoot + "/restaurant/allocateitem/selmaterial/mainView.action?branchType=" + branchType
				+ "&transferType=" + transferType;
		_url = getUrl(_url);

		var option = {
			title : "自选原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		materialDlg = createDialog(option);
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.loadData();
		materialDlg.show();
	}
}

function getBranchId() {
	return dojo.byId('outStorageId').value;
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}

function closePickModelDlg(data) {
	loadData(data);
	pickModelDlg.hide();
}

function closeSaveModelDlg(data) {
	saveModelDlg.hide();
}
