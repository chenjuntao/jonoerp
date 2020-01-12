<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<style type="text/css">
    table.hovertable {
        font-family: verdana, arial, sans-serif;
        font-size: 11px;
        color: #333333;
        border-width: 1px;
        border-color: #999999;
        border-collapse: collapse;
    }

    table.hovertable th {
        background-color: #c3dde0;
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }

    table.hovertable tr {
        background-color: #d4e3e5;
    }

    table.hovertable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
</style>

<html>
<head>
</head>
<body bgcolor="white">

<div style="width: 100%; height: 100%; overflow: auto;">

    <table class="hovertable" width="100%" border="1" cellpadding="6"
           cellspacing="2" bordercolor="#FEB5AE" bgcolor="#E9EFF5"
           align="center">

        <tr valign="middle">
            <td colspan="4">
                <div align="Left">
                    <b>出品原材料信息</b>
                </div>
            </td>
        </tr>

        <tr valign="middle">
            <th style="text-align: center;">出品名称</th>
            <th style="text-align: center;">原料名称</th>
            <th style="text-align: center;">万元用量</th>
            <th style="text-align: center;">原料单位</th>
        </tr>


        <c:forEach var="item" items="${itemLst}">
				<tr onmouseover="this.style.backgroundColor='#ffff66';"
					onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
					<td style="width: auto;">${item.therapyName}</td>
					<td style="width: auto;">${item.itemName}</td>
					<td style="width: auto;"><fmt:formatNumber type="number"
							maxFractionDigits="2" value="${item.itemCount * amtTTCNY}"/></td>
					<td style="width: auto;">${item.itemDimension}</td>
				</tr>
			</c:forEach>
			</div>
</body>