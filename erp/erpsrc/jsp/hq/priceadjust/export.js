var sheetName = '调价单';

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
			name : 'itemId',
			align : 'center',
			width : 100
		},{
			name : 'itemName',
			align : 'left',
			width :150
		},{
			name : 'itemDimension',
			align : 'left',
			width :60
		},{
			name : 'itemSpecification',
			align : 'left',
			width :100
		},{
			name : 'itemPackager',
			align : 'right',
			width :80
		},{
			name : 'oldPrice',
			align : 'right',
			width :80
		},{
			name : 'newPrice',
			align : 'right',
			width :80
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
	
	var _params = {};
	_params.jsonData =  JSON.stringify(dataClone);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

