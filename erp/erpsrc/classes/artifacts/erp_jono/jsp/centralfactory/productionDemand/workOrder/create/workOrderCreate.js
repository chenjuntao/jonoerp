dojo.ready(function() {
	initGrid();
	addEvent();
	
	dojo.byId('productionCycle').value = parseInt(dojo.byId('productionCycle').value);
});

function addEvent() {
	//进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(formId);
	};
}

var dataStore = null;
var grid = null;

function initGrid() {
	require([ 
	          "dojo/window",
	          "dgrid/editor",
	          "dojo/dom",
	          "dijit/form/NumberTextBox",  // when user input is restricted to numeric input
	          "dijit/form/DateTextBox",
	          "dgrid/OnDemandGrid",
	          "dojo/_base/declare",
	          "dgrid/Keyboard",
	          "dojox/widget/Standby",
	          "dojo/domReady!" 
	          ], function(win,editor,dom,NumberTextBox,DateTextBox,OnDemandGrid, declare, Keyboard,Standby) {
	
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
		
		new NumberTextBox({
			  required : true, // required 该值是否可以为空
			  value : parseInt(dom.byId("itemCountValue").value),
	          constraints: { // 约束
					min : 0, // 最小值
					max : 99550, //最大值
					places : '0' //小数位0到3位，超出则违反约束
				},
			invalidMessage : '请输入整数!'
	    }, "itemCount").startup();
		
		// calculate the grid height for avoid the outside scrollbar
		var vs = win.getBox();
		var gridHeight = vs.h - 160;
		var gridNode = dojo.byId("detailDataGrid");
		gridNode.style.height = gridHeight + "px";
		
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(editor,NumberTextBox,DateTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "detailDataGrid");
		
		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'productionCount' || field == 'productionMan' || field == 'productionNote') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				dataStore.put(row);
			}
		});

		grid.startup();
		loadGridData();
	});
}

function validateGrid() {
	if (!validateColumn(grid, 'productionCount')) {
		return false;
	}
	
	if (!validateColumn(grid, 'productionTime')) {
		return false;
	}
	return true;
}

function loadGridData() {
	var _url = appRoot
			+ "/centralfactory/productionDemand/workOrder/create/doWorkDetailQuery.action?formId="
			+ formId;
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ],
			function(xhr, Observable, Memory) {
				xhr.get(_url, {
					handleAs : "json"
				}).then(function(data) {
					dataStore = new Observable(new Memory({
						idProperty : "step",
						data : data
					}));
					grid.set("store", dataStore);
				}, function(err) {
				});
			});
}

function getColumn(editor,NumberTextBox,DateTextBox) {
	return [ {
		label : "制程顺序",
		field : "productionStep"
	},{
		label : "制程",
		field : "productionName"
	}, editor({
		label : "日期",
		field : "productionTime"
	},DateTextBox),
	editor({
		label : "产量",
		field : "productionCount",
		editorArgs : {
			style : 'width: 5em;',
			constraints : { // 约束
				min : 0, // 最小值
				max : 99550, //最大值
				places : '0' //小数位0到3位，超出则违反约束
			},
			required : true, // required 该值是否可以为空
			invalidMessage : '请输入整数!' // 输入的信息非数字触发提示
			//rangeMessage  违反 constrains 约束触发显示的提示信息
		}
	}, NumberTextBox), editor({
		label : "生产人员",
		field : "productionMan"
	}), editor({
		label : "备注",
		field : "productionNote"
	} ), {
		label : "",
		field : "workOrderNone"
	}];
}

function doCommit() {
//	if("true" === dojo.byId("itemCount").getAttribute("aria-invalid")){
//		alert("生产数量是必填项且为整数！");
//		return;
//	}
	
	if (!validateGrid()) {
		return;
	}
	// 显示遮罩层
	standby.show();
	grid.save();
	
//	dojo.byId('itemCountValue').value = dojo.byId('itemCount').value;
	
	var _url = appRoot
			+ "/centralfactory/productionDemand/workOrder/create/doWorkCommit.action";
	_url = getUrl(_url);
	
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			if (data.msg == 'ok') {
				alert("生产工单生成成功！");
				doClose();
			} else {
				alert("生产工单生成失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId,"doAllQuery");
}
