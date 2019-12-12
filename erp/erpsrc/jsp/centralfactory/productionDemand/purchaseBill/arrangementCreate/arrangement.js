var arrangementGrid = new ArrangementGrid('requestGrid');

function ArrangementGrid(gridId) {
	var instance = this;

	this.grid = null;
	this.dataStore = null;

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
//			if (this.grid.selection[formId]) {
//				selectArr.push(formId);    //如果勾选单据有问题，可以把注释去掉
//			}
			selectArr.push(formId);
		}
		return selectArr;
	};

	this.init = function(_query) {
		require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dgrid/selector",
				"dojo/_base/declare", "dojo/store/Observable",
				"dojo/store/Cache", "dojo/store/Memory" ], function(
				OnDemandGrid, Selection, selector, declare, Observable, Cache,
				Memory) {
			instance.dataStore = new Observable(new Memory({
				idProperty : "formId",
				data : []
			}));
			var CustomGrid = declare([ OnDemandGrid, Selection ]);
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
			field : 'rownumber'
		}), {
			label : "序号",
			field : "rownumber"
		}, {
			label : "单据编号",
			field : "formId"
		}, {
			label : "制单人员",
			field : "formMaker"
		}, {
			label : "制单日期",
			field : "formTime"
		}, {
			label : "审核人员",
			field : "auditor"
		}, {
			label : "审核日期",
			field : "auditTime"
		}, {
			label : "备注",
			field : "formNote"
		}, {
			label : '查看',
			field : 'scan',
			renderCell : function(object, data) {
				return hrefFmt("查看", instance.doScan, object);
			}
		}, {
			label : "",
			field : "none"
		} ];
	};

	this.doScan = function(row) {
		var _url = appRoot
				+ "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId="
				+ row.formId;
		_url = getUrl(_url);
		
		addTab("中央工厂生产计划单查看", _url);
	};
}
