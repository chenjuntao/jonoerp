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
	var _url = appRoot
	+ "/restaurant/goodsbill/query/doQuery.action?formType=request&queryType=pcreate";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/domReady!" ], function(OnDemandGrid, Server, Observable,
			Cache, Memory) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		grid = new OnDemandGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(editor) {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : '要货单编号',
		field : 'formId'
	}, {
		label : '要货部门',
		field : 'buyerName'
	}, {
		label : '制单人',
		field : 'formMaker'
	}, {
		label : '制单日期',
		field : 'formTime'
	}, {
		label : '到货日期',
		field : 'receiveTime'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '主要要货',
		field : 'maxPayItem'
	}, {
		label : '要货总额',
		field : 'allPayAmt'
	}, {
		label : '单据状态',
		field : 'formStatus'
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt("生成采购单", doCreate, object);
		}
	}, {
		label : "",
		field : "none"
	} ];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已处理') {
		alert("配送反审核单已处理！")
		return false;
	}
	if (data.hasLock) {
		alert("单据正在编辑或处理中！")
		return false;
	}
	return true;
};

function doCreate(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/lc/request/addition/createView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("生成采购单" + row.formId, _url);
}