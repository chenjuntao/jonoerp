function initTree() {
	var _url = appRoot + "/restaurant/costcard/loadTree.action";
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dijit/Tree", 
	          "dijit/tree/ObjectStoreModel","dojo/dom-form", "dojo/domReady!" ],
			function(xhr, Memory, Tree, ObjectStoreModel,domForm) {
				xhr.post(_url, {
					handleAs : 'json',
					data:domForm.toObject("queryForm")
				}).then(function(data) {
					myStore = new Memory({
						data : data,
						getChildren : function(object) {
							return this.query({
								parent_id : object.id
							});
						}
					});

					// Create the model
					var myModel = new ObjectStoreModel({
						store : myStore,
						query : {
							id : g_foodId
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
						showRoot : true,
						autoExpand : true
					}, "myTree");
					tree.startup();
				}, function(err) {
					alert("load error");
				});
			});
}

var g_cateId;

function queryByCate() {
	var _url = appRoot + "/restaurant/shopdish/listItem.action";
	_url += "?categoryId=" + g_cateId;
	_url = getUrl(_url);
	
	queryItem(_url);
}