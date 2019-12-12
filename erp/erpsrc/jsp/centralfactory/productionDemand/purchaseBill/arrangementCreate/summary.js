
function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;

function doSubmit() {
	require([ "dojo/dom" ], function(dom) {
		var $storageId = dom.byId('storageId');
		dom.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;

		var _url = appRoot + '/centralfactory/productionDemand/purchaseBill/arrangementCreate/checkView.action?parentTabId=' + tabId;
		_url = getUrl(_url);
		
		addPostTab('billForm', '生成采购单', _url);
	});
}

function doClose() {
	closeTab(tabId);
}
