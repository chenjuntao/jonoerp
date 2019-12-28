var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		display : "原料编码",
		align : 'center',
		width : 100,
		name : "itemId"
	}, {
		display : "原料名称",
		name : "itemName",
		align : 'center',
		width : 200,
	}, {
		display : "单位",
		name : "itemDimension",
		align : 'center',
		width : 100,
	}, {
		display : "规格",
		name : "itemSpecification",
		align : 'center',
		width : 100,
	}, {
		display : "订货量",
		name : "itemCount",
		align : 'right',
		width : 150,
	}, {
		display : "标准价",
		name : "itemUnitPrice",
		align : 'right',
		width : 100,
	}, {
		display : "标准金额",
		name : "payAmt",
		align : 'right',
		width : 100,
	}, {
		display : "进货价",
		name : "receivePrice",
		align : 'right',
		width : 100,
	}, {
		display : "进货金额",
		name : "receiveAmt",
		align : 'right',
		width : 100,
	}, {
		display : "原料有效期",
		name : "expiredTime",
		align : 'center',
		width : 100,
	} ]
};

function fillData(content) {
	var sheetName = '采购单-' + formId;
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
