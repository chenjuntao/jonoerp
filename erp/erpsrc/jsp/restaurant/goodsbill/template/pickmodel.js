var getItemName = null;

var shopData = [];
var grid = null;
var doPick = null;

require([ 
          "dojo/dom", 
          "dojo/request/xhr", 
          "dojo/_base/declare", 
          "dgrid/OnDemandGrid",
          "custom/store/Server",
          "dgrid/selector", 
          "dgrid/Selection", 
          "dojo/store/Observable", 
          "dojo/store/Cache", 
          "dojo/store/Memory",
          "dojo/_base/array", 
          "dojo/ready" 
      ], function(dom, xhr, declare, OnDemandGrid, Server, selector, Selection,	Observable, Cache, Memory, array, ready) {
	ready(function() {
		initData();
		initGrid();
	});

	getItemName = function() {
		return dom.byId("itemName").value;
	};

	function initData() {
		var _url = appRoot + "/common/listShop.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			shopData = data;
		}, function(err) {
			//登录超时
			errorHandler(err);
		});
	}

	function initGrid() {
		var _url = appRoot + "/restaurant/goodsbill/template/doQuery.action?templateOwner=" + templateOwner;
		_url = getUrl(_url);
		
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : 'templateId',
			query : function(query, options) {
				query = getQuery();
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));
		
		var CustomGrid = declare([ OnDemandGrid, Selection ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(selector),
			selectionMode : "single", // 单选模式
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "pickGrid");

		grid.startup();
	}
	
	doPick = function() {
		var selectArr = [];
		for ( var templateId in grid.selection) {
			if (grid.selection[templateId]) {
				selectArr.push(templateId);
			} else {
				break;
			}
			
			var row = grid.store.get(templateId);
			var _url = appRoot + "/restaurant/goodsbill/template/queryItem.action?templateId=" + templateId
					+ "&branchId=" + parent.getPickBranchId(); // 根据父窗口的门店获取价格类型
			_url = getUrl(_url);
			
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				//调用父页面方法
				parent.closePickModelDlg(data, row.arrivePeriod, row.deliveryType);
			}, function(err) {
			});
		}
		
		if (selectArr.length == 0) {
			alert("请选择模板！");
		}
	}
});

function doQuery() {
	grid.set('query', getQuery());
}

var branchId = '';
var regionId = '-1';

//查询对象
function getQuery() {
	if (parent.getBranchId != undefined) {
		branchId = parent.getBranchId();
	}
	
	if (parent.getRegionId != undefined) {
		regionId = parent.getRegionId();
	}
	
	return {
		branchId : branchId,
		regionId : regionId,
		arrivePeriod : -1,
		templateType : templateType,
		itemName : getItemName()
	};
}

var selectArr = [];

function getColumn(selector) {
	return [
    selector({
		label : "",
		selectorType : "radio",
		field : 'rownumber',
		sortable:false
	}), {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '模板编号',
		field : 'templateId',
		sortable:false
	}, {
		label : '模板名称',
		field : 'templateName',
		sortable:false
	}, {
		label : '模板要货类别',// (最多列出前五种)
		field : 'categoryName',
		sortable:false
	}, {
		label : '到货周期',
		field : 'arrivePeriod',
		sortable:false
	}, {
		label : '模板查看',
		field : 'scan',
		renderCell : function(object, data) {
			return hrefFmt("查看", doScan, object);
		},
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ];
}

function doScan(row) {
	var _url = appRoot + "/restaurant/goodsbill/template/scanView.action?templateId=" + row.templateId;
	_url = getUrl(_url);
	
	addTab("要货单模板查看", _url);
}
