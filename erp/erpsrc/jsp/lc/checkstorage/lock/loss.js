var lossGrid = new LossGrid('lossGrid');

function LossGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot
				+ "/restaurant/reportdamage/queryloss/queryByStatus.action?queryType=RAWLOSS&statusType=ap&branchType="+branchType;
		_url = getUrl(_url);
		
		require([ "dgrid/OnDemandGrid", "custom/store/Server",
				"dojo/store/Observable", "dojo/store/Cache",
				"dojo/store/Memory" ], function(OnDemandGrid, Server,
				Observable, Cache, Memory) {
			var myStore = Observable(Cache(Server({
				target : _url,
				query : function(query, options) {
					if (query.branchId == undefined) {
						query = _query;
					}
					return Server.prototype.query.call(this, query, options);
				}
			}), Memory()));

			instance.grid = new OnDemandGrid({
				store : myStore,
				sort : [ {
					attribute : "rownumber",
					descending : false
				} ],
				columns : instance.getColumn(),
				cellNavigation : false,
				loadingMessage : '加载中...',
				noDataMessage : "无数据！"
			}, gridId);

			instance.grid.startup();
			
//			instance.grid.on("dgrid-refresh-complete", function(event) {
//				console.log("dgrid-refresh-complete");
//				dishLossGrid.init(getQuery());
//			});
		});
	};

	this.doAudit = function(row) {
		var result = getCurrentStatus(row.formId);
		if (result.hasLock) {
			alert("单据正在编辑或审核中！");
		} else if (result.formStatus == '未审核') {
			var _url = appRoot
					+ "/restaurant/reportdamage/confirmloss/auditView.action?formId="
					+ row.formId + "&parentTabId=" + tabId;
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
			var _url = appRoot
					+ "/restaurant/reportdamage/processloss/processView.action?formId="
					+ row.formId + "&parentTabId=" + tabId;
			addTab("报损单处理-" + row.formId, _url);
		} else {
			alert("单据" + result.formStatus);
		}
	};

	this.getColumn = function() {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable:false
		}, {
			label : "报损单编号",
			field : "formId",
			sortable:false
		}, {
			label : "报损部门",
			field : "lossBranch",
			sortable:false
		}, {
			label : "报损人",
			field : "lossMan",
			sortable:false
		}, {
			label : "报损日期",
			field : "lossTime",
			sortable:false
		}, {
			label : "备注",
			field : "formNote",
			sortable:false
		}, {
			label : "主要报损品",
			field : "maxPayItem",
			sortable:false
		}, {
			label : "报损总额",
			field : "allPayAmt",
			sortable:false
		}, {
			label : '单据状态',
			field : 'formStatus',
			sortable:false
		}, {
			label : "操作",
			field : "confirm",
			renderCell : function(object) {
				if (object.formStatus == '未审核') {
					return hrefFmt("审核", instance.doAudit, object);
				} else {
					return hrefFmt("处理", instance.doProcess, object);
				}
			},
			sortable:false
		} ];
	};
}
