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
	var _url = appRoot + "/restaurant/preject/manage/doQuery.action?queryType=confirm";
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
		label : '退货单号',
		field : 'formId'
	}, {
		label : '供应商',
		field : 'provider'
	}, {
		label : '入库单编号',
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
		label : '退货人员',
		field : 'returner'
	}, {
		label : '退货日期',
		field : 'returnTime'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '主要退货',
		field : 'maxPayItem'
	}, {
		label : '退货总额',
		field : 'allPayAmt',
		className : 'grid-number'
	}, {
		label : '单据状态',
		field : 'formStatus'
	}, {
		label : '确认采购退货单',
		field : 'operate',
		renderCell : function(object, data) {
			if(object.rownumber == "合计"){
				return "";
			}
			return hrefFmt("确认", doProcess, object);
		}
	}, {
		label : "",
		field : "none"
	} ];
}


function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已确认') {
		alert("采购退货单已确认！")
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
	var _url = appRoot + "/restaurant/preject/confirm/confirmView.action?formId="
			+ row.formId
			+ "&parentTabId=" + tabId
			+ "&branchType=" +  dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	var _title = '采购退货单确认' + row.formId;
	addTab(_title, _url);
}