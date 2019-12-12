<!--
	Copyright (c) 2006
	National University of Defence Technology
	ChangSha, China
	
	All Rights Reserved
	
	Authors: Hu Jie
	
	$Id: admin_reg.jsp,v 1.18 2012/02/17 17:06:54 HuangJie Exp $
	
	描述：用户注册界面
-->
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib uri="/tags/struts-bean" prefix="bean" %> --%>
<%@ page import="java.util.*"%>
<%@ page import="com.tanry.framework.acl.*"%>

<html>

<head>
<link href="css/usermanager.css" rel="stylesheet" type="text/css">
<title>系统配置 >> 安全管理 >> 用户注册</title>
<script type="text/javascript">
function check()
{
if(document.form1.username.value=="")
  {
    alert("请输入用户名");
    document.form1.username.focus();
    return false;
  }
 else if(document.form1.passwd.value=="")
  {
    alert("请输入密码");
    document.form1.passwd.focus();
    return false;
  }
  else if(document.form1.reigthpasswd.value=="")
  {
    alert("请输入确认密码");
    document.form1.reigthpasswd.focus();
    return false;
  }else if(document.form1.passwd.value!=document.form1.reigthpasswd.value)
  {
    alert("新密码和确认密码不一致!");
    document.form1.reigthpasswd.focus();
    refresh();
    return false;
  }else
  {
    return true;
  }
}

</script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>
<body>
<div>
   <table width="100%" cellspacing="0" cellpadding="1" border="0" class="fillcolor1">
	<tr>
	<td class="label">	<img src="../img/Xbutton39.gif" width="1" align="absmiddle" id="Image1"> 系统配置 >> 安全管理 >> 用户注册</td>
	 </tr>
  </table>
</div>
<br>
<center>
<div class="label1">
  <strong>用户注册  </strong></div>
  <form name="form1" method="post" action="useraccount.action">
    <table width="600" class="label1">
      <tr >
        <td width="600">用 户 名：
          <input type="text" size="25" maxlength="25" name="username"/> (请填写英文或数字,不允许空格) </td>
      </tr>
      <tr >
        <td>用户密码：
          <input type="password" maxlength="25" name="passwd"/>        </td>
      </tr>
      <tr >
        <td>确认密码： 
          <input type="password" maxlength="25" name="reigthpasswd"/>        </td>
      </tr>
      <tr >
        <td>真实姓名：
          <input type="text" name="realname"></td>
      </tr>
      <tr >
        <td>所属部门：
          <input type="text" name="department" size="40"></td>
      </tr>
      <tr >
        <td>联系电话：
          <input type="text" name="phone"></td>
      </tr>
      <tr >
        <td>用户权限：          
          <select name="role">
		  <%
		  	java.util.Vector roles = SecurityContextListener.getAllRoles();
			pageContext.setAttribute("roles", roles);
		  %>
		  <c:forEach var="role" items="${roles}">
		  <option value="${role.role}">${role.desc}</option>
		  </c:forEach>
          </select>
          <input type="hidden" name="method" value="adduser"></td>
      </tr>
    
      <tr>
        <td>
          <div align="center">
            <input type="submit" onClick="check()" value="注册"/>&nbsp;&nbsp;
           <input type="reset" value="重置"/>
        </div></td>
      </tr>
      <tr>
        <td><div align="center">
      
		  <span class="label2">${failure}</span></div></td>
      </tr>
    </table>
  </form>
</center>
</body>
</html>
