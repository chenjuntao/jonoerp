dojo.ready(function() {
	initGrid();
	
	resetDisplay();
});

function refreshStorage1(){
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

function resetDisplay(){
	var selectedItemName = dojo.byId("selectedItemName").value;
	if(selectedItemName != null ){
		 dojo.byId("displayItemName").innerHTML = selectedItemName;
	}
}

function doQuery() {
	grid.set('query', getQuery());
}

var materialDlg = null;
function selMaterial() {
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/common/selmaterialRadio/mainView.action";
		_url = getUrl(_url);
		
		var option = {
			title : "选择原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		materialDlg = createDialog(option);
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.loadData();
		materialDlg.show();
	}
}

/**
 * 关闭对话框时调用，所选数据传入父页面
 */
function closeMaterialDlg(data) {
	if(data.length != 0){
		var itemId  = data[0].itemId;
		var itemName  = data[0].itemName;
		var displayItemName = "[" + itemId + "]" + itemName;
		
		dojo.byId("selectedItemId").value = itemId;
		dojo.byId("selectedItemName").value = displayItemName;
		dojo.byId("displayItemName").innerHTML = displayItemName;
	}else{
		dojo.byId("selectedItemId").value = "";
		dojo.byId("selectedItemName").value = "";
		dojo.byId("displayItemName").innerHTML = "";
	}
	
	materialDlg.hide();
}

/**
 * 选择原料时需要关联门店Id
 */
function getBranchId() {
	return dojo.byId('branchId').value;
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value,
		storageId : dojo.byId('storageId').value,
		selectedItemId : dojo.byId('selectedItemId').value,
		reason : dojo.byId('reason').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/inoutquery/inventory/detail/doQuery.action?branchType="+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/ColumnSet","dojo/_base/declare","dgrid/extensions/Pagination",
			"dgrid/extensions/ColumnResizer","dojo/domReady!" ], function(Grid, Server, Observable,
			Cache, Memory,ColumnSet,declare,Pagination,ColumnResizer) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : "rownumber",
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([Grid, ColumnSet,Pagination,ColumnResizer]);  
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			rowsPerPage:15,
			pageSizeOptions : [ 10,15,20],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}


function doScan(row) {
	doDetailScan(row.formId);
}

function getColumn() {
	return [[[ {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '仓库',
		field : 'storageName',
		sortable:false
	}, {
		label : '原料编码',
		field : 'itemId',
		sortable:false
	},{
		label : '原料名称',
		field : 'itemName',
		sortable:false
	},{
		label : "单位",
		field : "unit",
		sortable:false
	}]], [[ {
		label : '门店',
		field : 'branchName',
		sortable:false
	} ,{
		label : '操作时间',
		field : 'operationTime',
		sortable:false
	}, {
		label : '单据日期',
		field : 'businessDate',
		sortable:false
	}, {
		label : '单据号码',
		field : 'formId',
		renderCell : function(object, data) {
			if(isEmpty(object.formId)){
				
				return hrefFmt("", doScan, object);
			}
			return hrefFmt(object.formId, doScan, object);
		},
		sortable:false
	}, {
		label : '业务类型',
		field : 'reason',
		sortable:false
	}, {
		label : '期初数量',
		field : 'orgiCount',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			return field;
		},
		sortable:false
	}, {
		label : '入库数量（盘盈）',
		field : 'itemCountIn',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable:false
	}, {
		label : '入库金额',
		field : 'itemCountInMoney',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable:false
	},{
		label : '出库数量（盘亏）',
		field : 'itemCountOut',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable:false
	}, {
		label : '出库金额',
		field : 'itemCountOutMoney',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable:false
	},  {
		label : '结存数量',
		field : 'resultCount',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			return field;
		},
		sortable:false
	}, {
		label : '结存单价',
		field : 'itemUnitPrice',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			return field;
		},
		sortable:false
	}, {
		label : '结存金额',
		field : 'resultCountMoney',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			return field;
		},
		sortable:false
	} ]]];
}
