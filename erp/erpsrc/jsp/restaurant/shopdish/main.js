dojo.ready(function() {
	initTree();
	queryDetail();
});

var g_categoryId = '';

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport1() {
	fillData();
}

function customExport() {
	var condition = dojo.byId('condition').value.trim();
	if (condition == '' && g_categoryId == '') {
		alert("请选择类别或输入查询条件！");
		return;
	}

	var temp = document.getElementsByName("searchType");
	for (var i = 0; i < temp.length; i++) {
		if (temp[i].checked)
			searchType = temp[i].value;
	}

	var _url = appRoot + "/hq/item/product/queryProduct.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				categoryId : g_categoryId,
				condition : condition,
				searchType : searchType,
				newEnd : 1,
				// displayAllFlag : 'Y'
			}
		}).then(function(data) {
			fillData(data);
		}, function(err) {
			errorHandler(err);
		});
	});
}

function showDialog2() {
	dijit.byId('customDialog2').show();
}

function hideDialog2() {
	dijit.byId('customDialog2').hide();
}

// 导出售卖价
function customExport2() {
	fillData2(cols, grid.get('store').getData());
}

function doClose() {
	closeTab(tabId);
}

function initTree() {
	var _url = appRoot + "/restaurant/shopdish/loadTree.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Memory", "dijit/Tree", "dijit/tree/ObjectStoreModel", "dojo/parser",
			"dojo/domReady!" ], function(xhr, Memory, Tree, ObjectStoreModel, parser) {
		parser.parse();
		xhr(_url, {
			handleAs : 'json'
		}).then(function(data) {
			// This store implements the new Dojo Object Store API.
			myStore = new Memory({
				data : data,
				getChildren : function(object) {
					return this.query({
						parent : object.id
					// cbtree默认支持parent
					});
				}
			});

			// Create the model
			var myModel = new ObjectStoreModel({
				store : myStore,
				query : {
					id : 'root'
				},
				mayHaveChildren : function(item) {
					var children = this.store.getChildren(item);
					if (children.length > 0) {
						return true;
					}
					return false;
				}
			});

			var tree = new Tree({
				model : myModel,
				showRoot : false,
				autoExpand : true,
				onClick : function(item, node, evt) {
					if (!node.isExpandable) {// 叶子节点
						g_categoryId = item.id;
						queryDetail();
					}
				}
			}, "categoryTree");
			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

var cols = [ {
	label : "",
	field : "none",
	sortable : false
} ];

/**
 * 点击查询按钮时调用
 */
function doQuery() {
	var condition = dojo.byId('condition').value.trim();
	if (condition.length == 0) {
		alert("请输入查询条件");
		return;
	}

	require([ "dojo/dom-form" ], function(domForm) {
		grid.set('query', domForm.toObject("queryForm"));
	});

}

function queryDetail() {
	if (grid == null) {
		initGrid(g_categoryId);
	} else {
		grid.set('query', {
			categoryId : g_categoryId
		});
	}
}

var grid = null;
var hasData = true;
var myStore = null;
function initGrid(_id) {
	var _url = appRoot + "/hq/item/product/queryProduct.action";
	_url = getUrl(_url);

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dgrid/extensions/Pagination", "dgrid/ColumnSet", "dojo/_base/array",
			"dojo/parser" ], function(Grid, Server, Observable, Cache, Memory, declare, selector, Selection, Keyboard,
			ColumnResizer, Pagination, ColumnSet, array, parser) {
		parser.parse();
		myStore = Observable(Cache(Server({
			target : _url,
			idProperty : "itemId",
			query : function(query, options) {
				if (query.categoryId == undefined) {
					query.categoryId = _id;
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Selection, Keyboard, Pagination, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on('dgrid-error', function(event) {
			errorHandler(event.error);
		});

		grid.startup();

		if (cols.length == 1 && hasData) {
			require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
				xhr.post(appRoot + "/hq/item/meta/queryPrice.action", {
					handleAs : "json",
					data : {
						priceType : " g.PRICE_GROUP_ID LIKE 'SALE%' "
					}
				}).then(function(data) {
					if (data.length == 0) {
						hasData = false;
						return;
					}

					array.forEach(data, function(item) {
						cols.unshift({
							label : item.priceGroupName,
							field : item.priceGroupId,
							className : 'text-right',
							sortable : false
						});
					});
					cols.unshift({
						label : "类别",
						field : "category",
						sortable : false,
						formatter : function(field, renderCell) {
							return "[" + renderCell.categoryId + "]" + renderCell.categoryName;
						}
					}, {
						label : "查看",
						field : "therapy",
						className : 'text-center',
						renderCell : function(rowData) {
							if (!rowData.hasTherapy) {
								var spanEle = document.createElement("span");
								spanEle.innerHTML = '无';
								return spanEle;
							}

							var categoryId = rowData.categoryId;
							var fdStart = categoryId.indexOf(g_suitCateId);
							if (fdStart == 0) {
								return hrefFmt("套餐", scanSuit, rowData);
							}
							return hrefFmt("成本卡", scanTherapy, rowData);
						},
						sortable : false
					}, {
						label : "是否启用",
						field : "enabled",
						className : 'text-center',
						formatter : statusFmt,
						sortable : false
					}, {
						label : "生效日期",
						className : 'text-center',
						field : "effectDate",
						sortable : false
					}, {
						label : "助记码",
						field : "queryCode",
						sortable : false
					}, {
						label : "例牌",
						field : "itemDimension",
						className : 'text-center',
						sortable : false
					}, {
						label : "手持下载",
						field : "bInHandPro",
						className : 'text-center',
						formatter : statusFmt,
						sortable : false
					}, {
						label : "出品折扣",
						field : "bDisFood",
						className : 'text-center',
						formatter : statusFmt,
						sortable : false
					}, {
						label : "照单折扣",
						field : "bDisCount",
						className : 'text-center',
						formatter : statusFmt,
						sortable : false
					});

					grid.set('columnSets', getColumn(selector));
				}, function(err) {
				});
			});
		}
	});
}

function getColumn(selector) {
	return [ [ [ selector({
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		},
		sortable : false
	} ] ], [ cols ] ];
}

function statusFmt(value, rowData) {
	if (value == '1') {
		return '已启用';
	}
	return '';
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/hq/item/product/addView.action?categoryId=" + g_categoryId;
	if (_id != undefined) {
		_title = "修改";
		_url = appRoot + "/hq/item/product/editView.action?categoryId=" + g_categoryId;
		_url += "&itemId=" + _id;
	}
	_url = getUrl(_url);
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "180px"
		}
		createDialog(option, function(iDlg) {
			editDlg = iDlg;
		});
	} else {
		editDlg.set('title', _title);
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		editDlg.show();
	}
}

function closeEditDlg(data) {
	queryDetail();
	editDlg.hide();
}

function doEdit(row) {
	doAdd(row.itemId);
}

var uploadDlg = null;
function doUpload(row) {
	var _title = "上传照片";
	var _url = appRoot + "/hq/item/meta/uploadView.action?itemId=" + row.itemId;
	_url = getUrl(_url);

	var frameId = 'ifr_upload';
	if (uploadDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "90px"
		}
		createDialog(option, function(iDlg) {
			uploadDlg = iDlg;
		});
	} else {
		uploadDlg.set('title', _title);
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		uploadDlg.show();
	}
}

function closeUploadDlg(data) {
	uploadDlg.hide();
}

function doDelete() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			var rowData = myStore.get(itemId);
			if (rowData.enabled == 1) {
				alert('[' + rowData.itemName + ']已启用，不允许删除！');
				return;
			}
			selectArr.push(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/item/meta/doDelete.action";
	_url = getUrl(_url);

	if (confirm("确定删除选定的出品吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					itemIds : selectArr.join(',')
				}
			}).then(function(data) {
				alert("删除成功！");
				queryDetail();
			}, function(err) {
			});
		});
	}
}

