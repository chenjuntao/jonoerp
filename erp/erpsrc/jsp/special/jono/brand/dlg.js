var branchDlg = null;
function setBranch(row) {
	var _title = "设置餐厅";
	var _url = appRoot + "/hq/brand/j/branchView.action";
	_url += "?brandId=" + row.brandId;
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
		createDialog(option, function(iDlg) {
			branchDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		branchDlg.show();
	}
}

function closeBranchDlg(data) {
	branchDlg.hide();
}

var productDlg = null;
var productFrameId = 'ifr_product';
function setProduct(row) {
	var _title = "设置出品";
	var _url = appRoot + "/hq/brand/product/mainView.action";
	_url += "?brandId=" + row.brandId;
	_url = getUrl(_url);
	
	if (productDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : productFrameId,
			width : "450px",
			height : "320px"
		}
		createDialog(option, function(iDlg) {
			productDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(productFrameId).contentWindow;
		ifrWindow.location = _url;
		productDlg.show();
	}
}

function closeProductDlg() {
	productDlg.hide();
}

var selProductDlg = null;
var selectedRows = [];
function selProduct() {
	var ifrWindow = dojo.byId(productFrameId).contentWindow;
	selectedRows = ifrWindow.getSelectedRows();// 初始化被选择的记录
	var _url = appRoot + "/hq/brand/selproduct/mainView.action";
	_url = getUrl(_url);
	
	var frameId = 'ifr_selproduct';
	if (selProductDlg == null) {
		var option = {
			title : "选择出品",
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "250px"
		}
		createDialog(option, function(iDlg) {
			selProductDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		selProductDlg.show();
	}
}

/**
 * 由于设置出品与选择出品两个对话框大小重叠的问题，设置出品对话框要通过父页面调用选择出品对话框，
 * 关系比较绕，关闭选择出品对话框后要将数据传回到设置出品对话框中
 */
function afterSelProduct(selRows) {
	var ifrWindow = dojo.byId(productFrameId).contentWindow;
	ifrWindow.afterSelProduct(selRows);
	selProductDlg.hide();
}

var priceDlg = null;
var priceFrameId = 'ifr_price';
function setPrice(row) {
	var _url = appRoot + "/hq/pgroup/brand/setprice/mainView.action";
	_url = getUrl(_url);
	
	_url += "?priceGroupId=" + row.brandId + "_default";
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
