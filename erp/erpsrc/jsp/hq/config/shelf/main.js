var grid = null;
var dataStore = null;

require([ "dojo", "dojo/ready", "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/selector",
		"dgrid/Selection", "dgrid/extensions/ColumnResizer" ], function(dojo, ready, OnDemandGrid, Server, declare,
		selector, Selection, ColumnResizer) {
	ready(function() {
		initGrid();
	});

	function initGrid() {
		var _url = appRoot + "/hq/shelf/doQuery.action";
		
		dataStore = new Server({
			target : _url,
			idProperty : 'shelfId'
		});

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	}
});

function doQuery() {
	grid.set('query', {});
}

var grid = null;

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
		field : "shelfId",
		sortable : false
	}, {
		label : "名称",
		field : "shelfName",
		sortable : false
	}, {
		label : "描述",
		field : "description",
		sortable : false
	}, {
		label : '修改',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
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
	var _url = appRoot + "/hq/shelf/editView.action";
	if (_id != undefined) {
		_title = "修改";
		_url += "?shelfId=" + _id;
	}
	
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "750px",
			height : "120px"
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
	doQuery();
	editDlg.hide();
}

function doEdit(row) {
	doAdd(row.shelfId);
}

/**
 * 验证是否可以删除
 */
function deletable(_shelfId) {
	var _url = appRoot + "/hq/shelf/deletable.action?shelfId=" + _shelfId;
	
	var deletable = true;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (!data.deletable) {
				var rowData = dataStore.get(_shelfId);
				alert('[' + rowData.shelfName + ']下已经存在物料，不允许删除！');
				deletable = false;
			}
		}, function(err) {
		});
	});
	return deletable;
}

function doDelete() {
	var selectArr = [];
	for ( var shelfId in grid.selection) {
		if (grid.selection[shelfId]) {
			if (!deletable(shelfId)) {
				return;
			}
			selectArr.push(shelfId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/shelf/deleteShelf.action";
	
	if (confirm("确定删除选定的库位吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					shelfIds : selectArr.join(',')
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