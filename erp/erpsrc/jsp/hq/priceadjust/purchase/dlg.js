var supplierDlg = null;
var selectedRows = [];
function selSupplier() {
	var frameId = 'ifr_selSupplier';
	if (supplierDlg == null) {
		var _url = appRoot + "/common/selsupplier/mainView.action?branchType=" + branchType;

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
	supplierDlg.hide();
	if (rowData.branchId != dojo.byId('supplierId').value) {
		freshGrid();
	}
	dojo.byId('supplierId').value = rowData.branchId;
	dojo.byId('supplier').value = rowData.branchName;
	dojo.byId('supplierText').innerHTML = rowData.branchName;
	dojo.byId('branchType').value = rowData.branchType;
}
