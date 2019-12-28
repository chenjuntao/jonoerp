$(function() {
	addEvent();
	initGrid();
	loadGridDatas();
});
var arrs="";
function loadGridDatas() {
	var _url = appRoot + "/lc/stock/in/create/queryUnifiedDetails.action";
	_url = getUrl(_url);
	$.ajax({
		type: "POST",
		url: _url,
		error: function () {
			console.error("query failed");
		},
		success: function (data) {
			var arr = data.weigLst;
			var arrs=data.weights;
			$("#weight").val(arr.num);
			// $("#picsd").val(arr.pic);
            if (arr.pic==null) {
                // $("#picsd").attr("src","display:none;");
                $("#sss").hide();
            }
            var p=appRoot+"/upload/"+arr.pic;
            $("#picsd").attr("src",p);
			$("#weightcount").val(data.weightcount);
			// if (arr != null) {
			// 	for (var i = 0; i < arr.length; i++) {
			// 		if (ids == i) {
			// 			mydata.rows[i].nums = arr[i].num;
			// 			mydata.rows[i].pic = arr[i].pic;
			// 		}
			// 	}
			// }
		}
	});}

function loadGridData(ids) {
	var _url = appRoot + "/lc/stock/in/create/queryUnifiedDetails.action?ids="+ids;
	_url = getUrl(_url);
	$.ajax({
		type : "POST",
		url : _url,
		error : function() {
			console.error("query failed");
		},
		success : function(data) {
			var arr=data.nums;
			var arrss=data.weights;
			// var as=data.weigLst;
			arrs=data.nums;
			console.info(data.weights);
			for(var i=0;i<arrs.length;i++){
				if (ids==i){
					var weight=$("#weight").val();
					var picsd=$("#picsd").val();
					// for (var j = 0; j <arrs.length ; j++) {
					if (arrs[i].isok!=1) {
						$("#weight").val("");
						$("#picsd").val("");
						var myids=arrs[i].myid;
						var w=$("#weight").val(arrs[i].num);
						var p=$("#picsd").val(arrs[i].pic);
						var weig=$("#weight").val();
						var pi=$("#picsd").val();
						mydata.rows[i].nums=weig;
						mydata.rows[i].pic=pi;
						if (arrss.length==0) {
							$("#weight").val("");
							$("#picsd").val("");
						}
						loadGridDatas();

						if (arrss.length==0) {
							$("#weight").val("");
							$("#picsd").val("");
						}
					}
				}
				var _url = appRoot + "/lc/stock/in/create/updateEntity.action?myids="+myids;
				_url = getUrl(_url);
				$.ajax({
					type: "POST",
					url: _url,
					error: function () {
						console.error("query failed");
					},
					success: function (data) {
						if (data>0){
							if (arrss.length==0) {
								$("#weight").val("");
								$("#picsd").val("");
							}
						}
					}
				});
			}
			if (arrss.length==0) {
				$("#weight").val("");
				$("#picsd").val("");
			}

			$('#dataGrid').handsontable('loadData', mydata.rows);
			$.isLoading("hide");
		}
	});
}
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
	var s=$("#weight").val();
	var oldrows = getGridData();
	var oldItemId = "";
	var rows = [];
	if (oldrows.length > 0) {
		var orderCount = 0.0;
		var receivedCount = 0.0;
		var receiveCount = 0.0;
		for (var i = 0; i < oldrows.length; i++) {
			console.info(oldrows);
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
				alert(parseFloat((item.receiveCount * item.itemUnitPrice).toFixed(4)));
				item.payAmt = parseFloat((item.receiveCount  * item.itemUnitPrice).toFixed(4));
				item.receiveAmt = parseFloat((item.receiveCount * item.receivePrice).toFixed(4));
				rows.push(item);
				oldItemId = itemId;
				for (var j = 0; j < rows.length; j++) {
					var item2 = rows[j];
					if (itemId == item2.itemId) {
						item2.differentCount = item.differentCount;
						item2.sumItemCount = item.sumItemCount;
						item2.receiveCount  = item.receiveCount ;
						item2.payAmt = item.payAmt;
						item2.receiveAmt = item.receiveAmt;
						item2.orderCount = item.orderCount;
						item2.receivedCount = item.receivedCount;
					}
				}
			} else {
				orderCount = item.orderCount;
				receivedCount = item.receivedCount;
				receiveCount = item.receiveCount ;
				item.sumItemCount = item.receiveCount  + item.receivedCount;
				item.differentCount = item.orderCount - item.receivedCount - item.receiveCount ;
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
//add by wuqing,20191222
function negativeValueRenderer(instance, td, row, col, prop, value, cellProperties) {
    Handsontable.renderers.TextRenderer.apply(this, arguments);
    if (prop=='nums') {
		if(value!=null && value!='' && value!=undefined){
			// value='<input TYPE="text" id="sss" value='+value+' style="color:red； width:30%;"/>';
			// td.innerHTML = value;
		}
	}
    if (prop == 'link') {
    	value="<a href='#' onclick='loadGridData("+value+")' >读取</a>";
        td.innerHTML = value;//字符串转化成HTML的写法
    }
    if (prop == 'pic') {
        //添加自定义的图片，并给图片的chick添加事件
		    if(value!=null && value!='' && value!=undefined){
                var urls = appRoot+"/upload/"+value;
		    	var values="<div style='pointer-events: none;'><img src='"+urls+"' style='width: 60px;' class='mouse-img' target='_blank'/></div>";
				td.innerHTML = values;
				// td.onmouseover = function () {
				// 	console.info(value);
				// 	var urls = appRoot+"/upload/"+value;
				// 	this.innerHTML = "<div style='pointer-events: none;'><img src='"+urls+"' style='width: 20px;' class='mouse-img' target='_blank'/></div>";
				// },
				// td.onmouseout = function(){
				// 	this.innerHTML=values;
				// }
				// return td;
			}else{
		    	value="";
			}
    }
}
Handsontable.renderers.registerRenderer('negativeValueRenderer', negativeValueRenderer);
var grid = null;
var dataStore = null;
//add by wuqing,20191222
var mydata = null;
function loadPic(pic) {
	alert(pic);

}
   function initGrid() {
       $('#dataGrid').handsontable({
        data : [],
        colHeaders : colHeaders,
        columns : columns,
        rowHeaders : true,
        fixedColumnsLeft : 7,
        manualColumnFreeze : true,
		dropdownMenu: true,
        allowInvalid : false,
		// colWidths: 70,  //colWidths: [100, 200, 300, 200, 100]
        height : 800,
		   //add by wuqing,20191222
		contextMenu: ['row_above', 'row_below', '---------', 'remove_row', '---------', 'undo', 'redo', '---------', 'make_read_only', '---------', 'alignment'],
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
			var f=$("#weight").val();
			mydata = data;
            console.info(mydata);
			// 如果没有统配相关的数据，则提供进入下一步的选择
			if (mydata.length == 0) {
				alert('无统配，直接进入下一步！');
				doSubmit();
				return;
			}
			$(mydata.rows).each(function(i, row) {
				row.payAmt = parseFloat((row.receiveCount * row.itemUnitPrice).toFixed(4)); // 计算金额
				row.receiveAmt = parseFloat((row.receiveCount * row.receivePrice).toFixed(4)); // 计算金额
			});
			$('#dataGrid').handsontable('loadData', mydata.rows);
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
	data : 'receiveCount',
	type : 'numeric',
	format : '0.00',
}, {
    data : 'link',
    type : 'numeric',
    format : '0.00',
}, {
    data : 'nums',
    type : 'text',
    format : '0.00',
	style:'width: 5em;text-align: right;color:red',
	editorArgs : {
		style : 'width: 5em;text-align: right;color:red	',
		constraints : {
			min : 0,
			max : 99550,
			places : '0,3'
		},
		required : true,
		invalidMessage : '请输入不多于三位小数的数字。'
	}
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
},{
	data : 'pic',
	width:'70',
	readOnly : true,
	sortable : false
}];

var colHeaders = [ '原料编码', '原料名称', '类别', '单位', '规格', '收货部门', '供货周期', '订货数量', '已入库数量','数量', '操作', '实收数量','超收率', '标准单价', '标准金额',
		'进货单价', '进货金额', '有效期' ,'图片名称'];

function doClose() {
	closeTab(tabId);
}
