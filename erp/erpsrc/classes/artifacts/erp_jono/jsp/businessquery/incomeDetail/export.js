var type;

var dayExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "日期",
	name : "businessDate",
	align : 'center',
	width : 100,
} ];

var monthExportCols = [ {
	display : "序号",
	name : "rowNumber",
	align : 'center',
	width : 50,
	sum : {
		count : false,
		text : '合计'
	}
}, {
	display : "月份",
	name : "businessMonth",
	align : 'center',
	width : 100,
} ];

var endExportCols = [ {
	display : "大类",
	name : "categoryN",
	align : 'center',
	width : 100
}, {
	display : "午餐",
	name : "period1",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "午茶",
	name : "period2",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "晚餐",
	name : "period3",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "晚茶",
	name : "period4",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
}, {
	display : "小计",
	name : "counts",
	align : 'center',
	width : 100,
	sum : {
		count : true
	}
} ];

function exportXls(_type) {
	type = dojo.byId('type').value;

	var data = new Object();
	var sheetName = '营业收入明细报表-按日';
	var _url = appRoot + "/bq/incomeDetail/export/dayExport.action";

	if (type == 'day') {
		data.columns = dayExportCols.concat(endExportCols);
	} else if (type == 'month') {
		sheetName = '营业收入明细报表-按月';
		data.columns = monthExportCols.concat(endExportCols);
		_url = appRoot + "/bq/incomeDetail/export/monthExport.action";
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