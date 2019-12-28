require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initTree();
	});
});

function initTree() {
	var _url = appRoot + "/restaurant/option/queryBranchTree.action";
	// var _url = appRoot + "/common/selbranch/queryStore.action";
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
					if (item.isLeaf == 'Y') {
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
					if (item.isLeaf == 'Y') {
						reloadMenu(item.id);
					}

					this._onExpandoClick({
						node : node
					});
				},
				clickEventCheckBox : false
			}, "userTree");

			userTree.startup();
		}, function(err) {
			alert("load error!");
		});
	});
}

function reloadMenu(_branchId) {
	var _url = appRoot + "/restaurant/option/mainView.action?branchId=" + _branchId;
	_url = getUrl(_url);

	dojo.byId('ifrMenu').src = _url;
}