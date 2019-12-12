require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

/**
 * 验证编号是否重复
 */
function checkOld() {
	var _url = appRoot + "/setting/user/password/checkOldPwd.action";
	_url = getUrl(_url);
	
	var match = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			sync : true,
			data : {
				userId : dojo.byId('userId').value,
				oldPwd : dojo.byId('oldPwd').value
			}
		}).then(function(data) {
			if (data.match) {
				match = true;
			}
		}, function(err) {
		});
	});
	return match;
}

function doValidate() {
	if (!checkOld()) {
		alert("现在的密码输入不正确！");
		return false;
	}
	var password = dojo.byId('password').value.trim();
	if (password == '') {
		alert('新的密码不能为空！');
		dojo.byId('password').focus();
		return false;
	}
	var confirmPwd = dojo.byId('confirmPwd').value.trim();
	if (confirmPwd != password) {
		alert('两次密码输入不一致，请重新输入！');
		dojo.byId('confirmPwd').focus();
		return false;
	}
	return true;
}

function doModify() {
	if (!doValidate()) {
		return;
	}
	var _url = appRoot + "/setting/user/password/doModify.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				userId : dojo.byId('userId').value,
				newPwd : dojo.byId('password').value
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("修改密码成功！");
			} else {
				alert("修改密码失败！");
			}
		}, function(err) {
		});
	});
}