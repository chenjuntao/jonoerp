require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGlobal();
		addEvent();
		initGrid();
	});
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
	dojo.byId('btn_submit').onclick = doSubmit;
}

// 导入原料
function importMaterial() {

	var fileurl = dojo.byId("fileurl").value;
	if (fileurl == "") {
		alert("请选择文件！");
		return;
	}

	hide();

	var _url = appRoot + "/restaurant/goodsbill/create/doImport.action";
	_url = getUrl(_url);

	require([ "dojo/io/iframe", "dojox/widget/Standby" ], function(ioIframe, Standby) {
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
		standby.show();

		ioIframe.send({
			form : "uploadForm",
			url : _url,
			handleAs : "json"
		}).then(function(data) {
			reloadData(data.existLst);
			standby.hide();
		}, function(err) {
			standby.hide();
			alert(err);
		});
	});

}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

/**
 * 初始化全局函数
 */
function initGlobal() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		window.getBranchId = function() {// 用于传递到子窗口使用
			return lcCode; // 模板制作部门，物流中心
		};
		window.getRegionId = function() {// 用于传递到子窗口使用
			return 'r03'; // 这里是获取区域模板，外部订货方
		};
	});
}

function doSubmit() {
	grid.save();
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}

	var rowsSave = [];
	if (rows.length > 0) {
		var zeroItem = [];
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].orderCount == 0) {
				zeroItem.push(rows[i].itemName);
			} else {
				rows[i].payAmt = parseFloat((rows[i].orderCount * rows[i].itemUnitPrice).toFixed(4)); // 计算金额
				rowsSave.push(rows[i]);
			}
		}
		if (rowsSave.length == 0) {
			alert("不允许所有物料的订货量为零！");
			return;
		}
		if (zeroItem.length > 0) {
			if (!confirm(zeroItem.join('、') + "输入为零，确定提交吗？")) {
				return;
			}
		}
	}
	require([ "dojo/dom", "dojo/date", "dojo/date/locale" ], function(dom, date, locale) {
		/**
		 * 到货日期不能小于制单日期加上到货周期
		 */
		function checkReceiveTime() {
			var _arrivePeriod = dom.byId('arrivePeriod').value;
			var parseOption = {
				datePattern : "yyyy-MM-dd",
				selector : "date"
			};
			var formTime = locale.parse(dom.byId('formTime').value, parseOption);
			var receiveTime = locale.parse(dom.byId('receiveTime').value, parseOption);
			var receiveTime2 = date.add(formTime, "day", parseInt(_arrivePeriod));
			var result = date.compare(receiveTime, receiveTime2, "date");

			if (result < 0) {
				alert('到货日期不能提前!');
				return false;
			}
			return true;
		}
		if (!checkReceiveTime()) {
			return;
		}
		dom.byId('jsonData').value = JSON.stringify(rows);
		var $buyerId = dom.byId('buyerId');
		dom.byId('buyerName').value = $buyerId.options[$buyerId.selectedIndex].text;

		var _url = appRoot + '/outerorder/create/checkView.action?parentTabId=' + tabId;
		_url = getUrl(_url);

		addPostTab('billForm', '外部订货单提交', _url);
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/Observable", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dijit/Dialog", "dojo/parser",
			"dgrid/extensions/ColumnResizer", "dijit/form/NumberTextBox", "dojo/_base/lang", "dojo/on", "dojo/keys",
			"dojo/query" ], function(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor,
			Dialog, parser, ColumnResizer, NumberTextBox, lang, on, keys, query) {
		execute(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor, Dialog, parser,
				ColumnResizer, NumberTextBox, lang, on, keys, query);
	});

	function execute(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor, Dialog, parser,
			ColumnResizer, NumberTextBox, lang, on, keys, query) {
		parser.parse();

		dataStore = new Observable(new Memory({
			// data : gridData
			idProperty : "itemId",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(editor, selector, NumberTextBox, lang),
			cellNavigation : false,
			selectionMode : "none",
			allowSelectAll : true,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'orderCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(4)); // 计算金额
				dataStore.put(row);
			}
		});

		grid.startup();

		// TODO
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
			if (e.parentType != undefined) { // 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});

	}
}

