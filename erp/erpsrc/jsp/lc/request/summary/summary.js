require([ "dojo/dom-attr", "dojo/query", "dijit/layout/TabContainer", "dijit/layout/ContentPane", "dojo/domReady!" ],
		function(attr, query, TabContainer, ContentPane) {
			new ContentPane({
				title : '餐厅要货（直配）'
			}, 'directCp');
			new ContentPane({
				title : '餐厅要货（越库）'
			}, 'crossCp');
			new ContentPane({
				title : '餐厅要货（统配）、外部订货、需求预估'
			}, 'unifiedCp');
			var tc = new TabContainer({}, "deliveryTc");
			tc.startup();
		});

function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;

function doSubmit() {
	require([ "dojo/dom" ], function(dom) {
		var $storageId = dom.byId('storageId');
		dom.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;

		var _url = appRoot + '/lc/request/summary/checkView.action?parentTabId=' + tabId;
		_url = getUrl(_url);
		
		addPostTab('billForm', '生成采购单', _url);
	});
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
