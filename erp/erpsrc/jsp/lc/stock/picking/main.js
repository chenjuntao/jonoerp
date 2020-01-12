require([ "dojo/dom-form", "dojo/domReady!" ], function(domForm) {

	window.getQuery = function() {
		var query = domForm.toObject("dataForm");
		return query;
	}

	initGrid();
});


function doQuery() {
	var businessDate = dojo.byId('businessDate').value;
	if (businessDate.trim() == '') {
		alert('请选择配送日期！');
		return;
	}
	
	grid.set('query', getQuery());
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/lc/stock/picking/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dgrid/selector", "dojo/_base/declare", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory","dgrid/extensions/ColumnResizer",
			"dgrid/ColumnSet","dojo/domReady!" ], function(
			OnDemandGrid, Selection, selector, declare, Server, Observable, Cache, Memory,ColumnResizer,ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : 'formId',
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer,ColumnSet]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(selector),
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on("dgrid-refresh-complete", function(evt) {
			grid.selectAll();
		});
		
		grid.startup();
	});
}

function getColumn(selector) {
	return [
			[ [selector({
		label : "",
		field : 'rownumber',
		sortable:false
	}), {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '单据编号',
		field : 'formId',
		renderCell : function(object, data) {
			return hrefFmt(data, doScan, object);
		},
		sortable:false
	} ] ], [ [  {
		label : '是否加单',
		field : 'isAddForm',
		sortable:false
	},{
		label : '订货部门',
		field : 'requester',
		sortable:false
	}, {
		label : '订货地址',
		field : 'requestAddress',
		sortable:false
	}, {
		label : '配送部门',
		field : 'provider',
		sortable:false
	}, {
		label : '配送日期',
		field : 'receiveTime',
		sortable:false
	}, {
		label : '备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '主要配送品',
		field : 'maxPayItem',
		sortable:false
	}, {
		label : '配送总额',
		field : 'allPayAmt',
		sortable:false
	}, {
		label : '单据状态',
		field : 'formStatus',
		sortable:false
	} ] ] ];
}

function doScan(row) {
	var _url = appRoot + "/restaurant/inoutquery/shipping/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	addTab("配送单查看", _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已入库') {
		alert("配送单已入库！")
		return false;
	}
	return true;
}

function doAggregate() {
	var selectArr = [];
	for ( var formId in this.grid.selection) {
		if (this.grid.selection[formId]) {
			selectArr.push(formId);
		}
	}
	
	if (selectArr.length <= 0) {
		alert("数据为空！");
		return;
	}
	var _url = appRoot + '/lc/stock/picking/aggregateView.action?parentTabId=' + tabId;
	_url = getUrl(_url);
	
	require([ "dojo/_base/array", "dojo/dom" ], function(array, dom) {
		dom.byId('bDate').value = dom.byId('businessDate').value;
		dom.byId('ids').value = selectArr.join(",");
		addPostTab('dataForm', '汇总订货数据', _url);
	});
}
