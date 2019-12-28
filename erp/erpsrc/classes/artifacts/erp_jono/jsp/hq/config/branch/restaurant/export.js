
var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		name : 'regionName',
		align : 'left',
		width : 120,
		display : "配送分组"
	}, {
		name : 'branchId',
		align : 'center',
		width : 50,
		display : "编号"
	}, {
		name : 'branchName',
		align : 'left',
		width : 120,
		display : "名称"
	}, {
		name : 'queryCode',
		align : 'left',
		width : 80,
		display : "助记码"
	}, {
		name : 'enabled',
		align : 'left',
		width : 100,
		display : "启用状态"
	}, {
		name : 'branchAddress',
		align : 'left',
		width : 280,
		display : "位置"
	}, {
		name : '电子邮箱',
		align : 'left',
		width : 120,
		display : "email"
	}, {
		name : 'telephone',
		align : 'left',
		width : 120,
		display : "电话"
	}, {
		name : 'fax',
		align : 'left',
		width : 120,
		display : "传真"
	}, {
		name : 'remark',
		align : 'left',
		width : 150,
		display : "备注"
	} ]
};

function fillData(content) {
	var sheetName = '门店基本信息';
	data.content = content;
	data.sheetName = sheetName, data.title = {
		text : sheetName
	};

	exportXls();
}

function exportXls() {
	var _type = 'excel';

	var _url = appRoot + "/common/function/export.action";
	
	var _params = {};
	_params.jsonData = JSON.stringify(data);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
