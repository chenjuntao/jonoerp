var sheetName = '餐厅调拨汇总报表';

function updateCopy(object){
	dataCopy.columns = [];
	var columns = object.columns;
	
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
			display : '调入部门',
			name : 'in_branch',
			align : 'left',
			width : 130
		},{
			display : '调入仓库',
			name : 'in_storage',
			align : 'left',
			width : 150
		},{
			display : '调出部门',
			name : 'out_branch',
			align : 'left',
			width : 130
		},{
			display : '调出仓库',
			name : 'out_storage',
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
			width : 100
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
			display : '申请调拨数量',
			name : 'apply_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '实际调拨数量',
			name : 'actual_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '调拨差异',
			name : 'different_count',
			align : 'right',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '金额',
			name : 'pay_amt',
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

