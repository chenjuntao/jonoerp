function exportXls(_type) {
	var sheetName = '付款方式';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '门店编号',
			name : 'shopC',
			align : 'center',
			width : 90
		}, {
			display : '门店名称',
			name : 'shopName',
			width : 120,
			align : 'center'
		} ]
	};
	var _url = appRoot + "/bq/payment/export/doOneShopQuery.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		shopC : dojo.byId('shopC').value,
		jsonData : JSON.stringify(data)
	};
	if(_type!=undefined){
		_params.type = _type;
	}
	post_redirect(_url, _params);
}

function exportCsv() {
	exportXls("csv");
}