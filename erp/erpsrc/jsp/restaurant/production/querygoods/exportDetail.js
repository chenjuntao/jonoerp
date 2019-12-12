var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		display : "半成品编码",
		name : "itemId",
		align : 'center',
		width : 80
	}, {
		display : "半成品名称",
		name : "itemName",
		align : 'left',
		width : 120
	}, {
		display : "单位",
		name : "itemDimension",
		align : 'center',
		width : 80
	}, {
		display : "规格",
		name : "itemSpecification",
		align : 'left',
		width : 120
	}, {
		display : "类别",
		name : "itemCategory",
		align : 'left',
		width : 80
	}, {
		display : "计划耗料数量",
		name : "itemCountPlan",
		align : 'right',
		width : 80
	}, {
		display : "入库/耗料数量",
		name : "itemCountActual",
		align : 'right',
		width : 80
	} ]
};

function fillData(content) {
	var sheetName = '半成品入库单-' + formId;
	data.content = content;
	data.sheetName = sheetName, data.title = {
		text : sheetName
	};

	exportXls();
}
var int;

var token;
function exportXls() {
	require([ "dojo/domReady!" ], function() {

		var _type = dijit.byId("typeSelection").get('value');

		var _url = appRoot + "/common/function/export.action";
		_url = getUrl(_url);

		var _params = {};
		_params.jsonData = JSON.stringify(data);

		if (_type != undefined) {
			_params.type = _type;
		}

		post_redirect(_url, _params);
	});
}
