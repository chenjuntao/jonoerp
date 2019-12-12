require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		var query = getQuery();
		orderGrid.init(query);
	});
});

function doQuery() {
	query();
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		outOrderId : dojo.byId('outOrderId').value,
	}
}

function query() {
	var _url = appRoot + "/outerorder/handle/query.action";
	
	require([ 
	          "dojo/_base/lang",
	          "dojo/request/xhr", 
	          "dojo/store/Observable" ,
	          "dojo/store/Memory"
	        ], function(lang,xhr,Observable,Memory) {
		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(
				function(data) {
					var myStore =  Observable(new Memory({
						data : data,
						idProperty : 'form_id'
					}));
					
					orderGrid.set('store',myStore);

				}, function(err) {
					return null;
					// Handle the error condition
				});
	})
}
