dojo.ready(function() {
	resetDisplay();
	
	init();
});

function resetDisplay(){
	var itemName = dojo.byId("itemName").value;
	if(itemName != null ){
		 dojo.byId("displayItemName").innerHTML = itemName;
	}
}

function init() {
	require([ 
	          "dojo/dom", 
	          "dojo/request/xhr", 
	          "dojo/_base/array",
	          "dojo/dom-form",
	          "dojo/parser",
	          "dojo/store/Memory",
	          "dijit/form/FilteringSelect",
	          "dijit/form/Button",
	          "dijit/Dialog"], function(dom, xhr, array,
			domForm,parser,Memory,FilteringSelect) {
		  
		parser.parse();
		
		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		xhr.post(appRoot + "/common/listBs.action", {
			handleAs : "json",
			data : {
				branchType1 : "RESTAURANT"
			}
		}).then(function(data) {
			    new FilteringSelect({
			        id: "branchCondition",
			        value:"code",
			        labelAttr : 'name',
			        displayValueAttr:"name",
			        searchAttr: "name",
			        required : false,
					autoComplete : false,
					queryExpr: "*${0}*",
			        store: new Memory({ idProperty: "code", data: data.branch }),
			        style: "width: 150px;"
			    }, "branchCondition").startup();
		}, function(err) {
					console.log(err);
				});

	});
}

function doQuery(){
	dojo.empty("chartNode");
	dojo.empty("legend");
	
	var startDate = dojo.byId('startDate').value;
	var endDate = dojo.byId('endDate').value;
	var itemId = dojo.byId('itemId').value;
	
	if(isEmpty(startDate) || isEmpty(endDate)){
		alert("请选择营业日期！");
		return;
	}
	
	if(isEmpty(itemId)){
		alert("请选择出品！");
		return;
	}
	
	reload();
}

var productDlg = null;
function selMaterial() {
	var frameId = 'ifr_selProduct';
	if (productDlg == null) {
		var _url = appRoot + "/reportmanage/hq/tenthousand/selproduct/mainView.action?branchType=RESTAURANT";
		
		var option = {
			title : "选择出品",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		productDlg = createDialog(option);
	} else {
		productDlg.show();
	}
}

function closeProductDlg(data) {
	productDlg.hide();
	
	if(data.length == 0){
		// do nothing
	}else{
		var item = data[0];
		
		dojo.byId("itemId").value = item.itemId  ; 
		dojo.byId("itemName").value = '[' + item.itemId + ']' +item.itemName  ; 
		dojo.byId("displayItemName").innerHTML ='[' + item.itemId + ']' +item.itemName   ; 
	}
}

function createTipText(item) {
	return '[' + item.item_id + ']'+ item.item_name + " 万元用量：" + item.cost;
}
	
function reload(){
	require([
             // Require the basic chart class
            "dojox/charting/Chart",

            // Require the theme of our choosing
            "dojox/charting/themes/MiamiNice",
            
            // Charting plugins: 

            "dojox/charting/action2d/Tooltip",
            "dojox/charting/action2d/Highlight",
            "dojo/request/xhr",
            "dojo/_base/array",
            
            //  We want to plot Columns 
            "dojox/charting/plot2d/Columns",
            
            //  We want to use Markers
            "dojox/charting/plot2d/Markers",

            //  We'll use default x/y axes
            "dojox/charting/axis2d/Default",

            // Wait until the DOM is ready
            "dojo/domReady!"
        ], function(Chart, theme,Tooltip,Highlight,xhr,arrayUtil) {
			xhr.post(appRoot + "/reportmanage/hq/tenthousand/doQuery.action", {
				handleAs : "json",
				data : getQuery()
			}).then(function(data) {
				var chartData = [] ;
				var labelArr = [];
				
				arrayUtil.forEach(data, function(item,index){
					chartData.push({x:index , y:item.cost,tooltip:createTipText(item)});
					labelArr.push({value:index, text:item.item_name});
				});
				
	            // Create the chart within it's "holding" node
	            var chart = new Chart("chartNode");

	            // Set the theme
	            chart.setTheme(theme);

	            // Add the only/default plot 
	            chart.addPlot("default", {
	                type: "Columns",
	                markers: true,
	                gap: 5
	            });
	            
	         // Create the tooltip
				var tip = new Tooltip(chart, "default");
				var highlight = new Highlight(chart, "default");
				
	            // Add axes
	            chart.addAxis("x", {min: 0, max: labelArr.length-1,labels :labelArr});
	            chart.addAxis("y", { vertical: true, fixLower: "major", fixUpper: "major" });

	            // Add the series of data
	            chart.addSeries("Monthly Sales",chartData);

	            // Render the chart!
	            chart.render();
			}, function(err) {
				console.log(err);
			});
            
        });
	
}

