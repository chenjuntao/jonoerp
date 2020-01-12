function exportXls(_type) {
	var sheetName = '餐厅盘点单';
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
			display : '清单编号',
			name : 'formId',
			align : 'center',
			width : 200
		},{
			display : '盘点部门',
			name : 'checkBranch',
			align : 'center',
			width : 200
		},{
			display : '盘点批次',
			name : 'checkBatchId',
			align : 'center',
			width : 180
		},{
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
			display : '备注',
			name : 'formNote',
			align : 'center',
			width : 150
		} ,{
			display : '主要盘点物',
			name : 'maxPayItem',
			align : 'center',
			width : 200
		},{
			display : '盘点总额',
			name : 'allPayAmt',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '盘点单状态',
			name : 'formStatus',
			align : 'center',
			width : 100
		}]
	};
	var _url = appRoot + "/bq/inoutquery/checkstorage/export/export.action";
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