var sheetName = '操作日志';


function updateCopy(object){
	dataCopy.columns = [];
	
	var columns = object.columns;
	
	for(var i=0,lengthI = data.columns.length; i< lengthI ; i++){
		var col1 = data.columns[i].name;
		
		for(var j=0,lengthJ = columns.length; j<lengthJ; j++){
			var col2 = columns[j];
			
		
			if(col1 == col2){
				dataCopy.columns.push(data.columns[i]);
				break;
			}
		}
	}
}

var dataCopy = {
	sheetName : sheetName,
	title : {
		text : sheetName
	},
	columns : []
};

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 50
		},{
			display : '操作时间',
			name : 'operate_time',
			align : 'left',
			width : 150
		},{
			display : '单据编码',
			name : 'business_id',
			align : 'left',
			width :150
		},{
			display : '操作类型',
			name : 'operate_type',
			align : 'left',
			width : 100
		},{
			display : '简要说明',
			name : 'title',
			align : 'left',
			width : 120
		},{
			display : '详细说明',
			name : 'operate_desc',
			align : 'left',
			width : 150
		},{
			display : '操作人编码',
			name : 'operator_id',
			align : 'left',
			width : 120
		},{
			display : '操作人名称',
			name : 'operator_name',
			align : 'left',
			width : 120
		},{
			display : '门店编码',
			name : 'branch_id',
			align : 'left',
			width : 120
		},{
			display : '门店名称',
			name : 'branch_name',
			align : 'left',
			width : 120
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/reportmanage/common/export/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData =  JSON.stringify(dataCopy);
	_params.dateFormat = dojo.byId("dateFormat").value;
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

