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
var selectedRows = [];
function selBranch() {

	var lastMonth = dojo.byId('lastMonth').value;

	var frameId = 'ifr_selBranch';
	var month = dojo.byId("month").value;

	dojo.byId('lastMonth').value = month;
	if (branchDlg != null && lastMonth == month) {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		branchDlg.show();
	} else {
		var _url = appRoot + "/backup/selStore/mainView.action?month=" + month;
		_url = getUrl(_url);

		var option = {
			title : "选择门店",
			url : _url,
			frameId : frameId,
			width : "350px",
			height : "350px"
		}
		createDialog(option, function(iDlg) {
			branchDlg = iDlg;
		});
	}

	// if (branchDlg == null) {
	//		
	// } else {
	// var ifrWindow = dojo.byId(frameId).contentWindow;
	// branchDlg.show();
	// }
}
function exportXls() {
	var _params = {};
	console.log(_params)
	var month = dojo.byId('month').value;
	if (month != "") {
		_params.month = month;
	} else {
		alert("请选择清除月份！");
		return;
	}

	var bIds = dojo.byId("branchId").value;
	if (bIds != "") {

	} else {
		alert("请选择清除门店！");
		return;
	}
	if (confirm("确定要清空数据吗？")) {
		var _url = appRoot + "/setting/clear/clearFoods.action";
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
