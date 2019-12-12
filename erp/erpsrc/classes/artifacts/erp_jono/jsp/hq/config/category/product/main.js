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
	var _url = appRoot + "/hq/category/product/queryCate.action";
	
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
	rootLabel : '所有出品',
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
		showRoot : true,
		onClick : function(item, node, evt) {
			loadData(item);
			toggleBtn('enable');
			if (item.id == 'root') {// 不允许修改根类别
				dojo.byId("saveBtn").disabled = true;
				dojo.byId("delBtn").disabled = true;
			}
			this._onExpandoClick({
				node : node
			}); // This will expand the node

			g_lastNode = cateTree.lastFocused.item;
		}
	};

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

		toggleBtn('disable');

		if (g_lastNode != null) {
			var path = g_lastNode.path;
			var pathArr = [ 'root' ];
			if (path != undefined) {
				pathArr = path.split('/');
				pathArr[0] = 'root';
			}
			setTimeout(function() {
				cateTree.set('paths', [ pathArr ]); // 使得修改更加显而易见
			}, 20);
		}
	}
});

function loadData(_cate) {
	dojo.byId('categoryId').value = _cate.id;
	dojo.byId('categoryName').value = _cate.name;
	dojo.byId('idText').innerHTML = _cate.id;
}

function toggleBtn(_type) {
	if (_type == 'disable') {
		dojo.byId("saveBtn").disabled = true;
		dojo.byId("addBtn").disabled = true;
		dojo.byId("delBtn").disabled = true;
	} else {
		dojo.byId("saveBtn").disabled = false;
		dojo.byId("addBtn").disabled = false;
		dojo.byId("delBtn").disabled = false;
	}
}

function doSave() {
	var idText = dojo.byId('idText').innerHTML;
	if (idText == '') {
		alert('请选择类别！');
		return;
	}
	var _url = appRoot + "/hq/category/raw/saveCate.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.categoryId = dojo.byId('categoryId').value;// 更新类别信息
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

var editDlg = null;
function doAdd() {
	var _parentId = dojo.byId('categoryId').value;
	var _title = "新增类别";
	var _url = appRoot + "/hq/category/raw/addView.action?itemType=PRODUCT&parentId=" + _parentId;
	
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

/**
 * 验证是否可以删除
 */
function deletable() {
	var _url = appRoot + "/hq/category/raw/deletable.action?itemType=PRODUCT&categoryId="
			+ dojo.byId('categoryId').value;
	
	var deletable = true;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (!data.deletable) {
				alert('此类别下已经存在物料，不允许删除！');
				deletable = false;
			}
		}, function(err) {
		});
	});
	return deletable;
}

function doDelete() {
	if (!deletable()) {
		return;
	}
	var _url = appRoot + "/hq/category/raw/deleteCate.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				categoryId : dojo.byId('categoryId').value
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("删除成功！");

				reloadCate();
			} else {
				alert("删除失败！");
			}
		}, function(err) {
		});
	});
}