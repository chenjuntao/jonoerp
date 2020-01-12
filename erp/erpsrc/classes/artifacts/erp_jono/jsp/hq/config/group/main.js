dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', {});
}

var grid = null;
var dataStore = null;
function initGrid(_id) {
	var _url = appRoot + "/hq/group/doQuery.action";
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare",
	          "dgrid/selector", "dgrid/Selection","dgrid/extensions/ColumnResizer" ],
			function(OnDemandGrid, Server, declare, selector, Selection,ColumnResizer) {
				dataStore = new Server({
					target : _url,
					idProperty : 'regionId'
				});

				var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
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
		sortable:false
	}), {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "编号",
		field : "regionId",
		sortable:false
	}, {
		label : "名称",
		field : "regionName",
		sortable:false
	}, {
		label : "所属物流中心ID",
		field : "branchId",
		sortable:false
	}, {
		label : "配送周期(天)",
		field : "deliveryCycle",
		sortable:false
	}, {
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
		label : "",
		field : "none",
		sortable:false
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
	var _url = appRoot + "/hq/group/editView.action";
	if (_id != undefined) {
		_title = "修改";
		_url += "?regionId=" + _id;
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
	doAdd(row.regionId);
}

var doEnable = null;
var doSelect = null;
require([ "dojo/request/xhr", "dijit/Dialog", "dojo/_base/array", "dojo/dom-construct" ], function(xhr, Dialog, array,
		domConstruct) {
	var _enableData = {};
	doEnable = function(row) {
		_enableData = {
			regionId : row.regionId
		};
		var _enabled = row.enabled;
		if (_enabled == 0) {
			_enableData.enabled = 1;
			showInitDlg();
		} else {
			_enableData.enabled = 0;
			sendReq();
		}
	}

	var initDlg = null;
	// 从已有的一个分组中选择一个作为参考，对所有原料的配送方式进行初始化
	function showInitDlg() {
		var rows = dataStore.getData();
		var regionLst = [];
		array.forEach(rows, function(row, i) {
			if (row.enabled == 1) {
				regionLst.push(row);
			}
		});

		loadSelect('regionLst', regionLst, 'regionId', 'regionName');

		if (initDlg == null) {
			initDlg = new Dialog({
				title : "选择其它配送分组，用来初始化原料的配送方式",
				style : "width: 400px"
			});

			initDlg.set("content", dojo.byId('initDlg').innerHTML);// 这里相当于复制
			domConstruct.destroy("initDlg");// 删除原来的标签，避免出现重复的元素
		}
		initDlg.show();
	}

	doSelect = function() {
		_enableData.refRegionId = dojo.byId('regionLst').value;
		sendReq();
		initDlg.hide();
	}

	function sendReq() {
		var _url = appRoot + "/hq/group/doEnable.action";
		
		xhr.post(_url, {
			handleAs : "json",
			data : _enableData
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("操作成功！");
				doQuery();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	}

	/**
	 * 装载下拉框数据，数据的键值分开，eg:[{'key' : '1', value : 'test'}]
	 * 
	 * @param id
	 * @param data
	 * @param listKey
	 * @param listValue
	 */
	function loadSelect(id, data, listKey, listValue) {
		// clear options first;
		var select = dojo.byId(id);
		var length = select.options.length;
		for (var i = length - 1; i >= 0; i--) { // 倒序删除，防止移动后造成空项
			select.options[i] = null;
		}

		array.forEach(data, function(item, i) {
			var option = domConstruct.create("option", {
				value : item[listKey],
				innerHTML : item[listValue]
			});
			domConstruct.place(option, id);
		});
	}

});

function doDelete() {
	var selectArr = [];
	for ( var regionId in grid.selection) {
		if (grid.selection[regionId]) {
			var rowData = dataStore.get(regionId);
			if (rowData.enabled == 1) {
				alert('[' + rowData.regionName + ']已启用，不允许删除！');
				return;
			}
			selectArr.push(regionId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/group/doDelete.action";
	
	if (confirm("确定删除选定的分组吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					regionIds : selectArr.join(',')
				}
			}).then(function(data) {
				alert("删除成功！");
				doQuery();
			}, function(err) {
			});
		});
	}
}
