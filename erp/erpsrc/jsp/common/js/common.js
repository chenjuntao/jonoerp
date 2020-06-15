init_common();

var numReg = /^(-)?[0-9]{1,}\.{0,1}[0-9]{0,}$/g;
function init_common() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		window.validateNum = function(_id, _text, _required) {
			var value = dom.byId(_id).value.trim();
			if (_required && value == '') {
				alert(_text + "必须有值！");
				return false;
			}
			if (value != '' && value.match(numReg) == null) {
				alert(_text + "只能为数字！");
				return false;
			}
			return true;
		};
	});
}

function getUrl(_url) {
	var url;
	var loginUserId = "";
	loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	if (_url.indexOf('?') != -1) {
		url = _url + "&myLoginUserId=" + loginUserId
	} else {
		url = _url + "?myLoginUserId=" + loginUserId
	}

	return url;
}

function getBoldText(text) {
	if (!text) {
		return ' ';
	} else {
		return '<b>' + text + '</b>';
	}
}

function getLoadingMsg() {
	var imgSrc = appRoot + "/jsp/common/img/wait.gif";
	var msg = '<div style="text-align: center; padding-top: 100px; height: 300px;">'
			+ '<div style="vertical-align: middle;">正在查询请等待...</div>' + '<img src="' + imgSrc + '"> </div>';
	return msg;
}

if (String.prototype.trim == undefined) {
	/**
	 * IE8下对String的扩展，将 String 去前后空格
	 */
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	};
}

// querystring to POST JavaScript
// copyright 15th October 2007 by Stephen Chapman
// permission to use this Javascript on your web page is granted
// provided that all of the code in this script (including these
// comments) is used without any alteration
/**
 * 使用post方式跳转页面，还可用于导出下载
 * 
 * @param _url
 *            字符串
 * @param _params
 *            js对象
 */
function post_redirect(_url, _params, _target) {
	var newF = document.createElement("form");
	newF.action = _url;
	newF.method = 'POST';
	if (_target != undefined) {
		newF.target = _target;
	}

	for ( var key in _params) {
		var val = _params[key];
		var newH = document.createElement("input");
		newH.name = key;
		newH.type = 'hidden';
		newH.value = val;
		newF.appendChild(newH);
	}
	document.getElementsByTagName('body')[0].appendChild(newF);
	newF.submit();
}

/**
 * dojo ajax请求出错处理函数
 * 
 * @param _error
 */
function errorHandler(_error) {
	if (_error.response.status == '403') {
		alert('登录超时，请重新登录!');
		window.top.location.href = appRoot + "/jsp/login/main.jsp";
	} else {
		alert('数据加载错误!');
	}
}

/**
 * 增加tab页
 * 
 * @param _title
 * @param _url
 */
function addTab(_title, _url) {
	if (window.top != window.self) {
		parent.addTab(_title, _url);
	} else {
		window.open(_url);
	}
}

function closeTab(_tabId, parentCallback) {
	if (window.top != window.self) {
		if (parentCallback != undefined) {
			parent.frames['ifr_' + parentTabId][parentCallback]();
		}
		parent.closeTab(_tabId);
	} else {
		window.close();
	}
}

/**
 * 通过post方式增加tab页，达到传递大量数据的目的
 * 
 * @param formId
 * @param _title
 * @param _url
 */
function addPostTab(formId, _title, _url) {
	var target = "_blank";
	if (window.top != window.self) {
		var tabId = parent.addPostTab(_title, _url);
		target = 'ifr_' + tabId;
		_url = addParameter(_url, 'tabId', tabId);
	}
	require([ "dojo/dom" ], function(dom) {
		var form = dom.byId(formId);
		form.action = _url;
		form.target = target;
		form.method = 'post';
		form.submit();
	});
}

/**
 * 在当前页面跳转提交
 * 
 * @param formId
 * @param _url
 */
function postTab(formId, _url) {
	if (window.top != window.self) {
		_url = addParameter(_url, 'tabId', tabId);// 继续传递当前页面的tabId
	}
	require([ "dojo/dom" ], function(dom) {
		var form = dom.byId(formId);
		form.action = _url;
		form.method = 'post';
		form.submit();
	});
}

/**
 * atStart : Add param before others
 */
function addParameter(url, parameterName, parameterValue, atStart) {
	var replaceDuplicates = true;
	var urlhash = '';
	if (url.indexOf('#') > 0) {
		var cl = url.indexOf('#');
		urlhash = url.substring(url.indexOf('#'), url.length);
	} else {
		cl = url.length;
	}
	var sourceUrl = url.substring(0, cl);

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
		if (newQueryString !== "" && newQueryString != '?')
			newQueryString += "&";
		newQueryString += parameterName + "=" + (parameterValue ? parameterValue : '');
	}
	console.log(urlParts[0] + newQueryString + urlhash);
	return urlParts[0] + newQueryString + urlhash;
}

