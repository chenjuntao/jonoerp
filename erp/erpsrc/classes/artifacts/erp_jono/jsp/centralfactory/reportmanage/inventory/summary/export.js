var sheetName = '中央工厂仓库汇总账';

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
		align : 'center',
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
		display : '原料报损(出)',
		name : 'rawloss',
		align : 'right',
		width : 100
	}, {
		display : '出品报损(出)',
		name : 'dishloss',
		align : 'right',
		width : 100
	}, {
		display : '盘亏(出)',
		name : 'checkstorageout',
		align : 'right',
		width : 100
	}, {
		display : '盘盈(入)',
		name : 'checkstoragein',
		align : 'right',
		width : 100
	}, {
		display : '产品出库(出)',
		name : 'publicstorageout',
		align : 'right',
		width : 100
	}, {
		display : '产品入库(入)',
		name : 'publicstoragein',
		align : 'right',
		width : 100
	}, {
		display : '采购退货(出)',
		name : 'preject',
		align : 'right',
		width : 100
	}, {
		display : '采购入库(入)',
		name : 'putinstorage',
		align : 'right',
		width : 100
	}, {
		display : '调拨出库(出)',
		name : 'allocateitemout',
		align : 'right',
		width : 100
	}, {
		display : '调拨入库(入)',
		name : 'allocateitemin',
		align : 'right',
		width : 100
	}, {
		display : '生产领料(出)',
		name : 'scll',
		align : 'right',
		width : 100
	}, {
		display : '生产超领(出)',
		name : 'sccl',
		align : 'right',
		width : 100
	}, {
		display : '生产退料(入)',
		name : 'sctl',
		align : 'right',
		width : 100
	}, {
		display : '非工单领料(出)',
		name : 'fgll',
		align : 'right',
		width : 100
	}]
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
