function doQuery() {
	grid.set('query', {});
}

var grid = null;
require([ "dojo", "dojo/ready", "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable",
		"dojo/store/Cache", "dojo/store/Memory", "dgrid/ColumnSet", "dojo/_base/declare",
		"dgrid/extensions/ColumnResizer" ], function(dojo, ready, OnDemandGrid, Server, Observable, Cache, Memory,
		ColumnSet, declare, ColumnResizer) {
	ready(function() {
		initGrid();
	});

	function initGrid() {
		var _url = appRoot + "/lc/distribution/diffhandle/doQuery.action";
		_url = getUrl(_url);

		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : "rownumber"
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, ColumnSet, ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	}
});

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '单据编号',
		field : 'formId',
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmtId('处理差异', handleDiff, object, "operate" + object.rownumber);
		}
	} ] ], [ [ {
		label : '单据类型',
		field : 'formType',
		formatter : function(value, rowData) {
			if (value == "INNER_CROSS") {
				return "越库";
			}
			return '统配';
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
		field : 'requester',
		sortable : false
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
		label : '金额最大的商品',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '总额',
		field : 'allPayAmt',
		sortable : false
	}, {
		label : '',
		field : 'none',
		sortable : false
	} ] ] ];
}

function doFormScan(_row) {
	doDetailScan(_row.formId);
}

function handleDiff(_row) {
	var _url = appRoot + "/lc/distribution/diffhandle/handleView.action?formId=" + _row.formId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);

	addTab("处理配送差异", _url);
}
