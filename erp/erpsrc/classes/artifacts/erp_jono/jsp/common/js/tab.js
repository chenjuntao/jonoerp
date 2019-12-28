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