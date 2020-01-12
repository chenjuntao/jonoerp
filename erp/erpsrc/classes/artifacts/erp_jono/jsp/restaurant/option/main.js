require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initMenuTree();
	});
});

var treeStore = null;
var treeModel = null;
var menuTree = null;

function initMenuTree() {
	var _url = appRoot + "/restaurant/option/loadTree.action";
	_url += "?branchId=" + branchId;
	_url = getUrl(_url);
	
	require([ 
	          "dojo/request/xhr", 
	          "dojo/store/Memory", 
	          "cbtree/Tree", 
	          "cbtree/model/TreeStoreModel",
	          "cbtree/model/StoreModel-EXT"
      ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			var rootId = "root";
			data.push({
				id : rootId,
				name : '全部'
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
					if (item.isLeaf == 'Y') {
						return false;
					}
					return true;
				}
			});

			menuTree = new Tree({
				model : treeModel,
				showRoot : false,
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
	var _url = appRoot + "/restaurant/option/saveData.action";
	_url = getUrl(_url);
	
	require([ "dojo/_base/array", "dojo/request/xhr" ], function(array, xhr) {
		array.forEach(selArr, function(menu, i) {
			if (menu.isLeaf == 'Y') {
				idArr.push(menu.id);
				nameArr.push(menu.name);
			}
		});
		
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : branchId,
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