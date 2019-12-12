dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', {});
}

var grid = null;
function initGrid(_id) {
	var _url = appRoot + "/hq/fpgroup/lc/doQuery.action?priceGroupType=LC";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection","dgrid/extensions/ColumnResizer"  ],
			function(OnDemandGrid, JsonRest, declare, selector, Selection,ColumnResizer) {
				var myStore = new JsonRest({
					target : _url,
					idProperty : 'priceGroupId'
				});

				var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
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
		sortable:false
	}, {
		label : "编号",
		field : "priceGroupId",
		sortable:false
	}, {
		label : "名称",
		field : "priceGroupName",
		sortable:false
	}, {
		label : "备注",
		field : "priceGroupNote",
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
		field : "none",
		sortable:false
	} ];
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/hq/fpgroup/lc/editView.action";
	if (_id != undefined) {
		_title = "修改";
		_url += "?priceGroupId=" + _id;
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
	doAdd(row.priceGroupId);
}

function doDelete() {

}
