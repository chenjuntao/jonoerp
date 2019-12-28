var sheetName = '配送反审核汇总报表';


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
			display : '反审核部门编码',
			name : 'antiaudit_branch_id',
			align : 'center',
			width : 150
		},{
			display : '反审核部门',
			name : 'antiaudit_branch',
			align : 'center',
			width : 150
		},{
			display : '配送部门',
			name : 'provider',
			align : 'left',
			width :120
		},{
			display : '出货仓库',
			name : 'out_storage',
			align : 'left',
			width :150
		},{
			display : '订货部门',
			name : 'requester',
			align : 'left',
			width : 150
		},{
			display : '入库仓库',
			name : 'in_storage',
			align : 'left',
			width : 150
		},{
			display : '商品编码',
			name : 'item_id',
			align : 'center',
			width : 120
		},{
			display : '商品名称',
			name : 'item_name',
			align : 'left',
			width : 150
		},{
			display : '反审核实收数',
			name : 'antiaudit_receive_count',
			align : 'right',
			width : 150,
			sum : {
				count : true
			}
		},{
			display : '反审核金额',
			name : 'antiaudit_pay_amt',
			align : 'right',
			width : 120,
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

