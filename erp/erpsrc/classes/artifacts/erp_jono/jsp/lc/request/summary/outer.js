var outerGrid = new OuterGrid('outerGrid');

function OuterGrid(gridId) {
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
		// return this.dataStore.getData();
		var selectArr = [];
		for ( var formId in this.grid.selection) {
			if (this.grid.selection[formId]) {
				selectArr.push(formId);
			}
		}
		return selectArr;
	};

	this.init = function(_query) {
		require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dgrid/selector", "dojo/_base/declare",
				"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory" ], function(OnDemandGrid, Selection,
				selector, declare, Observable, Cache, Memory) {
			instance.dataStore = new Observable(new Memory({
				idProperty : "formId",
				data : []
			}));
			var CustomGrid = declare([ OnDemandGrid, Selection ]);
			instance.grid = new CustomGrid({
				store : instance.dataStore,
				// sort : [ {
				// attribute : "rownumber",
				// descending : false
				// } ],
				columns : instance.getColumn(selector),
				cellNavigation : false,
				allowSelectAll : true,
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);

			instance.grid.startup();
		});
	};

	this.checkStatus = function(_formId) {
		var data = getCurrentStatus(_formId);
		var status = data.formStatus;
		if (status == '已处理') {
			alert("配送反审核单已处理！")
			return false;
		}
		if (data.hasLock) {
			alert("单据正在编辑或处理中！")
			return false;
		}
		return true;
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
			label : '订货单编号',
			field : 'formId',
			sortable:false,
			renderCell : function(object, data) {
				return hrefFmt(object.formId, instance.doScan, object);
			},
		}, {
			label : '订货部门',
			field : 'buyerName',
			sortable:false
		}, {
			label : '制单人',
			field : 'formMaker',
			sortable:false
		}, {
			label : '制单日期',
			field : 'formTime',
			sortable:false
		}
//		, {
//			label : '到货日期',
//			field : 'receiveTime',
//			sortable:false
//		}, {
//			label : '到货周期',
//			field : 'arrivePeriod',
//			sortable:false
//		}
		, {
			label : '备注',
			field : 'formNote',
			sortable:false
		}, {
			label : '主要订货',
			field : 'maxPayItem',
			sortable:false
		}, {
			label : '订货总额',
			field : 'allPayAmt',
			sortable:false
		}, {
			label : "使用模板",
			field : "templateName",
			renderCell : function(object, data) {
				return hrefFmt(object.templateName, doScan, object);
			},
			sortable:false
		}, {
			label : '配送方式',
			field : 'deliveryType',
			sortable:false
		}];
	};

	this.doScan = function(row) {
		var _url = appRoot + "/outerorder/manage/scanView.action?formId=" + row.formId;
		_url = getUrl(_url);
		
		addTab("查看订货单-"+row.formId, _url);
	};
}
