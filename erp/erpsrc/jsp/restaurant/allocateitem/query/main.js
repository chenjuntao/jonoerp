dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function refreshStorage1(branchId,storageId){
	var _url = appRoot + '/restaurant/reportdamage/queryloss/refreshStorage.action?branchType='+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr"], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {branchId:dojo.byId(branchId).value}
		}).then(function(data) {
			if (data.msg) {
				var storageSelector = dojo.byId(storageId);
				storageSelector.length = 0;
				storageSelector.options.add(new Option("--请选择--","")); 
				for ( var i=0,length =data.msg.length; i< length;i++ ) {
					var item = data.msg[i];
					storageSelector.options.add(new Option(item.storageName,item.storageId)); 
				}
			} else {
				// do something
			}
		}, function(err) {
		});
	});
} 

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchType : dojo.byId('branchType').value,
		inBranchId : dojo.byId('inBranchId').value,
		inStorageId : dojo.byId('inStorageId').value,
		outBranchId : dojo.byId('outBranchId').value,
		outStorageId : dojo.byId('outStorageId').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/allocateitem/query/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/ColumnSet","dojo/_base/declare","dgrid/extensions/Pagination","dgrid/extensions/ColumnResizer",
			"dojo/domReady!" ], function(Grid, Server, Observable,
			Cache, Memory,ColumnSet,declare,Pagination,ColumnResizer) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : "rownumber",
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([Grid, ColumnSet,Pagination,ColumnResizer]);  
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			rowsPerPage:15,
			pageSizeOptions : [ 10,15,20],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doFormScan(row) {
	var data = getCurrentVersion(row.formId);
	if(data.operationContent == "已删除"){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return;
	}
	
	doDetailScan(row.formId);
}

function getColumn() {
	return [[[ {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		},
		sortable:false
	}, {
		label : '调拨单编号',
		field : 'formId',
		sortable:false,
		renderCell : function(object, data) {
			if(object.rownumber == '合计'){
				return hrefFmt("", doFormScan, object);
			}
			return hrefFmt(object.formId, doFormScan, object);
		}
	},{
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt("修改/删除/作废", doScan, object);
		},
		sortable:false
	}]], [[{
		label : '单据状态',
		field : 'formStatus',
		sortable:false
	},  {
		label : '调入部门',
		field : 'inBranch',
		sortable:false
	},  {
		label : '调入仓库',
		field : 'inStorage',
		sortable:false
	}, {
		label : '调出部门',
		field : 'outBranch',
		sortable:false
	},  {
		label : '调出仓库',
		field : 'outStorage',
		sortable:false
	}, {
		label : '制单人员',
		field : 'fromMaker',
		sortable:false
	}, {
		label : '制单日期',
		field : 'formTime',
		sortable:false
	}, {
		label : '操作时间',
		field : 'formTimeActual',
		sortable:false
	}
	, {
		label : '确认人员',
		field : 'confirmer',
		sortable:false
	}, {
		label : '审核日期',
		field : 'confirmTime',
		sortable:false
	}
	,  {
		label : '备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '主要调拨品',
		field : 'maxPayItem',
		sortable:false
	}, {
		label : '调拨总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable:false
	}, {
		label : '',
		field : 'none',
		sortable:false
	} ]]];
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if(isInOperation(data.operationContent)){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	
	return true;
}

function doScan(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	
	var _url = appRoot + "/restaurant/allocateitem/query/editView.action?formId="
	+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("管理调拨单-"+row.formId , _url);
}
