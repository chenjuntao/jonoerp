function exportXls(_type) {
	var branchType = dojo.byId('branchType').value;
	
	var sheetName = '仓库汇总帐';
	var data = {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rowNumber',
			align : 'center',
			width : 50
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
			display : '单位',
			name : 'unit',
			align : 'center',
			width : 60
		},{
			display : '期初数量',
			name : 'orgiCount',
			align : 'center',
			width : 100
		},{
			display : '期初金额',
			name : 'orgiCountmoney',
			align : 'center',
			width : 100
		},{
			display : '入库数量合计',
			name : 'itemCountIn',
			align : 'center',
			width : 100
		},{
			display : '入库金额合计',
			name : 'itemCountInMoney',
			align : 'center',
			width : 100
		},{
			display : '出库数量合计',
			name : 'itemCountOut',
			align : 'center',
			width : 100
		},{
			display : '出库金额合计',
			name : 'itemCountOutMoney',
			align : 'center',
			width : 100
		},{
			display : '结存数量',
			name : 'resultCount',
			align : 'center',
			width : 100
		},{
			display : '结存金额',
			name : 'resultCountMoney',
			align : 'center',
			width : 100
		},{
			display : '采购入库数量',
			name : 'putinstorage',
			align : 'center',
			width : 100
		},{
			display : '配送入库数量',
			name : 'distribution',
			align : 'center',
			width : 100
		},{
			display : '配送反审核入库数量',
			name : 'antiauditIn',
			align : 'center',
			width : 150
		},{
			display : '配送反审核出库数量',
			name : 'antiauditOut',
			align : 'center',
			width : 150
		},{
			display : '配送退货数量',
			name : 'preject',
			align : 'center',
			width : 100
		},{
			display : '采购退货数量',
			name : 'preject',
			align : 'center',
			width : 100
		},{
			display : '原料报损数量',
			name : 'rawLoss',
			align : 'center',
			width : 100
		},{
			display : '出品报损数量',
			name : 'dishLoss',
			align : 'center',
			width : 100
		},{
			display : '调拨入库数量',
			name : 'allocateitemIn',
			align : 'center',
			width : 100
		},{
			display : '调拨出库数量',
			name : 'allocateitemOut',
			align : 'center',
			width : 100
		},{
			display : '盘盈数量',
			name : 'checkstorageIn',
			align : 'center',
			width : 100
		},{
			display : '盘亏数量',
			name : 'checkstorageOut',
			align : 'center',
			width : 100
		},{
			display : '理论扣库数量',
			name : 'theoryDeduct',
			align : 'center',
			width : 100
		},{
			display : '仓库',
			name : 'branchName',
			align : 'center',
			width : 150
		},{
			display : '原料类别',
			name : 'itemCategory',
			align : 'center',
			width : 100
		}]
	};
	var _url = appRoot + "/bq/inoutquery/inventory/summary/export/summaryExport.action";
	_url = getUrl(_url);
	
	var _params = {
		startDate : dojo.byId("startDate").value,
		endDate : dojo.byId("endDate").value,
		storageId : dojo.byId('storageId').value,
		branchId : dojo.byId("branchId").value,
		selectedItemId : dojo.byId('selectedItemId').value,
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