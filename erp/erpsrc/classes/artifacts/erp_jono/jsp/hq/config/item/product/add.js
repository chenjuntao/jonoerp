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
	if (itemId != oldItemId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('itemId').focus();
		return false;
	}
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName == '') {
		alert('名称不能为空！');
		dojo.byId('itemName').focus();
		return false;
	}
//	var queryCode = dojo.byId('queryCode').value.trim();
//	if (queryCode == '') {
//		alert('助记码不能为空！');
//		dojo.byId('queryCode').focus();
//		return false;
//	}
//	var itemSpecification = dojo.byId('itemSpecification').value.trim();
//	if (itemSpecification == '') {
//		alert('规格不能为空！');
//		dojo.byId('itemSpecification').focus();
//		return false;
//	}
	var itemDimension = dojo.byId('itemDimension').value.trim();
	if (itemDimension == '') {
		alert('库存单位不能为空！');
		dojo.byId('itemDimension').focus();
		return false;
	}
	if (!validateNum('salePrice', '售卖价', true)) {
		dojo.byId('salePrice').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/item/meta/checkItemId.action?itemId=" + dojo.byId('itemId').value;
	
	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				exist = true;
			}
		}, function(err) {
		});
	});
	return exist;
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
