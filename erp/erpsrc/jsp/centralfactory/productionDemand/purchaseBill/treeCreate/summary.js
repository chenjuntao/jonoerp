
require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		treeSummaryGrid.init();
	});
});

function doSubmit() {
	require([ "dojo/dom" ], function(dom) {
		var $storageId = dom.byId('storageId');
		dom.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;

		var _url = appRoot + '/centralfactory/productionDemand/purchaseBill/treeCreate/treeCheckView.action?parentTabId=' + tabId;
		_url = getUrl(_url);
		
		addPostTab('billForm', '确认采购单', _url);
	});
}

function doClose() {
	closeTab(tabId);
}


function doScan (row) {
	var _url = appRoot
			+ "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	addTab("中央工厂生产计划单查看", _url);
};