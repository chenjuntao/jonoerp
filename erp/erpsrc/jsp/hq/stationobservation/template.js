

require([
         "dojo/parser", 
         "dijit/InlineEditBox",
         "dijit/Editor", 
         "dijit/form/Textarea",
         "dojo/domReady!"
       ],function(parser,InlineEditBox){
		parser.parse();
		
		loadData();
});

function loadData(){
	var _url = appRoot + "/hq/station_observation/doLoadTemplate.action";
	
	require(["dojo/request/xhr","dijit/registry"], function(xhr,registry) {
		xhr.post(_url, {
			handleAs : "json"
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


function doSave() {
	var _url = appRoot + "/hq/station_observation/doSaveTemplate.action";
	
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
			data : {jsonData:submitStr}
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


