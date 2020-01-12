dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', {});
}

var grid = null;
function initGrid(_id) {
	var _url = appRoot + "/hq/brand/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection",
			"dgrid/extensions/ColumnResizer" ], function(OnDemandGrid, JsonRest, declare, selector, Selection,
			ColumnResizer) {
		var myStore = new JsonRest({
			target : _url,
			idProperty : 'brandId'
		});

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
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
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "编号",
		field : "brandId",
		sortable : false
	}, {
		label : "名称",
		field : "brandName",
		sortable : false
	}, {
		label : "备注",
		field : "brandNote",
		sortable : false
	}, {
		label : '修改',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改", doEdit, object);
		},
		sortable : false
	}, {
		label : '设置餐厅',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("设置", setBranch, object);
		},
		sortable : false
	}, {
		label : '设置出品',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("设置", setProduct, object);
		},
		sortable : false
	}, {
		label : '设置价格',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("设置", setPrice, object);
		},
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/hq/brand/j/editView.action";
	
	var _height = "150px";
	if (_id != undefined) {
		_title = "修改";
		_url += "?brandId=" + _id;
		_height = "120px";
	}
	_url = getUrl(_url);
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : _height
		}
		createDialog(option, function(iDlg) {
			editDlg = iDlg;
		});
	} else {
		editDlg.set('title', _title);
		editDlg.set('style', {
			'height' : _height
		});
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
	doAdd(row.brandId);
}

function doDelete() {
	var selectArr = [];
	for ( var brandId in grid.selection) {
		if (grid.selection[brandId]) {
			selectArr.push(brandId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/brand/doDelete.action";
	_url = getUrl(_url);
	
	if (confirm("确定删除选定的品牌吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					brandIds : selectArr.join()
				// 如果省略该参数，则使用逗号作为分隔符。
				}
			}).then(function(data) {
				alert("删除成功！");
				doQuery();
			}, function(err) {
			});
		});
	}

}
