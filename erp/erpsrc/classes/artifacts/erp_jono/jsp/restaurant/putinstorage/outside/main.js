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
		branchId : dojo.byId('branchId').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/putinstorage/outside/doQuery.action?branchType=RESTAURANT";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", 
	          "dojo/store/Memory","dojo/_base/declare", "dgrid/extensions/Pagination",
	          "dgrid/extensions/ColumnResizer" ,"dgrid/ColumnSet", "dojo/domReady!" ]
	, function(Grid, Server, Observable,Cache, Memory, declare, Pagination,ColumnResizer,ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty:'rownumber',
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination ,ColumnResizer,ColumnSet]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [
			[ [{
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
		label : '入库单号',
		field : 'formId',
		sortable:false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt("修改/删除/作废", doEdit, object);
		},
		sortable:false
	}] ], [ [  {
		label : '单据状态',
		field : 'status',
		sortable:false
	}, {
		label : '供应商',
		field : 'provider',
		sortable:false
	}, {
		label : '采购单号',
		field : 'formRefId',
		sortable:false,
		renderCell : function(object, data) {
			if(object.rownumber == '合计'){
				return hrefFmt("", doRefFormScan, object);
			}
			return hrefFmt(object.formRefId, doRefFormScan, object);
		}
	}, {
		label : '入库部门',
		field : 'inputDepartment',
		sortable:false
	}, {
		label : '入库人员',
		field : 'inputer',
		sortable:false
	}, {
		label : '入库日期',
		field : 'inputTime',
		sortable:false
	}, {
		label : '备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '主要入库品',
		field : 'maxPayItem',
		sortable:false
	}, {
		label : '入库总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}  ] ] ];
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if(isInOperation(data.operationContent)){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	
	return true;
}

function doFormScan(row) {
	var data = getCurrentVersion(row.formId);
	if(data.operationContent == "已删除"){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return;
	}
	
	doDetailScan(row.formId);
}

function doRefFormScan(row) {
	doDetailScan(row.formRefId);
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/putinstorage/outside/editView.action?formId=" + row.formId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	var _title = '入库单修改' + row.formId;
	addTab(_title, _url);
}