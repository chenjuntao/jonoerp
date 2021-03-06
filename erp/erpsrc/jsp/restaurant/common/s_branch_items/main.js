require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

var grid = null;
var dataStore = null;

function initGrid(_id) {
	var _url = appRoot + "/hq/item/supplier/queryBranchItemsBySupplier.action";

	require([ 
	          "dgrid/OnDemandGrid", 
	          "dojo/store/Memory",
	          "dojo/_base/declare", 
	          "dgrid/extensions/ColumnResizer", 
	          "dgrid/extensions/Pagination",
	          "dojo/request/xhr"
	          ], 
          function(OnDemandGrid,Memory, declare, ColumnResizer, Pagination,xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data:{branchId : branchId}
		}).then(function(data) {
			var CustomGrid = declare([ OnDemandGrid, ColumnResizer, Pagination ]);
			grid = new CustomGrid({
				store : new Memory({
					data : data,
					idProperty : 'rownumber'
				}),
				columns : getColumn(),
				cellNavigation : false,
				pageSizeOptions : [ 10, 30, 100 ],
				loadingMessage : '加载中...'
			}, "dataGrid");

			grid.startup();
		}, function(err) {
			console.log(err);
		});
		
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "门店编码",
		field : "branchId",
		className:"text-center",
		sortable : false
	}, {
		label : "门店名称",
		field : "branchName",
		sortable : false
	}, {
		label : "物料编码",
		field : "itemId",
		className:"text-center",
		sortable : false
	}, {
		label : "物料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "主供应商",
		field : "priority",
		sortable : false,
		className:'text-center',
		formatter : function(field) {
			return field=='0'?'主供应商':'';
		}
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function doConfirm() {
	var _url = appRoot + "/hq/branch/r/doDisableWithSupplier.action";
	require([ 
	          "dojo/store/Memory",
	          "dojo/request/xhr"
	          ], 
         function(Memory,xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data:{branchId : branchId,enabled:0}
		}).then(function(data) {
			if(data.msg == 'ok'){
				alert("停用成功！");
			}
			
			parent.closePurchaseDlg();
		}, function(err) {
			console.log(err);
			parent.closePurchaseDlg();
		});
		
	});
}