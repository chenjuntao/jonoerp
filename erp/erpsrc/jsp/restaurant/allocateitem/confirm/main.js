dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchType : dojo.byId('branchType').value,
		outStorageId : dojo.byId('outStorageId').value,
		outBranchId : dojo.byId('outBranchId').value
	}
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

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/allocateitem/confirm/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory","dgrid/ColumnSet","dojo/_base/declare",
			"dgrid/extensions/ColumnResizer","dojo/domReady!" ], function(OnDemandGrid, Server, Observable,
			Cache, Memory,ColumnSet,declare,ColumnResizer) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([OnDemandGrid, ColumnSet,ColumnResizer]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
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

function getColumn(editor) {
	return [[[ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '单据编号',
		field : 'formId',
		sortable:false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}
	},{
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmtId("审核", doConfirm, object, "operate"+object.rownumber);
		},
		sortable:false
	}]], [[{
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
	}
	, {
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
		sortable:false
	}, {
		label : '单据状态',
		field : 'formStatus',
		sortable:false
	}, {
		label : '',
		field : 'none',
		sortable:false
	}]]];
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if(isInOperation(data.operationContent)){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	
	return true;
}

function doConfirm(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot
			+ "/restaurant/allocateitem/confirm/confirmView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("审核调拨单-"+row.formId, _url);
}