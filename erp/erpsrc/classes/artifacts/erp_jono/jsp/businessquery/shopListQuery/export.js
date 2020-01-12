
function byDay(_type){
	var sheetName = '门店收入明细信息-按天';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [  {
			display : '日期',
			name : 'businessDate',
			align : 'center',
			width : 100		,
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '门店编号',
			name : 'shopC',
			align : 'center',
			width : 100
		}, {
			display : '门店名称',
			name : 'shopName',
			width : 120,
			align : 'center'
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
			display : '单均消费',
			name : 'amtPerBill',
			align : 'center',
			width : 150
		},{
			display : '人数',
			name : 'guestNum',
			align : 'center',
			width : 80,
			sum : {
				count : true
			}
		},{
			display : '人均消费',
			name : 'amtPerGuest',
			align : 'center',
			width : 90
		},{
			display : '台位数',
			name : 'tableNum',
			align : 'center',
			width : 90,
			sum : {
				count : true
			}
		} ,{
			display : '台均消费',
			name : 'amtPerTable',
			align : 'center',
			width : 150
		},{
			display : '台位周转率',
			name : 'guestPerTable',
			align : 'center',
			width : 120
		},{
			display : '总折扣额',
			name : 'disAmt',
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
			display : '应付金额',
			name : 'oughtAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '付款金额',
			name : 'payAmt',
			align : 'center',
			width : 90,
			sum : {
				count : true
			}
		}]
	};
	var _url = appRoot + "/bq/shop/export/byDay.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		jsonData : JSON.stringify(data)
	};
	if(_type!=undefined){
		_params.type = _type;
	}
	post_redirect(_url, _params);
}


function exportXls(_type) {
	var type = dojo.byId("type").value;
	if (type == "2") {
		byDay(_type);
		return;
	}
	var sheetName = '门店收入明细信息-累计';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '门店编号',
			name : 'shopC',
			align : 'center',
			width : 100,
			sum : {
				count : false,
				text : '合计'
			}
		}, {
			display : '门店名称',
			name : 'shopName',
			width : 120,
			align : 'center'
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
			display : '单均消费',
			name : 'amtPerBill',
			align : 'center',
			width : 150
		},{
			display : '人数',
			name : 'guestNum',
			align : 'center',
			width : 80,
			sum : {
				count : true
			}
		},{
			display : '人均消费',
			name : 'amtPerGuest',
			align : 'center',
			width : 90
		},{
			display : '台位数',
			name : 'tableNum',
			align : 'center',
			width : 90,
			sum : {
				count : true
			}
		} ,{
			display : '台均消费',
			name : 'amtPerTable',
			align : 'center',
			width : 150
		},{
			display : '台位周转率',
			name : 'guestPerTable',
			align : 'center',
			width : 120
		},{
			display : '总折扣额',
			name : 'disAmt',
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
			display : '应付款金额',
			name : 'oughtAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '付款金额',
			name : 'payAmt',
			align : 'center',
			width : 90,
			sum : {
				count : true
			}
		}]
	};
	var _url = appRoot + "/bq/shop/export/doQuery.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
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