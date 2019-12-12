var dishLossGrid = new DishLossGrid('dishLossGrid');

function DishLossGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot
				+ "/restaurant/reportdamage/queryloss/queryByStatus.action?queryType=DISHLOSS&statusType=ap&branchType="+branchType;
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
		});
	};

	this.doAudit = function(row) {
		var result = getCurrentStatus(row.form_id);
		if (result.hasLock) {
			alert("单据正在编辑或审核中！");
		} else if (result.formStatus == '未审核') {
			var _url = appRoot
					+ "/restaurant/reportdamage/confirmdishloss/auditView.action?formId="
					+ row.form_id + "&parentTabId=" + tabId;
			_url = getUrl(_url);
			
			addTab("报损单审核-" + row.form_id, _url);
		} else {
			alert("单据" + result.formStatus);
		}
	};

	this.doProcess = function(row) {
		var result = getCurrentStatus(row.form_id);
		if (result.hasLock) {
			alert("单据正在编辑或审核中！");
		} else if (result.formStatus == '未处理') {
			var _url = appRoot
					+ "/restaurant/reportdamage/processdishloss/processView.action?formId="
					+ row.form_id + "&parentTabId=" + tabId;
			_url = getUrl(_url);
			
			addTab("报损单处理-" + row.form_id, _url);
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
			field : "form_id",
			sortable:false
		}, {
			label : "报损部门",
			field : "loss_branch",
			sortable:false
		}, {
			label : "报损人",
			field : "loss_man",
			sortable:false
		}, {
			label : "报损日期",
			field : "loss_time",
			sortable:false
		}, {
			label : "备注",
			field : "form_note",
			sortable:false
		}, {
			label : "主要报损品",
			field : "max_pay_item",
			sortable:false
		}, {
			label : "报损总额",
			field : "all_pay_amt",
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
