require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

var branchDlg = null;
var selectedRows = [];
function selBranch() {
	
	var lastMonth = dojo.byId('lastMonth').value;
	
	var frameId = 'ifr_selBranch';
	var month = dojo.byId("month").value;
	
	dojo.byId('lastMonth').value = month;
	if(branchDlg != null&&lastMonth==month){
		var ifrWindow = dojo.byId(frameId).contentWindow;
	    branchDlg.show();
	}else{
		var _url = appRoot + "/backup/selStore/mainView.action?month="+month;
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
	
//	if (branchDlg == null) {
//		
//	} else {
//		var ifrWindow = dojo.byId(frameId).contentWindow;
//		branchDlg.show();
//	}
}

function closeBranchDlg(_ids, _names) {
	dojo.byId('branchId').value = _ids;
	dojo.byId('branchText').innerHTML = _names;
	branchDlg.hide();
}
