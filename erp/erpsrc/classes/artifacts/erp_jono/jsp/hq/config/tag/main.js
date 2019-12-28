dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', {});
}

var grid = null;
var myStore = null;

function initGrid(_id) {
	var _url = appRoot + "/hq/config/tag/doQuery.action";
	
	require([ 
	          "dgrid/OnDemandGrid", 
	          "custom/store/Server",
	          "dojo/store/JsonRest", 
	          "dojo/_base/declare",
	          "dgrid/selector",
	          "dgrid/Selection", 
	          "dojo/_base/array",
	          "dgrid/extensions/ColumnResizer"
          ], function(OnDemandGrid, Server, JsonRest, declare, selector, Selection, array, ColumnResizer) {
		myStore = new Server({
			target : _url,
			idProperty : "tagId"
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

function doEdit(row) {
	doAdd(row.tagId);
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
		label : "标签编码",
		field : "tagId",
		sortable : false
	}, {
		label : "标签名称",
		field : "tagName",
		sortable : false
	}, {
		label : "备注",
		field : "tagNote",
		sortable : false
	}, {
		label : '修改',
		field : 'operate',
		className:'text-center',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
		},
		sortable : false
	},{
		label : "",
		field : "none",
		sortable : false
	} ];
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/hq/config/tag/editView.action";
	if (_id != undefined) {
		_title = "修改";
		_url += "?tagId=" + _id;
	}
	
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "120px"
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

function doDelete() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择标签！");
		return;
	}
	var itemIds = selectArr.join(',');
	
	var _url = appRoot + "/hq/config/tag/doDelete.action";
	
	if (confirm("确定删除所选标签吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					tagId : itemIds
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

