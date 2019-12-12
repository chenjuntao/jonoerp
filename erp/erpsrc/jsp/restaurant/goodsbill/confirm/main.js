var isWelcome = null;
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
	var _url = appRoot + "/restaurant/goodsbill/query/doQuery.action?formType=request&queryType=unaudit";
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/Grid",
	          "custom/store/Server", 
	          "dojo/store/Observable", 
	          "dojo/store/Cache",
	          "dojo/store/Memory",
	          "dgrid/extensions/ColumnResizer",
	          "dojo/_base/declare",
	          "dgrid/ColumnSet" ,
	          "dgrid/extensions/Pagination",
	          "dojo/domReady!"
	          ],function(Grid, Server, Observable, Cache, Memory,ColumnResizer,declare,ColumnSet,Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid ,ColumnResizer,ColumnSet,Pagination]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(editor) {
	return  [
	 		[ [ {
		label : "序号",
		field : "rownumber",
		sortable : false,
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
		renderCell : function(object, field) {
			if (object.rownumber == "合计") {
				return "";
			}
			
			return hrefFmt(object.formId, doFormScan, object);
		},
		sortable : false
	}, {
		label : '操作',
		field : 'operate',
		className:'text-center',
		renderCell : function(object, field) {
			if (object.rownumber == "合计") {
				return "";
			}
			
			return hrefFmtId("审核", doAudit, object, "operate"+object.rownumber);
		},
		sortable : false
	}] ], [ [{
		label : '要货部门',
		field : 'buyerName',
		sortable : false
	}, {
		label : '制单人',
		field : 'formMaker',
		className:'text-center',
		sortable : false
	}, {
		label : '制单日期',
		field : 'formTime',
		className:'text-center',
		sortable : false
	}, {
		label : '到货日期',
		field : 'receiveTime',
		className:'text-center',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '主要要货',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '要货总额',
		field : 'allPayAmt',
		className:'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return getBoldText(field);
			}
			
			return field;
		}
	}, {
		label : "使用模板",
		field : "templateName",
		renderCell : function(object, field) {
			if (object.rownumber == "合计") {
				return "";
			}
			
			return hrefFmt(object.templateName, doScan, object);
		},
		sortable : false
	} , {
		label : '单据状态',
		field : 'formStatus',
		className:'text-center',
		sortable : false
	} ] ] ];
}

function doScan(row) {
	var _url = appRoot + "/restaurant/goodsbill/template/scanView.action?templateId=" + row.templateId;
	_url = getUrl(_url);
	
	addTab("要货单模板查看", _url);
}

/**
 * 如果单据被删除了，则不允许用户查看其单据明细界面
 */
function doFormScan(row){
	var data = getCurrentVersion(row.formId);
	if(data.operationContent == "已删除"){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	doDetailScan(row.formId);
}

/**
 * 如果该单据被删除、作废、审核以及结案，那么则不允许用户进行下一步操作了
 */
function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if(isInOperation(data.operationContent)){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	
	return true;
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/goodsbill/confirm/auditView.action?formId=" + row.formId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	addTab("审核要货单-"+row.formId, _url);
}