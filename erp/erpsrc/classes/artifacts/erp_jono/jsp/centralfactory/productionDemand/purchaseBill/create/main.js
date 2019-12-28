require([ "dojo/ready" ], function(ready) {
	ready(function() {
		initGlobal();
		addEvent();
		initGrid();
	});
});

/**
 * 初始化全局函数
 */
function initGlobal() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
	});
}

function addEvent() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		query(".Wdate").forEach(function(node) {
			node.onfocus = function(e) {
				WdatePicker();
			};
		});

		dom.byId('btn_submit').onclick = doSubmit;
	});
}

function doSubmit() {
	require([ "dojo/dom", "dojo/date", "dojo/date/locale" ], function(dom, date, locale) {
		// function doValidate() {
		// var receiveTime = dom.byId('receiveTime').value;
		// if (receiveTime.trim() == '') {
		// alert("到货日期不能为空！");
		// return false;
		// }
		// return true;
		// }

		function validateGrid() {
			if (!validateColumn(grid, 'realCount')) {
				return false;
			}
			return true;
		}

		var numReg = /^(-)?[0-9]{1,}\.{0,1}[0-9]{0,}$/g;
		function validateNum(_id, _text) {
			var value = dom.byId(_id).value.trim();
			if (value != '' && value.match(numReg) == null) {
				alert(_text + "只能为数字！");
				return false;
			}
			return true;
		}

		if (!validateGrid()) {
			return;
		}
		// if (!doValidate()) {
		// return;
		// }
		// /**
		// * 到货日期不能小于制单日期
		// */
		// function checkReceiveTime() {
		// var parseOption = {
		// datePattern : "yyyy-MM-dd",
		// selector : "date"
		// };
		// var formTime = locale.parse(dom.byId('formTime').value,
		// parseOption);
		// var receiveTime = locale.parse(
		// dom.byId('receiveTime').value, parseOption);
		// var result = date
		// .compare(receiveTime, formTime, "date");
		//
		// if (result < 0) {
		// alert('到货日期不能提前!');
		// return false;
		// }
		// return true;
		// }
		// if (!checkReceiveTime()) {
		// return;
		// }

		grid.save();
		var rows = dataStore.query();
		if (rows.length == 0) {
			alert("请选择原料！");
			return;
		}
		dom.byId('jsonData').value = JSON.stringify(rows);

		var _url = appRoot + '/centralfactory/productionDemand/purchaseBill/create/checkView.action';
		_url = getUrl(_url);
		
		addPostTab('billForm', '原料采购单提交', _url);
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/Observable", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor" ], function(OnDemandGrid, selector, Selection,
			Observable, Memory, declare, Keyboard, editor) {
		execute(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor);
	});

	function execute(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor) {
		dataStore = new Observable(new Memory({
			// data : gridData
			idProperty : "rownumber",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(editor, selector),
			cellNavigation : false,
			selectionMode : "toggle",
			allowSelectAll : true,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'orderCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
				dataStore.put(row);
			}
		});

		grid.startup();
	}
}

function loadData(data) {
	require([ "dojo/dom", "dojo/_base/array" ], function(dom, array) {
		// var sellPredict = dom.byId('sellPredict').value;
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			var inventory = row.inventory;
			if (inventory == undefined) {
				inventory = 0;
				row.inventory = 0;
			}
			// // 万元用量平均值*预估销售额-库存量=建议值
			// if (row.amtTTCNY1 != undefined) {
			// var requireCount1 = parseFloat((row.amtTTCNY1 * sellPredict)
			// .toFixed(2));
			// row.requireCount1 = requireCount1;
			// row.suggestCount1 = getSuggest(requireCount1);
			// }
			// if (row.amtTTCNY2 != undefined) {
			// var requireCount2 = parseFloat((row.amtTTCNY2 * sellPredict)
			// .toFixed(2));
			// row.requireCount2 = requireCount2;
			// row.suggestCount2 = getSuggest(requireCount2);
			// }
			// if (row.amtTTCNY3 != undefined) {
			// var requireCount3 = parseFloat((row.amtTTCNY3 * sellPredict)
			// .toFixed(2));
			// row.requireCount3 = requireCount3;
			// row.suggestCount3 = getSuggest(requireCount3);
			// }

			// function getSuggest(_require) {
			// var suggest = parseFloat((_require - inventory).toFixed(2));
			// if (suggest < 0) {
			// return 0;
			// }
			// return suggest;
			// }

			if (row.orderCount == undefined) {
				row.orderCount = 0;
			}
			if (row.payAmt == undefined) {
				row.payAmt = 0;
			}
			dataStore.put(row);
		});
	});
}

function getColumn(editor, selector) {
	return [ selector({
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber"
	}, {
		label : "原料编码",
		field : "itemId"
	}, {
		label : "原料名称",
		field : "itemName"
	}, {
		label : "类别",
		field : "itemCategory"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}, {
		label : '库存量',
		field : 'inventory',
		className : 'grid-number'
	}, {
		label : '在途',
		field : 'onTheWay',
		className : 'grid-number'
	}, {
		label : '安全库存',
		field : 'safeStorage',
		className : 'grid-number'
	}, {
		label : '计划生产量',
		field : 'itemCount',
		className : 'grid-number'
	}, editor({
		label : "实际生产量",
		field : "realCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
			constraints : {
				min : 0,
				max : 99550,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		}
	}, NumberTextBox), {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number'
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number'
	}, {
		label : "",
		field : "none"
	} ];
}

function delMaterial() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		selectArr.push(itemId);
		dataStore.remove(itemId);
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
}

var materialDlg = null;
var selectedRows = [];
function selMaterial() {
	selectedRows = dataStore.query();// 初始化被选择的记录
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/selmaterial/mainView.action";
		_url = getUrl(_url);
		
		var option = {
			title : "自选原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		materialDlg = createDialog(option);
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.loadData();
		materialDlg.show();
	}
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}

var pickModelDlg = null;
function pickModel() {
	var frameId = 'ifr_pickModel';
	if (pickModelDlg == null) {
		var _url = appRoot + "/centralfactory/productionDemand/template/create/pickModelView.action?templateType=pw";
		_url = getUrl(_url);
		
		var option = {
			title : "使用模板",
			frameId : frameId,
			url : _url,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			pickModelDlg = iDlg;
		});
	} else {
		require([ "dojo/dom" ], function(dom) {
			var ifrWindow = dom.byId(frameId).contentWindow;
			ifrWindow.doQuery();
			pickModelDlg.show();
		});
	}
}

function closePickModelDlg(data) {
	loadData(data);
	pickModelDlg.hide();
}

var saveModelDlg = null;

/**
 * 获取主界面的数据，传递到子窗口保存为模板
 */
function getSaveModelInfo() {
	var rows = dataStore.query();
	var $shopId = dojo.byId('shopId');
	var result = {
		items : rows,
		branchId : $shopId.value,
		branchName : $shopId.options[$shopId.selectedIndex].text
	};
	return result;
}

function closeSaveModelDlg(data) {
	saveModelDlg.hide();
}
