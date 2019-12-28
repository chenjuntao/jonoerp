dojo.ready(function() {
	initGrid();
});

var grid = null;

function initGrid() {
	var _url = appRoot + "/hq/acscan/queryInBySForm.action?formId="+formId;
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/OnDemandGrid", 
	          "custom/store/Server", 
	          "dojo/store/Observable",
	          "dojo/store/Cache", 
	          "dojo/store/Memory", 
	          "dojo/_base/declare",
	          "dojo/domReady!" 
	        ], function(OnDemandGrid,Server, Observable, Cache, Memory, declare) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

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
		field : 'subFormId',
		renderCell : function(item, data) {
			if(item.rownumber == "合计"){
			return hrefFmt("",doScan, item);
		}
		
		return hrefFmt(item.subFormId,doScan, item);}
	}, {
		label : '关联单号',
		field : 'formRefId',
		renderCell : function(item, data) {
			if(item.rownumber == "合计"){
				return hrefFmt("",doRelInScan, item);
			}
			
		return hrefFmt(item.formRefId,doRelInScan, item);}
	},{
		label : '操作',
		field : 'delete',
		renderCell : function(item, data) {
			if(item.rownumber == "合计"){
				return hrefFmt("",doDelete, item);
			}
			
			return hrefFmt("删除",doDelete, item);
		}
	}, {
		label : '操作人员',
		field : 'formOperate'
	}, {
		label : '操作日期',
		field : 'formOperateTime'
	}, {
		label : '入库总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	}, {
		label : '确认状态',
		field : 'status',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			
			if(field == '外部订货方已确认'){
				return "已确认";
			}else{
				return "未确认";
			}
		}
	}, {
		label : '备注',
		field : 'formNote'
	}];
}

function doDelete (row){
	var dataArr = grid.get("store").data;
	
	var _url = appRoot + "/sp/statement/manage/doDelete.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {subFormIds:row.subFormId,formId:formId,length:dataArr.length}
		}).then(function(data) {
			if (data.msg == 'ok') {
				if(dataArr.length == 1){
					closeTab(tabId,"doQuery");
					return;
				}
				
				grid.refresh();
				dojo.byId('allPayAmt').innerHTML = data.sum;
			} else {
			}
		}, function(err) {
		});
	});
}

function doScan(row) {
	doDetailScan(row.subFormId);
}

function doRelInScan(row) {
	doDetailScan(row.formRefId);
}