dojo.ready(function() {
	initGrid();
});

var grid = null;

function initGrid() {
	var _url = appRoot + "/hq/acscan/queryInBySForm.action?formId="+formId;
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/OnDemandGrid", 
	          "custom/store/Server", 
	          "dojo/store/Observable",
	          "dojo/store/Cache", 
	          "dojo/store/Memory", 
	          "dojo/_base/declare",
	          "dojo/parser",
	          "dojo/domReady!" 
	        ], function(OnDemandGrid,Server, Observable, Cache, Memory, declare, parser) {
		parser.parse();
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '单号',
		field : 'subFormId',
		renderCell : function(item, data) {
			if(item.rownumber == "合计"){
				return hrefFmt("",doScan, item);
			}
			
			return hrefFmt(item.subFormId,doScan, item);
		}
	}, {
		label : '关联单号',
		field : 'formRefId',
		renderCell : function(item, data) {
			if(item.rownumber == "合计"){
				return hrefFmt("",doRelInScan, item);
			}
			
			return hrefFmt(item.formRefId,doRelInScan, item);
		}
	}, {
		label : '操作人员',
		field : 'formOperate'
	}, {
		label : '操作日期',
		field : 'formOperateTime'
	}, {
		label : '入库总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	}, {
		label : '确认状态',
		field : 'status',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			
			if(field == '供应商已确认'){
				return "已确认";
			}else{
				return "未确认";
			}
		}
	}, {
		label : '备注',
		field : 'formNote'
	}];
}

function doScan(row) {
	doDetailScan(row.subFormId);
}

function doRelInScan(row) {
	doDetailScan(row.formRefId);
}

function doExport(){
	var tmp = grid.get('store').getData();
	var str = "";
	for (var i = 0 ; i < tmp.length ; i++){
		var obj = tmp[i];
		str += obj.subFormId;
		if (i < tmp.length-1)
			str+=",";
	}
	var _url = appRoot + "/sp/statement/manage/queryExport.action?formList="+str;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				provider : dojo.byId('provider').value,
				branchName : dojo.byId('branchName').value
			}
		}).then(function(data) {
			fillData(cols, data);
		}, function(err) {
			errorHandler(err);
		});
	});
}

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport(){
//	console.dir(grid.get('store').getData());
	var tmp = grid.get('store').getData();
	var str = "";
	for (var i = 0 ; i < tmp.length ; i++){
		var obj = tmp[i];
		str += obj.subFormId;
		if (i < tmp.length-1)
			str+=",";
	}
	var _url = appRoot + "/sp/statement/manage/queryExport.action?formList="+str;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				provider : dojo.byId('provider').value,
				branchName : dojo.byId('branchName').value
			}
		}).then(function(data) {
			fillData(data);
		}, function(err) {
			errorHandler(err);
		});
	});
	//fillData(grid.get('store').getData());
}


//function customExport(){
//	dijit.byId('customDialog').show();
////	var tmp = grid.get('store').getData();
////	var str = "";
////	for (var i = 0 ; i < tmp.length ; i++){
////		var obj = tmp[i];
////		str += obj.subFormId;
////		if (i < tmp.length-1)
////			str+=",";
////	}
////	var _url = appRoot + "/hq/acscan/queryExport.action?formList="+str;
//_url = getUrl(_url);
////	require([ "dojo/request/xhr" ], function(xhr) {
////		xhr.post(_url, {
////			handleAs : "json",
////			data : {
////			}
////		}).then(function(data) {
////			fillData(cols, data);
////		}, function(err) {
////			errorHandler(err);
////		});
////	});
//
//}
//
//function showDialog() {
//	dijit.byId('customDialog').show();
////	var tmp = grid.get('store').getData();
////	var str = "";
////	for (var i = 0 ; i < tmp.length ; i++){
////		var obj = tmp[i];
////		str += obj.subFormId;
////		if (i < tmp.length-1)
////			str+=",";
////	}
////	console.log(str);
////	var _url = appRoot + "/hq/acscan/queryExport.action?formList="+str;
//_url = getUrl(_url);
////	require([ "dojo/request/xhr" ], function(xhr) {
////		xhr.post(_url, {
////			handleAs : "json",
////			data : {
////			}
////		}).then(function(data) {
////			fillData(cols, data);
////		}, function(err) {
////			errorHandler(err);
////		});
////	});
//}
//
//function hideDialog() {
//	dijit.byId('customDialog').hide();
//}

