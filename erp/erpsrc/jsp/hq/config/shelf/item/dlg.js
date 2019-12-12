var materialDlg = null;
var selectedRows = [];
function addItem() {
	selectedRows = []; 
	var frameId = 'ifr_additem';
	var _url = appRoot + "/hq/shelfitem/additem/mainView.action?shelfId=" + g_shelfId;
	
	if (materialDlg == null) {
		var option = {
			title : "新增商品",
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
		ifrWindow.location = _url;
		materialDlg.show();
	}
}

function closeItemDlg() {
	queryItem(g_shelfId);
	materialDlg.hide();
}
