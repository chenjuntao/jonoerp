var getBranchId = null, getRegionId = null;
var calcSuggest = null;
/**
 * 计算建议值时速度比较慢，显示遮罩层
 */
var standby = null;
var grid = null;
var dataStore = null;

/**
 * 在grid中增加原料记录的函数
 */
var addData = null;

/**
 * function
 */
var setArrivePeriod = null;

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

function getPickBranchId() {
	return dojo.byId('shopId').value;
}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

require([ "dojo", "dojo/query", "dojo/_base/array", "dojo/dom", "dojo/date", "dojo/date/locale", "dojo/request/xhr",
		"dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/Observable", "dojo/store/Memory",
		"dojo/_base/lang", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/ColumnSet", "dgrid/editor",
		"dijit/form/NumberTextBox", "dojox/widget/Standby", "dojo/on", "dojo/keys", "dgrid/extensions/ColumnResizer",
		"dojo/parser", "dijit/Dialog", "dojo/ready" ], function(dojo, query, array, dom, date, locale, xhr,
		OnDemandGrid, selector, Selection, Observable, Memory, lang, declare, Keyboard, ColumnSet, editor,
		NumberTextBox, Standby, on, keys, ColumnResizer, parser, Dialog, ready) {
	ready(function() {
		parser.parse();

		doClean();
		setFormTime();
		calcSellPredict();
		addEvent();
		initGrid();
	});

	getBranchId = function() {// 用于传递到子窗口使用
		return dom.byId("shopId").value;
	};
	getRegionId = function() {
		return dom.byId("regionId").value;
	};

	function doClean() {
		dom.byId('arrivePeriod').value = '0';// 到货周期初始化库零
	}

	// 相关节点事件注册
	function addEvent() {
		// 日期控件添加事件
		query(".Wdate").forEach(function(node) {
			node.onfocus = function(e) {
				WdatePicker();
			};
		});

		// 切换门店时获取对应门店的营业日期
		dom.byId('shopId').onchange = setFormTime;

		// #delayPredict 延滞期金额
		// #purchasePredict 进货周期金额
		// #safetyStock 安全存量
		// 三者之一发生变动就会重新计算预估销售额
		query('#delayPredict, #purchasePredict, #safetyStock').forEach(function(node) {
			node.onchange = calcSellPredict;
		});

		// 提交按钮事件注册
		dom.byId('btn_submit').onclick = doSubmit;
	}

	// 设定操作时间
	function setFormTime() {
		var _url = appRoot + "/common/queryBusinessDate.action";
		var _url2 = appRoot + "/common/getRegionByBranch.action";
		_url = getUrl(_url);
		_url2 = getUrl(_url2);
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dom.byId('shopId').value
			}
		}).then(function(data) {
			dom.byId('formTime').value = data.businessDate;
			dom.byId('formTimeText').innerHTML = data.businessDate;
			dom.byId('receiveTimeText').innerHTML = data.businessDate;
			dom.byId('receiveTime').value = data.businessDate;
		}, function(err) {
		});

		xhr.post(_url2, {
			handleAs : "json",
			data : {
				branchId : dom.byId('shopId').value
			}
		}).then(function(data) {
			dom.byId('regionId').value = data.regionId;
		}, function(err) {
		});
	}

	// 计算预估销售额
	function calcSellPredict() {
		// 内部函数 闭包
		function parseValue(_id) {
			var value = dom.byId(_id).value;

			// 如果节点的值为空，则默认为零
			if (value == '') {
				value = 0;
			} else {
				value = parseFloat(value);
			}
			return value;
		}

		// 预估销售额
		var sellPredict = parseValue('delayPredict') + parseValue('purchasePredict') + parseValue('safetyStock');

		// 表单元素值
		dom.byId('sellPredict').value = sellPredict;

		// 仅供显示使用
		dom.byId('sellPredictText').innerHTML = sellPredict;
	}

	// 初始化遮罩层
	standby = new Standby({
		target : "billForm"
	});

	document.body.appendChild(standby.domNode);
	standby.startup();

	// 计算建议值
	calcSuggest = function() {
		var refDateStart1 = dom.byId('refDateStart1').value;
		var refDateEnd1 = dom.byId('refDateEnd1').value;
		var refDateStart2 = dom.byId('refDateStart2').value;
		var refDateEnd2 = dom.byId('refDateEnd2').value;
		var refDateStart3 = dom.byId('refDateStart3').value;
		var refDateEnd3 = dom.byId('refDateEnd3').value;

		// 将对一组字符串的判断转换为对一个数字的判断
		var count = 0;
		if (refDateStart1 != '' && refDateEnd1 != '') {
			count++;
		}
		if (refDateStart2 != '' && refDateEnd2 != '') {
			count++;
		}
		if (refDateStart3 != '' && refDateEnd3 != '') {
			count++;
		}

		grid.save().then(function(value) {
			if (count == 0) {
				alert('请先选择一组参考日期！');
				return;
			}

			// 若选择的原料为空，则给予提示
			var rows = dataStore.query();
			if (rows.length == 0) {
				alert("请选择原料！");
				return;
			}

			// 显示遮罩层
			standby.show();

			var _url = appRoot + "/restaurant/goodsbill/create/calcSuggest.action";
			_url = getUrl(_url);

			xhr.post(_url, {
				handleAs : "json",
				data : {
					branchId : dom.byId('shopId').value,
					jsonData : JSON.stringify(dataStore.query()),// dataStore.query()
					// 所选原料
					refDateStart1 : refDateStart1,
					refDateEnd1 : refDateEnd1,
					refDateStart2 : refDateStart2,
					refDateEnd2 : refDateEnd2,
					refDateStart3 : refDateStart3,
					refDateEnd3 : refDateEnd3
				}
			}).then(function(data) {
				reloadData(data);

				// 计算建议值完毕，隐藏遮罩层
				standby.hide();
			}, function(err) {
			});

		}, function(err) {
			console.log(err);
		});

	}

	function doValidate() {
		if (!validateNum('delayPredict', '延滞期金额')) {
			return false;
		}
		if (!validateNum('purchasePredict', '进货周期金额')) {
			return false;
		}
		if (!validateNum('safetyStock', '安全存量')) {
			return false;
		}

		var sellPredict = dom.byId('sellPredict').value;
		if (sellPredict == 0) {
			alert("预估销售额必须有数值！");
			return false;
		}

		var receiveTime = dom.byId('receiveTime').value;

		if (receiveTime.length == 0) {
			alert("请先选择到货日期！");
			return false;

		}

		return true;
	}

	/**
	 * 到货日期不能小于制单日期
	 */
	function checkReceiveTime() {
		var _arrivePeriod = dom.byId('arrivePeriod').value;
		var parseOption = {
			datePattern : "yyyy-MM-dd",
			selector : "date"
		};
		var formTime = locale.parse(dom.byId('formTime').value, parseOption);
		var receiveTime = locale.parse(dom.byId('receiveTime').value, parseOption);
		var result = date.compare(receiveTime, formTime, "date");

		if (result < 0) {
			alert('到货日期不能提前!');
			return false;
		}
		return true;
	}

	function doSubmit() {
		if (!validateGrid()) {
			return;
		}
		if (!doValidate()) {
			return;
		}

		if (!checkReceiveTime()) {
			return;
		}

		grid.save().then(function(value) {
			var rows = dataStore.query();
			if (rows.length == 0) {
				alert("请选择原料！");
				return;
			}

			var rowsSave = [];
			if (rows.length > 0) {

				for (var i = 0; i < rows.length; i++) {
					rows[i].payAmt = parseFloat((rows[i].orderCount * rows[i].itemUnitPrice).toFixed(4)); // 计算金额
					rowsSave.push(rows[i]);
				}
			}

			dom.byId('jsonData').value = JSON.stringify(rowsSave);
			var $shopId = dom.byId('shopId');
			dom.byId('shopName').value = $shopId.options[$shopId.selectedIndex].text;

			var _url = appRoot + '/restaurant/goodsbill/create/commitView.action?parentTabId=' + tabId;
			_url = getUrl(_url);

			addPostTab('billForm', '餐厅要货单提交', _url);
		}, function(err) {
			console.log(err);
		});
	}

	function initGrid() {
		dataStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(),
			selectionMode : "none",
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

		grid.startup();
	}

	addData = function(data) {
		var sellPredict = dom.byId('sellPredict').value;
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			var inventory = row.inventory;
			if (inventory == undefined) {
				inventory = 0;
				row.inventory = 0;
			}
			// 万元用量*预估销售额-库存量=建议值
			if (row.amtTTCNY1 != undefined) {
				var requireCount1 = parseFloat((row.amtTTCNY1 * sellPredict).toFixed(2));
				row.requireCount1 = requireCount1;
				row.suggestCount1 = getSuggest(requireCount1);
			}
			if (row.amtTTCNY2 != undefined) {
				var requireCount2 = parseFloat((row.amtTTCNY2 * sellPredict).toFixed(2));
				row.requireCount2 = requireCount2;
				row.suggestCount2 = getSuggest(requireCount2);
			}
			if (row.amtTTCNY3 != undefined) {
				var requireCount3 = parseFloat((row.amtTTCNY3 * sellPredict).toFixed(2));
				row.requireCount3 = requireCount3;
				row.suggestCount3 = getSuggest(requireCount3);
			}

			function getSuggest(_require) {
				var suggest = parseFloat((_require - inventory).toFixed(2));
				if (suggest < 0) {
					return 0;
				}
				return suggest;
			}

			if (row.orderCount == undefined) {
				row.orderCount = 0;
			}
			if (row.itemUnitPrice == undefined) {
				row.itemUnitPrice = 0;
			}
			if (row.payAmt == undefined) {
				row.payAmt = 0;
			}
			dataStore.put(row);
		});
	}

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
	function getColumn() {
		return [ [ [ selector({
			field : 'rownumber',
			sortable : false
		}), {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "原料编码",
			field : "itemId",
			sortable : false,
			className : 'text-center'
		}, {
			label : "原料名称",
			field : "itemName",
			sortable : false
		} ] ], [ [ lang.mixin(getNumEditor(), {
			label : "库存量",
			field : "inventory",
			sortable : false,
			className : 'text-right'
		}), {
			label : "规格",
			field : "itemSpecification",
			sortable : false
		}, lang.mixin(getNumEditor(), {
			label : "订货量",
			field : "orderCount",
			sortable : false,
			className : 'text-right'
		}), {
			label : "单位",
			field : "itemDimension",
			sortable : false,
			className : 'text-center'
		}, {
			label : "类别",
			field : "itemCategory",
			sortable : false
		}, {
			label : branchType == 'FRANCHISEE' ? "加盟价" : "标准价",
			field : "itemUnitPrice",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "建议1",
			field : "suggestCount1",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "建议2",
			field : "suggestCount2",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "建议3",
			field : "suggestCount3",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "需求1",
			field : "requireCount1",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "需求2",
			field : "requireCount2",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "需求3",
			field : "requireCount3",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "万用1",
			field : "amtTTCNY1",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "万用2",
			field : "amtTTCNY2",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "万用3",
			field : "amtTTCNY3",
			className : 'grid-number',
			sortable : false,
			className : 'text-right'
		}, {
			label : "",
			field : "none",
			sortable : false
		} ] ] ];
	}

	/**
	 * 设置到货周期，到货日期默认值为制单日期加上到货周期，以及配送方式
	 */
	setArrivePeriod = function(_arrivePeriod, _deliveryType) {
		dom.byId('arrivePeriodText').innerHTML = _arrivePeriod;
		dom.byId('arrivePeriod').value = _arrivePeriod;
		var parseOption = {
			datePattern : "yyyy-MM-dd",
			selector : "date"
		};
		var formTime = locale.parse(dom.byId('formTime').value, parseOption);
		var receiveTime = date.add(formTime, "day", _arrivePeriod);
		dom.byId('receiveTimeText').innerHTML = locale.format(receiveTime, parseOption);
		dom.byId('receiveTime').value = locale.format(receiveTime, parseOption);

		dom.byId('deliveryType').value = _deliveryType;
	}

});

