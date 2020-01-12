function exportXls(_type) {
	var sheetName = '按单据号查看';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '单据编号',
			name : 'billC',
			align : 'center',
			width : 150,
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '市别',
			name : 'period',
			align : 'center',
			width : 80
		},{
			display : '班次',
			name : 'shift',
			align : 'center',
			width : 80
		},{
			display : '人数',
			name : 'guestNum',
			align : 'center',
			width : 80,
			sum : {
				count : true
			}
		},{
			display : '开台时间',
			name : 'billTime',
			align : 'center',
			width : 180
		},{
			display : '结账时间',
			name : 'settleTime',
			align : 'center',
			width : 180
		},{
			display : '开台人',
			name : 'createMan',
			align : 'center',
			width : 90
		} ,{
			display : '结账人',
			name : 'settleMan',
			align : 'center',
			width : 100
		},{
			display : '消费金额',
			name : 'foodAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '舍尾金额',
			name : 'roundAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '赠送金额',
			name : 'presentAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '付款金额',
			name : 'payAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '总折扣额',
			name : 'disAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		}]
	};
	var _url = appRoot + "/bq/billcode/export/doQuery.action";
	_url = getUrl(_url);
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		billCode : dojo.byId("billCode").value,
		shopC : dojo.byId("shopC").value,
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