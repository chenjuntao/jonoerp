/**
 * function
 */
var loadData = null;
var t = null;
var count=1;

function timedCount() {
	var firstModuleId = parent.frames['topFrame'].firstModuleId;
	if (firstModuleId) {
		loadData(firstModuleId);
		clearTimeout(t)
	}else{
		t = setTimeout("timedCount()", 1000)
	}
}


require([ "dojo", "dojo/ready", "dojox/widget/Standby", "dojo/request/xhr" ], function(dojo, ready, Standby, xhr) {
	ready(function() {
		// 初始化遮罩层
		standby = new Standby({
			target : "treeWrapper"
		});
		document.body.appendChild(standby.domNode);
		standby.startup();
		
		timedCount();
	});

	loadData = function(_parentId) {
		// 显示遮罩层
		standby.show();

		var _url = appRoot + "/desktop/listMenu.action?parentId=" + _parentId;
		
		xhr(_url, {
			handleAs : 'json'
		}).then(function(data) {
			// 数据获取完毕，隐藏遮罩层
			standby.hide();

			data.push({
				id : _parentId
			});
			treeStore.setData(data);

			modelConfig['store'] = treeStore;
			modelConfig['query'] = {
				id : _parentId
			};
			buildTree();

			widgets.initToggle();
		}, function(err) {
			errorHandler(err);
		});
	}
});

var treeStore = null;
var modelConfig = {
	store : null,
	query : null,
	rootLabel : 'The Simpsons',
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
		openOnClick : true,
		icon : null,
		showRoot : false,
		onClick : function(item, node, evt) {
			if (item.isLeaf == 1) {
				navigate(item);
			}
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
	}
});

function navigate(_menu) {
	var loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	
	if (_menu.url != '') {
		parent.frames['tab'].addTab(_menu.name, addParameter(appRoot + _menu.url,'myLoginUserId',loginUserId));
	}
}

function toggleNav() {
	if (divCollapsedNav.style.display == "none") {
		divCollapsedNav.style.display = "";
		divOpenNav.style.display = "none";
		parent.frmstOuter.cols = "24,*"
		parent.frmstOuter.scrolling = "no";
		// parent.document.all ('frmToolbar').noResize = true;
	} else {
		divOpenNav.style.display = "";
		divCollapsedNav.style.display = "none";
		parent.frmstOuter.cols = "216,*"
		parent.frmstOuter.scrolling = "auto";
		// parent.document.all ('frmToolbar').noResize = false;
	}
}

var imgMin = new Image();
imgMin.src = appRoot + "/jsp/common/img/minimize.gif";

var imgMax = new Image();
imgMax.src = appRoot + "/jsp/common/img/maximize.gif";

var imgItem = new Image();
imgItem.src = appRoot + "/jsp/common/img/arrows.gif";

function Widgets() {
	this.divCount = 0;

	this.ClosedText = "全部折叠";
	this.ClosedAlt = "全部折叠";
	this.OpenText = "全部展开";
	this.OpenAlt = "全部展开";

	this.Display = "";
	this.Img = imgMin;
	this.Text = this.OpenText;
	this.Alt = this.OpenAlt;

	this.toggleAll = toggleAll;// important
	this.initToggle = initToggle; // 初始化切换按钮显示的图标和文字等
}

var widgets = new Widgets();
function toggleAll() {
	if (this.Display == "open") {
		this.Display = "close";
		this.Img = imgMin;
		this.Text = this.ClosedText;
		this.Alt = this.ClosedAlt;
		expparent00.title = this.ClosedAlt;
		menuTree.expandAll();
	} else {
		this.Display = "open";
		this.Img = imgMax;
		this.Text = this.OpenText;
		this.Alt = this.OpenAlt;
		expparent00.title = this.OpenAlt;
		menuTree.collapseAll();
	}
	document.getElementById("exp00").src = this.Img.src;
	document.getElementById("textExp").innerHTML = this.Text;
	document.getElementById("exp00").alt = this.Alt;
}

function initToggle() {// 默认菜单为折叠状态
	this.Display = "open";
	this.Img = imgMax;
	this.Text = this.OpenText;
	this.Alt = this.OpenAlt;
	expparent00.title = this.OpenAlt;
	document.getElementById("exp00").src = this.Img.src;
	document.getElementById("textExp").innerHTML = this.Text;
	document.getElementById("exp00").alt = this.Alt;
}
