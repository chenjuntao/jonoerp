require([
         "dojo/parser", 
         "dijit/InlineEditBox",
         "dijit/Editor", 
         "dijit/form/Textarea",
         "dojo/domReady!"
       ],function(parser,InlineEditBox){
		parser.parse();
		
		loadStepGrids();
		initCostGrid();
		loadData();
});

var aGridData,bGridData,cGridData = [];

function loadStepGrids(){
	var _url = appRoot + "/hq/station_observation/getData.action";
	
	require(["dojo/request/xhr","dijit/registry"], function(xhr,registry) {
		xhr.post(_url, {
			handleAs : "json",
			data:{itemId:item_id},
			sync : true
		}).then(
				function(data) {
					aGridData = data.a;
					bGridData = data.b;
					cGridData = data.c;
					initStepGridData();
				}
	)})
}

function initStepGridData(){
	require(["dojo/dom", "dojo/dom-construct", "dojo/dom-attr", "dojo/dom-style","dojo/topic","dojo/on"]
			  , function(dom, domc, domAttr, domStyle,topic,on) {
		
		var aTable = dojo.byId('aTable');
		var bTable = dojo.byId('bTable');
		var cTable = dojo.byId('cTable');
		
			if(aGridData.length != 0){
				var tr1 =  domc.create("tr", null, aTable);
				var tr2 =  domc.create("tr", null, aTable);
				var tr3 =  domc.create("tr", null, aTable);
				var tr4 =  domc.create("tr", null, aTable);
				
				for (var i = 0; i < aGridData.length; i++) {
					var itemId = aGridData[i].itemId;
					var processId = aGridData[i].processId;
					var step = aGridData[i].step;
					
					var td1 =  domc.create("td", null, tr1);
					var span1 = domc.create("span",{innerHTML: aGridData[i].stepName},td1);
					domStyle.set(span1, {
						'font-size' : '1.5em',
						'text-align': 'center',
						'display':'block'
					});
					
					var td2 =  domc.create("td", null, tr2);
					var img = domc.create("img",null,td2);
					domAttr.set(img, "src", appRoot+"/hq/station_observation/loadImage.action?itemId="+ itemId +"&processId="+ processId + "&step="+ step);
					domStyle.set(img, {
						'height' : '200px',
						'width' : '200px'
					});
					
					var td3 =  domc.create("td", null, tr3);
					var textarea  = domc.create("textarea",{innerHTML: aGridData[i].stepRemark},td3);
					domAttr.set(textarea, "readonly", "readonly");
					domStyle.set(textarea , {
						'height' : '85px',
						'width' : '200px',
						'font-size' : '1.3em'
					});
					
					var td4 =  domc.create("td", null, tr4);
					var button  = domc.create("input",null,td4);
					domAttr.set(button, "type",  "button");
					domAttr.set(button, "value",  "编辑");
					domAttr.set(button, "processId", processId);
					domAttr.set(button, "step",  step);
					
					domStyle.set(button , {
						'height' : '30px',
						'width' : '200px',
						'font-size' : '1.3em'
					});
					
					on(button, "click", function(evt){
						var processId = domAttr.get(evt.target, "processId") 
						var step =  domAttr.get(evt.target, "step");
						
						doUploadEdit(processId,step);
					});
				}
			}
			
			if(bGridData.length != 0){
				var tr1 =  domc.create("tr", null, bTable);
				var tr2 =  domc.create("tr", null, bTable);
				var tr3 =  domc.create("tr", null, bTable);
				var tr4 =  domc.create("tr", null, bTable);
				
				for (var i = 0; i < bGridData.length; i++) {
					var itemId = bGridData[i].itemId;
					var processId = bGridData[i].processId;
					var step = bGridData[i].step;
					
					var td1 =  domc.create("td", null, tr1);
					var span1 = domc.create("span",{innerHTML: bGridData[i].stepName},td1);
					domStyle.set(span1, {
						'font-size' : '1.5em',
						'text-align': 'center',
						'display':'block'
					});
					
					var td2 =  domc.create("td", null, tr2);
					var img = domc.create("img",null,td2);
					domAttr.set(img, "src",  appRoot+"/hq/station_observation/loadImage.action?itemId="+ itemId +"&processId="+ processId + "&step="+ step);
					domStyle.set(img, {
						'height' : '200px',
						'width' : '200px'
					});
					
					var td3 =  domc.create("td", null, tr3);
					var textarea  = domc.create("textarea",{innerHTML: bGridData[i].stepRemark},td3);
					domAttr.set(textarea, "readonly", "readonly");
					domStyle.set(textarea , {
						'height' : '85px',
						'width' : '200px',
						'font-size' : '1.3em'
					});
					
					var td4 =  domc.create("td", null, tr4);
					var button  = domc.create("input",null,td4);
					domAttr.set(button, "type",  "button");
					domAttr.set(button, "value",  "编辑");
					domAttr.set(button, "processId", processId);
					domAttr.set(button, "step",  step);
					
					domStyle.set(button , {
						'height' : '30px',
						'width' : '200px',
						'font-size' : '1.3em'
					});
					
					on(button, "click", function(evt){
						var processId = domAttr.get(evt.target, "processId") 
						var step =  domAttr.get(evt.target, "step");
						
						doUploadEdit(processId,step);
					});
				}
			}
			
			if(cGridData.length != 0){
				var tr1 =  domc.create("tr", null, cTable);
				var tr2 =  domc.create("tr", null, cTable);
				var tr3 =  domc.create("tr", null, cTable);
				var tr4 =  domc.create("tr", null, cTable);
				
				for (var i = 0; i < cGridData.length; i++) {
					var itemId = cGridData[i].itemId;
					var processId = cGridData[i].processId;
					var step = cGridData[i].step;
					
					var td1 =  domc.create("td", null, tr1);
					var span1 = domc.create("span",{innerHTML: cGridData[i].stepName},td1);
					domStyle.set(span1, {
						'font-size' : '1.5em',
						'text-align': 'center',
						'display':'block'
					});
					
					var td2 =  domc.create("td", null, tr2);
					var img = domc.create("img",null,td2);
					domAttr.set(img, "src",  appRoot+"/hq/station_observation/loadImage.action?itemId="+ itemId +"&processId="+ processId + "&step="+ step);
					domStyle.set(img, {
						'height' : '200px',
						'width' : '200px'
					});
					
					var td3 =  domc.create("td", null, tr3);
					var textarea  = domc.create("textarea",{innerHTML: cGridData[i].stepRemark},td3);
					domAttr.set(textarea, "readonly", "readonly");
					domStyle.set(textarea , {
						'height' : '85px',
						'width' : '200px',
						'font-size' : '1.3em'
					});
					
					var td4 =  domc.create("td", null, tr4);
					var button  = domc.create("input",null,td4);
					domAttr.set(button, "type",  "button");
					domAttr.set(button, "value",  "编辑");
					domAttr.set(button, "processId", processId);
					domAttr.set(button, "step",  step);
					
					domStyle.set(button , {
						'height' : '30px',
						'width' : '200px',
						'font-size' : '1.3em'
					});
					
					on(button, "click", function(evt){
						var processId = domAttr.get(evt.target, "processId") 
						var step =  domAttr.get(evt.target, "step");
						
						doUploadEdit(processId,step);
					});
				}
			}
		});
}


