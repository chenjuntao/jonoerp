var sheetName = '中央工厂生产计划单';


function updateCopy(object){
	var columns = object.columns;
	dataCopy.columns = [];
	
	if(columns.length == 0){
		dataCopy.columns = dataCopy.columns.concat(data.columns);
	}else{
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
			width : 50,
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '生产计划单编号',
			name : 'form_id',
			align : 'center',
			width :200
		},{
			display : '制单人员编码',
			name : 'form_maker_id',
			align : 'center',
			width :120
		},{
			display : '制单人员',
			name : 'form_maker',
			align : 'center',
			width : 120
		},{
			display : '制单日期',
			name : 'form_time',
			align : 'left',
			width : 100
		},{
			display : '审核人员编码',
			name : 'auditor_id',
			align : 'left',
			width : 120
		},{
			display : '审核人员',
			name : 'auditor',
			align : 'center',
			width : 100
		},{
			display : '审核日期',
			name : 'audit_time',
			align : 'center',
			width : 100
		},{
			display : '备注信息',
			name : 'form_note',
			align : 'center',
			width : 120
		},{
			display : '单据状态',
			name : 'status',
			align : 'center',
			width : 100
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/reportmanage/common/export/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData =  JSON.stringify(dataCopy);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

