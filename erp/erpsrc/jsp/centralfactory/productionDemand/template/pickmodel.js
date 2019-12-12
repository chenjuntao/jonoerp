require([ "dojo/ready" ], function(ready) {
	ready(function() {
		initGlobal();
		initData();
		initGrid();
	});
});

/**
 * 初始化全局函数
 */
function initGlobal() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		window.getItemName = function() {
			return dom.byId("itemName").value;
		};
	});
}

var shopData = [];
function initData() {
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/common/listShop.action";
		_url = getUrl(_url);
		
		xhr.post({
			url : _url,
			handleAs : "json",
			load : function(data) {
				shopData = data;
			},
			error : function(error) {
				alert(error);
			}
		});
	});
}

function doQuery() {
	grid.set('query', getQuery());
}

var branchId = '';
var regionId = '';
function getQuery() {
	return {
		branchId : cfCode,
		templateType : templateType,
		itemName : getItemName()
	};
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/common/template/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dojo/_base/declare", "dgrid/OnDemandGrid",
			"custom/store/Server", "dgrid/selector", "dgrid/Selection",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/array", "dojo/domReady!" ], function(declare,
			OnDemandGrid, Server, selector, Selection, Observable, Cache,
			Memory, array) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : 'templateId',
			query : function(query, options) {
				query = getQuery();
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));
		var CustomGrid = declare([ OnDemandGrid, Selection ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(selector),
			selectionMode : "single",
			cellNavigation : false,
			// allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "pickGrid");

		grid.startup();
	});
}

var selectArr = [];

function getColumn(selector) {
	return [ selector({
		label : "",
		selectorType : "radio",
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber"
	}, {
		label : '模板编号',
		field : 'templateId'
	}, {
		label : '模板名称',
		field : 'templateName'
	}, {
		label : '模板要货类别',// (最多列出前五种)
		field : 'categoryId'
	// }, {
	// label : '模板备注信息',
	// field : 'templateNote'
	}, {
		label : '到货周期',
		field : 'arrivePeriod'
	}, {
		label : '模板查看',
		field : 'scan',
		renderCell : function(object, data) {
			return hrefFmt("查看", doScan, object);// hrefFmt(_text, _handler,
			// _data)
		}
	}, {
		label : "",
		field : "none"
	} ];
}

function doScan(row) {
	var _url = appRoot
			+ "/restaurant/common/template/scanView.action?templateId="
			+ row.templateId;
	_url = getUrl(_url);
	
	addTab("要货单模板查看", _url);
}

function doPick() {
	var selectArr = [];
	for ( var templateId in grid.selection) {
		selectArr.push(templateId);
		var row = grid.store.get(templateId);
		var _url = appRoot
				+ "/restaurant/common/template/queryItemByCF.action?templateId="
				+ templateId + "&branchId=200"; // 获取物流中心库存信息
		_url = getUrl(_url);
		
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				parent.closePickModelDlg(data);
			}, function(err) {
			});
		});
	}
	if (selectArr.length == 0) {
		alert("请选择模板！");
	}
}
