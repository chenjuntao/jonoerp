var sheetName = '调拨明细报表';

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
		display : '制单日期',
		name : 'form_time',
		align : 'center',
		width : 100
	}, {
		display : '单据编号',
		name : 'form_id',
		align : 'center',
		width : 150
	}, {
		display : '审核日期',
		name : 'audit_time',
		align : 'center',
		width : 100
	}, {
		display : '调入部门',
		name : 'in_branch',
		align : 'center',
		width : 130
	}, {
		display : '调入仓库',
		name : 'in_storage',
		align : 'center',
		width : 180
	}, {
		display : '调出部门',
		name : 'out_branch',
		align : 'center',
		width : 130
	}, {
		display : '调出仓库',
		name : 'out_storage',
		align : 'center',
		width : 180
	}, {
		display : '商品编码',
		name : 'item_id',
		align : 'center',
		width : 100
	}, {
		display : '商品名称',
		name : 'item_name',
		align : 'center',
		width : 150
	}, {
		display : '类别编码',
		name : 'item_category',
		align : 'center',
		width : 100
	}, {
		display : '类别名称',
		name : 'category_name',
		align : 'center',
		width : 100
	}, {
		display : '单位',
		name : 'item_dimension',
		align : 'center',
		width : 100
	}, {
		display : '规格',
		name : 'item_specification',
		align : 'center',
		width : 100
	}, {
		display : '申请调拨数量',
		name : 'apply_count',
		align : 'center',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '实际调拨数量',
		name : 'actual_count',
		align : 'center',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '调拨差异',
		name : 'different_count',
		align : 'center',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '标准单价',
		name : 'unit_price',
		align : 'center',
		width : 100
	}, {
		display : '金额',
		name : 'pay_amt',
		align : 'center',
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
