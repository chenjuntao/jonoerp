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
		display : "主库存数量(物)",
		name : "storageCount",
		align : 'right',
		width : 100
	}, {
		display : "计划生产量",
		name : "produceCount",
		align : 'right',
		width : 100
	}, {
		display : "主原材料",
		name : "mainName",
		align : 'left',
		width : 100
	}, {
		display : "工单编号",
		name : "workOrderId",
		align : 'left',
		width : 150
	}, {
		display : "生产车间",
		name : "workshop",
		align : 'center',
		width : 100
	}, {
		display : "生产日期",
		name : "produceTime",
		align : 'center',
		width : 100
	}, {
		display : "生产周期",
		name : "productionCycle",
		align : 'center',
		width : 100
	}, {
		display : "完工日期",
		name : "completeTime",
		align : 'center',
		width : 100
	}, {
		display : "备注",
		name : "note",
		align : 'center',
		width : 100
	} ]
};

function fillData(content) {
	var sheetName = '生产计划单-' + formId;
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
