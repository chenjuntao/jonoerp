var sheetName = '要货单';

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
		display : '操作时间',
		name : 'form_time_actual',
		align : 'center',
		width : 150
	}, {
		display : '单据编号',
		name : 'form_id',
		align : 'center',
		width : 150
	}, {
		display : '单据状态',
		name : 'status',
		align : 'left',
		width : 100
	}, {
		display : '制单日期',
		name : 'form_time',
		align : 'left',
		width : 150
	}, {
		display : '审核日期',
		name : 'audit_time',
		align : 'left',
		width : 150
	}, {
		display : '订货部门',
		name : 'buyer_name',
		align : 'left',
		width : 150
	}, {
		display : '供货部门',
		name : 'provider',
		align : 'center',
		width : 150
	}, {
		display : '到货日期',
		name : 'receive_time',
		align : 'center',
		width : 150
	}, {
		display : '制单人员',
		name : 'form_maker',
		align : 'center',
		width : 100
	}, {
		display : '审核人员',
		name : 'auditor',
		align : 'center',
		width : 100
	}, {
		display : '制单人员',
		name : 'form_maker',
		align : 'center',
		width : 100
	}, {
		display : '备注信息',
		name : 'form_note',
		align : 'left',
		width : 120
	}, {
		display : '主要要货',
		name : 'max_pay_item',
		align : 'left',
		width : 150
	}, {
		display : '要货总额',
		name : 'all_pay_amt',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '模板编码',
		name : 'template_id',
		align : 'left',
		width : 100
	}, {
		display : '模板名称',
		name : 'template_name',
		align : 'left',
		width : 100
	}, {
		display : '采购汇总',
		name : 'purchase_status',
		align : 'left',
		width : 100
	}, {
		display : '配送汇总',
		name : 'shipping_status',
		align : 'left',
		width : 100
	} ]
};

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/reportmanage/common/export/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData = JSON.stringify(dataCopy);

	console.dir(_params.jsonData);
	
	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
