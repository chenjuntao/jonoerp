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

function changeStorage(){
	refreshStorage({
		query : {branchType : dojo.byId("branchType").value, 
			branchId:dojo.byId("branchId").value},
		storageId :"storageId"
	});
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/antiaudit/create/doQuery.action?branchType="+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server","dgrid/extensions/Pagination",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory","dgrid/extensions/ColumnResizer" ,
			"dojo/_base/declare","dgrid/ColumnSet" ,"dojo/domReady!" ], function(Grid, Server,Pagination, Observable,
			Cache, Memory,ColumnResizer,declare,ColumnSet) {
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

		var CustomGrid = declare([Grid ,Pagination,ColumnResizer,ColumnSet]);
		
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

function getColumn() {
	return [
			[ [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '单据编号',
		field : 'formId',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		},
		sortable:false,
		className:'text-center'
	}, {
		label : '反审核',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmtId("反审核", doEdit, object, "operate"+object.rownumber);
		},
		sortable:false,
		className:'text-center'
	}] ], [ [  {
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
		sortable:false,
		className:'text-center'
	}, {
		label : '订货部门',
		field : 'requester',
		sortable:false,
		className:'text-center'
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
		sortable:false,
		className:'text-center'
	}, {
		label : '备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '主要配送品',
		field : 'maxPayItem',
		sortable:false
	}, {
		label : '配送总额',
		field : 'allPayAmt',
		sortable:false,
		className:'text-right'
	},{
		label : '',
		field : 'none',
		sortable:false
	}] ] ];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	var hasLock = data.hasLock;
	
	if (hasLock) {
		alert("单据正在生成中！")
		return false;
	}
	
	if (status == '反审核中') {
		alert("不能重复进行反审核！")
		return false;
	}
	
	return true;
}

function doFormScan(row) {
	doDetailScan(row.formId);
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/antiaudit/create/editView.action?formId="
			+ row.formId + "&parentTabId=" + tabId + "&branchType="+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	var _title = '配送单反审核-' + row.formId;
	addTab(_title, _url);
}