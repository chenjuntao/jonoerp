dojo.ready(function() {
	// var logId = ${logId};
	doViewErrJson(logId);
});

// 查询出错的json数据
function doViewErrJson(logId) {
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/api/log/listErrJson.action?logId=" + logId;
		_url = getUrl(_url);
		xhr.post({
			url : _url,
			// form : 'logForm',
			handleAs : "json",
			load : function(data) {
				queryErrJson(data);
			},
			error : function(error) {
				alert(error);
			}
		});
	});
	return false;
}

function queryErrJson(data) {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "custom/SummaryRow",
			"dojo/_base/lang", "dgrid/Selection" ], function(OnDemandGrid, Memory, declare, Keyboard, SummaryRow, lang,
			Selection) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, SummaryRow, Selection ]);
		jsonGrid = new CustomGrid(lang.mixin({
			store : new Memory({
				data : data
			}),
			columns : jsonCols,
			cellNavigation : false,
			allowTextSelection : true,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}), "errJsonGrid");

		jsonGrid.startup();
	});
}

// 查询出错的详细信息
function doViewErrMsg(row) {
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/api/log/listErrMsg.action?logId=" + row.logId + "&errId=" + row.errId;
		_url = getUrl(_url);
		xhr.post({
			url : _url,
			// form : 'logForm',
			handleAs : "json",
			load : function(data) {
				queryErrMsg(data);
			},
			error : function(error) {
				alert(error);
			}
		});
	});
	return false;
}

var errGrid;
function queryErrMsg(data) {
	if (errGrid == undefined) {
		require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard",
				"custom/SummaryRow", "dojo/_base/lang", "dgrid/Selection" ], function(OnDemandGrid, Memory, declare,
				Keyboard, SummaryRow, lang, Selection) {
			var CustomGrid = declare([ OnDemandGrid, Keyboard, SummaryRow, Selection ]);
			errGrid = new CustomGrid(lang.mixin({
				store : new Memory({
					data : data
				}),
				columns : msgCols,
				cellNavigation : false,
				allowTextSelection : true,
				pageSizeOptions : [ 10, 30, 100 ],
				loadingMessage : '加载中...',
			// noDataMessage : "无数据！"
			}), "errMsgGrid");

			errGrid.startup();
		});
	} else {
		require([ "dojo/store/Memory" ], function(Memory) {
			var store = new Memory({
				data : data
			});
			errGrid.set('store', store);
		});
	}
}

var jsonCols = [ {
	label : "logId",
	field : "logId",
	className : "text-center",
	sortable : false
}, {
	label : "出错位置",
	field : "errId",
	className : "text-center",
	sortable : true
}, {
	label : "出错的Json字符串",
	field : "jsonStr",
	className : "text-center",
	sortable : false
}, {
	label : '查看',
	// field : "errId",
	className : 'text-center',
	renderCell : function(object, data) {
		return hrefFmt("错误/警告信息", doViewErrMsg, object);
	},
	sortable : false
} ];

var msgCols = [ {
	label : "logId",
	field : "logId",
	className : "text-center",
	sortable : false
}, {
	label : "出错位置",
	field : "errId",
	className : "text-center",
	sortable : false
}, {
	label : "出错的字段",
	field : "msgId",
	className : "text-center",
	sortable : false
}, {
	label : "状态码",
	field : "code",
	className : "text-center",
	sortable : false
}, {
	label : "出错/警告信息",
	field : "message",
	className : "text-center",
	sortable : false
} ];
