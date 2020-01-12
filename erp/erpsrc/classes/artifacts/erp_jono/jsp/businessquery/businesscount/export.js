var type;

var lcExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}];

var sbExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "市别",
	name : "period",
	align : 'center',
	width : 100,
} ];

var bcExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "班次",
	name : "shift",
	align : 'center',
	width : 100,
} ];

var endExportCols = [ {
	display : "单数",
	name : "billNum",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "人数",
	name : "guestNum",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "消费金额",
	name : "foodAmt",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
},{
	display : "折扣金额",
	name : "disAmt",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "舍尾金额",
	name : "roundAmt",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "应收金额",
	name : "oughtAmt",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "付款金额",
	name : "payAmt",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "赠送金额",
	name : "presentAmt",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}

}, {
	display : "门店编码",
	name : "shopC",
	align : 'center',
	width : 100
}, {
	display : "门店名称",
	name : "shopN",
	align : 'center',
	width : 100
} ];

function exportXls(_type) {
	type = dojo.byId('type').value;

	var data = new Object();

	var sheetName = '营业统计表-班次';
	data.columns = 	 bcExportCols.concat(endExportCols);
	var _url = appRoot + "/bq/businesscount/export/shiftExport.action";

	if (type == 'sb') {
		sheetName = '营业统计表-市别';
		data.columns = 	 sbExportCols.concat(endExportCols);
		_url = appRoot + "/bq/businesscount/export/periodExport.action";
	} else if (type == 'lc') {
		sheetName = '营业统计表-楼层';
		data.columns = 	 lcExportCols.concat(endExportCols);
		_url = appRoot + "/bq/businesscount/export/floorExport.action";
	}

	data.sheetName = sheetName;
	data.title = {
		text : sheetName
	};

	var _params = {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		shopC : dojo.byId('shopC').value,
		jsonData : JSON.stringify(data)
	};
	if (_type != undefined) {
		_params.type = _type;
	}
	_url = getUrl(_url);
	post_redirect(_url, _params);
}

function exportCsv() {
	exportXls("csv");
}