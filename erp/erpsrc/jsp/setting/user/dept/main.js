require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

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

		queryUser();
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
		label : "编号",
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
		label : '设置角色',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("设置角色", setRole, object);
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
		createDialog(option, function(iDlg) {
			editDlg = iDlg;
		});
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
	roleDlg.hide();
}
/**
 * 由于新增用户与角色设置窗口大小重叠的问题，新增子窗口调用父窗口的选择角色对话框时比较绕，关闭对话框后要将数据传回到子窗口中
 */
function closeSubRoleDlg(selRow) {
	var ifrWindow = dojo.byId(editFrameId).contentWindow;
	ifrWindow.closeRoleDlg(selRow);
	roleDlg.hide();
}
