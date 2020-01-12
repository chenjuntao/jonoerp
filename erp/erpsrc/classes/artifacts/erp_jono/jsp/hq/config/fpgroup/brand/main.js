dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', {});
}

var grid = null;
var myStore = null;
function initGrid(_id) {
	var _url = appRoot + "/hq/fpgroup/lc/doQuery.action?priceGroupType=LC";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/JsonRest", "dojo/_base/declare",
			"dgrid/selector", "dgrid/Selection", "dojo/_base/array", "dgrid/extensions/ColumnResizer" ], function(
			OnDemandGrid, Server, JsonRest, declare, selector, Selection, array, ColumnResizer) {
		myStore = new Server({
			target : _url,
			idProperty : "priceGroupId"
		});

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(selector),
			allowSelectAll : false,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(selector) {
	return [ selector({
		label : "",
		selectorType : "radio",
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "编号",
		field : "priceGroupId",
		sortable : false
	}, {
		label : "名称",
		field : "priceGroupName",
		sortable : false
	}, {
		label : "备注",
		field : "priceGroupNote",
		sortable : false
	}, {
		label : '修改',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
		},
		sortable : false
	}, {
		label : '设置部门',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("设置", setBranch, object);
		},
		sortable : false
	}, {
		label : '查看价格',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("查看", setPrice, object);
		},
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/hq/fpgroup/brand/editView.action?priceGroupType=LC";
	if (_id != undefined) {
		_title = "修改";
		_url += "&priceGroupId=" + _id;
	}
	_url = getUrl(_url);
	
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "150px"
		}
		editDlg = createDialog(option);
	} else {
		editDlg.set('title', _title);
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		editDlg.show();
	}
}

function closeEditDlg(data) {
	doQuery();
	editDlg.hide();
}

function doEdit(row) {
	doAdd(row.priceGroupId);
}

function doDelete() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			var rowData = myStore.get(itemId);
			if (itemId == 'JOIN' || itemId == 'BENCHMARK' || itemId == 'RETAIL' || itemId == 'WHOLESALE'
					|| itemId == 'SUPPLIER' || itemId == 'PURCHASE' || itemId == 'SALE') {
				alert('[' + rowData.priceGroupName + ']为系统价格组，不允许删除！');
				return;
			}
			selectArr.push(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var itemIds = selectArr.join(',');
	isItemCount(itemIds);
	if (info != null) {
		alert(info);
		return;
	}

	isTherapy(itemIds);
	if (therapyInfo != null) {
		alert(therapyInfo);
		return;
	}
	var _url = appRoot + "/hq/fpgroup/lc/doDelete.action";
	_url = getUrl(_url);
	
	if (confirm("确定删除选定的价格组吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					priceGroupId : itemIds
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					alert("删除成功！");
					doQuery();
				} else {
					alert("删除失败！");
				}
			}, function(err) {
			});
		});
	}
}
var info = null;

/**
 * 验证物料库存是否都为0
 */
function isItemCount(itemIds) {

	var _url = appRoot + "/hq/item/meta/checkItemCount.action?itemIds=" + itemIds;
	_url = getUrl(_url);
	
	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				exist = true;
				info = data.info;
			} else {
				info = null;
			}
		}, function(err) {
		});
	});
	return exist;
}

var therapyInfo = null;

/**
 * 验证物料库存是否都为0
 */
function isTherapy(itemIds) {

	var _url = appRoot + "/hq/item/meta/checkIsTherapy.action?itemIds=" + itemIds;
	_url = getUrl(_url);
	
	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				exist = true;
				therapyInfo = data.info;
			} else {
				therapyInfo = null;
			}
		}, function(err) {
		});
	});
	return exist;
}
