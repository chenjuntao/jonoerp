dojo.ready(function() {
	initTree();
	queryDetail();
});

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}
var g_categoryId = '';

function customExport() {
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName != '') {
		g_categoryId = '';
	}
	var _url = appRoot + "/hq/item/meta/queryItem.action";

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				categoryId : g_categoryId,
				itemName : itemName,
				newEnd : 1
			}
		}).then(function(data) {
			fillData(cols, data);
		}, function(err) {
			errorHandler(err);
		});
	});

}

function initTree() {
	var _url = appRoot + "/restaurant/selmaterial/loadTree.action";
	require([ "dojo/request/xhr", "dojo/store/Memory", "dijit/Tree", "dijit/tree/ObjectStoreModel" ], function(xhr,
			Memory, Tree, ObjectStoreModel) {
		xhr(_url, {
			handleAs : 'json'
		}).then(function(data) {
			myStore = new Memory({
				data : data,
				getChildren : function(object) {
					return this.query({
						parent : object.id
					// cbtree默认支持parent
					});
				}
			});

			// Create the model
			var myModel = new ObjectStoreModel({
				store : myStore,
				query : {
					id : 'root'
				},
				mayHaveChildren : function(item) {
					var children = this.store.getChildren(item);
					if (children.length > 0) {
						return true;
					}
					return false;
				}
			});

			var tree = new Tree({
				model : myModel,
				showRoot : false,
				autoExpand : true,
				onClick : function(item, node, evt) {
					g_categoryId = item.id;
					queryDetail();
				}
			}, "categoryTree");
			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

var cols = [ {
	label : "",
	field : "none",
	sortable : false
} ];

/**
 * 点击查询按钮时调用
 */
doQuery = function() {
	hasData = true;
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName == '') {
		alert('请输入物料名称或编码！');
		return;
	}
	grid.set('query', {
		'categoryId' : '',
		'itemName' : itemName
	});
}

function queryDetail() {
	if (grid == null) {
		initGrid(g_categoryId);
	} else {
		grid.set('query', {
			categoryId : g_categoryId
		});
	}
}

var grid = null;
var hasData = true;
var myStore = null;
function initGrid(_id) {
	var _url = appRoot + "/hq/item/meta/queryItem.action";

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dgrid/extensions/Pagination", "dgrid/ColumnSet", "dojo/_base/array",
			"dojo/parser" ], function(Grid, Server, Observable, Cache, Memory, declare, selector, Selection, Keyboard,
			ColumnResizer, Pagination, ColumnSet, array, parser) {
		parser.parse();
		myStore = Observable(Cache(Server({
			target : _url,
			idProperty : "itemId",
			query : function(query, options) {
				if (query.categoryId == undefined) {
					query.categoryId = _id;
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Selection, Keyboard, Pagination, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on('dgrid-error', function(event) {
			errorHandler(event.error);
		});

		grid.startup();

		if (cols.length == 1 && hasData) {
			require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
				xhr.post(appRoot + "/hq/item/meta/queryPrice.action", {
					handleAs : "json"
				}).then(function(data) {
					if (data.length == 0) {
						hasData = false;
						return;
					}

					array.forEach(data, function(item) {
						cols.unshift({
							label : item.priceGroupName,
							field : item.priceGroupId,
							className : 'text-right',
							sortable : false
						});
					});
					cols.unshift({
						label : "启用状态",
						field : "enabled",
						className : 'text-center',
						formatter : statusFmt,
						sortable : false
					}, {
						label : "类别",
						field : "categoryName",
						sortable : false
					}, {
						label : "助记码",
						field : "queryCode",
						sortable : false
					}, {
						label : "单位",
						field : "itemDimension",
						sortable : false
					}, {
						label : "规格",
						field : "itemSpecification",
						sortable : false
					}, {
						label : "库位",
						field : "shelfName",
						sortable : false,
						formatter : shelfFmt
					}, {
						label : "主供应商",
						field : "mainSuppiler",
						sortable : false,
						formatter : shelfFmt
					}, {
						label : "箱子类型",
						field : "boxTypeName",
						sortable : false
					});

					grid.set('columnSets', getColumn(selector));
				}, function(err) {
				});
			});
		}
	});

}

function getColumn(selector) {
	return [ [ [ selector({
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		},
		sortable : false
	} ] ], [ cols ] ];
}

function statusFmt(value, rowData) {
	if (value == '1') {
		return '已启用';
	}
	return '';
}

function boxFmt(value, rowData) {
	if (value == '10') {
		return '非冷藏';
	}
	return '冷藏';
}

function shelfFmt(value, rowData) {
	if (isEmpty(value)) {
		return '无';
	}
	return value;
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/hq/item/meta/addView.action?categoryId=" + g_categoryId;

	if (g_categoryId == '') {
		alert("请先选择一个原料类别！");
		return;
	}
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "360px"
		}
		createDialog(option, function(iDlg) {
			editDlg = iDlg;
		});
	} else {
		editDlg.set('title', _title);
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		editDlg.show();
	}
}

function closeEditDlg(data) {
	queryDetail();
	editDlg.hide();
}

function doEdit(row) {
	var _url = appRoot + "/hq/item/meta/editView.action";
	_url += "?itemId=" + row.itemId;

	var _title = "修改";

	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "360px"
		}
		createDialog(option, function(iDlg) {
			editDlg = iDlg;
		});
	} else {
		editDlg.set('title', _title);
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		editDlg.show();
	}
}

var uploadDlg = null;
function doUpload(row) {
	var _title = "上传";
	var _url = appRoot + "/hq/item/meta/uploadView.action?itemId=" + row.itemId;

	var frameId = 'ifr_upload';
	if (uploadDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "90px"
		}
		createDialog(option, function(iDlg) {
			uploadDlg = iDlg;
		});
	} else {
		uploadDlg.set('title', _title);
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		uploadDlg.show();
	}
}

function closeUploadDlg(data) {
	uploadDlg.hide();
}

function doDelete() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			var rowData = myStore.get(itemId);
			var enabled = isEnable(itemId);
			if (enabled == 1) {
				alert('[' + rowData.itemName + ']已启用，不允许删除！');
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

	var _url = appRoot + "/hq/item/meta/doDelete.action";

	if (confirm("确定删除选定的物料吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					itemIds : itemIds
				}
			}).then(function(data) {
				alert("删除成功！");
				queryDetail();
			}, function(err) {
			});
		});
	}
}

