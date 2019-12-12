dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		sender : dojo.byId('sender').value,
		startTime : dojo.byId('startTime').value,
		endTime : dojo.byId('endTime').value,
		api : dojo.byId('api').value,
		code : dojo.byId('code').value,
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/api/log/listLog.action";
	_url = getUrl(_url);

	require([ "dgrid/Grid", "dgrid/extensions/Pagination", "custom/store/Server", "dojo/store/Observable",
			"dojo/store/Cache", "dojo/store/Memory", "dgrid/ColumnSet", "dojo/_base/declare",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(Grid, Pagination, Server, Observable, Cache,
			Memory, ColumnSet, declare, ColumnResizer) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination, ColumnSet, ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			pageSizeOptions : [ 10, 30, 100 ],
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");
		console.dir(getColumn());
		grid.startup();
	});
}

function doViewErr(row) {
	var _url = appRoot + "/api/log/listErr.action?logId=" + row.logId;
	_url = getUrl(_url);

	addTab("传输警告/错误", _url);
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "logId",
		field : "logId",
		className : "text-center",
		sortable : false
	}, {
		label : "API",
		field : "api",
		className : "text-center",
		sortable : false
	} ] ], [ [ {
		label : "状态",
		field : "code",
		className : "text-center",
		sortable : false
	}, {
		label : '查看',
		// field : "code",
		className : 'text-center',
		renderCell : function(object, data) {
			if (object.code == 200) {
				return "";
			} else if (object.code == 201) {
				return hrefFmt("警告", doViewErr, object);
			} else if (object.code == 202) {
				return hrefFmt("错误/警告", doViewErr, object);
			} else if (object.code == 203) {
				return hrefFmt("错误", doViewErr, object);
			}
		},
		sortable : false
	}, {
		label : "接收",
		field : "receive",
		className : "text-right",
		sortable : false
	}, {
		label : "成功",
		field : "success",
		className : "text-right",
		sortable : false
	}, {
		label : "失败",
		field : "fail",
		className : "text-right",
		sortable : false
	}, {
		label : "传输时间",
		field : "logTime",
		className : "text-center",
		sortable : false
	}, {
		label : "发送方",
		field : "sender",
		className : "text-center",
		sortable : false
	} ] ] ];
}
