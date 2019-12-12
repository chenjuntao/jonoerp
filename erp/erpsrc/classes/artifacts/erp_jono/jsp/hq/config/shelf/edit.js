require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doValidate() {
	var shelfId = dojo.byId('shelfId').value.trim();
	if (shelfId == '') {
		alert('编号不能为空！');
		dojo.byId('shelfId').focus();
		return false;
	}
	if (shelfId != oldShelfId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('shelfId').focus();
		return false;
	}
	var shelfName = dojo.byId('shelfName').value.trim();
	if (shelfName == '') {
		alert('名称不能为空！');
		dojo.byId('shelfName').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/shelf/checkCode.action?shelfId=" + dojo.byId('shelfId').value;
	
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
	var _url = appRoot + "/hq/shelf/saveShelf.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.shelfId = oldShelfId;
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
