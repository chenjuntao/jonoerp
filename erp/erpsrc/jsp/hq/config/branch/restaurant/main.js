dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', {});
}

function customExport() {
	fillData(grid.get('store').data);
}

var grid = null;
var dataStore = null;
function initGrid(_id) {
	var _url = appRoot + "/hq/branch/r/queryRestaurant.action?branchType=" + branchType;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", 
	          "dgrid/selector", "dgrid/Selection" ,"dgrid/extensions/ColumnResizer"
	          ,"dgrid/ColumnSet"],
			function(OnDemandGrid, Server, declare, selector, Selection,ColumnResizer,ColumnSet) {
				dataStore = new Server({
					target : _url,
					idProperty : "branchId"
				});

				var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer,ColumnSet ]);
				grid = new CustomGrid({
					store : dataStore,
					columnSets : getColumn(selector),
					allowSelectAll : true,
					cellNavigation : false,
					selectionMode : "single",
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
			});
}

function getColumn(selector) {
	return [
			[ [selector({
		field : 'rownumber',
		sortable:false
	}), {
		label : "配送分组",
		field : "regionName",
		sortable:false
	}, {
		label : "编号",
		field : "branchId",
		sortable:false
	}, {
		label : "名称",
		field : "branchName",
		sortable:false
	}] ], [ [{
		label : '修改',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
		},
		sortable:false
	}, {
		label : "启用状态",
		field : "enabled",
		formatter : statusFmt,
		sortable:false
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
		sortable:false
	}, {
		label : "助记码",
		field : "queryCode",
		sortable:false
	}, {
		label : "位置",
		field : "branchAddress",
		sortable:false
	}, {
		label : "电子邮箱",
		field : "email",
		sortable:false
	}, {
		label : "电话",
		field : "telephone",
		sortable:false
	}, {
		label : "传真",
		field : "fax",
		sortable:false
	}, {
		label : "备注",
		field : "remark",
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}  ] ] ];
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
	var _url = appRoot + "/hq/branch/r/addView.action?branchType=" + branchType;
	
	if (_id != undefined) {
		_title = "修改";
		_url += "&branchId=" + _id;
	}
	_url = getUrl(_url);
	
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "180px"
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
	var _url = appRoot + "/hq/branch/r/editView.action?branchType=" + branchType;
		_title = "修改";
		_url += "&branchId=" + row.branchId;
		_url = getUrl(_url);
		
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "180px"
		}
		editDlg = createDialog(option);
	} else {
		editDlg.set('title', _title);
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		editDlg.show();
	}
}


function doEnable(row) {
	var _url = appRoot + "/hq/branch/r/doEnable.action";
	
	var _enabled = row.enabled;
	
	//enable branch 
	if (_enabled == 0) {
		_enabled = 1;
	} else {
		// disable branch
		_enabled = 0;
		_url = appRoot + "/hq/branch/r/containitems.action";
	}
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : row.branchId,
				enabled : _enabled
			}
		}).then(function(data) {
			if(_enabled == 0){
				if (data.msg == 'ok') {
					alert("操作成功！");
					doQuery();
				} else {
					selPurchase(row);
				}
			}else{
				if (data.msg == 'ok') {
					alert("操作成功！");
					doQuery();
				} else {
					alert("操作失败！");
				}
			}
			
		}, function(err) {
		});
	});
}

function doDelete() {
	var selectArr = [];
	for ( var branchId in grid.selection) {
		if (grid.selection[branchId]) {
			var rowData = dataStore.get(branchId);
			if (rowData.enabled == 1) {
				alert('[' + rowData.branchName + ']已启用，不允许删除！');
				return;
			}
			selectArr.push(branchId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/branch/r/doDelete.action";
	_url = getUrl(_url);
	
	if (confirm("确定删除选定的餐厅信息吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					branchIds : selectArr.join(',')
				}
			}).then(function(data) {
				alert("删除成功！");
				doQuery();
			}, function(err) {
			});
		});
	}
}