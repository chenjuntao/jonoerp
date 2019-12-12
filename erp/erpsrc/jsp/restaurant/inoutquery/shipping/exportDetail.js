var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	},{
		display : '单据编号',
		name : 'formId',
		align : 'center',
		width : 150
	},{
		display : '商品编码',
		name : 'itemId',
		align : 'center',
		width : 100
	},{
		display : '商品名称',
		name : 'itemName',
		align : 'left',
		width : 180
	},{
		display : '单位',
		name : 'itemDimension',
		align : 'center',
		width : 100
	},{
		display : '规格',
		name : 'itemSpecification',
		align : 'left',
		width : 150
	},{
		display : '包装因子',
		name : 'packagingFactor',
		align : 'right',
		width : 80
	},{
		display : '包装单位',
		name : 'packagingUnit',
		align : 'center',
		width : 100
	},{
		display : '订货数',
		name : 'shippingCount',
		align : 'right',
		width : 80
	},{
		display : '包装数',
		name : 'packagingCount',
		align : 'right',
		width : 80
	} ,{
		display : '实发数',
		name : 'deliveryCount',
		align : 'right',
		width : 80
	},{
		display : '实收数',
		name : 'receiveCount',
		align : 'right',
		width : 80
	},{
		display : '差异数',
		name : 'differentCount',
		align : 'right',
		width : 80
	},{
		display : '标准单价',
		name : 'itemUnitPrice',
		align : 'right',
		width : 100
	},{
		display : '标准金额',
		name : 'payAmt',
		align : 'right',
		width : 100
	}]
};

function fillData(content) {
	var sheetName = '配送入库单-' + formId;
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