var moveDlg = null;
function doMove(row) {
	var _title = "修改类别";
	var _url = appRoot + "/hq/item/meta/moveView.action";
	_url += "?itemId=" + row.itemId + "&itemType=PRODUCT";
	_url = getUrl(_url);

	var frameId = 'ifr_move';
	if (moveDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "350px",
			height : "350px"
		}
		createDialog(option, function(iDlg) {
			moveDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		moveDlg.show();
	}
}

function closeMoveDlg() {
	queryDetail();
	moveDlg.hide();
}

/**
 * 出品信息即时推送
 */
function doPush() {
	var _url = appRoot + "/quartz/manual/transformProduct.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("操作成功！");
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			errorHandler(err);
		});
	});
}

/**
 * 出品成本价即时计算
 */
function doCompute() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		selectArr.push(itemId);
	}
	var _url = appRoot + "/hq/item/product/computeBill.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				therapyIds : selectArr.join(','),
				itemType : "PRODUCT"
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("操作成功！");
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			errorHandler(err);
		});
	});
}

function isFinished() {
	var token = dojo.byId('downloadTokenValue').value;
	var _url = appRoot + "/common/function/isFinish.action";
	_url += "?downloadTokenValue=" + token;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (token == data.msg) {
				clearInterval(int);
				standby.hide();
			}
		}, function(err) {
		});
	});
}

