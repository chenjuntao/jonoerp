require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {

		requisitionGrid.init();
	});
});

function doQuery() {
	requisitionGrid.query(getQuery());
}

function getQuery() {
	return {
		itemName : dojo.byId('itemName').value.trim(),
		startDate : dojo.byId('startDate').value,
		queryStr : dojo.byId('queryStr').value,
		endDate : dojo.byId('endDate').value
	}
}
