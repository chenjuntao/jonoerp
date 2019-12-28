init();

var grid;

function customExport() {
	exportXls();
}

function doQuery() {
	var startDate = dojo.byId('startDate').value;
	var endDate = dojo.byId('endDate').value;

	if (isEmpty(startDate)) {
		alert("请选择开始营业日期！");
		return;
	}

	if (isEmpty(endDate)) {
		alert("请选择结束营业日期！");
		return;
	}

	grid.set('query', getQuery());
}

function print() {
	prn1_preview(getExportArgs());
}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

function init() {
	require([ "dojo/dom", "dojo/request/xhr", "dojo/_base/array", "dojo/dom-form", "dojo/parser", "dojo/store/Memory",
			"dijit/form/FilteringSelect", "dijit/form/Button", "dijit/Dialog", "dojo/domReady!" ], function(dom, xhr,
			array, domForm, parser, Memory, FilteringSelect) {
		parser.parse();

		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		initGrid();
	});
}

function doScan(item) {
	var _url = appRoot + "/reportmanage/restaurant/cost/jono/detailView?branchId=" + item.branchId+"&startDate="+dojo.byId('startDate').value
	+ "&endDate="+dojo.byId('endDate').value;
	_url = getUrl(_url);
	
	_title = item.branchName+"成本差异分析明细表";
	addTab(_title, _url);
}

function getColumn() {
	return [ [ [ {
		label : "餐厅",
		className : 'text-center',
		field : "branchName",
		sortable:false,
		renderCell : function(object, data) {
			if (object.branchName == "合计") {
				return  spanFmt(getBoldText(object.branchName));
			}

			return hrefFmt(object.branchName, doScan, object);
		}
	}, {
		label : "出品金额",
		className : 'text-right',
		field : "produceAmt",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "营业额",
		className : 'text-right',
		field : "businessAmt",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	} ] ], [ [ {
		label : "期初库存",
		className : 'text-right',
		field : "beginAmt",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "本期进货",
		className : 'text-right',
		field : "inAmt",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "期末库存",
		className : 'text-right',
		field : "resultAmt",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "实际成本",
		className : 'text-right',
		field : "actualCost",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "实际成本比例",
		className : 'text-right',
		field : "actualCostPer",
		sortable:false,
		formatter : function(field, renderCell) {
			if (field == null) {
				return '';
			}
			if (renderCell.branchName == "合计") {
				return getBoldText(field + "%");
			}
			return field + "%";
		}
	}, {
		label : "基本成本额",
		className : 'text-right',
		field : "baseAmt",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "折前成本率",
		className : 'text-right',
		field : "beforeCostPer",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field + "%");
			}

			return field + "%";
		}
	}, {
		label : "折后成本率",
		className : 'text-right',
		field : "afterCostPer",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field + "%");
			}

			return field + "%";
		}
	}, {
		label : "赠送",
		className : 'text-right',
		field : "presentCost",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "赠送比例",
		className : 'text-right',
		field : "presentCostPer",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field + "%");
			}

			return field + "%";
		}
	}, {
		label : "成品损耗",
		className : 'text-right',
		field : "dishAmt",
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "成品损耗比例",
		className : 'text-right',
		field : "dishPer",
		sortable:false,
		formatter : function(field, renderCell) {
			if (field == null) {
				return '';
			}
			if (renderCell.branchName == "合计") {
				return getBoldText(field + "%");
			}
			return field + "%";
		}
	}, {
		label : "半成品损耗",
		className : 'text-right',
		field : "rawAmt",
		sortable:false,
		formatter : function(field, renderCell) {
			if (field == null) {
				return '';
			}
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "半成品损耗比例",
		className : 'text-right',
		sortable:false,
		field : "rawPer",
		formatter : function(field, renderCell) {
			if (field == null) {
				return '';
			}
			if (renderCell.branchName == "合计") {
				return getBoldText(field + "%");
			}
			return field + "%";
		}
	}, {
		label : "损益",
		className : 'text-right',
		field : "lossGain",
		sortable:false,
		formatter : function(field, renderCell) {
			if (field == null) {
				return '';
			}
			if (renderCell.branchName == "合计") {
				return getBoldText(field);
			}

			return field;
		}
	}, {
		label : "损益比例",
		className : 'text-right',
		field : "lossGainPer",
		sortable:false,
		formatter : function(field, renderCell) {
			if (field == null) {
				return '';
			}
			if (renderCell.branchName == "合计") {
				return getBoldText(field + "%");
			}
			return field + "%";
		}
	}, {
		label : "管理成本模块总成本率",
		className : 'text-right',
		field : "totalPer",
		sortable:false,
		formatter : function(field, renderCell) {
			if (field == null) {
				return '';
			}
			if (renderCell.branchName == "合计") {
				return getBoldText(field + "%");
			}
			return field + "%";
		}
	} ] ] ];
};

function initGrid() {
	var _url = appRoot + "/reportmanage/restaurant/cost/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dojo/_base/lang", "dojo/_base/declare", "dgrid/Selection", "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory", "dgrid/ColumnSet", "dgrid/Keyboard",
			"dojo/domReady!" ], function(lang, declare, Selection, OnDemandGrid, Server, Observable, Cache, Memory,
			ColumnSet, Keyboard) {
		var dataStore = Observable(Cache(Server({
			target : _url,
			idProperty:'rownumber',
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnSet, Keyboard ]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, 'dataGrid');

		grid.startup();
	});
};
