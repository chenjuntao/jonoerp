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
	// var _url = appRoot + "/restaurant/shopSemisQuery/loadTree.action";
	var _url = appRoot + "/cf/workorder/set/queryWorkShop.action";
	_url = getUrl(_url);
	
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
	rootLabel : '车间',
	multiState : true,
	checkedRoot : true,
	checkedStrict : true,
	mayHaveChildren : function(item) {
		var children = this.store.getChildren(item);
		if (children.length > 0) {
			return true;
		}
		return false;
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
	if (_cate.parent != "root") {

		dojo.byId('workOrderId').value = _cate.id;
		dojo.byId('workshop').value = _cate.name;
		dojo.byId('factoryId').value = _cate.parent;
		dojo.byId('priority').value = _cate.priority;
		dojo.byId('idText').innerHTML = _cate.id;
		if (0 == parseInt(_cate.priority)) {
			// 主仓
			dojo.byId('typeText').innerHTML = '是';
		} else {
			// 副仓
			dojo.byId('typeText').innerHTML = '否';
		}
	}
}

function toggleBtn(_type) {
	if (_type == 'disable') {
		dojo.byId("saveBtn").disabled = true;
		dojo.byId("addBtn").disabled = true;
		dojo.byId("delBtn").disabled = true;
		dojo.byId("setBtn").disabled = true;
	} else {
		dojo.byId("saveBtn").disabled = false;
		dojo.byId("addBtn").disabled = false;
		dojo.byId("delBtn").disabled = false;
		dojo.byId("setBtn").disabled = false;
	}
}

function doSave() {
	var idText = dojo.byId('idText').innerHTML;
	if (idText == '') {
		alert('请选择央厂！');
		return;
	}
	var _url = appRoot + "/cf/workorder/set/save.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.workOrderId = dojo.byId('workOrderId').value;// 更新类别信息
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
	var _parentId = dojo.byId('workOrderId').value;
	var _title = "新增类别";
	var _url = appRoot + "/cf/workorder/set/addView.action?parentId=" + _parentId;
	_url = getUrl(_url);
	
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
function setMain() {
	var priority = dojo.byId('priority').value;
	if (0 == parseInt(priority)) {
		alert("本车间已为主车间！");
		return;
	}

	if (isEmpty(priority)) {
		alert("请先选择车间！");
		return;
	}

	var _url = appRoot + "/cf/workorder/set/setMain.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		xhr.post(_url, {
			handleAs : "json",
			data : dataObj
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("设置车间成功！");
				reloadCate();
			} else {
				alert("设置车间失败！");
			}
		}, function(err) {
		});
	});
}
function doDelete() {
	var workOrderId = dojo.byId('workOrderId').value;

	if (isEmpty(workOrderId)) {
		alert("请先选择车间！");
		return;
	}
	var priority = dojo.byId('priority').value;

	if (0 == parseInt(priority)) {
		alert("主车间不能删除！");
		return;
	}
	var _url = appRoot + "/cf/workorder/set/del.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				workOrderId : dojo.byId('workOrderId').value
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