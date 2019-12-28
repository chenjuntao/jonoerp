var data1 = {
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
		align : 'center',
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
		display : "采购数量",
		name : "orderCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "已入库数量",
		name : "receivedCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "实收数量",
		name : "receiveCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "实收差异",
		name : "differentCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "标准单价",
		name : "itemUnitPrice",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "标准金额",
		name : "payAmt",
		className : 'grid-number',
		align : 'right',
		width : 100
	} ]
};

var data2 = {
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
		align : 'center',
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
		display : "采购数量",
		name : "orderCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "已入库数量",
		name : "receivedCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "实收数量",
		name : "receiveCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "实收差异",
		name : "differentCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "进货单价",
		name : "receivePrice",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "进货金额",
		name : "receiveAmt",
		className : 'grid-number',
		align : 'right',
		width : 100
	} ]
};

var data3 = {
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
		align : 'center',
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
		display : "采购数量",
		name : "orderCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "已入库数量",
		name : "receivedCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "实收数量",
		name : "receiveCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "实收差异",
		name : "differentCount",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "标准单价",
		name : "itemUnitPrice",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "标准金额",
		name : "payAmt",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "进货单价",
		name : "receivePrice",
		className : 'grid-number',
		align : 'right',
		width : 100
	}, {
		display : "进货金额",
		name : "receiveAmt",
		className : 'grid-number',
		align : 'right',
		width : 100
	} ]
};
var data = [];
function getData() {
	var branchFlag = dojo.byId('branchFlag').value;
	var branchType = dojo.byId('branchType').value;
	if (!isEmpty(branchFlag)) {
		data = data2;
	} else if (branchType != 'RESTAURANT') {
		data = data3;
	} else {
		data = data1;
	}
}

function fillData(content) {
	getData();
	var sheetName = '入库单-' + formId;
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
