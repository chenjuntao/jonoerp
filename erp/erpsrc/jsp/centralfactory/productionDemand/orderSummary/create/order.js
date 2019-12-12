var orderGrid = new OrderGrid('orderGrid');

function OrderGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot + "/centralfactory/productionDemand/orderSummary/create/doQuery.action";
		_url = getUrl(_url);
		
		require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
				"dojo/store/Memory" ], function(OnDemandGrid, Server, Observable, Cache, Memory) {
			var myStore = Observable(Cache(Server({
				target : _url,
				query : function(query, options) {
					if (query.branchId == undefined) {
						query = _query;
					}
					return Server.prototype.query.call(this, query, options);
				}
			}), Memory()));

			instance.grid = new OnDemandGrid({
				store : myStore,
				columns : instance.getColumn(),
				cellNavigation : false,
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);

			instance.grid.startup();
		});
	};

	this.getColumn = function() {
		return [ {
			label : "序号",
			field : "rownumber"
		}, {
			label : "单据编号",
			field : "formId",
			renderCell : function(object, data) {
				return hrefFmt(object.formId, instance.doScan, object);
			}
		}, {
			label : "供应商",
			field : "provider"
		}, {
			label : "到货日期",
			field : "receiveTime"
		}, {
			label : "订货部门",
			field : "requester"
		}, {
			label : "收货地址",
			field : "receiveAddress"
		}, {
			label : "备注说明",
			field : "formNote"
		}, {
			label : "总金额",
			field : "allPayAmt"
		}, {
			label : "主要采购品",
			field : "maxPayItem"
		} ];
	};

	this.doScan = function(row) {
		var _url = appRoot + "/centralfactory/productionDemand/orderSummary/create/scanView.action?formId="
				+ row.formId;
		_url = getUrl(_url);
		
		addTab(row.formId + "采购单查看", _url);
	};
}
