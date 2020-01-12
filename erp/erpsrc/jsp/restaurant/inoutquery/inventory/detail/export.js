function exportXls(_type) {
	var branchType = dojo.byId('branchType').value;
	var sheetName = '餐厅仓库明细帐';
	if("LOGISTICSCENTER" == branchType){
		sheetName = '物流中心仓库明细帐';
	}
	
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rowNumber',
			align : 'center',
			width : 50,
			sum : {
				count : false,
				text : '合计'
			}
		},{
			display : '门店',
			name : 'branchName',
			align : 'center',
			width : 150
		},{
			display : '仓库',
			name : 'storageName',
			align : 'center',
			width : 180
		},{
			display : '原料编码',
			name : 'itemId',
			align : 'center',
			width : 100
		},{
			display : '原料名称',
			name : 'itemName',
			align : 'center',
			width : 150
		},{
			display : '操作时间',
			name : 'operationTime',
			align : 'center',
			width : 160
		},{
			display : '单据日期',
			name : 'businessDate',
			align : 'center',
			width : 120
		},{
			display : '单据号码',
			name : 'formId',
			align : 'center',
			width : 160
		},{
			display : '业务类型',
			name : 'reason',
			align : 'center',
			width : 130
		},{
			display : '期初数量',
			name : 'orgiCount',
			align : 'center',
			width : 100
		},{
			display : '入库数量',
			name : 'itemCountIn',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '入库金额',
			name : 'itemCountInMoney',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '出库数量',
			name : 'itemCountOut',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '出库金额',
			name : 'itemCountOutMoney',
			align : 'center',
			width : 100,
			sum : {
				count : true
			}
		},{
			display : '结存数量',
			name : 'resultCount',
			align : 'center',
			width : 100
		},{
			display : '结存单价',
			name : 'itemUnitPrice',
			align : 'center',
			width : 100
		},{
			display : '结存金额',
			name : 'resultCountMoney',
			align : 'center',
			width : 100
		}]
	};
	var _url = appRoot + "/bq/inoutquery/inventory/detail/export/detailExport.action";
	_url = getUrl(_url);
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		branchId : dojo.byId("branchId").value,
		storageId : dojo.byId('storageId').value,
		selectedItemId : dojo.byId('selectedItemId').value,
		reason : dojo.byId('reason').value,
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