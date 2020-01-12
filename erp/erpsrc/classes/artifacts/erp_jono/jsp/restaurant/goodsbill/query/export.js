//var sheetName = '要货单-';

var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		name : 'itemId',
		align : 'center',
		width : 100,
		display : "原料编码"
	}, {
		name : 'itemName',
		align : 'left',
		width : 150,
		display : "原料名称"
	}, {
		name : 'itemCategory',
		align : 'left',
		width : 100,
		display : "类别"
	}, {
		name : 'itemDimension',
		align : 'left',
		width : 100,
		display : "单位"
	}, {
		name : 'itemSpecification',
		align : 'left',
		width : 120,
		display : "规格"
	}, {
		name : 'orderCount',
		align : 'right',
		width : 100,
		display : "订货量"
	}, {
		name : 'amtTTCNY1',
		align : 'right',
		width : 100,
		display : "万用1"
	}, {
		name : 'amtTTCNY2',
		align : 'right',
		width : 100,
		display : "万用2"
	}, {
		name : 'amtTTCNY3',
		align : 'right',
		width : 100,
		display : "万用3"
	}, {
		name : 'requireCount1',
		align : 'right',
		width : 100,
		display : "需求1"
	}, {
		name : 'requireCount2',
		align : 'right',
		width : 100,
		display : "需求2"
	}, {
		name : 'requireCount3',
		align : 'right',
		width : 100,
		display : "需求3"
	}, {
		name : 'inventory',
		align : 'right',
		width : 100,
		display : "库存量"
	}, {
		name : 'suggestCount1',
		align : 'right',
		width : 100,
		display : "建议1"
	}, {
		name : 'suggestCount2',
		align : 'right',
		width : 100,
		display : "建议2"
	}, {
		name : 'suggestCount3',
		align : 'right',
		width : 100,
		display : "建议3"
	}, {
		name : 'itemUnitPrice',
		align : 'right',
		width : 100,
		display : "标准单价"
	}, {
		name : 'payAmt',
		align : 'right',
		width : 100,
		display : "标准金额"
	} ]
};

function fillData(content) {
	var sheetName = '要货单-' + formId;
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
