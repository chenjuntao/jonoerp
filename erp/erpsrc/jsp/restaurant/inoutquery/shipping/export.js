function exportXls(_type) {
	var branchType = dojo.byId('branchType').value;
	var sheetName = '餐厅配送单';
	
	if("LOGISTICSCENTER" == branchType){
		sheetName = '物流中心配送单';
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
			width : 50,
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '单据编号',
			name : 'formId',
			align : 'center',
			width : 150
		},{
			display : '单据状态',
			name : 'formStatus',
			align : 'center',
			width : 100
		},{
			display : '是否捡货',
			name : 'pickStatus',
			align : 'center',
			width : 100
		},{
			display : '是否入库',
			name : 'inputStatus',
			align : 'center',
			width : 100
		},{
			display : '是否在途',
			name : 'onStatus',
			align : 'center',
			width : 100
		},{
			display : '配送部门',
			name : 'provider',
			align : 'center',
			width : 160
		},{
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
			display : '订货地址',
			name : 'requestAddress',
			align : 'center',
			width : 250
		} ,{
			display : '制单人员',
			name : 'formMaker',
			align : 'center',
			width : 100
		},{
			display : '制单日期',
			name : 'formTime',
			align : 'center',
			width : 100
		},{
			display : '审核人员',
			name : 'auditor',
			align : 'center',
			width : 100
		},{
			display : '审核日期',
			name : 'auditTime',
			align : 'center',
			width : 100
		},{
			display : '入库人员',
			name : 'inputer',
			align : 'center',
			width : 100
		},{
			display : '入库时间',
			name : 'inputTime',
			align : 'center',
			width : 160
		},{
			display : '备注',
			name : 'formNote',
			align : 'center',
			width : 150
		},{
			display : '金额最大的商品',
			name : 'maxPayItem',
			align : 'center',
			width : 200
		},{
			display : '调拨总额',
			name : 'allPayAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		}]
	};
	var _url = appRoot + "/bq/inoutquery/shipping/export/export.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		branchId : dojo.byId("branchId").value,
		branchType : branchType,
		formType : 'request',
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