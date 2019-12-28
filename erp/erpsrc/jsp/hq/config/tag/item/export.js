var sheetName = '物料标签';

var data =  {
		sheetName : sheetName,
		title : {
			text : sheetName
		},
		columns : [ {
			display : '序号',
			name : 'rownumber',
			align : 'center',
			width : 80
		},{
			display : '商品编码',
			name : 'item_id',
			align : 'center',
			width : 100
		},{
			display : '商品名称',
			name : 'item_name',
			align : 'left',
			width : 200
		},{
			display : '类别名称',
			name : 'category_name',
			align : 'left',
			width : 120
		},{
			display : '标签',
			name : 'tags',
			align : 'left',
			width : 300
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/hq/config/tag/item/export.action";
	
	var selCateArr = treeStore.query({
		checked : true
	});
	
	var idArr = [];
	
	require([ 
	          "dojo/_base/array" 
	         ], function(array) {
		array.forEach(selCateArr, function(cate, i) {
			idArr.push(cate.id);
		});
	});
	
	var _params = {
			cateIds : idArr.join(","), 
			tagCondition : dijit.byId("tagCondition").get('value'),
			itemCondition : dojo.byId('itemCondition').value,
			itemCategoryType :!itemCategoryType?"RAW,SEMIS":itemCategoryType
		};
	
	_params.jsonData =  JSON.stringify(data);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

