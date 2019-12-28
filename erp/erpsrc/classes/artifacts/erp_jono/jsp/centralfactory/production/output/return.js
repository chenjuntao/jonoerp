dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});

	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(formId);
	};
}

var dataStore = null;
var grid = null;
var changeIdObj = {};
function initGrid() {
	require([ "dijit/form/NumberTextBox", "dgrid/editor", "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard",
	          "dojo/on","dojo/keys","dojo/query","dojox/widget/Standby","dojo/domReady!" ]
	, function(NumberTextBox, editor, OnDemandGrid, declare, Keyboard,on,keys,query,Standby) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "detailDataGrid");

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'receiveCount') {
				// dgrid 视图和数据源相分离
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				row.differentCount = row.itemCount - row.receiveCount; // 计算实领差异
				dataStore.put(row);
			}
		});
		
		// TODO
		on(grid, 'keydown',
		function(e) {
		    if (e.keyCode === keys.UP_ARROW) {
		        Keyboard.moveFocusUp.call(grid, e);
		    } else if (e.keyCode === keys.DOWN_ARROW) {
		        Keyboard.moveFocusDown.call(grid, e);
		    } else if (e.keyCode === keys.ENTER) {
		        Keyboard.moveFocusDown.call(grid, e);
		    }
		});
		// 保证鼠标焦点与键盘焦点的连贯性
		on(grid, 'mousedown',
		function(e) {
		    grid.focus(e.target);
		});

		on(grid, 'dgrid-cellfocusin',
		function(e) {
		    if (e.parentType != undefined) { // 鼠标事件不予处理
		        var $input = query('.dijitInputField input[type=text]', e.target);

		        if (!isEmpty($input[0])) {
		            $input[0].select();
		        }
		    }
		});

		// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});
		
		document.body.appendChild(standby.domNode);
		standby.startup();
		
		grid.startup();
		
		loadGridData();
	});
}

function validateGrid() {
	if (!validateColumn(grid, 'itemCount')) {
		return false;
	}
	return true;
}

function loadGridData() {
	var _url = appRoot + "/cf/requisition/create/transformToDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ 
	          "dojo/request/xhr", 
	          "dojo/store/Observable", 
	          "dojo/store/Memory", 
	          "dojo/_base/array" ,
	          "dojo/dom-form"
	        ], function(xhr,Observable, Memory, array,domForm) {
		xhr.post(_url, {
			handleAs : "json",
		    data : domForm.toObject("billForm")
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "itemId",
				data : data
			}));
			array.forEach(data, function(row, i) {
				row.itemCount = 0;// 初始化为零
				row.receiveCount = 0;// 对于退料而言是无关字段
			});
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "商品编码",
		field : "itemId",
		sortable:false
	}, {
		label : "商品名称",
		field : "itemName",
		sortable:false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable:false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable:false
	}, editor({
		label : "退料数",
		field : "itemCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 80px;text-align:right',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable:false
	}, NumberTextBox),
	 {
		label : "已退料数",
		field : "returnedCount",
		sortable:false
	},{
		label : "",
		field : "none",
		sortable:false
	} ];
}

function doCommit(row) {
	if (!validateGrid()) {
		return;
	}

	grid.save();
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	standby.show();
	var _url = appRoot + "/cf/requisition/create/doCommit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("退料单生成成功！");
				standby.hide();
				doClose();
			} else {
				alert("退料单生成失败！");
				standby.hide();
			}
		}, function(err) {
				alert("退料单生成失败！");
		});
	});
}

function doClose() {
	closeTab(tabId);
}