/**
 * dlg_iframe,dlg_loading,iframe_wrapper三个样式在common.css中定义 we should set
 * data-dojo-config="async:0" in main.jsp, not async, because we need return the
 * dialog instance.
 * 
 * @param opt
 * @returns {Dialog}
 */
function createDialog(opt, callback) {
	var iDlg = null;
	var frameId = '';
	if (opt.frameId != undefined) {
		frameId = opt.frameId;
	}

	// Dialog 内嵌入 iframe，以防父窗口与子窗口资源同名冲突
	var loadingMsg = "<span class='dijitContentPaneLoading'><span class='dijitInline dijitIconLoading'></span>正在加载...</span>";
	var ifrHtml = '<div class="iframe_wrapper">' + '<div class="dlg_loading">' + loadingMsg + '</div>' + '<iframe id="'
			+ frameId + '" class="dlg_iframe" onload="dlgIframeLoaded(this);" src="' + opt.url + '"></iframe>'
			+ '</div>';

	require([ "custom/Dialog", "dojo/dom-style" ], function(Dialog, domStyle) {
		iDlg = new Dialog({
			style : {
				width : opt.width,
				height : opt.height
			},
			title : opt.title
		});

		iDlg.set("content", ifrHtml);

		domStyle.set(iDlg.containerNode, {
			position : 'absolute',
			width : "100%",
			top : '25px',
			bottom : '0px',
			height : 'auto',
			padding : '0px'
		});
		iDlg.show();

		// 适用于异步设置async:1
		if (callback != undefined) {
			callback(iDlg);
		}
	});
	return iDlg;
}

function dlgIframeLoaded(_ifr) {
	require([ "dojo/query", "dojo/NodeList-traverse" ], function(query) {
		var loaddingDiv = query(_ifr).prev()[0];
		loaddingDiv.style.display = 'none';
		_ifr.style.display = 'block';
	});
}

/**
 * 装载下拉框数据，数据的键值分开，eg:[{'key' : '1', value : 'test'}]
 * 
 * @param id
 * @param data
 * @param selVal
 *            设置当前选中的值
 * @param listKey
 * @param listValue
 * @param onSuccess
 * @param otherKeys
 *            其它键值对数据存储对对应的attr属性中，方便数据处理
 */
function loadSelect(id, data, selVal, listKey, listValue, onSuccess, otherKeys) {
	// clear options first;
	var select = dojo.byId(id);
	var length = select.options.length;
	for (var i = length - 1; i >= 0; i--) { // 倒序删除，防止移动后造成空项
		select.options[i] = null;
	}

	require([ "dojo/_base/array", "dojo/query", "dojo/dom-construct", "dojo/dom-attr" ], function(array, query, dom,
			domAttr) {
		array.forEach(data, function(item, i) {
			var option = dom.create("option", {
				value : item[listKey],
				innerHTML : item[listValue]
			});
			if (item[listKey] == selVal) {
				domAttr.set(option, "selected", true); // set
			}
			if (otherKeys != undefined) {
				array.forEach(otherKeys, function(key, i) {
					domAttr.set(option, key, item[key]); // set
				});
			}
			dom.place(option, id);
		});
	});

	if (onSuccess != undefined) {
		onSuccess();
	}
}

/**
 * 装载下拉框数据，数据的键值相同，eg:[1,2,3]
 * 
 * @param id
 * @param data
 * @param selVal
 */
function loadSelData(id, data, selVal, onSuccess) {
	require([ "dojo/dom", "dojo/_base/array", "dojo/query", "dojo/dom-construct", "dojo/dom-attr" ], function(dom,
			array, query, domc, domAttr) {
		// clear options first;
		var select = dom.byId(id);
		var length = select.options.length;
		for (var i = length - 1; i >= 0; i--) { // 倒序删除，防止移动后造成空项
			select.options[i] = null;
		}

		array.forEach(data, function(value, i) {
			var text = value;
			if (value == '-1') {
				text = '--请选择--';
			}
			var option = domc.create("option", {
				value : value,
				innerHTML : text
			});
			if (value == selVal) {
				domAttr.set(option, "selected", true); // set
			}
			domc.place(option, id);
		});
		if (onSuccess != undefined) {
			onSuccess();
		}
	});
}

/**
 * refresh dijit Tree
 * 
 * @param iTree
 */
