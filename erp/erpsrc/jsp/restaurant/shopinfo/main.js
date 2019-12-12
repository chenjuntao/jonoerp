var grid = null;

require([ 
          "dgrid/OnDemandGrid",
          "dojo/_base/declare", 
          "dojo/dom", 
          "dojo/request/xhr", 
          "dojo/store/Memory", 
          "dgrid/extensions/ColumnResizer", 
          "dgrid/ColumnSet",
		  "dojo/domReady!"
      ], function(OnDemandGrid, declare, dom, xhr, Memory,ColumnResizer, ColumnSet) {
	var _url = appRoot + "/restaurant/shopInfoQuery/listShopInfo.action";
	
	xhr.post(_url, {
		handleAs : "json",
		data : {
			branchType : dom.byId("branchType").value
		}
	}).then(function(data) {
		var CustomGrid = declare([ OnDemandGrid, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : new Memory({
				data : data,
				idProperty:'rownumber'
			}),
			columnSets : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	}, function(err) {
		console.log(err);
	});

});

function statusFmt(value, rowData) {
	if (value == '1') {
		return '已启用';
	}
	return '';
}

var cols = [ [ [ {
	label : "编号",
	field : "branchId",
	className:'text-center'
}, {
	label : "名称",
	field : "branchName",
	sortable : false
} ] ], [ [ {
	label : "启用状态",
	field : "enabled",
	className:'text-center',
	formatter : statusFmt,
	sortable:false
}, {
	label : "助记码",
	field : "queryCode"
}, {
	label : "位置",
	field : "branchAddress",
	sortable : false
}, {
	label : "电子邮箱",
	field : "email",
	sortable : false
}, {
	label : "电话",
	field : "telephone",
	className:'text-center',
	sortable : false
}, {
	label : "传真",
	field : "fax",
	sortable : false
}, {
	label : "备注",
	field : "remark",
	sortable : false
} ] ] ];
