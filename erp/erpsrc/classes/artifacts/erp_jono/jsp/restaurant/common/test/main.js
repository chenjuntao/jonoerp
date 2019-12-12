
var productDlg = null;
function selProduct() {
	var frameId = 'ifr_selProduct';
	if (productDlg == null) {
		var _url = appRoot + "/restaurant/common/selproduct/mainView.action";
		
		var option = {
			title : "选择出品",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		productDlg = createDialog(option);
	} else {
		productDlg.show();
	}
}

function closeProductDlg(data) {
	console.log(data);
	productDlg.hide();
}


