function byDay(_type){
	var sheetName = '付款方式-按天';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [   {
			display : '日期',
			name : 'businessDate',
			align : 'center',
			width : 100
		},{
			display : '门店编号',
			name : 'shopC',
			align : 'center',
			width : 90
		} ,{
			display : '门店名称',
			name : 'shopName',
			align : 'center',
			width : 150
		} ,{
			display : '付款方式编号',
			name : 'payCode',
			align : 'center',
			width : 150
		} ,{
			display : '付款方式名称',
			name : 'payName',
			align : 'center',
			width : 150
		} ,{
			display : '付款方式金额',
			name : 'payAmt',
			align : 'center',
			width : 150
		} ]
	};
	var _url = appRoot + "/bq/payment/export/byDay.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		jsonData : JSON.stringify(data)
	};
	if (_type != undefined) {
		_params.type = _type;
	}
	post_redirect(_url, _params);
}


function exportXls(_type) {
	var type = dojo.byId("type").value;
	if (type == "2") {
		byDay(_type);
		return;
	}
	var sheetName = '付款方式-累计';
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
			align : 'center',
			sum : {
				count : false,
				text : '合计'
			}
		} ]
	};
	var _url = appRoot + "/bq/payment/export/doQuery.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		jsonData : JSON.stringify(data)
	};
	if (_type != undefined) {
		_params.type = _type;
	}
	post_redirect(_url, _params);
}

function exportCsv() {
	exportXls("csv");
}