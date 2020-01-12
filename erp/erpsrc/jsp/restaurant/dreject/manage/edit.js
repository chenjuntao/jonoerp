function addEvent() {
	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

var dataStore = null;
var grid = null;
var changeIdObj = {};
var reasonData = [ {
	"key" : "10",
	"value" : "损坏"
}, {
	"key" : "20",
	"value" : "过期"
}, {
	"key" : "30",
	"value" : "无"
} ];

require([
         "dojo", 
         "dojo/ready",
         "dgrid/OnDemandGrid",
         "dgrid/editor", 
         "dijit/form/NumberTextBox",
         "dijit/form/Select",
         "dojo/_base/declare", 
         "dgrid/Keyboard", 
         "dgrid/extensions/ColumnResizer",
         "dojo/on",
         "dojo/keys", 
         "dojo/query",
		"dojo/request/xhr",
		"dojo/data/ObjectStore",
		"dojo/store/Observable", 
		"dojo/store/Memory" 
	], function(dojo,ready, OnDemandGrid, editor, NumberTextBox, Select, declare, Keyboard, ColumnResizer, on, keys, query, xhr,ObjectStore, Observable, Memory) {
	ready(function() {
		addEvent();
		initGrid();
	});

	function initGrid() {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on("dgrid-datachange", dataChangeHandler);

		on(grid, 'keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});

		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});

		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		grid.startup();
		loadGridData();
	}

	function loadGridData() {
		var _url = appRoot + "/restaurant/dreject/manage/queryDetail.action?formId=" + formId;
		_url = getUrl(_url);
		
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "rownumber",
				data : data
			}));
			grid.set("store", dataStore);

			calcMoney(dataStore.query());
		}, function(err) {
		});
	}

	function getColumn() {
		reasonStore = new ObjectStore({
			objectStore : new Observable(new Memory({
				idProperty : "value",
				data : reasonData,
				sort : [ {// 这里排序不起作用，要何如做才行，以后再研究
					attribute : "key",
					descending : false
				} ]
			})),
			labelProperty : "value"
		});
		return [ {
			label : "序号",
			field : "rownumber",
			sortable:false
		}, {
			label : "原料编码",
			field : "itemId",
			className:'text-center',
			sortable:false
		}, {
			label : "原料名称",
			field : "itemName",
			sortable:false
		}, {
			label : "单位",
			field : "itemDimension",
			className:'text-center',
			sortable:false
		}, {
			label : "规格",
			field : "itemSpecification",
			sortable:false
		}, {
			label : "配送数",
			field : "shippingCount",
			className:'text-right',
			sortable:false
		}, {
			label : "实发数",
			field : "deliveryCount",
			className:'text-right',
			sortable:false
		}, {
			label : "实收数",
			field : "receiveCount",
			className:'text-right',
			sortable:false
		}, editor({
			label : "退货数",
			field : "returnCount",
			className : 'text-right edit-note',
			editorArgs : {
				style : 'width: 5em; text-align: right;',
				constraints : {
					min : 0,
					max : 1999999999,
					places : '0,3',
					row : this
				},
				required : true,
				invalidMessage : '请输入不多于三位小数的数字。'
			},
			sortable:false
		}, NumberTextBox), {
			label : "标准单价",
			field : "itemUnitPrice",
			className:'text-right',
			sortable:false
		}, {
			label : "标准金额",
			field : "returnPayAmt",
			className:'text-right',
			sortable:false
		}, editor({
			label : "退货原因",
			field : "returnReason",
			className : 'edit-note',
			editorArgs : {
				store : reasonStore,
				maxHeight : 150,
				style : "width:100px;"
			},
			sortable:false
		}, Select, "click") ];
	}

});

// 计算总金额
function calcMoney(data) {
	var sumMoney = 0;

	require([ "dojo/_base/array", "dojo/dom" ], function(array, dom) {
		array.forEach(data, function(item) {
			if (!isNaN(sumMoney)) {
				sumMoney += parseFloat(item.returnPayAmt);
			} else {
				sumMoney += 0;
			}
		});

		dom.byId("sumMoney").innerHTML = sumMoney.toFixed(2);

	});
}

function dataChangeHandler(event) {
	var field = event.cell.column.field;
	if (field == 'returnCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		row[field] = event.value;// necessary
		row.returnPayAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		dataStore.put(row);
	}
	changeIdObj[event.rowId] = event.rowId;

	calcMoney(dataStore.query());
}

function validateGrid() {
	if (!validateColumn(grid, 'returnCount')) {
		return false;
	}
	return true;
}

function numValid(){
	var info = '';
	var flag =false;
	var rows = dataStore.query();
	for (var i = 0; i < rows.length; i++) {
		var data = rows[i];
		if(data.returnCount!=0){
			flag=true;
		}
		if(data.returnCount > data.receiveCount){
			info += '[' + data.itemId +']' + data.itemName + '\n';
		}
	}
	
	if(!isEmpty(info)){
		info += "退货数不能大于实收数！";
	}
	
	if(!flag){
		info= "原料退货数全为0，请在配送退货管理中进行作废处理！";
	}
	return info;
}

function doSave() {
	if (!validateGrid()) {
		return;
	}
	
//	var info = numValid();
//	if(!isEmpty(info)){
//		alert(info);
//		return;
//	}
	
	grid.save();
	
	var rows = [];
	for ( var id in changeIdObj) {// 只获取修改过的记录
		rows.push(dataStore.get(id));
	}
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/restaurant/dreject/manage/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("提交成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doDelete() {
	var _url = appRoot + "/restaurant/dreject/manage/doDelete.action?formId=" + formId;
	_url = getUrl(_url);
	
	if (confirm("确定删除单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				alert("删除成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
			});
		});
	}
}

function doClose() {
	closeTab(tabId);
}