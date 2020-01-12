require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doSave() {
	var menuId = dojo.byId('menuId').value;
	if (menuId.trim() == '') {
		alert('菜单编码不能为空！');
		return;
	}
	if (isRepeat()) {
		dojo.byId('menuId').focus();
		return false;
	}
	var menuName = dojo.byId('menuName').value;
	if (menuName.trim() == '') {
		alert('菜单名称不能为空！');
		return;
	}
	if (!validateNum('priority', '排序字段', true)) {
		dojo.byId('priority').focus();
		return false;
	}
	var _url = appRoot + "/setting/menu/saveMenu.action";
	_url = getUrl(_url);
	
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

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/setting/menu/checkMenuId.action?menuId=" + dojo.byId('menuId').value;
	_url = getUrl(_url);
	
	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				alert('编码已经被"' + data.menuName + '"占用！');
				exist = true;
			}
		}, function(err) {
		});
	});
	return exist;
}


function doClose() {
	parent.closeEditDlg();
}