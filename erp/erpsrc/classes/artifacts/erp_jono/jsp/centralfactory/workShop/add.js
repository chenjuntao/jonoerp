require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doSave() {
	var workOrderId = dojo.byId('workOrderId').value;
	if (workOrderId.trim() == '') {
		alert('类别编码不能为空！');
		return;
	}
	if (isRepeat()) {
		dojo.byId('workOrderId').focus();
		return false;
	}
	var workshop = dojo.byId('workshop').value;
	if (workshop.trim() == '') {
		alert('类别名称不能为空！');
		return;
	}
	var _url = appRoot + "/cf/workorder/set/save.action";
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
	var _url = appRoot + "/cf/workorder/set/checkId.action?workOrderId=" + dojo.byId('workOrderId').value;
	_url = getUrl(_url);
	
	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				alert('编号已经被"' + data.workshop + '"占用！');
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