function refreshTree(iTree) {
	iTree.dndController.selectNone(); // As per the answer below
	// Credit to this discussion:
	// http://mail.dojotoolkit.org/pipermail/dojo-interest/2010-April/045180.html
	// Close the store (So that the store will do a new fetch()).
	iTree.model.store.clearOnClose = true;
	// iTree.model.store.close();

	// Completely delete every node from the dijit.Tree
	iTree._itemNodesMap = {};
	iTree.rootNode.state = "UNCHECKED";
	iTree.model.root.children = null;

	// Destroy the widget
	iTree.rootNode.destroyRecursive();

	// Recreate the model, (with the model again)
	iTree.model.constructor(iTree.model);

	// Rebuild the tree
	iTree.postMixInProperties();
	iTree._load();
}

function createContent(_data, _hander) {
	var content = "";
	require([ "dojo/_base/array" ], function(arrayUtil) {
		arrayUtil.forEach(_data.formRefId.split(","), function(_refId) {
			content += "<a href='#' onclick='" + _hander + "(\"" + _refId + "\");'>" + _refId + "</a></br></br>";
		});
	});
	return content;
}

/**
 * 更改单据的查看状态
 * 
 * @param form_id
 *            单据编号
 */
function updateStatus(formId) {
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(appRoot + "/sp/poscan/updateStatus.action", {
			handleAs : "json",
			data : {
				formId : formId
			}
		}).then(function(data) {
			console.log("success");
		}, function(err) {
			console.log(err);
		});
	});
}

function doDetailScan(formId, type) {
	var _url = null;
	var flag = false;
	var _title = " ";

	var testStr = formId;
	if (testStr.indexOf("PTH") != -1) {
		_title = "查看配送退货单-";
		_url = appRoot + "/restaurant/dreject/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJP") != -1 && !flag) {
		_title = "查看采购调价单-";
		_url = appRoot + "/hq/priceadjust/purchase/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJB") != -1 && !flag) {
		_title = "查看标准调价单-";
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJJ") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJR") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJW") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJS") != -1 && !flag) {
		_title = "查看出品售卖调价单-";
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("YK") != -1 || testStr.indexOf("ZP") != -1 || testStr.indexOf("TP") != -1
			|| testStr.indexOf("CG") != -1 && !flag) {
		_url = appRoot + "/sp/poscan/scanView.action";
		_title = "查看采购单-";

		if (type == 'LITTLE') {
			_url = appRoot + "/sp/poscan/lscanView.action";
		}
		_url += "?formId=" + formId + "&parentTabId=" + tabId;

		flag = true;
	}

	if (testStr.indexOf("LK") != -1 && !flag) {
		_url = appRoot + "/restaurant/putinstorage/outside/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看入库单-";
	}

	if (testStr.indexOf("LL") != -1 && !flag) {
		_url = appRoot + "/cf/requisition/manage/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看领料单-";
	}

	if (testStr.indexOf("CTH") != -1 && !flag) {
		_title = "查看采购退货单-";
		_url = appRoot + "/restaurant/preject/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("DZ") != -1 && !flag) {
		_url = appRoot + "/sp/statement/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("CH") != -1 && !flag) {
		_url = appRoot + "/lc/request/delivery/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("RQO") != -1 && !flag) {
		_url = appRoot + "/outerorder/manage/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看外部订货单-";
	}

	if (testStr.indexOf("YH") != -1 && !flag) {
		_url = appRoot + "/restaurant/goodsbill/query/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看要货单-";

		if (type == 'outer') {
			_url = appRoot + "/outerorder/manage/scanView.action?formId=" + formId;
			_title = "查看外部订货单-";
		}
	}

	if (testStr.indexOf("PS") != -1 && !flag) {
		_url = appRoot + "/restaurant/inoutquery/shipping/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看配送单-";
	}

	if (testStr.indexOf("CK") != -1 && !flag) {
		_url = appRoot + "/cf/stock/out/query/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看出库单-";
	}

	if (testStr.indexOf("JH") != -1 && !flag) {
		_title = "查看计划单-";
		_url = appRoot + "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("BSR") != -1 && !flag) {
		_title = "查看原料报损单-";
		_url = appRoot + "/restaurant/reportdamage/queryloss/showView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("BSD") != -1 && !flag) {
		_title = "查看出品报损单-";
		_url = appRoot + "/restaurant/reportdamage/querydishloss/showView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("GD") != -1 && !flag) {
		_title = "查看工单-";
		_url = appRoot + "/centralfactory/productionDemand/workOrder/audit/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("DB") != -1 && !flag) {
		_title = "查看调拨单-";
		_url = appRoot + "/restaurant/allocateitem/query/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("CS") != -1 && !flag) {
		_url = appRoot + "/restaurant/inoutquery/checkstorage/scan/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("WBMB") != -1 && !flag) {
		var _url = appRoot + "/restaurant/common/template/scanView.action?templateId=" + formId;
		_title = "外部订货模板查看-";
		flag = true;
	}

	if (testStr.indexOf("BCR") != -1 && !flag) {
		var _url = appRoot + "/restaurant/production/querySelf/scanView.action?formId=" + formId;
		_title = "半成品入库单查看-";
		flag = true;
	}

	_title += formId;

	addTab(_title, _url);
	return;
}

