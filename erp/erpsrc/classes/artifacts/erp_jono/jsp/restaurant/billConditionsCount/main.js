var type,startDate,endDate,shopC,period,shift;
var busyButton;

dojo.require("dojox.form.BusyButton");

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
	type = dojo.byId("type").value;
	startDate = dojo.byId("startDate").value;
	endDate = dojo.byId("endDate").value;
	shopC = dojo.byId("shopC").value;
	period = dojo.byId("period").value;
	shift = dojo.byId("shift").value;
	
	setCols();
	
	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/restaurant/billConditionsCount/freeBillInfo.action";
		
		if(type == 'zk'){
			 _url = appRoot + "/restaurant/billConditionsCount/discountInfo.action";
		}else if(type == 'zs'){
			_url = appRoot + "/restaurant/billConditionsCount/presentInfo.action";
		}else if(type == 'tp'){
			_url = appRoot + "/restaurant/billConditionsCount/returnInfo.action";
		}
		
		_url+= "?startDate="+ startDate	+"&endDate="+endDate + 
		"&period="+ period + "&shift="+ shift + "&shopC="+ shopC;
		_url = getUrl(_url);
		
		dojo.empty("gridDiv");
		initGrid(_url);
	});
	return false;
}


function setCols(){
	if(cols != undefined){
		cols.splice(0, cols.length);
	}else{
		cols = [];
	}
	
	cols.push(startCols);
	if(type == "md"){
		cols.push(mdCols);
	}else if(type == "zs"){
		cols.push(zsCols);
	}else if(type == "tp"){
		cols.push(tpCols);
	}else if(type == "zk"){
		cols.push(zkCols);
	}
}

var grid;

function initGrid(_url) {
	require([ "dgrid/Grid", "dojo/_base/declare",
			"dgrid/Keyboard", "dojo/window", "dgrid/ColumnSet",
			"dgrid/Selection" , "dgrid/extensions/Pagination","custom/store/Server"
			],
			function(Grid, declare, Keyboard, win, ColumnSet,Selection,Pagination,Server) {

		// calculate the grid height for avoid the outside scrollbar
		var vs = win.getBox();
		var gridHeight = vs.h - 100;
		var gridNode = dojo.byId("gridDiv");
		gridNode.style.height = gridHeight + "px";
	    
	    var store = new Server({
	        target: _url,
	        idProperty: "id"
	    });
		
		var CustomGrid = declare([ Grid, Keyboard, ColumnSet ,Selection,Pagination]);
		grid = new CustomGrid({
			store :store,
			columnSets : cols,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...',
			pageSizeOptions: [10, 15, 25,50]
		}, "gridDiv");
		
		grid.on("dgrid-refresh-complete", function(event) {
			if(grid.store.data.length != 0){
				busyButton.cancel();
			}
		
		});
		
		grid.startup();
	});
}

var startCols =  [ 
                  [ 
                   	 {label : "序号",	field : "rownumber",
                   		formatter : function(field) {
                   			if(field == "合计"){
                   				return "<b>合计</b>";
                   			}
                			return field;
                		}}, 
                   	 {	label : "营业日期",field : "businessDate"}      
                   ] 
                  ];

var zkCols = [ 
				  [ 
				    {
					label : "台卡号",
					field : ""
				}, {
					label : "台位",
					field : "table"
				}, {
					label : "出品单号",
					field : "billC",
					renderCell : function(object, data) {
						if(object.rownumber == "合计"){
							return "";
						}
						
						return hrefFmt(object.billC, doShow, object);
					}
				}, {
					label : "班次",
					field : "shift"
				}, {
					label : "市别",
					field : "period"
				}, {
					label : "消费金额",
					field : "foodAmt",
					formatter : function(field,renderCell) {
						if(renderCell.rownumber == "合计"){
							return "<b>" +field +"</b>";
						}
						return field;
	         		}
				}, {
					label : "折扣金额",
					field : "disAmt",
					formatter : function(field,renderCell) {
						if(renderCell.rownumber == "合计"){
							return "<b>" +field +"</b>";
						}
						return field;
	         		}
				}, {
					label : "付款金额",
					field : "payAmt",
					formatter : function(field,renderCell) {
						if(renderCell.rownumber == "合计"){
							return "<b>" +field +"</b>";
						}
						return field;
	         		}
				}, {
					label : "门店编码",
					field : "shopC"
				} , {
					label : "门店名称",
					field : "shopN"
				} , {
					label : "折扣原因",
					field : "disWhy"
				} , {
					label : "折扣人",
					field : "disMan"
				}
				] ] ;

