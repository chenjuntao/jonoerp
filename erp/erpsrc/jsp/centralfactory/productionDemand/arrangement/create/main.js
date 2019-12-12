dojo.require("dojox.form.BusyButton");

var busyButton;
dojo.ready(function(){
	busyButton = new dojox.form.BusyButton({
             id: "submit",
             busyLabel: "正在提交数据...",
             label: "生成生产计划单",
             timeout: 10000
  }, "placeHolder");
  
  dojo.connect(dijit.byId("submit"), "_onClick", function(){
	  		doSubmit();
	  });
});

require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		var query = getQuery();
		orderAuditedGrid.init(query);
	});
});

function transform(){
	var formId = orderAuditedGrid.doPick();
	dojo.byId('formId').value = formId;
	loadGridData();
}

function loadGridData() {
	var _url = appRoot + "/centralfactory/productionDemand/arrangement/create/toArrangement.action?formId="
			+ dojo.byId('formId').value;
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ],
			function(xhr, Observable, Memory) {
				xhr.get(_url, {
					handleAs : "json"
				}).then(function(data) {
					dataStore = new Observable(new Memory({
						idProperty : "itemId",
						data : data
					}));
					
					arrangementGrid.init();
					arrangementGrid.grid.set("store", dataStore);
				}, function(err) {
				});
			});
}

function doQuery() {
	var query = getQuery();
	orderAuditedGrid.query(query);
}

function getQuery() {
	return {
		startDate :dojo.byId('startDate').value,
		endDate :dojo.byId('endDate').value
	}
}

function doSubmit() {
	if(!arrangementGrid.grid){
		busyButton.cancel();
		alert("请先转化生产计划单！");
		return false;
	}
	
	arrangementGrid.grid.save();
	
	var transformData = arrangementGrid.grid.store.data;
	if(transformData.length == 0){
		busyButton.cancel();
		alert("请先转化生产计划单！");
		return false;
	}
	
	// JSON.stringify 将 JavaScript 值转换为 JavaScript 对象表示法（Json） 字符串
	// value 需转换的 JavaScript 值（通常为对象或数组）
	dojo.byId("jsonData").value = JSON.stringify(arrangementGrid.grid.store.data);
	
	var _url = appRoot + "/centralfactory/productionDemand/arrangement/create/doCommit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			
//			1. 将表单节点和元素转换成 JavaScript 具有键值对的对象
//			2. 表单元素，按钮 其他元素仅仅只有 id 但是没有 name 属性会全部会忽略掉，并且没有 value 属性也会被忽略掉。
//			3. Property : data , Type : String|Object
			
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				busyButton.cancel();
				closeTab(tabId);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
