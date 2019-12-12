
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
	console.log(_params)
	var month = dojo.byId('month').value;
	if (month != "") {
		_params.month = month;
	} else {
		alert("请选择备份月份！");
		return;
	}
	
	var bIds = dojo.byId("branchId").value;
	if(bIds!=""){
		
	}else{
		alert("请选择备份门店！");
		return;
	}
	token = new Date().getTime(); //use the current timestamp as the token value
	dojo.byId('downloadTokenValue').value = token;
	
	require(["dojo/io/iframe" ], function(ioIframe) {
		var flag = 4;
		standby.show();
		int = setInterval("isFinished()",10000);
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
			if (data.msg != null && parseInt(data.msg) == 2) {
				alert("导出成功！");
				closeTab(tabId);
			}else{
				alert("系统繁忙，请稍后再试！");
				closeTab(tabId);
			}
		}, function(err) {
			alert(err);
		});
	});
}