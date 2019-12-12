require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		doQuery();
	});
});

var cols = [ {
	label : "小计",
	field : "sum",
	className : 'grid-number',
	sortable : false
} ];

var grid = null;
var dataStore = null;
var branchIdArr = [];

function doSubmit() {
	standby.show();
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

function getAppendValue() {
	var appendValue = '';
	
	if (!isEmpty(dojo.byId("itemText").value)) {
		itemText = dojo.byId("itemText").value;
	} else {
		itemText = '';
	}
	
	if (!isEmpty(itemText)) {
		appendValue += "  AND d.ITEM_ID like '%" + itemText + "%' ";
	}
	return appendValue;
}

function doQuery() {
	var _url = appRoot + "/lc/stock/picking/doQuerySummary.action";
	_url = getUrl(_url);
	
	require([ "dojo/dom", "dojo/request/xhr", "dojo/_base/array", "dgrid/editor", "dijit/form/NumberTextBox" ],
			function(dom, xhr, array, editor, NumberTextBox) {
				xhr.post(_url, {
					handleAs : "json",
					data : {
						ids : dom.byId('ids').value
					}
				}).then(function(data) {
					initGrid(data);
				}, function(err) {
				});
			});
}

var grid = null;
function initGrid(_data) {
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/parser", "dojo/_base/declare", "dgrid/extensions/Pagination", "dgrid/extensions/ColumnResizer","dojox/widget/Standby",
			"dgrid/ColumnSet", "dojo/dom-form","dojo/_base/array", "dojo/domReady!" ], function(Grid, Server, Observable, Cache, Memory,
			parser, declare, Pagination, ColumnResizer, Standby,ColumnSet, domForm,array) {
		parser.parse();
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : _data.headerLst
		}));
		var CustomGrid = declare([ Grid, Pagination, ColumnResizer, ColumnSet ]);

		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.startup();
		array.forEach(_data.branchLst, function(item) {
			cols.unshift({
				label : item.branch_name,
				field : item.branch_id,
				className : 'grid-number',
				sortable : false
			});
		});

		grid.set('columnSets', getColumn());
		
		standby = new Standby({
			target : "billForm"
		});
		
		document.body.appendChild(standby.domNode);
		standby.startup();
	});
}

function getColumn() {
	return [ [ [ {
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
		field : "item_name",
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
	}] ], [ cols ] ];
}

function boxFmt(value, rowData) {
	if (value == '10') {
		return '非冷藏';
	}
	return '冷藏';
}