var orderGrid = new OrderGrid('orderGrid');

function OrderGrid(gridId) {
	var instance = this;
	this.grid = null;
	this.dataStore = null;

	this.set = function(pro, store) {
		instance.grid.set('store', store);
	}

	this.init = function(_query) {
		var _url = appRoot + "/outerorder/handle/query.action";
		
		require([ "dgrid/ColumnSet", "dojo/_base/lang", "dojo/request/xhr",
				"dojo/_base/array", "dojo/_base/declare", "dgrid/Selection",
				"dgrid/OnDemandGrid", "custom/store/Server",
				"dojo/store/Observable", "dojo/store/Cache",
				"dojo/store/Memory" ], function(ColumnSet, lang, xhr,
				arrayUtil, declare, Selection, OnDemandGrid, Server,
				Observable, Cache, Memory) {

			xhr.post(_url, {
				handleAs : "json",
				data : getQuery()
			}).then(
					function(data) {
						instance.dataStore = Observable(new Memory({
							data : data,
							idProperty : 'form_id'
						}));

						var CustomGrid = declare([ OnDemandGrid, Selection,
								ColumnSet ]);
						instance.grid = new CustomGrid({
							store : instance.dataStore,
							columnSets : instance.getColumn(),
							cellNavigation : false,
							loadingMessage : '加载中...',
							noDataMessage : "无数据！"
						}, gridId);

						instance.grid.startup();
					}, function(err) {
						// Handle the error condition
					});
		});
	};


	this.getColumn = function() {
		return [
				[ [
						{
							label : "序号",
							field : "rownumber",
							formatter : function(value, row) {
								return !row.form_id ? "<b>" + value + "</b>"
										: value;
							}
						},
						{
							label : "单据编号",
							field : "form_id",
							renderCell : function(item, data) {
								return hrefFmt(!item.form_id ? ''
										: item.form_id, instance.doScan, item);
							}
						} ] ],
				[ [
						{
							label : "订货部门",
							field : "buyer_name"
						},
						{
							label : "供应商",
							field : "provider"
						},
						{
							label : "到货时间",
							field : "receive_time"
						},
						{
							label : "审核日期",
							field : "audit_time"
						},
						{
							label : "主要采购品",
							field : "max_pay_item"
						},
						{
							label : "总金额",
							field : "all_pay_amt",
							formatter : function(value, row) {
								return !row.form_id ? "<b>" + value + "</b>"
										: value;
							}
						}, {
							label : "备注说明",
							field : "form_note"
						} ] ] ];
	};

	this.doScan = function(row) {
		var _url = appRoot	+ "/outerorder/handle/scanView.action?formId="	+ row.form_id;
		
		addTab(row.form_id + "订货单查看", _url);
	};

}
