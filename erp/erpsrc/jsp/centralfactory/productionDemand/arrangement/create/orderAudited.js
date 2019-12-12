var orderAuditedGrid = new OrderAuditedGrid('orderAuditedGrid');

function OrderAuditedGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.doPick = function() {
		var selectArr = [];

		for ( var formId in instance.grid.selection) {
			selectArr.push(formId);
			var row = instance.grid.store.get(formId);
		}

		if (selectArr.length == 0) {
			alert("请选择一条已审核的汇总单！");
			return;
		}

		return selectArr[0];
	}

	this.init = function(_query) {
		var _url = appRoot + "/centralfactory/productionDemand/arrangement/create/doAuditedQuery.action";
		_url = getUrl(_url);
		
		require([ "dgrid/selector", "dojo/_base/declare", "dgrid/OnDemandGrid", "custom/store/Server",
				"dojo/store/Observable", "dojo/store/Cache", "dgrid/Selection", "dojo/store/Memory" ], function(
				selector, declare, OnDemandGrid, Server, Observable, Cache, Selection, Memory) {
			var myStore = Observable(Cache(Server({
				target : _url,
				idProperty : 'formId',
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
				columns : instance.getColumn(selector),
				cellNavigation : false,
				selectionMode : "single",
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);

			instance.grid.startup();
		});
	};

	function doScan(row) {
		var _url = appRoot + "/centralfactory/productionDemand/orderSummary/audit/scanView.action?formId=" + row.formId;
		_url = getUrl(_url);
		
		addTab(row.formId + "查看", _url);
	}

	this.getColumn = function(selector) {
		return [ selector({
			label : "",
			selectorType : "radio",
			field : 'rownumber'
		}), {
			label : "序号",
			field : "rownumber"
		}, {
			label : "单据编号",
			field : "formId",
			renderCell : function(object, data) {
				return hrefFmt(object.formId, doScan, object);
			}
		}, {
			label : "汇总部门",
			field : "branchId"
		}, {
			label : "制单人员",
			field : "formMaker"
		}, {
			label : "制单日期",
			field : "formTime"
		}, {
			label : "审核人员",
			field : "auditor"
		}, {
			label : "审核日期",
			field : "auditTime"
		}, {
			label : "备注说明",
			field : "formNote"
		}, {
			label : "总金额",
			field : "allPayAmt"
		}, {
			label : "主要汇总品",
			field : "maxPayItem"
		} ];
	};
}
