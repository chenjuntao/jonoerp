require([ "dojo/store/Memory", "dojo/store/Observable", "dojo/domReady!" ], function(Memory, Observable) {
	myStore = new Observable(new Memory({
		idProperty : 'formId',
		data : []
	}));
	requestData();
});

function doQuery() {
	requestData();
}

var grid = null;
var myStore = null;

function requestData() {
	var _url = appRoot + "/centralfactory/productionDemand/workOrder/audit/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/dom-form" ], function(xhr, Memory, domForm) {

		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			myStore.setData(data);

			if (!isEmpty(grid)) {
				grid.set('store', myStore);
			} else {
				initGrid();
			}
		}, function(err) {
		});
	});
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		queryStr : dojo.byId('queryStr').value,
		endDate : dojo.byId('endDate').value
	}
}

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dojo/_base/array",
			"dgrid/editor", "dijit/form/NumberTextBox", "dijit/form/DateTextBox", "dojo/date/locale",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, declare, selector, Selection,
			array, editor, NumberTextBox, DateTextBox, locale, ColumnResizer) {

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);

		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(selector, editor, NumberTextBox, DateTextBox),
			cellNavigation : false,
			allowSelectAll : true,
			selectionMode : "multiple",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'itemCount') {
				grid.save();// very important
				var row = myStore.get(event.rowId);
				row[field] = event.value;
				myStore.put(row);
			} else if (field == 'completeTime') {
				grid.save();// very important
				var row = myStore.get(event.rowId);
				row[field] = locale.format(event.value, {
					selector : 'date',
					datePattern : 'yyyy-MM-dd'
				});
				myStore.put(row);
			}
		});
	});
}

function dateValidInfo(jsonData) {
	var info = '';
	for (var i = 0, len = jsonData.length; i < len; i++) {
		var item = jsonData[i];
		if (item.completeTime < item.formTime) {
			info += '[' + item.itemId + ']' + item.itemName + "\n";
		}
	}

	if (!isEmpty(info)) {
		info += "物料的完工日期小于制单日期！";
	}

	return info;
}

function audit() {
	var jsonData = [];

	require([ "dojo/request/xhr" ], function(xhr) {
		for ( var formId in this.grid.selection) {
			if (this.grid.selection[formId]) {
				jsonData.push(myStore.get(formId));
			}
		}

		if (jsonData.length == 0) {
			alert("请先选择数据！");
			return;
		}

		// 遍历jsonData，若完工日期小于制单日期，则不能审核
		var info = dateValidInfo(jsonData);
		if (!isEmpty(info)) {
			alert(info);
			return;
		}

		var _url = appRoot + "/centralfactory/productionDemand/workOrder/audit/doAudit.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : {
				jsonData : JSON.stringify(jsonData)
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("审核成功！");
				requestData();
			}
		}, function(err) {
			alert("审核失败！");
		});
	});
}

function getColumn(selector, editor, NumberTextBox, DateTextBox) {
	return [ selector({
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '单据编号',
		field : 'formId',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doScan, object);
		},
		sortable : false
	}, {
		label : "商品编码",
		field : "itemId",
		sortable : false
	}, {
		label : "商品名称",
		field : "itemName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension2",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "生产数量",
		field : "itemCount2",
		sortable : false
	}, {
		label : "制单人员",
		field : "formMaker",
		sortable : false
	}, {
		label : "制单日期",
		field : "formTime",
		sortable : false
	}, editor({
		label : "完工日期",
		field : "completeTime",
		sortable : false,
		editorArgs : {
			constraints : {
				datePattern : 'yyyy-MM-dd',
				selector : 'date'
			},
			style : "width:100px;"
		}
	}, DateTextBox), {
		label : "备注说明",
		field : "formNote",
		sortable : false
	} ];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑或审核中！")
		return false;
	}
	if (status == '已审核') {
		alert("单据已审核！");
		return false;
	}
	return true;
}

function doScan(row) {
	var _url = appRoot + "/centralfactory/productionDemand/workOrder/audit/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);
	
	addTab(row.formId + "查看", _url);
}