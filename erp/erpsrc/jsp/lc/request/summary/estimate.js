require([ "dojo/ready" ], function(ready) {
	ready(function() {
		initGlobal();
		addEvent();
		//
		initGrid();
	});
});

/**
 * 初始化全局函数
 */
function initGlobal() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
	});
}

function addEvent() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		query(".Wdate").forEach(function(node) {
			node.onfocus = function(e) {
				WdatePicker();
			};
		});
		dom.byId('btn_submit').onclick = doSubmit;
	});
}

function calcSuggest() {
	grid.save().then(function(value) {
		var _url = appRoot + "/lc/request/summary/queryAverageRequest.action";
		_url = getUrl(_url);
		
		require([ "dojo/dom", "dojo/request/xhr" ], function(dom, xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					jsonData : JSON.stringify(dataStore.query()),
					refDateStart1 : dom.byId('refDateStart1').value,
					refDateEnd1 : dom.byId('refDateEnd1').value,
					refDateStart2 : dom.byId('refDateStart2').value,
					refDateEnd2 : dom.byId('refDateEnd2').value,
					refDateStart3 : dom.byId('refDateStart3').value,
					refDateEnd3 : dom.byId('refDateEnd3').value
				}
			}).then(function(data) {
				grid = null;
				dataStore = null;
				
				dojo.empty("dataGrid");
				initGrid(data);
			}, function(err) {
			});
		});
		
	}, function(err) {
		console.log(err);
	});

}

function validateGrid() {
	if (!validateColumn(grid, 'orderCount')) {
		return false;
	}
	return true;
}

function doSubmit() {
	if (!validateGrid()) {
		return;
	}
	require([ "dojo/dom", "dojo/date", "dojo/date/locale" ], function(dom, date, locale) {
		grid.save().then(function(value) {
			
			var rows = dataStore.query();
			for(var i=0,length = rows.length;i<length;i++){
				rows[i].payAmt = parseFloat((rows[i].orderCount * rows[i].itemUnitPrice).toFixed(4)); // 计算金额
				rows[i].unitAmt = parseFloat((rows[i].orderCount * rows[i].itemPrice).toFixed(4)); // 计算金额
			}
			
			dom.byId('jsonData').value = JSON.stringify(rows);

			var _url = appRoot + '/lc/request/summary/checkView.action?parentTabId=' + tabId;
			_url = getUrl(_url);
			
			addPostTab('billForm', '采购单生成', _url);
			
		}, function(err) {
			console.log(err);
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid(gridData) {
	require([ "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/Observable", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox","dojo/keys","dojo/on",
			"dgrid/extensions/ColumnResizer","dojo/query" ], function(
			OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor, NumberTextBox,keys,on,ColumnResizer,query) {
		execute(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor, NumberTextBox,keys,on,ColumnResizer,query);
	});

	function execute(OnDemandGrid, selector, Selection, Observable, Memory, declare, Keyboard, editor, NumberTextBox,keys,on,ColumnResizer,query) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		
		var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer]);
		grid = new CustomGrid({
			store : dataStore,
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			minRowsPerPage:10000,
			maxRowsPerPage:10000,
			bufferRows:10000,
			farOffRemoval:10000,
			columns : getColumn(editor, selector, NumberTextBox),
			selectionMode : "toggle",
			allowSelectAll : true,
			loadingMessage : '加载中...',
		}, "dataGrid");

		
		//TODO
		on(grid, 'keydown', function(e){
		    if(e.keyCode === keys.UP_ARROW){
		    	 Keyboard.moveFocusUp.call(grid, e);
		    }else if(e.keyCode === keys.DOWN_ARROW){
		    	 Keyboard.moveFocusDown.call(grid, e);
		    }else if(e.keyCode === keys.ENTER){
		    	Keyboard.moveFocusDown.call(grid, e);
		    }
		});
		
		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);
				
				if(!isEmpty($input[0])){
					$input[0].select();
				}
			}
		});
		
		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		grid.startup();

		initGridData(gridData);
	}
}

