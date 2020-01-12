<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
    <title>连锁餐饮ERP系统</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type="text/javascript">
		if (window.top != this) { // 终于弄清楚这几句话是做什么用的了：拦截器对所有action进行拦截，让最顶级窗口跳转到登录界面是很有必要的
			alert('登录超时，请重新登录!');
		    window.top.location.href = location.href;
		}
	</script>
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/login/main.js?Version=<%=currenttime %>"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link rel="shortcut icon" href="../favicon.ico"> 
    <link rel="stylesheet" type="text/css" href="<%=appRoot %>/jsp/login/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="<%=appRoot %>/jsp/login/css/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=appRoot %>/jsp/login/css/animate-custom.css" />
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
</head>
<body class="claro">
    <div class="container">        
        <section>				
            <div id="container_demo" >          
                <div id="wrapper">
                    <div id="login" class="animate form">
                        <form id="queryForm"> 
                            <h1>连锁餐饮ERP系统</h1> 
                            <p> 
                                <label for="username" class="uname" data-icon="e">企业编码 </label>
                                <input id="companyId" name="companyId" type="text" onkeydown="if(event.keyCode==13){document.getElementById('username').focus();}"
                                	placeholder="eg. company001"/>
                            </p> 
                            <p> 
                                <label for="username" class="uname" data-icon="u">你的用户名 </label>
                                <input id="username" name="username" type="text" onkeydown="if(event.keyCode==13){document.getElementById('password').focus();}"
                                	placeholder="myusername or mymail@mail.com"/>
                            </p>
                            <p> 
                                <label for="password" class="youpasswd" data-icon="p">你的密码 </label>
                                <input id="password" name="password" type="password" onkeydown="if(event.keyCode==13)login();"
                                 	placeholder="eg. X8df!90EO" /> 
                            </p>
                            <p class="keeplogin"> 
<!-- 								<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" />  -->
<!-- 								<label for="loginkeeping">记住</label> -->
							</p>
				
                            <p class="login button"> 
                                 <input type="button" onclick="login();" style="margin-right: 50px;" value="登录" /> 
                                 <input type="button" onclick="mobileLogin();" value="手机登录" /> 
							</p>
							
							<p class="login button"> 
								<span style="margin-right: 120px;color: rgb(61, 157, 179); font-size: 15px" >温馨提示：推荐使用火狐浏览器</span>
							</p>
                        </form>
                    </div>						
                </div>
            </div>  
        </section>
    </div>
    
    <div id="dlgArea" style="display: none;">
		<select id="branchLst" style="width: 160px;" onkeydown="if(event.keyCode==13)doSelect();">
		</select>
        <input type="button" onclick="doSelect();" style="margin-left: 38px;" value="确定" /> 
    </div>
</body>
</html>