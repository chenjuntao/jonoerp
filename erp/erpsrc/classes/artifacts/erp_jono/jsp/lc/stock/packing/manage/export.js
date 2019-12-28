var sheetName = '装箱单';

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
			name : 'boxName',
			align : 'left',
			width : 100
		},{
			name : 'branchId',
			align : 'left',
			width :100
		},{
			name : 'itemId',
			align : 'left',
			width :120
		},{
			name : 'itemName',
			align : 'left',
			width :100
		},{
			name : 'itemCategory',
			align : 'left',
			width : 100
		},{
			name : 'itemDimension',
			align : 'left',
			width : 100
		},{
			name : 'unitVolume',
			align : 'left',
			width : 100
		},{
			name : 'unitWeight',
			align : 'left',
			width : 100
		},{
			name : 'itemCount',
			align : 'right',
			width : 100
		},{
			name : 'volume',
			align : 'right',
			width : 100
		},{
			name : 'weight',
			align : 'right',
			width : 100
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

