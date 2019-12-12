var loadingMsg = getLoadingMsg();

function doQuery() {
	var detailGrid = dojo.byId("detailGrid");
	if (detailGrid) {
		detailGrid.innerHTML = "";
	}

	dojo.byId("dataGrid").innerHTML = loadingMsg;
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/lc/request/wydemand/doQuery.action";
		_url = getUrl(_url);
		
		xhr.post({
			url : _url,
			form : 'queryForm',
			handleAs : "text",
			load : function(data) {
				var grid = dojo.byId("dataGrid");
				grid.innerHTML = data;
				addEvent();
			},
			error : function(error) {
				alert(error);
			}
		});
	});
	return false;
}

function queryDetail(categoryId, categoryName) {
	dojo.byId("detailGrid").innerHTML = loadingMsg;
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot
				+ "/lc/request/wydemand/queryDetail.action?categoryId="
				+ categoryId;
		_url = getUrl(_url);
		
		xhr.post({
			url : _url,
			form : 'queryForm',
			handleAs : "text",
			load : function(data) {
				require([ "dojo/window" ], function(win) {
					var vs = win.getBox();
					var gridHeight = vs.h - 122;
					var grid = dojo.byId("detailGrid");
					grid.style.height = gridHeight + "px";
					grid.innerHTML = data;
				});
				addEvent();

				dojo.byId("categoryName").innerHTML = categoryName;
			},
			error : function(error) {
				alert(error);
			}
		});
	});
	return false;
}

function addEvent() {
	var $tr = dojo.query("#datazone tr.highlight");
	$tr.on("mouseenter, mouseleave", function(e) {
		if (e.type == "mouseenter") {
			dojo.style(e.target, "backgroundColor", "#ffff66");
		} else {
			dojo.style(e.target, "backgroundColor", "#d4e3e5");
		}
	});
}
