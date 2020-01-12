function exportXls(_type) {
	var branchType =  dojo.byId("branchType").value;
	
	var sheetName = '餐厅配送退货单';
	if(branchType == "LOGISTICSCENTER"){
		sheetName = '配送退货单';
	}
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 50
		},{
			display : '退货单号',
			name : 'formId',
			align : 'center',
			width : 150
		},{
			display : '退货部门',
			name : 'returnBranch',
			align : 'center',
			width : 200
		},{
			display : '退货人员',
			name : 'returner',
			align : 'center',
			width : 100
		},{
			display : '退货日期',
			name : 'returnTime',
			align : 'center',
			width : 100
		},{
			display : '退货备注',
			name : 'formNote',
			align : 'center',
			width : 150
		},{
			display : '配送部门',
			name : 'provider',
			align : 'center',
			width : 200
		} ,{
			display : '配送日期',
			name : 'receiveTime',
			align : 'center',
			width : 100
		},{
			display : '订货部门',
			name : 'requester',
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
			width : 100
		},{
			display : '配送单备注',
			name : 'snote',
			align : 'center',
			width : 150
		},{
			display : '主要配送品',
			name : 'maxPayItem',
			align : 'center',
			width : 150
		},{
			display : '单据状态',
			name : 'formStatus',
			align : 'center',
			width : 100
		}]
	};
	var _url = appRoot + "/bq/inoutquery/dreject/export/export.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		branchId : dojo.byId("branchId").value,
		branchType : branchType,
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