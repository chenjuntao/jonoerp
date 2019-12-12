require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		if(tagId){
			dojo.byId('tagId').disabled = true;
		}
	});
});

function doValidate() {
	var tagId = dojo.byId('tagId').value.trim();
	if (tagId == '') {
		alert('编号不能为空！');
		dojo.byId('tagId').focus();
		return false;
	}
	
	var tagName = dojo.byId('tagName').value.trim();
	if (tagName == '') {
		alert('名称不能为空！');
		dojo.byId('tagName').focus();
		return false;
	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/config/tag/checkTagId.action?tagId=" + dojo.byId('tagId').value;
	
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
	
	if(!tagId){
		if(isRepeat()){
			alert(tagId+"编码已被占用！")
			return;
		}
	}
	
	var _url = appRoot + "/hq/config/tag/doSave.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj['tagHeader.tagId'] = dojo.byId('tagId').value;
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
