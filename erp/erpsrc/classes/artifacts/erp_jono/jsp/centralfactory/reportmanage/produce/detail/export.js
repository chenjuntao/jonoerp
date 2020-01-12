var sheetName = '中央工厂工单领料单明细报表';

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
		display : '单据编号',
		name : 'form_id',
		align : 'center',
		width : 180
	}, {
		display : '制单日期',
		name : 'form_time',
		align : 'center',
		width : 100
	}, {
		display : '仓库编码',
		name : 'storage_id',
		align : 'center',
		width : 100
	}, {
		display : '仓库名称',
		name : 'storage',
		align : 'left',
		width : 150
	}, {
		display : '领料车间',
		name : 'workshop',
		align : 'left',
		width : 150
	}, {
		display : '制单人员',
		name : 'form_maker',
		align : 'center',
		width : 100
	}, {
		display : '物料编码',
		name : 'item_id',
		align : 'center',
		width : 100
	}, {
		display : '物料名称',
		name : 'item_name',
		align : 'left',
		width : 180
	}, {
		display : '领料数',
		name : 'receive_count',
		align : 'right',
		width : 100
	}, {
		display : '规格',
		name : 'item_specification',
		align : 'left',
		width : 80
	}, {
		display : '单位',
		name : 'item_dimension',
		align : 'center',
		width : 50
	}, {
		display : '备注说明',
		name : 'form_note',
		align : 'center',
		width : 150
	} ]
};

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/reportmanage/common/export/export.action";
	_url = getUrl(_url);
	
	var _params = getQuery();
	_params.jsonData = JSON.stringify(data);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
