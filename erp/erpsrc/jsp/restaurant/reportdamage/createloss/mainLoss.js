dojo.ready(function() {
	initGrid();
});

var grid = null;
var dataStore = null;
var myTooltipDialog = null;

// 选择不同门店，仓库也随之刷新
function changeStorage() {
	refreshStorage({
		query : {
			branchId : dojo.byId('lossBranchId').value,
			branchType : dojo.byId('branchType').value
		},
		storageId : "storageId"
	});
}

function initGrid() {
	require([ 
	          "dgrid/OnDemandGrid", 
	          "dojo/store/Memory", 
	          "dojo/_base/declare", 
	          "dgrid/Keyboard", 
	          "dgrid/editor",
	          "dijit/form/NumberTextBox", 
	          "dojo/store/Observable",
	          "dojo/_base/array", 
	          "dgrid/selector",
	          "dgrid/Selection", 
	          "dgrid/extensions/ColumnResizer",
	          "dojo/on",
	          "dojo/keys", 
	          "dojo/query"
            ], function(OnDemandGrid, Memory, declare, Keyboard, editor, NumberTextBox, Observable, array, selector, Selection,	ColumnResizer, on, keys, query) {
		dataStore = new Observable(new Memory({
			data : [],
			idProperty : "rownumber"
		}));

		var CustomGrid = declare([ OnDemandGrid, Keyboard, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(selector, editor, NumberTextBox),
			cellNavigation : false,
			allowSelectAll : true,
			selectionMode : "none",
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
	});
}

function dataChangeHandler(event) {

	var field = event.cell.column.field;
	if (field == 'lossNum') {
		var row = dataStore.get(event.rowId);

		grid.save();
		row[field] = event.value;
		row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		dataStore.put(row);
	}

	calcMoney(dataStore.query());
}

// 计算总金额
function calcMoney(data) {
	var lossMoney = 0;

	require([ "dojo/_base/array", "dojo/dom" ], function(array, dom) {
		array.forEach(data, function(item) {
			if (!isNaN(lossMoney)) {
				lossMoney += parseFloat(item.payAmt);
			} else {
				lossMoney += 0;
			}
		});

		dom.byId("lossMoney").innerHTML = lossMoney.toFixed(2);

	});
}

function getColumn(selector, editor, NumberTextBox) {
	return [ selector({
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		className:'text-center',
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false,
		className:'text-center'
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}
	, editor({
		label : "报损数量",
		field : "lossNum",
		sortable : false,
		className:'text-right',
		editorArgs : {
			style : 'width: 75px;text-align: right;',
			constraints : { // 约束
				min : 0, // 最小值
				max : 99550, // 最大值
				places : '0,3' // 小数位0到3位，超出则违反约束
			},
			required : true, // required 该值是否可以为空
			invalidMessage : '请输入不多于三位小数的数字。' // 输入的信息非数字触发提示
		}
	}, NumberTextBox), {
		label : "标准单价",
		field : "itemUnitPrice",
		className:'text-right',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className:'text-right',
		sortable : false
	}, {
		label : "库存数量",
		field : "inventory",
		className:'text-right',
		sortable : false
	}, editor({
		label : "报损原因",
		field : "reason",
		editor : "text",
		sortable : false
	}), {
		label : "",
		field : "blank",
		sortable : false
	} ];
}

var materialDlg = null;
function selMaterial() {
	var branchType = dojo.byId('branchType').value;
	var frameId = 'ifr_selMaterial';
	var _url = appRoot + "/restaurant/reportdamage/selmaterial/mainView.action?branchType=" + branchType;
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
		ifrWindow.location.url = _url;
		materialDlg.show();
	}
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

/**
 * 查询原料的库存是对应仓库的库存
 */
function getStorageId() {
	return dojo.byId('storageId').value;
}

function closeMaterialDlg(data) {
	materialDlg.hide();

	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/array" ], function(Observable, Memory, array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;

			if (row.inventory == undefined) {
				row.inventory = 0;
			}
			if (row.itemUnitPrice == undefined) {
				row.itemUnitPrice = 0;
			}
			row.lossNum = 0;
			row.exp_date = "2017-01-01";
			row.payAmt = 0;
			row.reason = "";

			dataStore.put(row);
		});
	});
}

function validateGrid() {
	if (!validateColumn(grid, 'lossNum')) {
		return false;
	}
	return true;
}

// 报损数量不能为零
function hasContainZero() {
	var arr = dataStore.query();
	for (var i = 0; i < arr.length; i++) {
		if (arr[i].lossNum == 0) {
			return true;
		}
	}

	return false;
}

function createLoss() {
	if (!validateGrid()) {
		return;
	}

	if (hasContainZero()) {
		alert("报损数量不能为零！");
		return;
	}

	grid.save();
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}

	dojo.byId('jsonData').value = JSON.stringify(rows);

	var $lossBranchId = dojo.byId('lossBranchId');
	dojo.byId('lossBranch').value = $lossBranchId.options[$lossBranchId.selectedIndex].text;

	var $storageId = dojo.byId('storageId');
	dojo.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;

	var _url = appRoot + "/restaurant/reportdamage/createloss/commitView.action?branchType="
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	addPostTab("createLossForm", brandWord + "原料报损单生成确认", _url);
}
