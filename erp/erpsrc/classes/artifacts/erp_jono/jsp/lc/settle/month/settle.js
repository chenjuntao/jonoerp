require([ "dojo/domReady!" ], function() {
});

function getWaittingMsg(waittingMsg) {
	var imgSrc = appRoot + "/jsp/common/img/wait.gif";
	var msg = '<div style="text-align: center; padding-top: 50px; height: 100px;">'
			+ '<div style="vertical-align: middle; font-size:20px;">' + waittingMsg + '</div>' + '<img src="' + imgSrc
			+ '"> </div>';
	return msg;
}

function doSubmit() {
	dojo.byId("btnSubmit").disabled = 'disabled';
	saveSettleRecord();
}

function saveSettleRecord() {
	var _url = appRoot + '/restaurant/dailysettlement/saveSettleRecord.action';
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("settleForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				dojo.byId("waitingDiv").innerHTML = "月结成功！";
			} else {
				dojo.byId("waitingDiv").innerHTML = "月结失败！";
			}
		}, function(err) {
		});
	});
}
