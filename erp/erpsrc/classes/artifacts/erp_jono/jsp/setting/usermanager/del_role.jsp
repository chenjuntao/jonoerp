<!--
	Copyright (c) 2006
	National University of Defence Technology
	ChangSha, China
	
	All Rights Reserved
	
	Authors: Hu Jie
	
	$Id: del_role.jsp,v 1.6 2007/01/08 11:41:01 XuJingYu Exp $
	
	描述：删除用户角色界面
-->
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib uri="/tags/struts-bean" prefix="bean" %> --%>
<%@ page import="logic.module.setting.*"%>
<%@ page import="com.tanry.framework.acl.*"%>
<%@ page import="action.setting.*"%>
<%@ page import="java.util.*"%>

<html>
<head>
<link href="css/usermanager.css" rel="stylesheet" type="text/css">
<title>系统配置 >> 安全管理 >> 删除用户角色</title>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>
<body bgcolor="#ffffff" class="label1">
<div>
   <table width="100%" cellspacing="0" cellpadding="1" border="0" class="fillcolor1">
	<tr>
	<td class="label">	<img src="../img/Xbutton39.gif" width="1" align="absmiddle" id="Image1"> 系统配置 >> 安全管理 >> 删除用户角色</td>
	 </tr>
  </table>
</div>
<br>
<center>
<div class="label2">
  删除用户角色</div>
 <form name="form1" method="get" action="useraccount.action">

    <table width="309" height="129">
      <tr class="label2">
        <td width="284" height="24">  
		  <%
		  	//java.util.Vector userInfos = setting.userRoleBean.getUsersInfo("*");
		  //String listuser = setting.userAccountBean.getusername();
			//pageContext.setAttribute("username",username);
			 %>
			         
 用 户 名: <input name="username" type="hidden"  maxlength="26" value="${param.username}">
 <span class="STYLE1">          ${param.username}</span>	</td>
      </tr>
     
      <tr class="label2">
        <!--<td>用户角色：  <input name="role" type="hidden"  maxlength="26" value="${role.role}">
          ${role.role}        
          </td>-->
		  <td>用户角色：          
          <select name="role1">
		  <%
		  	java.util.Vector roles = SecurityContextListener.getAllRoles();
			pageContext.setAttribute("roles", roles);
		  %>
		  <c:forEach var="role" items="${roles}">
		  <option value="${role.role}">${role.desc}</option>
		  </c:forEach>
          </select></td>
      </tr>
    
      <tr>
        <td>
          <div align="center">
            <input type="submit" value="确定删除"/>&nbsp;&nbsp;
           <!-- <input type="reset"  onClick="javascript:window.close(this);" value="取消"/>      -->  
        </div></td>
      </tr>
      <tr>
        <td>
          <input type="hidden" name="method" value="delrole"> 
		  <span class="label2">${failure}${success}</span></td>
      </tr>
    </table>
  </form>
</center>
</body>
</html>