var moveDlg = null;
function doMove(row) {
	var _title = "修改类别";
	var _url = appRoot + "/hq/item/meta/moveView.action";
	_url += "?itemId=" + row.itemId;

	var frameId = 'ifr_move';
	if (moveDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "350px",
			height : "350px"
		}
		createDialog(option, function(iDlg) {
			moveDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		moveDlg.show();
	}
}

function closeMoveDlg() {
	queryDetail();
	moveDlg.hide();
}

var info = null;

/**
 * 验证物料库存是否都为0
 */
function isItemCount(itemIds) {

	var _url = appRoot + "/hq/item/meta/checkItemCount.action?itemIds=" + itemIds;

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

/**
 * 验证物料启用状态
 */
function isEnable(itemId) {
	var _url = appRoot + "/hq/item/meta/queryEnable.action?itemId=" + itemId;

	var enable = 0;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			enable = data.enable;
		}, function(err) {
		});
	});
	return enable;
}

/**
 * 启停用物料
 */
function doEnable(row) {
	var _enabled = row.enabled;
	if (_enabled == 0) {
		_enabled = 1;
	} else {
		_enabled = 0;
	}
	var _url = appRoot + "/hq/item/meta/doEnable.action";

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				itemId : row.itemId,
				itemType : row.itemType,
				enabled : _enabled
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("操作成功！");
				// location.reload();// 刷新页面
				queryDetail();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			errorHandler(err);
		});
	});
}