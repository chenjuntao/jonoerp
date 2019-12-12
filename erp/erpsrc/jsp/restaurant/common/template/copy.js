dojo.ready(function() {
});

function doSave() {
	var templateName = dojo.byId('templateName').value;
	if (templateName.trim() == '') {
		alert("模板名称不能为空！");
		return;
	}

	var _url = appRoot + "/restaurant/common/template/doCopy.action";
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				templateId : templateId,
				'templateMeta.deliveryRegion' :dojo.byId('regionId').value,
				'templateMeta.templateName' : dojo.byId('templateName').value,
				'templateMeta.templateType' : templateType,
				'templateMeta.categoryId' : dojo.byId('categoryId').value,
				
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("复制成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	parent.closeCopyDlg();
}