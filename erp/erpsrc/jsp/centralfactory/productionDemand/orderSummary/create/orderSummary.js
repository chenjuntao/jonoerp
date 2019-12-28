var orderSummaryGrid = new OrderSummaryGrid('orderSummaryGrid');

function OrderSummaryGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot + "/centralfactory/productionDemand/orderSummary/create/doSummaryQuery.action";
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
			label : '商品编码',
			field : 'itemId'
		}, {
			label : '商品名称',
			field : 'itemName'
		}, {
			label : '单位',
			field : 'itemDimension'
		}, {
			label : '规格',
			field : 'itemSpecification'
		}, {
			label : '类别',
			field : 'itemCategory'
		}, {
			label : '数量',
			field : 'itemCount'
		}, {
			label : '标准单价',
			field : 'itemUnitPrice'
		}, {
			label : '金额',
			field : 'payAmt'
		}, {
			label : '',
			field : 'none'
		} ];
	};
}
