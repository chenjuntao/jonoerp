var sheetName = '入库退货明细报表';


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
			display : '审核日期',
			name : 'audit_times',
			align : 'center',
			width : 100
		},{
			display : '入库单号',
			name : 'form_ids',
			align : 'center',
			width : 100
		},{
			display : '供应商',
			name : 'providers',
			align : 'center',
			width : 100
		},{
			display : '供应商编码',
			name : 'provider_ids',
			align : 'center',
			width : 100
		},{
			display : '部门编码',
			name : 'input_department_ids',
			align : 'center',
			width : 150
		},{
			display : '部门名称',
			name : 'input_departments',
			align : 'left',
			width : 150
		},{
			display : '仓库编码',
			name : 'storage_ids',
			align : 'center',
			width : 100
		},{
			display : '仓库名称',
			name : 'storages',
			align : 'left',
			width : 120
		},{
			display : '商品编码',
			name : 'item_ids',
			align : 'center',
			width : 80
		},{
			display : '商品名称',
			name : 'item_names',
			align : 'left',
			width : 120
		},{
			display : '类别编码',
			name : 'item_categorys',
			align : 'center',
			width : 100
		},{
			display : '单位',
			name : 'item_dimensions',
			align : 'center',
			width : 100
		},{
			display : '规格',
			name : 'item_specifications',
			align : 'left',
			width : 150
		},{
			display : '订货量',
			name : 'order_counts',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '实收数',
			name : 'receive_counts',
			align : 'left',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '实收差异',
			name : 'different_counts',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '标准单价',
			name : 'item_unit_prices',
			align : 'left',
			width : 100
		},{
			display : '标准金额',
			name : 'pay_amts',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '进货单价',
			name : 'receive_prices',
			align : 'right',
			width : 100
		},{
			display : '进货金额',
			name : 'receive_amts',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '采购价差',
			name : 'different_amts',
			align : 'right',
			width : 100
		},{
			display : '退货单号',
			name : 'form_ref_ids',
			align : 'right',
			width : 100
		},{
			display : '退货数',
			name : 'return_counts',
			align : 'right',
			width : 100
		},{
			display : '退货金额',
			name : 'return_pay_amts',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '退货原因',
			name : 'return_reasons',
			align : 'right',
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

