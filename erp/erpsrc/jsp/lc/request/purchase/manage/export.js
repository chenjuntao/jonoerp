var data = {
	columns : [ {
		name : 'rownumber',
		align : 'center',
		width : 50,
		display : "序号"
	}, {
		display : "原料编码",
		align : 'center',
		width : 100,
		name : "itemId"
	}, {
		display : "原料名称",
		name : "itemName",
		align : 'center',
		width : 200,
	}, {
		display : "类别",
		name : "itemCategory",
		align : 'center',
		width : 150,
	}, {
		display : "单位",
		name : "itemDimension",
		align : 'center',
		width : 100,
	}, {
		display : "规格",
		name : "itemSpecification",
		align : 'center',
		width : 100,
	}, {
		name : 'receiver',
		display : "收货部门",
		align : 'center',
		width : 200,
	}, {
		name : 'receiveAddress',
		display : "收货地址",
		align : 'center',
		width : 250,
	}, {
		display : "订货量",
		name : "itemCount",
		align : 'right',
		width : 150,
	}, {
		display : "标准价",
		name : "itemUnitPrice",
		align : 'right',
		width : 100,
	}, {
		display : "标准金额",
		name : "payAmt",
		align : 'right',
		width : 100,
	}, {
		display : "进货价",
		name : "receivePrice",
		align : 'right',
		width : 100,
	}, {
		display : "进货金额",
		name : "receiveAmt",
		align : 'right',
		width : 100,
	}, {
		display : "原料有效期",
		name : "expiredTime",
		align : 'center',
		width : 100,
	} ]
};

var bigFormId;

function fillData(content) {
	urlLinks = [];
	var _type = dijit.byId("typeSelection").get('value');

	if ("pdf" == _type) {
		if (bigFormId.indexOf('ZP') != -1) {
			exportPdf();
			return;
		} else {
			alert("暂时只支持直配订单以PDF格式导出！");
			return;
		}
	}

	var sheetName = '采购单-' + bigFormId;
	data.content = content;
	data.bigFormId = bigFormId;
	data.sheetName = sheetName, data.title = {
		text : sheetName
	};

	exportXls();
}

function exportPdf() {
	var _url = appRoot + "/lc/request/purchase/manage/queryLittleFormIds.action?formId=" + bigFormId;

	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/query", "dojo/_base/array" ], function(xhr, Memory, query,
			array) {
		xhr.post(_url, {
			handleAs : "json",
			sync : true
		}).then(
				function(data) {
					array.forEach(data, function(item, index) {
						urlLinks[index] = appRoot + "/common/function/exportPdf.action?type=pdf&bigFormId=" + bigFormId
								+ "&littleFormId=" + item;
					});

					urls_length = urlLinks.length;
					downloadStepByStep();
				}, function(err) {
				});
	});
}

function isFinished() {
	var token = dojo.byId('downloadTokenValue').value;
	var _url = appRoot + "/common/function/isFinish.action";

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (token == data.msg) {
				clearInterval(int);

				if (index < urls_length) {
					downloadStepByStep();
				} else {
					index = 0;
				}
			}
		}, function(err) {
		});
	});
}

var int;
var urlLinks = [];
var index = 0;
var urls_length;
var link;

function initLink() {
	link = document.createElement('a');
	link.setAttribute('download', null);
	link.style.display = 'none';
	document.body.appendChild(link);
}

function removeLink() {
	document.body.removeChild(link);
}

function downloadStepByStep() {
	initLink();
	var token = new Date().getTime();
	dojo.byId("downloadTokenValue").value = token;

	link.setAttribute('href', urlLinks[index] + "&downloadTokenValue=" + token);
	link.click();
	removeLink();

	index++;
	int = setInterval("isFinished()", 1000);
}

function exportXls() {
	var _type = dijit.byId("typeSelection").get('value');

	var _url = appRoot + "/common/function/export.action";
	_url = getUrl(_url);

	var _params = {};
	_params.jsonData = JSON.stringify(data);

	if (_type != undefined) {
		_params.type = _type;
	}

	post_redirect(_url, _params);
}
