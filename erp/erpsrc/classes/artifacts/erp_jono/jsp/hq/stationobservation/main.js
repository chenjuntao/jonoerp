require([ "dojo/parser", "dijit/layout/BorderContainer",
		"dijit/layout/ContentPane", "dijit/layout/StackContainer",
		"dijit/layout/StackController", "dojo/domReady!" ], function(parser) {
	parser.parse();
	
});


require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		orderGrid.init({itemId:itemId});
		observationGrid.init({itemId:itemId});
	});
});
