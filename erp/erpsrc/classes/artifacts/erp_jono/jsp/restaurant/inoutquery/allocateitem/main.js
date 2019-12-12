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
			"dgrid/ColumnSet","dojo/_base/declare","dgrid/extensions/Pagination",
			"dojo/domReady!" ], function(Grid, Server, Observable,
			Cache, Memory,ColumnSet,declare,Pagination) {
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

		var CustomGrid = declare([Grid, ColumnSet,Pagination]);  
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

function getColumn() {
	return [[[ {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '调拨单编号',
		field : 'formId'
	}]], [[ {
		label : '查看',
		field : 'scan',
		renderCell : function(object, data) {
			if(object.rownumber == "合计"){
				return "";
			}
			return hrefFmt("查看", doScan, object);// hrefFmt(_text, _handler,
			// _data)
		}
	}, {
		label : '单据状态',
		field : 'formStatus'
	},  {
		label : '调入部门',
		field : 'inBranch'
	},  {
		label : '调入仓库',
		field : 'inStorage'
	}, {
		label : '调出部门',
		field : 'outBranch'
	},  {
		label : '调出仓库',
		field : 'outStorage'
	}, {
		label : '制单人员',
		field : 'fromMaker'
	}, {
		label : '制单日期',
		field : 'formTime'
	}
	, {
		label : '确认人员',
		field : 'confirmer'
	}, {
		label : '确认日期',
		field : 'confirmTime'
	}
	,  {
		label : '备注',
		field : 'formNote'
	}, {
		label : '主要调拨品',
		field : 'maxPayItem'
	}, {
		label : '调拨总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	} ]]];
}

function doScan(row) {
	var data = getCurrentStatus(row.formId);
	if (data.formStatus == '已删除') {
		alert("单据已删除！")
		return false;
	}
	
	var result = getCurrentStatus(row.formId);
	if (result.hasLock)
	{
		alert("单据正在编辑或确认中！")
		return false;
	}
	
	var _url = appRoot + "/restaurant/allocateitem/query/scanView.action?formId="
			+ row.formId;
	var note = "调拨单查看";
	
	if(row.formStatus == "未确认"){
		 _url = appRoot + "/restaurant/allocateitem/query/editView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
		 
		 note = "调拨单管理";
	}
	
	_url = getUrl(_url);
	addTab(note, _url);
}
