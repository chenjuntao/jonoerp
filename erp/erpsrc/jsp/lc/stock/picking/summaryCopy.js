require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		doQuery();
	});
});
function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;
var branchIdArr = [];

function validateGrid() {
	for (var i = 0; i < branchIdArr.length; i++) {
		if (!validateColumn(grid, branchIdArr[i])) {
			return false;
		}
	}
	return true;
}

function doSubmit() {
	standby.show();
	// if (!validateGrid()) {
	// return;
	// }
	grid.save();
	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
		var rows = dataStore.query();
		dom.byId('jsonData').value = JSON.stringify(rows);

		//保存仓库名称
		var $storageId = dojo.byId('storageId');
		dojo.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;
		
		var _url = appRoot + "/lc/stock/picking/createPickingBill.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			if (data.msg == 'ok') {
				alert("生成单据号为：" + data.formId + "，保存成功！");
				closeTab(tabId, 'doQuery');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId);
}

function doQuery() {
	var _url = appRoot + "/lc/stock/picking/querySummary.action";
	_url = getUrl(_url);
	
	require([ "dojo/dom", "dojo/request/xhr", "dojo/_base/array", "dgrid/editor", "dijit/form/NumberTextBox" ],
			function(dom, xhr, array, editor, NumberTextBox) {
				xhr.post(_url, {
					handleAs : "json",
					data : {
						ids : dom.byId('ids').value
					}
				}).then(function(data) {
					branchIdArr = data.branchIds;
					dom.byId('branchIds').value = data.branchIds.join(',');
					array.forEach(data.branchCols, function(bCol, i) {
						bCol.editorArgs = {
							style : 'width: 5em;',
							constraints : {
								min : 0,
								max : 1999999999,
								places : '0,3'
							},
							required : true,
							invalidMessage : '请输入不多于三位小数的数字。'
						};
						// editor(bCol, NumberTextBox);
					});
					data.branchCols.push({
						'label' : '',
						'field' : 'none'
					})
					initGrid(data);
				}, function(err) {
				});
			});
}

var grid = null;
var dataStore = null;
function initGrid(_data) {
	require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dgrid/selector", "dojo/_base/declare", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory" ,"dojox/widget/Standby","dgrid/extensions/ColumnResizer"], function(OnDemandGrid, Selection,
			selector, declare, Server, Observable, Cache, Memory,Standby,ColumnResizer) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : _data.itemLst
		}));
		var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer ]);
		var cols = getColumn().concat(_data.branchCols);
		grid = new CustomGrid({
			store : dataStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
		// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});
		
		document.body.appendChild(standby.domNode);
		standby.startup();
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "库位",
		field : "shelfName",
		sortable:false
	}, {
		label : "类别",
		field : "categoryName",
		sortable:false
	}, {
		label : "原料编码",
		field : "itemId",
		sortable:false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable:false
	}, {
		label : "箱子类型",
		field : "boxType",
		formatter : boxFmt,
		sortable:false
	}, {
		label : "包装单位",
		field : "deliveryUnit",
		sortable:false
	}, {
		label : "单位体积",
		field : "unitVolume",
		className : 'grid-number',
		sortable:false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable:false
	} ];
}

function boxFmt(value, rowData) {
	if (value == '10') {
		return '非冷藏';
	}
	return '冷藏';
}