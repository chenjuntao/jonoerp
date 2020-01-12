dojo.ready(function() {
	initDishGrid();
	initRawGrid();
});

function initDishGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dojo/query",
			"dgrid/extensions/ColumnResizer", "dojox/widget/Standby", "dojo/domReady!" ], function(OnDemandGrid,
			Memory, declare, Keyboard, query, ColumnResizer, Standby) {
		var dataStore1 = new Memory({
			data : jsonData,
			idProperty : 'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		var grid1 = new CustomGrid({
			store : dataStore1,
			columns : dishCols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dishGrid");

		grid1.startup();

		// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
}

var dataStore = null;
var grid = null;
function initRawGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dgrid/editor", "dijit/form/NumberTextBox",
			"dojo/_base/declare", "dgrid/Keyboard", "dojo/on", "dojo/keys", "dgrid/extensions/ColumnResizer",
			"dojo/domReady!" ], function(OnDemandGrid, Memory, editor, NumberTextBox, declare, Keyboard, on, keys,
			ColumnResizer) {
		dataStore = new Memory({
			data : rawJsonData,
			idProperty : 'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "rawGrid");

		on(grid, 'keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});

		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});

		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		grid.startup();
	});
}

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "例牌",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : "计划耗料数量",
		className : 'text-right',
		field : "itemCountPlan",
		sortable : false
	}, editor({
		label : "实际耗料数量",
		field : "itemCountActual",
		editorArgs : {
			style : 'width: 100px;text-align: right;',
			constraints : {
				min : 0,
				max : 99550,
				places : '0,4'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), {
		label : "",
		field : "blank",
		sortable : false
	} ];
}

var dishCols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "半成品编码",
	field : "itemId",
	className : 'text-center',
	sortable : false
}, {
	label : "半成品名称",
	field : "itemName",
	sortable : false
}, {
	label : "例牌",
	field : "itemDimension",
	className : 'text-center',
	sortable : false
}, {
	label : "类别",
	field : "itemCategory",
	sortable : false
}, {
	label : "入库数量",
	field : "itemCountActual",
	sortable : false
}, {
	label : "",
	field : "blank",
	sortable : false
} ];

function doAudit() {
	standby.show();
	var _url = appRoot + "/restaurant/production/confirmSelf/doAudit.action";
	_url = getUrl(_url);

	grid.save();
	var rawRows = dataStore.query();
	dojo.byId('rawJsonData').value = JSON.stringify(rawRows);
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				closeTab(parentTabId);
				alert("审核成功！");
				standby.hide();
				closeTab(tabId);
			} else {
				standby.hide();
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}