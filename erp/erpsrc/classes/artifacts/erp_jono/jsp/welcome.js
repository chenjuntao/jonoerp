dojo.ready(function() {
	getCounts();
	getVisiable();
});

var loginUserId = null;
function gotoRequest(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("要货单审核", addParameter(appRoot + "/restaurant/goodsbill/confirm/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoInput(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("入库单审核", addParameter(appRoot + "/restaurant/putinstorage/confirm/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoReturnGoods(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("配送退货单审核", addParameter(appRoot + "/restaurant/dreject/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoInputReturn(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("采购退货单审核", addParameter(appRoot + "/restaurant/preject/reject/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoLossItem(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("原料报损单审核", addParameter(appRoot + "/restaurant/reportdamage/confirmloss/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoLossSale(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("出品报损单审核", addParameter(appRoot + "/restaurant/reportdamage/confirmdishloss/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoTransfer(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("调拨单审核", addParameter(appRoot + "/restaurant/allocateitem/confirm/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoCheck(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("盘点单审核", addParameter(appRoot + "/restaurant/checkstorage/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoCheckstorage(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("盘点单审核", addParameter(appRoot + "/lc/checkstorage/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoOutReturn(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("外部订货方退货审核", addParameter(appRoot + "/restaurant/preject/audit/mainView.action",'branchType=OUTERORDER&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoGoodsReturn(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("采购退货审核", addParameter(appRoot + "/restaurant/preject/reject/mainView.action",'branchType=LOGISTICSCENTER&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoConfirmloss(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("报损单审核", addParameter(appRoot + "/restaurant/reportdamage/confirmloss/mainView.action",'branchType=LOGISTICSCENTER&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoPicking(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("捡货单审核", addParameter(appRoot + "/lc/stock/picking/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoDistribution(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("越库配送单审核", addParameter(appRoot + "/lc/request/distribution/audit/mainView.action",'branchType=RESTAURANT&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoIn(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("验收入库单审核", addParameter(appRoot + "/lc/stock/in/audit/mainView.action",'branchType=LOGISTICSCENTER&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoPurchase(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("采购单审核", addParameter(appRoot + "/lc/request/purchase/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

//cf
function gotoPlan(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("生产计划单审核", addParameter(appRoot + "/centralfactory/productionDemand/arrangement/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoItemBuy(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("原料采购单审核", addParameter(appRoot + "/cf/purchase/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoWork(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("生产工单审核", addParameter(appRoot + "/centralfactory/productionDemand/workOrder/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoSupperGet(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("超领单审核", addParameter(appRoot + "/cf/requisition/extra/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoCfInput(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("采购入库单审核", addParameter(appRoot + "/cf/stock/in/raw/audit/mainView.action",'branchType=CENTRALFACTORY&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoItemInput(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("产品入库审核", addParameter(appRoot + "/cf/stock/in/confirm/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoWorkReturn(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("生产退料审核", addParameter(appRoot + "/cf/requisition/return/confirm/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoWorkGet(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("生产领料审核", addParameter(appRoot + "/cf/requisition/confirm/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoWorkSupper(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("生产超领审核", addParameter(appRoot + "/cf/requisition/extra/confirm/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoNoWorkGet(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("非工单领料审核", addParameter(appRoot + "/cf/requisition/manual/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoItemOut(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("产品出库单审核", addParameter(appRoot + "/cf/stock/out/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoCfItemLoss(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("原料报损单审核", addParameter(appRoot + "/restaurant/reportdamage/confirmloss/mainView.action",'branchType=CENTRALFACTORY&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoCfSaleLoss(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("出品报损单审核", addParameter(appRoot + "/restaurant/reportdamage/confirmdishloss/mainView.action",'branchType=CENTRALFACTORY&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoGetReturn(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("采购退货审核", addParameter(appRoot + "/restaurant/preject/reject/mainView.action",'branchType=CENTRALFACTORY&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoCfCheck(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("盘点单审核", addParameter(appRoot + "/cf/checkstorage/audit/mainView.action",'myLoginUserId='+loginUserId+'&isWelcome','Y'));
}

function gotoCfTransfer(){
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	parent.frames['tab'].addTab("调拨单审核", addParameter(appRoot + "/restaurant/allocateitem/confirm/mainView.action",'branchType=CENTRALFACTORY&myLoginUserId='+loginUserId+'&isWelcome','Y'));
}
function getCounts(){
	var _url = appRoot + "/welcome/getCounts.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr"], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data) {
				dojo.byId('gotoRequest').value = dojo.byId('gotoRequest').value + "(" +data.Request + ")";
				dojo.byId('gotoInput').value = dojo.byId('gotoInput').value + "(" +data.Input + ")";
//				dojo.byId('gotoReturnGoods').value = dojo.byId('gotoReturnGoods').value + "(" +data.ReturnGoods + ")";
				dojo.byId('gotoInputReturn').value = dojo.byId('gotoInputReturn').value + "(" +data.InputReturn + ")";
				dojo.byId('gotoLossItem').value = dojo.byId('gotoLossItem').value + "(" +data.LossItem + ")";
				dojo.byId('gotoLossSale').value = dojo.byId('gotoLossSale').value + "(" +data.LossSale + ")";
				dojo.byId('gotoTransfer').value = dojo.byId('gotoTransfer').value + "(" +data.Transfer + ")";
				dojo.byId('gotoCheck').value = dojo.byId('gotoCheck').value + "(" +data.Check + ")";
				
				dojo.byId('gotoPurchase').value = dojo.byId('gotoPurchase').value + "(" +data.Purchase + ")";
				dojo.byId('gotoIn').value = dojo.byId('gotoIn').value + "(" +data.In + ")";
				dojo.byId('gotoDistribution').value = dojo.byId('gotoDistribution').value + "(" +data.Distribution + ")";
				dojo.byId('gotoPicking').value = dojo.byId('gotoPicking').value + "(" +data.Picking + ")";//
				dojo.byId('gotoConfirmloss').value = dojo.byId('gotoConfirmloss').value + "(" +data.LossItem + ")";//
				dojo.byId('gotoOutReturn').value = dojo.byId('gotoOutReturn').value + "(" +data.OutIReturn + ")";//
				dojo.byId('gotoGoodsReturn').value = dojo.byId('gotoGoodsReturn').value + "(" +data.InputReturn + ")";//
				dojo.byId('gotoCheckstorage').value = dojo.byId('gotoCheckstorage').value + "(" +data.Check + ")";//
				
				
				dojo.byId('gotoPlan').value = dojo.byId('gotoPlan').value + "(" +data.Plan + ")";
				dojo.byId('gotoItemBuy').value = dojo.byId('gotoItemBuy').value + "(" +data.ItemBuy + ")";
//				dojo.byId('gotoWork').value = dojo.byId('gotoWork').value + "(" +data.Work + ")";
//				dojo.byId('gotoSupperGet').value = dojo.byId('gotoSupperGet').value + "(" +data.SupperGet + ")";
				dojo.byId('gotoCfInput').value = dojo.byId('gotoCfInput').value + "(" +data.CfInput + ")";
				dojo.byId('gotoItemInput').value = dojo.byId('gotoItemInput').value + "(" +data.ItemInput + ")";
				dojo.byId('gotoWorkReturn').value = dojo.byId('gotoWorkReturn').value + "(" +data.WorkReturn + ")";
				dojo.byId('gotoWorkGet').value = dojo.byId('gotoWorkGet').value + "(" +data.WorkGet + ")";
				dojo.byId('gotoWorkSupper').value = dojo.byId('gotoWorkSupper').value + "(" +data.WorkSupper + ")";
				dojo.byId('gotoNoWorkGet').value = dojo.byId('gotoNoWorkGet').value + "(" +data.NoWorkGet + ")";
				dojo.byId('gotoItemOut').value = dojo.byId('gotoItemOut').value + "(" +data.ItemOut + ")";
				dojo.byId('gotoCfItemLoss').value = dojo.byId('gotoCfItemLoss').value + "(" +data.CfItemLoss + ")";//
				dojo.byId('gotoCfSaleLoss').value = dojo.byId('gotoCfSaleLoss').value + "(" +data.CfSaleLoss + ")";//
				dojo.byId('gotoGetReturn').value = dojo.byId('gotoGetReturn').value + "(" +data.GetReturn + ")";//
				dojo.byId('gotoCfCheck').value = dojo.byId('gotoCfCheck').value + "(" +data.CfCheck + ")";//
				dojo.byId('gotoCfTransfer').value = dojo.byId('gotoCfTransfer').value + "(" +data.CfTransfer + ")";//
			} else {
				// do something
			}
		}, function(err) {
		});
	});
}

function getVisiable(){
	var _url = appRoot + "/welcome/getVisiable.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr"], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
//			console.dir(data);
			if (data) {
				dojo.byId('gotoRequest').hidden = data.Request>0?false:true;
				dojo.byId('gotoInput').hidden = data.Input>0?false:true;
//				dojo.byId('gotoReturnGoods').hidden = data.ReturnGoods>0?false:true;
				dojo.byId('gotoInputReturn').hidden = data.InputReturn>0?false:true;
				dojo.byId('gotoLossItem').hidden = data.LossItem>0?false:true;
				dojo.byId('gotoLossSale').hidden = data.LossSale>0?false:true;
				dojo.byId('gotoTransfer').hidden = data.Transfer>0?false:true;
				dojo.byId('gotoCheck').hidden = data.Check>0?false:true;
				
				dojo.byId('gotoPurchase').hidden = data.Purchase>0?false:true;
				dojo.byId('gotoIn').hidden = data.In>0?false:true;
				dojo.byId('gotoDistribution').hidden = data.Distribution>0?false:true;
				dojo.byId('gotoPicking').hidden = data.Picking>0?false:true;
				dojo.byId('gotoOutReturn').hidden = data.OutReturn>0?false:true;
				dojo.byId('gotoConfirmloss').hidden = data.Confirmloss>0?false:true;
				dojo.byId('gotoGoodsReturn').hidden = data.GoodsReturn>0?false:true;
				dojo.byId('gotoCheckstorage').hidden = data.Checkstorage>0?false:true;
				
				dojo.byId('gotoPlan').hidden = data.Plan>0?false:true;
				dojo.byId('gotoItemBuy').hidden = data.ItemBuy>0?false:true;
//				dojo.byId('gotoWork').hidden = data.Work>0?false:true;
//				dojo.byId('gotoSupperGet').hidden = data.SupperGet>0?false:true;
				dojo.byId('gotoCfInput').hidden = data.CfInput>0?false:true;
				dojo.byId('gotoItemInput').hidden = data.ItemInput>0?false:true;
				dojo.byId('gotoWorkReturn').hidden = data.WorkReturn>0?false:true;
				dojo.byId('gotoWorkGet').hidden = data.WorkGet>0?false:true;
				dojo.byId('gotoWorkSupper').hidden = data.WorkSupper>0?false:true;
				dojo.byId('gotoNoWorkGet').hidden = data.NoWorkGet>0?false:true;
				dojo.byId('gotoItemOut').hidden = data.ItemOut>0?false:true;
				dojo.byId('gotoCfItemLoss').hidden = data.CfItemLoss>0?false:true;
				dojo.byId('gotoCfSaleLoss').hidden = data.CfSaleLoss>0?false:true;
				dojo.byId('gotoGetReturn').hidden = data.GetReturn>0?false:true;
				dojo.byId('gotoCfCheck').hidden = data.CfCheck>0?false:true;
				dojo.byId('gotoCfTransfer').hidden = data.CfTransfer>0?false:true;
			} else {
				// do something
			}
		}, function(err) {
		});
	});
}

function addTab(_title, _url) {
	require([ "dijit/registry", "dijit/layout/ContentPane" ], function(registry, ContentPane) {
		var tabs = registry.byId("TabContainer");
		var tabId = generateTabId(_url);
		var epane = registry.byId(tabId);
		if (epane) {
			tabs.selectChild(epane);
		} else {
			var pane = new ContentPane({
				id : tabId,
				title : _title,
				closable : true,
				content : '',
				selected : true
			});
			_url = addParameter(_url, 'tabId', tabId);
			var ifrHtml = '<div class="iframe_wrapper">' + '<div class="loading">' + pane.loadingMessage + '</div>'
					+ '<iframe id="ifr_' + tabId + '" name="ifr_' + tabId + '" class="tab_iframe" onload="afterLoaded(this);"></iframe>'
					+ '</div>';
			pane.set("content", ifrHtml);
			tabs.addChild(pane);
			document.getElementById("ifr_" + tabId).src = _url;
			tabs.selectChild(pane);
		}
	});
}

/**
 * 通过post方式增加tab页，达到传递大量数据的目的
 * 
 * 原理： 这里只是增加一个iframe, 不设定src路径，子窗口中的表单target指向这个iframe
 * 
 * @param formId
 * @param _title
 * @param _url
 */
function addPostTab(_title, _url) {
	var tabId = generateTabId(_url);
	require([ "dijit/registry", "dijit/layout/ContentPane" ], function(registry, ContentPane) {
		var tabs = registry.byId("TabContainer");
		var epane = registry.byId(tabId);
		if (epane) {
			tabs.selectChild(epane);
		} else {
			var pane = new ContentPane({
				id : tabId,
				title : _title,
				closable : true,
				content : '',
				selected : true
			});

			var src = window.ActiveXObject ? 'javascript:false' : 'about:blank';
			var ifrHtml = '<div class="iframe_wrapper">' + '<div class="loading">' + pane.loadingMessage + '</div>'
					+ '<iframe name="ifr_' + tabId + '" class="tab_iframe" onload="afterLoaded(this);"></iframe>'
					+ '</div>';
			pane.set("content", ifrHtml);
			tabs.addChild(pane);
			tabs.selectChild(pane);
		}
	});
	return tabId;
}

function closeTab(tabId) {
	require([ "dijit/registry" ], function(registry) {
		var tabs = registry.byId("TabContainer");
		var epane = registry.byId(tabId);
		if (epane) {
			tabs.closeChild(epane);
		}
	});
}

function afterLoaded(_ifr) {
	dojo.require("dojo.NodeList-traverse");
	var loaddingDiv = dojo.query(_ifr).prev()[0];
	loaddingDiv.style.display = 'none';
	_ifr.style.display = 'block';
}

var tabMap = {};

function generateTabId(tabUrl) {
	var uuid;
	if (!tabMap[tabUrl]) {
		uuid = Math.uuidFast();// url->uuid
		tabMap[tabUrl] = uuid;
	} else {
		uuid = tabMap[tabUrl];
	}
	return uuid;
}

/**
 * atStart : Add param before others
 */
function addParameter(url, parameterName, parameterValue, atStart) {
	replaceDuplicates = true;
	if (url.indexOf('#') > 0) {
		var cl = url.indexOf('#');
		urlhash = url.substring(url.indexOf('#'), url.length);
	} else {
		urlhash = '';
		cl = url.length;
	}
	sourceUrl = url.substring(0, cl);

	var urlParts = sourceUrl.split("?");
	var newQueryString = "";

	if (urlParts.length > 1) {
		var parameters = urlParts[1].split("&");
		for (var i = 0; (i < parameters.length); i++) {
			var parameterParts = parameters[i].split("=");
			if (!(replaceDuplicates && parameterParts[0] == parameterName)) {
				if (newQueryString == "")
					newQueryString = "?";
				else
					newQueryString += "&";
				newQueryString += parameterParts[0] + "=" + (parameterParts[1] ? parameterParts[1] : '');
			}
		}
	}
	if (newQueryString == "")
		newQueryString = "?";

	if (atStart) {
		newQueryString = '?' + parameterName + "=" + parameterValue
				+ (newQueryString.length > 1 ? '&' + newQueryString.substring(1) : '');
	} else {
		if (newQueryString != "" && newQueryString != '?')
			newQueryString += "&";
		newQueryString += parameterName + "=" + (parameterValue ? parameterValue : '');
	}
	return urlParts[0] + newQueryString + urlhash;
}