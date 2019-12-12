function exportXls(_type) {
	var branchType = dojo.byId("branchType").value;
	
	var sheetName = '餐厅调拨单';
	if("LOGISTICSCENTER" == branchType){
		sheetName = '物流中心调拨单';
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
			display : '调拨单编号',
			name : 'formId',
			align : 'center',
			width : 150
		},{
			display : '单据状态',
			name : 'formStatus',
			align : 'center',
			width : 100
		},{
			display : '调入部门',
			name : 'inBranch',
			align : 'center',
			width : 200
		},{
			display : '调入仓库',
			name : 'inStorage',
			align : 'center',
			width : 200
		},{
			display : '调出部门',
			name : 'outBranch',
			align : 'center',
			width : 200
		},{
			display : '调出仓库',
			name : 'outStorage',
			align : 'center',
			width : 200
		},{
			display : '制单人员',
			name : 'fromMaker',
			align : 'center',
			width : 100
		},{
			display : '制单日期',
			name : 'formTime',
			align : 'center',
			width : 100
		} ,{
			display : '确认人员',
			name : 'confirmer',
			align : 'center',
			width : 100
		},{
			display : '确认日期',
			name : 'confirmTime',
			align : 'center',
			width : 100
		},{
			display : '备注',
			name : 'formNote',
			align : 'center',
			width : 150
		},{
			display : '主要调拨品',
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
	var _url = appRoot + "/bq/inoutquery/allocateitem/export/export.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		inBranchId : dojo.byId("inBranchId").value,
		inStorageId : dojo.byId("inStorageId").value,
		outBranchId : dojo.byId("outBranchId").value,
		outStorageId : dojo.byId("outStorageId").value,
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