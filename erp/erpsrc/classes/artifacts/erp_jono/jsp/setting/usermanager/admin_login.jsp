<!--
	Copyright (c) 2006
	ChangSha, China
	
	All Rights Reserved
	
	Authors: Hu Jie
	
	$Id: admin_login.jsp,v 1.7 2006/10/20 03:02:42 WuPeng Exp $
	
	描述：监控子系统用户登录界面
-->
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>监控子系统用户登录</title>
<script type="javascript">
function check()
{
  if(document.form1.userName.value=="")
  {
    alert("请输入用户名");
    document.form1.userNamefocus();
    return false;
  }else if(document.form1.userPwd.value=="")
  {
    alert("请输入密码");
    document.form1.userPwd.focus();
    return false;
  }
  {
    return true;
  }
}
</script>
<style type="text/css">
<!--
.STYLE2 {
	font-size: 36px;
	color: #003300;
	font-weight: bold;
}
-->
</style>
</head>

<body bgcolor="#C8DDC6">
<div>
   <table width="100%" cellspacing="0" cellpadding="1" border="0" class="fillcolor1">
	<tr>
	<td class="label">	<img src="../img/Xbutton39.gif" width="1" align="absmiddle" id="Image1"> 系统配置 >> 安全管理 >> 用户登陆</td>
	 </tr>
  </table>
</div>
<center>
<table width="685" height="252" border="0" cellpadding="0" cellspacing="0" background="login2.5.gif">
  <tr>
    <td height="73" colspan="2"><div align="center"><span class="STYLE2">系统管理界面登录</span></div></td>
    </tr>
  <form name="form1" method="post" action="UserLogin.action">
    <br> 
	         
        <tr><td >  <table width="300" border="0" cellspacing="1" cellpadding="1" align="center">
          <br>
            <tr> 
              <td width="109" height="27" align="right">用户名：</td>
              <td width="184"> 
              <input type="text" name="userName" size="15" maxlength="25">              </td>
            </tr>
            <tr> 
              <td height="41" align="right">密 &nbsp;码：</td>
              <td> 
                <input type="password" name="userPwd" size="15" maxlength="20">              </td>
            </tr>
            <tr> 
              <td colspan="2" align="center"> 
                <input type="submit" name="Submit" value="登录">
                &nbsp;  &nbsp; 
                <input type="reset" name="Submit2" value="取消">              </td>
            </tr>
             <tr>     
           	<td height="10" colspan="2" align="center">      		</td>
   	    </tr>
         </table></td></tr>
</form>
</table>
</center>
</body>
</html>
