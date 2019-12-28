require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doValidate() {
	var branchId = dojo.byId('branchId').value.trim();
	if (branchId == '') {
		alert('编号不能为空！');
		dojo.byId('branchId').focus();
		return false;
	}
	
	if (branchId != oldBranchId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('branchId').focus();
		return false;
	}
	
	var branchName = dojo.byId('branchName').value.trim();
	if (branchName == '') {
		alert('名称不能为空！');
		dojo.byId('branchName').focus();
		return false;
	}
	
	var queryCode = dojo.byId('queryCode').value.trim();
	if (queryCode == '') {
		alert('助记码不能为空！');
		dojo.byId('queryCode').focus();
		return false;
	}

	var email = dojo.byId('email').value.trim();
	if (email != '' && !isMail(email)) {
		dojo.byId('email').focus();
		return false;
	}

	var telephone = dojo.byId('telephone').value.trim();
	if (telephone != '' && !isTel(telephone)) {
		dojo.byId('telephone').focus();
		return false;
	}

	var faxNum = dojo.byId('fax').value.trim();
	if (faxNum != '' && !isTel(faxNum, 'fax')) {
		dojo.byId('fax').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/branch/r/checkBranchId.action?branchId=" + dojo.byId('branchId').value;
	_url = getUrl(_url);
	
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

function isMail(_val) {
	var pattern = /^[a-zA-Z0-9_\-]{1,}@[a-zA-Z0-9_\-]{1,}\.[a-zA-Z0-9_\-.]{1,}$/;
	if (!pattern.exec(_val)) {
		alert('请输入正确的邮箱地址！');
		return false;
	}
	return true;
}

// 校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
function isTel(_val, _type) {
	// 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"

	var pattern = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	
	if (!pattern.exec(_val)) {
		var typeText = "电话号码";
		if (_type == 'fax') {
			typeText = "传真号码";
		}
		
		alert('请输入正确的' + typeText + '！\n电话号码格式为：国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"');
		
		return false;
	}
	return true;
}

function doSave() {
	if (!doValidate()) {
		return;
	}
	var _url = appRoot + "/hq/branch/r/saveRestaurant.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.branchId = oldBranchId;
		
/*		var priceType = 'JOIN';
		if (g_branchType == 'RESTAURANT') {
			priceType = 'BENCHMARK';
		}
		
		dataObj['branch.priceType'] = priceType;*/
		
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
