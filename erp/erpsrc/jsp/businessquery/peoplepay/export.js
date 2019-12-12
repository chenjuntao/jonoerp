
function exportXls(_type) {
	var sheetName = '按人员付款方式';
	
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [  {
			display : '门店名称',
			name : 'cBranch_N',
			align : 'center',
			width : 120,
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '结帐人',
			name : 'cSettleMan',
			align : 'center',
			width : 120
		}]
	};
	
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
			url : appRoot + "/businessquery/peoplepay/getColumn.action",
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