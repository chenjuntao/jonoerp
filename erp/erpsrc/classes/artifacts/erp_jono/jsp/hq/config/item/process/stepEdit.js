require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

var intReg = /^[0-9]*[1-9][0-9]*$/g;
function validateInt(value, _text) {
	if (value == '' || value.match(intReg) == null) {
		return false;
	}
	return true;
}

function doValidate() {
	var step = dojo.byId('step').value.trim();
	if (!validateInt(step)) {
		alert("顺序只能为整数！");
		dojo.byId('step').focus();
		return false;
	}
	if (step != oldStep && isRepeat()) {
		alert('该顺序已经存在！');
		dojo.byId('step').focus();
		return false;
	}
	var stepOperation = dojo.byId('stepOperation').value.trim();
	if (stepOperation == '') {
		alert('具体操作不能为空！');
		dojo.byId('stepOperation').focus();
		return false;
	}
	return true;
}

/**
 * 验证顺序是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/item/process/checkStep.action?itemId=" + g_itemId + "&step=" + dojo.byId('step').value;
	
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
	var _url = appRoot + "/hq/item/process/doSave.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.step = oldStep;
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


