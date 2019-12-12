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
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value
	}
}

var busyButton = null;

var grid = null;
function initGrid() {
	var _url = appRoot + "/lc/request/distribution/doQuery.action";
	_url = getUrl(_url);

	require([ "dojo/_base/declare", "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory", "dgrid/extensions/ColumnResizer",
			'dojox/form/BusyButton', "dijit/registry", "dgrid/ColumnSet" ], function(declare, OnDemandGrid, selector,
			Selection, Server, Observable, Cache, Memory, ColumnResizer, BusyButton, registry, ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			},
			idProperty : 'formId'
		}), Memory()));

		busyButton = BusyButton({
			id : "submit",
			busyLabel : "正在提交数据...",
			label : "批量生成",
			timeout : 1000 * 120
		}, "placeHolder");

		dojo.connect(registry.byId("submit"), "_onClick", function() {
			batchCreate();
		});

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(selector),
			selectionMode : "toggle",
			allowSelectAll : true,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doFormScan(row) {
	doDetailScan(row.formId);
}

function getColumn(selector) {
	return [ [ [ selector({
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '要货单编号',
		field : 'formId',
		className : 'text-center',
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		className : 'text-center',
		renderCell : function(object, data) {
			return hrefFmtId("生成配送单", doCreate, object, "operate" + object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : '模板',
		field : 'templateName',
		sortable : false
	}, {
		label : '要货部门',
		field : 'buyerName',
		sortable : false
	}, {
		label : '制单人',
		field : 'formMaker',
		className : 'text-center',
		sortable : false
	}, {
		label : '制单日期',
		field : 'formTime',
		className : 'text-center',
		sortable : false
	}, {
		label : '审核日期',
		field : 'auditTime',
		className : 'text-center',
		sortable : false
	}, {
		label : '到货日期',
		field : 'receiveTime',
		className : 'text-center',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '主要要货',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '要货总额',
		field : 'allPayAmt',
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}

function exist(_businessDate, _branchId, _branch) {
	var _url = appRoot + "/lc/request/distribution/exist.action?businessDate=" + _businessDate + "&branchId="
			+ _branchId;
	_url = getUrl(_url);

	var result = {};
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			result = data;
		}, function(err) {
		});
	});

	if (result.exist == true) {
		alert("门店" + _branch + "营业日期" + _businessDate + "已经生成过配送单！");
		return true;
	}
	return false;
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	return true;
}

function doCreate(row) {
	if (exist(row.formTime, row.buyerId, row.buyerName)) {
	}
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/lc/request/distribution/createView.action?formRefId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addTab("生成配送单-" + row.formId, _url);
}

function batchCreate() {
	var selectArr = [];
	for ( var formId in grid.selection) {
		if (grid.selection[formId]) {
			selectArr.push(formId);
		}
	}
	if (selectArr.length == 0) {
		busyButton.cancel();
		alert("请选择记录！");
		return;
	}

	dojo.byId('ids').value = selectArr.join();
	var _url = appRoot + "/lc/request/distribution/batchCreate.action"
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			var sidLst = data.sidLst;
			alert("生成单据号为：" + sidLst + "，操作成功！");
			doQuery();
			busyButton.cancel();
		}, function(err) {
			alert("操作失败！");
			doQuery();
			busyButton.cancel();
		});
	});

}