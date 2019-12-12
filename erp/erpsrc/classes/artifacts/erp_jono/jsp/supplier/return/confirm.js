require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
	});
});

function addEvent() {
	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(formId);
	};
}

function doConfirm() {
	dojo.byId('btn_submit').disabled = true;
	var _url = appRoot + "/sp/return/doConfirm.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				formId : formId,
				status : '已退货确认'
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("采购退货确认成功！");
				closeTab(tabId, 'doQuery');
			} else {
				alert("采购退货确认失败！");
			}
		}, function(err) {
		});
	});
}
