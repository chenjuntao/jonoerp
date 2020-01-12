require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

var intReg = /^\d+$/g;
function validateInt(value) {
	if (value == '' || value.match(intReg) == null) {
		console.log(value);
		return false;
	}
	return true;
}

function doValidate() {
	var regionId = dojo.byId('regionId').value.trim();
	if (regionId == '') {
		alert('编号不能为空！');
		dojo.byId('regionId').focus();
		return false;
	}
	if (regionId[0] != 'r') {
		alert('编号请以小写r开头！');
		dojo.byId('regionId').focus();
		return false;
	}
	if (regionId != oldRegionId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('regionId').focus();
		return false;
	}
	var regionName = dojo.byId('regionName').value.trim();
	if (regionName == '') {
		alert('名称不能为空！');
		dojo.byId('regionName').focus();
		return false;
	}
	var deliveryCycle = dojo.byId('deliveryCycle').value.trim();
	if (!validateInt(deliveryCycle)) {
		alert("配送周期只能为非负整数！");
		dojo.byId('deliveryCycle').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/group/checkGroup.action?regionId=" + dojo.byId('regionId').value;
	
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
	var _url = appRoot + "/hq/group/doSave.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.regionId = oldRegionId;
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
