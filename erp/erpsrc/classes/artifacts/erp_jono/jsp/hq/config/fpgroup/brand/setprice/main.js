require([ 
          "dgrid/Grid", 
          "dojo/store/Observable", 
          "custom/store/Server", 
          "dojo/_base/declare", 
          "dgrid/extensions/ColumnResizer",
          "dojo/store/Observable",
          "dojo/_base/lang" ,
          "dgrid/extensions/Pagination",
          "dojo/parser",
          "dojo/domReady!"
      ]	, function( Grid, Observable, Server,declare, ColumnResizer, Observable, lang,Pagination,parser) {
	parser.parse();
	
	var _url = appRoot + "/hq/fpgroup/brand/setprice/queryProduct.action";
	
	var myStore = Observable(Server({
		target : _url,
		idProperty : "id",
		query : function(query, options) {
			return Server.prototype.query.call(this, {priceGroupId:g_priceGroupId,itemIdorName:dojo.byId("itemIdorName").value}, options);
		}
	}));

	
	var CustomGrid = declare([ Grid, Pagination,ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");
	
		grid.startup();
	});

function doQuery() {
	grid.set('query', {priceGroupId:g_priceGroupId,itemIdorName:dojo.byId("itemIdorName").value});
}
	var grid = null;

	function getColumn() {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "编号",
			field : "id",
			sortable : false
		}, {
			label : "名称",
			field : "name",
			sortable : false
		}, {
			label : "价格",
			field : "price",
			sortable : false
		}, {
			label : "",
			field : "none",
			sortable : false
		} ];
	}

	
	function showDialog() {
		dijit.byId('customDialog').show();
	}
	
	function hideDialog() {
		dijit.byId('customDialog').hide();
	}
	
	function customExport() {
		var itemIdorName = dojo.byId('itemIdorName').value.trim();
//		var _url = appRoot + "/hq/item/meta/queryItem.action";
		var _url = appRoot + "/hq/fpgroup/brand/setprice/queryExport.action";
		_url = getUrl(_url);
		
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					priceGroupId : g_priceGroupId,
					itemIdorName : itemIdorName,
					newEnd : 1
				}
			}).then(function(data) {
				fillData(cols, data);
			}, function(err) {
				errorHandler(err);
			});
		});

	}
	
	var cols = [ {
		label : "",
		field : "none",
		sortable : false
	} ];