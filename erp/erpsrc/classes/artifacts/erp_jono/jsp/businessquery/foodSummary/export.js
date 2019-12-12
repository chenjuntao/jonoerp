
var exportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "出品编码",
	name : "foodC",
	align : 'center',
	width : 100
}, {
	display : "出品名称",
	name : "foodN",
	align : 'left',
	width : 200
}, {
	display : "单位",
	name : "unit",
	align : 'center',
	width : 100
},{
	display : "数量",
	name : "qty",
	align : 'right',
	width : 100,
	sum : {
		count : true
	}
},{
	display : "赠送数量",
	name : "pesentQty",
	align : 'right',
	width : 100,
	sum : {
		count : true
	}
},{
	display : "退品数量",
	name : "returnQty",
	align : 'right',
	width : 100,
	sum : {
		count : true
	}
} ];

function exportXls(_type) {
	var data = new Object();

	var sheetName = '出品销售统计汇总表';
	data.columns = exportCols;
	var _url = appRoot + "/bq/foodSummary/export/export.action";
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