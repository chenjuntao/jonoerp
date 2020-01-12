dojo.ready(function() {
	initData();
	initGrid();
});

var shopData = [];
function initData() {
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/common/listShop.action?branchType=R_C_L";

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

function getQuery() {
	return {
		branchId : dojo.byId('branchId').value,
		templateType : templateType
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/common/template/doQuery.action";

	require([ "dojo/_base/declare", "dgrid/OnDemandGrid", "custom/store/Server", "dgrid/selector", "dgrid/Selection",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory", "dojo/_base/array", "dojo/domReady!" ],
			function(declare, OnDemandGrid, Server, selector, Selection, Observable, Cache, Memory, array) {
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
		field : 'rownumber',
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '模板编号',
		field : 'templateId',
		renderCell : function(object, data) {
			return hrefFmt(object.templateId, doScan, object);// hrefFmt(_text,
			// _handler,
			// _data)
		},
		sortable : false
	}, {
		label : '模板名称',
		field : 'templateName',
		sortable : false
	}, {
		label : '模板所属部门',// （门店）
		field : 'branchId',
		formatter : function(val) {
			return commonFmt(val, shopData, 'code', 'name');
		},
		sortable : false
	}, {
		label : '模板要货类别',// (最多列出前五种)
		field : 'categoryId',
		sortable : false
	}, {
		label : '模板管理',
		field : 'manage',
		renderCell : function(object, data) {
			return hrefFmt("管理", doEdit, object);
		},
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function doScan(row) {
	var _url = appRoot + "/restaurant/common/template/scanView.action?templateId=" + row.templateId;

	addTab("盘点单模板查看", _url);
}

function doEdit(row) {
	var _url = appRoot + "/restaurant/common/template/editView.action?templateId=" + row.templateId + "&templateType="
			+ templateType + "&parentTabId=" + tabId;

	addTab("盘点单模板管理", _url);
}

function doPick() {
	var selectArr = [];
	for ( var templateId in grid.selection) {
		if (grid.selection[templateId]) {
			selectArr.push(templateId);
		} else {
			break;
		}
		var row = grid.store.get(templateId);
		var _url = appRoot + "/restaurant/common/template/queryItem.action?templateId=" + templateId + "&branchId="
				+ parent.getBranchId(); // 根据父窗口的门店获取库存信息

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
