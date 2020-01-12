var exportCols = [  {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
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
	width : 120
}, {
	display : "付款方式",
	name : "payN",
	align : 'center',
	width : 100
}, {
	display : "单位",
	name : "unit",
	align : 'center',
	width : 100
}, {
	display : "本币金额",
	name : "payAmt",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "营业日期",
	name : "businessDate",
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
	display : "门店编号",
	name : "shopC",
	align : 'center',
	width : 100
}, {
	display : "门店名称",
	name : "shopN",
	align : 'center',
	width : 100
}, {
	display : "会员卡号",
	name : "vipC",
	align : 'center',
	width : 120
}, {
	display : "会员名称",
	name : "vipN",
	align : 'center',
	width : 100
}, {
	display : "消费人",
	name : "",
	align : 'center',
	width : 100
} ];

function exportXls(_type) {
	var data = new Object();

	var sheetName = '付款明细表';
	data.columns = exportCols;
	var _url = appRoot + "/bq/billPayDetail/export/export.action";
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