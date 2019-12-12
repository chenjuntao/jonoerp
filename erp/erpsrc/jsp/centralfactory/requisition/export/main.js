require([ "dojo", "dojo/ready","dojox/widget/Standby" ], function(dojo, ready,Standby) {
	ready(function() {
		init();
		
		standby = new Standby({
			target : "billForm"
		});
		
		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

var standby = null;

function doQuery() {
	grid.set('query',getQuery())
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		workshop : dojo.byId('workshop').value,
		endDate : dojo.byId('endDate').value
	}
}

function batchExport() {
	fillData(grid.get('columns'), grid.get('store').data);
}


var grid = null;

function init() {
	var _url = appRoot + "/cf/requisition/export/doBatchExportQuery.action";
	_url = getUrl(_url);
	
	require([ 
	         "dgrid/Selection",
	         "dijit/form/NumberTextBox", 
	         "dgrid/editor",
	          "dgrid/selector",
	          "dojo/_base/declare", 
	          "dgrid/OnDemandGrid", 
	          "custom/store/Server", 
	          "dojo/store/Observable", 
	          "dojo/store/Cache", 
	          "dgrid/Selection",
	          "dojo/store/Memory" ,
	          "dgrid/extensions/ColumnResizer"
	        ], function(Selection, NumberTextBox,editor,selector, declare, OnDemandGrid, Server, Observable, Cache,Selection, Memory,ColumnResizer) {
		 myStore = Observable(Cache(Server({
			target : _url,
			idProperty:'rownumber',
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(editor,selector, NumberTextBox),
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, 'requisitionGrid');

		grid.startup();
	});
};


function getColumn(editor,selector, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "生产车间",
		field : "workshop",
		sortable:false
	},
	{
		label : "商品编码",
		field : "item_id",
		sortable:false
	}, {
		label : "商品名称",
		field : "item_name",
		sortable:false
	}, {
		label : "单位",
		field : "item_dimension",
		sortable:false
	},  {
		label : "领料数",
		field : "item_count",
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}  ];
};