var zsCols = [ 
			  [ 
			    {
				label : "台卡号",
				field : ""
			}, {
				label : "台位",
				field : "table"
			}, {
				label : "出品单号",
				field : "billC",
				renderCell : function(object, data) {
					if(object.rownumber == "合计"){
						return "";
					}
					
					return hrefFmt(object.billC, doShow, object);
				}
			},
			{
				label : "班次",
				field : "shift"
			}, {
				label : "市别",
				field : "period"
			}, {
				label : "赠送数量",
				field : "presentAmount",
				formatter : function(field,renderCell) {
					if(renderCell.rownumber == "合计"){
						return "<b>" +field +"</b>";
					}
					return field;
         		}
			}, {
				label : "赠送金额",
				field : "presentPrice",
				formatter : function(field,renderCell) {
					if(renderCell.rownumber == "合计"){
						return "<b>" +field +"</b>";
					}
					return field;
         		}
			} ,{
				label : "门店编码",
				field : "shopC"
			} , {
				label : "门店名称",
				field : "shopN"
			}
			] ] ;

function doShow(row) {
	var _url = appRoot + "/jsp/businessquery/foodlist.action?billC="
			+ row.billC;
	_url = getUrl(_url);
	
	addTab( row.billC, _url);
}

var mdCols = 	[ 
				  [ 
				    {
					label : "台卡号",
					field : ""
				}, {
					label : "出品单号",
					field : "billC",
					renderCell : function(object, data) {
						if(object.rownumber == "合计"){
							return "";
						}
						
						return hrefFmt(object.billC, doShow, object);
					}
				}, {
					label : "台位",
					field : "table"
				}, {
					label : "班次",
					field : "shift"
				}, {
					label : "市别",
					field : "period"
				}, {
					label : "收银员",
					field : "payMan"
				}, {
					label : "授权人",
					field : "grantMan"
				}, {
					label : "付款金额",
					field : "payAmt",
					formatter : function(field,renderCell) {
						if(renderCell.rownumber == "合计"){
							return "<b>" +field +"</b>";
						}
						return field;
             		}
				}, {
					label : "消费金额",
					field : "foodAmt",
					formatter : function(field,renderCell) {
						if(renderCell.rownumber == "合计"){
							return "<b>" +field +"</b>";
						}
						return field;
             		}
				}, {
					label : "付款人",
					field : "payMan"
				}, {
					label : "门店编码",
					field : "shopC"
				}, {
					label : "门店名称",
					field : "shopN"
				}
				] ] ;

var tpCols = 	[ 
				  [ 
				    {
					label : "台卡号",
					field : ""
				}, {
					label : "出品单号",
					field : "billC",
					renderCell : function(object, data) {
						if(object.rownumber == "合计"){
							return "";
						}
						
						return hrefFmt(object.billC, doShow, object);
					}
				}, {
					label : "台位",
					field : "table"
				}, {
					label : "班次",
					field : "shift"
				}, {
					label : "市别",
					field : "period",
				}, {
					label : "点单号",
					field : "foodBill"
				}, {
					label : "出品码",
					field : "foodC"
				}, {
					label : "出品名称",
					field : "foodN"
				}, {
					label : "单位",
					field : "unit"
				}, {
					label : "价格",
					field : "price",
					formatter : function(field,renderCell) {
						if(renderCell.rownumber == "合计"){
							return "<b>" +field +"</b>";
						}
						return field;
	         		}
				}, {
					label : "退品数量",
					field : "returnAmount",
					formatter : function(field,renderCell) {
						if(renderCell.rownumber == "合计"){
							return "<b>" +field +"</b>";
						}
						return field;
	         		}
				}, {
					label : "退品金额",
					field : "returnAmt",
					formatter : function(field,renderCell) {
						if(renderCell.rownumber == "合计"){
							return "<b>" +field +"</b>";
						}
						return field;
	         		}
				} , {
					label : "退品原因",
					field : "returnWhy"
				}, {
					label : "退品时间",
					field : "returnTime",
					formatter : function(field) {
						if(field == undefined){
							return "";
						}
            			 if(field.indexOf(".") != -1){
            				 return field.substring(0,field.indexOf("."));
            			 }
            			 
            			 return field;
            		}
				}, {
					label : "退品授权人",
					field : "backMan"
				}, {
					label : "套餐标志",
					field : "suitFlag"
				}, {
					label : "小类编码",
					field : "smallC"
				}, {
					label : "小类名称",
					field : "smallN"
				}, {
					label : "门店编码",
					field : "shopC"
				}, {
					label : "门店名称",
					field : "shopN"
				}
				] ] ;
var cols;
