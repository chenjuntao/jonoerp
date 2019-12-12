var branchDlg = null;
function setBranch(row) {
	var _title = "设置部门";
	var _url = appRoot + "/hq/fpgroup/brand/branchView.action";
	_url += "?priceGroupId=" + row.priceGroupId;
	_url = getUrl(_url);
	
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
	var _url = appRoot + "/hq/fpgroup/brand/setprice/mainView.action";
	_url += "?priceGroupId=" + row.priceGroupId + "&priceGroupName=" + row.priceGroupName;
	_url = getUrl(_url);
	 
	if (priceDlg == null) {
		console.dir(1);
		var option = {
			title : "查看价格",
			url : _url,
			frameId : priceFrameId,
			width : "550px",
			height : "320px"
		}
		createDialog(option, function(iDlg) {
			priceDlg = iDlg;
		});
	} else {
		console.dir(2);
		var ifrWindow = dojo.byId(priceFrameId).contentWindow;
		ifrWindow.location = _url;
		priceDlg.show();
	}
}

function closePriceDlg() {
	priceDlg.hide();
}
