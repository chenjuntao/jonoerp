var msgDlg;
function showLog(CBILL_C) {
	
	require(["dijit/Dialog"], function() {
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
	
	
	require(["dojo/_base/xhr" ], function(xhr) {  
        xhr.post({
	    	url : "getBillPay.action?CBILL_C=" + CBILL_C,
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
