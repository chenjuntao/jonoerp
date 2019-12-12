require([ "dojo/parser", "dijit/InlineEditBox", "dijit/Editor", "dijit/form/Textarea", "dojo/domReady!" ], function(
		parser, InlineEditBox) {
	parser.parse();

	loadStepGrids();
	initCostGrid();
	loadData();
});

var aGridData, bGridData, cGridData = [];

function loadStepGrids() {
	var _url = appRoot + "/hq/station_observation/getData.action";
	
	require([ "dojo/request/xhr", "dijit/registry" ], function(xhr, registry) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				itemId : item_id
			},
			sync : true
		}).then(function(data) {
			aGridData = data.a;
			bGridData = data.b;
			cGridData = data.c;
			initStepGridData();
		})
	})
}

function initStepGridData() {

	require([ "dojo/dom", "dojo/dom-construct", "dojo/_base/window", "dojo/dom-attr", "dojo/dom-style" ], function(dom,
			domc, win, domAttr, domStyle) {
		var aTable = dojo.byId('aTable');
		var bTable = dojo.byId('bTable');
		var cTable = dojo.byId('cTable');

		if (aGridData.length != 0) {
			var tr1 = domc.create("tr", null, aTable);
			var tr2 = domc.create("tr", null, aTable);
			var tr3 = domc.create("tr", null, aTable);

			for (var i = 0; i < aGridData.length; i++) {
				var itemId = aGridData[i].itemId;
				var processId = aGridData[i].processId;
				var step = aGridData[i].step;

				var td1 = domc.create("td", null, tr1);
				var span1 = domc.create("span", {
					innerHTML : aGridData[i].stepName
				}, td1);
				domStyle.set(span1, {
					'font-size' : '1.5em',
					'text-align' : 'center',
					'display' : 'block'
				});

				var td2 = domc.create("td", null, tr2);
				var img = domc.create("img", null, td2);
				domAttr.set(img, "src", appRoot + "/hq/station_observation/loadImage.action?itemId=" + itemId
						+ "&processId=" + processId + "&step=" + step);
				domStyle.set(img, {
					'height' : '200px',
					'width' : '200px'
				});

				var td3 = domc.create("td", null, tr3);
				var textarea = domc.create("textarea", {
					innerHTML : aGridData[i].stepRemark
				}, td3);
				domAttr.set(textarea, "readonly", "readonly");
				domStyle.set(textarea, {
					'height' : '85px',
					'width' : '200px',
					'font-size' : '1.3em'
				});
			}
		}

		if (bGridData.length != 0) {
			var tr1 = domc.create("tr", null, bTable);
			var tr2 = domc.create("tr", null, bTable);
			var tr3 = domc.create("tr", null, bTable);

			for (var i = 0; i < bGridData.length; i++) {
				var itemId = bGridData[i].itemId;
				var processId = bGridData[i].processId;
				var step = bGridData[i].step;

				var td1 = domc.create("td", null, tr1);
				var span1 = domc.create("span", {
					innerHTML : bGridData[i].stepName
				}, td1);
				domStyle.set(span1, {
					'font-size' : '1.5em',
					'text-align' : 'center',
					'display' : 'block'
				});

				var td2 = domc.create("td", null, tr2);
				var img = domc.create("img", null, td2);
				domAttr.set(img, "src", appRoot + "/hq/station_observation/loadImage.action?itemId=" + itemId
						+ "&processId=" + processId + "&step=" + step);
				domStyle.set(img, {
					'height' : '200px',
					'width' : '200px'
				});

				var td3 = domc.create("td", null, tr3);
				var textarea = domc.create("textarea", {
					innerHTML : bGridData[i].stepRemark
				}, td3);
				domAttr.set(textarea, "readonly", "readonly");
				domStyle.set(textarea, {
					'height' : '85px',
					'width' : '200px',
					'font-size' : '1.3em'
				});
			}
		}

		if (cGridData.length != 0) {
			var tr1 = domc.create("tr", null, cTable);
			var tr2 = domc.create("tr", null, cTable);
			var tr3 = domc.create("tr", null, cTable);

			for (var i = 0; i < cGridData.length; i++) {
				var itemId = cGridData[i].itemId;
				var processId = cGridData[i].processId;
				var step = cGridData[i].step;

				var td1 = domc.create("td", null, tr1);
				var span1 = domc.create("span", {
					innerHTML : cGridData[i].stepName
				}, td1);
				domStyle.set(span1, {
					'font-size' : '1.5em',
					'text-align' : 'center',
					'display' : 'block'
				});

				var td2 = domc.create("td", null, tr2);
				var img = domc.create("img", null, td2);
				domAttr.set(img, "src", appRoot + "/hq/station_observation/loadImage.action?itemId=" + itemId
						+ "&processId=" + processId + "&step=" + step);
				domStyle.set(img, {
					'height' : '200px',
					'width' : '200px'
				});

				var td3 = domc.create("td", null, tr3);
				var textarea = domc.create("textarea", {
					innerHTML : cGridData[i].stepRemark
				}, td3);
				domAttr.set(textarea, "readonly", "readonly");
				domStyle.set(textarea, {
					'height' : '85px',
					'width' : '200px',
					'font-size' : '1.3em'
				});
			}
		}
	});
}

function loadData() {
	var _url = appRoot + "/hq/station_observation/doLoadData.action";
	
	require([ "dojo/request/xhr", "dijit/registry" ], function(xhr, registry) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				itemId : item_id
			},
			sync : true
		}).then(function(data) {
			for (var i = 0, len = data.length; i < len; i++) {
				var item = data[i];
				setTemplateValue(item, registry);
			}
		}

		)
	})
}

function setTemplateValue(item, registry) {
	switch (item.partId) {
	case "leftTitle":
		registry.byId('leftTitle').set('value', item.partRemark)
		break;

	case "aPartTitle":
		registry.byId('aPartTitle').set('value', item.partRemark)
		break;

	case "aPartContent":
		registry.byId('aPartContent').set('value', item.partRemark)
		break;

	case "costPartContent":
		registry.byId('costPartContent').set('value', item.partRemark)
		break;

	case "bPartTitle":
		registry.byId('bPartTitle').set('value', item.partRemark)
		break;

	case "bPartContent":
		registry.byId('bPartContent').set('value', item.partRemark)
		break;

	case "cPartTitle":
		registry.byId('cPartTitle').set('value', item.partRemark)
		break;

	case "cPartContent":
		registry.byId('cPartContent').set('value', item.partRemark)
		break;

	case "dPartTitle":
		registry.byId('dPartTitle').set('value', item.partRemark)
		break;

	case "dPartContent":
		registry.byId('dPartContent').set('value', item.partRemark)
		break;

	default:
		break;
	}
}

function initCostGrid() {
	var grid = null;

	var _url = appRoot + "/restaurant/costcard/doQuery.action?foodId=" + item_id;
	_url = getUrl(_url);
	
	require([ "custom/store/Server", "dojo/_base/declare", "dgrid/OnDemandGrid" ], function(Server, declare,
			OnDemandGrid) {
		var dataStore = new Server({
			target : _url
		});

		var CustomGrid = declare([ OnDemandGrid ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

var cols = [ {
	label : "编码",
	field : "itemId",
	sortable : false
}, {
	label : "名称",
	field : "itemName",
	sortable : false
}, {
	label : "净料耗量",
	field : "itemCount",
	sortable : false
} ];

function exportXLS() {
	var _url = appRoot + "/hq/station_observation/doExport.action?itemId=" + item_id;
	_url = getUrl(_url);
	
	parent.addTab("导出观察表", _url);
}