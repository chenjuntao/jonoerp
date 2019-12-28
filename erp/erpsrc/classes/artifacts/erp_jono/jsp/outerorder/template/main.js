require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		branchId : lcCode,
		regionId : 'r03',// 外部订货方
		deliveryType : '-1',
		arrivePeriod : dojo.byId('arrivePeriod').value,
		itemName : dojo.byId('itemName').value,
		templateType : templateType
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/goodsbill/template/doQuery.action?templateOwner=public";
	_url = getUrl(_url);
	
	require([ "dojo/_base/declare", "dgrid/OnDemandGrid", "custom/store/Server", "dgrid/selector", "dgrid/Selection",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory", "dojo/_base/array","dgrid/extensions/ColumnResizer", "dojo/domReady!" ],
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
				var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer ]);
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
		field : 'categoryId',
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
	doDetailScan(row.templateId);
}

function doAdd() {
	var _url = appRoot + "/outerorder/template/addView.action?templateType=" + templateType + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("新增要货单模板", _url);
}

function doEdit(row) {
	var _url = appRoot + "/outerorder/template/editView.action?templateId=" + row.templateId + "&templateType="
			+ templateType + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("要货单模板管理", _url);
}

function doDelete() {
	var selectArr = [];
	for ( var templateId in grid.selection) {
		selectArr.push(templateId);
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
