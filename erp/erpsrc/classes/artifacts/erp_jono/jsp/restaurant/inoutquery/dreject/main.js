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
	var _url = appRoot + "/restaurant/dreject/manage/doQuery.action?branchType="+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare","dgrid/extensions/Pagination",
			"dojo/domReady!" ], function(Grid, Server, Observable,
			Cache, Memory,declare,Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([Grid,Pagination]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : '退货单号',
		field : 'formId'
	}, {
		label : '退货部门',
		field : 'returnBranch'
	}, {
		label : '退货人员',
		field : 'returner'
	}, {
		label : '退货日期',
		field : 'returnTime'
	}, {
		label : '退货备注',
		field : 'formNote'
	}, {
		label : '配送部门',
		field : 'provider'
	}, {
		label : '配送日期',
		field : 'receiveTime'
	}, {
		label : '订货部门',
		field : 'requester'
	}, {
		label : '入库人员',
		field : 'inputer'
	}, {
		label : '入库日期',
		field : 'inputTime'
	}, {
		label : '配送单备注',
		field : 'snote'
	}, {
		label : '主要配送品',
		field : 'maxPayItem'
	}, {
		label : '单据状态',
		field : 'formStatus'
	}, {
		label : '查看',
		field : 'scan',
		renderCell : function(object, data) {
			return hrefFmt("查看", doScan, object);// hrefFmt(_text, _handler,
			// _data)
		}
	},{
		label : "",
		field : "none"
	} ];
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

