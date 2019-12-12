var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		display : "编码",
		name : "itemId",
		align : 'center',
		width : 100
	}, {
		display : "名称",
		name : "itemName",
		align : 'left',
		width : 150
	}, {
		display : "库存单位",
		name : "stockDimension",
		align : 'center',
		width : 150
	}, {
		display : "配方单位",
		name : "itemDimension",
		align : 'center',
		width : 150
	}, {
		display : "转换因子",
		name : "unitConvertFactor",
		align : 'right',
		width : 150
	}, {
		display : "净料耗量(配方单位)",
		name : "itemCount",
		align : 'center',
		width : 200
	}, {
		display : "净耗料率(%)",
		name : "itemUsageRate",
		align : 'center',
		width : 100
	}, {
		display : "毛耗料量(配方单位)",
		name : "itemGrossCount",
		align : 'right',
		width : 200
	}, {
		display : "进货价",
		name : "purchasePrice",
		align : 'right',
		width : 100
	}, {
		display : "进货金额",
		name : "purchaseAmt",
		align : 'right',
		width : 100
	}, {
		display : "标准价",
		name : "benchmarkPrice",
		align : 'right',
		width : 100
	}, {
		display : "标准金额",
		name : "benchmarkAmt",
		align : 'right',
		width : 100
	} ]
};

function fillData(content) {
	var sheetName = itemId + '-' + '成本卡出品信息表';
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
