require([ "dojo/ready" ], function(ready) {
	ready(function() {
		initGrid();
	});
});

function doSubmit() {
	var flag = isSummaryValid(dojo.byId('ids').value);
	if (!flag) {
		return;
	}

	standby.show();

	var _url = appRoot + "/centralfactory/productionDemand/summary/doCommit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				standby.hide();
				alert("生成单据号为：" + data.formId + "提交成功！");
				closeTab(tabId, "doClose");
			} else {
				standby.hide();
				alert("操作失败！");
			}
		}, function(err) {
			standby.hide();
			alert("操作失败！");
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dgrid/extensions/ColumnResizer", "dojox/widget/Standby", "dgrid/ColumnSet" ], function(
			OnDemandGrid, Observable, Memory, declare, Keyboard, ColumnResizer, Standby, ColumnSet) {
		execute(OnDemandGrid, Observable, Memory, declare, Keyboard, ColumnResizer, Standby, ColumnSet);
	});

	function execute(OnDemandGrid, Observable, Memory, declare, Keyboard, ColumnResizer, Standby, ColumnSet) {
		dataStore = new Observable(new Observable(new Memory({
			idProperty : "rownumber",
			data : gridData
		})));

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(),
			cellNavigation : false,
			selectionMode : "toggle",
			allowSelectAll : true,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.startup();

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();

	}
}

function getColumn(editor, NumberTextBox) {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	} ] ], [ [ {
		label : "类别",
		field : "itemCategoryName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension2",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "生产车间",
		field : "workshop",
		sortable : false
	}, {
		label : "当前要货量",
		field : "itemCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "生产日期",
		field : "businessDate",
		className : 'grid-number',
		sortable : false
	}, {
		label : "生产周期",
		field : "productionCycle",
		className : 'grid-number',
		sortable : false
	}, {
		label : "完工日期",
		field : "completeTime",
		className : 'grid-number',
		sortable : false
	}, {
		label : "计划生产量",
		field : "orderCount2",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}
