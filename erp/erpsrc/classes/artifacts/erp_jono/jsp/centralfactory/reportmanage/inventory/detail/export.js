var sheetName = '中央工厂仓库明细账';

function updateCopy(object) {
	var columns = object.columns;
	dataCopy.columns = [];

	if(columns.length == 0){
		dataCopy.columns = dataCopy.columns.concat(data.columns);
	}else{
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
		display : '门店',
		name : 'branch_name',
		align : 'center',
		width : 120
	}, {
		display : '仓库',
		name : 'storage_name',
		align : 'left',
		width : 150
	}, {
		display : '原料编码',
		name : 'item_id',
		align : 'center',
		width : 100
	}, {
		display : '原料名称',
		name : 'item_name',
		align : 'left',
		width : 180
	}, {
		display : '库存单位',
		name : 'item_dimension',
		align : 'center',
		width : 180
	}, {
		display : '操作时间',
		name : 'operation_time',
		align : 'center',
		width : 160
	}, {
		display : '单据日期',
		name : 'business_date',
		align : 'center',
		width : 160
	}, {
		display : '单据号码',
		name : 'form_id',
		align : 'left',
		width : 160
	}, {
		display : '业务类型',
		name : 'reason_text',
		align : 'left',
		width : 130
	}, {
		display : '期初数量',
		name : 'orgi_count',
		align : 'right',
		width : 100
	}, {
		display : '期初金额',
		name : 'orgiCountMoney',
		align : 'right',
		width : 100
	}, {
		display : '入库数量',
		name : 'item_count_in',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '入库金额',
		name : 'itemCountInMoney',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '出库数量',
		name : 'item_count_out',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '出库金额',
		name : 'itemCountOutMoney',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '结存数量',
		name : 'result_count',
		align : 'right',
		width : 100
	}, {
		display : '结存单价',
		name : 'item_unit_price',
		align : 'right',
		width : 100
	}, {
		display : '结存金额',
		name : 'resultCountMoney',
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
