var data = {
	columns : [ {
		name : 'itemIdRf',
		align : 'left',
		width : 100,
		display : "产品编码"
	}, {
		name : 'itemNameRf',
		align : 'left',
		width : 160,
		display : "产品名称"
	}, {
		name : 'rownumber',
		align : 'center',
		width : 60,
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
		align : 'left',
		width : 100
	}, {
		display : "配方单位",
		name : "itemDimension",
		align : 'left',
		width : 100
	// 数据库中只存了这个字段名，其实有原料id，其它字段都动态获取是最准确的
	}, {
		display : "转换因子",
		name : "unitConvertFactor",
		align : 'right',
		width : 100
	}, {// 净料耗量=(原料耗量)*净料耗率
		display : "净料耗量(配方单位)",
		name : "itemCount",
		align : 'right',
		width : 150
	}, {
		display : "净耗料率(%)",// 净料耗率=净料耗量*100/(原料耗量)
		name : "itemUsageRate",
		align : 'right',
		width : 100
	}, {
		display : "毛耗料量(配方单位)",
		name : "itemGrossCount",
		align : 'right',
		width : 150
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

var data2 = {};

function fillData() {
	var sheetName = '半成品成本卡信息';
	data.sheetName = sheetName, data.title = {
		text : sheetName
	};

	exportXls();
}

function fillData(content) {
	var sheetName = '半成品成本卡信息';
	data.sheetName = sheetName, data.title = {
		text : sheetName
	};

	exportXls1(content);
}

function exportXls() {
	var selectArr = [];
	var selectName = [];
	for ( var itemId in grid.selection) {
		var rowData = myStore.get(itemId);
		selectArr.push(itemId);
		selectName.push(rowData.itemName);
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/hq/item/meta/doExport.action?exportType=SEMIS";

	var _params = {};
	_params.jsonData = JSON.stringify(data);
	_params.itemIds = selectArr.join(',');
	_params.itemNames = selectName.join(',');
	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}

function fillData2(columns, content) {
	require([ "dojo/_base/array", "dojo/_base/lang" ], function(array, lang) {
		var sheetName = '半成品信息';
		data2.content = content;
		data2.sheetName = sheetName, data2.title = {
			text : sheetName
		};

		// 拷贝副本
		dataClone = lang.clone(data2)
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
			if (item.field != "operate" && item.field != "enabled" && item.field != "therapy"
					&& item.field != "bInHandPro" && item.field != "bDisFood" && item.field != "bDisCount") {
				newCols.push({
					display : item.label,
					name : item.field,
					align : 'center',
					width : '100'
				});
			}
		});
		dataClone.columns = newCols;
		exportXls2();
	});
}

function exportXls1(content) {
	var selectArr = [];
	var selectName = [];
	for (var i = 0; i < content.length; i++) {
		var rowData = content[i];
		selectArr.push(rowData.itemId);
		selectName.push(rowData.itemName);
	}
	if (selectArr.length == 0) {
		alert("记录为空，请重输条件！");
		return;
	}
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/hq/item/meta/doExport.action";
	_url = getUrl(_url);

	var _params = {};
	_params.jsonData = JSON.stringify(data);
	_params.itemIds = selectArr.join(',');
	_params.itemNames = selectName.join(',');
	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}

function exportXls2() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/common/function/export.action";

	var _params = {};
	_params.jsonData = JSON.stringify(dataClone);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
