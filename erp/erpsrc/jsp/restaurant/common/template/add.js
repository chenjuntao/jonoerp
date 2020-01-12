dojo.ready(function() {
	initGrid();
});

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dojo/domReady!" ], function(OnDemandGrid,
			Memory, declare, Keyboard) {
		dataStore = new Memory({
			data : []
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.startup();
	});
}

function loadData(data) {
	require([ "dojo/store/Memory", "dojo/_base/array" ],
			function(Memory, array) {
				array.forEach(data, function(row, i) {
					row.rownumber = i + 1;
				});
				dataStore = new Memory({
					data : data
				});
				grid.set("store", dataStore);
			});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "原料编码",
		field : "itemId"
	}, {
		label : "原料名称",
		field : "itemName"
	}, {
		label : "类别",
		field : "itemCategory"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}, {
		label : "单价",
		field : "itemUnitPrice"
	}, {
		label : "",
		field : "none"
	} ];
}

var materialDlg = null;
function selMaterial() {
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/selmaterial/mainView.action";
		
		var option = {
			title : "自选原料",
			url : _url,
			width : "850px",
			height : "300px"
		}
		materialDlg = createDialog(option);
	} else {
		materialDlg.show();
	}
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}

function doSave() {
	var templateName =  dojo.byId('templateName').value;
	if(templateName.trim() == ''){
		alert("模板名称不能为空！");
		return;
	}
	
	var rows = dataStore.query();
	if(rows.length == 0){
		alert("请选择原料！");
		return;
	}
	
	var _url = appRoot + "/restaurant/common/template/doSave.action";
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				'templateMeta.branchId' : dojo.byId('branchId').value,
				'templateMeta.templateName' : dojo.byId('templateName').value,
				'templateMeta.templateType' : templateType,
				// 'templateMeta.categoryId' : dojo.byId('categoryId').text,
				jsonData : JSON.stringify(rows)
			}
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

function doClose() {
	closeTab(tabId);
}