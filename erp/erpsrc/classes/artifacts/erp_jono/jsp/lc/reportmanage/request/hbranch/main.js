var grid;
var cols = [ {
	label : "小计",
	field : "sum",
	className : 'text-right',
	sortable : false
} ];

function customExport() {
	var columns = getColumn();
	var newCols = [];

	var args1 = [ 'center', 'center', 'left','center' ];
	var args2 = [ 50, 65, 160 ,50];

	require([ "dojo/_base/array" ], function(array) {
		array.forEach(columns[0][0], function(item, i) {
			newCols.unshift({
				display : item.label,
				name : item.field,
				align : args1[i],
				width : args2[i]
			});
		});

		array.forEach(columns[1][0], function(item, i) {
			newCols.unshift({
				display : item.label,
				name : item.field,
				align : 'right',
				width : '120'
			});
		});
	});
	newCols.reverse();
	fillData(newCols, grid.get('store').data);
}

function print() {
	prn1_preview(getExportArgs());
}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

function getAppendValue() {
	if (!isEmpty(dojo.byId("startDate").value)) {
		startDate = dojo.byId("startDate").value;
	} else {
		startDate = '';
	}

	if (!isEmpty(dojo.byId("endDate").value)) {
		endDate = dojo.byId("endDate").value;
	} else {
		endDate = '';
	}

	if (!isEmpty(dojo.byId("itemText").value)) {
		itemText = dojo.byId("itemText").value;
	} else {
		itemText = '';
	}

	var appendValue = '';

	if (!isEmpty(itemText)) {
		appendValue += "  AND d.ITEM_ID like '%" + itemText + "%' ";
	}

	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(h.AUDIT_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(h.AUDIT_TIME,'YYYY-MM-DD') <= '" + endDate + "' "
	}

	return appendValue;
}

dojo.ready(function() {
	initGrid();
});

function doQuery() {
	hasData = true;
	resetCols();
	
	dojo.byId('append').value = getAppendValue();
	grid.set('query', getQuery());
}

function resetCols(){
	for(;;){
		if(cols.length == 1){
			break;
		}else{
			cols.shift();
		}
	}
}


var hasData = true;

var grid = null;
function initGrid() {
	dojo.byId('append').value = getAppendValue();

	var _url = appRoot + "/reportmanage/request/lc/branch/collect/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/parser", "dojo/_base/declare","dgrid/extensions/ColumnResizer",
			"dgrid/ColumnSet", "dojo/dom-form", "dojo/domReady!" ], function(OnDemandGrid, Server, Observable, Cache, Memory,
			parser, declare, ColumnResizer, ColumnSet, domForm) {
		parser.parse();
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, ColumnResizer, ColumnSet ]);

		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();

		grid.on("dgrid-refresh-complete", function(event) {
			if (cols.length == 1 && hasData) {
				require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
					xhr.post(appRoot + "/reportmanage/request/lc/branch/collect/doQueryBranch.action", {
						handleAs : "json",
						data : domForm.toObject("dataForm")
					}).then(function(data) {
						if(data.length == 0){
							hasData = false;
							return;
						}
						
						array.forEach(data, function(item) {
							cols.unshift({
								label : '[' + item.branch_id + ']' + item.branch_name,
								field : item.branch_id,
								className : 'text-right',
								sortable : false
							});
						});

						grid.set('columnSets', getColumn());
					}, function(err) {
					});
				});
			}
		});
	});
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '商品编码',
		field : 'item_id',
		sortable : false,
		className:'text-center'
	}, {
		label : '商品名称',
		field : 'item_name',
		sortable : false
	}, {
		label : '单位',
		field : 'item_dimension',
		sortable : false,
		className:'text-center'
	} ] ], [ cols ] ];
}
