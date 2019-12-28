require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initBranchTree();
	});
});

function initBranchTree() {
	var _url = appRoot + "/common/selbranch/queryAllTree.action";

	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			treeStore = new Memory({
				data : data
			});

			treeModel = new TreeStoreModel({
				store : treeStore,
				checkedRoot : true,
				query : {
					id : 'root'
				}
			});

			var tree = new Tree({
				model : treeModel,
				clickEventCheckBox : false
			}, "branchTree");

			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

function doSelect() {
	var selArr = treeStore.query({
		checked : true
	});

	var idArr = [];
	var nameArr = [];
	var branchTypeArr = [];
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(selArr, function(branch, i) {
			if (branch.type == 'branch') {
				idArr.push(branch.id);
				nameArr.push(branch.name);
				branchTypeArr.push(branch.branchType);
			}
		});
		parent.closeBranchDlg(idArr.join(), nameArr.join(), branchTypeArr.join());
	});
	if (selArr.length == 0) {
		alert('请选择门店！');
	}
}