function initGridData(gridData) {
	if(gridData == undefined){
		var _url = appRoot + "/lc/request/summary/queryMRP.action?ids=" + ids;
		
		_url = getUrl(_url);
		require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				// 如果没有统配相关的数据，则提供进入下一步的选择
				if (data.length == 0) {
					alert('无统配，直接进入下一步！');
					doSubmit();
					return;
				}

				array.forEach(data, function(row, i) {
					var safeCount = parseFloat((row.distribution * row.inventorysCycle).toFixed(2));
					row.safeCount = safeCount;
					// 默认把当前要货量当成日均要货量
					var suggest = row.distribution + row.allocation + safeCount - row.realCount - row.roadCount;
					if (suggest < 0) {
						suggest = 0;
					}
					row.suggestCount = parseFloat(suggest.toFixed(2));
					if (suggest > 0 && suggest < row.minOrderCount) {// 如果建议值不为零并且小于最小订货倍量，则默认使用最小订货倍量为采购量
						row.orderCount = row.minOrderCount;
					} else {
						row.orderCount = row.suggestCount;
					}
					
					row.payAmt = parseFloat((suggest * row.itemUnitPrice).toFixed(2));
					row.unitAmt = parseFloat((suggest * row.itemPrice).toFixed(2));
				});
				dataStore.setData(data);
				grid.set("store", dataStore);

			}, function(err) {
			});
		});
	}else{
		dataStore.setData(gridData);
		grid.set("store", dataStore);
	}
}

function getColumn(editor, selector, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "原料编码",
		field : "itemId",
		sortable:false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable:false
	}, {
		label : "类别",
		field : "categoryName",
		sortable:false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable:false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable:false
	}, {
		label : "最小订货倍量",
		field : "minOrderCount",
		className : 'grid-number',
		sortable:false
	}, editor({
		label : "采购量",
		field : "orderCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable:false
	}, NumberTextBox), {
		label : "日均订货量1",
		field : "average1",
		className : 'grid-number',
		sortable:false
	}, {
		label : "日均订货量2",
		field : "average2",
		className : 'grid-number',
		sortable:false
	}, {
		label : "日均订货量3",
		field : "average3",
		className : 'grid-number',
		sortable:false
	}, {
		label : "当前要货量",
		field : "requestCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : "应配送量",
		field : "distribution",
		className : 'grid-number',
		sortable:false
	}, {
		label : "供货周期",
		field : "supplyPeriod",
		className : 'grid-number',
		sortable:false
	},{
		label : "库存周期",
		field : "inventorysCycle",
		className : 'grid-number',
		sortable:false
	}, {
		label : "安全库存1",
		field : "safeCount1",
		className : 'grid-number',
		sortable:false
	}, {
		label : "安全库存2",
		field : "safeCount2",
		className : 'grid-number',
		sortable:false
	}, {
		label : "安全库存3",
		field : "safeCount3",
		className : 'grid-number',
		sortable:false
	}, {
		label : "默认安全库存",
		field : "safeCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : "已分配量",
		field : "allocation",
		className : 'grid-number',
		sortable:false
	}, {
		label : "在途量",
		field : "roadCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : '系统库存',
		field : 'realCount',
		className : 'grid-number',
		sortable:false
	}, {
		label : "建议1",
		field : "suggestCount1",
		className : 'grid-number',
		sortable:false
	}, {
		label : "建议2",
		field : "suggestCount2",
		className : 'grid-number',
		sortable:false
	}, {
		label : "建议3",
		field : "suggestCount3",
		className : 'grid-number',
		sortable:false
	}, {
		label : "默认建议值",
		field : "suggestCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : "标准价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable:false
	},  {
		label : "进货价",
		field : "itemPrice",
		className : 'grid-number',
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ];
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
