require([ "dojo", "dojo/ready" ], function(dojo, ready) {
});

// 保存
function uploadFile() {
	var fileurl = dojo.byId("fileurl").value;
	if (fileurl == "") {
		alert("附件不能为空");
		return;
	}
	var _url = appRoot + "/hq/item/meta/doUpload.action";
	
	require([ "dojo/io/iframe", "custom/Loading" ], function(ioIframe, Mask) {
		var mask = new Mask();
		mask.show();
		ioIframe.send({
			form : "uploadForm",
			url : _url,
			handleAs : "json"
		}).then(function(data) {
			parent.closeUploadDlg();
		}, function(err) {
			alert(err);
			// Handle Error
		});
	});

}
