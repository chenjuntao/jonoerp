require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function doQuery() {
	grid.set('query', getQuery());
}

function refreshStorage(){
	var _url = appRoot + '/restaurant/reportdamage/queryloss/refreshStorage.action?branchType='+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	require([ "dojo/request/xhr"], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {branchId:dojo.byId('branchId').value}
		}).then(function(data) {
			if (data.msg) {
				var storageSelector = dojo.byId('storageId');
				storageSelector.length = 0;
				storageSelector.options.add(new Option("--请选择--","")); 
				for ( var i=0,length =data.msg.length; i< length;i++ ) {
					var item = data.msg[i];
					storageSelector.options.add(new Option(item.storageName,item.storageId)); 
				}
			} else {
				// do something
			}
		}, function(err) {
		});
	});
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value,
		storageId : dojo.byId('storageId').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/reportdamage/querydishloss/doQuery.action?branchType="+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable",
		"dojo/store/Cache", "dojo/store/Memory","dojo/_base/declare",
		"dgrid/extensions/Pagination", "dgrid/ColumnSet","dojo/domReady!" ],
		function(Grid, Server, Observable, Cache, Memory,declare,Pagination,ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([Grid,Pagination,ColumnSet]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10],
			loadingMessage : '加载中...',
			noDataMessage: "无数据！"
		}, "listLossGrid");

		grid.startup();
	});
}

function getColumn(editor) {
	return [[[{
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	},{
		label : "报损单编号",
		field : "formId"
	}]], [[ {
		label : '查看',
		field : 'show',
		renderCell : function(object, data) {
			if(object.rownumber == "合计"){
				return "";
			}
			
			return hrefFmt("查看", doShow, object);
		}
	}, {
		label : '单据状态',
		field : 'formStatus'
	}, {
		label : "报损部门",
		field : "lossBranch"
	}, {
		label : "报损仓库",
		field : "storage"
	}, {
		label : "报损人员",
		field : "lossMan"
	}, {
		label : "报损日期",
		field : "lossTime"
	}, {
		label : "审核人员",
		field : "auditor"
	}, {
		label : "审核日期",
		field : "auditTime"
	}
//	, {
//		label : "处理人员",
//		field : "processor"
//	}, {
//		label : "处理日期",
//		field : "process_time"
//	}
	, {
		label : "备注",
		field : "formNote"
	}, {
		label : "主要报损品",
		field : "maxPayItem"
	}, {
		label : "报损总额",
		field : "allPayAmt",
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	}]]];
}

function doShow(row) {
	var data = getCurrentStatus(row.formId);
	if (data.formStatus == '已删除') {
		alert("单据已删除！")
		return false;
	}
	var _url = appRoot + "/restaurant/reportdamage/querydishloss/showView.action?formId="
			+ row.formId + "&branchType="+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	addTab('报损单查看' + row.formId, _url);
}

function doEdit(row) {
	var result = getCurrentStatus(row.formId);
	if (result.hasLock)
	{
		alert("单据正在编辑或审核中！")
		return;
	}
	else if (result.formStatus == '未审核')
	{
		var _url = appRoot + "/restaurant/reportdamage/querydishloss/editView.action?formId="
			+ row.formId + "&parentTabId=" + tabId + "&branchType="+ dojo.byId('branchType').value;
		_url = getUrl(_url);
		
		addTab('报损单修改删除/作废' + row.formId, _url);
	}
	else
	{
		alert("单据" + result.formStatus);
	}
}