require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function transfer() {
	require([ "dojo/request/xhr", "dojo/dom" ], function(xhr, dom) {
		var dataForm = dom.byId("dataForm");
		dataForm.action = _url;
		dom.byId("jsonData").value = JSON.stringify(json);
		dataForm.submit();
		return;
		xhr.post(_url, {
			handleAs : "json",
			data : {
				jsonData : JSON.stringify(json)
			}
		}).then(function(data) {
		}, function(err) {
		});
	});
}

var json = {
	"日志服务" : "",
	"按时间查询日志" : "businessBasbusinessQueryByTime.jsp",
	"按操作查询日志" : "businessBasbusinessQueryByTime.jsp",
	"系统维护" : "",
	"基本信息备份" : "businessBasbusinessQueryByTime.jsp",
	"基本信息恢复" : "businessBasbusinessQueryByTime.jsp",
	"系统信息备份" : "businessBasbusinessQueryByTime.jsp",
	"系统信息恢复" : "businessBasbusinessQueryByTime.jsp",
	"业务信息备份" : "businessBasbusinessQueryByTime.jsp",
	"业务信息恢复" : "businessBasbusinessQueryByTime.jsp",
	"业务信息删除" : "businessBasbusinessQueryByTime.jsp"
};