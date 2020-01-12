function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		'productName' : dojo.byId('productName').value
	}
}

var grid = null;
var dataStore = null;
require([ "dojo", "dojo/ready", "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable",
		"dojo/store/Cache", "dojo/store/Memory", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection",
		"dgrid/extensions/ColumnResizer" ], function(dojo, ready, OnDemandGrid, Server, Observable, Cache, Memory,
		declare, selector, Selection, ColumnResizer) {
	ready(function() {
		initGrid();
	});

	function initGrid() {
		var _url = appRoot + "/hq/sample/manage/doQuery.action?queryType=unaudit";
		_url = getUrl(_url);
		
		dataStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);

		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			allowSelectAll : true,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	}

	function getColumn() {
		return [ selector({
			field : 'rownumber'
		}), {
			label : "序号",
			field : "rownumber",
			sortable : false
		}, {
			label : "产品名称",
			field : "productName",
			sortable : false
		}, {
			label : '单据状态',
			field : 'formStatus',
			sortable : false
		}, {
			label : '审核',
			field : 'operate',
			renderCell : function(object, data) {
				return hrefFmt("审核", doAudit, object);
			},
			sortable : false
		}, {
			label : "起订量",
			field : "minimumOrderQuantity",
			sortable : false
		}, {
			label : "产地",
			field : "producePlace",
			sortable : false
		}, {
			label : "订货周期(天)",
			field : "orderCycle",
			sortable : false
		}, {
			label : "使用周期(天)",
			field : "useCycle",
			sortable : false
		}, {
			label : '上传资格证照片',
			field : 'qualificationPic',
			formatter : picFmt,
			sortable : false
		}, {
			label : '上传照片',
			field : 'samplePic',
			formatter : picFmt,
			sortable : false
		}, {
			label : "价格(元)",
			field : "price",
			sortable : false
		}, {
			label : "毛价(元)",
			field : "grossPrice",
			sortable : false
		}, {
			label : "毛重(克)",
			field : "grossWeight",
			sortable : false
		}, {
			label : "净重(克)",
			field : "netWeight",
			sortable : false
		}, {
			label : "出净率",
			field : "netRatio",
			sortable : false
		}, {
			label : "保质期(月)",
			field : "shelfLife",
			sortable : false
		}, {
			label : "送样时间(天)",
			field : "deliveryTime",
			sortable : false
		}, {
			label : "验收标准",
			field : "accptanceCriteria",
			sortable : false
		}, {
			label : "供应商",
			field : "supplier",
			sortable : false
		}, {
			label : "",
			field : "none",
			sortable : false
		} ];
	}

});

function picFmt(value, rowData) {
	var genHtml = '';
	if (value != '') {
		var _picUrl = appRoot + "/common/loadImage.action?picId=" + value;
		_picUrl += "&timestamp=" + new Date().getTime(); // 防止缓存
		genHtml += '<a href="' + _picUrl + '" target="_blank">查看</a>';
	}
	return genHtml;
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);

	var status = data.formStatus;
	if (status == '已审核') {
		alert("单据已审核！");
		return false;
	}
	return true;
}

function doAudit(row) {
	if (!checkStatus(row.id)) {
		return;
	}

	var _url = appRoot + "/hq/sample/audit/doAudit.action";
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				id : row.id
			}
		}).then(function(data) {
			alert("审核成功！");
			doQuery();
		}, function(err) {
			console.log(err);
		});
	});
}

function doDelete() {
	var selectArr = [];
	for ( var id in grid.selection) {
		if (grid.selection[id]) {
			selectArr.push(id);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/sample/manage/doDelete.action";
	
	if (confirm("确定删除选定的样品信息吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					ids : selectArr.join(',')
				}
			}).then(function(data) {
				alert("删除成功！");
				doQuery();
			}, function(err) {
			});
		});
	}
}
