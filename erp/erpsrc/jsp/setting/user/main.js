dojo.ready(function() {
	initGroupTree();
	initGrid();
	dojo.byId('add').disabled = true;
});

var g_branchId = '00';
function initGroupTree() {
	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		var _url = appRoot + "/setting/user/queryDeptTree.action";
		_url = getUrl(_url);

		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			treeStore = new Memory({
				data : data
			});

			treeModel = new TreeStoreModel({
				store : treeStore,
				query : {
					id : 'root'
				}
			});

			var tree = new Tree({
				model : treeModel,
				showRoot : false,
				autoExpand : false,
				// openOnClick : true,
				checkBoxes : false,
				clickEventCheckBox : false,
				onClick : function(item, node, evt) {
					if (item.parent != 'root') {// 如果是门店
						dojo.byId('add').disabled = false;
						g_branchId = item.id;
						queryUser();
					} else {
						dojo.byId('add').disabled = true;
					}
				}
			}, "groupTree");

			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

function doDelete() {
	var selectArr = [];

	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
		}
	}

	if (selectArr.length == 0) {
		alert("请选择用户进行删除！");
		return;
	}

	var _url = appRoot + "/setting/user/doDelete.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				userId : selectArr.join(",")
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("删除用户成功！");
				queryUser();
			} else {
				alert("删除用户失败！");
			}
		}, function(err) {
		});
	});

}

function queryUser() {
	var _url = appRoot + "/setting/user/doQuery.action";
	_url += "?branchId=" + g_branchId;

	dataStore.setData([]);
	grid.set("store", dataStore);
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				dataStore.put(row);
			});
		}, function(err) {
		});
	});
}

var grid = null;
var dataStore = null;
function initGrid(_id) {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dojo/_base/lang" ], function(OnDemandGrid,
			Observable, Memory, ObjectStore, declare, selector, Selection, editor, Select, lang) {
		dataStore = new Observable(new Memory({
			idProperty : "userId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(selector) {
	return [ selector({
		field : 'rownumber',
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "用户名",
		field : "userId",
		sortable : false
	}, {
		label : "姓名",
		field : "userName",
		sortable : false
	}, {
		label : '修改',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
		},
		sortable : false
	}, {
		label : "角色名称",
		field : "roleName",
		sortable : false
	}, {
		label : '设置角色',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("设置角色", setRole, object);
		},
		sortable : false
	}, {
		label : '设置兼职',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("设置兼职", pluralizeUser, object);
		},
		sortable : false
	}, {
		label : '重置密码',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("重置密码", resetPwd, object);
		},
		sortable : false
	}, {
		label : "性别",
		field : "gender",
		sortable : false
	}, {
		label : "电子邮件",
		field : "email",
		sortable : false
	}, {
		label : "电话",
		field : "telephone",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var editDlg = null;
var editFrameId = 'ifr_edit';
function doAdd(_id) {
	dojo.byId('add').disabled = true;
	var _title = "新增";
	var _url = appRoot + "/setting/user/addView.action?branchId=" + g_branchId;
	if (_id != undefined) {
		_title = "修改";
		_url = appRoot + "/setting/user/editView.action?branchId=" + g_branchId;
		_url += "&userId=" + _id;
	}

	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : editFrameId,
			width : "650px",
			height : "150px"
		}
		editDlg = createDialog(option);
	} else {
		editDlg.set('title', _title);
		var ifrWindow = dojo.byId(editFrameId).contentWindow;
		ifrWindow.location = _url;
		editDlg.show();
	}
}

function closeEditDlg(data) {
	queryUser();
	editDlg.hide();
}

function doEdit(row) {
	doAdd(row.userId);
}

var roleDlg = null;
function setRole(row) {
	var _title = "设置角色";
	var _url = appRoot + "/setting/user/setRoleView.action?branchId=" + g_branchId;
	if (row != undefined) { // 新增用户子窗口可能会调用
		_url += "&userId=" + row.userId;
	}

	var frameId = 'ifr_setRole';
	if (roleDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "250px"
		}
		createDialog(option, function(iDlg) {
			roleDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		roleDlg.show();
	}
}

function closeRoleDlg() {
	queryUser();
	roleDlg.hide();
}
/**
 * 由于新增用户与角色设置窗口大小重叠的问题，新增子窗口调用父窗口的选择角色对话框时比较绕，关闭对话框后要将数据传回到子窗口中
 */
function closeSubRoleDlg(selRow) {
	var ifrWindow = dojo.byId(editFrameId).contentWindow;
	ifrWindow.closeRoleDlg(selRow);
	queryUser();
	roleDlg.hide();
}

var deptDlg = null;
function pluralizeUser(row) {
	var _title = "设置兼职";
	var _url = appRoot + "/setting/user/setDeptView.action?branchId=" + g_branchId;
	if (row != undefined) {
		_url += "&userId=" + row.userId;
	}

	var frameId = 'ifr_setDept';
	if (deptDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "250px"
		}
		createDialog(option, function(iDlg) {
			deptDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		deptDlg.show();
	}
}
function closeDeptDlg() {
	queryUser();
	deptDlg.hide();
}

function resetPwd(row) {
	var _url = appRoot + "/setting/user/password/doReset.action" + "&parentTabId=" + tabId;

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				userId : row.userId
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("重置成功！");
			} else {
				alert("重置失败！");
			}
		}, function(err) {
		});
	});
}
