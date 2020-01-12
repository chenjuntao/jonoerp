var sheetName = '餐厅成本差异分析表';

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '餐厅',
			name : 'branchName',
			align : 'left',
			width : 120
		},{
			display : '出品金额',
			name : 'produceAmt',
			align : 'right',
			width : 120
		},{
			display : '营业额',
			name : 'businessAmt',
			align : 'right',
			width : 120
		},{
			display : '期初库存',
			name : 'beginAmt',
			align : 'right',
			width : 120
		},{
			display : '本期进货',
			name : 'inAmt',
			align : 'right',
			width : 120
		},{
			display : '期末库存',
			name : 'resultAmt',
			align : 'right',
			width : 120
		},{
			display : '实际成本',
			name : 'actualCost',
			align : 'right',
			width : 120
		},{
			display : '实际成本比例',
			name : 'actualCostPer',
			align : 'right',
			width : 150
		},{
			display : '基本成本额',
			name : 'baseAmt',
			align : 'right',
			width : 120
		},{
			display : '折前成本率',
			name : 'beforeCostPer',
			align : 'right',
			width : 150
		},{
			display : '折后成本率',
			name : 'afterCostPer',
			align : 'right',
			width : 150
		},{
			display : '员工餐',
			name : 'staffCost',
			align : 'right',
			width : 120
		},{
			display : '员工餐比例',
			name : 'staffCostPer',
			align : 'right',
			width : 150
		},{
			display : '赠送',
			name : 'presentCost',
			align : 'right',
			width : 120
		},{
			display : '赠送比例',
			name : 'presentCostPer',
			align : 'right',
			width : 150
		},{
			display : '成品损耗',
			name : 'dishAmt',
			align : 'right',
			width : 120
		},{
			display : '成品损耗比例',
			name : 'dishPer',
			align : 'right',
			width : 150
		},{
			display : '半成品损耗',
			name : 'rawAmt',
			align : 'right',
			width : 120
		},{
			display : '半成品损耗比例',
			name : 'rawPer',
			align : 'right',
			width : 150
		},{
			display : '调味料损耗',
			name : 'oilAmt',
			align : 'right',
			width : 120
		},{
			display : '调味料损耗比例',
			name : 'oilPer',
			align : 'right',
			width : 150
		},{
			display : '损益',
			name : 'lossGain',
			align : 'right',
			width : 120
		},{
			display : '损益比例',
			name : 'lossGainPer',
			align : 'right',
			width : 150
		},{
			display : '管理成本模块总成本率',
			name : 'totalPer',
			align : 'right',
			width : 250
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/reportmanage/restaurant/cost/jono/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData =  JSON.stringify(data);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

