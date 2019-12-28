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
	var _url = appRoot + "/hq/fpgroup/lc/checkGroupId.action?priceGroupId=" + dojo.byId('priceGroupId').value;
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

function categoryChange(){
	var theType = dojo.byId("type").value;
	if (theType == 1){
		dojo.byId("preId").innerHTML = 'JOIN_';//+dojo.byId("priceGroupId").value;
		}
	if (theType == 2){
		dojo.byId("preId").innerHTML = 'BENCHMARK_';//+dojo.byId("priceGroupId").value;
	}
	if (theType == 3){
		dojo.byId("preId").innerHTML = 'RETAIL_';//+dojo.byId("priceGroupId").value;
	}
	if (theType == 4){
		dojo.byId("preId").innerHTML = 'SALE_';//+dojo.byId("priceGroupId").value;
	}
}

function doSave() {
	if (!doValidate()) {
		return;
	}
	dojo.byId("priceGroupId").value = dojo.byId("preId").innerHTML + dojo.byId("priceGroupId").value;
	var _url = appRoot + "/hq/fpgroup/lc/doSave.action";
	_url = getUrl(_url);
	
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
