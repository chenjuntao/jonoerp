var priceGrid = new PriceGrid('priceGrid');

function PriceGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var g_adjustType = "SUPPLIER,PURCHASE,BENCHMARK,JOIN,RETAIL,WHOLESALE,SALE";
		var _url = appRoot + "/hq/priceadjust/queryEffect.action?adjustType=" + g_adjustType + "&queryType=unaudit";
		_url = getUrl(_url);
		
		require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
				"dojo/store/Memory" ], function(OnDemandGrid, Server, Observable, Cache, Memory) {
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

			// instance.grid.on("dgrid-refresh-complete", function(event) {
			// console.log("dgrid-refresh-complete");
			// dishLossGrid.init(getQuery());
			// });
		});
	};

	this.doAudit = function(row) {
		if (!instance.checkStatus(row.formId)) {
			return;
		}
		var _url = appRoot + "/hq/priceadjust/audit/auditView.action?formId=" + row.formId + "&parentTabId=" + tabId;
		_url = getUrl(_url);
		
		addTab("审核调价单-" + row.formId, _url);
	};

	this.checkStatus = function(_formId) {
		var data = getCurrentStatus(_formId);
		var status = data.formStatus;
		if (data.hasLock) {
			alert("单据正在编辑或审核中！")
			return false;
		}

		if (status == '已作废') {
			alert("单据已作废！")
			return false;
		} else if (status == '已审核') {
			alert("单据已审核！")
			return false;
		}
		return true;
	};

	this.getColumn = function() {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "单据编号",
			field : "formId",
			sortable : false
		}, {
			label : "单据日期",
			field : "formTime",
			sortable : false
		}, {
			label : "生效时间",
			field : "effectTime",
			sortable : false
		}, {
			label : "制表人员",
			field : "formMaker",
			sortable : false
		}, {
			label : "备注说明",
			field : "formNote",
			sortable : false
		}, {
			label : '单据状态',
			field : 'formStatus',
			sortable : false
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
			sortable : false
		} ];
	};
}
