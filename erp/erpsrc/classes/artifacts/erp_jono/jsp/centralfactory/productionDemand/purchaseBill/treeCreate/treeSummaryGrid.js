var treeSummaryGrid = new TreeSummaryGrid('treeSummaryGrid');

function TreeSummaryGrid(gridId) {
	var instance = this;

	this.grid = null;

	this.init = function() {
		var _url = appRoot
				+ "/centralfactory/productionDemand/purchaseBill/treeCreate/treeGridData.action?ids="
				+ dojo.byId('ids').value;
		_url = getUrl(_url);
		
		require([ "dojo/_base/declare", "dgrid/OnDemandGrid",
				"dojo/store/Observable", "dgrid/Selection",
				"dojo/store/Memory", "dgrid/tree", "dojo/_base/lang",
				"dojo/request/xhr", "dgrid/ColumnSet" ], function(declare,
				OnDemandGrid, Observable, Selection, Memory, tree, lang, xhr,
				ColumnSet) {
			var myStore = {};

			xhr(_url, {
				handleAs : "json"
			}).then(
					function(data) {
						myStore = Observable(new Memory({
							data : data,
							idProperty : 'id',
							query : function(query, options) {
								query = query || {};
								options = options || {};

								if (!query.parent && !options.deep) {
									// Default to a single-level query for root
									// items (no parent)
									query.parent = undefined;
								}
								return this.queryEngine(query, options)(
										this.data);
							},
							getChildren : function(parent, options) {
								// Support persisting the original query via
								// options.originalQuery
								// so that child levels will filter the same way
								// as the root level
								return this.query(lang.mixin({}, options
										&& options.originalQuery || null, {
									parent : parent.id
								}), options);
							},
							mayHaveChildren : function(parent) {
								return parent.formId == "head"
										|| parent.formId == "second";
							}
						}));

						var CustomGrid = declare([ OnDemandGrid, Selection,
								ColumnSet ]);
						instance.grid = new CustomGrid({
							store : myStore,
							columnSets : instance.getColumn(tree),
							cellNavigation : false,
							selectionMode : "single",
							loadingMessage : '加载中...',
							noDataMessage : "无数据！"
						}, gridId);

						instance.grid.startup();

					}, function(err) {
						// Handle the error condition
					});

		});
	};

	this.getColumn = function(tree) {
		return [
				[ [ tree({
					label : "序号",
					field : "rownumber",
					shouldExpand : function() {
						// console.dir(arguments) 查看其参数详细情况
						var depth = arguments[1];
						return depth == 0 ;
					}
				}), {
					label : "供应商",
					field : "provider"
				} ] ],
				[ [
						{
							label : "原料编码",
							field : "itemId"
						},
						{
							label : "原料名称",
							field : "itemName"
						},
						{
							label : "库存单位",
							field : "itemDimension"
						},
						{
							label : "规格",
							field : "itemSpecification"
						},
						{
							label : "订单编号",
							field : "formId",
							renderCell : function(object, data) {
								var cell = object.formId;
								return cell == "head" || cell == "second" ? ""	: hrefFmt(cell, doScan, object);
							}
						},
						{
							label : "毛需求",
							field : "item_count",
							formatter : function(cell, rowObject) {
								return rowObject.formId == "head" ? "" : cell;
							}
						},
						{
							label : "安全库存",
							field : "safeCount",
							formatter : function(cell, rowObject) {
								return rowObject.formId == "head"
										|| rowObject.formId.indexOf("SCJH") >= 0 ? ""
										: cell;
							}
						},
						{
							label : "实际库存",
							field : "realCount",
							formatter : function(cell, rowObject) {
								return rowObject.formId == "head"
										|| rowObject.formId.indexOf("SCJH") >= 0 ? ""
										: cell;
							}
						},
						{
							label : "在途量",
							field : "onTheWayCount",
							formatter : function(cell, rowObject) {
								return rowObject.formId == "head"
										|| rowObject.formId.indexOf("SCJH") >= 0 ? ""
										: cell;
							}
						},
						{
							label : "净需求",
							field : "netRequirement",
							formatter : function(cell, rowObject) {
								return rowObject.formId == "head"
										|| rowObject.formId.indexOf("SCJH") >= 0 ? ""
										: cell;
							}
						} ] ] ];
	};
}
