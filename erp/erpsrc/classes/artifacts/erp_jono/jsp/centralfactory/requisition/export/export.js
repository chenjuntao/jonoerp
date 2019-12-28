var sheetName = '领料单';

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			name : 'rownumber',
			align : 'center',
			width : 50
		},{
			name : 'workshop',
			align : 'left',
			width : 100
		},{
			name : 'item_id',
			align : 'center',
			width :100
		},{
			name : 'item_name',
			align : 'left',
			width :180
		},{
			name : 'item_dimension',
			align : 'left',
			width :60
		},{
			name : 'item_count',
			align : 'right',
			width :100
		}]
	};

var dataClone = {};

function fillData(columns,content){
	
	require([ "dojo/_base/array","dojo/_base/lang" ], function(array,lang) {
		
		//拷贝副本
		dataClone = lang.clone(data)
		dataClone.content = content;
		
		//添加动态列
		array.forEach(columns, function(item) {
			if(!isNaN(item.field) && !dataClone.columns.hasOwnProperty(item.field)){
				dataClone.columns.push({
					name: item.field,
					align : 'right',
					width : 100
				});
			}
		});
		
		setColumns(columns,array);
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
	var _type  = 'excel';
	
	var _url = appRoot + "/common/function/export.action";
	_url = getUrl(_url);
	
	var _params = {};
	_params.jsonData =  JSON.stringify(dataClone);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

