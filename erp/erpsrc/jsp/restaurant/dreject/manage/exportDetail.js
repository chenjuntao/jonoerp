var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		display : "原料编码",
		name : "itemId",
		align : 'center',
		width : 50
	}, {
		display : "原料名称",
		name : "itemName",
		align : 'center',
		width : 50
	}, {
		display : "类别",
		name : "itemCategory",
		align : 'center',
		width : 50
	}, {
		display : "单位",
		name : "itemDimension",
		align : 'center',
		width : 50
	}, {
		display : "规格",
		name : "itemSpecification",
		align : 'center',
		width : 50
	}, {
		display : "配送数",
		name : "shippingCount",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "实发数",
		name : "deliveryCount",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "实收数",
		name : "receiveCount",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "退货数",
		name : "returnCount",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "标准单价",
		name : "itemUnitPrice",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "标准金额",
		name : "returnPayAmt",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "退货原因",
		name : "returnReason",
		align : 'center',
		width : 50
	}]
};

function fillData(content) {
	var sheetName = '配送退货单-' + formId;
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
