require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doValidate() {
	var priceGroupId = dojo.byId('priceGroupId').value.trim();
	if (priceGroupId == '') {
		alert('编号不能为空！');
		dojo.byId('priceGroupId').focus();
		return false;
	}
	if (priceGroupId != oldGroupId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('priceGroupId').focus();
		return false;
	}
	var priceGroupName = dojo.byId('priceGroupName').value.trim();
	if (priceGroupName == '') {
		alert('名称不能为空！');
		dojo.byId('priceGroupName').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/pgroup/lc/checkGroupId.action?priceGroupId=" + dojo.byId('priceGroupId').value;
	
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
	var _url = appRoot + "/hq/pgroup/lc/doSave.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.priceGroupId = oldGroupId;
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
