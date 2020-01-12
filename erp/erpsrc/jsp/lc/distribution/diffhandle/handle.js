var reasonData = [ {
	"key" : "10",
	"value" : "拒收"
}, {
	"key" : "20",
	"value" : "报损"
} ];

var grid = null;
var dataStore = null;
var reasonStore = null;

/**
 * function
 */
var doHandle = null;

var standby = null;

require([ "dojo", "dojo/ready", "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory",
		"dojo/data/ObjectStore", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/extensions/ColumnResizer",
		"dgrid/editor", "dijit/form/Select", "dojo/request/xhr", "dojo/dom-form", "dgrid/ColumnSet",
		"dojox/widget/Standby" ], function(dojo, ready, OnDemandGrid, Observable, Memory, ObjectStore, declare,
		Keyboard, ColumnResizer, editor, Select, xhr, domForm, ColumnSet, Standby) {
	ready(function() {
		// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();

		initGrid();
	});

	function initGrid() {
		reasonStore = new ObjectStore({
			objectStore : new Observable(new Memory({
				idProperty : "value",
				data : reasonData,
				sort : [ {// 这里排序不起作用，要何如做才行，以后再研究
					attribute : "key",
					descending : false
				} ]
			})),
			labelProperty : "value"
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			columnSets : getCols(),
			cellNavigation : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
		loadGridData();
	}

	function loadGridData() {
		var _url = appRoot + "/lc/distribution/diffhandle/queryDetail.action?formId=" + formId;
		_url = getUrl(_url);

		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "itemId",
				data : data
			}));
			grid.set("store", dataStore);
		}, function(err) {
		});
	}

	function getCols() {
		return [ [ [ {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "商品编码",
			field : "itemId",
			sortable : false
		}, {
			label : "商品名称",
			field : "itemName",
			sortable : false
		} ] ], [ [ {
			label : "类别",
			field : "itemCategory",
			sortable : false
		}, {
			label : "单位",
			field : "itemDimension",
			sortable : false
		}, {
			label : "规格",
			field : "itemSpecification",
			sortable : false
		}, {
			label : "订货数",
			field : "requestCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "实发数",
			field : "deliveryCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "实收数",
			field : "receiveCount",
			className : 'grid-number',
			sortable : false
		}, {
			label : "差异数",
			field : "differentCount",
			className : 'grid-number',
			sortable : false
		}, editor({
			label : "差异类别",
			field : "differentReason",
			className : 'edit-note',
			editorArgs : {
				store : reasonStore,
				maxHeight : 150,
				style : "width:100px;"
			},
			sortable : false
		}, Select, "click"), editor({
			label : "差异备注",
			field : "differentNote",
			editor : "text",
			sortable : false
		}), {
			label : "标准单价",
			field : "itemUnitPrice",
			className : 'grid-number',
			sortable : false
		}, {
			label : "标准金额",
			field : "payAmt",
			className : 'grid-number',
			sortable : false
		}, {
			label : "",
			field : "none",
			sortable : false
		} ] ] ];
	}

	doHandle = function() {
		grid.save().then(function(value) {
			var rows = dataStore.query();

			var noneItem = [];
			for (var i = 0; i < rows.length; i++) {
				if (rows[i].differentReason == '') {
					noneItem.push(rows[i].itemName);
				}
			}
			if (noneItem.length > 0) {
				alert(noneItem.join('、') + "的差异原因不能为空！");
				return;
			}

			dojo.byId('jsonData').value = JSON.stringify(rows);

			var _url = appRoot + "/lc/distribution/diffhandle/doHandle.action";
			_url = getUrl(_url);

			standby.show();

			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				if (data.msg == 'ok') {
					if (data.formId != undefined) {
						alert("已生成报损单" + data.formId + "，操作成功！");
					} else {
						alert("操作成功！");
					}
					closeTab(tabId, 'doQuery');

					standby.hide();
				} else {
					standby.hide();
					alert("操作失败！");
				}
			}, function(err) {
				standby.hide();
			});
		}, function(err) {
			standby.hide();
			console.log(err);
		});
	}
});