function doMoreDetailScan(row, type) {
	var _url = null;
	var flag = false;
	var _title = " ";
	var formId = row.formId;

	var testStr = formId;

	if (testStr.indexOf("PTH") != -1) {
		_title = "查看配送退货单-";
		_url = appRoot + "/restaurant/dreject/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJP") != -1 && !flag) {
		_title = "查看采购调价单-";
		_url = appRoot + "/hq/priceadjust/purchase/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJB") != -1 && !flag) {
		_title = "查看标准调价单-";
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJJ") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJR") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJW") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJS") != -1 && !flag) {
		_title = "查看出品售卖调价单-";
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("YK") != -1 || testStr.indexOf("ZP") != -1 || testStr.indexOf("TP") != -1
			|| testStr.indexOf("CG") != -1 && !flag) {
		_url = appRoot + "/sp/poscan/scanView.action";
		_title = "查看采购单-";

		if (type == 'LITTLE') {
			_url = appRoot + "/sp/poscan/lscanView.action";
		}
		_url += "?formId=" + formId + "&parentTabId=" + tabId;

		flag = true;
	}

	if (testStr.indexOf("LK") != -1 && !flag) {
		_url = appRoot + "/restaurant/putinstorage/outside/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看入库单-";
	}

	if (testStr.indexOf("CTH") != -1 && !flag) {
		_title = "查看采购退货单-";
		_url = appRoot + "/restaurant/preject/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("DZ") != -1 && !flag) {
		_url = appRoot + "/sp/statement/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("CH") != -1 && !flag) {
		_url = appRoot + "/lc/request/delivery/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("RQO") != -1 && !flag) {
		_url = appRoot + "/outerorder/manage/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看外部订货单-";
	}

	if (testStr.indexOf("YH") != -1 && !flag) {
		_url = appRoot + "/restaurant/goodsbill/query/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看要货单-";
	}

	if (testStr.indexOf("PS") != -1 && !flag) {
		_url = appRoot + "/restaurant/inoutquery/shipping/scanView.action?formId=" + formId + "&addFormFlag="
				+ row.isAddForm;
		flag = true;
		_title = "查看配送单-";
	}

	if (testStr.indexOf("JH") != -1 && !flag) {
		_title = "查看计划单-";
		_url = appRoot + "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("BSR") != -1 && !flag) {
		_title = "查看原料报损单-";
		_url = appRoot + "/restaurant/reportdamage/queryloss/showView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("BSD") != -1 && !flag) {
		_title = "查看出品报损单-";
		_url = appRoot + "/restaurant/reportdamage/querydishloss/showView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("GD") != -1 && !flag) {
		_title = "查看工单-";
		_url = appRoot + "/centralfactory/productionDemand/workOrder/audit/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("DB") != -1 && !flag) {
		_title = "查看调拨单-";
		_url = appRoot + "/restaurant/allocateitem/query/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("CS") != -1 && !flag) {
		_url = appRoot + "/restaurant/inoutquery/checkstorage/scan/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("WBMB") != -1 && !flag) {
		var _url = appRoot + "/restaurant/common/template/scanView.action?templateId=" + formId;
		_title = "外部订货模板查看-";
		flag = true;
	}
	_title += formId;

	addTab(_title, _url);
	return;
}

