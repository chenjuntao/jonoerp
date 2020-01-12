
function byDay(_type){
	var sheetName = '营业统计-按天';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		}
	};
	var _url = appRoot + "/bq/shoppayment/export/byDay.action";
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
	var sheetName = '营业统计-累计';
	if(type == "2"){
		sheetName = '营业统计-按天';
	}
	
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [  {
			display : '日期',
			name : 'businessDate',
			align : 'center',
			width : 100
		},{
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
			name : 'shopN',
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
			name : 'payTypeAmt',
			align : 'center',
			width : 90,
			sum : {
				count : true
			}
		}]
	};
	
	if(type == '1'){
		data.columns.shift();
	}
	
	requestPayColumns(_type,data);
}

function getPayColumns(data,response,Memory){
	var store = new Memory({ data: response });
	store.query().forEach(function(item) {
		for ( var k in item) {
			var label = item[k] + "";

			data.columns[data.columns.length] = {name : k,display : label,align : 'center',width : 100,sum : {	count : true}};
		};
	});
}

function execExport(_type,data){
	
	var _url = appRoot + "/bq/shoppayment/export/doQuery.action";
	if(type == "2"){
		 _url = appRoot + "/bq/shoppayment/export/byDay.action";
	}
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

function requestPayColumns(_type,data){
	require([ "dojo/_base/xhr" , "dojo/store/Memory" ], function(xhr,Memory) {
		xhr.post({
			url : appRoot + "/businessquery/shoppayment/getColumn.action",
			form : 'queryForm',
			handleAs : "json",
			load : function(response) {
				getPayColumns(data,response,Memory);
				execExport(_type,data);
			},
			error : function(error) {
				alert(error);
			}
		});
	});
}

function exportCsv() {
	exportXls("csv");
}