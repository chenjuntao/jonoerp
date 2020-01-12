<!--界面功能：营业信息显示界面
 *作者：罗腾展
 *时间：2014.6.5
 *具体功能：根据时间获取各个店的营业信息
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="struts"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>营业信息显示界面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<struts:head theme="ajax" />

<style type="text/css">
body,td {
	font-size: 12px;
}
</style>
</head>

<body>
<!-- 清空 session 的计数器 -->
<s:set name="clear" value="%{#session.updatedTimes = 0}"></s:set>

<!-- 要显示页面的 URL -->
<s:url id="businessQueryByTime" action="businessQueryByTime" />

<!-- Ajax主题 -->
<s:div theme="ajax"/>
<!-- Ajax查询表单 -->
<s:form name="businessQueryByTimeActionForm" id="businessQueryByTimeActionForm"><table width="534"   border="1" bgcolor="#E6F1FF">
<table  bgcolor="#E7E7FE">
  <tr>
    <td width="61"  style="font-family: "宋体""><div>
    <s:datetimepicker name="date" displayFormat="yyyy-MM-dd"  label="起始日期"  /></div></td>
	<td>
    <s:datetimepicker name="date" displayFormat="yyyy-MM-dd"  label="截止日期" /></td>
    <td width="96"><s:submit value="查询" href="%{businessQueryByTime}" formId="businessQueryByTimeActionForm"
		theme="ajax" targets="divbusinessQueryByTimeSearch" /></td>
  </tr>

</table></s:form>

<!-- 查询结果显示在这里 -->
<div id="divbusinessQueryByTimeSearch"></div>


</body>
</html>
