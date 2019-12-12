require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		query = getQuery();

		requisitionGrid.init(query);
	});
});

function doQuery() {
	requisitionGrid.query(getQuery());
}

var query;

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value
	}
}

var requisitionGrid = new RequisitionGrid('requisitionGrid');

function RequisitionGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', _query);
	};

	this.init = function(_query) {
		var _url = appRoot + "/cf/requisition/create/doQuery.action";
		_url = getUrl(_url);
		
		require([ "dgrid/selector", "dojo/_base/declare", "dgrid/OnDemandGrid", "dgrid/ColumnSet",
				"custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dgrid/Selection",
				"dojo/store/Memory" ], function(selector, declare, OnDemandGrid, ColumnSet, Server, Observable, Cache,
				Selection, Memory) {
			var myStore = Observable(Cache(Server({
				target : _url,
				query : function(query, options) {
					if (query.branchId == undefined) {
						query = _query;
					}
					return Server.prototype.query.call(this, query, options);
				}
			}), Memory()));

			var CustomGrid = declare([ OnDemandGrid, Selection, ColumnSet ]);
			instance.grid = new CustomGrid({
				store : myStore,
				sort : [ {
					attribute : "rownumber",
					descending : false
				} ],
				columnSets : instance.getColumn(),
				cellNavigation : false,
				selectionMode : "single",
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);

			instance.grid.startup();
		});
	};

	this.getColumn = function() {
		return [ [ [ {
			label : "序号",
			field : "rownumber"
		}, {
			label : '单据编号',
			field : 'formId'
		} ] ], [ [ {
			label : '生成超领单',
			field : 'operate',
			renderCell : function(object, data) {
				return hrefFmt("生成", doCreate, object);
			}
		}, {
			label : "商品编码",
			field : "itemId"
		}, {
			label : "商品名称",
			field : "itemName"
		}, {
			label : "单位",
			field : "itemDimension"
		}, {
			label : "规格",
			field : "itemSpecification"
		}, {
			label : "生产数量",
			field : "itemCount"
		}, {
			label : "生产车间",
			field : "workshop"
		}, {
			label : "制单人员",
			field : "formMaker"
		}, {
			label : "制单日期",
			field : "formTime"
		}, {
			label : "备注说明",
			field : "formNote"
		} ] ] ];
	};
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("超领单正在生成中！")
		return false;
	}

	if (status == "未审核") {
		alert("超领单已生成！")
		return false;
	}

	return true;
}

function doCreate(row) {
	if (!checkStatus(row.formId)) {
		return;
	}

	var _url = appRoot + "/cf/requisition/extra/create/createView.action?formRefId=" + row.formId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	addTab("生成超领单", _url);
}
