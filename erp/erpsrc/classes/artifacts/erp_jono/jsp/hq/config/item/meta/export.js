var sheetName = '物料基本信息';
var data = {
	sheetName : sheetName,
	title : {
		text : sheetName
	}
};

function fillData(columns, content) {
	require([ "dojo/_base/array", "dojo/_base/lang" ], function(array, lang) {

		// 拷贝副本
		dataClone = lang.clone(data)
		dataClone.content = content;
		var newCols = [ {
			name : 'rownumber',
			align : 'center',
			width : 50,
			display : "序号"
		}, {
			display : "编码",
			name : "itemId",
			align : 'center',
			width : 90,
		}, {
			display : "名称",
			name : "itemName",
			align : 'left',
			width : 150,
		} ];
		// 添加动态列
		array.forEach(columns, function(item) {
			if (item.field != "operate" && item.field != "enabled" && item.field != "therapy") {
				newCols.push({
					display : item.label,
					name : item.field,
					align : 'center',
					width : '100'
				});
			}
		});
		dataClone.columns = newCols;
		exportXls();
		// setColumns(columns, array);
	});
}

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/common/function/export.action";

	var _params = {};
	_params.jsonData = JSON.stringify(dataClone);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