function loadData(){
	var _url = appRoot + "/hq/station_observation/doLoadData.action";
	
	require(["dojo/request/xhr","dijit/registry"], function(xhr,registry) {
		xhr.post(_url, {
			handleAs : "json",
			data:{itemId:item_id},
			sync : true
		}).then(
				function(data) {
					for(var i=0,len = data.length; i< len;i++){
						var item = data[i];
						setTemplateValue(item,registry);
					}
				}

	)})}

function setTemplateValue(item,registry){
	switch (item.partId) {
	case "leftTitle":
		registry.byId('leftTitle').set('value',item.partRemark)
		break;
		
	case "aPartTitle":
		registry.byId('aPartTitle').set('value',item.partRemark)
		break;
		
	case "aPartContent":
		registry.byId('aPartContent').set('value',item.partRemark)
		break;
		
	case "costPartContent":
		registry.byId('costPartContent').set('value',item.partRemark)
		break;
		
	case "bPartTitle":
		registry.byId('bPartTitle').set('value',item.partRemark)
		break;
		
	case "bPartContent":
		registry.byId('bPartContent').set('value',item.partRemark)
		break;
		
	case "cPartTitle":
		registry.byId('cPartTitle').set('value',item.partRemark)
		break;
		
	case "cPartContent":
		registry.byId('cPartContent').set('value',item.partRemark)
		break;
		
	case "dPartTitle":
		registry.byId('dPartTitle').set('value',item.partRemark)
		break;
		
	case "dPartContent":
		registry.byId('dPartContent').set('value',item.partRemark)
		break;
		
	default:
		break;
	}
}

