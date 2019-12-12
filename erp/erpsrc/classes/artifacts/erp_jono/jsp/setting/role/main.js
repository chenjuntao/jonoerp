dojo.ready(function() {
	initGrid();
});

function doQuery() {
	var _url = appRoot + "/setting/role/doQuery.action";
	_url = getUrl(_url);

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
			idProperty : "roleId",
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

		doQuery();
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
		label : "部门类型",
		field : "branchTypeName",
		sortable:false
	}, {
		label : "编号",
		field : "roleId",
		sortable:false
	}, {
		label : "名称",
		field : "roleName",
		sortable:false
	}, {
		label : '修改',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
		},
		sortable:false
	}, {
		label : "描述",
		field : "description",
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ];
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/setting/role/addView.action";
	if (_id != undefined) {
		_title = "修改";
		_url = appRoot + "/setting/role/editView.action";
		_url += "?roleId=" + _id;
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
	doAdd(row.roleId);
}

/**
 * 验证是否可以删除
 */
function deletable(_roleId) {
	var _url = appRoot + "/setting/role/deletable.action?roleId=" + _roleId;
	_url = getUrl(_url);
	
	var deletable = true;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (!data.deletable) {
				var rowData = dataStore.get(_roleId);
				alert('角色[' + rowData.roleName + ']下已经存在用户，不允许删除！');
				deletable = false;
			}
		}, function(err) {
		});
	});
	return deletable;
}

function doDelete() {
	var selectArr = [];
	for ( var roleId in grid.selection) {
		if (grid.selection[roleId]) {
			if (!deletable(roleId)) {
				return;
			}
			selectArr.push(roleId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/setting/role/doDelete.action";
	_url = getUrl(_url);
	
	if (confirm("确定删除选定的角色吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					roleIds : selectArr.join(',')
				}
			}).then(function(data) {
				alert("删除成功！");
				doQuery();
			}, function(err) {
			});
		});
	}
}
