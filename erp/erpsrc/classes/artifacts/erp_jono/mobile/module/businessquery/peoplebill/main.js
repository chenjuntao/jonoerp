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
	$('#createMan, #settleMan, #disCurMan')
			.scroller('destroy').scroller({
				preset : 'select',
				theme : 'default',
				mode : 'mixed',
				lang : 'zh',
				animate : 'none'
			});
}

function doQuery() {
	var currentPage = 0;
	pageQuery(currentPage);
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

var loadingMsg = getLoadingMsg();

function pageQuery(_page) {
	var pageCount = $('#pageCount').val();
	$("#dataGrid").html(loadingMsg);

	function getSelVal(_id) {
		var _val = $('#' + _id + ' option:selected').attr('data');
		return _val;
	}

	var _url = appRoot + "/jsp/businessquery/peopleBillListResult.action";
	var data = {
		startDate : $('#startDate').val(),
		endDate : $('#endDate').val(),
		createMan : getSelVal('createMan'),
		settleMan : getSelVal('settleMan'),
		disCurMan : getSelVal('disCurMan'),
		currentPage : _page,
		pageCount : pageCount,
		fromMobile : true
	};
	$.ajax({
		type : "POST",
//		async : false,
		url : _url,
		data : data,
		error : function(e) {
			alert(e);
		},
		success : function(data) {
			$("#dataGrid").html(data);

			var table = $('#billTable').DataTable({
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
		}
	});
}

function gotoURL(_billCode) {
	var _url = appRoot + '/jsp/businessquery/foodlist.action?billC='
			+ _billCode;
	window.open(_url);
}
