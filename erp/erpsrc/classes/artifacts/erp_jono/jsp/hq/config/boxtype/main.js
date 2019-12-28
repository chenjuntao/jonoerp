var grid = null;
var dataStore = null;

require([ "dojo", "dojo/ready", "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/selector",
		"dgrid/Selection", "dgrid/extensions/ColumnResizer" ], function(dojo, ready, OnDemandGrid, Server, declare,
		selector, Selection, ColumnResizer) {
	ready(function() {
		initGrid();
	});

	function initGrid() {
		var _url = appRoot + "/hq/boxtype/doQuery.action";
		_url = getUrl(_url);

		dataStore = new Server({
			target : _url,
			idProperty : 'typeId'
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

function getColumn(selector) {
	return [ selector({
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "编码",
		field : "typeId",
		sortable : false
	}, {
		label : "名称",
		field : "typeName",
		sortable : false
	}, {
		label : "体积(升/L)",
		field : "volume",
		className : "grid-number",
		sortable : false
	}, {
		label : "重量(克/G)",
		field : "weight",
		className : "grid-number",
		sortable : false
	}, {
		label : '修改',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
		},
		sortable : false
	}, {
		label : "启用状态",
		field : "enabled",
		formatter : statusFmt,
		sortable : false
	}, {
		label : '启/停用',
		field : 'operate',
		renderCell : function(rowData, value) {
			var str = "启用";
			if (rowData.enabled == 1) {
				str = "停用";
			}
			return hrefFmt(str, doEnable, rowData);
		},
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function statusFmt(value, rowData) {
	if (value == '1') {
		return '已启用';
	}
	return '';
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/hq/boxtype/editView.action";

	if (_id != undefined) {
		_title = "修改";
		_url += "?typeId=" + _id;
	}
	_url = getUrl(_url);
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
	doAdd(row.typeId);
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
	var _url = appRoot + "/hq/boxtype/doEnable.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				typeId : row.typeId,
				enabled : _enabled
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("操作成功！");
				doQuery();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doDelete() {
	var selectArr = [];
	for ( var typeId in grid.selection) {
		if (grid.selection[typeId]) {
			var rowData = dataStore.get(typeId);
			if (rowData.enabled == 1) {
				alert('[' + rowData.typeName + ']已启用，不允许删除！');
				return;
			}
			selectArr.push(typeId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/boxtype/doDelete.action";
	_url = getUrl(_url);

	if (confirm("确定删除选定的箱子类型吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					typeIds : selectArr.join(',')
				}
			}).then(function(data) {
				alert("删除成功！");
				doQuery();
			}, function(err) {
			});
		});
	}
}