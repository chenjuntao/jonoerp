var observationGrid = new ObservationGrid('observationGrid');

function ObservationGrid(gridId) {
	var instance = this;
	this.grid = null;
	this.dataStore = null;

	this.set = function(pro, store) {
		instance.grid.set('store', store);
	}

	this.init = function(_query) {
		var _url =  appRoot + "/hq/station_observation/getData.action";
		_url = getUrl(_url);
		
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
		return [{
			label : "部分名称",
			field : "part_name",
			sortable:false
		}, {
			label : "步骤具体操作",
			field : "step_operation",
			sortable:false
		}, {
			label : "步骤备注",
			field : "step_note",
			sortable:false
		}, {
			label : "分数",
			field : "score",
			sortable:false
		},{
			label : "",
			field : "none",
			sortable:false
		}];
	};

}
