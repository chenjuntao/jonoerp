dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/goodsbill/query/doQuery.action?formType=request";
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/Grid",
	          "custom/store/Server", 
	          "dojo/store/Observable", 
	          "dojo/store/Cache", 
	          "dojo/store/Memory",
	          "dojo/_base/declare",
	          "dgrid/extensions/Pagination",
	          "dgrid/extensions/ColumnResizer", 
	          "dgrid/ColumnSet" ,
	          "dojo/domReady!"
	          ], function(Grid, Server, Observable,Cache, Memory, declare, Pagination,ColumnResizer,ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination,ColumnResizer,ColumnSet ]);

		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return  [
	 		[ [ {
		label : "序号",
		field : "rownumber",
		sortable:false,
		formatter : function(field) {
			if (field == "合计") {
				return getBoldText(field);
			}
			return field;
		}
	}, {
		label : '单据编号',
		field : 'formId',
		className:'text-center',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt(object.formId, doScan, object);
		},
		sortable:false
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
	}] ], [ [ {
		label : '单据状态',
		field : 'formStatus',
		className:'text-center',
		sortable:false
	}, {
		label : '是否加单',
		field : 'isAddForm',
		className:'text-center',
		sortable:false,
		formatter : function(field) {
			if (field == "Y") {
				return "加单";
			}
			return field;
		}
	}, {
		label : '要货部门',
		field : 'buyerName',
		sortable:false
	}, {
		label : '制单人',
		field : 'formMaker',
		className:'text-center',
		sortable:false  
	}, {
		label : '制单日期',
		field : 'formTime',
		className:'text-center',
		sortable:false
	}, {
		label : '操作时间',
		field : 'formTimeActual',
		className:'text-center',
		sortable:false
	}, {
		label : '到货日期',
		field : 'receiveTime',
		className:'text-center',
		sortable:false
	}, {
		label : '审核日期',
		field : 'auditTime',
		className:'text-center',
		sortable:false
	}, {
		label : '备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '主要要货',
		field : 'maxPayItem',
		sortable:false
	}, {
		label : '要货总额',
		field : 'allPayAmt',
		className:'text-right',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return getBoldText(field);
			}
			
			return field;
		},
		sortable:false
	}, {
		label : "使用模板",
		field : "templateName",
		renderCell : function(object, data) {
			return hrefFmt(object.templateName, doScanTemp, object);
		},
		sortable:false
	} , {
		label : '采购汇总',
		field : 'purchaseStatus',
		className:'text-center',
		sortable:false
	}, {
		label : '配送处理',
		field : 'shippingStatus',
		className:'text-center',
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}] ] ];
}

function doScanTemp(row) {
	var _url = appRoot + "/restaurant/goodsbill/template/scanView.action?templateId=" + row.templateId;
	_url = getUrl(_url);
	
	addTab("要货单模板查看", _url);
}

function doScan(row) {
	var data = getCurrentVersion(row.formId);
	if(data.operationContent == "已删除"){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	
	doDetailScan(row.formId);
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if(isInOperation(data.operationContent)){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	
	return true;
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/goodsbill/query/editView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("管理要货单-" + row.formId, _url);
}