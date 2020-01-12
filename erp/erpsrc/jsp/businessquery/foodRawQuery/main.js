var loadingMsg = getLoadingMsg();
var queryAmount = null;

require([
         "dojo", 
         "dojo/ready", 
         "dojo/request/xhr", 
         "dojo/store/Memory", 
         "dijit/Tree", 
         "dijit/tree/ObjectStoreModel",
		 "dojo/window"
         ], function(dojo, ready, xhr, Memory, Tree, ObjectStoreModel, win) {
	ready(function() {
		initTree();
	});

	function initTree() {
		var _url = appRoot + "/restaurant/selmaterial/loadTree.action";
		_url = getUrl(_url);
		
		xhr(_url, {
			handleAs : 'json'
		}).then(function(data) {
			myStore = new Memory({
				data : data,
				getChildren : function(object) {
					return this.query({
						parent : object.id
					});
				}
			});

			var myModel = new ObjectStoreModel({
				store : myStore,
				query : { // 指定根节点
					id : 'root'
				},
				mayHaveChildren : function(item) {
					var children = this.store.getChildren(item);
					if (children.length > 0) {
						return true;
					}
					return false;
				}
			});

			var tree = new Tree({
				model : myModel,
				showRoot : false, // 不展示根节点
				autoExpand : true, // 自动展开
				onClick : function(item, node, evt) { // 点击触发事件
					queryDetail(item.id, item.name);
				}
			}, "categoryTree");
			tree.startup();
		}, function(err) {
			alert("load error");
		});
	}
	
	function getQuery() {
		return {
			shopC : dojo.byId('shopC').value,
			startDate : dojo.byId('startDate').value,
			endDate : dojo.byId('endDate').value
		};
	}

	queryAmount = function () {
		dojo.byId("payAmt").innerHTML = '';
		var _url = appRoot + "/businessquery/foodRawQuery/queryAmount.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : 'json',
			data : getQuery()
		}).then(function(data) {
			dojo.byId("payAmt").innerHTML = data.payAmt;
		}, function(err) {
			alert("load error");
		});
		return false;
	}
	
	function queryDetail(categoryId, categoryName) {
		dojo.byId("detailGrid").innerHTML = loadingMsg;
		var _url = appRoot + "/businessquery/foodRawQuery/queryDetail.action?categoryId=" + categoryId;
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : 'text',
			data : getQuery()
		}).then(function(data) {
			var grid = dojo.byId("detailGrid");
			grid.innerHTML = data;
			addEvent();
		}, function(err) {
			alert("load error");
		});
	}

});

function addEvent() {
	var $tr = dojo.query("#datazone tr.highlight");
	$tr.on("mouseenter, mouseleave", function(e) {
		if (e.type == "mouseenter") {
			dojo.style(e.target, "backgroundColor", "#ffff66");
		} else {
			dojo.style(e.target, "backgroundColor", "#d4e3e5");
		}
	});
}

