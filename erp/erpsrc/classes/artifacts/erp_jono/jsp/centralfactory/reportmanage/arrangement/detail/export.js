var sheetName = '中央工厂生产计划明细报表';


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
			display : 'form_time',
			name : '制单日期',
			align : 'center',
			width : 100
		},{
			display : '单据编号',
			name : 'form_id',
			align : 'center',
			width : 200
		},{
			display : '审核日期',
			name : 'audit_time',
			align : 'center',
			width : 100
		},{
			display : '商品编码',
			name : 'item_id',
			align : 'center',
			width : 100
		},{
			display : '商品名称',
			name : 'item_name',
			align : 'left',
			width : 150
		},{
			display : '单位',
			name : 'item_dimension',
			align : 'center',
			width : 100
		},{
			display : '规格',
			name : 'item_specification',
			align : 'left',
			width : 100
		},{
			display : '计划生产量',
			name : 'produce_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{},{
			display : '工单编号',
			name : 'work_order_id',
			align : 'center',
			width : 180
		},{
			display : '生产车间',
			name : 'workshop',
			align : 'left',
			width : 100
		},{
			display : '生产日期',
			name : 'produce_time',
			align : 'center',
			width : 80
		},{
			display : '生产周期',
			name : 'production_cycle',
			align : 'center',
			width : 80
		},{
			display : '完工日期',
			name : 'complete_time',
			align : 'center',
			width : 80
		},{
			display : '备注',
			name : 'note',
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
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

