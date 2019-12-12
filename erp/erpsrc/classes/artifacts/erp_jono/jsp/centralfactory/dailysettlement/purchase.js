var purchaseGrid = new PurchaseGrid('purchaseGrid');

function PurchaseGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot
				+ "/lc/request/purchase/manage/queryByStatus.action?queryType=unaudit&statusType=ap&branchType="+branchType;
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
		var result = getCurrentStatus(row.formId);
		if (result.hasLock) {
			alert("单据正在编辑或审核中！");
		} else if (result.formStatus == '未审核') {
			var _url = appRoot + '/cf/purchase/audit/auditView.action?formId=' + row.formId + "&parentTabId=" + tabId;
			_url = getUrl(_url);
			
			addTab("原料采购单审核-" + row.formId, _url);
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
					+ "/lc/request/purchase/manage/editView.action??formId="
					+ row.formId + "&parentTabId=" + tabId;
			_url = getUrl(_url);
			
			addTab("原料采购单处理-" + row.formId, _url);
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
			label : "单据编号",
			field : "formId",
			sortable:false
		}, {
			label : "供应商",
			field : "provider",
			sortable:false
		}, {
			label : "到货日期",
			field : "receiveTime",
			sortable:false
		}, {
			label : "订货部门",
			field : "requester",
			sortable:false
		}, {
			label : "收货地址",
			field : "receiveAddress",
			sortable:false
		}, {
			label : "制单人员",
			field : "formMaker",
			sortable:false
		}, {
			label : "制单日期",
			field : "formTime",
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
