require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

var grid = null;
var dataStore = null;
function initGrid(_id) {
	var _url = appRoot + "/setting/role/doQuery.action?branchType=" + g_branchType;

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection" ],
			function(OnDemandGrid, Server, declare, selector, Selection) {
				dataStore = new Server({
					target : _url,
					idProperty : "roleId"
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
					grid.select(grid.row(g_roleId));
				});
				grid.startup();
			});
}

function getColumn(selector) {
	return [ selector({
		label : "",
		field : 'rownumber',
		selectorType : "radio",
		sortable : false
	}), {
		label : "编号",
		field : "roleId",
		sortable : false
	}, {
		label : "名称",
		field : "roleName",
		sortable : false
	}, {
		label : "描述",
		field : "description",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function doSelect() {
	var selRow = null;
	for ( var roleId in grid.selection) {
		if (grid.selection[roleId]) {
			selRow = dataStore.get(roleId);
		}
	}
	if (selRow == null) {
		alert('请选择记录！');
		return;
	}
	if (g_userId == '') {
		// 在新增窗口中设置角色
		parent.closeSubRoleDlg(selRow);
	} else {
		var _url = appRoot + "/setting/user/setUserRole.action";

		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					userId : g_userId,
					roleId : selRow.roleId
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					alert("保存成功！");
					parent.closeRoleDlg();
				} else {
					alert("保存失败！");
				}
			}, function(err) {
			});
		});
	}
}