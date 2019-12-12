//var sheetName = '要货单-';

var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	},  {
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
		name : "	",
		align : 'center',
		width : 50
	}, {
		display : "规格",
		name : "itemSpecification",
		align : 'center',
		width : 50
	}, {
		display : "申请调拨数量",
		name : "applyCount",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "调拨数量",
		name : "actualCount",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "调拨差异",
		name : "differentCount",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "标准单价",
		name : "unitPrice",
		className : 'grid-number',
		align : 'center',
		width : 50
	}, {
		display : "标准金额",
		name : "payAmt",
		className : 'grid-number',
		align : 'center',
		width : 50
	}]
};

function fillData(content) {
	var sheetName = '调拨单-' + formId;
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
