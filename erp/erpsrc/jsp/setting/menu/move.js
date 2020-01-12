require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		reloadMenu();
	});
});

function reloadMenu() {
	var _url = appRoot + "/setting/menu/queryAllMenu.action";
	_url = getUrl(_url);
	
	var _parentId = 'root';
	require([ "dojo/_base/xhr" ], function(xhr) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
				data.push({
					id : _parentId
				});
				treeStore.setData(data);

				modelConfig['store'] = treeStore;
				modelConfig['query'] = {
					id : _parentId
				};
				buildTree();
			},
			error : function(error) {
				errorHandler(error);
			}
		});
	});
}

var treeStore = null;
var modelConfig = {
	store : null,
	query : null,
	rootLabel : '所有菜单',
	multiState : true,
	checkedRoot : true,
	checkedStrict : true,
	mayHaveChildren : function(item) {
		if (item.isLeaf == 1) {
			return false;
		}
		return true;
	}
};
var buildTree = null;
var menuTree = null;
require([ "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel", "cbtree/model/StoreModel-EXT" ], function(
		Memory, Tree, TreeStoreModel, StoreModelExt) {
	var treeConfig = {
		model : null,
		id : "menuTree",
		checkBoxes : false,
		branchCheckBox : true,
		branchReadOnly : false,
		branchIcons : true,
		leafCheckBox : true,
		leafReadOnly : false,
		leafIcons : true,
		icon : null,
		showRoot : true,
		onClick : function(item, node, evt) {
			dojo.byId("saveBtn").disabled = false;
		}
	};

	treeStore = new Memory({
		data : []
	});

	buildTree = function() {
		if (menuTree) {
			var treeModel = menuTree.model;
			if (treeModel) {
				treeModel.destroy();
				delete menuTree.model;
			}
			menuTree.destroy();
			delete menuTree;
		}

		treeConfig.model = new TreeStoreModel(modelConfig);
		menuTree = new Tree(treeConfig);
		menuTree.placeAt("treeWrapper");
		menuTree.startup();

		dojo.byId("saveBtn").disabled = true;
	}
});

function doSave() {
	var node = menuTree.lastFocused;
	var _url = appRoot + "/setting/menu/moveMenu.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				menuId : g_menuId,
				parentId : node.item.id
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("操作成功！");
				parent.closeMoveDlg();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}