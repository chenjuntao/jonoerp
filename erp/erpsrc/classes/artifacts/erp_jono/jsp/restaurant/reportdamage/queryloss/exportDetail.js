var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	},{
		display : "原料编码",
		name : "item_id",
		align : 'center',
		width : 80
	}, {
		display : "原料名称",
		name : "item_name",
		align : 'left',
		width : 120
	}, {
		display : "单位",
		name : "item_dimension",
		align : 'center',
		width : 80
	}, {
		display : "规格",
		name : "item_specification",
		align : 'left',
		width : 120
	}, {
		display : "类别",
		name : "item_category",
		align : 'left',
		width : 80
	}, {
		display : "报损数量",
		name : "item_count",
		align : 'right',
		width : 80
	}, {
		display : "标准单价",
		name : "unit_price",
		align : 'right',
		width : 80
	}, {
		display : "标准金额",
		name : "pay_amt",
		align : 'right',
		width : 80
	}, {
		display : "库存数量",
		name : "storage_count",
		align : 'right',
		width : 100
	}
	, {
		display : "报损原因",
		name : "reason",
		align : 'center',
		width : 120
	}]
};

function fillData(content) {
	var sheetName = '原料报损单-' + formId;
	data.content = content;
	data.sheetName = sheetName, data.title = {
		text : sheetName
	};

	exportXls();
}
var int;

var token;
function exportXls() {
	require([ "dojo/domReady!" ],
			function() {
	
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
