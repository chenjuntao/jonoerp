dojo.ready(function() {
	if('CENTRALFACTORY' == branchType){
		if( 'allocation' == mrpType){
			cols = cfaCols;
		}else{
			cols = cfoCols;
		}
	}else{
		if( 'allocation' == mrpType){
			cols = lcaCols;
		}else{
			if('RAW' == itemType){
				cols = lcoRawCols;
			}else{
				cols = lcoSemiCols;
			}
		}
	}
	
	initGrid();
});

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	fillData(grid.get('store').getData());
}

function doClose() {
	closeTab(tabId);
}

var grid = null;
var myStore = null;
function initGrid() {
	require([ 
	          "dgrid/OnDemandGrid", 
	          "custom/store/Server",
	          "dojo/_base/declare",
	          "dgrid/Keyboard",
	          "dgrid/extensions/ColumnResizer", 
	          "dojo/parser", 
	          "dgrid/ColumnSet", 
	          "dojo/domReady!" 
          ], function(OnDemandGrid, Server, declare, Keyboard, ColumnResizer, parser, ColumnSet) {
		parser.parse();

		var _url = appRoot + "/common/mrp/query.action?branchType=" + branchType + "&itemId=" + itemId + "&mrpType="
				+ mrpType+"&itemType="+itemType;
		
		myStore = new Server({
			target : _url,
			idProperty : 'rownumber'
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}


function doScan(row) {
	doDetailScan(row.form_id);
}

function doScan2(row) {
	doDetailScan(row.form_id2);
}

