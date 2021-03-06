var dishLossGrid = new DishLossGrid('dishLossGrid');

function DishLossGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot
				+ "/restaurant/reportdamage/queryloss/queryByStatus.action?queryType=DISHLOSS&statusType=ap&branchType="
				+ branchType;
		_url = getUrl(_url);
		
		require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
				"dojo/store/Memory" ], function(OnDemandGrid, Server, Observable, Cache, Memory) {
			var myStore = Observable(Cache(Server({
				target : _url,
				idProperty : 'rownumber',
				query : function(query, options) {
					if (query.branchId == undefined) {
						query = _query;
					}
					return Server.prototype.query.call(this, query, options);
				}
			}), Memory()));

			instance.grid = new OnDemandGrid({
				store : myStore,
				columns : instance.getColumn(),
				cellNavigation : false,
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);

			instance.grid.startup();
		});
	};

	this.doAudit = function(row) {
		var result = getCurrentStatus(row.formId);
		if (result.hasLock) {
			alert("单据正在编辑或审核中！");
		} else if (result.formStatus == '未审核') {
			var _url = appRoot + "/restaurant/reportdamage/confirmdishloss/auditView.action?formId=" + row.formId
					+ "&parentTabId=" + tabId;
			_url = getUrl(_url);
			
			addTab("报损单审核-" + row.formId, _url);
		} else {
			alert("单据" + result.formStatus);
		}
	};

	this.doProcess = function(row) {
		var result = getCurrentStatus(row.formId);
		if (result.hasLock) {
			alert("单据正在编辑或审核中！");
		} else if (result.formStatus == '未处理') {
			var _url = appRoot + "/restaurant/reportdamage/processdishloss/processView.action?formId=" + row.formId
					+ "&parentTabId=" + tabId;
			_url = getUrl(_url);
			
			addTab("报损单处理-" + row.formId, _url);
		} else {
			alert("单据" + result.formStatus);
		}
	};

	this.getColumn = function() {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "报损单编号",
			field : "formId",
			className : 'text-center',
			sortable : false,
		// renderCell : function(object, data) {
		// return hrefFmt(object.formId, doScan, object);
		// }
		}, {
			label : "报损部门",
			field : "lossBranch",
			sortable : false
		}, {
			label : "报损人",
			field : "lossMan",
			className : 'text-center',
			sortable : false
		}, {
			label : "报损日期",
			field : "lossTime",
			className : 'text-center',
			sortable : false
		}, {
			label : "备注",
			field : "formNote",
			sortable : false
		}, {
			label : "主要报损品",
			field : "maxPayItem",
			sortable : false
		}, {
			label : "报损总额",
			field : "allPayAmt",
			className : 'text-right',
			sortable : false
		}, {
			label : '单据状态',
			field : 'formStatus',
			className : 'text-center',
			sortable : false
		}, {
			label : "操作",
			field : "confirm",
			className : 'text-center',
			renderCell : function(object) {
				if (object.formStatus == '未审核') {
					return hrefFmt("审核", instance.doAudit, object);
				}
			},
			sortable : false
		} ];
	};
}
