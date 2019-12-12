initTagTree();

var branchData = [];

function initTagTree() {
	var _url = appRoot + "/hq/config/tag/queryTagTree.action";
	
	require([ 
	          "dojo/request/xhr", 
	          "dojo/store/Memory", 
	          "cbtree/Tree", 
	          "cbtree/model/TreeStoreModel",
	          "cbtree/model/StoreModel-EXT",
	          "dojo/domReady!" 
          ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			treeStore = new Memory({
				data : branchData.concat(data)
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
				autoExpand : 'true',
				clickEventCheckBox : false
			}, "tagTree");

			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

function doSave() {
	var selArr = treeStore.query({
		checked : true
	});
	
	var tagIds = [];
	
	var _url = appRoot + "/hq/config/tag/settag/doBatchTagSet.action";
	
	require([ "dojo/_base/array", "dojo/request/xhr" ], function(array, xhr) {
		array.forEach(selArr, function(item, i) {
			if('root' != item.id){
				tagIds.push(item.id);
			 }
			}
		);
		
		if(tagIds.length == 0){
			alert("请选择标签！");
			return;
		}
		
		xhr.post(_url, {
			handleAs : "json",
			data : {
				bindTag:bindTag,
				cateIds : cateIds,
				tagIds : tagIds.join(',')
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
				doClose();
			} else {
				alert("保存失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	parent.closeItemTagDlg();
}
