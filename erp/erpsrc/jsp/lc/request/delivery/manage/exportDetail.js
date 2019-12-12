var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		display : '单据编号',
		name : 'formId',
		align : 'center',
		width : 150
	}, {
		display : '商品名称',
		name : 'itemName',
		align : 'center',
		width : 100
	}, {
		display : '类别',
		name : 'itemCategory',
		align : 'center',
		width : 100
	}, {
		display : '单位',
		name : 'itemDimension',
		align : 'center',
		width : 100
	}, {
		display : '规格',
		name : 'itemSpecification',
		align : 'center',
		width : 100
	}, {
		display : '包装因子',
		name : 'packagingFactor',
		align : 'center',
		width : 160
	}, {
		display : '包装单位',
		name : 'packagingUnit',
		align : 'center',
		width : 100
	}, {
		display : '订货数',
		name : 'requestCount',
		align : 'center',
		width : 150
	}, {
		display : '配送数',
		name : 'shippingCount',
		align : 'center',
		width : 250
	}, {
		display : '实收数',
		name : 'receiveCount',
		align : 'center',
		width : 250
	}, {
		display : '差异数',
		name : 'differentCount',
		align : 'center',
		width : 250
	}, {
		display : '单价',
		name : 'itemUnitPrice',
		align : 'center',
		width : 100
	}, {
		display : '金额',
		name : 'payAmt',
		align : 'center',
		width : 100
	} ]
};

function fillData(content) {
	var sheetName = '外部出货单-' + formId;
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
