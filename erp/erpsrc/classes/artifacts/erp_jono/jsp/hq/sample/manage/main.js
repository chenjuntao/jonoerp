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
		var _url = appRoot + "/hq/sample/manage/doQuery.action";
		
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
			label : '修改',
			field : 'operate',
			formatter : operateFmt,
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
			formatter : qPicFmt,
			sortable : false
		}, {
			label : '上传照片',
			field : 'samplePic',
			formatter : sPicFmt,
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

function operateFmt(value, rowData) {
	var genHtml = '';
	if (rowData.formStatus != '已审核') {
		genHtml += '<a href="#" onclick="doAdd(\'' + rowData.id + '\')">修改</a>';
	}
	return genHtml;
}

function qPicFmt(value, rowData) {
	var genHtml = '';
	if (value != '') {
		var _picUrl = appRoot + "/common/loadImage.action?picId=" + value;
		_picUrl += "&timestamp=" + new Date().getTime(); // 防止缓存
		genHtml += '<a href="' + _picUrl + '" target="_blank">查看</a>';

		if (rowData.formStatus != '已审核') {
			genHtml += '<a href="#" onclick="doUpload(\'' + rowData.id + '\')" style="padding-left: 10px;">重新上传</a>';
		}
	} else {
		if (rowData.formStatus != '已审核') {
			genHtml += '<a href="#" onclick="doUpload(\'' + rowData.id + '\')">上传</a>';
		}
	}
	return genHtml;
}

function sPicFmt(value, rowData) {
	var genHtml = '';
	if (value != '') {
		var _picUrl = appRoot + "/common/loadImage.action?picId=" + value;
		_picUrl += "&timestamp=" + new Date().getTime(); // 防止缓存
		genHtml += '<a href="' + _picUrl + '" target="_blank">查看</a>';

		if (rowData.formStatus != '已审核') {
			genHtml += '<a href="#" onclick="uploadSample(\'' + rowData.id
					+ '\')" style="padding-left: 10px;">重新上传</a>';
		}
	} else {
		if (rowData.formStatus != '已审核') {
			genHtml += '<a href="#" onclick="uploadSample(\'' + rowData.id + '\')">上传</a>';
		}
	}
	return genHtml;
}

var editDlg = null;
function doAdd(_id) {
	var _title = "新增";
	var _url = appRoot + "/hq/sample/manage/createView.action";
	if (_id != undefined) {
		_title = "修改";
		_url = appRoot + "/hq/sample/manage/editView.action";
		_url += "?id=" + _id;
	}
	
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "300px"
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
	doQuery();
	editDlg.hide();
}

var uploadDlg = null;
function doUpload(_id, _picType) {
	var _title = "上传";
	var _url = appRoot + "/hq/sample/manage/uploadView.action?id=" + _id;
	if (_picType != undefined) {
		_url += "&picType=" + _picType;
	}
	
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

function uploadSample(_id) {
	doUpload(_id, 'sample');
}

function closeUploadDlg(data) {
	doQuery();
	uploadDlg.hide();
}

function doDelete() {
	var selectArr = [];
	for ( var id in grid.selection) {
		if (grid.selection[id]) {
			var rowData = dataStore.get(id);
			if (rowData.formStatus == '已审核') {
				alert('[' + rowData.productName + ']已审核，不允许删除！');
				return;
			}
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
