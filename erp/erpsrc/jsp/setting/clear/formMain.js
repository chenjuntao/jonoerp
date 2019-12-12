require([ "dojox/widget/Standby", "dojo", "dojo/ready" ], function(Standby, dojo, ready) {
	ready(function() {
		// 初始化遮罩层
		standby = new Standby({
			target : "queryForm"
		});

		document.body.appendChild(standby.domNode);

		standby.startup();
	});
});

var branchDlg = null;

function exportXls() {
	var _params = {};
	var month = dojo.byId('month').value;
	if (month != "") {
		_params.month = month;
	} else {
		alert("请选择清楚月份！");
		return;
	}
	if (confirm("确定要清空数据吗？")) {
		var _url = appRoot + "/setting/clear/clearForms.action";
		_url = getUrl(_url);

		standby.show();
		require([ "dojo/io/iframe" ], function(ioIframe) {
			console.log(_params)
			ioIframe.send({
				form : "queryForm",
				url : _url,
				content : {
					month : month,
				},
				method : "POST",
				handleAs : "json"
			}).then(function(data) {
				standby.hide();
				if (data.msg != null) {
					alert(data.sidLst);
					closeTab(tabId);
				} else {
					alert("系统繁忙，请稍后再试！");
					closeTab(tabId);
				}
			}, function(err) {
				alert(err);
			});
		});
	} else {
		return;
	}

}
function closeBranchDlg(_ids, _names) {
	dojo.byId('branchId').value = _ids;
	dojo.byId('branchText').innerHTML = _names;
	branchDlg.hide();
}
