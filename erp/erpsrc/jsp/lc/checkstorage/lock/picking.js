var pickingGrid = new PickingGrid('pickingGrid');

function PickingGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot	+ "/lc/stock/picking/queryByStatus.action?queryType=unaudit&branchType="+ branchType;
		_url = getUrl(_url);
		
		require([ 
		          "dgrid/OnDemandGrid", 
		          "custom/store/Server",
		          "dojo/store/Observable", 
		          "dojo/store/Cache",
		          "dojo/store/Memory" 
		          ], function(OnDemandGrid, Server,	Observable, Cache, Memory) {
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
			var _url = appRoot + '/lc/stock/picking/audit/auditView.action?formId=' + row.formId+"&parentTabId="+tabId;
			_url = getUrl(_url);
			
			addTab("捡货单审核-" + row.formId, _url);
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
			label : "捡货单编号",
			field : "formId",
			sortable:false
		},{
			label : '制单人员',
			field : 'formMaker',
			sortable:false
		}, {
			label : '制单时间',
			field : 'formTime',
			sortable:false
		}, {
			label : '捡货仓库',
			field : 'storage',
			sortable:false
		}, {
			label : "操作",
			field : "confirm",
			renderCell : function(object) {
					return hrefFmt("审核", instance.doAudit, object);
			},
			sortable:false
		} ];
	};
}
