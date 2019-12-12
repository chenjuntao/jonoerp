
function doCategoryQuery() {
	var detailDataGrid = dojo.byId("detailDataGrid");
	if(detailDataGrid){
		detailDataGrid.innerHTML = "";
	}
	
	var imgSrc = appRoot + "/jsp/common/img/wait.gif";
	var msg = "<br/><br/><br/><div align=\'center\'>正在查询请等待... <br/><br/><img src=\'"
			+ imgSrc + "\'> </div>";
	dojo.byId("dataGrid").innerHTML = msg;
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/businessquery/foodAmtQuery/doCategoryQuery.action";
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
				showDojoDialog(error);
			}
		});
	});
	return false;
}


function doDetailCategoryQuery(categoryId,categoryName) {
	var imgSrc = appRoot + "/jsp/common/img/wait.gif";
	var msg = "<br/><br/><br/><div align=\'center\'>正在查询请等待... <br/><br/><img src=\'"
			+ imgSrc + "\'> </div>";
	
	dojo.byId("detailDataGrid").innerHTML = msg;
	require(
			[ "dojo/_base/xhr" ],
			function(xhr) {
				var _url = appRoot
						+ "/businessquery/foodAmtQuery/doDetailCategoryQuery.action?categoryId="+ categoryId;
				_url = getUrl(_url);
				xhr.post({
					url : _url,
					form : 'queryForm',
					handleAs : "text",
					load : function(data) {
						require([ "dojo/window" ], function(win) {
							var vs = win.getBox();
							var gridHeight = vs.h - 191;
							var grid = dojo.byId("detailDataGrid");
							grid.style.height = gridHeight + "px";
							grid.innerHTML = data;
						});
						addEvent();
						
						dojo.byId("categoryName").innerHTML = categoryName;
					},
					error : function(error) {
						showDojoDialog(error);
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
