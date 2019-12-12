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
	_url = getUrl(_url);
	
	var _params = {};
	var flag=2;
	console.log(_params)
	token = new Date().getTime(); //use the current timestamp as the token value
	dojo.byId('downloadTokenValue').value = token;
	
	require(["dojo/io/iframe" ], function(ioIframe) {
		standby.show();
		int = setInterval("isFinished()",1000);
		console.log(_params)
		ioIframe.send({
			form : "queryForm",
			url : _url,
			content : {
				flag : flag,
				downloadTokenValue : token
			},
			method : "POST",
			handleAs : "json"
		}).then(function(data) {
			if (data.msg != null && parseInt(data.msg) == 1) {
				alert("系统繁忙，请稍后再试！");
				closeTab(tabId);
			}
		}, function(err) {
			alert(err);
		});
	});
}