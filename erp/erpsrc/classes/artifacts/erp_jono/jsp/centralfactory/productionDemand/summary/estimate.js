require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function doClose() {
	closeTab(tabId, "doQuery");
}

function validateGrid() {
	if (!validateColumn(grid, 'orderCount2')) {
		return false;
	}
	return true;

}
var grid = null;
var dataStore = null;

function doQuery() {
	var _url = appRoot + "/centralfactory/productionDemand/summary/queryEstimateSummary.action?parentTabId=" + tabId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/store/Observable" ], function(xhr, Memory, Observable) {
		xhr(_url, {
			handleAs : "json",
			method : "POST",
			data : {
				ids : document.getElementById("ids").value,
				formTime : document.getElementById('formTime').value
			}
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				data : data,
				idProperty : "itemId"
			}));
			grid.set("store", dataStore);
		});
	})
}

function doSubmit() {
	if (!validateGrid()) {
		return;
	}

	var flag = isSummaryValid(dojo.byId('ids').value);
	if (!flag) {
		return;
	}

	require([ "dojo/date/locale" ], function(locale) {
		grid.save().then(function(value) {
			var rows = dataStore.query();
			var info = '';
			var rowsSave = [];
			if (rows.length > 0) {
				var zeroItem = [];
				for (var i = 0; i < rows.length; i++) {
					if (typeof rows[i].completeTime == 'object') {
						rows[i].completeTime = locale.format(rows[i].completeTime, {
							selector : 'date',
							datePattern : 'yyyy-MM-dd'
						});
					}

					if (rows[i].completeTime < rows[i].businessDate) {
						info += '[' + rows[i].itemId + ']' + rows[i].itemName + "\n";
					}

					if (rows[i].itemCount == 0) {
						zeroItem.push(rows[i].itemName);
					} else {
						rowsSave.push(rows[i]);
					}
				}

				if (!isEmpty(info)) {
					info += "物料的完工日期小于制单日期！";
					alert(info);
					return;
				}

				if (rowsSave.length == 0) {
					alert("不允许所有物料的计划生产量为零！");
					return;
				}
				if (zeroItem.length > 0) {
					if (!confirm(zeroItem.join('、') + "输入为零，确定提交吗？")) {
						return;
					}
				}
			}

			dojo.byId('jsonData').value = JSON.stringify(rowsSave);
			var _url = appRoot + "/centralfactory/productionDemand/summary/checkView.action?parentTabId=" + tabId;
			_url = getUrl(_url);
			
			addPostTab('billForm', '生产计划单提交', _url);
		}, function(err) {
			console.log(err);
		});
	});

}

var grid = null;
var dataStore = null;

function doQuery() {
	var _url = appRoot + "/centralfactory/productionDemand/summary/queryEstimateSummary.action?parentTabId=" + tabId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/store/Observable" ], function(xhr, Memory, Observable) {
		xhr(_url, {
			handleAs : "json",
			method : "POST",
			data : {
				ids : document.getElementById("ids").value,
				formTime : document.getElementById('formTime').value
			}
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				data : data,
				idProperty : "rownumber"
			}));
			grid.set("store", dataStore);
		});
	})
}

function initGrid() {
	require([ "dijit/form/NumberTextBox", "dojo/date/locale", "dijit/form/DateTextBox", "dgrid/OnDemandGrid",
			"dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query", "dgrid/ColumnSet" ], function(
			NumberTextBox, locale, DateTextBox, OnDemandGrid, Observable, Memory, declare, Keyboard, editor,
			ColumnResizer, on, keys, query, ColumnSet) {
		execute(NumberTextBox, locale, DateTextBox, OnDemandGrid, Observable, Memory, declare, Keyboard, editor,
				ColumnResizer, on, keys, query, ColumnSet);
	});

	function execute(NumberTextBox, locale, DateTextBox, OnDemandGrid, Observable, Memory, declare, Keyboard, editor,
			ColumnResizer, on, keys, query, ColumnSet) {
		dataStore = new Observable(new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		})));

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : dataStore,
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			columnSets : getColumn(editor, NumberTextBox, DateTextBox),
			selectionMode : "toggle",
			allowSelectAll : true,
			loadingMessage : '加载中...',
		}, "dataGrid");

		on(grid, 'keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});

		// 保证鼠标焦点与键盘焦点的连贯性
		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});

		// on(grid, "dgrid-datachange", function(event) {
		// var field = event.cell.column.field;
		// if (field == 'orderCount2') {
		// // dgrid 视图和数据源相分离
		// grid.save();// very important
		// var row = dataStore.get(event.rowId);
		// row[field] = event.value;
		// row.orderCount = event.value * row.deliveryFactor; // 计算库存单位
		// dataStore.put(row);
		// }
		// });
		grid.startup();
		doQuery();
	}
}

function getColumn(editor, NumberTextBox, DateTextBox) {
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
	}, editor({
		label : "完工日期",
		field : "completeTime",
		sortable : false,
		editorArgs : {
			constraints : {
				datePattern : 'yyyy-MM-dd',
				selector : 'date'
			},
			style : "width:100px;"
		}
	}, DateTextBox), {
		label : "最小生产倍量",
		field : "minOrderCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "建议值",
		field : "suggestAmt",
		className : 'grid-number',
		sortable : false
	}, editor({
		label : "计划生产量",
		field : "orderCount2",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), {
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
