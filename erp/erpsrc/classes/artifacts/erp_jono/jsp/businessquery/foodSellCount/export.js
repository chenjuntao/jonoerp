var type;

var endExportCols = [ {
	display : "数量",
	name : "qty",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "标准金额",
	name : "amt",
	align : 'amt',
	width : 100,
	sum : {
		count : true
	}

}, {
	display : "折后金额",
	name : "afterAmt",
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

var xlExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "小类编码",
	name : "categoryId",
	align : 'center',
	width : 100
}, {
	display : "小类名称",
	name : "categoryN",
	align : 'center',
	width : 100
} ];

var dlExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "大类编码",
	name : "categoryId",
	align : 'center',
	width : 100
}, {
	display : "大类名称",
	name : "categoryN",
	align : 'center',
	width : 100
} ];

function exportXls(_type) {
	type = dojo.byId('type').value;

	var data = new Object();

	var sheetName = '出品销售统计表-大类';
	data.columns = dlExportCols.concat(endExportCols);
	var _url = appRoot + "/bq/foodSellCount/export/bigCategoryExport.action";
	
	if (type == 'xl') {
		sheetName = '出品销售统计表-小类';
		data.columns = xlExportCols.concat(endExportCols);
		_url = appRoot + "/bq/foodSellCount/export/littleCategoryExport.action";
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