var sheetName = '角色功能表';
var data = {
	sheetName : sheetName,
	title : {
		text : sheetName
	}
};

function fillData(columns, content) {
	require([ "dojo/_base/array", "dojo/_base/lang" ], function(array, lang) {
		dataClone = lang.clone(data)
		dataClone.content = content;
		var newCols = [{
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 80
		}, {
			display : '角色编码',
			name : 'roleId',
			align : 'center',
			width : 80
		}, {
			display : '角色名称',
			name : 'roleName',
			width : 120,
			align : 'center'
		},{
			display : '根角色',
			name : 'rootName',
			align : 'center',
			width : 90
		},{
			display : '功能名称',
			name : 'powerName',
			align : 'left',
			width : 180
		},{
			display : '根功能',
			name : 'powerRoot',
			align : 'center',
			width : 150
		}];
		dataClone.columns = newCols;
		exportXls();
	});
}

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/common/function/export.action";
	_url = getUrl(_url);
	
	var _params = {};
	_params.jsonData = JSON.stringify(dataClone);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
