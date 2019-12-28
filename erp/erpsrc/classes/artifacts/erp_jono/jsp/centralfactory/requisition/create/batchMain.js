require([ "dojo", "dojo/ready","dojox/widget/Standby" ], function(dojo, ready,Standby) {
	ready(function() {
		requisitionGrid.init();
		
		standby = new Standby({
			target : "billForm"
		});
		
		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

var standby = null;

function doQuery() {
	requisitionGrid.query(getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		workshop : dojo.byId('workshop').value,
		queryStr : dojo.byId('queryStr').value,
		endDate : dojo.byId('endDate').value
	}
}
