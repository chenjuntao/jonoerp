var grid = null;

require([ "dojo/dom-form", "dojo/domReady!" ], function(domForm) {

	// 函数延迟声明
	window.getQuery = function() {

	var query = domForm.toObject("dataForm");
	return query;
	}

	initGird();
});

function doQuery() {
	grid.set('query', getQuery());
}

function initGird() {
	var _url = appRoot + "/sp/statement/doQuery.action";
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/Grid", 
	          "custom/store/Server", 
	          "dojo/store/Observable",
	          "dojo/store/Cache", 
	          "dojo/store/Memory", 
	          "dojo/_base/declare",
	          "dgrid/extensions/Pagination", 
	          "dojo/domReady!" 
	        ], function(Grid,Server, Observable, Cache, Memory, declare, Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10 ,20,50],
			noDataMessage : "无数据！",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '单号',
		field : 'formId',
		renderCell : function(item, data) {
			if(item.rownumber == "合计"){
				return hrefFmt("",doScan, item);
			}
			
			return hrefFmt(item.formId,doScan, item);
		}
	}, {
		label : '供应商',
		field : 'provider'
	}, {
		label : '关联单号',
		field : 'formRefId',
		renderCell : function(item, data) {
			if(item.rownumber == "合计"){
				return hrefFmt("",doRelInScan, item);
			}
			
			return hrefFmt(item.formRefId,doRelInScan, item);
		}
	}, {
		label : '操作部门',
		field : 'inputDepartment'
	}, {
		label : '操作人员',
		field : 'inputer'
	}, {
		label : '操作日期',
		field : 'inputTime'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '确认状态',
		field : 'status',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			if (field == '供应商已确认') {
				return "已确认";
			} else {
				return "未确认";
			}
		}
	}, {
		label : '总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	}];
}

function createView() {
	var startDate  = dojo.byId("startDate").value;
	var endDate  = dojo.byId("endDate").value;
	
	var supplierId  = dojo.byId("supplierId").value;
	var branchId  = dojo.byId("branchId").value;
	
	if(!supplierId){
		alert("请选择一个供应商！");
		return;
	}
	
	if(!branchId){
		alert("请选择一个门店！");
		return;
	}
	
	if(!startDate || !endDate){
		alert("操作日期不能为空！");
		return;
	}
	
	if(grid.get('store').data == 0){
		alert("没有数据，请重新选取日期后生成对账单！");
		return;
	}
	
	var _url = appRoot + '/sp/statement/createView.action?parentTabId=' + tabId;
	_url = getUrl(_url);
	
	
	addPostTab('dataForm', '生成对账单', _url);
}	

function doScan(row) {
	doDetailScan(row.formId);
}

function doRelInScan(row) {
	doDetailScan(row.formRefId);
}


