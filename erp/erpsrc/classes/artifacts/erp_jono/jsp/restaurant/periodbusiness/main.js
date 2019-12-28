dojo.require("dojox.form.BusyButton");

var busyButton;
dojo.ready(function(){
	busyButton = new dojox.form.BusyButton({
         id: "submit",
         busyLabel: "正在查询...",
         label: "查询",
         timeout: 10000
  }, "placeHolder");
  
  dojo.connect(dijit.byId("submit"), "_onClick", function(){
	  	doQuery();
	  });
});

function doQuery() {
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/restaurant/periodbusiness/listPeriodBussiness.action";
		_url = getUrl(_url);
		
		xhr.post({
			url : _url,
			form : 'queryForm',
			handleAs : "json",
			load : function(data) {
				queryDetail(data);
				busyButton.cancel();
			},
			error : function(error) {
				alert(error);
			}
		});
	});
	return false;
}

var grid;

function queryDetail(data) {
	if (grid == undefined) {
		initGrid(data);
	} else {
		require([ "dojo/store/Memory" ], function(Memory) {
			var store = new Memory({ data: data });
			grid.set('store', store);
		});
	}
}

function initGrid(response) {
	require([ "dgrid/OnDemandGrid",
	          "dojo/store/Memory",
	          "dojo/_base/declare", 
	          "dgrid/Keyboard",
	          "custom/SummaryRow",
	          "dojo/_base/lang",
	          "dgrid/Selection"
			], function(OnDemandGrid, Memory, declare, Keyboard, SummaryRow,lang,Selection) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard ,SummaryRow,Selection]);
		grid = new CustomGrid(lang.mixin({
			store : new Memory({ data: response }),
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}), "dataGrid");
		
		grid.on("dgrid-refresh-complete", function(event) {
				busyButton.cancel();
		});
		
		grid.startup();
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable:false,
	formatter : function(field) {
		if(field == "合计"){
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "时段",
	field : "timeSlot",
	className:"text-center",
	sortable:false,
},{
	label : "结单人数",
	field : "guestCount",
	sortable:false,
	className:"text-right",
	formatter : function(field,renderCell) {
		if(renderCell.rownumber == "合计"){
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "单数",
	field : "billCount",
	sortable:false,
	className:"text-right",
	formatter : function(field,renderCell) {
		if(renderCell.rownumber == "合计"){
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "人均时间",
	field : "guestPerTime",
	className:"text-right",
	sortable:false
}, {
	label : "人均消费",
	field : "guestPerCost",
	className:"text-right",
	sortable:false
}, {
	label : "消费总额",
	field : "foodAmtSum",
	className:"text-right",
	sortable:false,
	formatter : function(field,renderCell) {
		if(renderCell.rownumber == "合计"){
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "结单金额",
	field : "oughtAmt",
	className:"text-right",
	sortable:false,
	formatter : function(field,renderCell) {
		if(renderCell.rownumber == "合计"){
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "实收金额",
	field : "payAmt",
	className:"text-right",
	sortable:false,
	formatter : function(field,renderCell) {
		if(renderCell.rownumber == "合计"){
			return getBoldText(field);
		}
		return field;
	}
}, {
	label : "门店编码",
	field : "shopC",
	sortable:false
}, {
	label : "门店名称",
	field : "shopName",
	sortable:false
} , {
	label : "",
	field : "none",
	sortable:false
}];
