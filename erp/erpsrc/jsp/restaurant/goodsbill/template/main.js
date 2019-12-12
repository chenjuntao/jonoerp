require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initData();
		initGrid();
	});
});

function addEvent() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
	});
}

var shopData = [];
function initData() {
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/common/listShop.action?branchType=storage";
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
	if (!validateNum('arrivePeriod', '到货周期')) {
		return false;
	}
	grid.set('query', getQuery());
}

function getQuery() {
	var arrivePeriod = dojo.byId('arrivePeriod').value.trim();
	if (arrivePeriod == '') {
		arrivePeriod = '-1';
	}
	return {
		branchId : lcCode,
		regionId : dojo.byId('regionId').value,
		arrivePeriod : arrivePeriod,
		itemName : dojo.byId('itemName').value,
		templateType : 'request'
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/goodsbill/template/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dojo/_base/declare", "dgrid/OnDemandGrid", "custom/store/Server", "dgrid/selector", "dgrid/Selection",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory", 
			"dojo/_base/array","dgrid/extensions/ColumnResizer","dojo/domReady!" ],
			function(declare, OnDemandGrid, Server, selector, Selection, Observable, Cache, Memory, array,ColumnResizer) {
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
				var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
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
		field : 'rownumber',
		sortable:false
	}), {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '模板编号',
		field : 'templateId',
		renderCell : function(object, data) {
			return hrefFmt(object.templateId, doScan, object);// hrefFmt(_text, _handler,
			// _data)
		},
		sortable:false
	}, {
		label : '模板名称',
		field : 'templateName',
		sortable:false
	}, {
		label : '模板要货类别',// (最多列出前五种)
		field : 'categoryName',
		sortable:false
	}, {
		label : '到货周期',
		field : 'arrivePeriod',
		sortable:false
	}, {
		label : '模板管理',
		field : 'manage',
		renderCell : function(object, data) {
			return hrefFmt("管理", doEdit, object);
		},
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ];
}

function doScan(row) {
	var _url = appRoot + "/restaurant/goodsbill/template/scanView.action?templateId=" + row.templateId;
	_url = getUrl(_url);
	
	addTab("要货单模板查看", _url);
}

function doAdd() {
	var _url = appRoot + "/restaurant/goodsbill/template/addView.action?templateType=" + templateType + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	addTab("新增要货单模板", _url);
}

function doEdit(row) {
	var _url = appRoot + "/restaurant/goodsbill/template/editView.action?templateId=" + row.templateId
			+ "&templateType=" + templateType + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("要货单模板管理", _url);
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
				// 如果省略该参数，则使用逗号作为分隔符。
				}
			}).then(function(data) {
				alert("删除成功！");
				doQuery();
			}, function(err) {
			});
		});
	}
}
