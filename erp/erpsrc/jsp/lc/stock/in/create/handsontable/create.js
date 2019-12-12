$(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	$(".Wdate").focus(function(e) {
		WdatePicker();
	});
	// $('#btn_submit').bind('click', doSubmit);
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

function doSubmit() {
	var oldrows = getGridData();
	oldrows.sort(function(a, b) {
		return b.itemId - a.itemId;
	});
	var oldItemId = "";
	var rows = [];
	if (oldrows.length > 0) {

		var orderCount = 0.0;
		var receivedCount = 0.0;
		var receiveCount = 0.0;
		for (var i = 0; i < oldrows.length; i++) {
			var item = oldrows[i];
			if ((item.receiveCount + item.receivedCount) > item.orderCount * item.outReceiveRate) {
				alert(item.itemName + "入库数不能超过限制比例！");
				return;
			}
			var itemId = item.itemId;
			if (itemId == oldItemId) {
				orderCount = item.orderCount += orderCount;
				receivedCount = item.receivedCount += receivedCount;
				receiveCount = item.receiveCount += receiveCount;
				item.differentCount = item.orderCount - item.receivedCount - item.receiveCount;
				item.sumItemCount = item.receiveCount + item.receivedCount;
				item.payAmt = parseFloat((item.receiveCount * item.itemUnitPrice).toFixed(4));
				item.receiveAmt = parseFloat((item.receiveCount * item.receivePrice).toFixed(4));
				rows.push(item);
				oldItemId = itemId;
				for (var j = 0; j < rows.length; j++) {
					var item2 = rows[j];
					if (itemId == item2.itemId) {
						item2.differentCount = item.differentCount;
						item2.sumItemCount = item.sumItemCount;
						item2.receiveCount = item.receiveCount;
						item2.payAmt = item.payAmt;
						item2.receiveAmt = item.receiveAmt;
						item2.orderCount = item.orderCount;
						item2.receivedCount = item.receivedCount;
					}
				}
			} else {
				orderCount = item.orderCount;
				receivedCount = item.receivedCount;
				receiveCount = item.receiveCount;
				item.sumItemCount = item.receiveCount + item.receivedCount;
				item.differentCount = item.orderCount - item.receivedCount - item.receiveCount;
				item.payAmt = parseFloat((item.receiveCount * item.itemUnitPrice).toFixed(4));
				item.receiveAmt = parseFloat((item.receiveCount * item.receivePrice).toFixed(4));
				rows.push(item);
				oldItemId = itemId;
			}
		}
	} else {
		alert("请选择物料！");
		return;
	}
	var temp = "";
	var i = 1;
	var newRows = [];
	for (var j = 0, length = rows.length - 1; j <= length; j++) {
		if (rows[j].itemId != temp) {
			rows[j].rownumber = i;
			newRows.push(rows[j]);
			i++;
			temp = rows[j].itemId;
		}
	}
	var $storageId = $('storageId').val();
	$('#storage').val($("#storageId").find("option:selected").text());

	$('#jsonData').val(JSON.stringify(newRows));
	var _url = appRoot + '/lc/stock/in/create/checkView.action?parentTabId=' + tabId;
	_url = getUrl(_url);
	
	addPostTab('billForm', '入库单生成确认', _url);
}

var grid = null;
var dataStore = null;

function initGrid() {
	$('#dataGrid').handsontable({
		data : [],
		colHeaders : colHeaders,
		columns : columns,
		rowHeaders : true,
		fixedColumnsLeft : 7,
		manualColumnFreeze : true,
		allowInvalid : false,
		height : 450
	});

	$.isLoading({
		text : "加载中"
	});

	var supplierId = $('#supplierId').val();
	var formId = $('#formId').val();
	var _url = appRoot + "/lc/stock/in/create/queryUnifiedDetail.action?formId=" + formId + "&supplierId=" + supplierId;
	_url = getUrl(_url);
	
	$.ajax({
		type : "POST",
		url : _url,
		data : {
			deliveryType : deliveryType
		},
		error : function() {
			console.error("query failed");
		},
		success : function(data) {

			// 如果没有统配相关的数据，则提供进入下一步的选择
			if (data.length == 0) {
				alert('无统配，直接进入下一步！');
				doSubmit();
				return;
			}
			$(data.rows).each(function(i, row) {
				console.dir(row)
				row.payAmt = parseFloat((row.receiveCount * row.itemUnitPrice).toFixed(4)); // 计算金额
				row.receiveAmt = parseFloat((row.receiveCount * row.receivePrice).toFixed(4)); // 计算金额
			});

			$('#dataGrid').handsontable('loadData', data.rows);
			$.isLoading("hide");
		}
	});
}

function dataChangeHandler(event) {
	var field = event.cell.column.field;
	if (field == 'receiveCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		var oldCount = row.receiveCount;
		row[field] = event.value;
		row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		row.receiveAmt = parseFloat((event.value * row.receivePrice).toFixed(2)); // 计算金额
		row.differentCount = row.orderCount - (event.value + row.receivedCount); // 计算实收差异
		var rows = dataStore.query();
		for (var i = 0; i < rows.length; i++) {
			if (row.itemId == rows[i].itemId) {
				rows[i].sumItemCount += event.value - oldCount;
				dataStore.put(rows[i]);
			}
		}
		// dataStore.put(row);
	}
}

var columns = [ {
	data : 'itemId',
	readOnly : true
}, {
	data : 'itemName',
	readOnly : true
}, {
	data : 'itemCategory',
	readOnly : true
}, {
	data : 'itemDimension',
	readOnly : true
}, {
	data : 'itemSpecification',
	readOnly : true
}, {
	data : 'receive',
	readOnly : true
}, {
	data : 'supplyCycle',
	readOnly : true,
	type : 'numeric',
	format : '0.00'
}, {
	data : 'orderCount',
	readOnly : true,
	type : 'numeric',
	format : '0.00'
}, {
	data : 'receivedCount',
	readOnly : true,
	type : 'numeric',
	format : '0.00'
}, {
	data : 'receiveCount',
	type : 'numeric',
	format : '0.00'
}, {
	data : 'outReceiveRate',
	readOnly : true,
	type : 'numeric',
	format : '0.00'
}, {
	data : 'itemUnitPrice',
	readOnly : true,
	type : 'numeric',
	format : '0.00'
}, {
	data : 'payAmt',
	readOnly : true,
	type : 'numeric',
	format : '0.00'
}, {
	data : 'receivePrice',
	readOnly : true,
	type : 'numeric',
	format : '0.00'
}, {
	data : 'receiveAmt',
	readOnly : true,
	type : 'numeric',
	format : '0.00'
}, {
	data : 'expiredTime',
	readOnly : true
} ];
var colHeaders = [ '原料编码', '原料名称', '类别', '单位', '规格', '收货部门', '供货周期', '订货数量', '已入库数量', '实收数量', '超收率', '标准单价', '标准金额',
		'进货单价', '进货金额', '有效期' ];
function doClose() {
	closeTab(tabId);
}
