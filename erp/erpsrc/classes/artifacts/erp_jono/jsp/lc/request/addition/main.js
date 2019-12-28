require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initData();
		var query = getQuery();
		requestGrid.init(query);
		outerGrid.init(query);
	});
});

function initData() {
	require([ "dojo/dom" ], function(dom) {
	});
}

function doQuery() {
	require([ "dojo/dom", "dojo/date", "dojo/date/locale" ], function(dom,
			date, locale) {
		var query = getQuery();
		requestGrid.query(query);
		outerGrid.query(query);
	});
}

function getQuery() {
	return {
		businessDate : dojo.byId('businessDate').value
	}
}

var grid = null;
var dataStore = null;
