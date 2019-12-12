require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value
	}
}

var grid = null;
var dataStore = null;
function initGrid() {
	var _url = appRoot + "/lc/request/purchase/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/domReady!" ], function(OnDemandGrid, Server, Observable,
			Cache, Memory) {
		dataStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		grid = new OnDemandGrid({
			store : dataStore,
			columns : getColumn(),
			cellNavigation : false,
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
		label : '汇总单编号',
		field : 'formId'
	}, {
		label : '制单人',
		field : 'formMaker'
	}, {
		label : '制单日期',
		field : 'formTime'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '主要订货',
		field : 'maxPayItem'
	}, {
		label : '订货总额',
		field : 'allPayAmt'
	}, {
		label : '单据状态',
		field : 'formStatus'
//	}, {
//		label : '查看',
//		field : 'scan',
//		renderCell : function(object, data) {
//			return hrefFmt("查看", doScan, object);
//		}
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("生成采购单", doCreate, object);
		}
	}, {
		label : "",
		field : "none"
	} ];
}

function doScan(row) {
	var _url = appRoot + "/lc/request/purchase/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	addTab("查看", _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑中！")
		return false;
	}
	if (status == '已下发') {
		alert("单据已下发！");
		return false;
	}
	return true;
}

function doCreate(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/lc/request/purchase/createView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("生成采购单" + row.formId, _url);
}