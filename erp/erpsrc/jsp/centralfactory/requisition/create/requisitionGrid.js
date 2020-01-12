var requisitionGrid = new RequisitionGrid('requisitionGrid');

function RequisitionGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', _query);
	};

	this.init = function() {
		var _url = appRoot + "/cf/requisition/create/doQuery.action";
		_url = getUrl(_url);

		require([ "dgrid/selector", "dojo/_base/declare", "dgrid/OnDemandGrid", "dgrid/ColumnSet",
				"custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dgrid/Selection",
				"dojo/store/Memory", "dgrid/extensions/ColumnResizer" ], function(selector, declare, OnDemandGrid,
				ColumnSet, Server, Observable, Cache, Selection, Memory, ColumnResizer) {
			var myStore = Observable(Cache(Server({
				target : _url,
				query : function(query, options) {
					return Server.prototype.query.call(this, getQuery(), options);
				}
			}), Memory()));

			var CustomGrid = declare([ OnDemandGrid, Selection, ColumnSet, ColumnResizer ]);
			instance.grid = new CustomGrid({
				store : myStore,
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
			field : "rownumber",
			sortable : false
		}, {
			label : "生成情况",
			field : "count",
			formatter : function(count) {
				return count == 0 ? '未生成' : "<font color='red'>已生成</font>";
			},
			sortable : false
		}, {
			label : '单据编号',
			field : 'formId',
			renderCell : function(object, data) {
				return hrefFmt(object.formId, doScan, object);
			},
			sortable : false
		} ] ], [ [ {
			label : '操作',
			field : 'operate',
			renderCell : function(object, data) {
				return hrefFmtId("生成", doCreate, object, "operate" + object.rownumber);
			},
			sortable : false
		}, {
			label : "商品编码",
			field : "itemId",
			sortable : false
		}, {
			label : "商品名称",
			field : "itemName",
			sortable : false
		}, {
			label : "单位",
			field : "itemDimension",
			sortable : false
		}, {
			label : "规格",
			field : "itemSpecification",
			sortable : false
		}, {
			label : "生产数量",
			field : "itemCount",
			sortable : false
		}, {
			label : "生产车间",
			field : "workshop",
			sortable : false
		}, {
			label : "制单人员",
			field : "formMaker",
			sortable : false
		}, {
			label : "制单日期",
			field : "formTime",
			sortable : false
		}, {
			label : "备注说明",
			field : "formNote",
			sortable : false
		}, {
			label : "",
			field : "none",
			sortable : false
		} ] ] ];
	};
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("领料单正在生成中！")
		return false;
	}

	if (status == "未审核") {
		alert("领料单已生成！")
		return false;
	}

	return true;
}

function getCounts(_formId) {
	var result = {};
	var _url = appRoot + "/cf/requisition/create/getCounts.action?formId=" + _formId + "&type=produce";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			result = data;
		}, function(err) {
		});
	});
	return result;
}

function doScan(row) {
	var _url = appRoot + "/centralfactory/productionDemand/workOrder/audit/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);

	addTab("查看工单-" + row.formId, _url);
}

function doCreate(row) {
	if (!checkStatus(row.formId)) {
		return;
	}

	var count = getCounts(row.formId).count;
	if (parseInt(count, 10) != 0) {
		alert(" 请先审核与" + row.formId + "相关的领料单！");
		return;
	}

	var _url = appRoot + "/cf/requisition/create/createView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addTab("生成领料单-" + row.formId, _url);
}
