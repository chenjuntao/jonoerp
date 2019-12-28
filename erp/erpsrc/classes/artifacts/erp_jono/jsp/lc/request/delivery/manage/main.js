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
	var _url = appRoot + "/restaurant/inoutquery/shipping/doQuery.action?formType=OUTER";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/ColumnSet", "dojo/_base/declare", "dgrid/extensions/Pagination", "dojo/domReady!" ], function(Grid,
			Server, Observable, Cache, Memory, ColumnSet, declare, Pagination) {
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

		var CustomGrid = declare([ Grid, ColumnSet, Pagination ]);
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
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable:false,
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '单据编号',
		field : 'formId',
		sortable:false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt(object.formId, doScan, object);// hrefFmt(_text, _handler,
			// _data)
		}
	} ] ], [ [ {
		label : '单据状态',
		field : 'formStatus',
		sortable:false
	}, {
		label : '出货部门',
		field : 'providerId',
		sortable:false
	}, {
		label : '出货日期',
		field : 'receiveTime',
		sortable:false
	}, {
		label : '订货部门',
		field : 'requester',
		sortable:false
	}, {
		label : '订货地址',
		field : 'requestAddress',
		sortable:false
	}, {
		label : '制单人员',
		field : 'formMaker',
		sortable:false
	}, {
		label : '制单日期',
		field : 'formTime',
		sortable:false
	}, {
		label : '审核人员',
		field : 'auditor',
		sortable:false
	}, {
		label : '审核日期',
		field : 'auditTime',
		sortable:false
	}, {
		label : '备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '金额最大的商品',
		field : 'maxPayItem',
		sortable:false
	} ] ] ];
}

function doScan(row) {
	var _url = appRoot + "/lc/request/delivery/manage/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);
	
	addTab("查看出货单-"+row.formId, _url);
}
