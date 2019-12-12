<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>闹铃服务</title>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>

</head>
<body bgcolor="#66563d">

<br/>
<div align="center">
<s:form theme="xhtml">
<span class="bai118">定时时间：</span>
  <input name="alarmForm.alarmTime" type="text" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"><br>
</s:form>
<br/>
</div>

</BODY>
</HTML>
