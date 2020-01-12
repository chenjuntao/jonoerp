require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

var grid = null;
var dataStore = null;
function initGrid(_id) {
	var _url = appRoot + "/setting/user/queryDept.action?branchType=" + g_branchType + "&branchId=" + g_branchId;

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection",
			"dojo/_base/array" ], function(OnDemandGrid, Server, declare, selector, Selection, array) {
		dataStore = new Server({
			target : _url,
			idProperty : "branchId"
		});

		var CustomGrid = declare([ OnDemandGrid, Selection ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on("dgrid-refresh-complete", function(evt) {
			if (g_branchIds != "") {
				array.forEach(g_branchIds.split(","), function(branchId, i) {
					grid.select(grid.row(branchId));
				});
			}
		});
		grid.startup();
	});
}

function getColumn(selector) {
	return [ selector({
		label : "",
		field : 'rownumber'
	}), {
		label : "编号",
		field : "branchId"
	}, {
		label : "名称",
		field : "branchName"
	}, {
		label : "地址",
		field : "branchAddress"
	}, {
		label : "",
		field : "none"
	} ];
}

function doSelect() {
	var selArr = [];
	for ( var branchId in grid.selection) {
		if (grid.selection[branchId]) {
			selArr.push(branchId);
		}
	}

	function getDiff(arr1, arr2) {
		return arr1.filter(function(x) {
			return arr2.indexOf(x) < 0
		});
	}
	var origArr = [];
	if (g_branchIds != "") {
		origArr = g_branchIds.split(",");
	}
	var addArr = getDiff(selArr, origArr);// 新增的部门列表
	var delArr = getDiff(origArr, selArr);// 减少的部门列表
	if (addArr.length == 0 && delArr.length == 0) {
		alert('数据没有变化！');
		return;
	}

	var _url = appRoot + "/setting/user/setMultiDept.action";

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				userId : g_userId,
				jsonData : JSON.stringify([ addArr, delArr ])
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
				parent.closeDeptDlg();
			} else {
				alert("保存失败！");
			}
		}, function(err) {
		});
	});
}