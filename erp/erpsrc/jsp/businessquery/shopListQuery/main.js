var loadingMsg = getLoadingMsg();


function doQuery() {
	var type = dojo.byId("type").value;
	if(type == '1'){
		listShop();
	} else {
		var currentPage = 1;
		var cpInput = dojo.byId("currentPage");
		if (cpInput != null) {
			currentPage = cpInput.value;
		}
		pageQuery(currentPage);
	}
}

function listShop() {
	var imgSrc = appRoot + "/jsp/common/img/wait.gif";
	var msg = "<br/><br/><br/><div align=\'center\'>正在查询请等待... <br/><br/><img src=\'"
			+ imgSrc + "\'> </div>";
	dojo.byId("dataGrid").innerHTML = msg;
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/businessquery/shopListQuery/result.action";
		_url = getUrl(_url);
		
		xhr.post({
			url : _url,
			form : 'queryForm',
			handleAs : "text",
			load : function(data) {
				require([ "dojo/window" ], function(win) {
					var vs = win.getBox();
					var gridHeight = vs.h - 79;
					var grid = dojo.byId("dataGrid");
					grid.style.height = gridHeight + "px";
					grid.innerHTML = data;
				});
			},
			error : function(error) {
				showDojoDialog(error);
			}
		});
	});
	return false;
}

