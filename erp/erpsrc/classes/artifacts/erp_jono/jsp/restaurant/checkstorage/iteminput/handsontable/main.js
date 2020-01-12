$(function() {
	addEvent();
	initGrid();
});

function printList() {
	prn1_preview(getExportArgs());
}

function addEvent() {
	$(".Wdate").focus(function(e) {
		WdatePicker();
	});

	$('#btn_submit').bind('click', doSubmit);
}

var formLst = [];
function loadCheckList(checkBatchId, checkId) {
	var _url = appRoot + "/restaurant/checkstorage/imanage/queryCheckList.action";
	_url = getUrl(_url);

	$.ajax({
		type : "POST",
		url : _url,
		data : {
			batchId : $(checkBatchId).val()
		},
		error : function() {
			console.error("query failed");
		},
		success : function(data) {
			if (data.msg) {
				var formSelector = document.getElementById("checkId");
				console.dir(formSelector);
				formSelector.length = 0;
				for (var i = 0, length = data.msg.length; i < length; i++) {
					var item = data.msg[i].formId;
					formSelector.options.add(new Option(item, item));
				}
			} else {
			}

			$.isLoading("hide");
		}
	});
}

function doValidate() {
	if (!validateColumn(grid, 'checkCount')) {
		return false;
	}
	return true;
}

function getGridData() {
	var arrLst = $('#dataGrid').handsontable('getData');
	var objLst = [];
	$(arrLst).each(function(i, rowArr) {
		var rowObj = {};
		$(columns).each(function(j, col) {
			var key = col.data;
			rowObj[key] = rowArr[j];
		});
		objLst.push(rowObj);
	});
	return objLst;
}

var grid = null;
var dataStore = null;

function initGrid() {
	$('#dataGrid').handsontable({
		data : [],
		colHeaders : colHeaders,
		columns : columns,
		className : "htCenter",
		colWidths : colWidths,
		manualColumnResize : true,
		rowHeaders : true,
		allowInvalid : false,
		height : 395
	});

	$.isLoading({
		text : "加载中"
	});
	loadGridData();
}

function loadGridData() {
	var _url = appRoot + "/restaurant/checkstorage/manage/queryDetail.action";
	_url = getUrl(_url);

	$.ajax({
		type : "POST",
		url : _url,
		data : {
			formId : $('#checkId').val()
		},
		error : function() {
			console.error("query failed");
		},
		success : function(data) {
			$('#dataGrid').handsontable('loadData', data);
			$.isLoading("hide");
		}
	});
}

var columns = [ {
	data : 'rownumber',
	readOnly : true
}, {
	data : 'itemId',
	readOnly : true
}, {
	data : 'itemName',
	readOnly : true
}, {
	data : 'itemDimension',
	readOnly : true
}, {
	data : 'itemSpecification',
	readOnly : true
}, {
	data : 'itemPrice',
	readOnly : true
}
// , {
// data : 'itemCategory',
// readOnly : true
// }
// , {
// data : 'expiredTime',
// readOnly : true
// }
, {
	data : 'checkCount',
	type : 'numeric',
	format : '0.0000',
	id : 'hehe'
} ];

var colHeaders = [ '序号', '原料编码', '原料名称', '单位', '规格', '标准单价', '盘点数量' ];
var colWidths = [ 60, 80, 200, 80, 150, 120, 120 ];

function doSubmit() {
	var rows = getGridData();
	if (rows.length > 0) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].checkCount == 0) {
				if (!confirm(rows[i].itemName + "输入为零，确定提交吗？")) {
					return;
				}
			}
		}
	}
	$('#jsonData').val(JSON.stringify(rows));
	var $branchId = $('#branchId').val();
	$('#checkBranch').val($("#branchId").find("option:selected").text());

	var _url = appRoot + '/restaurant/checkstorage/iteminput/checkView.action';
	_url = getUrl(_url);

	addPostTab('billForm', '餐厅盘点清单输入确认', _url);
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
