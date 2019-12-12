var antiauditGrid = new AntiauditGrid('antiauditGrid');

function AntiauditGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot	+ "/restaurant/antiaudit/manage/doQuery.action?queryType=unaudit&branchType="+branchType;
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
				idProperty:'rownumber',
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

	this.checkStatus = function(_formId) {
		var data = getCurrentStatus(_formId);
		var status = data.formStatus;
		if (status == '已处理') {
			alert("配送反审核单已处理！")
			return false;
		}
		if (data.hasLock) {
			alert("单据正在编辑或处理中！")
			return false;
		}
		return true;
	};

	this.doAudit = function(row) {
		if (!instance.checkStatus(row.formId)) {
			return;
		}
		var _url = appRoot+ "/restaurant/antiaudit/audit/auditView.action?formRefId="+ row.formRefId + "&parentTabId=" + tabId;
		_url = getUrl(_url);
		
		var _title = '配送单反审核' + row.formRefId;
		addTab(_title, _url);
	};

	this.getColumn = function() {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable:false
		}, {
			label : '配送单号',
			field : 'formRefId',
			className:'text-center',
			sortable:false
		}, {
			label : '反审核部门',
			field : 'antiauditBranch',
			sortable:false
		}, {
			label : '反审核人员',
			field : 'antiauditor',
			className:'text-center',
			sortable:false
		}, {
			label : '反审核日期',
			field : 'antiauditTime',
			className:'text-center',
			sortable:false
		}, {
			label : '反审核备注',
			field : 'formNote',
			sortable:false
		}, {
			label : '配送部门',
			field : 'provider',
			sortable:false
		}, {
			label : '配送日期',
			field : 'receiveTime',
			className:'text-center',
			sortable:false
		}, {
			label : '订货部门',
			field : 'requester',
			sortable:false
		}, {
			label : '入库人员',
			field : 'inputer',
			className:'text-center',
			sortable:false
		}, {
			label : '入库日期',
			field : 'inputTime',
			className:'text-center',
			sortable:false
		}, {
			label : '配送单备注',
			field : 'snote',
			sortable:false
		}, {
			label : '主要配送品',
			field : 'maxPayItem',
			sortable:false
		}, {
			label : '单据状态',
			field : 'formStatus',
			className:'text-center',
			sortable:false
		}, {
			label : '处理',
			field : 'operate',
			className:'text-center',
			renderCell : function(object, data) {
				return hrefFmt("反审核处理-", instance.doAudit, object);
			},
			sortable:false
		}, {
			label : "",
			field : "none",
			sortable:false
		} ];
	};
}
