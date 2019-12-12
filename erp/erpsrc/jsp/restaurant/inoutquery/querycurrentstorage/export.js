function exportXls(_type) {
	if(!storage_grid.store){
		alert("请先查询库存再导出！");
		return;
	}
	
	var sheetName = '当前库存查询';
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
			display : '编码',
			name : 'itemId',
			align : 'center',
			width : 100
		},{
			display : '名称',
			name : 'itemName',
			align : 'center',
			width : 250
		},{
			display : '简称',
			name : 'itemNameEng',
			align : 'center',
			width : 250
		},{
			display : '助记码',
			name : 'queryCode',
			align : 'center',
			width : 100
		},{
			display : '单位',
			name : 'itemDimension',
			align : 'center',
			width : 80
		},{
			display : '供应商',
			name : 'supplier',
			align : 'center',
			width : 250
		} ,{
			display : '配送价',
			name : 'itemUnitPrice',
			align : 'center',
			width : 100
		},{
			display : '库存量',
			name : 'inventory',
			align : 'inventory',
			width : 100
		}]
	};
	
	
	var _url = appRoot + "/bq/inoutquery/querycurrentstorage/export/export.action";
	_url = getUrl(_url);
	
	var _params = {
		jsonData : JSON.stringify(data),
		exportContent :JSON.stringify({data:storage_grid.store.data}) 
	};
	if(_type!=undefined){
		_params.type = _type;
	}
	post_redirect(_url, _params);
}

function exportCsv() {
	exportXls("csv");
}