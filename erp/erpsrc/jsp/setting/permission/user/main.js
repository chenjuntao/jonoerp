require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initUserTree();
	});
});

function initUserTree() {
	var _url = appRoot + "/setting/permission/user/queryUserTree.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			var userStore = new Memory({
				data : data
			});

			var userModel = new TreeStoreModel({
				store : userStore,
				query : {
					id : 'root'
				},
				mayHaveChildren : function(item) {
					if (item.isLeaf == 1) {
						return false;
					}
					return true;
				}
			});

			userTree = new Tree({
				model : userModel,
				showRoot : false,
				checkBoxes : false,
				autoExpand : false,
				onClick : function(item, node, evt) {
					if (item.parent != 'root') {// 如果不是顶层部门分类
						reloadMenu(item.id, item.type, item.name);
					}
					this._onExpandoClick({
						node : node
					}); // This will expand the node
				},
				clickEventCheckBox : false
			}, "userTree");

			userTree.startup();
		}, function(err) {
		});
	});
}

function reloadMenu(_principalId, _principalType, _principal) {
	var _url = appRoot + "/setting/permission/user/menuView.action";
	_url += "?principalId=" + _principalId + "&principalType=" + _principalType + "&principal=" + _principal;
	_url = getUrl(_url);

	dojo.byId('ifrMenu').src = _url;
}