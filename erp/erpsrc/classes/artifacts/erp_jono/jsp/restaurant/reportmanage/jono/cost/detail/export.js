var sheetName = '餐厅成本差异分析明细表';

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 70
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
			display : '库存单位',
			name : 'item_dimension',
			align : 'center',
			width : 100
		},{
			display : '单价',
			name : 'item_price',
			align : 'right',
			width : 80
		},{
			display : '期初库存数量',
			name : 'begin_count',
			align : 'right',
			width : 120
		},{
			display : '期初库存金额',
			name : 'begin_amt',
			align : 'right',
			width : 120
		},{
			display : '本期进货',
			name : 'sum1',
			align : 'right',
			width : 120
		},{
			display : '本期进货金额',
			name : 'amt1',
			align : 'right',
			width : 120
		},{
			display : '调入数量',
			name : 'sum2',
			align : 'right',
			width : 120
		},{
			display : '调入金额',
			name : 'amt2',
			align : 'right',
			width : 120
		},{
			display : '调出数量',
			name : 'sum3',
			align : 'right',
			width : 120
		},{
			display : '调出金额',
			name : 'amt3',
			align : 'right',
			width : 120
		},{
			display : '期末库存数量',
			name : 'end_count',
			align : 'right',
			width : 120
		},{
			display : '期末库存金额',
			name : 'end_amt',
			align : 'right',
			width : 120
		},{
			display : '消耗数量',
			name : 'sum7',
			align : 'right',
			width : 120
		},{
			display : '消耗金额',
			name : 'amt7',
			align : 'right',
			width : 120
		},{
			display : '标准数量',
			name : 'sum4',
			align : 'right',
			width : 120
		},{
			display : '标准金额',
			name : 'amt4',
			align : 'right',
			width : 120
		},{
			display : '原料报损数量',
			name : 'sum5',
			align : 'right',
			width : 120
		},{
			display : '原料报损金额',
			name : 'amt5',
			align : 'right',
			width : 120
		},{
			display : '出品报损数量',
			name : 'sum6',
			align : 'right',
			width : 120
		},{
			display : '出品报损金额',
			name : 'amt6',
			align : 'right',
			width : 120
		},{
			display : '差异数量',
			name : 'sum8',
			align : 'right',
			width : 120
		},{
			display : '差异金额',
			name : 'amt8',
			align : 'right',
			width : 120
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/reportmanage/restaurant/cost/jono/detailExport.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData =  JSON.stringify(data);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

