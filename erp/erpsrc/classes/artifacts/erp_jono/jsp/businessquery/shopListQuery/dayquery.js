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

	var _url = appRoot + "/businessquery/shopListQuery/dayResult.action";
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
				require([ "dojo/window" ], function(win) {
					var vs = win.getBox();
					var gridHeight = vs.h - 79;
					var grid = dojo.byId("dataGrid");
					grid.style.height = gridHeight + "px";
					grid.innerHTML = data;
				});
				return;
			},
			error : function(error) {
				alert(error);
			}
		});
	});
}
