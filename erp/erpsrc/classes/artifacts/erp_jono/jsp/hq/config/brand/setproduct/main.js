/**
 * 接收父窗口传回的数据
 * 
 * @param selRows
 */
var afterSelProduct = null;
/**
 *  初始化被选择的记录
 */
var getSelectedRows = null;

var delProduct = null;
var doSave = null;

require([ "dojo", "dojo/ready", "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory",
		"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/extensions/ColumnResizer",
		"dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/array" ], function(dojo, ready,
		OnDemandGrid, Observable, Memory, declare, selector, Selection, ColumnResizer, xhr, Observable, Memory, array) {
	ready(function() {
		initGrid();
	});
	var grid = null;
	var dataStore = null;
	function initGrid() {
		dataStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "toggle",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();

		reloadGridData();
	}

	function reloadGridData() {
		var _url = appRoot + "/hq/brand/product/doQuery.action?brandId=" + g_brandId;
		_url = getUrl(_url);
		
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			loadGridData(data);
		}, function(err) {
		});
	}

	function loadGridData(_rows) {
		array.forEach(_rows, function(row, i) {
			row.rownumber = i + 1;
			dataStore.put(row);
		});
	}

	function getColumn() {
		return [ selector({
			field : 'rownumber'
		}), {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "编号",
			field : "itemId",
			sortable : false
		}, {
			label : "名称",
			field : "itemName",
			sortable : false
		}, {
			label : "",
			field : "none",
			sortable : false
		} ];
	}

	delProduct = function() {
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

	/**
	 * 接收父窗口传回的数据
	 * 
	 * @param selRows
	 */
	afterSelProduct = function(selRows) {
		loadGridData(selRows);
	}
	getSelectedRows = function() {
		return dataStore.query();// 初始化被选择的记录
	}

	doSave = function() {
		var _url = appRoot + "/hq/brand/product/doSave.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : {
				brandId : g_brandId,
				jsonData : JSON.stringify(dataStore.query())
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				reloadGridData();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	}

});

function selProduct() {
	parent.selProduct();
}
