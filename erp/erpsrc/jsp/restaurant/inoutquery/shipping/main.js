require([ "dojo/dom-form", "dojo/domReady!" ], function(domForm) {
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
var myStore = null;
function initGrid() {
	var _url = appRoot + "/restaurant/inoutquery/shipping/doQuery.action?formType=request&branchType="
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/Selection", "dgrid/selector", "dgrid/ColumnSet", "dojo/_base/declare",
			"dgrid/extensions/Pagination", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(Grid, Server,
			Observable, Cache, Memory, Selection, selector, ColumnSet, declare, Pagination, ColumnResizer) {
		myStore = Observable(Cache(Server({
			target : _url,
			idProperty : "rownumber",
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, ColumnSet, Selection, Pagination, ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(selector),
			cellNavigation : false,
			selectionMode : "toggle",
			allowSelectAll : true,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();

	});
}

function getColumn(selector) {
	return [ [ [ selector({
		label : "",
		field : 'rownumber',
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		},
		sortable : false
	}, {
		label : '单据编号',
		field : 'formId',
		sortable : false,
		renderCell : function(object, data) {
			if (object.rownumber == '合计') {
				return hrefFmt("", doFormScan, object);
			}
			return hrefFmt(object.formId, doFormScan, object);
		}
	} ] ], [ [ {
		label : '操作',
		field : 'print',
		renderCell : function(object, data) {
			if (object.rownumber == '合计') {
				return hrefFmt("", doEdit, object);
			}

			return hrefFmt("管理", doEdit, object);
		},
		sortable : false
	}, {
		label : '单据状态',
		field : 'formStatus',
		sortable : false
	}, {
		label : '是否加单',
		field : 'isAddForm',
		sortable : false,
		formatter : function(field) {
			if (field == "Y") {
				return "加单";
			}

			return '';
		}
	}, {
		label : '前置单据',
		field : 'formRefId',
		sortable : false
	}, {
		label : '主要配送品',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '打印',
		field : 'print',
		renderCell : function(object, data) {
			if (object.rownumber == '合计') {
				return hrefFmt("", doPrint, object);
			}

			return hrefFmt("打印", doPrint, object);
		},
		sortable : false
	}, {
		label : '打印次数',
		field : 'printTimes',
		renderCell : function(object, data) {
			if (object.rownumber == '合计') {
				return spanFmt("", object.formId);
			}

			return spanFmt(object.printTimes, object.formId);
		},
		sortable : false
	}, {
		label : '是否捡货',
		field : 'pickStatus',
		sortable : false
	}, {
		label : '是否入库',
		field : 'inputStatus',
		sortable : false
	}, {
		label : '是否在途',
		field : 'onStatus',
		sortable : false
	}, {
		label : '单据类型',
		field : 'formType',
		formatter : function(value, rowData) {
			if (value == "INNER_CROSS") {
				return "越库";
			}
			return '';
		},
		sortable : false
	}, {
		label : '配送部门',
		field : 'provider',
		sortable : false
	}, {
		label : '配送日期',
		field : 'receiveTime',
		sortable : false
	}, {
		label : '订货部门',
		field : 'requester'
	}, {
		label : '订货地址',
		field : 'requestAddress',
		sortable : false
	}, {
		label : '制单人员',
		field : 'formMaker',
		sortable : false
	}, {
		label : '制单日期',
		field : 'formTime',
		sortable : false
	}, {
		label : '操作时间',
		field : 'formTimeActual'
	}, {
		label : '捡货人员',
		field : 'auditor',
		sortable : false
	}, {
		label : '捡货日期',
		field : 'auditTime',
		sortable : false
	}, {
		label : '入库人员',
		field : 'inputer',
		sortable : false
	}, {
		label : '入库时间',
		field : 'inputTime',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable : false
	}, {
		label : '',
		field : 'none',
		sortable : false
	} ] ] ];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑或审核中！")
		return false;
	}
	if (status == '已删除') {
		alert("单据已删除！");
		return false;
	} else if (status == '已作废') {
		alert("单据已作废！");
		return false;
	} else if (status == '已处理') {
		alert("单据已处理！");
		return false;
	} else if (status == '已审核') {
		alert("单据已审核！");
		return false;
	} else if (status == '已确认') {
		alert("单据已确认！");
		return false;
	} else if (status == '已作废') {
		alert("单据已作废！");
		return false;
	}
	return true;
}
function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/inoutquery/shipping/editView.action?formId=" + row.formId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	var _title = '管理配送单-' + row.formId;
	addTab(_title, _url);
}

function doFormScan(row) {
	doMoreDetailScan(row);
}

function getCols() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "商品编码",
		field : "itemId",
		sortable : false
	}, {
		label : "商品名称",
		field : "itemName",
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "包装因子",
		field : "packagingFactor",
		className : 'grid-number',
		sortable : false
	}, {
		label : "包装单位",
		field : "packagingUnit",
		sortable : false
	}, {
		label : "订货数",
		field : "requestCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "配送数",
		field : "shippingCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "包装数",
		field : "packagingCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "实发数",
		field : "deliveryCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "实收数",
		field : "receiveCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "差异数",
		field : "differentCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	} ];
}

var myColumns = getCols();

var printTimes = [];

function allPrint() {
	var selectArr = [];
	for ( var formId in grid.selection) {
		if (grid.selection[formId]) {
			selectArr.push(formId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var rows = myStore.getData();
	var length = rows.length;
	for (var i = 0, len = selectArr.length - 1; i <= len; i++) {
		var j = selectArr[i];
		if (j != length) {
			var row = rows[j - 1];
			if (row.printTimes == 0) {
				doPrint(row);
			}
		}
	}

}
function doPrint(row) {
	var times = dojo.byId(row.formId).innerHTML;
	if (times.length == 0) {
		dojo.byId(row.formId).innerHTML = '1';
	} else {
		times = parseInt(dojo.byId(row.formId).innerHTML) + 1;
		dojo.byId(row.formId).innerHTML = times;
	}

	var _url = appRoot + "/restaurant/inoutquery/shipping/preview.action?formId=" + row.formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/query" ], function(xhr, Memory, query) {
		xhr.post(_url, {
			handleAs : "json",
			sync : 'true'
		}).then(function(data) {
			shippingHeader = data.shippingHeader;
			provider = shippingHeader.provider;
			formTime = shippingHeader.formTime;

			fId = shippingHeader.formId;
			requester = shippingHeader.requester;
			requestAddress = shippingHeader.requestAddress;
			inputer = shippingHeader.inputer;
			formMaker = shippingHeader.formMaker;
			auditor = shippingHeader.auditor;
			auditTime = shippingHeader.auditTime;
			inputTime = shippingHeader.inputTime;
			receiveTime = shippingHeader.receiveTime;
			formNote = shippingHeader.formNote;
			shippingDetail = data.shippingDetail;

			prn1_print(row);
		}, function(err) {
		});
	});

}
