require([ "dojo/dom-attr", "dojo/query", "dijit/layout/TabContainer", "dijit/layout/ContentPane","dojox/widget/Standby", "dojo/domReady!" ],
		function(attr, query, TabContainer, ContentPane,Standby) {
			new ContentPane({
				title : '餐厅要货（直配）'
			}, 'directCp');
			new ContentPane({
				title : '餐厅要货（越库）'
			}, 'crossCp');
			new ContentPane({
				title : '餐厅要货（统配）、外部订货、需求预估'
			}, 'unifiedCp');
			var tc = new TabContainer({}, "deliveryTc");
			tc.startup();
			
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

var standby = null;

function doSave() {
	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
		var ids = dom.byId('ids').value;
		var idArr = ids.split(",");
		sdata = getCurrentStatus(idArr[0]);
		if (sdata.formStatus == '已汇总') { // 对引用的订货及预估单进行状态验证
			alert("单据已汇总！");
			return false;
		}
		
		if(!isEmpty(noProvider)){
			alert("请先设置'"+ noProvider+"'与供应商的对应关系！");
			return false;
		}
		
		standby.show();

		var $storageId = dom.byId('storageId');
		dom.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;

		var _url = appRoot + "/lc/request/summary/createSummaryBill.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			
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
				var allIdArr= idMap.directIds.concat(idMap.crossIds).concat(idMap.unifiedIds);
				msg += getIds(allIdArr);
				alert(msg);
				closeTab(tabId, 'doClose');
			} else {
				standby.hide();
				alert("操作失败！");
			}
		}, function(err) {
			standby.hide();
			alert("操作失败！");
		});
	});
}
