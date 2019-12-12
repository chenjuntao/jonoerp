var grid = null;
var dataStore = null;
var doSubmit = null;

require([ "dojo/request/xhr", "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/Selection",
		"dojo", "dojo/date", "dojo/ready" ],
		function(xhr, OnDemandGrid, Server, declare, Selection, dojo, date, ready) {
			ready(function() {
				initGrid();
			});

			function getMonthDate() {
				var month = new Date(dojo.byId('monthDate').value);
				var days = date.getDaysInMonth(month);
				var oldDate = dojo.byId('oldDate').value;
				if (oldDate < days) {
					days = oldDate;
				}
				dojo.byId('closeDate').innerHTML = "关账日为" + days;

				var monthDate = dojo.byId('monthDate').value + "-" + days;
				return monthDate;
			}

			function initGrid() {
				var _url = appRoot + "/settle/month/queryOvertime.action";
				_url = getUrl(_url);
				
				dataStore = new Server({
					target : _url,
					idProperty : "branchId",
					query : function(query, options) {
						if (query.monthDate == undefined) {
							query = {
								monthDate : getMonthDate()
							};
						}
						return Server.prototype.query.call(this, query, options);
					}
				});

				var CustomGrid = declare([ OnDemandGrid, Selection ]);
				grid = new CustomGrid({
					store : dataStore,
					columns : getColumn(),
					allowSelectAll : true,
					cellNavigation : false,
					selectionMode : "single",
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
			}
			doSubmit = function() {
				dojo.byId("btnSubmit").disabled = 'disabled';

				var _url = appRoot + "/settle/month/checkStatus.action?monthDate=" + getMonthDate();
				_url = getUrl(_url);
				
				xhr.post(_url, {
					handleAs : "json"
				}).then(function(data) {
					dojo.byId("btnSubmit").disabled = false;
					if (data.exist) {
						alert(getMonthDate() + "后已经完成过月结工作！");
					} else {
						doSettle();
					}
				}, function(err) {
					errorHandler(err);
				});
			}

			doQuery = function() {
				grid.set('query', {
					monthDate : getMonthDate()
				});
			}

			doSettle = function() {
				var rows = dataStore.getData();
				if (rows.length > 0) {
					alert('请通知相关部门完成日结！');
					return;
				}
				var _url = appRoot + "/settle/month/settleView.action?monthDate=" + getMonthDate();
				_url = getUrl(_url);
				_title = '月结';
				
				addTab(_title, _url);
			}

		});

function getDaysInMonth(temp) {

	// month = parseInt(month, 10); //
	// parseInt(number,type)这个函数后面如果不跟第2个参数来表示进制的话，默认是10进制。
	// var temp = new Date(year, month, 0);
	return temp.getDate();
}

function getColumn() {
	return [ {
		label : "序号",
		sortable:false,
		field : "rownumber"
	}, {
		label : "编号",
		sortable:false,
		field : "branchId"
	}, {
		label : "名称",
		sortable:false,
		field : "branchName"
	}, {
		label : "营业日期",
		sortable:false,
		field : "businessDate"
	}, {
		label : "助记码",
		sortable:false,
		field : "queryCode"
	}, {
		label : "位置",
		sortable:false,
		field : "branchAddress"
	}, {
		label : "联系人",
		sortable:false,
		field : "contactMan"
	}, {
		label : "电子邮箱",
		sortable:false,
		field : "email"
	}, {
		label : "电话",
		sortable:false,
		field : "telephone"
	}, {
		label : "传真",
		sortable:false,
		field : "fax"
	}, {
		label : "备注",
		sortable:false,
		field : "remark"
	}, {
		label : "",
		sortable:false,
		field : "none"
	} ];
}
