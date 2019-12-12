//var sheetName = '要货单-';

var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		display :  "原料编码",
		name :  "itemId",
		align : 'center',
		width : 50
	}, {
		display :  "原料名称",
		name :  "itemName",
		align : 'center',
		width : 50
	}, {
		display :  "单位",
		name :  "itemDimension",
		align : 'center',
		width : 50
	}, {
		display :  "规格",
		name :  "itemSpecification",
		align : 'center',
		width : 50
	}, {
		display :  "类别",
		name :  "itemCategory",
		align : 'center',
		width : 50
	}, {
		display :  "原料有效期",
		name :  "expiredTime",
		align : 'center',
		width : 50
	}, {
		display :  '盘点数量',
		name :  'checkCount',
		align : 'center',
		width : 50
	}, {
		display :  '系统库存',
		name :  'theoryCount',
		align : 'center',
		width : 50
	}, {
		display :  '盘盈',
		name :  'diffCount',
		align : 'center',
		width : 50
	}]
};

function fillData(content) {
	var sheetName = '盘点单-' + formId;
	
//	data.diffCount=data.checkCount - data.theoryCount;
	
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
