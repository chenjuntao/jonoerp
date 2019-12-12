var purchaseDlg = null;
var selectedRows = [];
function selPurchase(row) {
	var frameId = 'ifr_selPurchase';
	
	var _url = appRoot + "/common/s_branch_items/mainView.action?branchId="+row.branchId;

	var option = {
		title : "确定停用["+row.branchId+"]"+ row.branchName+"吗？",
		url : _url,
		frameId : frameId,
		width : "850px",
		height : "300px"
	}
	createDialog(option, function(iDlg) {
		purchaseDlg = iDlg;
	});

}

function closePurchaseDlg() {
	purchaseDlg.hide();
	doQuery();
}
