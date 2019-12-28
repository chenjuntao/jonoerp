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
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
}

// 导入商品
function importMaterial() {

	var fileurl = dojo.byId("fileurl").value;
	if (fileurl == "") {
		alert("请选择文件！");
		return;
	}

	hide();

	var _url = appRoot + "/hq/priceadjust/selitem/doImport.action?priceType=" + g_adjustType;
	
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

var dataStore = null;
var grid = null;
function initGrid() {
	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore", "dojo/_base/declare",
			"dojo/parser", "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dgrid/editor",
			"dijit/form/NumberTextBox", "dijit/Dialog", "dojo/_base/lang" ], function(Observable, Memory, ObjectStore,
			declare, parser, OnDemandGrid, selector, Selection, editor, NumberTextBox, Dialog, lang) {
		parser.parse();
		dataStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(selector, editor, NumberTextBox, lang),
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

var numArgs = {
	style : 'width: 5em;',
	constraints : {
		min : 0,
		max : 99550,
		places : '0,3'
	},
	required : true,
	invalidMessage : '请输入不多于三位小数的数字。'
};

function getColumn(selector, editor, NumberTextBox, lang) {
	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox, 'click');
	}
	return [ selector({
		field : 'rownumber',
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "商品编码",
		field : "itemId",
		sortable : false
	}, {
		label : "商品名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		},
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
		label : "包装因子",
		field : "itemPackager",
		sortable : false
	}, {
		label : "原价",
		field : "oldPrice",
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "新价",
		field : "newPrice",
		sortable : false
	}), {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var itemDlg = null;
function addItem() {
	var _url = appRoot + "/hq/priceadjust/selitem/mainView.action";
	_url += "?adjustType=" + dojo.byId("adjustType").value + "&itemType=R_S";
	
	var frameId = 'ifr_selItem';
	if (itemDlg == null) {
		var option = {
			title : "选择商品",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			itemDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		itemDlg.show();
	}
}

function closeItemDlg(data, selType) {
	require([ "dojo/_base/array" ], function(array) {
		var isRepeat = array.some(data, function(item, i) {
			var _itemId = item.itemId;
			var rows = dataStore.query();
			return array.some(rows, function(row, i) {
				if (row.itemId == _itemId) {
					alert(row.itemName + "已存在！");
					return true;
				}
			});
		});

		if (!isRepeat) {
			addData(data);
			itemDlg.hide();
		}
	});
}

function addData(data) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = dataStore.query().length + 1;
			dataStore.put(row);
		});
	});
}

function delItem() {
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

function doValidate() {
	return true;
}

function doSave() {
	standby.show();

	if (!doValidate()) {
		return;
	}
	grid.save().then(function(value) {
		var rows = dataStore.query();
		dojo.byId('jsonData').value = JSON.stringify(rows);

		var _url = appRoot + "/hq/priceadjust/createBill.action";

		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				standby.hide();

				if (data.msg == 'ok') {
					alert("提交成功！");
					doClose();
				} else {
					alert("操作失败！");
				}
			}, function(err) {
				standby.hide();
			});
		});
	}, function(err) {
		console.log(err);
	}, function(update) {
		console.log(update);
	});
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
