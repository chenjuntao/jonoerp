require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		reloadMenu();
	});
});

function reloadMenu() {
	var _url = appRoot + "/restaurant/selmaterial/loadTree.action?itemType=" + g_itemType;
	
	var _parentId = 'root';
	require([ "dojo/_base/xhr" ], function(xhr) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
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
	rootLabel : '所有类别',
	multiState : true,
	checkedRoot : true,
	checkedStrict : true
// ,
// mayHaveChildren : function(item) {
// if (item.isLeaf == 1) {
// return false;
// }
// return true;
// }
};
var buildTree = null;
var cateTree = null;
require([ "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel", "cbtree/model/StoreModel-EXT" ], function(
		Memory, Tree, TreeStoreModel, StoreModelExt) {
	var treeConfig = {
		model : null,
		id : "cateTree",
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
		if (cateTree) {
			var treeModel = cateTree.model;
			if (treeModel) {
				treeModel.destroy();
				delete cateTree.model;
			}
			cateTree.destroy();
			delete cateTree;
		}

		treeConfig.model = new TreeStoreModel(modelConfig);
		cateTree = new Tree(treeConfig);
		cateTree.placeAt("treeWrapper");
		cateTree.startup();

		dojo.byId("saveBtn").disabled = true;
	}
});

function doSave() {
	var node = cateTree.lastFocused;
	var _url = appRoot + "/hq/item/meta/moveCate.action";
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				itemId : g_itemId,
				categoryId : node.item.id
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