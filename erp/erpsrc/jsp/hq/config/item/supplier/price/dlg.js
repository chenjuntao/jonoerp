var materialDlg = null;
var selectedRows = [];
function selMaterial() {
	selectedRows = []; // 兼容两种操作方式，确定后重载主表格数据或者追加数据
	var frameId = 'ifr_selMaterial';
	var _url = appRoot + "/hq/priceadjust/selitem/mainView.action";
	_url += "?adjustType=RESTAURANT";// 取餐厅标准价做为参考
	
	if (materialDlg == null) {
		var option = {
			title : "选择原料",
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

function closeItemDlg(data) {
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
