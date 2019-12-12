var sheetName = '供应商商品-价格对应关系表';
var data = {
	sheetName : sheetName,
	title : {
		text : sheetName
	}
};

function fillData(columns, content) {
	require([ "dojo/_base/array", "dojo/_base/lang" ], function(array, lang) {
		dataClone = lang.clone(data)
		dataClone.content = content;
		var newCols = [{
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 80
		}, {
			display : '物料编码',
			name : 'item_id',
			align : 'center',
			width : 80
		}, {
			display : '物料编码',
			name : 'item_name',
			width : 200,
			align : 'left'
		},{
			display : '供应商编码',
			name : 'business_id',
			align : 'center',
			width : 80
		},{
			display : '供应商名称',
			name : 'business_name',
			align : 'left',
			width : 280
		},{
			display : '价格',
			name : 'item_price',
			align : 'right',
			width : 80
		}];
		dataClone.columns = newCols;
		exportXls();
	});
}

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/common/function/export.action";
	_url = getUrl(_url);
	
	var _params = {};
	_params.jsonData = JSON.stringify(dataClone);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
