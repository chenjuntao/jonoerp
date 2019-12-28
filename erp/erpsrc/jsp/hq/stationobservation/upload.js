require([ "dojo", "dojo/ready" ], function(dojo, ready) {
});

// 保存
function uploadFile() {
	var instruction = dojo.byId("instruction").value;
	var fileurl = dojo.byId("fileurl").value;
	
	if(fileurl == ""){
		alert("示范图片不能为空！");
		return;
	}
	
	if(instruction == ""){
		alert("示范说明不能为空！");
		return;
	}
	
	if(instruction.length > 400){
		alert("示范说明已超过最大字符长度限制！");
		return;
	}
	
	var _url = appRoot + "/hq/station_observation/doUpload.action";
	
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
