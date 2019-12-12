function firstPage() {
	pageQuery(1);
}

function prevPage() {
	var page = dojo.byId("currentPage").value;
	pageQuery(parseInt(page) - 1);
}

function nextPage() {
	var page = dojo.byId("currentPage").value;
	pageQuery(parseInt(page) + 1);
}

function lastPage() {
	var page = dojo.byId("pageCount").value;
	pageQuery(page);
}

function gotoPage() {
	var page = dojo.byId("gotoPage").value;
	pageQuery(page);
}
function pageQuery(_page) {
	dojo.byId("dataGrid").innerHTML = loadingMsg;

	var _url = appRoot + "/businessquery/paymentquery/queryByDay.action";
	_url = getUrl(_url);
	require([ "dojo/_base/xhr" ], function(xhr) {
		xhr.post({
			url : _url,
			postData : {
				startDate : dojo.byId('startDate').value,
				endDate : dojo.byId('endDate').value,
				currentPage : _page,
				pageSize : 2
			},
			handleAs : "text",
			load : function(data) {
				dojo.byId("dataGrid").innerHTML = data;
				return;
			},
			error : function(error) {
				alert(error);
			}
		});
	});
}
