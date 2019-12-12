require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		loadBatch();
		addEvent();
	});
});

function addEvent() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		dom.byId('branchId').onchange = loadBatch;
		dom.byId('checkBatchId').onchange = setTip;
	});
}

function loadBatch() {
	var _url = appRoot + "/restaurant/checkstorage/lock/queryBatch.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dojo.byId('branchId').value
			}
		}).then(function(data) {
			loadSelect('checkBatchId', data, '', 'batchId', 'batchId', setTip, [ 'itemRepeatable' ]);
		}, function(err) {
		});
	});
}

var repeatable = false;
function setTip() {
	require([ "dojo/dom", "dojo/dom-attr" ], function(dom, domAttr) {
		var node = dom.byId("checkBatchId");
		if (node.options.length <= 0) {// 判断非空
			dom.byId('itemRepeatable').innerHTML = "";
			return;
		}
		var itemRepeatable = domAttr.get(node.options[node.selectedIndex], "itemRepeatable");
		if (itemRepeatable == "on") {
			dom.byId('itemRepeatable').innerHTML = "";
			repeatable = true;
		} else {
			dom.byId('itemRepeatable').innerHTML = "当前批次的多个盘点清单之间不允许出现重复原料";
			repeatable = false;
		}
	});
}

/**
 * 验证当前批次是否盘点结束
 */
function checkFinish(_batchId) {
	var _url = appRoot + '/restaurant/checkstorage/lock/checkFinish.action?checkBatchId=' + _batchId;
	_url = getUrl(_url);
	
	var finished = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.finished) {
				finished = true;
			}
		}, function(err) {
		});
	});
	return finished;
}

function importMaterial() {
	var batchId = dojo.byId('checkBatchId').value;
	if (batchId == '') {
		alert('请选择一个盘点批次！');
		return;
	}
	if (checkFinish(batchId)) {
		alert('当前批次已盘点结束！');
		return;
	}
	var fileurl = dojo.byId("fileurl").value;
	if (fileurl == "") {
		alert("请选择文件！");
		return;
	}
	var _url = appRoot + "/restaurant/checkstorage/itemimport/doImport.action?checkBatchId=" + batchId;
	_url = getUrl(_url);
	
	require([ "dojo/io/iframe", "custom/Loading" ], function(ioIframe, Mask) {
		var mask = new Mask();
		mask.show();
		ioIframe.send({
			form : "uploadForm",
			url : _url,
			handleAs : "json"
		}).then(function(data) {
			var missingLst = data.missingLst;
			String
			msg = "生成清单编号为：" + data.formId + "，导入成功！";
			if (missingLst.length > 0) {
				for (var i = missingLst.length - 1; i >= 0; i--) {// 很奇怪，后台过滤的结果顺序是颠倒的
					msg += "第" + missingLst[i].rownumber + "行" + missingLst[i].itemId + "|";
				}
				msg += "物料在系统中不存在，请检查！";
			}
			alert(msg);
			closeTab(tabId);
		}, function(err) {
			alert(err);
		});
	});
}
