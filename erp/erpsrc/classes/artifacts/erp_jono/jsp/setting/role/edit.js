require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doValidate() {
	var roleId = dojo.byId('roleId').value.trim();
	if (roleId == '') {
		alert('编号不能为空！');
		dojo.byId('roleId').focus();
		return false;
	}
	if (roleId != oldRoleId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('roleId').focus();
		return false;
	}
	var roleName = dojo.byId('roleName').value.trim();
	if (roleName == '') {
		alert('姓名不能为空！');
		dojo.byId('roleName').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/setting/role/checkRoleId.action?roleId=" + dojo.byId('roleId').value;
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
	var _url = appRoot + "/setting/role/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.roleId = oldRoleId;
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
