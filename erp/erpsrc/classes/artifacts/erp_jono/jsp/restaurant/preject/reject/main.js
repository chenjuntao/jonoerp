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
		branchId : dojo.byId('branchId').value,
		branchType : dojo.byId('branchType').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/preject/manage/doQuery.action?queryType=audit";
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/ColumnSet", "dojo/_base/declare", "dojo/domReady!" ], function(OnDemandGrid,
			Server, Observable, Cache, Memory, ColumnSet, declare) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : 'rownumber',
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, ColumnSet ]);
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
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '退货单号',
		field : 'formId',
		className : 'text-center',
		sortable : false
	}, {
		label : '操作',
		field : 'operate',
		className : 'text-center',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmtId("审核", doProcess, object, "operate" + object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : '供应商',
		field : 'provider',
		sortable : false
	}, {
		label : '入库单编号',
		field : 'formRefId',
		className : 'text-center',
		sortable : false
	}, {
		label : '入库部门',
		field : 'inputDepartment',
		sortable : false
	}, {
		label : '仓库',
		field : 'storage',
		sortable : false
	}, {
		label : '入库人员',
		field : 'inputer',
		className : 'text-center',
		sortable : false
	}, {
		label : '入库日期',
		field : 'inputTime',
		className : 'text-center',
		sortable : false
	}, {
		label : '退货人员',
		field : 'returner',
		className : 'text-center',
		sortable : false
	}, {
		label : '退货日期',
		field : 'returnTime',
		className : 'text-center',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '主要退货',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '退货总额',
		field : 'allPayAmt',
		className : 'text-right',
		sortable : false
	}, {
		label : '单据状态',
		field : 'formStatus',
		className : 'text-center',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已处理') {
		alert("采购退货单已处理！")
		return false;
	}
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
	var _url = appRoot + "/restaurant/preject/reject/rejectView.action?formId=" + row.formId + "&parentTabId=" + tabId
			+ "&branchType=" + dojo.byId('branchType').value;
	_url = getUrl(_url);

	var _title = '审核采购退货单-' + row.formId;
	addTab(_title, _url);
}