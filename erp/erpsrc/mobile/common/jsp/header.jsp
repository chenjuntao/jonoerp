<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
    <meta charset="UTF-8">
    <title>连锁餐饮ERP系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

	<%@ include file="/jsp/common/jsp/path.jsp"%>
    <!-- The styles -->
    <link id="bs-css" href="<%=appRoot %>/mobile/framework/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="<%=appRoot %>/mobile/framework/css/charisma-app.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="<%=appRoot %>/mobile/framework/bower_components/jquery/jquery.min.js"></script>
    <script src="<%=appRoot %>/mobile/common/js/menu.js"></script>

  <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="<%=appRoot %>/mobile/framework/img/favicon.ico">
</head>

<body>
	<%@ include file="/mobile/common/jsp/topbar.jsp"%>
<div class="ch-container">
    <div class="row">

        <%@ include file="/mobile/common/jsp/leftmenu.jsp"%>

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->

		<%@ include file="/mobile/common/jsp/topblock.jsp"%>