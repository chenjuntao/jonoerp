var sheetName = '实时库存';

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
			display : '编码',
			name : 'itemId',
			align : 'center',
			width : 100
		},{
			display : '名称',
			name : 'itemName',
			align : 'left',
			width : 200
		},{
			display : '助记码',
			name : 'queryCode',
			align : 'left',
			width : 120
		},{
			display : '类别',
			name : 'categoryId',
			align : 'center',
			width : 100
		},{
			display : '单位',
			name : 'itemDimension',
			align : 'center',
			width : 100
		},{
			display : '供应商',
			name : 'supplierName',
			align : 'left',
			width : 250
		},{
			display : '库存量',
			name : 'itemCount',
			align : 'right',
			width : 120
		}]
	};

function exportXls() {
	var _type  = dijit.byId("typeSelection").get('value');
	
	var _url = appRoot + "/restaurant/common/stock/category/export.action?branchId="
					   + getElementValue("branchId") 
					   + "&branchType="+getElementValue("branchType") ;
	
	var selCateArr = treeStore.query({
		checked : true
	});
	
	var idArr = [];
	
	require([ 
	          "dojo/_base/array" 
	         ], function(array) {
		array.forEach(selCateArr, function(cate, i) {
			//arguments[0] 当前迭代对象; arguments[1] 当前索引位;  arguments[1] 被迭代的所有对象
			// idArr 存入被选中项的id
			idArr.push(cate.id);
		});
	});
	
	var _params = {
			cateIds : idArr.join(","), // 以指定的连接符连接数组中所有的元素，并返回一个字符串
			storageId : dojo.byId('storageId').value,
			itemName : dojo.byId('itemName').value
		};
	
	_params.jsonData =  JSON.stringify(data);
	
	if(_type!=undefined){
		_params.type = _type;
	}
	
	post_redirect(_url, _params);
}

