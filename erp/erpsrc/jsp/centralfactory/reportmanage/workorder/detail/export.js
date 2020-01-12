var sheetName = '中央工厂生产工单制程明细报表';

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
			display : '单据编号',
			name : 'form_id',
			align : 'center',
			width : 200
		},{
			display : '商品编码',
			name : 'item_id',
			align : 'center',
			width : 100
		},{
			display : '商品名称',
			name : 'item_name',
			align : 'left',
			width : 120
		},{
			display : '制程顺序号',
			name : 'production_step',
			align : 'center',
			width : 80
		},{
			display : '制程',
			name : 'production_name',
			align : 'left',
			width : 80
		},{
			display : '日期',
			name : 'production_time',
			align : 'center',
			width : 80
		},{
			display : '产量',
			name : 'production_count',
			align : 'right',
			width : 80,
			sum : {
				count : true
			}
		},{
			display : '生产人员',
			name : 'production_man',
			align : 'left',
			width : 80
		},{
			display : '备注',
			name : 'production_note',
			align : 'left',
			width : 120
		},{
			display : '是否完成',
			name : 'is_completed',
			align : 'left',
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

