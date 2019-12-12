dojo.ready(function() {
	initGrid();
});

var grid = null;
function initGrid() {
	var _url = appRoot + "/hq/item/process/doQuery.action?itemId=" + g_itemId;
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection","dgrid/extensions/ColumnResizer"  ],
			function(OnDemandGrid, JsonRest, declare, selector, Selection,ColumnResizer) {
				var myStore = new JsonRest({
					target : _url,
					idProperty : 'step'
				});

				var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
				grid = new CustomGrid({
					store : myStore,
					columns : getColumn(selector),
					cellNavigation : false,
					allowSelectAll : true,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
			});
}

function refresh() {
	grid.set('query', {});
}

function getColumn(selector) {
	return [ selector({
		field : 'rownumber'
	}), {
		label : "顺序",
		field : "step",
		sortable:false
	}, {
		label : "具体操作",
		field : "stepOperation",
		sortable:false
	}, {
		label : "备注",
		field : "stepNote",
		sortable:false
	}, {
		label : '修改',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
		},
		sortable:false
	}, {
		label : "",
		field : "blank",
		sortable:false
	} ];
}

var editDlg = null;
function doAdd(_step) {
	var _title = "新增";
	var _url = appRoot + "/hq/item/process/stepEditView.action?itemId=" + g_itemId;
	
	if (_step != undefined) {
		_title = "修改";
		_url += "&step=" + _step;
	}
	
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
	refresh();
	editDlg.hide();
}

function doEdit(row) {
	doAdd(row.step);
}

function doDelete() {
	var selectArr = [];
	for ( var step in grid.selection) {
		selectArr.push(step);
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/item/process/doDelete.action";
	
	if (confirm("确定删除选定的工序吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					itemId : g_itemId,
					steps : selectArr.join()
				}
			}).then(function(data) {
				alert("删除成功！");
				refresh();
			}, function(err) {
			});
		});
	}
}
