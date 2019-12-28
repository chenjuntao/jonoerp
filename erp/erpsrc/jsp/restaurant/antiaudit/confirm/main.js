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
	var _url = appRoot + "/restaurant/antiaudit/manage/doQuery.action?queryType=unaudited&branchType="+ dojo.byId('branchType').value;
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

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : '配送单号',
		field : 'formRefId'
	}, {
		label : '反审核部门',
		field : 'antiauditBranch'
	}, {
		label : '反审核人员',
		field : 'antiauditor'
	}, {
		label : '反审核日期',
		field : 'antiauditTime'
	}, {
		label : '反审核备注',
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
		label : '反审核确认',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("反审核确认", doConfirm, object);
		}
	}, {
		label : "",
		field : "none"
	} ];
}


function checkStatus(_formRefId) {
	var data = getCurrentStatus(_formRefId);
	var status = data.formStatus;
//	if (status == '已反审核') {
//		alert("配送反审核已完成！")
//		return false;
//	}
	if (data.hasLock) {
		alert("单据正在编辑或处理中！")
		return false;
	}
	return true;
}

function doConfirm(row) {
	if (!checkStatus(row.formRefId)) {
		return;
	}
	var _url = appRoot + "/restaurant/antiaudit/confirm/confirmView.action?formRefId="
			+ row.formRefId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '配送单反审核' + row.formRefId;
	addTab(_title, _url);
}