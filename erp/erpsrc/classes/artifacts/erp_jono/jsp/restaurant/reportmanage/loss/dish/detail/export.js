var sheetName = '餐厅出品报损明细报表';

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
			display : '报损日期',
			name : 'loss_time ',
			align : 'center',
			width : 100
		},{
			display : '单据编号',
			name : 'form_id',
			align : 'center',
			width : 150
		},{
			display : '审核日期',
			name : 'audit_time',
			align : 'center',
			width : 100
		},{
			display : '门店编码',
			name : 'loss_branch_id',
			align : 'center',
			width : 100
		},{
			display : '门店名称',
			name : 'loss_branch',
			align : 'left',
			width : 150
		},{
			display : '仓库编码',
			name : 'storage_id',
			align : 'center',
			width : 80
		},{
			display : '仓库名称',
			name : 'storage',
			align : 'left',
			width : 180
		},{
			display : '出品编码',
			name : 'item_id2',
			align : 'center',
			width : 100
		},{
			display : '出品名称',
			name : 'item_name2',
			align : 'left',
			width : 150
		},{
			display : '例牌',
			name : 'item_dimension2',
			align : 'center',
			width : 60
		},{
			display : '出品报损数量',
			name : 'item_count2',
			align : 'right',
			width : 120
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
			width : 60
		},{
			display : '规格',
			name : 'item_specification',
			align : 'left',
			width : 100
		},{
			display : '原料报损数量',
			name : 'item_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '标准单价',
			name : 'unit_price',
			align : 'right',
			width : 100
		},{
			display : '报损金额',
			name : 'pay_amt',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '报损原因',
			name : 'reason',
			align : 'right',
			width : 150
		}
		]
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

