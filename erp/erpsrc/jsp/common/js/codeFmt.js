function codeFmt(data, type, row) {
	return "<a href='javascript: doScan(\"" + data + "\");'>" + data + "</a>";
}

function operateFmt(data, type, row) {
	if (row.status == '未审核') {
		return "<a href='javascript: doAudit(\"" + data + "\");'>审核通过</a>";
	}
	if (row.formStatus == '未审核') {
		return "<a href='javascript: doAudit(\"" + data + "\");'>审核通过</a>";
	}
	return '';
}

function doScan(formId) {
var flag = false;
var _url = null;
	var testStr = formId.toUpperCase();

	if (testStr.indexOf("JHF") != -1) {
		_url = appRoot + "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId=" + formId;
		flag = true;
	}
	
	if (testStr.indexOf("PTH") != -1) {
		_url = appRoot + "/restaurant/dreject/manage/scanView.action?formId=" + formId;
		flag = true;
	}
	
	if (testStr.indexOf("TJP") != -1&& !flag) {
		_url = appRoot + "/hq/priceadjust/purchase/scanView.action?formId=" + formId;
		flag = true;
	}
	
	if (testStr.indexOf("TJB") != -1&& !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}
	
	if (testStr.indexOf("TJJ") != -1&& !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}
	
	if (testStr.indexOf("TJR") != -1&& !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}
	
	if (testStr.indexOf("TJW") != -1&& !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}
	
	if (testStr.indexOf("TJS") != -1&& !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("YK") != -1 || testStr.indexOf("ZP") != -1 || testStr.indexOf("TP") != -1
			|| testStr.indexOf("CFCG") != -1 && !flag) {
		_url = appRoot + "/sp/poscan/scanView.action";
		if (type == 'LITTLE') {
			_url = appRoot + "/sp/poscan/lscanView.action";
		}
		_url += "?formId=" + formId + "&parentTabId=" + tabId;
		
		flag = true;
	}

	if (testStr.indexOf("LK") != -1  && !flag) {
		_url = appRoot + "/restaurant/putinstorage/outside/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TH") != -1  && !flag) {
		_url = appRoot + "/restaurant/preject/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("DZ") != -1  && !flag) {
		_url = appRoot + "/sp/statement/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("CH") != -1  && !flag) {
		_url = appRoot + "/lc/request/delivery/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("RQOO") != -1  && !flag) {
		_url = appRoot + "/outerorder/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("YH") != -1  && !flag) {
		_url = appRoot + "/restaurant/goodsbill/query/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("PS") != -1  && !flag) {
		_url = appRoot + "/restaurant/inoutquery/shipping/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("BS") != -1  && !flag) {
		if (type == "RAWLOSS") {
			_url = appRoot + "/restaurant/reportdamage/queryloss/showView.action?formId=" + formId;
		} else {
			_url = appRoot + "/restaurant/reportdamage/querydishloss/showView.action?formId=" + formId;
		}
		
		flag = true;
	}

	if (testStr.indexOf("SCJH") != -1  && !flag) {
		_url = appRoot + "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("GD") != -1  && !flag) {
		_url = appRoot + "/centralfactory/productionDemand/workOrder/audit/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("DB") != -1  && !flag) {
		_url = appRoot + "/restaurant/allocateitem/query/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("CS") != -1  && !flag) {
		_url = appRoot + "/restaurant/inoutquery/checkstorage/scan/scanView.action?formId=" + formId;
		flag = true;
	}
	_url = getUrl(_url);
	
	window.open(_url);
}


function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑中！");
		return false;
	}
	if (status == '已删除') {
		alert("单据已删除！");
		return false;
	} else if (status == '已作废') {
		alert("单据已作废！");
		return false;
	}
	if (status == '未审核') {// 更保险的做法，上面的写法只能是排除法（当然更加友好些），这里是限定法
		return true;
	}
	alert("单据已审核！");
	return false;
}

function doAudit(_formId) {
//	if (!checkStatus(_formId)) {
//		return;
//	}
	if (!confirm("确定审核通过吗？")) {
		return;
	}
	var _url = appRoot + "/restaurant/goodsbill/confirm/auditById.action?formId=" + _formId;
	_url = getUrl(_url);
	
	$.ajax({
		type : "POST",
		url : _url,
		error : function(e) {
			alert(e);
		},
		success : function(data) {
			if (data.msg == 'ok') {
				alert('审核成功！');
				doQuery();
			}
		}
	});
}