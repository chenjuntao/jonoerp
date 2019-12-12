dojo.ready(function() {
	initGrid();
});

var dataStore = null;
var grid = null;
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dojo/_base/declare", "dgrid/Keyboard", "dojo/domReady!" ],
			function(OnDemandGrid, editor, declare, Keyboard) {
				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					columns : getColumn(editor),
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "saveAsGrid");
				grid.startup();
				loadData();
			});
}

var saveModelInfo = {};

function loadData() {
	var data = saveModelInfo = parent.getSaveModelInfo();
	;
	dojo.byId('branchName').innerHTML = data.branchName;
	require([ "dojo/store/Memory" ], function(Memory) {
		dataStore = new Memory({
			idProperty : "rownumber",
			data : data.items
		});
		grid.set("store", dataStore);
	});
}

function getColumn(editor) {
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

function doSave() {
	var templateName = dojo.byId('templateName').value;
	if (templateName.trim() == '') {
		alert("模板名称不能为空！");
		return;
	}

	var savedata = {
		'templateMeta.branchId' : saveModelInfo.branchId,
		isSord : dojo.byId('isSord').checked,
		'templateMeta.templateName' : dojo.byId('templateName').value,
		'templateMeta.templateType' : dojo.byId('templateType').value,
		jsonData : JSON.stringify(saveModelInfo.items)
	};
	var _url = appRoot + "/restaurant/common/template/doSave.action";

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : savedata
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
				parent.closeSaveModelDlg();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
