require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		// addEvent();
	});
});
function importMaterial() {
	var fileurl = dojo.byId("fileurl").value;
	if (fileurl == "") {
		alert("请选择文件！");
		return;
	}

	if (fileurl.match(/\.(zip)$/i) == null) {
		alert("请选择zip文件包！");
		return;
	}

	if (fileurl.indexOf("基本信息") == -1) {
		alert("请选择基本信息文件包！");
		return;
	}
	var _url = appRoot + "/setting/import/doInBranch.action?stuta=1";
	// _url = getUrl(_url);

	require([ "dojo/io/iframe", "custom/Loading" ], function(ioIframe, Mask) {
		var mask = new Mask();
		mask.show();
		ioIframe.send({
			form : "uploadForm",
			url : _url,
			handleAs : "json"
		}).then(function(data) {
			if (data.msg != null && parseInt(data.msg) == 2) {
				alert("导入成功！");
				closeTab(tabId);
			} else if (data.msg != null && parseInt(data.msg) == 0) {
				alert("数据已存在，请不要重复导入！");
				closeTab(tabId);
			} else {
				alert("系统繁忙,请稍后再试！");
				closeTab(tabId);
			}
		}, function(err) {
			alert(err);
		});
	});
}