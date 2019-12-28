require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initBranchTree();
	});
});

var branchData = [];

function initBranchTree() {
	var _url = appRoot + "/hq/fpgroup/brand/queryBranch.action?priceGroupId=" + g_priceGroupId;
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
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
			}, "branchTree");

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
	
	var idArr = [];
	var nameArr = [];
	var _url = appRoot + "/hq/fpgroup/brand/setBranch.action";
	
	require([ "dojo/_base/array", "dojo/request/xhr" ], function(array, xhr) {
		array.forEach(selArr, function(branch, i) {
			idArr.push(branch.id);
			nameArr.push(branch.name);
		});
		xhr.post(_url, {
			handleAs : "json",
			data : {
				priceGroupId : g_priceGroupId,
				branchIds : idArr.join()
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
	parent.closeBranchDlg();
}
