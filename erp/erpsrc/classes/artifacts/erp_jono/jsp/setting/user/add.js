require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		dojo.byId('roleId').value = '';
	});
});

function doValidate() {
	var userId = dojo.byId('userId').value.trim();
	if (userId == '') {
		alert('编号不能为空！');
		dojo.byId('userId').focus();
		return false;
	}
	if (isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('userId').focus();
		return false;
	}
	var userName = dojo.byId('userName').value.trim();
	if (userName == '') {
		alert('姓名不能为空！');
		dojo.byId('userName').focus();
		return false;
	}
	var roleId = dojo.byId('roleId').value.trim();
	if (roleId == '') {
		alert('请先选择角色！');
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/setting/user/checkUserId.action?userId=" + dojo.byId('userId').value;

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
	var _url = appRoot + "/setting/user/doSave.action";

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
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

function setRole() {
	parent.setRole();
}
/**
 * 接收父窗口传回的数据
 * 
 * @param _data
 */
function closeRoleDlg(_data) {
	dojo.byId('roleId').value = _data.roleId;
	dojo.byId('roleText').innerHTML = _data.roleName;
}
