var dataStore = null;
var grid = null;

require([ "dojo", "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory", "dgrid/OnDemandGrid",
		"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/Keyboard", "dojo/dom", "dojo/ready" ],
		function(dojo, xhr, Observable, Memory, OnDemandGrid, declare, selector, Selection, Keyboard, dom, ready) {
			ready(function() {
				initGrid();
			});

			window.getRegionId = function() {// 用于传递到子窗口使用
				return dojo.byId("deliveryRegion").value;
			};
			window.getDeliveryType = function() {
				return dojo.byId("deliveryType").value;
			};

			function initGrid() {
				dataStore = new Memory({
					// data : gridData
					idProperty : "itemId",
					data : []
				});
				var CustomGrid = declare([ OnDemandGrid, Keyboard, Selection ]);
				grid = new CustomGrid({
					store : dataStore,
					columns : getColumn(),
					selectionMode : "toggle",
					cellNavigation : false,
					allowSelectAll : true,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();

				/*
				 * grid.on(dom.byId('isSord'), 'click', function() {
				 * grid.set('sort', [ { property : 'itemId', descending : true }
				 * ]); });
				 */

				loadGridData();
			}

			/**
			 * 第一次装载表格数据
			 */
			function loadGridData() {
				var _url = appRoot + "/restaurant/common/template/queryItem.action?templateId=" + templateId;

				xhr.get(_url, {
					handleAs : "json"
				}).then(function(data) {
					dataStore.setData(data);
					grid.set("store", dataStore);
				}, function(err) {
				});
			}

			function getColumn() {
				return [ selector({
					field : 'rownumber',
					sortable : false
				}), {
					label : "原料编码",
					field : "itemId",
					sortable : true
				}, {
					label : "原料名称",
					field : "itemName",
					sortable : false
				}, {
					label : "类别",
					field : "itemCategory",
					sortable : false
				}, {
					label : "单位",
					field : "itemDimension",
					sortable : false
				}, {
					label : "规格",
					field : "itemSpecification",
					sortable : false
				}, {
					label : "标准单价",
					field : "itemUnitPrice",
					sortable : false
				}, {
					label : "",
					field : "none",
					sortable : false
				} ];
			}
		});

/**
 * 根据原料选择对话框的结果重新装载表格数据
 * 
 * @param data
 */
function reloadMeterial(data) {
	require([ "dojo/store/Memory", "dojo/_base/array" ], function(Memory, array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
		});
		dataStore = new Memory({
			data : data
		});
		grid.set("store", dataStore);
	});
}

var selectedRows = [];
var materialDlg = null;
function selMaterial() {
	selectedRows = dataStore.query();// 初始化被选择的记录
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/selmaterial/mainView.action";

		var option = {
			title : "新增原料",
			frameId : frameId,
			url : _url,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			materialDlg = iDlg;
		});
	} else {
		require([ "dojo/dom" ], function(dom) {
			var ifrWindow = dom.byId(frameId).contentWindow;
			ifrWindow.loadData();
			materialDlg.show();
		});
	}
}

function closeMaterialDlg(data) {
	reloadMeterial(data);
	materialDlg.hide();
}

function delMaterial() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
			dataStore.remove(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
}

function doSave() {
	var templateName = dojo.byId('templateName').value;
	if (templateName.trim() == '') {
		alert("模板名称不能为空！");
		return;
	}

	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}

	var savedata = {
		templateId : templateId,
		isSord : dojo.byId('isSord').checked,
		'templateMeta.templateId' : dojo.byId('templateId').value,
		'templateMeta.templateName' : dojo.byId('templateName').value,
		'templateMeta.branchId' : dojo.byId('branchId').value,
		'templateMeta.templateType' : dojo.byId('templateType').value,
		'templateMeta.categoryId' : dojo.byId('categoryId').text,
		jsonData : JSON.stringify(rows)
	};
	var _url = appRoot + "/restaurant/common/template/doSave.action";

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : savedata
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doDelete() {
	var _url = appRoot + "/restaurant/common/template/doDelete.action";

	if (confirm("确定删除模板吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					ids : templateId
				}
			}).then(function(data) {
				alert("刪除成功！");
				doClose();
			}, function(err) {
			});
		});
	}
}

var copyDlg = null;
function doCopy() {
	if (copyDlg == null) {
		var _url = appRoot + "/restaurant/common/template/copyView.action?templateId=" + templateId + "&templateType="
				+ templateType;

		var option = {
			title : "复制模板",
			url : _url,
			width : "550px",
			height : "120px"
		}
		copyDlg = createDialog(option);
	} else {
		copyDlg.show();
	}
}

function closeCopyDlg(data) {
	copyDlg.hide();
}

function doClose() {
	// console.log("xxx");
	// console.dir(parent.frames);
	// console.log(parent.frames[1][0].id);
	// return;
	//
	// var ifrWindow = top.document.getElementById("frame1").contentWindow;
	// ifrWindow.loadData();
	// parent.frames[0][0]['doQuery']();
	closeTab(tabId);
}
