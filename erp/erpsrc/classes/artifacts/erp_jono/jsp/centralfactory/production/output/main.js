require([ "dojo/store/Memory", "dojo/store/Observable", "dojo/domReady!" ], function(Memory, Observable) {
	myStore = new Observable(new Memory({
		idProperty : 'rownumber',
		data : []
	}));
	requestData();
});

function doQuery() {
	requestData();
}

var grid = null;
var myStore = null;

function requestData() {
	var _url = appRoot + "/cf/requisition/create/doQuery.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/_base/array", "dojo/dom-form" ], function(xhr, Memory,
			array, domForm) {

		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				row.requestCount = row.itemCount;
			});
			myStore.setData(data);

			if (!isEmpty(grid)) {
				grid.set('store', myStore);
			} else {
				initGrid();
			}
		}, function(err) {
		});
	});
}

function getQuery() {
	return {
		itemName : document.getElementById('itemName').value.trim(),
		startDate : document.getElementById('startDate').value,
		queryStr : document.getElementById('queryStr').value,
		endDate : document.getElementById('endDate').value
	}
}

function initGrid() {
	require([ "dojo/_base/array", "dojo/_base/declare", "dgrid/Selection", "dgrid/selector", "dgrid/OnDemandGrid",
			"custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/extensions/ColumnResizer", "dijit/form/NumberTextBox", "dgrid/editor", "dojo/on", "dojo/keys",
			"dojo/query", "dojox/widget/Standby", "dgrid/Keyboard" ], function(arrayUtil, declare, Selection, selector,
			OnDemandGrid, Server, Observable, Cache, Memory, ColumnResizer, NumberTextBox, editor, on, keys, query,
			Standby, Keyboard) {

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard, ColumnResizer ]);

		grid = new CustomGrid({
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			store : myStore,
			columns : getColumn(editor, selector, NumberTextBox),
			cellNavigation : false,
			allowSelectAll : true,
			selectionMode : "none",
			loadingMessage : '加载中...'
		}, "requisitionGrid");

		grid.startup();

		// 初始化遮罩层
		standby = new Standby({
			target : "hovertable"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();

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

	});
}

function doTHScan(row) {
	doReturn(row.formId)
}
function doReturn(formId) {
	var _url = appRoot + "/cf/production/output/returnView.action?formId=" + formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addTab("退料", _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑或审核中！");
		return false;
	}

	if (status == "未审核") {
		alert("超领单已生成！");
		return false;
	}

	return true;
}

function putinStorage() {
	// if (!validateGrid()) {
	// return;
	// }
	var selectArr = [];
	for ( var formId in this.grid.selection) {
		if (this.grid.selection[formId]) {
			selectArr.push(myStore.get(formId));
		}
	}
	if (selectArr.length == 0) {
		alert("请勾选至少一条产品入库单！");
		return;
	}
	standby.show();
	dojo.byId('jsonData').value = JSON.stringify(selectArr);

	var _url = appRoot + "/cf/production/output/putinStorage.action";
	_url = getUrl(_url);

	grid.save().then(function(value) {
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					jsonData : JSON.stringify(selectArr)
				}
			}).then(function(data) {
				standby.hide();
				if (data.msg == 'ok') {
					alert("入库单生成成功！");
				} else {
					alert("入库单生成失败！");
				}
			}, function(err) {
			});
		});
	}, function(err) {
		console.log(err);
	});
}

function fillBill(row) {
	if (!checkStatus(row.formId)) {
		return;
	}

	var _url = appRoot + "/cf/production/output/editView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addTab("产品入库", _url);
}

function doMyScan(row) {
	doDetailScan(row.formId)
}

function validateGrid() {
	if (!validateColumn(grid, 'itemCount')) {
		return false;
	}
	return true;
}

function getColumn(editor, selector, NumberTextBox) {
	return [ selector({
		label : "",
		field : 'none',
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	// },{
	// label : "生成情况",
	// field : "count",
	// formatter : function(count){
	// return count == 0?'未生成': "<font color='red'>已生成</font>";
	// },
	// sortable:false
	}, {
		label : '单据编号',
		field : 'formId',
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doMyScan, object);
		}
	}, editor({
		label : "入库(包装)数量",
		field : "itemCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align:right',
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
		label : "商品编码",
		field : "itemId",
		sortable : true
	}, {
		label : "商品名称",
		field : "itemName",
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
		label : "生产数量",
		field : "requestCount",
		sortable : false
	}, {
		label : "生产车间",
		field : "workshop",
		sortable : true
	}, {
		label : "制单人员",
		field : "formMaker",
		sortable : false
	}, {
		label : "制单日期",
		field : "formTime",
		sortable : false
	}, {
		label : "退料",
		field : "return",
		renderCell : function(object, data) {
			return hrefFmtId("退料", doTHScan, object, "return" + object.rownumber);
		},
		sortable : false
	} ];
};
