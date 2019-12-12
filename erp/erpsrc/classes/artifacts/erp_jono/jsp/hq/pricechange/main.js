var materialDlg = null;
function selMaterial() {
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/selmaterialRadio/mainView.action";
		
		var option = {
			title : "选择原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		materialDlg = createDialog(option);
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.loadData();
		materialDlg.show();
	}
}

/**
 * 关闭对话框时调用，所选数据传入父页面
 */
function closeMaterialDlg(data) {
	if(data.length != 0){
		var itemId  = data[0].itemId;
		var itemName  = data[0].itemName;
		var displayItemName = "[" + itemId + "]" + itemName;
		
		dojo.byId("itemId").value = itemId;
		dojo.byId("itemName").value = displayItemName;
		dojo.byId("displayItemName").innerHTML = displayItemName;
	}else{
		dojo.byId("itemId").value = "";
		dojo.byId("itemName").value = "";
		dojo.byId("displayItemName").innerHTML = "";
	}
	
	materialDlg.hide();
}

/**
 * 选择原料时需要关联门店Id
 */
function getBranchId() {
	return '00';
}

function doQuery(){
	dojo.empty("chartNode");
	dojo.empty("legend");
	
	verify();
}

dojo.ready(function() {
	resetDisplay();
});

function resetDisplay(){
	var itemName = dojo.byId("itemName").value;
	if(itemName != null ){
		 dojo.byId("displayItemName").innerHTML = itemName;
	}
}

function verify(){
	if(isEmpty(dojo.byId('itemId').value)){
		alert("请先选择一个原料！");
		return;
	}
	
	if(isEmpty(dojo.byId('startDate').value) ||isEmpty(dojo.byId('endDate').value)){
		alert("请选择生效日期！");
		return;
	}
	
	genChart();
}

