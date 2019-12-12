var supplierDlg = null;
var selectedRows = [];
function selSupplier() {
	var frameId = 'ifr_selSupplier';
	if (supplierDlg == null) {
		var _url = appRoot + "/common/selsupplier/mainView.action?branchType=S_Center";

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
	dojo.byId('supplierType').value = rowData.branchType;
	dojo.byId('supplierText').innerHTML = rowData.branchName;
	dojo.byId('supplierText').value = rowData.branchName;
	supplierDlg.hide();
}

var branchDlg = null;
var selectedRows = [];
function selBranch() {
	var frameId = 'ifr_selBranch';
	if (branchDlg == null) {
		var _url = appRoot + "/common/selbranch/mainViewBranch.action";

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

function closeBranchDlg(_ids, _names, _branchTypes) {
	dojo.byId('branchId').value = _ids;
	dojo.byId('branchTypes').value = _branchTypes;
	dojo.byId('branchText').innerHTML = _names;
	dojo.byId('branchText').value = _names;
	branchDlg.hide();
}

var materialDlg = null;
var selectedRows = [];
function selMaterial() {
	selectedRows = []; // 兼容两种操作方式，确定后重载主表格数据或者追加数据
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/selmaterial/mainView.action";

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
