require([ "dojo","dojox/widget/Standby", "dojo/ready" ], function(dojo, Standby, ready) {
	ready(function() {
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
		
		addEvent();
	});
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
}

function doValidate() {
	return true;
}

function doSave() {
	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
	
		var $storageId = dom.byId('storageId');
		dom.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;
		standby.show();
		var _url = appRoot + "/lc/request/purchase/manual/createPurchaseBill.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			if (data.msg == 'ok') {
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
				msg += getIds(data.idLst);
				alert(msg);
//				self.opener.location.reload();
//				  window.opener.location.reload();  
//				parent.location.reload();  
				closeTab(tabId, 'doClean');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}