function doDetailScanWithDel(formId, type) {
	var data = getCurrentVersion(formId);
	if (data.operationContent == "已删除") {
		msg = data.operationName + "在" + data.operationTime + data.operationContent;
		alert(msg);
		return;
	}

	var _url = null;
	var flag = false;
	var _title = " ";
	var testStr = formId.toUpperCase();

	if (testStr.indexOf("PTH") != -1) {
		_url = appRoot + "/restaurant/dreject/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJP") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/purchase/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJB") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJJ") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJR") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJW") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("TJS") != -1 && !flag) {
		_url = appRoot + "/hq/priceadjust/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("YK") != -1 || testStr.indexOf("ZP") != -1 || testStr.indexOf("TP") != -1
			|| testStr.indexOf("CFCG") != -1 && !flag) {
		_url = appRoot + "/sp/poscan/scanView.action";
		if (type == 'LITTLE') {
			_url = appRoot + "/sp/poscan/lscanView.action";
		}
		_title = "查看采购单-";

		_url += "?formId=" + formId + "&parentTabId=" + tabId;

		flag = true;
	}

	if (testStr.indexOf("LK") != -1 && !flag) {
		_url = appRoot + "/restaurant/putinstorage/outside/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看入库单-";
	}

	if (testStr.indexOf("CTH") != -1 && !flag) {
		_title = "查看采购退货单-";
		_url = appRoot + "/restaurant/preject/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("DZ") != -1 && !flag) {
		_url = appRoot + "/sp/statement/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("CH") != -1 && !flag) {
		_url = appRoot + "/lc/request/delivery/manage/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("RQO") != -1 && !flag) {
		_url = appRoot + "/outerorder/manage/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看外部订货单-";
	}

	if (testStr.indexOf("YH") != -1 && !flag) {
		_url = appRoot + "/restaurant/goodsbill/query/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看要货单-";
	}

	if (testStr.indexOf("PS") != -1 && !flag) {
		_url = appRoot + "/restaurant/inoutquery/shipping/scanView.action?formId=" + formId;
		flag = true;
		_title = "查看配送单-";
	}

	if (testStr.indexOf("JH") != -1 && !flag) {
		_url = appRoot + "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("BSR") != -1 && !flag) {
		_title = "查看原料报损单-";
		_url = appRoot + "/restaurant/reportdamage/queryloss/showView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("BSD") != -1 && !flag) {
		_title = "查看出品报损单-";
		_url = appRoot + "/restaurant/reportdamage/querydishloss/showView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("GD") != -1 && !flag) {
		_url = appRoot + "/centralfactory/productionDemand/workOrder/audit/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("DB") != -1 && !flag) {
		_title = "查看调拨单-";
		_url = appRoot + "/restaurant/allocateitem/query/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("CS") != -1 && !flag) {
		_url = appRoot + "/restaurant/inoutquery/checkstorage/scan/scanView.action?formId=" + formId;
		flag = true;
	}

	if (testStr.indexOf("WBMB") != -1 && !flag) {
		var _url = appRoot + "/restaurant/common/template/scanView.action?templateId=" + formId;
		_title = "外部订货模板查看-";
		flag = true;
	}
	_title += formId;
	_url = getUrl(_url);
	addTab(_title, _url);
	return;
}

function toHelpPage(flag) {
	var _url = "https://www.zybuluo.com/yodang2008/note/508661";
	var _title = '餐厅要货单帮助文档';

	switch (flag) {
	case "REQUEST_CREATE":
		_url = "https://www.zybuluo.com/yodang2008/note/508661";
		_title = '餐厅要货单帮助文档';
		break;
	case "LC_COST":
		_url = "https://www.zybuluo.com/yodang2008/note/554455";
		_title = '物流中心成本帮助文档';
		break;
	case "CF_COST":
		_url = "https://www.zybuluo.com/yodang2008/note/554576";
		_title = '中央工厂成本帮助文档';
		break;
	case "CF_P_OUT":
		_url = "https://www.zybuluo.com/yodang2008/note/564041";
		_title = '产品出库帮助文档';
		break;
	default:
		console.log("未找到帮助文档URL!");
		break;
	}

	addTab(_title, _url);
}

function isEmpty(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop))
			return false;
	}
	return true;
}

function isInOperation(operationContent) {
	// 影响单据状态的操作
	var operationArr = [ '已作废', '已删除', '已结案', '已审核' ];
	for (var i = 0, len = operationArr.length; i < len; i++) {
		if (operationContent == operationArr[i]) {
			return true;
		}
	}

	return false;
}

function doRefresh() {
	location.reload();// 刷新页面
}

function refreshStorage(object) {
	var _url = appRoot + '/restaurant/reportdamage/queryloss/refreshStorage.action';
	_url = getUrl(_url);
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr(_url, {
			handleAs : "json",
			query : object.query
		}).then(function(data) {
			if (data.msg) {
				var storageSelector = dojo.byId(object.storageId);
				storageSelector.length = 0;
				for (var i = 0, length = data.msg.length; i < length; i++) {
					var item = data.msg[i];
					storageSelector.options.add(new Option(item.storageName, item.storageId));
				}
			} else {
				// do something
			}
		}, function(err) {
		});
	});
}