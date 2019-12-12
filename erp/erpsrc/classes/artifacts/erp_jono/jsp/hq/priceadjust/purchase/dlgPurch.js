var purchaseDlg = null;
var selectedRows = [];
function selPurchase() {
	var frameId = 'ifr_selPurchase';
	freshGrid();
	if (purchaseDlg == null) {
		var _url = appRoot + "/common/selPurchase/mainView.action?branchType=S_Center";

		var option = {
			title : "选择采购单",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			purchaseDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		purchaseDlg.show();
	}
}

function closePurchaseDlg(rowData) {
	purchaseDlg.hide();
	var _formId = rowData.formId;
	var _deliveryType = rowData.deliveryType;
	var _providerId = rowData.providerId;
	dojo.byId("providerId").value = _providerId;
	var _provider = rowData.provider;
	dojo.byId("provider").value = _provider;

	var _url = appRoot + "/hq/priceadjust/purchase/queryPItem.action?formId=" + _formId;
	if (_deliveryType == 'DIRECT') {
		_url = appRoot + "/hq/priceadjust/purchase/queryZItem.action?formId=" + _formId;
	}

	require([ "dojo/_base/xhr", "dojo/store/Memory", "dojo/_base/array" ], function(xhr, Memory, array) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
				addData(data);
			},
			error : function(error) {
				alert("load error");
			}
		});
	});
}
