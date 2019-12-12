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
	$('#' + formId).prop({
		"action" : _url,
		"target" : target,
		"method" : "post"
	}).submit();
}

function getUrl(_url){
	var url;
	var loginUserId = top.frames[0].document.getElementById('loginUserId').value;
	
	if(_url.indexOf('?')!= -1){
		url = _url+"&myLoginUserId="+loginUserId
	}else{
		url = _url+"?myLoginUserId="+loginUserId
	}
	
	return url;
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
	return urlParts[0] + newQueryString + urlhash;
}

/**
 * @param _formId
 * @returns {hasLock, formStatus}
 */
function getCurrentStatus(_formId) {
	var result = {};
	var _url = appRoot + "/restaurant/goodsbill/query/getCurrentStatus.action?formId=" + _formId;
	_url += "&timestamp=" + new Date().getTime(); // 防止缓存
	$.ajax({
		type : "JSON",
		url : _url,
		data : {
			jsonData : $('#jsonData').val()
		},
		error : function() {
			console.error("query failed");
		},
		success : function(data) {
			result = data;
			$.isLoading("hide");
		}
	});
	return result;
}