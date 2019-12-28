require([ "dojox/widget/Standby","dojo/domReady!" ], function(Standby) {
	standby = new Standby({
		target : "lockForm"
	});
	
	document.body.appendChild(standby.domNode);
	standby.startup();
});

var standby = null;

function doSubmit() {
	standby.show();
	
	var $storageId = dojo.byId('storageId');
	dojo.byId('storage').value = $storageId.options[$storageId.selectedIndex].text;
	
	var _url = appRoot + '/restaurant/checkstorage/lock/saveLock.action';
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("lockForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				standby.hide();
				alert("批次号为："+data.checkBatchId+"\n"+"提交成功！");
				closeTab(tabId);
			} else {
				alert("操作失败！");
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
			title : "选择原料类别",
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
