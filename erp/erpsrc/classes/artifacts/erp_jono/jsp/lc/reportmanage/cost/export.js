var sheetName = '物流中心成本统计分析';

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '物流中心',
			name : 'branchName',
			align : 'left',
			width : 160
		},{
			display : '仓库名称',
			name : 'storageName',
			align : 'left',
			width : 160
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
			display : '原料损耗',
			name : 'rawAmt',
			align : 'right',
			width : 120
		},{
			display : '餐厅收货',
			name : 'receiveAmt',
			align : 'right',
			width : 120
		},{
			display : '配送差异',
			name : 'diffAmt',
			align : 'right',
			width : 120
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/reportmanage/lc/cost/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData =  JSON.stringify(data);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

