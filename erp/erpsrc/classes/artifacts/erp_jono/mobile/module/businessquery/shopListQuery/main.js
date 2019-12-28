$(function() {
	initControl();
});

function initControl() {
	$('#startDate, #endDate').scroller('destroy').scroller({
		preset : 'date',
		theme : 'default',
		mode : 'mixed',
		lang : 'zh',
		animate : 'none'
	});
}

var loadingMsg = getLoadingMsg();

function doQuery() {
	var type = $("#shopListType").val();
	if (type == '1') {
		listShop();
	} else {
		var currentPage = 1;
		var cpInput = $("#currentPage");
		if (cpInput.length > 0) {
			currentPage = cpInput.val();
		}
		pageQuery(currentPage);
	}

}

function listShop() {
	$("#dataGrid").html(loadingMsg);
	var _url = appRoot + "/businessquery/shopListQuery/result.action";
	$.ajax({
		type : "POST",
		// async : false,
		url : _url,
		data : {
			startDate : $('#startDate').val(),
			endDate : $('#endDate').val(),
			fromMobile : true
		},
		error : function(e) {
			alert(e);
		},
		success : function(data) {
			$("#dataGrid").html(data);

			var table = $('#shopTable').DataTable({
				// "scrollY": "300px",
				"scrollX" : "100%",
				"scrollCollapse" : true,
				language : {
					"info" : " _START_至_END_， 总共 _TOTAL_ 条记录",
					"lengthMenu" : "每页显示 _MENU_ 条记录",
					"search" : "查找：",
					"paginate" : {
						"first" : "首页",
						"last" : "尾页",
						"next" : "下一页",
						"previous" : "上一页"
					}
				}
			// ,
			// "paging" : false
			});
			new $.fn.dataTable.FixedColumns(table, {
				"leftColumns" : 2
			});

			addEvent();
		}
	});
}

function firstPage() {
	pageQuery(1);
}

function prevPage() {
	var page = $("#currentPage").val();
	pageQuery(parseInt(page) - 1);
}

function nextPage() {
	var page = $("#currentPage").val();
	pageQuery(parseInt(page) + 1);
}

function lastPage() {
	var page = $("#pageCount").val();
	pageQuery(page);
}

function gotoPage() {
	var page = $("#gotoPage").val();
	pageQuery(page);
}
function pageQuery(_page) {
	$("#dataGrid").html(loadingMsg);

	var _url = appRoot + "/businessquery/shopListQuery/dayResult.action";
	$.ajax({
		type : "POST",
		// async : false,
		url : _url,
		data : {
			startDate : $('#startDate').val(),
			endDate : $('#endDate').val(),
			currentPage : _page,
			pageSize : 2,
			fromMobile : true
		},
		error : function(e) {
			alert(e);
		},
		success : function(data) {
			$("#dataGrid").html(data);

			var table = $('#shopTable').DataTable({
				"scrollX" : "100%",
				"scrollCollapse" : true,
				language : {
					"info" : " _START_至_END_， 总共 _TOTAL_ 条记录",
					"lengthMenu" : "每页显示 _MENU_ 条记录",
					"search" : "查找："
				},
				"paging" : false
			});
			new $.fn.dataTable.FixedColumns(table, {
				"leftColumns" : 2
			});

			addEvent();
		}
	});
}

function addEvent() {
	var $tr = $("#datazone tr.highlight");
	$tr.on("mouseenter, mouseleave", function(e) {
		if (e.type == "mouseenter") {
			$(e.target).css({
				"background-color" : "#ffff66"
			});
		} else {
			$(e.target).css({
				"background-color" : "#d4e3e5"
			});
		}
	});
}

function gotoURL(_url) {
	window.open(_url);
}
