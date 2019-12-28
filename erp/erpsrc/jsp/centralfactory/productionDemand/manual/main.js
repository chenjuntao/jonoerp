require([ "dojo", "dojo/ready", "dojox/widget/Standby" ], function(dojo, ready, Standby) {
	ready(function() {
		addEvent();
		initGrid();

		if (dojo.byId('supplierId').value > 0) {
			dojo.byId('supplierId').value = '';
		}

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

function addEvent() {
}

function doClean() {
	dataStore.setData('');// 生成成功后清空数据
	grid.set("store", dataStore);
}

function validateGrid() {
	if (!validateColumn(grid, 'itemCount')) {
		return false;
	}

	if (!validateColumn(grid, 'orderCount')) {
		return false;
	}
	return true;
}

function freshGrid() {
}

function doSubmit() {
	if (!validateGrid()) {
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

					if (rows[i].orderCount == 0) {
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

function initGrid() {
	require([ "dojo/query", "dgrid/OnDemandGrid", "dojo/store/Observable", "dijit/form/DateTextBox",
			"dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dojo/keys", "dgrid/editor",
			"dijit/form/NumberTextBox", "dgrid/extensions/ColumnResizer" ], function(query, OnDemandGrid, Observable,
			DateTextBox, Memory, declare, Keyboard, keys, editor, NumberTextBox, ColumnResizer) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox, DateTextBox),
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.startup();

		grid.on('keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});

		// 保证鼠标焦点与键盘焦点的连贯性
		grid.on('mousedown', function(e) {
			grid.focus(e.target);
		});
		grid.on('dgrid-cellfocusin', function(e) {
			var $input = query('.dijitInputField input[type=text]', e.target);
			if ($input.length > 0) {
				if (e.parentType != undefined) {// 鼠标事件不予处理
					$input[0].select();
				}
			}
		});
	});
}

function loadData(data) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			if (row.itemCount == undefined) {
				row.itemCount = row.payAmt = 0;
			}
		});
		dataStore.setData(data);
		grid.set("store", dataStore);
	});
}

function getColumn(editor, NumberTextBox, DateTextBox) {
	return [ {
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
	}, {
		label : "类别",
		field : "itemCategoryName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
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
	}, DateTextBox), editor({
		label : "计划生产量",
		field : "orderCount",
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
	} ];
}

var productDlg = null;
function selMaterial() {
	var frameId = 'ifr_selProduct';
	if (productDlg == null) {
		var _url = appRoot + "/restaurant/reportdamage/selproduct/mainView.action?branchType="
				+ dojo.byId('branchType').value;
		_url = getUrl(_url);
		
		var option = {
			title : "选择出品",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		productDlg = createDialog(option);
	} else {
		productDlg.show();
	}
}

function closeProductDlg(data) {
	// console.dir(productDlg);
	productDlg.hide();

	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/array" ], function(Observable, Memory, array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			row.itemCount = 0;
			row.orderCount = 0;
		});
		dataStore = new Observable(new Memory({
			data : data,
			idProperty : "itemId"
		}));
		grid.set("store", dataStore);
	});
}