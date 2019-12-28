require([ "dojo/dom-form", "dojo/domReady!" ], function(domForm) {

	// 函数延迟声明
	window.getQuery = function() {

		// 单据编号转化为大写
		var query = domForm.toObject("dataForm");
		query.formIdText = query.formIdText.toUpperCase();
		return query;
	}

	initGird();
});

function doQuery() {
	grid.set('query', getQuery());
}

function doScan(row) {
	if (row.status == 'N') {
		// 更改单据查看状态
		updateStatus(row.form_id);
	}

	var _url = appRoot + "/sp/poscan/scanView.action?formId=" + row.form_id + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addTab("采购单查看-" + row.form_id, _url);
};

function getDeliveryType(currentValue) {
	if (currentValue == 'UNIFIED')
		return "统配";
	if (currentValue == 'DIRECT')
		return "直配";
	if (currentValue == 'CROSS')
		return "越库";

	return "";
}
