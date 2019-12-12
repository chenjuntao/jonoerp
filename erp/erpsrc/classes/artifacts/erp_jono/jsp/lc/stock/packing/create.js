require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});


function doSubmit() {
	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ,"dojox/widget/Standby"], function(dom, xhr, domForm ,Standby) {
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
		// 显示遮罩层
		standby.show();
		var _url = appRoot + "/lc/stock/packing/createPackingBill.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			if (data.msg == 'ok') {
				alert("生成单据号为：" + data.formId + "，保存成功！");
				closeTab(tabId, 'doQuery');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
