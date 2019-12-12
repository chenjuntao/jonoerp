require([ "dojox/widget/Standby", "dojo", "dojo/ready" ], function(Standby, dojo, ready) {
	ready(function() {
		// 初始化遮罩层
		standby = new Standby({
			target : "uploadForm"
		});

		document.body.appendChild(standby.domNode);

		standby.startup();
	});
});
function categoryChange() {
	var theType = dojo.byId("type").value;
	if (theType == 1) {
		dojo.byId("tableText").innerHTML = '大家好，我是基本信息表！';
	}
	if (theType == 2) {
		dojo.byId("tableText").innerHTML = '我是半成品信息表';
	}
	if (theType == 3) {
		dojo.byId("tableText").innerHTML = '我是部门信息表';
	}
	if (theType == 4) {
		dojo.byId("tableText").innerHTML = '我是价格组表';
	}
}

function importMaterial() {
	var fileurl = dojo.byId("fileurl").value;
	if (fileurl == "") {
		alert("请选择文件！");
		return;
	}

	if (fileurl.match(/\.(zip)$/i) == null) {
		alert("请选择zip文件包！");
		return;
	}

	var theType = dojo.byId("type").value;
	if (fileurl.indexOf("物料") != -1) {
		if (theType != 1) {
			alert("请选择基本物料信息文件包！");
			return;
		}
	} else if (fileurl.indexOf("半成品") != -1) {
		if (theType != 2) {
			alert("请选择半成品信息文件包！");
			return;
		}
	} else if (fileurl.indexOf("部门") != -1) {
		if (theType != 3) {
			alert("请选择部门信息文件包！");
			return;
		}
	} else if (fileurl.indexOf("价格组") != -1) {
		if (theType != 4) {
			alert("请选择价格组信息文件包！");
			return;
		}
	} else {
		alert("请选择正确的文件包导入！");
		return;
	}

	var _url = appRoot + "/setting/import/doInSystem.action?stuta=" + theType;
	// _url = getUrl(_url);

	require([ "dojo/io/iframe", "custom/Loading" ], function(ioIframe, Mask) {
		var mask = new Mask();
		mask.show();
		ioIframe.send({
			form : "uploadForm",
			url : _url,
			handleAs : "json"
		}).then(function(data) {
			if (data.msg != null && parseInt(data.msg) == 2) {
				alert("导入成功！");
				closeTab(tabId);
			} else if (data.msg != null && parseInt(data.msg) == 0) {
				alert("数据已存在，请不要重复导入！");
				closeTab(tabId);
			} else {
				alert("系统繁忙,请稍后再试！");
				closeTab(tabId);
			}
		}, function(err) {
			alert(err);
		});
	});
}

function exportXls() {
	var _url = appRoot + "/setting/backup/exportSys.action";

	var _params = {};
	var flag = dojo.byId("type").value;
	token = new Date().getTime(); // use the current timestamp as the token
	// value
	dojo.byId('downloadTokenValue').value = token;

	require([ "dojo/io/iframe" ], function(ioIframe) {

		standby.show();
		int = setInterval("isFinished()", 1000);
		ioIframe.send({
			form : "uploadForm",
			url : _url,
			content : {
				flag : flag,
				downloadTokenValue : token
			},
			method : "POST",
			handleAs : "json"
		}).then(function(data) {
			if (data.msg != null && parseInt(data.msg) == 1) {
				alert("系统繁忙，请稍后再试！");
				closeTab(tabId);
			}
		}, function(err) {
			alert(err);
		});
	});
}