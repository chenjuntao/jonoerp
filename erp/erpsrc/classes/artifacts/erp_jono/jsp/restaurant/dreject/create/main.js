dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		itemName : document.getElementById('itemName').value.trim(),
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value,
		itemName : dojo.byId("itemName").value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/antiaudit/create/doQuery.action?queryType=return";
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/Grid",
	          "custom/store/Server", 
	          "dojo/store/Observable", 
	          "dojo/store/Cache",
	          "dgrid/extensions/Pagination",
	          "dojo/store/Memory",
	          "dgrid/extensions/ColumnResizer" , 
	          "dojo/_base/declare",
	          "dgrid/ColumnSet" ,
	          "dojo/domReady!" 
          ],function(Grid, Server, Observable, Cache, Pagination,Memory,ColumnResizer,declare,ColumnSet) {
		
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

		var CustomGrid = declare([ Grid ,Pagination,ColumnResizer,ColumnSet]);
		
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

function doFormScan(row) {
	doDetailScan(row.formId);
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
		className:'text-center',
		sortable:false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		className:'text-center',
		renderCell : function(object, data) {
			return hrefFmtId("配送单退货", doEdit, object, "operate"+object.rownumber);
		},
		sortable:false
	}] ], [ [ {
		label : '配送部门',
		field : 'provider',
		sortable:false
	}, {
		label : '配送日期',
		className:'text-center',
		field : 'receiveTime',
		sortable:false
	}, {
		label : '订货部门',
		field : 'requester',
		sortable:false
	}, {
		label : '入库人员',
		className:'text-center',
		field : 'inputer',
		sortable:false
	}, {
		label : '入库日期',
		field : 'inputTime',
		className:'text-center',
		sortable:false
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
		className:'text-right',
		sortable:false
	}, {
		label : '单据状态',
		field : 'formStatus',
		className:'text-center',
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ] ] ];
}
function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	var hasLock = data.hasLock;

	if (hasLock) {
		alert("单据正在生成中！");
		return false;
	}

	if (status == '退货中') {
		alert("配送单正在退货中！");
		return false;
//	} else if (status == '已入库' || status == '已反审核') {
//		return true;
	}
//	return false;
	return true;
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	
	var _url = appRoot + "/restaurant/dreject/create/editView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	var _title = '配送单退货-' + row.formId;
	_url = getUrl(_url);
	
	addTab(_title, _url);
}