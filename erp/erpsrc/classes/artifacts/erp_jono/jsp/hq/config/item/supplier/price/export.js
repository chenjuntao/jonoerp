var dataClone = {};

function fillData(columns, content) {
	var sheetName = dojo.byId('supName').value + '供应商采购价';

	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : "序号",
			name : 'rownumber',
			align : 'center',
			width : 50
		}, {
			display : "物料编码",
			name : 'itemId',
			align : 'left',
			width : 100
		}, {
			display : "物料名称",
			name : 'itemName',
			align : 'center',
			width : 150
		}, {
			display : "助记码",
			name : 'queryCode',
			align : 'left',
			width : 160
		}, {
			display : "物料类别",
			name : 'categoryId',
			align : 'left',
			width : 100
		}, {
			display : "物料单位",
			name : 'itemDimension',
			align : 'right',
			width : 80
		}, {
			display : "规格",
			name : 'itemSpecification',
			align : 'right',
			width : 80
		}, {
			display : "供应商采购价",
			name : 'itemPrice',
			align : 'right',
			width : 80
		}, {
			display : "物料进货价",
			name : 'costPrice',
			align : 'right',
			width : 80
		} ]
	};
	require([ "dojo/_base/array", "dojo/_base/lang" ], function(array, lang) {

		// 拷贝副本
		dataClone = lang.clone(data)
		dataClone.content = content;

		// 添加动态列
		array.forEach(columns, function(item) {
			if (!isNaN(item.field) && !dataClone.columns.hasOwnProperty(item.field)) {
				dataClone.columns.push({
					name : item.field,
					align : 'right',
					width : 100
				});
			}
		});

		setColumns(columns, array);
	});
}

function setColumns(columns, array) {
	array.forEach(columns, function(outItem) {
		array.forEach(dataClone.columns, function(inItem) {
			if (outItem.field == inItem.name) {
				inItem.display = outItem.label;
			}
		});
	});

	exportXls();
}

function exportXls() {
	var _type = 'excel';

	var _url = appRoot + "/common/function/export.action";

	var _params = {};
	_params.jsonData = JSON.stringify(dataClone);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
