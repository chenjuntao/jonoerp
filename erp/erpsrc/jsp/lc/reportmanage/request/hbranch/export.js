var sheetName = '要货汇总报表(按门店)';

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ ]
	};

var dataClone = {};

function fillData(columns,content){
	
	require([ "dojo/_base/lang" ], function(lang) {
		
		//拷贝副本
		dataClone = lang.clone(data)
		dataClone.content = content;
		dataClone.columns = columns;
		exportXls();
	});
}


function setColumns(columns,array){
	array.forEach(columns, function(outItem) {
		array.forEach(dataClone.columns, function(inItem) {
			if(outItem.field == inItem.name){
				inItem.display = outItem.label;
			}
		});
	});
	exportXls();
}

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/common/function/export.action";
	_url = getUrl(_url);
	
	var _params = {};
	_params.jsonData =  JSON.stringify(dataClone);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

