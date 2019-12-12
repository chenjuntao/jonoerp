require([ "dojox/widget/Standby", "dojo", "dojo/ready" ], function(Standby,dojo, ready) {
	ready(function() {
		// 初始化遮罩层
		standby = new Standby({
			target : "queryForm"
		});
		
		document.body.appendChild(standby.domNode);
		
		standby.startup();
	});
});
function exportXls() {
	var _url = appRoot + "/setting/backup/exportCsv.action";
//	var _url = appRoot + "/setting/form/backup/exportForm.action";
	_url = getUrl(_url);
	
	var flag=3;
	var _params = {};
	console.log(_params)
	var month = dojo.byId('month').value;
	if (month != "") {
		_params.month = month;
	} else {
		alert("请选择备份月份！");
		return;
	}
	standby.show();
	//	post_redirect(_url, _params);
	token = new Date().getTime(); //use the current timestamp as the token value
	dojo.byId('downloadTokenValue').value = token;
	
	require(["dojo/io/iframe" ], function(ioIframe) {
		
		int = setInterval("isFinished()",4000);
		ioIframe.send({
			form : "queryForm",
			url : _url,
			content : {
				month : month,
				flag : flag,
				downloadTokenValue : token
			},
			method : "POST",
			handleAs : "json"
		}).then(function(data) {
			if (data.msg != null && parseInt(data.msg) == 1) {
				alert("本月单据数据为空，导出失败！");
				closeTab(tabId);
			}else {
				alert("系统繁忙，请稍后再试！");
				closeTab(tabId);
			}
		}, function(err) {
			alert(err);
		});
	});
}