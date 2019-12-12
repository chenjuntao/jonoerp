require([ "dojox/widget/Standby","dojo/domReady!" ], function(Standby) {
	
	// 初始化遮罩层
	standby = new Standby({
		target : "lockForm"
	});
	
	document.body.appendChild(standby.domNode);
	standby.startup();
	
});

function doSubmit() {
	var $storageId = dojo.byId('storageId');
	dojo.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;
	standby.show();
	
	var _url = appRoot + '/restaurant/checkstorage/lock/saveLock.action';
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("lockForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				standby.hide();
				closeTab(tabId);
			} else {
				alert("操作失败！");
				standby.hide();
			}
		}, function(err) {
		});
	});
}

var cateDlg = null;
function selCategory() {
	var frameId = 'ifr_selCate';
	if (cateDlg == null) {
		var _url = appRoot + "/restaurant/common/selcategory/mainView.action";
		_url = getUrl(_url);
		
		var option = {
			title : "自选原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			cateDlg = iDlg;
		});
	} else {
		cateDlg.show();
	}
}

function closeCateDlg(idArr, nameArr) {
	require([ "dojo/dom" ], function(dom) {
		dom.byId('cateSpan').innerHTML = nameArr.join("、");
		dom.byId('itemCategory').value = idArr.join(",");
	});
	cateDlg.hide();
}
