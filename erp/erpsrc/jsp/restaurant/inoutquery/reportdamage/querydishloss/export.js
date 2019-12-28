function exportXls(_type) {
	var branchType = dojo.byId('branchType').value;
	
	var sheetName = '餐厅出品报损';
	if("LOGISTICSCENTER" == branchType){
		sheetName= '物流中心出品报损单';
	}else if("CENTRALFACTORY" == branchType){
		sheetName= '中央工厂出品报损单';
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
				display : '报损单编号',
				name : 'formId',
				align : 'center',
				width : 150
			},{
				display : '报损部门',
				name : 'lossBranch',
				align : 'center',
				width : 150
			},{
				display : '报损仓库',
				name : 'storage',
				align : 'center',
				width : 150
			},{
				display : '报损人员',
				name : 'lossMan',
				align : 'center',
				width : 120
			},{
				display : '报损日期',
				name : 'lossTime',
				align : 'center',
				width : 150
			},{
				display : '审核人员',
				name : 'auditor',
				align : 'center',
				width : 100
			},{
				display : '审核日期',
				name : 'auditTime',
				align : 'center',
				width : 120
			},{
				display : '备注',
				name : 'formNote',
				align : 'center',
				width : 150
			},{
				display : '主要报损品',
				name : 'maxPayItem',
				align : 'center',
				width : 150
			},{
				display : '报损总额',
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
	var _url = appRoot + "/bq/inoutquery/reportdamage/export/export.action?branchType="+branchType;
	_url = getUrl(_url);
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		branchId : dojo.byId("branchId").value,
		storageId : dojo.byId('storageId').value,
		lossType: "DISHLOSS",
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