dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		completeDate : dojo.byId('completeDate').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/hq/produce/doQuery.action";
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare",
			"dojo/domReady!" ], function(OnDemandGrid, Server, Observable,
			Cache, Memory,declare) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([OnDemandGrid]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [  {
		label : '商品编码',
		field : 'item_id',
		sortable:false
	}, {
		label : '商品名称',
		field : 'item_name',
		sortable:false
	}, {
		label : '库存单位',
		field : 'item_dimension',
		sortable:false
	},{
		label : '标准领料量',
		field : 'standardCount',
		sortable:false
	}, {
		label : '领料量',
		field : 'getAmt',
		sortable:false
	}, {
		label : '超领量',
		field : 'extraAmt',
		sortable:false
	}, {
		label : '退料量',
		field : 'returnAmt',
		sortable:false
	},  {
		label : '实际生产量',
		field : 'actualCount',
		sortable:false
	}, {
		label : '差异',
		field : 'diff',
		sortable:false
	}, {
		label : '',
		field : 'none',
		sortable:false
	}];
}


