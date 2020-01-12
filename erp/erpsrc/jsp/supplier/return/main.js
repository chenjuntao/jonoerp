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
//	if (!checkStatus(row.form_id)) {
//		return;
//	}

	var _url = appRoot + "/sp/return/confirm.action?formId=" + row.form_id
			+ "&parentTabId=" + tabId + "&form_ref_id=" + row.form_ref_id;
	_url = getUrl(_url);
	
	addTab(row.form_id + '退货确认', _url);
};