function initCostGrid() {
	var grid = null;
	
	var _url = appRoot + "/restaurant/costcard/doQuery.action?foodId=" + item_id;
	
	require([ "custom/store/Server", "dojo/_base/declare", "dgrid/OnDemandGrid" ],
			function(Server,declare, OnDemandGrid) {
		var dataStore = new Server({
			target : _url
		});

		var CustomGrid = declare([ OnDemandGrid]);
		grid = new CustomGrid({
			store : dataStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

var cols = [{
	label : "编码",
	field : "itemId",
	sortable : false
}, {
	label : "名称",
	field : "itemName",
	sortable : false
},{
	label : "净料耗量",
	field : "itemCount",
	sortable : false
}];

var isEditDialog = false;

var uploadDlg = null;
function doUpload(processId) {
	isEditDialog = false;
	
	var _title = "新增步骤";
	var _url = appRoot + "/hq/station_observation/uploadView.action?itemId=" + item_id+"&processId="+processId;
	var frameId = 'ifr_upload';

	var option = {
		title : _title,
		url : _url,
		frameId : frameId,
		width : "650px",
		height : "212px"
	}
	createDialog(option, function(iDlg) {
		uploadDlg = iDlg;
	});
}

function closeUploadDlg(data) {
	if(isEditDialog){
		uploadEditDlg.hide();
	}else{
		uploadDlg.hide();
	}
	
	alert("操作步骤保存成功！");
	emptyTableNodes();
}

var uploadEditDlg = null;
function doUploadEdit(processId,step) {
	isEditDialog = true;
	
	var _title = "编辑操作步骤";
	var _url = appRoot + "/hq/station_observation/uploadEditView.action?itemId=" + item_id+"&processId="+processId+"&step="+step;
	var frameId = 'ifr_upload';
	
	var option = {
			title : _title,
			url : _url,
			frameId : frameId,
			width : "650px",
			height : "488px"
	}
	createDialog(option, function(iDlg) {
		uploadEditDlg = iDlg;
	});
}

function emptyTableNodes() {
	require([ "dojo/dom", "dojo/dom-construct"]
		, function(dom,	domc) {
			domc.empty('aTable');
			domc.empty('bTable');
			domc.empty('cTable');
			
		    aGridData = bGridData = cGridData = [];
			loadStepGrids();
	});
}

function doSave() {
	var _url = appRoot + "/hq/station_observation/doSaveData.action";
	
	require([ "dojo/request/xhr","dojox/html/entities","dijit/registry"], function(xhr,entities,registry) {
		var submitStr = JSON.stringify({
			leftTitle:dijit.byId('leftTitle').get('value'),
			aPartTitle:registry.byId('aPartTitle').get('value'),
			aPartContent:dijit.byId('aPartContent').get('value'),
			costPartContent:registry.byId('costPartContent').get('value'),
			bPartTitle:registry.byId('bPartTitle').get('value'),
			bPartContent:dijit.byId('bPartContent').get('value'),
			cPartTitle:registry.byId('cPartTitle').get('value'),
			cPartContent:dijit.byId('cPartContent').get('value'),
			dPartTitle:registry.byId('dPartTitle').get('value'),
			dPartContent:dijit.byId('dPartContent').get('value')
	});

		xhr.post(_url, {
			handleAs : "json",
			data : {jsonData:submitStr,
				itemId:item_id}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
				doClose();
			} else {
				alert("保存失败！");
			}
		}, function(err) {
		});
	});
}
function doClose(){
	closeTab(tabId);
}

