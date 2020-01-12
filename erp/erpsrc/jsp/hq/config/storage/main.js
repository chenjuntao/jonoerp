require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		reloadCate();
		loadData({
			id : '',
			name : ''
		});
	});
});

var g_lastNode = null;
function reloadCate() {
	reset();
	
	var _url = appRoot + "/hq/storage/set/queryStorage.action";
	
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
	rootLabel : '部门',
	multiState : true,
	checkedRoot : true,
	checkedStrict : true,
	mayHaveChildren : function(item) {
		if (item.isLeaf == 'Y') {
			return false;
		}
		return true;
	}
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
		// openOnClick : true,
		icon : null,
		showRoot : false,
		onClick : function(item, node, evt) {
			loadData(item);
			displayBtn(item);
			this._onExpandoClick({
				node : node
			}); // This will expand the node

			g_lastNode = cateTree.lastFocused.item;
		}
	};
	
	
	function displayBtn(_item){
		
		if('Y' == _item.isLeaf){ // 叶子节点
			if(0 == _item.priority){
				availableBtn(['saveBtn']);
			}else{
				availableBtn(['saveBtn','deleteBtn','setBtn']);
			}
		}else{
			//非叶子节点
			if(_item.parent ==  'root'){
				availableBtn([]);
			}else{
				availableBtn(['addBtn']);
			}
		}
	}

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

		availableBtn([]);

//		if (g_lastNode != null) {
//			var path = g_lastNode.path;
//			var pathArr = path.split('/');
//			// pathArr.pop();
//			pathArr[0] = 'root';
//			setTimeout(function() {
//				cateTree.set('paths', [ pathArr ]); // 使得修改更加显而易见
//			}, 20);
//		}
	}
});

function loadData(_item) {
	function byId(node){
		return dojo.byId(node);
	}
	
	if('Y' == _item.isLeaf){
		byId('storageId').value = _item.storage_id;
		byId('storageName').value = _item.storage_name;
		byId('priority').value = _item.priority;
		byId('branchId').value = _item.parent;
		byId('idText').innerHTML = _item.storage_id;
		
		if(0 == parseInt(_item.priority)){
			//主仓
			byId('typeText').innerHTML = '主仓';
		}else{
			//副仓
			byId('typeText').innerHTML = '副仓';
		}
		
	}else{
		byId('branchId').value = _item.id;
		
		reset();
	}
}

function reset(){
	function byId(node){
		return dojo.byId(node);
	}
	
	byId('storageId').value = '';
	byId('storageName').value = '';
	byId('idText').innerHTML = '';
	byId('typeText').innerHTML = '';
}

var btnArr = ['saveBtn','addBtn','deleteBtn','setBtn'];

function availableBtn(availableArr) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(btnArr, function(item) {
			dojo.byId(item).disabled = true;
		});
		
		array.forEach(availableArr, function(item) {
			dojo.byId(item).disabled = false;
		});
	});
}

function doSave() {
	var storageId = dojo.byId('storageId').value;
	
	if(isEmpty(storageId)){
		alert("请先选择仓库！");
		return;
	}
	
	var _url = appRoot + "/hq/storage/set/save.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		xhr.post(_url, {
			handleAs : "json",
			data : dataObj
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");

				reloadCate();
			} else {
				alert("保存失败！");
			}
		}, function(err) {
		});
	});
}

function deleteStorage() {
	var storageId = dojo.byId('storageId').value;
	
	if(isEmpty(storageId)){
		alert("请先选择仓库！");
		return;
	}
	
	var priority = dojo.byId('priority').value;
	
	if(0 == parseInt(priority)){
		alert("主仓不能删除！");
		return;
	}
	
	var _url = appRoot + "/hq/storage/set/del.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		xhr.post(_url, {
			handleAs : "json",
			data : dataObj
		}).then(function(data) {
			if (parseInt(data.count) == 0) {
				alert("删除仓库成功！");
				
				reloadCate();
			} else if(parseInt(data.count) > 0) {
				alert("该仓库库存不为空，不能删除！");
			}else{
				alert("删除仓库失败！");
			}
		}, function(err) {
		});
	});
}

function setMain() {
	var priority = dojo.byId('priority').value;
	if(0 == parseInt(priority)){
		alert("本仓库已为主仓！");
		return;
	}
	
	if(isEmpty(priority)){
		alert("请先选择仓库！");
		return;
	}
	
	var _url = appRoot + "/hq/storage/set/setMain.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		xhr.post(_url, {
			handleAs : "json",
			data : dataObj
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("设置主仓成功！");
				reloadCate();
			} else {
				alert("设置主仓失败！");
			}
		}, function(err) {
		});
	});
}

var editDlg = null;
function doAdd() {
	var _branchId = dojo.byId('branchId').value;
	var _title = "新增仓库";
	var _url = appRoot + "/hq/storage/set/addView.action?branchId=" + _branchId;
	
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "450px",
			height : "120px"
		}
		createDialog(option, function(iDlg) {
			editDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		editDlg.show();
	}
}

function closeEditDlg() {
	reloadCate();
	editDlg.hide();
}
