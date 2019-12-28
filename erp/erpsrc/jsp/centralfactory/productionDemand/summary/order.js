var orderGrid = new OrderGrid('orderGrid');

function OrderGrid(gridId) {
	var instance = this;
	this.grid = null;
	this.dataStore = null;
	
	this.query = function(_query) {
		instance.grid.set('query', getQuery());
		instance.grid.selectAll();
	};
	
	this.getSelectIds = function(){
		var selectArr = [];
		 for (var formId in instance.grid.selection) {
	         if (this.grid.selection[formId]) {
	        	 selectArr.push(instance.dataStore.get(formId).formId);
	         }
	     }
		 
		 return  selectArr;
	}
	
	this.init = function(_query) {
		var _url = appRoot + "/centralfactory/productionDemand/summary/doQuery.action";
		_url = getUrl(_url);
		
		require([ 
		          "dojo/_base/array",
		          "dojo/_base/declare",
		          "dgrid/Selection",
		          "dgrid/selector",
		          "dgrid/OnDemandGrid", 
		          "custom/store/Server",
		          "dojo/store/Observable",
		          "dojo/store/Cache",
		          "dojo/store/Memory",
		          "dgrid/extensions/ColumnResizer"
		          ], function(arrayUtil,declare,Selection,selector,OnDemandGrid, Server,Observable, Cache, Memory,ColumnResizer) {
			instance.dataStore = Observable(Cache(Server({
				idProperty : "formId",
				target : _url,
				query : function(query, options) {
					return Server.prototype.query.call(this, getQuery(), options);
				}
			}), Memory()));

			var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer ]);
			instance.grid = new CustomGrid({
				store : instance.dataStore,
				allowSelectAll : true,
				columns : instance.getColumn(selector),
				cellNavigation : false,
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);

			instance.grid.startup();
			instance.grid.selectAll();
		});
	};

	this.getColumn = function(selector) {
		return [  selector({
			label : "",
			field : 'rownumber',
			sortable:false
		}),{
			label : "序号",
			field : "rownumber",
			sortable:false
		}, {
			label : "单据编号",
			field : "formId",
			renderCell : function(object, data) {
				return hrefFmt(object.formId, instance.doScan, object);
			},
			sortable:false
		}, {
			label : "供应商",
			field : "provider",
			sortable:false
		}, {
			label : "到货日期",
			field : "receiveTime",
			sortable:false
		}, {
			label : "审核人员",
			field : "auditor",
			sortable:false
		}, {
			label : "审核日期",
			field : "auditTime",
			sortable:false
		}, {
			label : "订货部门",
			field : "requester",
			sortable:false
		}, {
			label : "收货地址",
			field : "receiveAddress",
			sortable:false
		},{
			label : "备注说明",
			field : "formNote",
			sortable:false
		}, {
			label : "总金额",
			field : "allPayAmt",
			sortable:false
		}, {
			label : "主要采购品",
			field : "maxPayItem",
			sortable:false
		}
	];
	};
	
	this.doScan = function(row) {
		var _url = appRoot
				+ "/centralfactory/productionDemand/summary/scanView.action?formId="
				+ row.formId;
		_url = getUrl(_url);
		
		addTab("查看采购单-"+row.formId, _url);
	};
}
