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
		storageId : dojo.byId('storageId').value,
		branchType : dojo.byId('branchType').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/preject/manage/doQuery.action";
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
		className : 'grid-number',
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
	}];
}

function doScan(row) {
	var data = getCurrentStatus(row.formId);
	if (data.formStatus == '已删除') {
		alert("单据已删除！")
		return false;
	}
	var _url = appRoot + "/restaurant/preject/manage/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	var _title = '采购退货单查看' + row.formId;
	addTab(_title, _url);
}

