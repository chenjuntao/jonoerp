function createBill() {
	dojo.byId("jsonData").value = JSON.stringify(grid.get('store').getData());
	
	var _url = appRoot	+ "/sp/statement/createBill.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ,"dojo/dom-form" ], function(xhr,domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data:domForm.toObject("billForm")
		}).then(function(data) {
			alert("对账单生成成功！");
			closeTab(tabId,'doQuery');
		}, function(err) {
		});
	});
}	

initGird();

var grid = null;

function initGird() {
	var _url = appRoot + "/sp/statement/doAllQuery.action";
	_url = getUrl(_url);
	
	require([ 
	          "dojo/dom-form",
	          "dgrid/OnDemandGrid", 
	          "custom/store/Server", 
	          "dojo/_base/declare",
	          "dojo/domReady!" 
	        ], function(domForm,OnDemandGrid,Server,  declare) {
		var myStore = Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, domForm.toObject("dataForm"), options);
			}
		});

		var CustomGrid = declare([ OnDemandGrid ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
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
		label : '单据状态',
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

function doScan(row) {
	doDetailScan(row.formId);
}

function doRelInScan(row) {
	doDetailScan(row.formRefId);
}


