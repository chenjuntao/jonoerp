/***********************************************************************

 Copyright (c) 2006
 National University of Defence Technology
 ChangSha, China

 All Rights Reserved

 Authors: Huang Jie

 $Id: request.js,v 1.19 2010/02/01 08:00:34 HuangJie Exp $

***********************************************************************/

//作者：黄杰
//描述：发送ajax异步请求

var READY_STATE_UNINITIALIZED = 0;
var READY_STATE_LOADING = 1;
var READY_STATE_LOADED = 2;
var READY_STATE_INTERACTIVE = 3;
var READY_STATE_COMPLETE = 4;

function initXMLHTTPRequest()
{
	//获取异步请求对象
	var xRequest = null;
	if(window.ActiveXObject)
	{
		xRequest = new ActiveXObject("Microsoft.XMLHTTP");
		if(xRequest == null)
		{
			xRequest = new ActiveXObject("msxml2.XMLHTTP");
		}
	}
	else
	{
		xRequest = new XMLHttpRequest();
	}
	return xRequest;
}

var sendRequestUrl;
var sendRequestParams;
var sendRequestHttpMethod;
var req;

function sendRequest(url, params, HttpMethod)
{
	if(!HttpMethod)
	{
		HttpMethod = "POST";
	}
	
	sendRequestUrl = url;
	sendRequestParams = params;
	sendRequestHttpMethod = HttpMethod;
	
	//避免提前析构慢速请求对象
	if(req != null && (typeof req) != "undefined")
	{
		setTimeout(sendRequest, 200);
		return;
	}
	
	req = initXMLHTTPRequest();
	
	//设置回调函数
	req.onreadystatechange = onReadyStateChange;
	//设置异步请求
	req.open(sendRequestHttpMethod, sendRequestUrl, true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//按照参数发送异步请求
	req.send(sendRequestParams);
}

function onReadyStateChange()
{
	var ready = req.readyState;
	var data = null;
	var doc = null;
	if(ready == READY_STATE_COMPLETE && req.status == 200)
	{	
		//应答完成，获取应答状态
		data = req.responseText;
		//获取页面的result容器
		var resultpage = document.getElementById('result');
		
		//更新result容器内容
		doc = req.responseXML;
		req = null;
		onLoadImage(doc);
		resultpage.innerHTML = data;
		onResetTimer();
	}
}

function sendRequestImg(url, params, HttpMethod)
{
	if(!HttpMethod)
	{
		HttpMethod = "POST";
	}
	
	sendRequestUrl = url;
	sendRequestParams = params;
	sendRequestHttpMethod = HttpMethod;
	
	//避免提前析构慢速请求对象
	if((typeof req) != "undefined" && req != null)
	{
		setTimeout(sendRequestImg, 300);
	}
	
	req = initXMLHTTPRequest();

	//设置回调函数
	req.onreadystatechange = onReadyStateChangeImg;
	//设置异步请求
	req.open(sendRequestHttpMethod, sendRequestUrl, true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//按照参数发送异步请求
	req.send(sendRequestParams);
}

function onReadyStateChangeImg()
{
	var ready = req.readyState;
	var data = null;
	var doc = null;
	if(ready == READY_STATE_COMPLETE && req.status == 200)
	{	
		data = req.responseText;
		doc = req.responseXML;

		if(doc == null)
		{
			doc = new DOMParser();
			doc = doc.parseFromString(data, "text/xml");
			onLoadImage(doc, 1);
		}
		else
		{
			doc.loadXML(data);
			onLoadImage(doc, 0);
		}

		req = null;
		onResetTimer();
	}
}

function onLoadImage(doc, firefox)
{
}

function onResetTimer()
{
}
