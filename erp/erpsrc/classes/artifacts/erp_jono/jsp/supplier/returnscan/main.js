require([ "dojo/dom-form", "dojo/domReady!" ], function(domForm) {

	// 函数延迟声明
	window.getQuery = function() {

		// 单据编号转化为大写
		var query = domForm.toObject("dataForm");
		query.formIdText1 = query.formIdText1.toUpperCase();
		query.formIdText2 = query.formIdText2.toUpperCase();
		return query;
	}

	initGird();
});

function doQuery() {
	grid.set('query', getQuery());
}

doConfirm = function(row) {
	// if (!checkStatus(row.form_id)) {
	// return;
	// }

	var _url = appRoot + "/sp/return/scan/confirm.action?formId=" + row.form_id + "&parentTabId=" + tabId
			+ "&form_ref_id=" + row.form_ref_id;
	_url = getUrl(_url);

	addTab('对账确认-' + row.form_id, _url);
};

function doScan(row) {
	var formId = row.form_id;
	_url = appRoot + "/restaurant/preject/manage/scanView.action?formId=" + formId + '&branchFlag=SP';
	_url = getUrl(_url);

	var _title = "查看采购退货单-";
	_title += formId;
	addTab(_title, _url);
	return;
}

function doRelInScan(row) {
	var formId = row.form_ref_id;
	var _url = appRoot + "/restaurant/putinstorage/outside/scanView.action?formId=" + formId + '&branchFlag=SP';
	_url = getUrl(_url);

	var _title = "查看入库单-";
	_title += formId;
	addTab(_title, _url);
	return;
}