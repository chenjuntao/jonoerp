dojo.ready(function() {
	addEvent();
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
}


function putinStorage() {
	var _url = appRoot + "/cf/production/output/putinStorage.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("入库单生成成功！");
				doClose();
			} else {
				alert("入库单生成失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId);
}
