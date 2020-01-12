var sheetName = '调拨单';

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
		display : '确认日期',
		name : 'confirm_time',
		align : 'center',
		width : 100
	}, {
		display : '调入部门',
		name : 'in_branch',
		align : 'left',
		width : 150
	}, {
		display : '调入仓库',
		name : 'in_storage',
		align : 'left',
		width : 150
	}, {
		display : '调出部门',
		name : 'out_branch',
		align : 'left',
		width : 150
	}, {
		display : '调出仓库',
		name : 'out_storage',
		align : 'left',
		width : 150
	}, {
		display : '总金额',
		name : 'all_pay_amt',
		align : 'left',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '主要调拨品',
		name : 'max_pay_item',
		align : 'left',
		width : 200
	}, {
		display : '单据状态',
		name : 'status',
		align : 'center',
		width : 100
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
