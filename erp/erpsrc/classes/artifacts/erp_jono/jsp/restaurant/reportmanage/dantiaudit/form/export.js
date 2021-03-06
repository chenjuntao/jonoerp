var sheetName = '餐厅配送反审核单';


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
			display : '反审核日期',
			name : 'antiaudit_time',
			align : 'center',
			width : 100
		},{
			display : '配送单号',
			name : 'form_ref_id',
			align : 'center',
			width :150
		},{
			display : '反审核人员',
			name : 'antiauditor',
			align : 'center',
			width :120
		},{
			display : '反审核部门',
			name : 'antiaudit_branch',
			align : 'center',
			width :130
		},{
			display : '备注信息',
			name : 'form_note',
			align : 'center',
			width : 120
		},{
			display : '配送部门',
			name : 'provider',
			align : 'left',
			width : 130
		},{
			display : '出货仓库',
			name : 'out_storage',
			align : 'left',
			width : 180
		},{
			display : '订货部门',
			name : 'requester',
			align : 'left',
			width : 130
		},{
			display : '配送日期',
			name : 'receive_time',
			align : 'center',
			width : 100
		},{
			display : '入库人员',
			name : 'inputer',
			align : 'center',
			width : 100
		},{
			display : '入库日期',
			name : 'input_time',
			align : 'center',
			width : 100
		},{
			display : '主要配送品',
			name : 'max_pay_item',
			align : 'left',
			width : 180
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

