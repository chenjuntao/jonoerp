require([ "dojo", "dojo/ready", "dojox/widget/Standby" ], function(dojo, ready, Standby) {
	ready(function() {
		addEvent();
		initGrid();

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

function addEvent() {
}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

function doClean() {
	dataStore.setData('');// 生成成功后清空数据
	grid.set("store", dataStore);
}

/**
 * 在grid中增加原料记录的函数
 */
var addData = null;

function validateGrid() {
	if (!validateColumn(grid, 'itemCount')) {
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
	grid.save().then(function(value) {
		var rows = dataStore.query();
		if (rows.length == 0) {
			alert("请选择原料！");
			return;
		}

		var providerName = [];

		var rowsSave = [];
		if (rows.length > 0) {
			var zeroItem = [];
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].itemCount == 0) {
					zeroItem.push(rows[i].itemName);
				} else {
					rows[i].payAmt = parseFloat((rows[i].itemCount * rows[i].itemUnitPrice).toFixed(2)); // 计算金额
					rows[i].receiveAmt = parseFloat((rows[i].itemCount * rows[i].receivePrice).toFixed(2)); // 计算金额
					rowsSave.push(rows[i]);
				}
				if (rows[i].provider == null) {
					providerName.push(rows[i].itemName);
				}
			}
			if (providerName.length != 0) {
				alert(providerName.join('、') + "供应商未设置，请先设置对应供应商");
				return;
			}
			if (rowsSave.length == 0) {
				alert("不允许所有物料的订货数为零！");
				return;
			}
			if (zeroItem.length > 0) {
				if (!confirm(zeroItem.join('、') + "输入为零，确定提交吗？")) {
					return;
				}
			}
		}
		dojo.byId('jsonData').value = JSON.stringify(rowsSave);
		var _url = appRoot + "/lc/request/purchase/manual/checkView.action?parentTabId=" + tabId;
		_url = getUrl(_url);
		
		addPostTab('billForm', '生成采购单', _url);
	}, function(err) {
		console.log(err);
	});
}

// 导入原料
function importMaterial() {

	var fileurl = dojo.byId("fileurl").value;
	if (fileurl == "") {
		alert("请选择文件！");
		return;
	}

	hide();

	var _url = appRoot + "/lc/request/purchase/manual/doImport.action";
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
// 装载数据
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

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dojo", "dojo/query", "dojo/_base/array", "dojo/dom", "dojo/date", "dojo/date/locale",
			"dojo/request/xhr", "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/Observable",
			"dojo/store/Memory", "dojo/_base/lang", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/ColumnSet",
			"dgrid/editor", "dijit/form/NumberTextBox", "dojox/widget/Standby", "dojo/on", "dojo/keys",
			"dgrid/extensions/ColumnResizer", "dojo/parser", "dijit/Dialog", "dojo/ready" ], function(dojo, query,
			array, dom, date, locale, xhr, OnDemandGrid, selector, Selection, Observable, Memory, lang, declare,
			Keyboard, ColumnSet, editor, NumberTextBox, Standby, on, keys, ColumnResizer, parser, Dialog, ready) {
		parser.parse();
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(editor, NumberTextBox, selector),
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			selectionMode : "none",
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.startup();

		addData = function(data) {
			array.forEach(data, function(row, i) {
				row.rownumber = i + 1;

				if (row.itemCount == undefined) {
					row.itemCount = 0;
				}
				if (row.itemUnitPrice == undefined) {
					row.itemUnitPrice = 0;
				}
				if (row.receivePrice == undefined) {
					row.receivePrice = 0;
				}
				dataStore.put(row);
			});
		}

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

function getColumn(editor, NumberTextBox, selector) {
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
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "类别编码",
		field : "itemCategory",
		sortable : false
	}, {
		label : "供应商",
		field : "provider",
		sortable : false
	}, {
		label : "标准价",
		field : "itemUnitPrice",
		sortable : false
	}, {
		label : "进货价",
		field : "receivePrice",
		sortable : false
	}, editor({
		label : "订货数",
		field : "itemCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3',
				row : this
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), /*
						 * { label : "金额", field : "payAmt", className :
						 * 'grid-number' },
						 */{
		label : "",
		field : "none",
		sortable : false
	} ];
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}
