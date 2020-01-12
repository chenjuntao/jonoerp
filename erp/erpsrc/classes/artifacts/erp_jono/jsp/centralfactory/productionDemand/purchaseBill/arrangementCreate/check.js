
function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;

function doSave() {
	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
		var formId = dom.byId('formId').value;// 对汇总单进行状态验证
		var sdata = getCurrentStatus(formId);
		if (sdata.formStatus == '未审核') {
			alert("单据已汇总！");
			return false;
		}
//		var ids = dom.byId('ids').value;
//		var idArr = ids.split(",");
//		sdata = getCurrentStatus(idArr[0]);
//		if (sdata.formStatus == '已汇总') { // 对引用的订货及预估单进行状态验证
//			alert("单据已汇总！");
//			return false;
//		}

		var _url = appRoot + "/centralfactory/productionDemand/purchaseBill/arrangementCreate/createSummaryBill.action";
		_url = getUrl(_url);	
		
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("提交成功！");
				closeTab(tabId, 'doClose');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
