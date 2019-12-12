var doSubmit = null;
require([ "dojo/request/xhr", "dojo/dom-form", "dojo", "dojo/domReady!" ], function(xhr, domForm, dojo) {
	doSubmit = function() {
		dojo.byId("btnSubmit").disabled = 'disabled';

		var _url = appRoot + "/settle/month/checkStatus.action?monthDate=" + monthDate;
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.exist) {
				alert('当前月的月结工作已经完成！');
			} else {
				saveSettleRecord();
			}
		}, function(err) {
			errorHandler(err);
		});
	}

	function saveSettleRecord() {
		var _url = appRoot + '/settle/month/saveRecord.action';
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("settleForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert('月结成功！');
			} else {
				alert('月结失败！');
			}
		}, function(err) {
			errorHandler(err);
		});
	}

});

function getWaittingMsg(waittingMsg) {
	var imgSrc = appRoot + "/jsp/common/img/wait.gif";
	var msg = '<div style="text-align: center; padding-top: 50px; height: 100px;">'
			+ '<div style="vertical-align: middle; font-size:20px;">' + waittingMsg + '</div>' + '<img src="' + imgSrc
			+ '"> </div>';
	return msg;
}
