
var standby = null;

require([ "dojo", "dojox/widget/Standby","dojo/ready" ], function(dojo, Standby,ready) {
	ready(function() {
		initTooTip();
		
		var query = getQuery();
		orderGrid.init(query);
		
		var workOrderQuery = getWorkOrderQuery();
		workOrderGrid.init(workOrderQuery);
		
		// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});
		
		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

function initTooTip() {
	// displays a tooltip that contains form elements(like a dialog)
	require([ "dijit/TooltipDialog" ], function(TooltipDialog) {
		myTooltipDialog = new TooltipDialog({
			id : 'myTooltipDialog',
			style : "color: red;width:100;height:auto;",
			onMouseLeave : function() {
				dijit.popup.close(myTooltipDialog);
			}
		});
	});
}


function doAllQuery(){
	doQuery();
	doWorkOrderQuery();
}

function doWorkOrderQuery() {
	var query = getWorkOrderQuery();
	workOrderGrid.query(query);
}

function doQuery() {
	var query = getQuery();
	orderGrid.query(query);
}

function doScan(row) {
	var _url = appRoot
				+ "/centralfactory/productionDemand/orderSummary/audit/scanView.action?formId="
				+ row.formRefId;
	_url = getUrl(_url);
	
	addTab(row.formRefId+"查看", _url);
}

function doMoreScan(_formRefId) {
	var _url = appRoot
			+ "/centralfactory/productionDemand/summary/scanView.action?formId="
			+ _formRefId;
	_url = getUrl(_url);
	
	addTab(_formRefId + "查看", _url);
}

function doRelScan(row) {
	require([ "dijit/popup", "dojo/query" ], function(popup, query) {
		myTooltipDialog.set("content", createContent(row, "doMoreScan"));
		popup.open({
			popup : myTooltipDialog,
			// x : event.target.getClientRects()[0].x, // x
			// y定位myTooltipDialog显示的位置
			// y : event.target.getClientRects()[0].y
			around : query(".field-formRefId", dojo.byId('"dataGrid-row-'
					+ row.id))[0]
		});
	});
}

function getQuery() {
	return {
		startDate :dojo.byId('startDate').value,
		endDate :dojo.byId('endDate').value
	}
}
function doArrangeScan(row) {
	var _url = appRoot
	+ "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId="
	+ row.formId;
	_url = getUrl(_url);
	
	addTab(row.formId+"查看", _url);
}

function transform(){
	var formId = orderGrid.doPick();
	dojo.byId('arrangementFormId').value = formId;
	
	workOrderGrid.query(getWorkOrderQuery());
	
	//生产计划单转化成生产工单时默认全部选中
	workOrderGrid.selectAll();
}

function getWorkOrderQuery() {
	return {
		arrangementFormId :dojo.byId('arrangementFormId').value
	}
}

function batchCreate(){
	var selectArr = workOrderGrid.selectArr;
	if(selectArr.length == 0){
		alert("请勾选至少一条生产工单！");
		return;
	}
	
	standby.show();
    
	var _url  = appRoot+ "/centralfactory/productionDemand/workOrder/create/doBatchCreate.action";
	_url = getUrl(_url);
	
    require([ "dojo/request/xhr"], function(xhr) {
		
		xhr.post(_url, {
			handleAs : "json",
			data : {jsonData:JSON.stringify(selectArr)}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("批量生成生产工单成功！");
				standby.hide();
				//刷新列表
				doAllQuery();
			} else {
				alert("批量生成生产工单失败！");
				standby.hide();
			}
		}, function(err) {
			standby.hide();
		});
	});
}
