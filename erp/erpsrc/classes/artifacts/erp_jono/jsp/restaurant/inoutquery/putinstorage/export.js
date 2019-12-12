function exportXls(_type) {
	var sheetName = '餐厅入库单';
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
			display : '入库单号',
			name : 'formId',
			align : 'center',
			width : 150
		},{
			display : '供应商',
			name : 'provider',
			align : 'center',
			width : 200
		},{
			display : '采购单号',
			name : 'formRefId',
			align : 'center',
			width : 150
		},{
			display : '入库部门',
			name : 'inputDepartment',
			align : 'center',
			width : 150
		},{
			display : '入库人员',
			name : 'inputer',
			align : 'center',
			width : 100
		},{
			display : '入库日期',
			name : 'inputTime',
			align : 'center',
			width : 120
		} ,{
			display : '备注',
			name : 'formNote',
			align : 'center',
			width : 120
		},{
			display : '主要入库品',
			name : 'maxPayItem',
			align : 'maxPayItem',
			width : 150
		},{
			display : '入库总额',
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
	var _url = appRoot + "/bq/inoutquery/putinstorage/export/export.action";
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