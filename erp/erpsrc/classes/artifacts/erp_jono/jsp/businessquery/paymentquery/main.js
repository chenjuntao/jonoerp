var loadingMsg = getLoadingMsg();

function doQuery() {
	var type = document.getElementById("type").value;
	if (type == "1") {
		dojo.byId("dataGrid").innerHTML = loadingMsg;

		var _url = appRoot + "/businessquery/paymentquery/doQuery.action";
		_url = getUrl(_url);
		
		require([ "dojo/_base/xhr" ], function(xhr) {
			xhr.post({
				url : _url,
				form : "queryForm",
				handleAs : "text",
				load : function(data) {
					dojo.byId("dataGrid").innerHTML = data;
					return;
				},
				error : function(error) {
					alert(error);
				}
			});
		});
		return false
	} else {
		var currentPage = 1;
		var cpInput = dojo.byId("currentPage");
		if (cpInput != null) {
			currentPage = cpInput.value;
		}
		pageQuery(currentPage);
	}
}


var msgDlg;
function showLog(CBILL_C) {

	require([ "dijit/Dialog" ], function() {
		if (!msgDlg) {
			var pane = dojo.byId('msgDlg');
			// should specify a width on dialogs with <p> tags, otherwise they
			// get too wide
			msgDlg = new dijit.Dialog({
				id : "msgDlg",
				style : {
					width : '500px'
				},
				title : "付款方式"
			}, pane);
		}

	});

	require([ "dojo/_base/xhr" ], function(xhr) {
		xhr.post({
			url : appRoot + "/businessquery/billInfoQuery/"
					+ "getBillPay.action?CBILL_C=" + CBILL_C,
			handleAs : "text",
			load : function(data) {
				dojo.byId('msgContent').innerHTML = data;
				msgDlg.show();
				return;
			},
			error : function(error) {
				showDojoDialog(error);
			}
		});
	});
}




