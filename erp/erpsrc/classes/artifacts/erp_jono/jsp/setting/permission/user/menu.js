require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initMenuTree();
	});
});

var treeStore = null;
var treeModel = null;
var menuTree = null;
function initMenuTree() {
	var _url = appRoot + "/setting/permission/user/queryMenuTree.action";
	_url += "?principalId=" + g_principalId + "&principalType=" + g_principalType + "&principal=" + g_principal;
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			var rootId = "root";
			data.push({
				id : rootId,
				name : '全部菜单'
			});
			treeStore = new Memory({
				data : data
			});

			treeModel = new TreeStoreModel({
				store : treeStore,
				checkedRoot : true,
				query : {
					id : rootId
				},
				mayHaveChildren : function(item) {
					if (item.isLeaf == 1) {
						return false;
					}
					return true;
				}
			});

			menuTree = new Tree({
				model : treeModel,
				showRoot : true,
				checkBoxes : true,
				autoExpand : false,
				openOnClick : true,
				clickEventCheckBox : false
			}, "menuTree");

			menuTree.startup();
		}, function(err) {
		});
	});
}

function doSave() {
	var selArr = treeStore.query({
		checked : true
	});
	var idArr = [];
	var nameArr = [];
	var _url = appRoot + "/setting/permission/user/savePermission.action";
	_url = getUrl(_url);

	require([ "dojo/_base/array", "dojo/request/xhr" ], function(array, xhr) {
		array.forEach(selArr, function(menu, i) {
			if (menu.isLeaf == 1) {// 获取所有叶子节点
				idArr.push(menu.id);
				nameArr.push(menu.name);
			}
		});
		xhr.post(_url, {
			handleAs : "json",
			data : {
				principalId : g_principalId,
				principalType : g_principalType,
				principal : g_principal,
				menuIds : idArr.join()
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
			} else {
				alert("保存失败！");
			}
		}, function(err) {
		});
	});
}