require([ "dojo/dom-attr", "dojo/query", "dijit/layout/TabContainer", "dijit/layout/ContentPane", "dojo/_base/declare",
		"dojox/widget/Standby", "dojo/domReady!" ], function(attr, query, TabContainer, ContentPane, declare, Standby) {
	new ContentPane({
		title : '中央工厂要货'
	}, 'unifiedCp');
	var tc = new TabContainer({}, "deliveryTc");
	tc.startup();

	// 初始化遮罩层
	standby = new Standby({
		target : "billForm"
	});

	document.body.appendChild(standby.domNode);
	standby.startup();
});

function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;

function doSave() {
	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
		var formId = dom.byId('formId').value;// 对汇总单进行状态验证
		var sdata = getCurrentStatus(formId);
		if (sdata.formStatus == '未审核') {
			alert("单据已汇总！");
			return false;
		}
		var ids = dom.byId('ids').value;
		var idArr = ids.split(",");
		sdata = getCurrentStatus(idArr[0]);
		if (sdata.formStatus == '已汇总') { // 对引用的订货及预估单进行状态验证
			alert("单据已汇总！");
			return false;
		}

		if (!isEmpty(noProvider)) {
			alert("请先设置'" + noProvider + "'与供应商的对应关系！");
			return false;
		}

		var $storageId = dom.byId('storageId');
		dom.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;

		standby.show();

		var _url = appRoot + "/centralfactory/productionDemand/purchaseBill/treeCreate/createSummaryBill.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				var idMap = data.idMap;
				var msg = "创建成功，各单据号如下：";

				function getIds(_ids) {
					var str = "";
					if (_ids.length > 0) {
						for (var i = 0; i < _ids.length - 1; i++) {
							str += _ids[i] + ",";
						}
						str += _ids[_ids.length - 1];
					}
					return str;
				}

				var allIdArr = idMap.unifiedIds;
				msg += getIds(allIdArr);
				alert(msg);
				standby.hide();
				closeTab(tabId, 'doClose');
			} else {
				alert("操作失败！");
				standby.hide();
			}
		}, function(err) {
			alert("操作失败！");
			standby.hide();
		});
	});
}
