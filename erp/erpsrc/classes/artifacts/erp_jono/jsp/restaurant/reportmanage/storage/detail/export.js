var sheetName = '餐厅盘点明细报表';

function updateCopy(object) {
	var columns = object.columns;
	dataCopy.columns = [];

	if (columns.length == 0) {
		dataCopy.columns = dataCopy.columns.concat(data.columns);
	} else {
		for (var i = 0, lengthI = data.columns.length; i < lengthI; i++) {
			var col1 = data.columns[i].name;

			for (var j = 0, lengthJ = columns.length; j < lengthJ; j++) {
				var col2 = columns[j];

				if (col1 == col2) {
					dataCopy.columns.push(data.columns[i]);
					break;
				}
			}
		}
	}
}

var dataCopy = {
	sheetName : sheetName,
	title : {
		text : sheetName
	},
	columns : []
};

var data = {
	sheetName : sheetName,
	title : {
		text : sheetName
	},
	columns : [ {
		display : '序号',
		name : 'rownumber',
		align : 'center',
		width : 50,
		sum : {
			count : false,
			text : '合计'
		}
	}, {
		display : '盘点日期',
		name : 'form_time',
		align : 'center',
		width : 100
	}, {
		display : '单据编号',
		name : 'form_id',
		align : 'center',
		width : 150
	}, {
		display : '盘点部门',
		name : 'check_branch',
		align : 'left',
		width : 120
	}, {
		display : '盘点仓库',
		name : 'check_storage',
		align : 'left',
		width : 280
	}, {
		display : '商品编码',
		name : 'item_id',
		align : 'center',
		width : 100
	}, {
		display : '商品名称',
		name : 'item_name',
		align : 'left',
		width : 150
	}, {
		display : '类别编码',
		name : 'item_category',
		align : 'center',
		width : 100
	}, {
		display : '类别名称',
		name : 'category_name',
		align : 'left',
		width : 150
	}, {
		display : '单位',
		name : 'item_dimension',
		align : 'center',
		width : 100
	}, {
		display : '规格',
		name : 'item_specification',
		align : 'left',
		width : 100
	}, {
		display : '盘点数量',
		name : 'check_count',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '系统库存',
		name : 'theory_count',
		align : 'right',
		width : 100
	}, {
		display : '盘盈',
		name : 'diffCount',
		align : 'right',
		width : 100,
	}, {
		display : '标准单价',
		name : 'item_unit_price',
		align : 'right',
		width : 100
	}, {
		display : '标准金额',
		name : 'pay_amt',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	} ]
};

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/reportmanage/common/export/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData = JSON.stringify(dataCopy);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
