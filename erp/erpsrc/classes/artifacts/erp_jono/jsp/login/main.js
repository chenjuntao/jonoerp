/**
 * login function
 */
var login = null;
/**
 * mobile login function
 */
var mobileLogin = null;

require([ "dojo", "dojo/request/xhr", "dojo/dom", "dijit/Dialog", "dojo/_base/array", "dojo/dom-construct",
		"dojo/ready" ], function(dojo, xhr, dom, Dialog, array, domConstruct, ready) {
	ready(function() {
		// 默认焦点设置
		dojo.byId('username').focus();
	});

	function validation() {
		var username = dom.byId("username").value;
		var password = dom.byId("password").value;

		if (!username && password) {
			alert("请输入用户名！");
			return false;
		}

		if (username && !password) {
			alert("请输入密码！");
			return false;
		}

		if (!username && !password) {
			alert("请输入用户名和密码！");
			return false;
		}

		return true;
	}

	login = function() {
		if (!validation()) {
			return;
		}

		var _url = appRoot + "/login.action";
		xhr.post(_url, {
			handleAs : "json",
			data : {
				companyId: dom.byId("companyId").value,
				username : dom.byId("username").value,
				password : dom.byId("password").value
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				if (data.isMulti != undefined) {
					showDeptDlg(data.branchLst);
					return;
				}
				redirect();
				if (data.stuta == "less") {
					alert("营业日期小于当前时间，请先日结在操作！")
				}
			} else {
				alert(data.msg);
			}
		}, function(err) {
		});
	}

	var isMobile = false;
	mobileLogin = function() {
		isMobile = true;
		login();
	}

	function redirect() {
		// window.location.href = appRoot + "/index.html";
		// window.location.href = appRoot + "/desktop.action";
		if (isMobile) {
			window.location.href = appRoot + "/m";
			return;
		}
		window.location.href = appRoot + "/desktop/mainView.action";
	}

	var deptDlg = null;
	// 多个部门中选择一个进行登录
	function showDeptDlg(_branchLst) {
		loadSelect('branchLst', _branchLst, 'branchId', 'branchName');

		if (deptDlg == null) {
			deptDlg = new Dialog({
				title : "选择部门",
				style : "width: 300px"
			});

			deptDlg.set("content", dom.byId('dlgArea').innerHTML);// 这里相当于复制
			domConstruct.destroy("dlgArea");// 删除原来的标签，避免出现重复的元素
		}
		deptDlg.show();
	}

	doSelect = function() {
		var _url = appRoot + "/selectDept.action";
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dom.byId('branchLst').value
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				redirect();
			} else {
				alert("系统错误！");
			}
		}, function(err) {
		});
	}

	/**
	 * 装载下拉框数据，数据的键值分开，eg:[{'key' : '1', value : 'test'}]
	 * 
	 * @param id
	 * @param data
	 * @param listKey
	 * @param listValue
	 */
	function loadSelect(id, data, listKey, listValue) {
		// clear options first;
		var select = dojo.byId(id);
		var length = select.options.length;
		for (var i = length - 1; i >= 0; i--) { // 倒序删除，防止移动后造成空项
			select.options[i] = null;
		}

		array.forEach(data, function(item, i) {
			var option = domConstruct.create("option", {
				value : item[listKey],
				innerHTML : item[listValue]
			});
			domConstruct.place(option, id);
		});
	}
});
