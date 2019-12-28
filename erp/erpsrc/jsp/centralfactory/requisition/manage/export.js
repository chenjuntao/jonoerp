var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 100,
		display : "序号"
	}, {
		display : "商品编码",
		name : "itemId",
		align : 'center',
		width : 100
	}, {
		display : "商品名称",
		name : "itemName",
		align : 'left',
		width : 100
	}, {
		display : "单位",
		name : "itemDimension",
		align : 'left',
		width : 100
	}, {
		display : "规格",
		name : "itemSpecification",
		align : 'left',
		width : 100
	}, {
		display : "计划领料数",
		name : "itemCount",
		align : 'right',
		width : 150
	}, {
		display : "已领料数量",
		name : "receivedCount",
		align : 'right',
		width : 150
	}, {
		display : "未领料数",
		name : "differentCount",
		align : 'right',
		width : 100
	}, {
		display : "领料数",
		name : "receiveCount",
		align : 'right',
		width : 100
	}]
};

function fillData(content) {
	var sheetName = '领料单-' + formId;
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
