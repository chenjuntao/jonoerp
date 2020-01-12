require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		reloadMenu();
		loadData({
			id : '',
			name : '',
			imageName : '',
			url : '',
			priority : ''
		});
	});
});

var g_lastNode = null;
function reloadMenu() {
	var _url = appRoot + "/setting/menu/queryAllMenu.action";
	_url = getUrl(_url);
	
	var _parentId = 'root';
	require([ "dojo/_base/xhr" ], function(xhr) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
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
	rootLabel : '所有菜单',
	multiState : true,
	checkedRoot : true,
	checkedStrict : true,
	mayHaveChildren : function(item) {
		if (item.isLeaf == 1) {
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
			loadData(item);
			toggleBtn('enable');
			this._onExpandoClick({
				node : node
			}); // This will expand the node

			g_lastNode = menuTree.lastFocused.item;
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

		toggleBtn('disable');

		if (g_lastNode != null) {
			var path = g_lastNode.path;
			var pathArr = path.split('/');
			// pathArr.pop();
			pathArr[0] = 'root';
			setTimeout(function() {
				menuTree.set('paths', [ pathArr ]); // 为了方便修改排序
			}, 20);
		}
	}
});

function loadData(_menu) {
	dojo.byId('menuId').value = _menu.id;
	dojo.byId('menuName').value = _menu.name;
	dojo.byId('imageName').value = _menu.imageName;
	dojo.byId('url').value = _menu.url;
	dojo.byId('priority').value = _menu.priority;
	dojo.byId('idText').innerHTML = _menu.id;
}

function toggleBtn(_type) {
	if (_type == 'disable') {
		dojo.byId("saveBtn").disabled = true;
		dojo.byId("addBtn").disabled = true;
		dojo.byId("moveBtn").disabled = true;
	} else {
		dojo.byId("saveBtn").disabled = false;
		dojo.byId("addBtn").disabled = false;
		dojo.byId("moveBtn").disabled = false;
	}
}

function doSave() {
	var idText = dojo.byId('idText').innerHTML;
	if (idText == '') {
		alert('请选择菜单！');
		return;
	}
	var _url = appRoot + "/setting/menu/saveMenu.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.menuId = dojo.byId('menuId').value;// 更新菜单信息
		xhr.post(_url, {
			handleAs : "json",
			data : dataObj
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");

				reloadMenu();
			} else {
				alert("保存失败！");
			}
		}, function(err) {
		});
	});
}

var editDlg = null;
function doAdd() {
	var _parentId = dojo.byId('menuId').value;
	var _title = "新增菜单";
	var _url = appRoot + "/setting/menu/addView.action?parentId=" + _parentId;
	_url = getUrl(_url);
	
	var frameId = 'ifr_edit';
	if (editDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "210px"
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
	reloadMenu();
	editDlg.hide();
}

var moveDlg = null;
function doMove() {
	var _menuId = dojo.byId('menuId').value;
	var _title = "移动菜单";
	var _url = appRoot + "/setting/menu/moveView.action";
	_url += "?menuId=" + _menuId;
	_url = getUrl(_url);
	
	var frameId = 'ifr_move';
	if (moveDlg == null) {
		var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "350px",
			height : "350px"
		}
		createDialog(option, function(iDlg) {
			moveDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		moveDlg.show();
	}
}

function closeMoveDlg() {
	reloadMenu();
	moveDlg.hide();
}