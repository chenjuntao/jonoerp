var doQuery = null;
require([ "dojo", "dojo/query", "dojo/ready", "dojo/request/xhr" ], function(dojo, query, ready, xhr) {
	ready(function() {
		initEvent();

		doQuery();
	});

	function initEvent() {
		query('#tagArea span').on("click", function() {
			dojo.byId("menuName").value = this.innerHTML;
			doQuery();
		});
	}

	doQuery = function() {
		var _parentId = 'root';
		var _url = appRoot + "/setting/menu/query/doQuery.action";
		_url = getUrl(_url);
		
		var postData = {
			"menu.name" : dojo.byId("menuName").value
		};
		if (filterHidden == 'false') {
			postData.filter = 'false';
		}
		xhr.post(_url, {
			handleAs : "json",
			data : postData
		}).then(function(data) {
			data.push({
				id : _parentId,
				name : '',
				imageName : '',
				url : '',
				priority : ''
			});
			treeStore.setData(data);

			modelConfig['store'] = treeStore;
			modelConfig['query'] = {
				id : _parentId
			};
			buildTree();
		}, function(err) {
		});
	}
});

var treeStore = null;
var modelConfig = {
	store : null,
	query : null,
	rootLabel : '所有菜单',
	multiState : true,
	checkedRoot : true,
	checkedStrict : true,
	mayHaveChildren : function(item) {
		if (item.url != "") {
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
		// openOnClick : true,
		icon : null,
		showRoot : true,
		onClick : function(item, node, evt) {
			this._onExpandoClick({
				node : node
			}); // This will expand the node
			if (node.isExpandable == false) {
				if (item.url != "") {
					addTab(item.name, appRoot + item.url);
				}
			}
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
	}
});