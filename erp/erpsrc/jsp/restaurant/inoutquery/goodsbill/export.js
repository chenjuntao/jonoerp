function exportXls(_type) {
	var sheetName = '餐厅要货单';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 50,
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '要货单编号',
			name : 'formId',
			align : 'center',
			width : 150
		},{
			display : '要货部门',
			name : 'buyerName',
			align : 'center',
			width : 120
		},{
			display : '制单人',
			name : 'formMaker',
			align : 'center',
			width : 100
		},{
			display : '制单日期',
			name : 'formTime',
			align : 'center',
			width : 120
		},{
			display : '到货日期',
			name : 'receiveTime',
			align : 'center',
			width : 120
		},{
			display : '备注',
			name : 'formNote',
			align : 'center',
			width : 120
		} ,{
			display : '要货模板',
			name : 'templateId',
			align : 'center',
			width : 100
		},{
			display : '主要要货',
			name : 'maxPayItem',
			align : 'center',
			width : 150
		},{
			display : '要货总额',
			name : 'allPayAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '单据状态',
			name : 'formStatus',
			align : 'center',
			width : 100
		}]
	};
	var _url = appRoot + "/bq/inoutquery/goodsbill/export/export.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		branchId : dojo.byId("branchId").value,
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