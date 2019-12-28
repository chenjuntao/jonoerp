require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doSave() {
	var storageId = dojo.byId('storageId').value;
	
	if (storageId.trim() == '') {
		alert('仓库编码不能为空！');
		return;
	}
	if (isRepeat()) {
		dojo.byId('storageId').focus();
		return false;
	}
	var storageName = dojo.byId('storageName').value;
	if (storageName.trim() == '') {
		alert('仓库名称不能为空！');
		return;
	}
	var _url = appRoot + "/hq/storage/set/saveStorage.action";
	
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
	var _url = appRoot + "/hq/storage/set/checkId.action?storageId=" + dojo.byId('storageId').value;
	
	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				alert('编号已经被"' + data.storageName + '"占用！');
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