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

function changeStorage() {
	refreshStorage({
		query : {
			branchType : dojo.byId("branchType").value,
			branchId : dojo.byId("branchId").value
		},
		storageId : "storageId"
	});
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/antiaudit/manage/doQuery.action?queryType=unaudit&branchType="
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/extensions/ColumnResizer","dojo/_base/declare","dgrid/ColumnSet" ,"dojo/domReady!" ]
			, function(OnDemandGrid, Server, Observable, Cache, Memory,ColumnResizer,declare,ColumnSet) {
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

		var CustomGrid = declare([ OnDemandGrid, ColumnResizer,ColumnSet ]);
		
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doScan(row) {
	doDetailScan(row.formRefId)
}
		

function getColumn() {
	return [
			[ [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '配送单号',
		field : 'formRefId',
		sortable:false,
		renderCell : function(object, data) {
			return hrefFmt(object.formRefId, doScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmtId("反审核处理", doAudit, object, "operate"+object.rownumber);
		},
		sortable:false
	}] ], [ [  {
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
		label : '配送日期',
		field : 'receiveTime',
		sortable:false
	}, {
		label : '订货部门',
		field : 'requester',
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
	}, {
		label : '单据状态',
		field : 'formStatus',
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}] ] ];
}

function checkStatus(_formRefId) {
	var data = getCurrentStatus(_formRefId);
	var status = data.formStatus;
	if (status == '已反审核') {
		alert("配送反审核已完成！")
		return false;
	}
	if (data.hasLock) {
		alert("单据正在编辑或反审核中！")
		return false;
	}
	return true;
}

function doAudit(row) {
	if (!checkStatus(row.formRefId)) {
		return;
	}
	var _url = appRoot + "/restaurant/antiaudit/audit/auditView.action?formRefId=" + row.formRefId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	var _title = '配送单反审核-' + row.formRefId;
	addTab(_title, _url);
}