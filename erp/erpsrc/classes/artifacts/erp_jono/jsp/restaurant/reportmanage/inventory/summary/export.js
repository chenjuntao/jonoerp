var sheetName = '餐厅仓库汇总账';

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
		display : '门店',
		name : 'branch_name',
		align : 'left',
		width : 120
	}, {
		display : '仓库',
		name : 'storage_name',
		align : 'left',
		width : 150
	}, {
		name : "item_id",
		display : "商品编码",
		align : 'left',
		width : 150
	}, {
		name : "item_name",
		display : "商品名称",
		align : 'left',
		width : 150
	}, {
		display : '库存单位',
		name : 'item_dimension',
		align : 'center',
		width : 100
	}, {
		display : '期初数量',
		name : 'orgiCount',
		align : 'right',
		width : 100
	}, {
		display : '期初金额',
		name : 'orgicountmoney',
		align : 'right',
		width : 100
	}, {
		display : '入库数量合计',
		name : 'itemcountin',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '入库金额合计',
		name : 'itemcountinmoney',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '出库数量合计',
		name : 'itemcountout',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '出库金额合计',
		name : 'itemcountoutmoney',
		align : 'right',
		width : 100,
		sum : {
			count : true
		}
	}, {
		display : '结存数量',
		name : 'resultcount',
		align : 'right',
		width : 100
	}, {
		display : '结存金额',
		name : 'resultcountmoney',
		align : 'right',
		width : 100
	}, {
		name : "putinstorage",
		display : "采购入库数量",
		align : 'left',
		width : 150
	}, {
		name : "distribution",
		display : "配送入库数量",
		align : 'left',
		width : 150
	}, {
		name : "antiauditin",
		display : "配送反审核入库数量",
		align : 'left',
		width : 150
	}, {
		name : "antiauditout",
		display : "配送反审核出库数量",
		align : 'left',
		width : 150
	}, {
		name : "dreject",
		display : "配送退货数量",
		align : 'left',
		width : 150
	}, {
		name : "preject",
		display : "采购退货数量",
		align : 'left',
		width : 150
	}, {
		name : "rawloss",
		display : "出品报损数量",
		align : 'left',
		width : 150
	}, {
		name : "dishloss",
		display : "采购入库数量",
		align : 'left',
		width : 150
	}, {
		name : "allocateitemin",
		display : "调拨入库数量",
		align : 'left',
		width : 150
	}, {
		name : "allocateitemout",
		display : "调拨出库数量",
		align : 'left',
		width : 150
	}, {
		display : '盘盈数量',
		name : 'checkstoragein',
		align : 'right',
		width : 100
	}, {
		display : '盘亏数量',
		name : 'checkstorageout',
		align : 'right',
		width : 100
	}, {
		display : '销售扣仓',
		name : 'theoryDeduct',
		align : 'right',
		width : 100
	}, {
		display : '自产入库',
		name : 'semiesin',
		align : 'right',
		width : 100
	}, {
		display : '自产耗料',
		name : 'rawout',
		align : 'right',
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
