require([ 
          "dojo/request/xhr", 
          'custom/FilteringSelect', 
          'dojo/store/Memory',
          'dojo/domReady!' 
        ], function(xhr, FilteringSelect, Memory) {
	var _url = appRoot + "/businessquery/peoplepay/getPeople.action";
	_url = getUrl(_url);
	
	xhr(_url, {
		handleAs : "json"
	}).then(function(dataJson) {
		var myStore = new Memory({data:dataJson,idProperty:'peopleC'});

		var serManSelect = new FilteringSelect({
			id : "peopleId",
			name : "peopleId",
			value : 'peopleC',
			store : myStore,
			searchAttr : 'peoplePinyin',
			labelAttr : 'peopleC',
			displayValueAttr : 'peopleC',
			required : false,
			autoComplete : false,
			style : 'width: 120px; height: 17px;'
		}, "peopleId");

	});
});

function doQuery() {
	var startDate = dojo.byId("startDate").value;
	var endDate = dojo.byId("endDate").value;
	var branchId = dojo.byId("branchId").value;
	var peopleId = dijit.byId('peopleId').get('value');
	
//	console.log('------------------');
//	console.dir(dijit.byId('peopleId'));

	if (!startDate || !endDate) {
		alert("请选择开始日期和结束日期！");
		return;
	}
	
	require(["dojo/request/xhr"], function(xhr){
		  xhr.post(appRoot + "/businessquery/peoplepay/getColumn.action", {
		    handleAs: "json",
		    data:{
				startDate :startDate,
				endDate: endDate,
				branchId:branchId,
				peopleId:peopleId
			}
		  }).then(function(data){
		    // Do something with the handled data
			  getColumn(data);
			  getData(xhr);
		  }, function(err){
		    // Handle the error condition
			  alert(error);
		  });
		});
	return false;
}

function getTotals(data) {
	var totals = {};

	// the total for pay
	totals.pay_01 = totals.pay_02 = totals.pay_03 = totals.pay_04 = totals.pay_05 = 0;
	totals.pay_06 = totals.pay_07 = totals.pay_08 = totals.pay_09 = totals.pay_14 = 0;
	totals.pay_15 = totals.pay_16 = totals.pay_17 = totals.pay_19 = totals.pay_20 = 0;
	totals.pay_21 = totals.pay_22 = totals.pay_23 = totals.pay_24 = totals.pay_25 = 0;
	totals.pay_26 = totals.pay_27 = totals.pay_sum = 0;

	for (var i = data.length; i--;) {
		for ( var k in totals) {
			var elem = data[i][k];

			if (elem == undefined || elem == null || elem == "") {
				totals[k] += 0;
			}else {
				totals[k] += elem;
			}
		}
	}

	for ( var k in totals) {
		if (totals[k] != Math.round(totals[k])) {
			totals[k] = totals[k].toFixed(2);
		}
	}
	
	totals.cBranch_N = '合计';

	return totals;
}

function getData(xhr) {
	
	var startDate = dojo.byId("startDate").value;
	var endDate = dojo.byId("endDate").value;
	var branchId = dojo.byId("branchId").value;
	var peopleId = dijit.byId('peopleId').get('value');
	
	require(["dojo/request/xhr"], function(xhr){
		  xhr.post(appRoot + "/businessquery/peoplepay/getData.action", {
		    handleAs: "json",
		    data:{
				startDate :startDate,
				endDate: endDate,
				branchId:branchId,
				peopleId:peopleId
			}
		  }).then(function(data){
		    // Do something with the handled data
			  queryDetail(data);
		  }, function(err){
		    // Handle the error condition
			  alert(error);
		  });
		});
}

var cols;

function getColumn(data) {

	if (cols != undefined) {
		cols.splice(0, cols.length);
	} else {
		cols = [];
	}

	require(
			[ "dojo/store/Memory" ],
			function(Memory) {
				var store = new Memory({
					data : data
				});

				var innerSets = [];
				var columnSets = [
						{
							label : "门店名称",
							field : "cBranch_N",
							formatter : function(field, renderCell) {
								return "<a href='javascript: parent.addTab(\""
										+ field
										+ "\","
										+ "\"../businessquery/tableListQuery/listTable.action?shopC="
										+ renderCell.cBranch_C + "&startDate="
										+ dojo.byId("startDate").value
										+ "&endDate="
										+ dojo.byId("endDate").value + "\");'>"
										+ field + "</a>";
							}
						}, {
							label : "结帐人",
							field : "cSettleMan"
						}, ];

				var columnSubs = [];
				var innerSubs = [];

				store.query().forEach(function(item) {
									for ( var k in item) {
										var label = item[k] + "";

										innerSubs[innerSubs.length] = {
											field : k,
											label : label,
											formatter : function(field,
													renderCell) {
												if (field == undefined) {
													return "";
												}
												
//												if(label == '合计'){
													return field;
//												}

//												return "<a href='javascript: parent.addTab(\""
//														+ renderCell.cBranch_N
//														+ "\","
//														+ "\"../jsp/businessquery/payShopBillList.action?shopC="
//														+ renderCell.cBranch_C
//														+ "&type="
//														+ renderCell.readPayCode
//														+ "&currentPage=0&startDate="
//														+ dojo
//																.byId("startDate").value
//														+ "&endDate="
//														+ dojo.byId("endDate").value
//														+ "\");'>"
//														+ parseFloat(field)
//														+ "</a>";
											}
										};
									}
									;
								});

				innerSets.push(columnSets);
				columnSubs.push(innerSubs);

				cols.push(innerSets);
				cols.push(columnSubs);
			});
}

var grid;

function queryDetail(data) {
	if (grid == undefined) {
		initGrid(data);
	} else {
		require([ "dojo/store/Memory" ], function(Memory) {
			var store = new Memory({
				data : data
			});
			grid.set('columnSets', cols);
			grid.set('store', store);
			grid.set('summary', getTotals(data));
		});
	}
}

function initGrid(data) {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dojo/window", 'custom/SummaryRow',
			'dojo/_base/lang', "dgrid/ColumnSet" ], function(OnDemandGrid,
			Memory, declare, Keyboard, win, SummaryRow, lang, ColumnSet) {

		// calculate the grid height for avoid the outside scrollbar
		var vs = win.getBox();
		var gridHeight = vs.h - 80;
		var gridNode = dojo.byId("dataGrid");
		gridNode.style.height = gridHeight + "px";

		var CustomGrid = declare([ OnDemandGrid, Keyboard, SummaryRow,
				ColumnSet ]);
		grid = new CustomGrid(lang.mixin({
			store : new Memory({
				data : data
			}),
			columnSets : cols,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...',
			farOffRemoval : 10000, // larger than total height of data; never
			// remove rows
			minRowsPerPage : 10000
		// request more data at a time
		}), "dataGrid");

		grid.set('summary', getTotals(data));

		grid.startup();
	});
}
