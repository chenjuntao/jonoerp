var sheetName = '中央工厂生产分析报表';

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
		display : '完工日期',
		name : 'complete_time',
		align : 'center',
		width : 100
	}, {
		display : '原料编码',
		name : 'item_id',
		align : 'center',
		width : 80
	}, {
		display : '原料名称',
		name : 'item_name',
		align : 'left',
		width : 150
	}, {
		display : '原料单位',
		name : 'item_dimension',
		align : 'center',
		width : 80
	}, {
		display : '理论领料',
		name : 'item_count',
		align : 'right',
		width : 80
	}, {
		display : '实际领料',
		name : 'actual',
		align : 'right',
		width : 80
	}, {
		display : '产品编码',
		name : 'item_id2',
		align : 'center',
		width : 80
	}, {
		display : '产品名称',
		name : 'item_name2',
		align : 'left',
		width : 180
	}, {
		display : '单位',
		name : 'item_dimension2',
		align : 'center',
		width : 80
	}, {
		display : '计划生产量',
		name : 'item_count2',
		align : 'right',
		width : 80
	}, {
		display : '实际生产量',
		name : 'inputed_count',
		align : 'right',
		width : 80
	}, {
		display : '差异',
		name : 'diff',
		align : 'right',
		width : 80
	} , {
		display : '单据编号',
		name : 'form_id',
		align : 'center',
		width : 180
	}]
};

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');
	var _url = appRoot + "/reportmanage/common/export/export.action";
	
	var _params = getQuery();
	_params.jsonData = JSON.stringify(data);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
