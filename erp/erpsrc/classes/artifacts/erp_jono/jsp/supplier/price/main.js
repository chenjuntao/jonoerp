var grid;

require([ 
          "dgrid/OnDemandGrid",
          "dojo/store/Observable",
          "dojo/store/Cache", 
          "dojo/store/Memory",
          "dojo/request/xhr",
          "custom/store/Server", 
          "dojo/_base/declare",
          "dgrid/Keyboard", 
          "dgrid/extensions/Pagination",
          "dojo/parser",
          "dojo/domReady!"
      ],function(OnDemandGrid, Observable, Cache, Memory, xhr, Server, declare, Keyboard,Pagination,parser) {
			parser.parse();
			var _url = appRoot + "/sp/price/queryPrice.action";

			dataStore = Observable(Cache(Server({
				target : _url,
				idProperty : 'rownumber',
				query : function(query, options) {
					query = getQuery();
					return Server.prototype.query.call(this, query, options);
				}
			}),Memory()));

			var CustomGrid = declare([OnDemandGrid, Keyboard,Pagination]);
			grid = new CustomGrid({
				store : dataStore,
				columns : getColumn(),
				cellNavigation : false,
				columnLockCount : 3,
				paginationBarShowRange : true,
				paginationInitialPageSize : 25,
				pageSize : 25,
				cacheClass : Cache,
				loadingMessage : '加载中...'
			}, "dataGrid");
			grid.startup();
		});

var cols = [ {
	label : "",
	field : "none",
	sortable : false
} ];

function getColumn() {
	return [{
		label : "序号",
		field : "rownumber",
		sortable:false,
	},{
		label : "物料编码",
		field : "item_id",
		sortable:false,
		className:"text-center"
	},{
		label : "物料名称",
		field : "item_name",
		sortable:false
	},{
		label : "供应商编码",
		field : "business_id",
		className:"text-center",
		sortable:false
	}, {
		label : "供应商名称",
		field : "business_name",
		sortable:false
	}, {
		label : "价格",
		field : "item_price",
		className:"text-right",
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}];}



function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		itemId : dojo.byId('itemId').value.trim(),
		supplierId : dojo.byId('supplierId').value.trim()
	}
}

function customExport() {
	var _url = appRoot + "/sp/price/queryExport.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				itemId : dojo.byId('itemId').value.trim(),
				supplierId : dojo.byId('supplierId').value.trim()
			}
		}).then(function(data) {
			fillData(cols, data);
		}, function(err) {
			errorHandler(err);
		});
	});

}

