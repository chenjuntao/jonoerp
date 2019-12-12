require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initBranchTree();
	});
});

var branchData = [ ];

function test(){
//	console.dir(treeStore);
	
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(treeStore.data, function(item, i) {
			console.dir(item);
			console.log(i);
			if(item.disabled == true){
				dijit.byId('dijit_form_CheckBox_'+i).set('disabled',true);
			}
		});
});


//	alert("load error");
	//dijit.byId('dijit_set_enabled_Attr_3').set('readOnly',!enabled);
}

function initBranchTree() {
	var _url = appRoot + "/common/selbranch/queryTheStore.action?month="+month;
//	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" , "dojo/_base/array" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt,array) {
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
				clickEventCheckBox : false
			}, "branchTree");
		
			tree.on("onLoad",test());
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
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(selArr, function(branch, i) {
			if (branch.type == 'branch'&&branch.disabled != true) {
				idArr.push(branch.id);
				nameArr.push('[' + branch.id + ']' +branch.name);
			}
		});
		
		console.dir(idArr);
		console.dir(nameArr);
		
		parent.closeBranchDlg(idArr.join(), nameArr.join());
	});
	if (selArr.length == 0) {
		alert('请选择门店！');
	}
}

function closeBranchDlg(_ids, _names) {
	dojo.byId('branchId').value = _ids;
	dojo.byId('branchText').innerHTML = _names;
	branchDlg.hide();
}