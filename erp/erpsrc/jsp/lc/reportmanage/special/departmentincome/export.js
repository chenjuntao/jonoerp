var sheetName = '餐厅部门营业收入运营数据';

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ ]
	};

function fillData(columns){
	data.columns = columns;
	exportXls();
}


function exportXls() {
	dojo.byId('append').value = getAppendValue();
	
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/reportmanage/special/common/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData =  JSON.stringify(data);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

