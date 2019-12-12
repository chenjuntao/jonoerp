
var branchDlg = null;
function setBranch(row) {
	var _title = "设置餐厅";
	var _url = appRoot + "/hq/pgroup/brand/branchView.action";
	_url += "?priceGroupId=" + row.priceGroupId;
	
	var frameId = 'ifr_branch';
	if (branchDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "450px",
			height : "320px"
		}
		branchDlg = createDialog(option);
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		branchDlg.show();
	}
}

function closeBranchDlg(data) {
	branchDlg.hide();
}


var priceDlg = null;
var priceFrameId = 'ifr_price';
function setPrice(row) {
	var _url = appRoot + "/hq/pgroup/brand/setprice/mainView.action";
	_url += "?priceGroupId=" + row.priceGroupId;
	
	if (priceDlg == null) {
		var option = {
			title : "设置出品价格",
			url : _url,
			frameId : priceFrameId,
			width : "550px",
			height : "320px"
		}
		createDialog(option, function(iDlg) {
			priceDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(priceFrameId).contentWindow;
		ifrWindow.location = _url;
		priceDlg.show();
	}
}

function closePriceDlg() {
	priceDlg.hide();
}
