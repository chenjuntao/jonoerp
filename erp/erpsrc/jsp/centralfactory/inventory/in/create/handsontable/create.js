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
	for (var j = 0, length = rows.length - 1; j < length; j++) {
		if (rows[j].itemId == temp) {
			rows.splice(j, j);
			length--;
			j--;
		} else {
			rows[j].rownumber = i;
			i++;
		}
		temp = rows[j].itemId;
	}
	var $storageId = $('storageId').val();
	$('#storage').val($("#storageId").find("option:selected").text());

	$('#jsonData').val(JSON.stringify(rows));
	var _url = appRoot + '/lc/stock/in/create/checkView.action?parentTabId=' + tabId;
	_url = getUrl(_url);
	
	addPostTab('billForm', '入库单生成确认', _url);
}

function negativeValueRenderer(instance, td, row, col, prop, value, cellProperties) {
    Handsontable.renderers.TextRenderer.apply(this, arguments);
    if (prop == 'link') {
        td.innerHTML = value;//字符串转化成HTML的写法
    }
    if (prop == 'pic') {
        //添加自定义的图片，并给图片的chick添加事件
            td.innerHTML = value;
            td.onmouseover = function () {
            this.innerHTML = "<div style='pointer-events: none;'><img src='/home/cjt/tomcat-7.0.54/webapps/erp/upload/1575625550152.jpg' style='width: 100px;' class='mouse-img' target='_blank' /></div>";
            // var _url = appRoot + "/lc/stock/in/create/queryUnifiedDetails.action";
            // $.ajax({
            //     type: "POST",
            //     url: _url,
            //     error: function () {
            //         console.error("query failed");
            //     },
            //     success: function (data) {
            //         console.info(data);
            //         this.innerHTML = "<img src='" + data.rows[0].pics + "' style='width: 200px;' class='mouse-img' target='_blank' />";
            //         // $('#dataGrid').handsontable('loadData', data.rows);
            //         // $.isLoading("hide");
            //     }
            // });
        },
        td.onmouseout = function(){
            this.innerHTML = value;
        }
        // td.onmouseover = function(){
        //     this.style.backgroundColor = "#337ab7";
        //     var aEle = document.createElement("a");
        //     var _url = appRoot + "/common/loadImage.action";
        //     _url += "&timestamp=" + new Date().getTime(); // 防止缓存
        //     aEle.href = _url;
        //     aEle.target = "_blank";
        //     aEle.innerHTML = _text;
        //             var $liveTip = dom.byId('livetip');
        //             if ($liveTip == null) {
        //                 $liveTip = domc.create("div", {
        //                     id: 'livetip'
        //                 });
        //                 domc.place($liveTip, win.body());
        //             }
        //             var html = "<img src='" + _url + "' style='width: 200px;' class='mouse-img' target='_blank' />";
        //             domAttr.set($liveTip, 'innerHTML', html);
        //             domStyle.set($liveTip, {
        //                 'display': 'block',
        //                 'top': evt.pageY + 'px',
        //                 'left': evt.pageX + 'px'
        //             });
        //         on(aEle, mouse.leave, function (evt) {
        //             var $liveTip = dom.byId('livetip');
        //             domStyle.set($liveTip, 'display', 'none');
        //     });
        //     return aEle;
        // }
        // td.onmouseout = function(){
        //     this.style.backgroundColor = "#5bc0de";
        //     this.innerHTML = "鼠标移入移出事件监测";
        // }
        // //添加自定义的图片，并给图片的chick添加事件
        // var escaped = Handsontable.helper.stringify(value),
        //     imgdel;
        // imgdel = document.createElement('IMG');
        // imgdel.src = value;
        // imgdel.width = 20;
        // imgdel.name = escaped;
        // imgdel.style = 'cursor:pointer;';//鼠标移上去变手型
        // // Handsontable.dom.addEvent(imgdel, 'click', function (event) {
        // //     hot.alter("remove_row", row);//删除当前行
        // // });
        // Handsontable.dom.empty(td);
        // td.appendChild(imgdel);
        // td.style.textAlign = 'center';//图片居中对齐
        // return td;

    }
}
Handsontable.renderers.registerRenderer('negativeValueRenderer', negativeValueRenderer);
var grid = null;
var dataStore = null;

