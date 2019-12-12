var orderGrid = new OrderGrid('costCardGrid');

function OrderGrid(gridId) {
	var instance = this;
	this.grid = null;
	this.dataStore = null;

	this.set = function(pro, store) {
		instance.grid.set('store', store);
	}

	this.init = function(_query) {
		var _url = appRoot + "/hq/station_observation/queryCostInfo.action";
		
		require([ 
		          "dojo/request/xhr", 
		          "dojo/_base/array", 
		          "dojo/_base/declare",
		          "dgrid/OnDemandGrid", 
		          "dojo/store/Observable",
		          "dojo/store/Memory" 
		        ], function(xhr, arrayUtil, declare,OnDemandGrid, Observable, Memory) {

			xhr.post(_url, {
				handleAs : "json",
				data : _query
			}).then(
					function(data) {
						instance.dataStore = Observable(new Memory({
							data : data,
							idProperty : 'itemId'
						}));

						var CustomGrid = declare([ OnDemandGrid ]);
						instance.grid = new CustomGrid({
							store : instance.dataStore,
							columns : instance.getColumn(),
							cellNavigation : false,
							showHeader: false,
							loadingMessage : '加载中...',
							noDataMessage : "无数据！"
						}, gridId);

						instance.grid.startup();
					}, function(err) {
						// Handle the error condition
					});
		});
	};

	this.getColumn = function() {
		return [ {
			field : "itemName"
		}, {
			field : "itemCount"
		} ];
	};

}
