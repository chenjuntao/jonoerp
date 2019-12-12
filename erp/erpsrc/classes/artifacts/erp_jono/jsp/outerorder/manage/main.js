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
	var _url = appRoot + "/restaurant/goodsbill/query/doQuery.action?formType=outer";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable",
			"dojo/store/Cache", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/extensions/Pagination","dgrid/ColumnSet" , "dgrid/extensions/ColumnResizer","dojo/domReady!" ], function(Grid,
			Server, Observable, Cache, Memory, declare, Pagination,ColumnSet,ColumnResizer) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination,ColumnSet,ColumnResizer ]);

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
	return  [
	 		[ [ {
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
		label : '订货单编号',
		field : 'formId',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt(object.formId, doScan, object);
		},
		sortable:false
	},{
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt("修改/删除/作废", doEdit, object);
		},
		sortable:false
	} ] ], [ [ {
		label : '单据状态',
		field : 'formStatus',
		sortable:false
	}, {
		label : '订货部门',
		field : 'buyerName',
		sortable:false
	}, {
		label : '制单人',
		field : 'formMaker',
		sortable:false
	}, {
		label : '制单日期',
		field : 'formTime',
		sortable:false
	}, {
		label : '到货日期',
		field : 'receiveTime',
		sortable:false
	}, {
		label : '备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '主要订货',
		field : 'maxPayItem',
		sortable:false
	}, {
		label : '订货总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable:false
	} ] ] ];
}

function doScan(row) {
	var _url = appRoot + "/outerorder/manage/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	addTab("查看订货单-"+row.formId, _url);
}

function checkStatus(_formId) {
	var hasLock = false;
	var status = '';
	var _url = appRoot
			+ "/restaurant/goodsbill/query/getCurrentStatus.action?formId="
			+ _formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			hasLock = data.hasLock;
			status = data.formStatus;
		}, function(err) {
		});
	});
	if (hasLock) {
		alert("单据正在编辑或审核中！")
		return false;
	}

	if (status == '已作废') {
		alert("单据已作废！")
		return false;
	} else if (status == '已审核') {
		alert("单据已审核！")
		return false;
	}
	return true;
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/outerorder/manage/editView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("管理订货单-" + row.formId, _url);
}