function loadGridData() {
    var supplierId = $('#supplierId').val();
    var formId = $('#formId').val();
    var df=1;
    var _url = appRoot + "/lc/stock/in/create/queryUnifiedDetail.action?formId=" + formId + "&supplierId=" + supplierId+ "&ss=" + df;
    _url = getUrl(_url);
    $.ajax({
        type : "POST",
        url : _url,
        error : function() {
            console.error("query failed");
        },
        success : function(data) {
            console.info(data);
            $('#dataGrid').handsontable('loadData', data.rows);
            $.isLoading("hide");
        }
    });
 }
   function initGrid() {
       $('#dataGrid').handsontable({
        data : [],
        colHeaders : colHeaders,
        columns : columns,
        rowHeaders : true,
        fixedColumnsLeft : 7,
        manualColumnFreeze : true,
        allowInvalid : false,
        height : 450,
        contextMenu: ['row_above', 'row_below', '---------', 'remove_row', '---------', 'undo', 'redo', '---------', 'make_read_only', '---------', 'alignment'],
        dropdownMenu: ['filter_by_condition', 'filter_by_value', 'filter_action_bar'],
        cells: function (row, col, prop) {
           var cellProperties = {};
           cellProperties.renderer = "negativeValueRenderer";
               return cellProperties;
           },

       })

    $.isLoading({
		text : "加载中"
	});
	var supplierId = $('#supplierId').val();
	var formId = $('#formId').val();
	var _url = appRoot + "/lc/stock/in/create/queryUnifiedDetail.action?formId=" + formId + "&supplierId=" + supplierId;
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
            console.info(data);
			// 如果没有统配相关的数据，则提供进入下一步的选择
			if (data.length == 0) {
				alert('无统配，直接进入下一步！');
				doSubmit();
				return;
			}
			$(data.rows).each(function(i, row) {
				row.payAmt = parseFloat((row.receiveCount * row.itemUnitPrice).toFixed(4)); // 计算金额
				row.receiveAmt = parseFloat((row.receiveCount * row.receivePrice).toFixed(4)); // 计算金额
			});
			$('#dataGrid').handsontable('loadData', data.rows);
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
	data : 'link',
	type : 'numeric',
	format : '0.00',
},  {
    data : 'nums',
    type : 'numeric',
    format : '0.00',
}, {
	data : 'outReceiveRate',
	readOnly : true,
	type : 'numeric',
	format : '0.00',

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
},{
	data : 'pic',
	readOnly : true,
	renderCell : function(object, data) {
		return imageFmt(data, object.itemId);
	},
	sortable : false
}];

var colHeaders = [ '原料编码', '原料名称', '类别', '单位', '规格', '收货部门', '供货周期', '订货数量', '已入库数量', '实收数量', '数量','超收率', '标准单价', '标准金额',
		'进货单价', '进货金额', '有效期' ,'图片名称'];

function doClose() {
	closeTab(tabId);
}

function imageFmt(_text, _objId) {
    alert("45");
    var aEle = document.createElement("a");
    // var _url = appRoot + "/common/loadImage.action?objectId=" + _objId;
    // _url += "&timestamp=" + new Date().getTime(); // 防止缓存
    // aEle.href = _url;
    // aEle.target = "_blank";
    // aEle.innerHTML = _text;
    // require(["dojo/mouse", "dojo/on", "dojo/query", "dojo/dom", "dojo/dom-construct", "dojo/_base/window",
    //     "dojo/dom-attr", "dojo/dom-style"], function (mouse, on, query, dom, domc, win, domAttr, domStyle) {
    //     on(aEle, mouse.enter, function (evt) {
    //         var $liveTip = dom.byId('livetip');
    //         if ($liveTip == null) {
    //             $liveTip = domc.create("div", {
    //                 id: 'livetip'
    //             });
    //             domc.place($liveTip, win.body());
    //         }
    //         var html = "<img src='" + _url + "' style='width: 200px;' class='mouse-img' target='_blank' />";
    //         domAttr.set($liveTip, 'innerHTML', html);
    //         domStyle.set($liveTip, {
    //             'display': 'block',
    //             'top': evt.pageY + 'px',
    //             'left': evt.pageX + 'px'
    //         });
    //     });
    //     on(aEle, mouse.leave, function (evt) {
    //         var $liveTip = dom.byId('livetip');
    //         domStyle.set($liveTip, 'display', 'none');
    //     });
    // });
    return aEle;
// }
// var _url = appRoot + "/lc/stock/in/create/queryUnifiedDetail.action?formId=" + formId + "&supplierId=" + supplierId;
// _url = getUrl(_url);
// $.ajax({
//     type : "POST",
//     url : _url,
//     data : {
//         deliveryType : deliveryType
//     },
//     error : function() {
//         console.error("query failed");
//     },
//     success : function(data) {
//         console.info(data);
//         // 如果没有统配相关的数据，则提供进入下一步的选择
//         if (data.length == 0) {
//             alert('无统配，直接进入下一步！');
//             doSubmit();
//             return;
//         }
//         $(data.rows).each(function(i, row) {
//             row.payAmt = parseFloat((row.receiveCount * row.itemUnitPrice).toFixed(4)); // 计算金额
//             row.receiveAmt = parseFloat((row.receiveCount * row.receivePrice).toFixed(4)); // 计算金额
//         });
//         $('#dataGrid').handsontable('loadData', data.rows);
//         $.isLoading("hide");
//     }
// });

}