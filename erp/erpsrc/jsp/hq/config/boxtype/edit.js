require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doValidate() {
	var typeId = dojo.byId('typeId').value.trim();
	if (typeId == '') {
		alert('编号不能为空！');
		dojo.byId('typeId').focus();
		return false;
	}
	if (typeId != oldTypeId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('typeId').focus();
		return false;
	}
	var typeName = dojo.byId('typeName').value.trim();
	if (typeName == '') {
		alert('名称不能为空！');
		dojo.byId('typeName').focus();
		return false;
	}
	if (!validateNum('volume', '体积', true)) {
		dojo.byId('volume').focus();
		return false;
	}
	if (!validateNum('weight', '重量', true)) {
		dojo.byId('weight').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/boxtype/checkCode.action?typeId=" + dojo.byId('typeId').value;
	
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
	var _url = appRoot + "/hq/boxtype/saveType.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.typeId = oldTypeId;
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
