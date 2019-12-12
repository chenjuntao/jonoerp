function exportXls(_type) {
	var sheetName = '出品明细统计信息';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '出品码',
			name : 'foodC',
			align : 'center',
			width : 80	,
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '出品名称',
			name : 'foodN',
			align : 'center',
			width : 200
		},{
			display : '出品小类',
			name : 'litCls',
			align : 'center',
			width : 80
		},{
			display : '例牌',
			name : 'unit',
			align : 'center',
			width : 60
		},{
			display : '标准单价',
			name : 'price',
			align : 'center',
			width : 80
		},{
			display : '数量',
			name : 'quantity',
			align : 'center',
			width : 60
		},{
			display : '消费金额',
			name : 'amt',
			align : 'center',
			width : 80,
			sum : {
				count : true
			}
		},{
			display : '折扣金额',
			name : 'disAmt',
			align : 'center',
			width : 80,
			sum : {
				count : true
			}
		},{
			display : '套餐标志',
			name : 'suitFlag',
			align : 'center',
			width : 100
		},{
			display : '退品或赠送标志',
			name : 'retSendFlag',
			align : 'center',
			width : 120
		},{
			display : '退品或赠送原因',
			name : 'retSendRemark',
			align : 'center',
			width : 150
		},{
			display : '退品或赠送人',
			name : 'retSendMan',
			align : 'center',
			width : 120
		}]
	};
	var _url = appRoot + "/bq/foodlist/export/doQuery.action";
	_url = getUrl(_url);
	var _params = {
			billC : dojo.byId('billC').value,
		jsonData : JSON.stringify(data)
	};
	if(_type!=undefined){
		_params.type = _type;
	}
	post_redirect(_url, _params);
}

function exportCsv() {
	exportXls("csv");
}