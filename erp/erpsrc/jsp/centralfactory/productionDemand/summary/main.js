require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		var query = getQuery();
		orderGrid.init(query);
	});
});

function doQuery(){
	orderGrid.query(getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value
	}
}

function doAggregate() {
	var ids = orderGrid.getSelectIds();
	
	if (ids.length <= 0) {
		alert("数据为空！");
		return;
	}
	
	var _url = 	appRoot + "/centralfactory/productionDemand/summary/estimateView.action?parentTabId=" + tabId;
	_url = getUrl(_url);
	
	require([ "dojo/_base/array", "dojo/dom" ], function(array, dom) {
		dom.byId('ids').value = ids.join(",");
		
		if(!isSummaryValid(dojo.byId('ids').value)){
			return;
		}
		addPostTab('dataForm', '汇总数据', _url);
	});
}