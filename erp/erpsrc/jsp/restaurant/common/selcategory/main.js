require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initTree();
	});
});

var treeStore = null;
var treeModel = null;
function initTree() {
	require([ "dojo/_base/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		var _url = appRoot + "/restaurant/selmaterial/loadTree.action";

		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
				treeStore = new Memory({
					data : data
				});
				// Create the model
				treeModel = new TreeStoreModel({
					store : treeStore,
					query : {
						id : 'root'
					}
				});
				var tree = new Tree({
					model : treeModel,
					showRoot : false,
					clickEventCheckBox : false
				}, "categoryTree");

				tree.startup();
			},
			error : function(error) {
				alert("load error");
			}
		});
	});
}

/**
 * 选择原材料类别
 */
function selCategory() {
	var selCateArr = treeStore.query({
		checked : true
	});
	var idArr = [];
	var nameArr = [];
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(selCateArr, function(cate, i) {
			var isLeaf = !treeModel.mayHaveChildren(cate);
			if (isLeaf) {
				idArr.push(cate.id);
				nameArr.push(cate.name);
			}
		});
		parent.closeCateDlg(idArr, nameArr);
	});
}
