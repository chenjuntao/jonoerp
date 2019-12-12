dojo.ready(function() {
	initData();
	initGrid();
});

var shopData = [];
function initData() {
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/common/listShop.action";
		
		_url = getUrl(_url);
		xhr.post({
			url : _url,
			handleAs : "json",
			load : function(data) {
				shopData = data;
			},
			error : function(error) {
				alert(error);
			}
		});
	});
}

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
	var _url = appRoot + "/restaurant/goodsbill/query/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare","dgrid/extensions/Pagination",
			"dojo/domReady!" ], function(Grid, Server, Observable,
			Cache, Memory,declare,Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([Grid,Pagination]);
		
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10],
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
		label : '要货单编号',
		field : 'formId'
	}, {
		label : '要货部门',
		field : 'buyerName',
		formatter : function(val) {
			return commonFmt(val, shopData, 'code', 'name');
		}
	}, {
		label : '制单人',
		field : 'formMaker'
	}, {
		label : '制单日期',
		field : 'formTime'
	}, {
		label : '到货日期',
		field : 'receiveTime'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '要货模板',
		field : 'templateId'
	}, {
		label : '主要要货',
		field : 'maxPayItem'
	}, {
		label : '要货总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	}, {
		label : '单据状态',
		field : 'formStatus'
	}, {
		label : '查看',
		field : 'scan',
		renderCell : function(object, data) {
			if(object.rownumber == "合计"){
				return "";
			}
			
			return hrefFmt("查看", doScan, object);// hrefFmt(_text, _handler,
			// _data)
		}
	} ];
}

function doScan(row) {
	var _url = appRoot + "/restaurant/goodsbill/query/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	addTab("查看", _url);
}
