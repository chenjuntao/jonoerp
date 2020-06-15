require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doValidate() {
	var itemId = dojo.byId('itemId').value.trim();
	if (itemId == '') {
		alert('原材料不能为空！');
		dojo.byId('itemId').focus();
		return false;
	}
	if (itemId != oldItemId && isRepeat()) {
		alert('编号已经存在！');
		dojo.byId('itemId').focus();
		return false;
	}
	var itemDimension = dojo.byId('itemDimension').value.trim();
	if (itemDimension == '') {
		alert('新材料不能为空！');
		dojo.byId('itemDimension').focus();
		return false;
	}
//	var queryCode = dojo.byId('queryCode').value.trim();
//	if (queryCode == '') {
//		alert('助记码不能为空！');
//		dojo.byId('queryCode').focus();
//		return false;
//	}
//	var itemSpecification = dojo.byId('itemSpecification').value.trim();
//	if (itemSpecification == '') {
//		alert('规格不能为空！');
//		dojo.byId('itemSpecification').focus();
//		return false;
//	}
// 	var itemDimension = dojo.byId('itemDimension').value.trim();
// 	if (itemDimension == '') {
// 		alert('库存单位不能为空！');
// 		dojo.byId('itemDimension').focus();
// 		return false;
// 	}
// 	if (!validateNum('salePrice', '售卖价', true)) {
// 		dojo.byId('salePrice').focus();
// 		return false;
// 	}
	return true;
}

/**
 * 验证编号是否重复
 */
function isRepeat() {
	var _url = appRoot + "/hq/item/meta/checkItemId.action?itemId=" + dojo.byId('itemId').value;
	
	var exist = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (data.exist) {
				exist = true;
			}
		}, function(err) {
		});
	});
	return exist;
}

function doSave() {
	if (!doValidate()) {
		return;
	}
	var _url = appRoot + "/hq/item/product/doSave.action";
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.itemId = oldItemId;
		xhr.post(_url, {
			handleAs : "json",
			data : dataObj
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
				doClose();
			} else {
				alert("保存失败！");
			}
		}, function(err) {
		});
	});
}
function doReplace() {
	if (!doValidate()) {
		return;
	}
	var itemId = dojo.byId('itemId').value.trim();
	var itemIds = dojo.byId('itemId').value.trim();
	itemId = encodeURI(encodeURI(itemId));
	var itemDimension = dojo.byId('itemDimension').value.trim();
	var itemDimensions = dojo.byId('itemDimension').value.trim();
	itemDimension = encodeURI(encodeURI(itemDimension));
	var _url = appRoot + "/hq/item/product/doReplace.action?itemId="+itemId+"&itemDimension="+itemDimension+"";

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		var dataObj = domForm.toObject("dataForm");
		dataObj.itemId = oldItemId;
		xhr.post(_url, {
			handleAs : "json",
			data : dataObj
		}).then(function(data) {
			console.info(data);
			if (data.therapy==null) {
				alert("没有找到该原材料");
				return false;
			}
			if (data.msg == 'ok') {
				alert("替换成功！");
				var tabContainer = dojo.byId('sss');
				tabContainer.style.display ='none';
				var tabContainers = dojo.byId('ddd');
				tabContainers.style.display ='none';
                var strs="";
				strs+='<tr><td style="text-align: center;">原出品</td><td style="text-align: center;">原材料</td><td style="text-align: center;">新材料</td></tr>';
		        strs+='<tr><td style="text-align: center;">'+data.therapyName+'</td><td style="text-align: center;">'+itemIds+'</td><td style="text-align: center;">'+itemDimensions+'</td></tr>';
		        dojo.byId('tab').innerHTML=strs;
		        var sdf="";
                sdf+='<tr><td style="text-align: center;"><input type="button" onclick="doClose();" value="关闭" /></td></tr>';
                dojo.byId('www').innerHTML=sdf;
            } else {
				alert("替换失败！");
			}
		}, function(err) {
		});
	});
}
		// function doReplaces(scheduleName) {
	    //         alert(scheduleName);
		// 		var strs="";
		// 		strs+='<tr><td style="text-align: center;">原出品</td><td style="text-align: center;">原材料</td><td style="text-align: center;">新材料</td></tr>';
		// 		strs+='<tr><td style="text-align: center;">ss</td><td style="text-align: center;">itemId</td><td style="text-align: center;">itemDimension</td></tr>';
		// 		// strs+='<tr><td style="text-align: center;"><input type="button" onclick="doClose();" value="关闭" /></td></tr>';
		// 		dojo.byId('tabs').innerHTML=strs;
		// }
		function doClose() {
			parent.closeEditDlg();
		}
