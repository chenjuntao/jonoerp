var requestGrid = new RequestGrid('requestGrid');

function RequestGrid(gridId) {
	var instance = this;

	this.grid = null;
	this.dataStore = null;

	this.query = function(_query) {
		this.grid.set('query', _query);
	};

	this.getData = function() {
		return this.dataStore.getData();
	};

	this.init = function(_query) {
		var _url = appRoot
				+ "/lc/request/addition/doQuery.action?formType=request";
		_url = getUrl(_url);
		
		require([ "dgrid/OnDemandGrid", "custom/store/Server",
				"dojo/store/Observable", "dojo/store/Cache",
				"dojo/store/Memory" ], function(OnDemandGrid, Server,
				Observable, Cache, Memory) {
			instance.dataStore = Observable(Cache(Server({
				target : _url,
				query : function(query, options) {
					if (query.startDate == undefined) {
						query = _query;
					}
					return Server.prototype.query.call(this, query, options);
				}
			}), Memory()));

			instance.grid = new OnDemandGrid({
				store : instance.dataStore,
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

	this.getColumn = function() {
		return [ {
			label : "序号",
			field : "rownumber"
		}, {
			label : '要货单编号',
			field : 'formId'
		}, {
			label : '要货部门',
			field : 'buyerName'
		}, {
			label : '制单人',
			field : 'formMaker'
		}, {
			label : '制单日期',
			field : 'formTime'
		}, {
			label : '到货日期',
			field : 'receiveTime'
		}, {
			label : '到货周期',
			field : 'arrivePeriod'
		}, {
			label : '备注',
			field : 'formNote'
		}, {
			label : '主要要货',
			field : 'maxPayItem'
		}, {
			label : '要货总额',
			field : 'allPayAmt'
		}, {
			label : '查看',
			field : 'scan',
			renderCell : function(object, data) {
				return hrefFmt("查看", instance.doScan, object);
			}
		}, {
			label : '操作',
			field : 'operate',
			renderCell : function(object, data) {
				return hrefFmt("生成采购单", instance.doCreate, object);
			}
		}, {
			label : "",
			field : "none"
		} ];
	};

	this.doScan = function(row) {
		var _url = appRoot
				+ "/restaurant/goodsbill/query/scanView.action?formId="
				+ row.formId;
		
		_url = getUrl(_url);
		addTab("查看", _url);
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

	this.doCreate = function(row) {
		if (!instance.checkStatus(row.formId)) {
			return;
		}
		var _url = appRoot + "/lc/request/addition/createView.action?formId="
				+ row.formId + "&parentTabId=" + tabId;
		_url = getUrl(_url);
		
		addTab("生成采购单" + row.formId, _url);
	}
}
