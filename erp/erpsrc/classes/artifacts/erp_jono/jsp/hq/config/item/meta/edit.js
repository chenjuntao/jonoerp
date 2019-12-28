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

	if (itemName != oldItemName && isItemNameRepeat()) {
		if (confirm(info)) {
			dojo.byId('itemName').focus();
			return true;
		} else {
			dojo.byId('itemName').focus();
			return false;
		}

	}

	var minOrderCount = dojo.byId('minOrderCount').value.trim();
	var oldMinOrderCount = dojo.byId('oldMinOrderCount').value.trim();
	if (minOrderCount == '' || minOrderCount == 0) {
		alert('最小订货倍量不能为空或为0！');
		dojo.byId('minOrderCount').focus();
		return false;
	}

	if (!validateNum('minOrderCount', '最小订货倍量', true)) {
		dojo.byId('minOrderCount').focus();
		return false;
	}

	if (minOrderCount != oldMinOrderCount) {
		if (!confirm("确定修改最小订货倍量吗？")) {
			return;
		}
	}

	// var queryCode = dojo.byId('queryCode').value.trim();
	// if (queryCode == '') {
	// alert('助记码不能为空！');
	// dojo.byId('queryCode').focus();
	// return false;
	// }
	var itemSpecification = dojo.byId('itemSpecification').value.trim();
	if (itemSpecification == '') {
		alert('规格不能为空！');
		dojo.byId('itemSpecification').focus();
		return false;
	}
	var itemDimension = dojo.byId('itemDimension').value.trim();
	if (itemDimension == '') {
		alert('库存单位不能为空！');
		dojo.byId('itemDimension').focus();
		return false;
	}

	var outReceiveRate = dojo.byId('outReceiveRate').value.trim();
	if (outReceiveRate == '' || outReceiveRate <= 0) {
		alert('超收率不能为空或小于等于0！');
		dojo.byId('outReceiveRate').focus();
		return false;
	}

	if (!validateNum('outReceiveRate', '超收率', true)) {
		dojo.byId('outReceiveRate').focus();
		return false;
	}
	var recipeUnit = dojo.byId('recipeUnit').value.trim();
	if (recipeUnit == '') {
		alert('配方单位不能为空！');
		dojo.byId('recipeUnit').focus();
		return false;
	}

	var recipeFactor = dojo.byId('recipeFactor').value.trim();
	var oldRecipeFactor = dojo.byId('oldRecipeFactor').value.trim();
	if (recipeFactor == '' || recipeFactor == 0) {
		alert('配方单位转化因子不能为空或为0！');
		dojo.byId('recipeFactor').focus();
		return false;
	}

	if (!validateNum('recipeFactor', '配方单位转换因子', true)) {
		dojo.byId('recipeFactor').focus();
		return false;
	}

	if (recipeFactor != oldRecipeFactor) {
		if (!confirm("确定修改配方单位转化因子吗？")) {
			return;
		}
	}

	var deliveryUnit = dojo.byId('deliveryUnit').value.trim();
	if (deliveryUnit == '') {
		alert('包装单位不能为空！');
		dojo.byId('deliveryUnit').focus();
		return false;
	}

	var deliveryFactor = dojo.byId('deliveryFactor').value.trim();
	var oldDeliveryFactor = dojo.byId('oldDeliveryFactor').value.trim();
	if (deliveryFactor == '' || deliveryFactor == 0) {
		alert('包装单位转化因子不能为空或为0！');
		dojo.byId('deliveryFactor').focus();
		return false;
	}
	if (!validateNum('deliveryFactor', '包装单位转换因子', true)) {
		dojo.byId('deliveryFactor').focus();
		return false;
	}

	if (deliveryFactor != oldDeliveryFactor) {
		if (!confirm("确定修改包装单位转化因子吗？")) {
			return;
		}
	}

	if (!validateNum('unitVolume', '单位体积', true)) {
		dojo.byId('unitVolume').focus();
		return false;
	}
	if (!validateNum('unitWeight', '单位重量', true)) {
		dojo.byId('unitWeight').focus();
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

var info = null;
/**
 * 验证名称是否重复
 */
function isItemNameRepeat() {
	var _url = appRoot + "/hq/item/meta/checkItemName.action?itemName=" + dojo.byId('itemName').value;

	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				exist = true;
				info = data.info;
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
	var _url = appRoot + "/hq/item/meta/doSave.action";

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
