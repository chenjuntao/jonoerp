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
		width : 100
	}, {
		display : "原料名称",
		name : "itemName",
		align : 'left',
		width : 150
	}, {
		display : "类别",
		name : "itemCategory",
		align : 'left',
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
		width : 100
	}, {
		display : "标准单价",
		name : "itemUnitPrice",
		align : 'right',
		width : 50
	}, {
		display : "主库位",
		name : "shelfName",
		align : 'left',
		width : 150
	} ]
};

function fillData(content) {
	var sheetName = '模板-' + templateId;
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
