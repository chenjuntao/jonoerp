var branchType ;

dojo.ready(function() {
	branchType= dojo.byId('branchType').value;
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		//branchId : dojo.byId('branchId').value,
//		storageId : dojo.byId('storageId').value,
		itemName : dojo.byId('itemName').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/inoutquery/shipping/doReturn.action?queryType=return&formType=OUTER&branchType="+branchType;
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server","dgrid/extensions/Pagination",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory","dgrid/extensions/ColumnResizer",
			"dojo/_base/declare","dgrid/ColumnSet","dojo/domReady!" ], function(Grid, Server,Pagination, Observable,
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

		var CustomGrid = declare([ Grid,Pagination, ColumnResizer ,ColumnSet]);
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

function doInScan(row) {
	doDetailScan(row.formId)
}

function doRefScan(row) {
	doDetailScan(row.formRefId)
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
				return hrefFmt(object.formId, doInScan, object);
			}
	},{
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt("出货单退货", doEdit, object);
		},
		sortable:false
	} ] ], [ [ {
		label : '单据状态',
		field : 'formStatus',
		sortable:false
	}, {
		label : '出货部门',
		field : 'provider',
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
	}, {
		label : "",
		field : "none",
		sortable:false
	}] ] ];
}

function doEdit(row) {
	var _url = appRoot + "/lc/request/delivery/manage/editOutView.action?formId="
			+ row.formId + "&parentTabId=" + tabId +"&branchType=" + branchType;
	
	_url = getUrl(_url);
	var _title = '出货单退货-' + row.formId;
	addTab(_title, _url);
}