// 检验数据正确性
function validateGrid() {
	if (!validateColumn(grid, 'inventory')) {
		return false;
	}
	if (!validateColumn(grid, 'orderCount')) {
		return false;
	}
	return true;
}

// 装载数据 计算建议值 与 选择模板 会调用该方法
function reloadData(data) {
	// 首先清空原料数据
	dataStore.setData([]);
	grid.set('store', dataStore);

	addData(data);
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

var materialDlg = null;
var selectedRows = [];

// 添加原料
function addMaterial() {
	selectedRows = dataStore.query();// 初始化被选择的记录
	var frameId = 'ifr_selMaterial';
	var _url = appRoot + "/restaurant/goodsbill/selmaterial/mainView.action";
	_url = getUrl(_url);

	// 添加原料 Dialog
	if (materialDlg == null) {
		var option = {
			title : "增加原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}

		createDialog(option, function(iDlg) {
			materialDlg = iDlg;
		});
	} else {
		// 若 Dialog已存在，则直接显示
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location.url = _url;
		materialDlg.show();
	}
}

function closeMaterialDlg(data) {
	addData(data);
	materialDlg.hide();
}

var pickModelDlg = null;
function pickModel() {
	var frameId = 'ifr_pickModel';

	if (pickModelDlg == null) {
		var _url = appRoot
				+ "/restaurant/goodsbill/template/pickModelView.action?templateType=request&templateOwner=both";
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
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.doQuery();
		pickModelDlg.show();
	}
}

function closePickModelDlg(data, _arrivePeriod, _deliveryType) {
	setArrivePeriod(_arrivePeriod, _deliveryType);
	reloadData(data);
	pickModelDlg.hide();
}

var saveModelDlg = null;
function saveAsModel() {
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}
	if (saveModelDlg == null) {
		var _url = appRoot + "/restaurant/common/template/saveModelView.action?templateType=request&templateOwner=both";
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
		ifrWindow.loadData();
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

function closeSaveModelDlg(data) {
	saveModelDlg.hide();
}

function doClose() {
	closeTab(tabId);
}
