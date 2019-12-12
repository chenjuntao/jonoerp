require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doValidate() {
	var brandId = dojo.byId('brandId').value.trim();
	if (brandId == '') {
		alert('编号不能为空！');
		dojo.byId('brandId').focus();
		return false;
	}
	if (brandId != oldBrandId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('brandId').focus();
		return false;
	}
	var brandName = dojo.byId('brandName').value.trim();
	if (brandName == '') {
		alert('名称不能为空！');
		dojo.byId('brandName').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/brand/checkBrandId.action?brandId=" + dojo.byId('brandId').value;
	_url = getUrl(_url);
	
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
	var _url = appRoot + "/hq/brand/j/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.brandId = oldBrandId;
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
