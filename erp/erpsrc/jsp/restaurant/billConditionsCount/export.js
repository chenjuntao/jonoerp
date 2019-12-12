var type;

var zkExportCols = [ {
	display : "营业日期",
	name : "businessDate",
	align : 'center',
	width : 100,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "台卡号",
	name : "",
	align : 'center',
	width : 100,
}, {
	display : "台位",
	name : "table",
	align : 'center',
	width : 100,
}, {
	display : "出品单号",
	name : "billC",
	align : 'center',
	width : 100,
}, {
	display : "班次",
	name : "shift",
	align : 'center',
	width : 100,
}, {
	display : "市别",
	name : "period",
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
}, {
	display : "折扣原因",
	name : "disWhy",
	align : 'center',
	width : 100,
}, {
	display : "折扣人",
	name : "disMan",
	align : 'center',
	width : 100,
} ];

var zsExportCols = [ {
	display : "营业日期",
	name : "businessDate",
	align : 'center',
	width : 100,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "台卡号",
	name : "",
	align : 'center',
	width : 100
}, {
	display : "台位",
	name : "table",
	align : 'center',
	width : 100
}, {
	display : "出品单号",
	name : "billC",
	align : 'center',
	width : 100
}, {
	display : "班次",
	name : "shift",
	align : 'center',
	width : 100
}, {
	display : "市别",
	name : "period",
	align : 'center',
	width : 100
}, {
	display : "出品名称",
	name : "foodName",
	align : 'center',
	width : 250
}, {
	display : "单位",
	name : "unit",
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
	display : "价格",
	name : "price",
	align : 'center',
	width : 100
}, {
	display : "赠送金额",
	name : "presentPrice",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "加价",
	name : "extracPrice",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "赠送授权人",
	name : "presentMan",
	align : 'center',
	width : 100
}, {
	display : "赠送原因",
	name : "presentWhy",
	align : 'center',
	width : 100
}, {
	display : "大类编码",
	name : "bigC",
	align : 'center',
	width : 100
}, {
	display : "大类名称",
	name : "bigN",
	align : 'center',
	width : 100
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

var mdExportCols = [ {
	display : "营业日期",
	name : "businessDate",
	align : 'center',
	width : 100,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "台卡号",
	name : "",
	align : 'center',
	width : 100
}, {
	display : "出品单号",
	name : "billC",
	align : 'center',
	width : 100
}, {
	display : "台位",
	name : "table",
	align : 'center',
	width : 100
}, {
	display : "班次",
	name : "shift",
	align : 'center',
	width : 100
}, {
	display : "市别",
	name : "period",
	align : 'center',
	width : 100
}, {
	display : "收银员",
	name : "payMan",
	align : 'center',
	width : 100
}, {
	display : "授权人",
	name : "grantMan",
	align : 'center',
	width : 100
}, {
	display : "标准金额",
	name : "payAmt",
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
}, {
	display : "付款人",
	name : "",
	align : 'center',
	width : 100
}, {
	display : "门店编码",
	name : "shopC",
	align : 'center',
	width : 100
}, {
	display : "门店名称",
	name : "shopN",
	align : 'center',
	width : 120
} ];

var tpExportCols = [ {
	display : "营业日期",
	name : "businessDate",
	align : 'center',
	width : 100,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "台卡号",
	name : "",
	align : 'center',
	width : 100
}, {
	display : "出品单号",
	name : "billC",
	align : 'center',
	width : 100
}, {
	display : "台位",
	name : "table",
	align : 'center',
	width : 100
}, {
	display : "班次",
	name : "shift",
	align : 'center',
	width : 100
}, {
	display : "市别",
	name : "period",
	align : 'center',
	width : 100
}, {
	display : "点单号",
	name : "foodBill",
	align : 'center',
	width : 100
}, {
	display : "出品码",
	name : "foodC",
	align : 'center',
	width : 100
}, {
	display : "出品名称",
	name : "foodN",
	align : 'center',
	width : 200
}, {
	display : "单位",
	name : "unit",
	align : 'center',
	width : 100
}, {
	display : "价格",
	name : "price",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
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
	display : "退品原因",
	name : "returnWhy",
	align : 'center',
	width : 150
}, {
	display : "退品时间",
	name : "returnTime",
	align : 'center',
	width : 180
}, {
	display : "退品授权人",
	name : "backMan",
	align : 'center',
	width : 100
}, {
	display : "套餐标志",
	name : "suitFlag",
	align : 'center',
	width : 100
},{
	display : "小类编码",
	name : "smallC",
	align : 'center',
	width : 100
}, {
	display : "小类名称",
	name : "smallN",
	align : 'center',
	width : 100
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

	var sheetName = '单据情况汇总表-免单';
	data.columns = mdExportCols;
	var _url = appRoot + "/bq/freeBillCount/export/export.action";

	if (type == 'zk') {
		sheetName = '单据情况汇总表-折扣';
		data.columns = zkExportCols;
		_url = appRoot + "/bq/discount/export/export.action";
	} else if (type == 'zs') {
		sheetName = '单据情况汇总表-赠送';
		data.columns = zsExportCols;
		_url = appRoot + "/bq/present/export/export.action";
	} else if (type == 'tp') {
		sheetName = '单据情况汇总表-退品';
		data.columns = tpExportCols;
		_url = appRoot + "/bq/return/export/export.action";
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
		period : dojo.byId('period').value,
		shift : dojo.byId('shift').value,
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