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
	var _url = appRoot + "/restaurant/dreject/manage/doQuery.action";
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/Grid",
	          "custom/store/Server",
	          "dojo/store/Observable",
	          "dojo/store/Cache", 
	          "dojo/store/Memory",
	          "dojo/_base/declare",
	          "dgrid/extensions/Pagination",
	          "dgrid/extensions/ColumnResizer" ,
	          "dgrid/ColumnSet",
	          "dojo/domReady!"
          ], function(Grid, Server, Observable,	Cache, Memory,declare,Pagination,ColumnResizer,ColumnSet) {
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

		var CustomGrid = declare([Grid,Pagination,ColumnResizer,ColumnSet]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [
			[ [
				 {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '退货单号',
		field : 'formId',
		className:'text-center',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doScan, object);
		},
		sortable:false
	}, {
		label : '操作',
		field : 'operate',
		className:'text-center',
		renderCell : function(object, data) {
			return hrefFmt("修改/删除/作废", doEdit, object);
		},
		sortable:false
	}] ], [ [ {
		label : '单据状态',
		field : 'status',
		className:'text-center',
		sortable:false
	}, {
		label : '退货人员',
		field : 'returner',
		className:'text-center',
		sortable:false
	}, {
		label : '退货日期',
		field : 'returnTime',
		className:'text-center',
		sortable:false
	}, {
		label : '退货备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '配送部门',
		field : 'provider',
		sortable:false
	}, {
		label : '配送日期',
		field : 'receiveTime',
		className:'text-center',
		sortable:false
	}, {
		label : '订货部门',
		field : 'requester',
		sortable:false
	}, {
		label : '入库人员',
		field : 'inputer',
		className:'text-center',
		sortable:false
	}, {
		label : '入库日期',
		field : 'inputTime',
		className:'text-center',
		sortable:false
	}, {
		label : '配送单备注',
		field : 'snote',
		sortable:false
	}, {
		label : '主要配送品',
		field : 'maxPayItem',
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ] ] ];
}

function doScan(row) {
	var data = getCurrentStatus(row.formId);
	if (data.formStatus == '已删除') {
		alert("单据已删除！")
		return false;
	}
	var _url = appRoot + "/restaurant/dreject/manage/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	var _title = '配送退货单查看' + row.formId;
	addTab(_title, _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑或审核中！")
		return false;
	}
	if (status == '已删除') {
		alert("单据已删除！");
		return false;
	} else if (status == '已作废') {
		alert("单据已作废！");
		return false;
	} else if (status == '已审核') {
		alert("单据已审核！");
		return false;
	} 
	
	return true;
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/dreject/manage/editView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '修改配送退货单-' + row.formId;
	addTab(_title, _url);
}