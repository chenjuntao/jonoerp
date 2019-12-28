var inputGrid = new InputGrid('inputGrid');

function InputGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.query = function(_query) {
		this.grid.set('query', getQuery());
	};

	this.init = function(_query) {
		var _url = appRoot + "/restaurant/putinstorage/outside/doQuery.action?queryType=unaudit&branchType="
				+ branchType;
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
		});
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

	function checkStatu(_formId) {
		var data = getCurrentStatus(_formId);
		var status = data.formStatus;

		if (status == '已结案') {
			alert("工单已结案，不能进行生产入库！");
			return false;
		}
		return true;
	}

	this.doAudit = function(row) {
		if (!instance.checkStatus(row.formId)) {
			return;
		}
		if (!checkStatu(row.formRefId)) {
			return;
		}
		var _url = appRoot + "/restaurant/putinstorage/confirm/auditView.action?formId=" + row.formId + "&parentTabId="
				+ tabId;
		if (row.formType == 'MANUAL') {
			_url = appRoot + "/lc/stock/in/audit/mauditView.action?formId=" + row.formId + "&parentTabId=" + tabId;
		}
		_url = getUrl(_url);
		
		var _title = '入库单审核' + row.formId;
		addTab(_title, _url);
	};

	this.getColumn = function() {
		return [ {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : '入库单号',
			field : 'formId',
			sortable : false
		}, {
			label : '供应商',
			field : 'provider',
			className : 'input-provider', // 与反审核中配送部门同名，有冲突,
			sortable : false
		}, {
			label : '采购单号',
			field : 'formRefId',
			sortable : false
		}, {
			label : '入库部门',
			field : 'inputDepartment',
			sortable : false
		}, {
			label : '入库人员',
			field : 'inputer',
			sortable : false
		}, {
			label : '入库日期',
			field : 'inputTime',
			sortable : false
		}, {
			label : '备注',
			field : 'formNote',
			sortable : false
		}, {
			label : '主要入库品',
			field : 'maxPayItem',
			sortable : false
		}, {
			label : '入库总额',
			field : 'allPayAmt',
			sortable : false
		}, {
			label : '单据状态',
			field : 'formStatus',
			sortable : false
		}, {
			label : '审核操作',
			field : 'audit',
			renderCell : function(object, data) {
				return hrefFmt("审核", instance.doAudit, object);
			},
			sortable : false
		}, {
			label : "",
			field : "none"
		} ];
	};
}
