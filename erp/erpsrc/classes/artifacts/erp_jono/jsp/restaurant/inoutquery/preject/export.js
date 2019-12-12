function exportXls(_type) {
	var branchType = dojo.byId('branchType').value;
	var sheetName = '餐厅采购退货单';
	
	if("LOGISTICSCENTER" == branchType){
		sheetName = '物流中心采购退货单';
	}else if("OUTERORDER" == branchType){
		sheetName = '外部订货采购退货单';
	}else if("CENTRALFACTORY" == branchType){
		sheetName = '中央工厂采购退货单';
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
			display : '退货单号',
			name : 'formId',
			align : 'center',
			width : 160
		},{
			display : '供应商',
			name : 'provider',
			align : 'center',
			width : 200
		},{
			display : '入库单编号',
			name : 'formRefId',
			align : 'center',
			width : 150
		},{
			display : '入库部门',
			name : 'inputDepartment',
			align : 'center',
			width : 200
		},{
			display : '入库人员',
			name : 'inputer',
			align : 'center',
			width : 150
		},{
			display : '仓库',
			name : 'storage',
			align : 'center',
			width : 200
		},{
			display : '入库日期',
			name : 'inputTime',
			align : 'center',
			width : 150
		} ,{
			display : '退货人员',
			name : 'returner',
			align : 'center',
			width : 150
		},{
			display : '退货日期',
			name : 'returnTime',
			align : 'center',
			width : 150
		},{
			display : '备注',
			name : 'formNote',
			align : 'center',
			width : 150
		},{
			display : '主要退货',
			name : 'maxPayItem',
			align : 'center',
			width : 150
		},{
			display : '退货总额',
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
	var _url = appRoot + "/bq/inoutquery/preject/export/export.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		branchId : dojo.byId("branchId").value,
		branchType :branchType,
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