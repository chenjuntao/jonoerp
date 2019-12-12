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
	var _url = appRoot + "/restaurant/putinstorage/outside/doQuery.action";
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
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '入库单号',
		field : 'formId'
	}, {
		label : '供应商',
		field : 'provider'
	}, {
		label : '采购单号',
		field : 'formRefId'
	}, {
		label : '入库部门',
		field : 'inputDepartment'
	}, {
		label : '入库人员',
		field : 'inputer'
	}, {
		label : '入库日期',
		field : 'inputTime'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '主要入库品',
		field : 'maxPayItem'
	}, {
		label : '入库总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	}, {
		label : '单据状态',
		field : 'formStatus'
	}, {
		label : '查看',
		field : 'scan',
		renderCell : function(object, data) {
			if(object.rownumber == "合计"){
				return "";
			}
			
			return hrefFmt("查看", doScan, object);// hrefFmt(_text, _handler,
			// _data)
		}
	}, {
		label : "",
		field : "none"
	} ];
}

function doScan(row) {
	var _url = appRoot + "/restaurant/putinstorage/outside/scanView.action?formId="
			+ row.formId;
	
	_url = getUrl(_url);
	var _title = '入库单查看' + row.formId;
	addTab(_title, _url);
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
	var _url = appRoot + "/restaurant/putinstorage/outside/editView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '入库单修改' + row.formId;
	addTab(_title, _url);
}