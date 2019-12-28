var supplierDlg = null;
var selectedRows = [];
function selSupplier() {
	var frameId = 'ifr_selSupplier';
	if (supplierDlg == null) {
		var _url = appRoot + "/common/selsupplier/mainView.action?branchType=S_Center";
		_url = getUrl(_url);

		var option = {
			title : "选择供应商",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			supplierDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		supplierDlg.show();
	}
}

function closeSupplierDlg(rowData) {
	dojo.byId('supplierId').value = rowData.branchId;
	dojo.byId('supplierText').innerHTML = rowData.branchName;
	supplierDlg.hide();
}

var branchDlg = null;
var selectedRows = [];
function selBranch() {
	var frameId = 'ifr_selBranch';
	if (branchDlg == null) {
		var _url = appRoot + "/common/selbranch/mainViewStore.action";
		_url = getUrl(_url);

		var option = {
			title : "选择门店",
			url : _url,
			frameId : frameId,
			width : "350px",
			height : "350px"
		}
		createDialog(option, function(iDlg) {
			branchDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		branchDlg.show();
	}
}

function resetData() {
	// 初始化
	dojo.byId('branchId').value = "";
	dojo.byId('branchText').innerHTML = "";
	dojo.byId('branchText').value = "";
	dataStore.setData([]);
	grid.set("store", dataStore);
	grid.set('columnSets', []);

	for (;;) {
		if (cols.length != length) {
			cols.shift();
		} else {
			break;
		}
	}
}

function closeBranchDlg(_ids, _names) {
	resetData();

	dojo.byId('branchId').value = _ids;
	dojo.byId('branchText').innerHTML = _names;
	dojo.byId('branchText').value = _names;
	branchDlg.hide();
	gridAppend();
}

var materialDlg = null;
var selectedRows = [];
function selMaterial() {
	selectedRows = []; // 兼容两种操作方式，确定后重载主表格数据或者追加数据
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/selmaterial/mainView.action";
		_url = getUrl(_url);

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
		ifrWindow.loadData();
		materialDlg.show();
	}
}

function closeMaterialDlg(data) {
	require([ "dojo/_base/array" ], function(array) {
		var isRepeat = array.some(data, function(item, i) {
			var _itemId = item.itemId;
			var repeat = false;
			var rows = itemStore.query();
			return array.some(rows, function(row, i) {
				if (row.itemId == _itemId) {
					alert(row.itemName + "已存在！");
					return true;
				}
			});
		});

		if (!isRepeat) {
			array.forEach(data, function(row, i) {
				var items = itemStore.query();
				Col.editorArgs = {
					style : 'width: 5em;',
					constraints : {
						min : 0,
						max : 1999999999,
						places : '0,3'
					},
					required : true,
					invalidMessage : '请输入不多于三位小数的数字。'
				};
				editor(bCol, NumberTextBox);
				row.rownumber = 1;
				if (items.length > 0) {
					row.rownumber = items[items.length - 1].rownumber + 1;// 应对中间删除的情况
				}
				itemStore.put(row);
			});
			materialDlg.hide();
		}
	});
}
