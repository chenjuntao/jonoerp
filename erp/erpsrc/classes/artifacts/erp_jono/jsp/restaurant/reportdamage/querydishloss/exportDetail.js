var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	},{
		display : "出品编码",
		name : "item_id",
		align : 'center',
		width : 100
	}, {
		display : "出品名称",
		name : "item_name",
		align : 'left',
		width : 200
	}, {
		display : "例牌",
		name : "item_dimension",
		align : 'center',
		width : 60
	}, {
		display : "出品报损数量",
		name : "item_count",
		align : 'right',
		width : 120
	},
//	{
//		display : "物料编码",
//		name : "item_id",
//		align : 'center',
//		width : 100
//	}, {
//		display : "物料名称",
//		name : "item_name",
//		align : 'left',
//		width : 200
//	}, {
//		display : "单位",
//		name : "item_dimension",
//		align : 'center',
//		width : 60
//	}, {
//		display : "原料报损数量",
//		name : "item_count",
//		align : 'right',
//		width : 120
//	}, 
	{
		display : "标准单价",
		name : "unit_price",
		align : 'right',
		width : 100
	}, {
		display : "报损金额",
		name : "pay_amt",
		align : 'right',
		width : 100
	}, {
		display : "报损原因",
		name : "reason",
		align : 'right',
		width : 150
	}]
};

//function fillData(arr1,arr2) {
function fillData(arr) {
	var sheetName = '出品报损单-' + formId;
	
	var tempArr =  arr;//arr1.concat(arr2);
	for(var i=0;i<tempArr.length;i++){
		var item = tempArr[i];
		item.rownumber= i+1;
	}
	
	data.content = tempArr;
	data.sheetName = sheetName, data.title = {
		text : sheetName
	};

	exportXls();
}

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/common/function/export.action";
	_url = getUrl(_url);
	
	var _params = {};
	_params.jsonData = JSON.stringify(data);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
