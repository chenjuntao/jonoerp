var sheetName = '餐厅耗损考核报表';

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 80
		},{
			display : '商品编码',
			name : 'item_id',
			align : 'center',
			width : 100
		},{
			display : '商品名称',
			name : 'item_name',
			align : 'left',
			width : 200
		},{
			display : '门店',
			name : 'branch_name',
			align : 'left',
			width : 200
		},{
			display : '标准价',
			name : 'item_price',
			align : 'right',
			width : 80
		},{
			display : '原料报损',
			name : 'ylbs',
			align : 'right',
			width : 80
		},{
			display : '出品报损',
			name : 'cpbs',
			align : 'right',
			width : 80
		},{
			display : '盘盈',
			name : 'pdi',
			align : 'right',
			width : 80
		},{
			display : '盘亏',
			name : 'pdo',
			align : 'right',
			width : 80
		},{
			display : '自产损耗',
			name : 'sshl2',
			align : 'right',
			width : 80
		},{
			display : '自产理论扣仓',
			name : 'sshl',
			align : 'right',
			width : 100
		},{
			display : '销售扣仓',
			name : 'llku',
			align : 'right',
			width : 80
		},{
			display : '理论消耗量',
			name : 'theory',
			align : 'right',
			width : 100
		},{
			display : '理论消耗金额',
			name : 'theory_amt',
			align : 'right',
			width : 100
		},{
			display : '实际消耗量',
			name : 'actual',
			align : 'right',
			width : 100
		},{
			display : '实际消耗金额',
			name : 'actual_amt',
			align : 'right',
			width : 100
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/reportmanage/special/common/export.action";
	
	var _params = getQuery();
	_params.jsonData =  JSON.stringify(data);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}