function genChart(){
	var normArr = []; // 标准价  		BENCHMARK
	var joinArr = []; //加盟价  		JOIN
	var retailArr = []; //零售价 		RETAIL
	var wholeSaleArr = []; //批发价	WHOLESALE
	var purchaseArr = []; //进货价	PURCHASE

	var labelDate = [];

	//开始日期 结束如期的默认值
	var startDate = dojo.byId('startDate').value;
	var endDate=  dojo.byId('endDate').value;
	
	require([
	     	// Require the basic chart class
	     	"dojox/charting/Chart",

	     	// Require the theme of our choosing
	     	"dojox/charting/themes/Claro",

	     	// Charting plugins:

	     	// We want to plot Lines
	     	"dojox/charting/plot2d/Lines",

	     	// Load the Legend, Tooltip, and Magnify classes
	     	"dojox/charting/widget/Legend",
	     	"dojox/charting/action2d/Tooltip",
	     	"dojox/charting/action2d/Magnify",
	     	
	     	"dojo/request/xhr",
	     	"dojo/_base/array",

	     	// We want to use Markers
	     	"dojox/charting/plot2d/Markers",

	     	// We'll use default x/y axes
	     	"dojox/charting/axis2d/Default",
	     	// Wait until the DOM is ready
	     	"dojo/domReady!" 
	     	], function(Chart, theme, LinesPlot, Legend, Tooltip,Magnify,xhr,arrayUtil) {

	     	require([ "dojo/request/xhr", "dojo/dom-form"],function(xhr,domForm){
	     		xhr.post(appRoot + "/reportmanage/pricechange/doQuery.action", {
	     			handleAs : "json",
	     			data :  domForm.toObject("billForm")
	     		}).then(function(data) {
	     			
	     			var maxPrice = data.maxPrice;
	     			
	     			arrayUtil.forEach(data.date, function(item,index){
	     				if(index == 0){
	     					startDate = item.effect_time;
	     				}
	     				
	     				if(index == data.date.length - 1){
	     					endDate = item.effect_time;
	     				}
	     				
	     				labelDate.push({value:item.index, text:item.effect_time});
	     			});
	     			
	     			labelDate.push({value:labelDate.length +1, text: endDate + "以后"});
	     			labelDate.unshift({value:0, text: startDate + "以前"});
	     			
	     			var length = labelDate.length ; // 日期个数，x 轴从零开始计算
	     			
	     			console.log(length);
	     			
	     			arrayUtil.forEach(data.content, function(item){
	     				if(item.adjust_type == "BENCHMARK"){
	     					normArr.push({x:getEffectTimeIndex(normArr.length,item.effect_time),y:item.new_price,tooltip:createTipText(item)});
	     				}
	     				
	     				if(item.adjust_type == "JOIN"){
	     					joinArr.push({x:getEffectTimeIndex(joinArr.length,item.effect_time),y:item.new_price,tooltip:createTipText(item)});
	     				}
	     				
	     				if(item.adjust_type == "RETAIL"){
	     					retailArr.push({x:getEffectTimeIndex(retailArr.length,item.effect_time),y:item.new_price,tooltip:createTipText(item)});
	     				}
	     				
	     				if(item.adjust_type == "WHOLESALE"){
	     					wholeSaleArr.push({x:getEffectTimeIndex(wholeSaleArr.length,item.effect_time),y:item.new_price,tooltip:createTipText(item)});
	     				}
	     				
	     				if(item.adjust_type == "PURCHASE"){
	     					purchaseArr.push({x:getEffectTimeIndex(purchaseArr.length,item.effect_time),y:item.new_price,tooltip:createTipText(item)});
	     				}
	     			 });
	     			
	     			function getEffectTimeIndex(length,effectTime){
	     				if(isEmpty(effectTime)){
	     					if(length == 0){
	     						return 0;
	     					}else{
	     						return labelDate.length - 1;
	     					}
	     				}else{
	     					for(var i=0,length=labelDate.length; i<length; i++){
	     						var item = labelDate[i];
	     						if(effectTime == item.text){
	     							return item.value;
	     						}
	     					}
	     				}
	     			}
	     			
	     			// Create the chart within it's "holding" node
	     			var chart = new Chart("chartNode");

	     			// Set the theme
	     			chart.setTheme(theme);

	     			// Add the only/default plot
	     			chart.addPlot("default", {
	     				type : LinesPlot,
	     				markers : true
	     			});

	     			// Add axes
	     			chart.addAxis("x", {min: 0, max: length-1,labels :labelDate});
	     			chart.addAxis("y", {vertical: true, majorLabels: true, minorLabels: true,
	     				 fixLower: "major",fixUpper: "major",
	     				 min: 0, max: maxPrice});

	     			// Add the series of data
	     			chart.addSeries("标准价", normArr);
	     			chart.addSeries("加盟价", joinArr);
	     			chart.addSeries("零售价", retailArr);
	     			chart.addSeries("批发价", wholeSaleArr);
	     			chart.addSeries("进货价", purchaseArr);

	     			// Create the tooltip
	     			var tip = new Tooltip(chart, "default");

	     			// Create the magnifier
	     			var mag = new Magnify(chart, "default");

	     			// Render the chart!
	     			chart.render();

	     			// Create the legend
	     			var legend = new Legend({
	     				chart : chart
	     			}, "legend");
	     			
	     		}, function(err) {
	     			console.log(err);
	     		});
	     	});

	     	function createTipText(item){
	     		if(isEmpty(item.effect_time)){
	     			return getPriceTypeText(item.adjust_type)+": "+item.new_price;
	     		}else{
	     			return getPriceTypeText(item.adjust_type)+": " +" 生效日期：" + item.effect_time  + " 变更前价格：" + item.old_price + " 变更后价格："  + item.new_price;
	     		}
	     	}
	     	
	     	function getPriceTypeText(str){
	     		if(str == "BENCHMARK"){
	     			return "标准价";
	     		}
	     		if(str == "JOIN"){
	     			return "加盟价";
	     		}
	     		if(str == "RETAIL"){
	     			return "零售价";
	     		}
	     		if(str == "WHOLESALE"){
	     			return "批发价";
	     		}
	     		
	     		if(str == "PURCHASE"){
	     			return "进货价";
	     		}
	     	}
	     	
	     });	
}

