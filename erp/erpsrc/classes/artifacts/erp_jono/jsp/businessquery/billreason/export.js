var type;

var zkExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "折扣原因",
	name : "disWhy",
	align : 'center',
	width : 100,
}, {
	display : "消费金额",
	name : "foodAmt",
	sum : {
		count : true
	},
	align : 'center',
	width : 100,
}, {
	display : "折扣金额",
	name : "disAmt",
	sum : {
		count : true
	},
	align : 'center',
	width : 100,
}, {
	display : "付款金额",
	name : "payAmt",
	sum : {
		count : true
	},
	align : 'center',
	width : 100,
}, {
	display : "门店编码",
	name : "shopC",
	align : 'center',
	width : 100,
}, {
	display : "门店名称",
	name : "shopN",
	align : 'center',
	width : 100,
}, ];

var zsExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "赠送原因",
	name : "presentWhy",
	align : 'center',
	width : 100
}, {
	display : "赠送数量",
	name : "presentAmount",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}

}, {
	display : "赠送金额",
	name : "presentPrice",
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

var tpExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "退品原因",
	name : "returnWhy",
	align : 'center',
	width : 150
}, {
	display : "退品数量",
	name : "returnAmount",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "退品金额",
	name : "returnAmt",
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

	var sheetName = '单据原因汇总报表-折扣';
	data.columns = zkExportCols;
	var _url = appRoot + "/bq/discountreason/export/export.action";

	if (type == 'zs') {
		sheetName = '单据原因汇总报表-赠送';
		data.columns = zsExportCols;
		_url = appRoot + "/bq/presentreason/export/export.action";
	} else if (type == 'tp') {
		sheetName = '单据原因汇总报表-退品';
		data.columns = tpExportCols;
		_url = appRoot + "/bq/returnreason/export/export.action";
	}
	
	_url = getUrl(_url);
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
	post_redirect(_url, _params);
}

function exportCsv() {
	exportXls("csv");
}