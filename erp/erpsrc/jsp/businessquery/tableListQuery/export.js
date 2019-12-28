function exportXls(_type) {
	var sheetName = '台位营业统计信息';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '台位名称',
			name : 'table',
			width : 120,
			align : 'center',
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '消费金额',
			name : 'foodAmt',
			align : 'center',
			width : 90,
			sum : {
				count : true
			}
		},{
			display : '单数',
			name : 'billNum',
			align : 'center',
			width : 80,
			sum : {
				count : true
			}
		},{
			display : '人数',
			name : 'guestNum',
			align : 'center',
			width : 150,
			sum : {
				count : true
			}
		}]
	};
	var _url = appRoot + "/bq/table/export/doQuery.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate	: dojo.byId('startDate').value,
		endDate		: dojo.byId('endDate').value,
		shopC		: dojo.byId('shopC').value,
		jsonData 	: JSON.stringify(data)
	};
	if(_type!=undefined){
		_params.type = _type;
	}
	post_redirect(_url, _params);
}

function exportCsv() {
	exportXls("csv");
}