function reloadData(data) {
	dataStore.setData([]);
	grid.set('store', dataStore);
	addData(data);
}

function addData(data) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			var inventory = row.inventory;
			if (inventory == undefined) {
				inventory = 0;
				row.inventory = 0;
			}

			if (row.orderCount == undefined) {
				row.orderCount = 0;
			}
			if (row.payAmt == undefined) {
				row.payAmt = 0;
			}
			if (row.itemUnitPrice == undefined) {
				row.itemUnitPrice = 0;
			}
			dataStore.put(row);
		});
	});
}

function getColumn(editor, selector, NumberTextBox, lang) {
	var numArgs = {
		style : 'width: 5em;text-align: right;',
		constraints : {
			min : 0,
			max : 1999999999,
			places : '0,3'
		},
		required : true,
		invalidMessage : '请输入不多于三位小数的数字。'
	};

	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox);
	}

	return [ selector({
		field : 'rownumber',
		sortable : false
	}), {
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
		field : "itemCategory",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "订货量",
		field : "orderCount",
		sortable : false
	}), {
		label : "零售单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	// }, {
	// label : "标准金额",
	// field : "payAmt",
	// className : 'grid-number',
	// sortable:false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function delMaterial() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
			dataStore.remove(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
}

var pickModelDlg = null;
function pickModel() {
	if (pickModelDlg == null) {
		var frameId = 'ifr_pickModel';
		// 获取公共的外部订货模板
		var _url = appRoot
				+ "/restaurant/goodsbill/template/pickModelView.action?templateType=outer&templateOwner=public";
		_url = getUrl(_url);

		var option = {
			title : "使用模板",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		pickModelDlg = createDialog(option);
	} else {
		pickModelDlg.show();
	}
}

function getPickBranchId() {
	return dojo.byId('buyerId').value;
}

function closePickModelDlg(data, _arrivePeriod) {
	setArrivePeriod(_arrivePeriod);
	reloadData(data);
	pickModelDlg.hide();
}

/**
 * 设置到货周期，到货日期默认值为制单日期加上到货周期
 * 
 * @param _arrivePeriod
 */
function setArrivePeriod(_arrivePeriod) {
	require([ "dojo/dom", "dojo/date", "dojo/date/locale" ], function(dom, date, locale) {
		dom.byId('arrivePeriodText').innerHTML = _arrivePeriod;
		dom.byId('arrivePeriod').value = _arrivePeriod;
		var parseOption = {
			datePattern : "yyyy-MM-dd",
			selector : "date"
		};
		var formTime = locale.parse(dom.byId('formTime').value, parseOption);
		var receiveTime = date.add(formTime, "day", _arrivePeriod);
		dom.byId('receiveTime').value = locale.format(receiveTime, parseOption);
		dom.byId('receiveTimeText').innerHTML = locale.format(receiveTime, parseOption);
	});
}

var saveModelDlg = null;
function saveAsModel() {
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}
	if (saveModelDlg == null) {
		var _url = appRoot + "/restaurant/goodsbill/template/saveModelView.action?templateType=request";
		_url = getUrl(_url);

		var option = {
			title : "存为模板",
			url : _url,
			frameId : 'saveAsModel',
			width : "850px",
			height : "300px"
		}
		saveModelDlg = createDialog(option);
	} else {
		var ifrWindow = dojo.byId("saveAsModel").contentWindow;
		ifrWindow.loadData()
		saveModelDlg.show();
	}
}

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

function refreshAddress() {
	var _url = appRoot + "/outerorder/create/queryAddress.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				buyerId : dojo.byId('buyerId').value
			}
		}).then(function(data) {
			dojo.byId('buyerAddress').value = data.msg;
			dojo.byId('buyerAddressText').innerHTML = data.msg;
		}, function(err) {
		});
	});
}

function closeSaveModelDlg(data) {
	saveModelDlg.hide();
}

function doClose() {
	closeTab(tabId);
}
