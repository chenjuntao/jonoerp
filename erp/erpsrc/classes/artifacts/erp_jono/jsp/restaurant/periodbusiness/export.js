
function exportXls(_type) {
	var sheetName = '时段营业分析表';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [{
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 80
		}, {
			display : '时段',
			name : 'timeSlot',
			align : 'center',
			width : 80
		}, {
			display : '结单人数',
			name : 'guestCount',
			width : 120,
			align : 'center'
		},{
			display : '单数',
			name : 'billCount',
			align : 'center',
			width : 90
		},{
			display : '人均时间',
			name : 'guestPerTime',
			align : 'center',
			width : 100
		},{
			display : '人均消费',
			name : 'guestPerCost',
			align : 'center',
			width : 150
		},{
			display : '消费总额',
			name : 'foodAmtSum',
			align : 'center',
			width : 100
		},{
			display : '结单金额',
			name : 'oughtAmt',
			align : 'center',
			width : 100
		},{
			display : '实收金额',
			name : 'payAmt',
			align : 'center',
			width : 100
		} ,{
			display : '门店编码',
			name : 'shopC',
			align : 'center',
			width : 100
		},{
			display : '门店名称',
			name : 'shopName',
			align : 'center',
			width : 120
		}]
	};
	var _url = appRoot + "/bq/periodbusiness/export/export.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		shopC: dojo.byId('shopC').value,
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