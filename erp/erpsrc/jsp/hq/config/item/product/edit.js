require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doValidate() {
	var itemId = dojo.byId('itemId').value.trim();
	if (itemId == '') {
		alert('编号不能为空！');
		dojo.byId('itemId').focus();
		return false;
	}
	
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName == '') {
		alert('名称不能为空！');
		dojo.byId('itemName').focus();
		return false;
	}

	var itemDimension = dojo.byId('itemDimension').value.trim();
	if (itemDimension == '') {
		alert('例牌不能为空！');
		dojo.byId('itemDimension').focus();
		return false;
	}
	return true;
}

function doSave() {
	if (!doValidate()) {
		return;
	}
	var _url = appRoot + "/hq/item/product/doSave.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.itemId = oldItemId;
		xhr.post(_url, {
			handleAs : "json",
			data : dataObj
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
				doClose();
			} else {
				alert("保存失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	parent.closeEditDlg();
}
