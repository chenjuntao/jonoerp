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

	var itemSpecification = dojo.byId('itemSpecification').value.trim();
	if (itemSpecification == '') {
		alert('规格不能为空！');
		dojo.byId('itemSpecification').focus();
		return false;
	}

	var outReceiveRate = dojo.byId('outReceiveRate').value.trim();
	if (outReceiveRate == '' || outReceiveRate <= 0) {
		alert('超收率不能为空或小于等于0！');
		dojo.byId('outReceiveRate').focus();
		return false;
	}

	var itemDimension = dojo.byId('itemDimension').value.trim();
	if (itemDimension == '') {
		alert('库存单位不能为空！');
		dojo.byId('itemDimension').focus();
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
	if (recipeFactor == '' || recipeFactor == 0) {
		alert('配方单位转化因子不能为空或为0！');
		dojo.byId('recipeFactor').focus();
		return false;
	}
	if (!validateNum('recipeFactor', '配方单位转换因子', true)) {
		dojo.byId('recipeFactor').focus();
		return false;
	}
	var deliveryUnit = dojo.byId('deliveryUnit').value.trim();
	if (deliveryUnit == '') {
		alert('包装单位不能为空！');
		dojo.byId('deliveryUnit').focus();
		return false;
	}

	var deliveryFactor = dojo.byId('deliveryFactor').value.trim();
	if (deliveryFactor == '' || deliveryFactor == 0) {
		alert('包装单位转化因子不能为空或为0！');
		dojo.byId('deliveryFactor').focus();
		return false;
	}

	if (!validateNum('deliveryFactor', '配送单位转换因子', true)) {
		dojo.byId('deliveryFactor').focus();
		return false;
	}
	if (!validateNum('unitVolume', '单位体积', true)) {
		dojo.byId('unitVolume').focus();
		return false;
	}
	if (!validateNum('unitWeight', '单位重量', true)) {
		dojo.byId('unitWeight').focus();
		return false;
	}
	if (!validateNum('purchasePrice', '进货价', true)) {
		dojo.byId('purchasePrice').focus();
		return false;
	}
	if (!validateNum('benchmarkPrice', '标准价', true)) {
		dojo.byId('benchmarkPrice').focus();
		return false;
	}
	if (!validateNum('joinPrice', '加盟价', true)) {
		dojo.byId('joinPrice').focus();
		return false;
	}
	if (!validateNum('retailPrice', '零售价', true)) {
		dojo.byId('retailPrice').focus();
		return false;
	}
	if (!validateNum('wholesalePrice', '批发价', true)) {
		dojo.byId('wholesalePrice').focus();
		return false;
	}

	isItemNameRepeat();
	if (!isEmpty(info)) {
		if (confirm(info)) {
			dojo.byId('itemName').focus();
			return true;
		} else {
			dojo.byId('itemName').focus();
			return false;
		}
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
			} else {
				info = null;
				exist = false;
			}
		}, function(err) {
		});
	});
	return exist;
}

function isItemName(itemId) {
	var result = {};
	var _url = appRoot + "/restaurant/goodsbill/query/getCurrentStatus.action?formId=" + _formId;
	_url += "&timestamp=" + new Date().getTime(); // 防止缓存
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			result = data;
		}, function(err) {
		});
	});
	return result;
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
