require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
	});
}

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		branchId : lcCode,
		itemName : dojo.byId('itemName').value,
		templateType : templateType
	}
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
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));
		var CustomGrid = declare([ OnDemandGrid, Selection ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(selector),
			selectionMode : "toggle",
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(selector) {
	return [ selector({
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
	}, {
		label : '模板查看',
		field : 'scan',
		renderCell : function(object, data) {
			return hrefFmt("查看", doScan, object);// hrefFmt(_text, _handler,
			// _data)
		}
	}, {
		label : '模板管理',
		field : 'manage',
		renderCell : function(object, data) {
			return hrefFmt("管理", doEdit, object);
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
	
	addTab("模板查看", _url);
}

function doAdd() {
	var _url = appRoot
			+ "/lc/request/template/addView.action?templateType="
			+ templateType + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("新增模板", _url);
}

function doEdit(row) {
	var _url = appRoot
			+ "/restaurant/common/template/editView.action?templateId="
			+ row.templateId + "&templateType=" + templateType
			+ "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("编辑模板", _url);
}

function doDelete() {
	var selectArr = [];
	for ( var templateId in grid.selection) {
		if (grid.selection[templateId]) {
			selectArr.push(templateId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/restaurant/common/template/doDelete.action";
	_url = getUrl(_url);
	
	if (confirm("确定删除选定的模板吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					ids : selectArr.join()
				}
			}).then(function(data) {
				alert("删除成功！");
				doQuery();
			}, function(err) {
			});
		});
	}
}