function doExport() {
	var selectArr = [];
	var selectName = [];
	for ( var itemId in grid.selection) {
		var rowData = dataStore.get(itemId);
		selectArr.push(itemId);
		selectName.push(rowData.itemName);
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _type = dijit.byId("typeSelection").get('value');
	var token = new Date().getTime(); // use the current timestamp as the
	dojo.byId('downloadTokenValue').value = token;

	var _url = appRoot + "/hq/item/meta/doExport.action";
	_url += "?timestamp=" + new Date().getTime(); // 防止缓存
	_url = getUrl(_url);

	require([ "dojo/request/iframe" ], function(iframe) {
		standby.show();
		int = setInterval("isFinished()", 1000);
		iframe._currentDfd = null;
		iframe(_url, {
			form : "queryForm",
			method : "POST",
			data : {
				itemIds : selectArr.join(','),
				itemNames : selectName.join(','),
				downloadTokenValue : token,
				type : _type
			},
			handleAs : "json"
		}).then(function(data) {
			if (data.msg != null && parseInt(data.msg) == 1) {
				alert("系统繁忙，请稍后再试！");
				closeTab(tabId);
			}
		}, function(err) {
			alert(err);
		});
	});
}
var doEnable = null;
var g_itemId = null;
var setEffectDate = null;
require([ "dijit/Dialog", "dojo/dom", "dojo/dom-construct", "dojo/date", "dojo/date/locale", "dojo/request/xhr" ],
		function(Dialog, dom, domConstruct, date, locale, xhr) {
			/**
			 * 启停用出品
			 */
			doEnable = function(row) {
				g_itemId = row.itemId;
				var _enabled = row.enabled;
				if (_enabled == 0) {
					showDateDlg(); // 启用出品需要设置生效日期
				} else {
					param = {
						itemId : g_itemId,
						enabled : 0,
					};
					doSend(param);
				}
			}

			var dateDlg = null;
			function showDateDlg() {
				if (dateDlg == null) {
					dateDlg = new Dialog({
						title : "设置生效日期",
						style : "width: 300px;"
					});

					dateDlg.set("content", dom.byId('dateDlg').innerHTML);// 这里相当于复制
					domConstruct.destroy("dateDlg");// 删除原来的标签，避免出现重复的元素
				}
				dateDlg.show();
			}

			/**
			 * 生效日期不能小于当前日期
			 */
			function checkEffectDate() {
				var parseOption = {
					datePattern : "yyyy-MM-dd",
					selector : "date"
				};
				var effectDate = locale.parse(dom.byId('effectDate').value, parseOption);
				var result = date.compare(effectDate, new Date(), "date");

				if (result < 0) {
					alert('生效日期不能小于当前日期!');
					return false;
				}
				return true;
			}

			setEffectDate = function() {
				if (!checkEffectDate()) {
					return;
				}
				param = {
					itemId : g_itemId,
					enabled : 1,
					effectDate : dom.byId('effectDate').value
				};
				doSend(param);
				dateDlg.hide();
			}

			function doSend(param) {
				var _url = appRoot + "/hq/item/meta/doEnable.action";
				_url = getUrl(_url);

				xhr.post(_url, {
					handleAs : "json",
					data : param
				}).then(function(data) {
					if (data.msg == 'ok') {
						alert("操作成功！");
						queryDetail();
					} else {
						alert("操作失败！");
					}
				}, function(err) {
				});
			}
		});

function scanTherapy(rowData) {
	var _url = appRoot + '/restaurant/costcard/mainView.action?foodId=' + rowData.itemId;
	_url = getUrl(_url);

	parent.addTab("查看成本卡-" + rowData.itemName, _url);
}

function editTherapy(rowData) {
	var _url = appRoot + '/hq/item/product/therapyEditView.action?foodId=' + rowData.itemId;
	_url = getUrl(_url);

	parent.addTab("成本卡定义-" + rowData.itemName, _url);
}

function scanSuit(rowData) {
	var _url = appRoot + '/hq/item/suit/scanView.action?foodId=' + rowData.itemId;
	_url = getUrl(_url);

	parent.addTab("查看套餐-" + rowData.itemName, _url);
}

function editSuit(rowData) {
	var _url = appRoot + '/hq/item/suit/mainView.action?foodId=' + rowData.itemId;
	_url = getUrl(_url);

	parent.addTab("套餐定义-" + rowData.itemName, _url);
}