require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doSave() {
	var categoryId = dojo.byId('categoryId').value;
	var parId = dojo.byId('parId').value;
	if (parId == 4) {
		if (categoryId.indexOf('40') != 0) {
			alert('套餐编码必须以40开头！');
			return;
		}
	} else {
		if (categoryId.indexOf('40') == 0) {
			alert('非套餐编码不能以40开头！');
			return;
		}
	}
	if (categoryId.trim() == '') {
		alert('类别编码不能为空！');
		return;
	}
	if (isRepeat()) {
		dojo.byId('categoryId').focus();
		return false;
	}
	var categoryName = dojo.byId('categoryName').value;
	if (categoryName.trim() == '') {
		alert('类别名称不能为空！');
		return;
	}
	var _url = appRoot + "/hq/category/raw/saveCate.action";
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
	var _url = appRoot + "/hq/category/raw/checkCateId.action?categoryId=" + dojo.byId('categoryId').value;
	_url = getUrl(_url);

	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				alert('编号已经被"' + data.categoryName + '"占用！');
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