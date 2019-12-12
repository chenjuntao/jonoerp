require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

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
			}
		}, function(err) {
		});
	});
}

function doQuery() {
	grid.set('query' , getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value,
		storageId : dojo.byId('storageId').value,
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/reportdamage/queryloss/doQuery.action?branchType="+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/Grid", 
	          "custom/store/Server", 
	          "dojo/store/Observable",
	          "dojo/store/Cache", 
	          "dojo/store/Memory", 
	          "dojo/_base/declare",
	          "dgrid/extensions/Pagination",
	          "dgrid/ColumnSet",
	          "dgrid/extensions/ColumnResizer",
	          "dojo/domReady!" 
           ],function(Grid, Server, Observable, Cache, Memory,declare,Pagination,ColumnSet,ColumnResizer) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty:'rownumber',
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([Grid,Pagination,ColumnSet,ColumnResizer]);
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
		sortable:false,
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	},{
		label : "报损单编号",
		field : "formId",
		className:'text-center',
		sortable:false,
		renderCell : function(object, data) {
			if(object.rownumber == "合计"){
				return "";
			}
			
			return hrefFmt(object.formId, doShow, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		className:'text-center',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt("修改/删除/作废", doEdit, object);
		},
		sortable:false
	}]], [[ {
		label : '单据状态',
		field : 'formStatus',
		className:'text-center',
		sortable:false
	},{
		label : "报损部门",
		field : "lossBranch",
		sortable:false
	},{
		label : "报损仓库",
		field : "storage",
		sortable:false
	}, {
		label : "报损人员",
		field : "lossManId",
		sortable:false
	}, {
		label : "报损日期",
		field : "lossTime",
		className:'text-center',
		sortable:false
	}, {
		label : "审核人员",
		field : "auditor",
		sortable:false
	}, {
		label : "审核日期",
		field : "auditTime",
		className:'text-center',
		sortable:false
	}, {
		label : "备注",
		field : "formNote",
		sortable:false
	}, {
		label : "主要报损品",
		field : "maxPayItem",
		sortable:false
	}, {
		label : "报损总额",
		field : "allPayAmt",
		className:'text-right',
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	},{
		label : "",
		field : "none",
		sortable:false
	}]]];
}

function doShow(row) {
	 var data = getCurrentVersion(row.formId);
	    if (data.operationContent == "已删除") {
	        alert(data.operationName + "在" + data.operationTime + data.operationContent);
	        return false;
	    }
	
	 doDetailScan(row.formId);
}

//function checkStatus(_formId) {
//    var data = getCurrentVersion(_formId);
//    if (isInOperation(data.operationContent)) {
//        alert(data.operationName + "在" + data.operationTime + data.operationContent);
//        return false;
//    }
//
//    return true;
//}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已结案') {
		alert("单据已结案！")
		return false;
	}
	
	if (status == '已审核') {
		alert("单据已审核！")
		return false;
	}
	return true;
}

function doEdit(row) {
	 if (!checkStatus(row.formId)) {
	     return;
	  }

	 var _url = appRoot + "/restaurant/reportdamage/queryloss/editView.action?formId="
		+ row.formId + "&parentTabId=" + tabId;
	 _url = getUrl(_url);
	 
	addTab("管理原料报损单-" + row.formId, _url);
}