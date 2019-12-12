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
	var _url = appRoot + "/restaurant/dreject/manage/doQuery.action?queryType=audit";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/extensions/ColumnResizer","dojo/_base/declare","dgrid/ColumnSet","dojo/domReady!" ], function(OnDemandGrid, Server, Observable,
			Cache, Memory,ColumnResizer,declare,ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
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

function getColumn() {
	return  [[ [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '退货单号',
		className:'text-center',
		field : 'formId',
		sortable:false
	}, {
		label : '操作',
		field : 'operate',
		className:'text-center',
		renderCell : function(object, data) {
			return hrefFmtId("进行退货", doProcess, object, "operate"+object.rownumber);
		},
		sortable:false
	}] ], [ [ {
		label : '退货人员',
		field : 'returner',
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
		label : '单据状态',
		field : 'status',
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
	if (data.hasLock) {
		alert("单据正在编辑或处理中！")
		return false;
	}
	return true;
}

function doProcess(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/dreject/reject/rejectView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '配送退货单处理-' + row.formId;
	addTab(_title, _url);
}