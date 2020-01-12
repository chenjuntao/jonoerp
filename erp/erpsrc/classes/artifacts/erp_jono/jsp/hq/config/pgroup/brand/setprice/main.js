/**
 * 接收父窗口传回的数据
 * 
 * @param selRows
 */
var afterSelProduct = null;
/**
 * 初始化被选择的记录
 */
var getSelectedRows = null;

var delProduct = null;
var doSave = null;

require([ "dojo", "dojo/ready", "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory",
		"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/extensions/ColumnResizer",
		"dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/array", "dgrid/editor",
		"dijit/form/NumberTextBox", "dojo/_base/lang" ], function(dojo, ready, OnDemandGrid, Observable, Memory,
		declare, selector, Selection, ColumnResizer, xhr, Observable, Memory, array, editor, NumberTextBox, lang) {
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
		var _url = appRoot + "/hq/pgroup/brand/setprice/queryProduct.action?priceGroupId=" + g_priceGroupId;
		
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

	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox, 'click');
	}

	function getColumn() {
		return [ {
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
		}, lang.mixin(getNumEditor(), {
			label : "价格",
			field : "itemPrice",
			sortable : false
		}), {
			label : "",
			field : "none",
			sortable : false
		} ];
	}

	doSave = function() {
		var _url = appRoot + "/hq/pgroup/brand/setprice/savePrice.action";
		
		grid.save().then(function(value) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					priceGroupId : g_priceGroupId,
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
		});
	}

});
