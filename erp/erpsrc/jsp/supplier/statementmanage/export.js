//var sheetName = '要货单-';

var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		name : 'STORAGE',
		align : 'center',
		width : 100,
		display : "仓库"
	}, {
		name : 'SUPPLIER',
		align : 'center',
		width : 150,
		display : "供应商"
	}, {
		name : 'AUDIT_TIME',
		align : 'center',
		width : 100,
		display : "操作日期"
	}, {
		name : 'FORM_ID',
		align : 'center',
		width : 150,
		display : "单据编号"
	}, {
		name : 'ITEM_ID',
		align : 'center',
		width : 100,
		display : "商品编码"
	}, {
		name : 'ITEM_NAME',
		align : 'center',
		width : 120,
		display : "商品名称"
	}, {
		name : 'ITEM_DIMENSION',
		align : 'center',
		width : 80,
		display : "采购单位"
	}, {
		name : 'ITEM_COUNT',
		align : 'right',
		width : 100,
		display : "采购数量"
	}, {
		name : 'PRICE',
		align : 'right',
		width : 100,
		display : "采购单价"
	}, {
		name : 'AMT',
		align : 'right',
		width : 100,
		display : "采购金额"
	}]
};

function fillData(content) {
	var sheetName = '对账单';
	data.content = content;
	data.sheetName = sheetName, data.title = {
		text : sheetName
	};

	exportXls();
}

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/common/function/export.action";
	_url = getUrl(_url);

	var _params = {};
	_params.jsonData = JSON.stringify(data);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
