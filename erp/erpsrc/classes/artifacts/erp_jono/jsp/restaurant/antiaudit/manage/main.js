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
		storageId : dojo.byId('storageId').value,
		branchId : dojo.byId('branchId').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/antiaudit/manage/doQuery.action?queryType=self&branchType="+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require([ "dojo/_base/declare", "dgrid/Grid", "dgrid/Selection", "custom/store/Server","dgrid/extensions/Pagination",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory" ,
			"dgrid/extensions/ColumnResizer","dgrid/ColumnSet" ], function(declare, Grid,
			Selection, Server,Pagination, Observable, Cache, Memory,ColumnResizer,ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination,Selection ,ColumnResizer,ColumnSet]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doScan(row) {
	doDetailScan(row.formRefId)
}

function getColumn() {
	return  [
	 		[ [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '配送单号',
		field : 'formRefId',
		renderCell : function(object, data) {
			return hrefFmt(object.formRefId, doScan, object);
		},
		sortable:false
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if(object.branchType!=dojo.byId("branchType").value){
				return hrefFmt("查看", doScan, object);
			}else{
				return hrefFmt("修改", doEdit, object);
			}
		},
		sortable:false
	}] ], [ [  {
		label : '反审核状态',
		field : 'antiStatus',
		sortable:false
	}, {
		label : '反审核部门',
		field : 'antiauditBranch',
		sortable:false
	}, {
		label : '反审核人员',
		field : 'antiauditor',
		sortable:false
	}, {
		label : '反审核日期',
		field : 'antiauditTime',
		sortable:false
	}, {
		label : '反审核备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '配送部门',
		field : 'provider',
		sortable:false
	}, {
		label : '出库仓库',
		field : 'outStorage',
		sortable:false
	}, {
		label : '配送日期',
		field : 'receiveTime',
		sortable:false
	}, {
		label : '订货部门',
		field : 'requester',
		sortable:false
	}, {
		label : '入库仓库',
		field : 'inStorage',
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
		label : '配送单备注',
		field : 'snote',
		sortable:false
	}, {
		label : '主要配送品',
		field : 'maxPayItem',
		sortable:false
	}] ] ];
}

function changeStorage(){
	refreshStorage({
		query : {branchType : dojo.byId("branchType").value, 
			branchId:dojo.byId("branchId").value},
		storageId :"storageId"
	});
}

function checkStatus(_formRefId) {
	var data = getCurrentStatus(_formRefId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑或审核中！")
		return false;
	}

	return true;
}

function doScan(row) {
	var data = getCurrentStatus(row.formId);
	if (data.formStatus == '已删除') {
		alert("单据已删除！")
		return false;
	}
	var _url = appRoot
			+ "/restaurant/antiaudit/manage/scanView.action?formRefId=" + row.formRefId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	var _title = '查看配送反审核-' + row.formRefId;
	addTab(_title, _url);
}

function doEdit(row) {
	if (!checkStatus(row.formRefId)) {
		return;
	}
	
	if (row.antiStatus == '已反审核') {
		alert("配送反审核已完成！")
		return;
	}
	var _url = appRoot + "/restaurant/antiaudit/manage/editView.action?formRefId=" + row.formRefId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	var _title = '修改配送反审核-' + row.formRefId;
	addTab(_title, _url);
}