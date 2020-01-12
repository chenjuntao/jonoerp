var doQuery = null;
var doAdd = null;
var grid = null;

require([ "dojo", "dojo/_base/lang", "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/JsonRest",
		"dojo/store/Cache", "dojo/store/Memory", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
		"dojo/_base/array", "dojo/request/xhr", "dojo/ready" ], function(dojo, lang, OnDemandGrid, selector, Selection,
		JsonRest, Cache, Memory, declare, query, Keyboard, array, xhr, ready) {
	ready(function() {
		initGrid();
	});

	/**
	 * 点击查询按钮时调用
	 */
	doQuery = function() {
		var itemName = dojo.byId('itemName').value;
		itemName = lang.trim(itemName);
		if (itemName == '') {
			alert('请输入原料名称或编码！');
			return;
		}
		g_cateId = null;
		grid.set('query', {
			'itemType' : 'R_S',
			'itemName' : itemName
		});
	}

	function initGrid() {
		var _url = appRoot + "/hq/shelfitem/additem/queryItem.action?shelfId=" + g_shelfId;
		
		var jStore = new JsonRest({
			target : _url,
			idProperty : "itemId",
			query : function(query, options) {
				return JsonRest.prototype.query.call(this, query, options);
			}
		});
		cStore = new Cache(jStore, new Memory());

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard ]);
		cols.unshift(selector({
			field : 'rownumber'
		}));
		grid = new CustomGrid({
			store : cStore,
			columns : cols,
			selectionMode : "toggle",
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.startup();
	}

	/**
	 * 保存商品与库位的对应关系
	 */
	doAdd = function() {
		var selectArr = [];
		for ( var itemId in grid.selection) {
			if (grid.selection[itemId]) {
				selectArr.push(itemId);
			}
		}
		if (selectArr.length == 0) {
			alert("请选择记录！");
			return;
		}
		var _url = appRoot + "/hq/shelfitem/additem/saveItem.action";
		
		xhr.post(_url, {
			handleAs : "json",
			data : {
				shelfId : g_shelfId,
				itemIds : selectArr.join()
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});

		parent.closeItemDlg();
	}
});

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable:false
}, {
	label : "原材料编码",
	field : "itemId",
	sortable:false
}, {
	label : "原材料名称",
	field : "itemName",
	renderCell : function(object, data) {
		return imageFmt(data, object.itemId);
	},
	sortable:false
}, {
	label : "助记码",
	field : "queryCode",
	sortable:false
}, {
	label : "单位",
	field : "itemDimension",
	sortable:false
}, {
	label : "类别",
	field : "categoryName",
	sortable:false
}, {
	label : "",
	field : "blank",
	sortable:false
} ];
