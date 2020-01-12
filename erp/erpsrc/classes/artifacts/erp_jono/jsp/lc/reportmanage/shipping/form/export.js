var sheetName = '物流中心配送单';


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
			display : '制单日期',
			name : 'form_time',
			align : 'center',
			width : 100
		},{
			display : '配送单号',
			name : 'form_id',
			align : 'center',
			width : 150
		},{
			display : '要货单号',
			name : 'form_ref_id',
			align : 'center',
			width : 150
		},{
			display : '单据状态',
			name : 'status',
			align : 'center',
			width : 100
		},{
			display : '是否捡货',
			name : 'pick_status',
			align : 'center',
			width : 100
		},{
			display : '是否入库',
			name : 'input_status',
			align : 'center',
			width : 100
		},{
			display : '是否在途',
			name : 'on_status',
			align : 'center',
			width : 100
		},{
			display : '配送日期',
			name : 'receive_time',
			align : 'center',
			width : 100
		},{
			display : '配送部门',
			name : 'provider',
			align : 'left',
			width : 200
		},{
			display : '出货仓库',
			name : 'out_storage',
			align : 'left',
			width : 180
		},{
			display : '订货部门',
			name : 'requester',
			align : 'left',
			width : 200
		},{
			display : '入库仓库',
			name : 'in_storage',
			align : 'left',
			width : 200
		},{
			display : '制单人员',
			name : 'form_maker',
			align : 'left',
			width : 100
		},{
			display : '拣货人员',
			name : 'auditor',
			align : 'left',
			width : 100
		},{
			display : '拣货日期',
			name : 'audit_time',
			align : 'center',
			width : 100
		},{
			display : '入库人员',
			name : 'inputer',
			align : 'left',
			width : 100
		},{
			display : '入库时间',
			name : 'input_time',
			align : 'center',
			width : 100
		},{
			display : '总金额',
			name : 'all_pay_amt',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
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

