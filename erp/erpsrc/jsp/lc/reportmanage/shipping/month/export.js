var sheetName = '物流中心配送明细报表';


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
			display : '审核日期',
			name : 'audit_time',
			align : 'center',
			width : 100
		},{
			display : '配送部门',
			name : 'provider',
			align : 'left',
			width : 150
		},{
			display : '出货仓库',
			name : 'out_storage',
			align : 'left',
			width : 180
		},{
			display : '订货部门',
			name : 'requester',
			align : 'left',
			width : 150
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
			display : '类别编码',
			name : 'item_category',
			align : 'center',
			width : 100
		},{
			display : '类别名称',
			name : 'category_name',
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
			display : '包装因子',
			name : 'packaging_factor',
			align : 'right',
			width : 80
		},{
			display : '包装单位',
			name : 'packaging_unit',
			align : 'center',
			width : 100
		},{
			display : '包装数量',
			name : 'packaging_count',
			align : 'right',
			width : 100
		},{
			display : '订货数',
			name : 'request_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '配送数',
			name : 'shipping_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '实发数',
			name : 'delivery_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '实收数',
			name : 'receive_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '差异数',
			name : 'different_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '标准单价',
			name : 'item_unit_price',
			align : 'right',
			width : 100
		},{
			display : '金额',
			name : 'pay_amt',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '差异原因',
			name : 'different_reason',
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

