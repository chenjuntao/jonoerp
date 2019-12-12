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
		supplierId : dojo.byId('supplierId').value,
		branchId : dojo.byId('branchId').value,
		formIdText : dojo.byId('formIdText').value,
		initStatus :initStatus,
		formType : 'G'
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/hq/acscan/doQuery.action";
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
			pageSizeOptions : [ 10 ],
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
		label : '单据编号',
		field : 'formId',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt(object.formId, doScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(item, data) {
			if(item.rownumber == "合计"){
				return hrefFmt("",doOperate, item);
			}
			
			if(item.status != "未审核"){
				return hrefFmt("",doOperate, item);
			}
			
			return hrefFmt("修改",doOperate, item);
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
	}, {
		label : '制单人员',
		field : 'formMaker'
	}, {
		label : '供应商',
		field : 'provider'
	}, {
		label : '部门',
		field : 'branchName'
	}, {
		label : '制单日期',
		field : 'formTime'
	}, {
		label : '操作时间',
		field : 'formTimeActual'
	}, {
		label : '对账开始日期',
		field : 'startDate'
	}, {
		label : '对账结束日期',
		field : 'endDate'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '单据状态',
		field : 'status'
	} 
	];
}

function doScan(row) {
	var _url = appRoot
			+ "/sp/statement/manage/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	var _title = '对账单查看' + row.formId;
	addTab(_title, _url);
}

function doOperate(row) {
	var _title = '供应商对账管理';

	var _url = appRoot	+ "/sp/statement/manage/operate.action?formId="+ row.formId+"&status="+initStatus+"&parentTabId="+tabId;
	_url = getUrl(_url);
	
	addTab(_title, _url);
}

