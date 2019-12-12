var arrangementGrid = new ArrangementGrid('arrangeGrid');

function ArrangementGrid(gridId) {
	var instance = this;

	this.grid = null;
	this.dataStore = null;
	this.selectArr = [];

	this.query = function(_query) {
		this.grid.set('query', _query);
	};

	this.loadData = function(_arr) {
		this.dataStore.setData([]);
		this.grid.set("store", this.dataStore);
		require([ "dojo/_base/array" ], function(array) {
			array.forEach(_arr, function(row, i) {
				instance.dataStore.put(row);
			});
			instance.grid.selectAll();
		});
	};
	
	this.getData = function() {
		var selectArr = [];
		for ( var formId in this.grid.selection) {
			if (this.grid.selection[formId]) {
				selectArr.push(formId);
			}
		}
		return selectArr;
	};

	this.init = function(_query) {
		require([ 
		          "dojo/_base/array", 
		          "dgrid/OnDemandGrid", 
		          "dgrid/Selection", 
		          "dgrid/selector",
		          "dojo/_base/declare", 
		          "dojo/store/Observable",
		          "dojo/store/Cache", 
		          "dojo/store/Memory" ,
		          "dgrid/extensions/ColumnResizer"
		        ], function(arrayUtil,OnDemandGrid, Selection, selector, declare, Observable, Cache,Memory,ColumnResizer) {
			instance.dataStore = new Observable(new Memory({
				idProperty : "formId",
				data : []
			}));
			var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer ]);
			instance.grid = new CustomGrid({
				store : instance.dataStore,
				columns : instance.getColumn(selector),
				cellNavigation : false,
				allowSelectAll : true,
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);
			
			instance.grid.startup();
		});
	};

	this.getColumn = function(selector) {
		return [ selector({
			label : "",
			field : 'rownumber',
			sortable:false
		}), {
			label : "序号",
			field : "rownumber",
			sortable:false
		}, {
			label : "单据编号",
			field : "formId",
			renderCell : function(object, data) {
				return hrefFmt(object.formId, doScan, object);
			},
			sortable:false
		},{
			label : '关联单据编号',
			field : 'formRefId',
			renderCell : function(object, data) {
				if (object.formRefId.split(",").length == 1) {
					return hrefFmt(object.formRefId.split(",")[0],
							doMoreScan, object.formRefId);
				} else {
					return hrefFmt(
							object.formRefId.split(",")[0] + "更多",
							doRelScan, object);
				}
			},
			sortable:false
		}, {
			label : "制单人员",
			field : "formMaker",
			sortable:false
		}, {
			label : "制单日期",
			field : "formTime",
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
			label : "备注",
			field : "formNote",
			sortable:false
		} ,{
			label : "",
			field : "none",
			sortable:false
		}];
	};
}
