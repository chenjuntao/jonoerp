var orderGrid = new OrderGrid('orderGrid');

function OrderGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.doPick = function() {
		var selectArr = [];

		for ( var formId in instance.grid.selection) {
			selectArr.push(formId);
		}

		if (selectArr.length == 0) {
			alert("请选择一条已审核的生成计划单！");
			return;
		}

		return selectArr[0];
	}

	this.init = function(_query) {
		var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/doAuditedQuery.action";
		_url = getUrl(_url);
		
		require([ "dgrid/selector", "dojo/_base/declare", "dgrid/Selection", "dgrid/OnDemandGrid",
				"custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory" ], function(
				selector, declare, Selection, OnDemandGrid, Server, Observable, Cache, Memory) {
			var myStore = Observable(Cache(Server({
				target : _url,
				idProperty : 'formId',
				sync : true,
				query : function(query, options) {
					if (query.branchId == undefined) {
						query = _query;
					}
					return Server.prototype.query.call(this, query, options);
				}
			}), Memory()));

			var CustomGrid = declare([ OnDemandGrid, Selection ]);

			instance.grid = new CustomGrid({
				store : myStore,
				selectionMode : "single",
				columns : instance.getColumn(selector),
				cellNavigation : false,
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);

			instance.grid.startup();
		});
	};

	this.getColumn = function(selector) {
		return [ selector({
			label : "",
			selectorType : "radio",
			field : 'rownumber',
			sortable : false
		}), {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "单据编号",
			field : "formId",
			renderCell : function(object, data) {
				return hrefFmt(object.formId, doArrangeScan, object);
			},
			sortable : false
		}, {
			label : "关联单据编号",
			field : "formRefId",
			renderCell : function(object, data) {
				if (object.formRefId.split(",").length == 1) {
					return hrefFmt(object.formRefId.split(",")[0], doMoreScan, object.formRefId);
				} else {
					return hrefFmt(object.formRefId.split(",")[0] + "更多", doRelScan, object);
				}
			},
			sortable : false
		}, {
			label : "制单人员",
			field : "formMaker",
			sortable : false
		}, {
			label : "制单日期",
			field : "formTime",
			sortable : false
		}, {
			label : "审核人员",
			field : "auditor",
			sortable : false
		}, {
			label : "审核日期",
			field : "auditTime",
			sortable : false
		}, {
			label : "备注说明",
			field : "formNote",
			sortable : false
		} ];
	};
}
