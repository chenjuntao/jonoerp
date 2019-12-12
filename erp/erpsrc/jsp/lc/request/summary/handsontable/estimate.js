$(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	$(".Wdate").focus(function(e) {
		WdatePicker();
	});
	$('#btn_submit').bind('click', doSubmit);
}

function calcSuggest() {
	var rows = getGridData();
	$('#dataGrid').handsontable('loadData', []);
	$.isLoading({
		text : "计算中"
	});
	var _url = appRoot + "/lc/request/summary/queryAverageRequest.action";
	_url = getUrl(_url);

	$.ajax({
		type : "POST",
		url : _url,
		data : {
			jsonData : JSON.stringify(rows),
			refDateStart1 : $('#refDateStart1').val(),
			refDateEnd1 : $('#refDateEnd1').val(),
			refDateStart2 : $('#refDateStart2').val(),
			refDateEnd2 : $('#refDateEnd2').val(),
			refDateStart3 : $('#refDateStart3').val(),
			refDateEnd3 : $('#refDateEnd3').val()
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
	var rows = getGridData();
	for (var i = 0, length = rows.length; i < length; i++) {
		var row = rows[i];
		if (row.orderCount == '') {
			row.orderCount = 0;// 非空处理
		}
		row.payAmt = parseFloat((row.orderCount * row.itemUnitPrice).toFixed(4)); // 计算金额
		row.unitAmt = parseFloat((row.orderCount * row.itemPrice).toFixed(4)); // 计算金额
	}

	$('#jsonData').val(JSON.stringify(rows));

	var _url = appRoot + '/lc/request/summary/checkView.action?parentTabId=' + tabId;
	_url = getUrl(_url);

	addPostTab('billForm', '采购单生成', _url);
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

	var _url = appRoot + "/lc/request/summary/queryMRP.action";
	_url = getUrl(_url);

	$.ajax({
		type : "POST",
		url : _url,
		data : {
			ids : g_ids
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
			$(data).each(function(i, row) {
				var safeCount = parseFloat((row.distribution * row.inventorysCycle).toFixed(2));
				row.safeCount = safeCount;
				// 默认把当前要货量当成日均要货量
				var suggest = row.distribution + row.allocation + safeCount - row.realCount - row.roadCount;
				if (suggest < 0) {
					suggest = 0;
				}
				row.suggestCount = parseFloat(suggest.toFixed(2));
				// if (suggest > 0 && suggest < row.minOrderCount) {//
				// 如果建议值不为零并且小于最小订货倍量，则默认使用最小订货倍量为采购量
				// row.orderCount = row.minOrderCount;
				// } else {
				// row.orderCount = row.suggestCount;
				// }

				// 采购量等于最小订货倍量的倍数
				var merchant = parseInt(row.suggestCount / row.minOrderCount);
				if (row.suggestCount % row.minOrderCount != 0) {
					row.orderCount = (merchant + 1) * row.minOrderCount;
				} else {
					row.orderCount = row.suggestCount;
				}
				row.payAmt = parseFloat((suggest * row.itemUnitPrice).toFixed(2));
				row.unitAmt = parseFloat((suggest * row.itemPrice).toFixed(2));
			});

			$('#dataGrid').handsontable('loadData', data);
			$.isLoading("hide");
		}
	});
}

var columns = [ {
	data : 'itemId',
	readOnly : true
}, {
	data : 'itemName',
	readOnly : true
}, {
	data : 'shelfName',
	readOnly : true
}, {
	data : 'categoryName',
	readOnly : true
}, {
	data : 'itemDimension',
	readOnly : true
}, {
	data : 'itemSpecification',
	readOnly : true
}, {
	data : 'minOrderCount',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'orderCount',
	type : 'numeric',
	format : '0.00',
}, {
	data : 'roadCount',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'realCount',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'average1',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'average2',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'average3',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'requestCount',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'distribution',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'supplyPeriod',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'inventorysCycle',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'safeCount1',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'safeCount2',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'safeCount3',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'safeCount',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'allocation',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'suggestCount1',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'suggestCount2',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'suggestCount3',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'suggestCount',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'itemUnitPrice',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
}, {
	data : 'itemPrice',
	readOnly : true,
	type : 'numeric',
	format : '0.00',
} ];
var colHeaders = [ '原料编码', '原料名称', '主库位', '类别', '单位', '规格', '最小订货倍量', '采购量', '在途量', '系统库存', '日均订货量1', '日均订货量2', '日均订货量3', '当前要货量',
		'应配送量', '供货周期', '库存周期', '安全库存1', '安全库存2', '安全库存3', '默认安全库存', '已分配量', '建议1', '建议2', '建议3',
		'默认建议值', '标准价', '进货价' ];
// console.log(columns.length, colHeaders.length);

function doClose() {
	closeTab(tabId, 'doQuery');
}
