/**
 * @param _formId
 * @returns {hasLock, formStatus}
 */
function getCurrentStatus(_formId) {
	var result = {};
	var _url = appRoot + "/restaurant/goodsbill/query/getCurrentStatus.action?formId=" + _formId;
	_url += "&timestamp=" + new Date().getTime(); // 防止缓存
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			result = data;
		}, function(err) {
		});
	});
	return result;
}

/**得到最新的单据版本号
 * 
 * @param _formId
 */
function getCurrentVersion(_formId) {
	var result = {};
	var _url = appRoot + "/common/function/getCurrentVeriosn.action?formId=" + _formId;
	_url += "&timestamp=" + new Date().getTime(); // 防止缓存
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			result = data;
		}, function(err) {
		});
	});
	return result;
}

/**
 * 检测版本号是否一致<br/>
 * 
 * 1. 进入页面时会读取一个版本号，即使页面刷新，这个版本号也不会变<br/>
 * 2. 在提交时会向服务器索取一个版本号<br/>
 * 3. 如果这两个版本号不一致，则不能提交<br/>
 * @param formId
 */
function checkFormVersion(formId,preNode,currentNode){
	//请求最新版本号信息
	var data = getCurrentVersion(formId);
	var preVersion = dojo.byId(preNode).value;
	
	dojo.byId(currentNode).value = data.version;
	
	//如果之前的版本号与当前的版本号不一致，那么肯定不能提交
	if(preVersion != data.version){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	
	return true;
}

function isSummaryValid(ids) {
	var flag = true;
	
	var _url = appRoot + "/centralfactory/productionDemand/summary/returnSummaryCount.action";
	_url += "?timestamp=" + new Date().getTime(); // 防止缓存
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data:{ids:ids},
			sync : true
		}).then(function(data) {
			
			if(data.counts > 0){
				alert("所选采购单已经被汇总！");
				flag =  false;
			}
			
		}, function(err) {
			flag = false;
		});
	});
	
	return flag;
}



function doInvalid(_formId, parentCallback) {
	var _url = appRoot + "/restaurant/goodsbill/query/doInvalid.action?formId=" + _formId;
	_url = getUrl(_url);
	if (confirm("确定作废单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				alert("操作成功！");
				if (parentCallback != undefined) {
					closeTab(tabId, parentCallback);
				} else {
					closeTab(tabId);
				}
			}, function(err) {
			});
		});
	}
}

function isFinished() {
	var _url = appRoot + "/common/function/isFinish.action";
	_url += "?downloadTokenValue=" + token;
	_url = getUrl(_url);
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if(token==data.msg ){
				clearInterval(int);
				standby.hide();
				location.reload(true);
			}
		}, function(err) {
		});
	});
}



function releaseLock(_formId) {
	var _url = appRoot + "/releaseLock.action?formId=" + _formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
		}, function(err) {
			alert("解锁失败！");
		});
	});
}