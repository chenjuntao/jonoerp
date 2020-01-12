var arrangementGrid = new ArrangementGrid('arrangementGrid');

function ArrangementGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.init = function() {
		require([ 
		          "dgrid/editor",
		          "dgrid/OnDemandGrid", 
		          "dojo/store/Observable", 
		          "dojo/store/Memory" 
		          ], function(
		        		  editor,
		        		  OnDemandGrid,
		        		  Observable, 
		        		  Memory) {
			
			instance.grid = new OnDemandGrid({
				columns : instance.getColumn(editor),
				cellNavigation : false,
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);
			
			instance.grid.on("dgrid-datachange", function(event) {
				var field = event.cell.column.field;
				if (field == 'note') {
					instance.grid.save();// very important
					var row = instance.grid.get("store").get(event.rowId);
					row[field] = event.value;
					instance.grid.get("store").put(row);
				}
			});


			instance.grid.startup();
		});
	};
	
	this.getColumn = function(editor) {
		return [ {
			label : "序号",
			field : "rownumber"
		}, {
			label : "单据编号",
			field : "formId"
		}, {
			label : "商品编码",
			field : "itemId"
		}, {
			label : "商品名称",
			field : "itemName"
		}, {
			label : "单位",
			field : "itemDimension"
		}, {
			label : "规格",
			field : "itemSpecification"
		}, {
			label : "计划生产量",
			field : "produceCount"
		}, {
			label : "工单编号",
			field : "workOrderId"
		}, {
			label : "生产车间",
			field : "workshop"
		}, {
			label : "生产日期",
			field : "produceTime"
		}, {
			label : "生产周期",
			field : "productionCycle"
		}, {
			label : "完工日期",
			field : "completeTime"
		}, editor({
			label : "备注",
			field : "note"
		})	];
	};
}
