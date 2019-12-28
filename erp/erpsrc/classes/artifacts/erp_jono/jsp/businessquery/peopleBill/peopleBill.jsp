<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript"src="<%=appRoot%>/jsp/businessquery/peopleBill/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>
<body bgcolor="white" class="claro">

<s:form id="peoplebilllist" onsubmit='
        dojo.byId("peopleBillResult").innerHTML = "<br/><br/><br/><div align=\'center\'>正在查询请等待... <br/><br/><img src=\'../common/img/wait.gif\'> </div>";
        require([ "dojo/_base/xhr" ], function(xhr) {  
        xhr.post({  
            url : "peopleBillListResult.action",
            form : "peoplebilllist",  
            handleAs : "text",  
            load : function(data) {  
              	dojo.byId("peopleBillResult").innerHTML = data;
              	return;
              },  
            error : function(error) {  
              showDojoDialog(error);  
            }  
            });  
         });  
              return false'
              method="post">

<script id = "peopebill_script">
 
require(['custom/FilteringSelect',
         'dojo/store/Memory',
         'dojo/domReady!'
     ],function(FilteringSelect,Memory){
        var peopleStore = new Memory({
        data:[
 	<% int i = 0; %>
 	<c:forEach var="people" items="${createMans}">
 	<% if (i != 0 ) { %> , <% } %>
        		{name:'${people.peopleName}',id:'${people.peopleC}',py:'${people.peoplePinyin}'} 
 	<% i++; %>
 	</c:forEach>
        ]
        });
         var peopleSelect = new FilteringSelect({
         id: "createMan",
         name: "createMan",
         value: '${people.peopleC}',
         store: peopleStore,
         searchAttr: 'py',
         labelAttr: 'id',
         displayValueAttr:'id',
         required: false,
         autoComplete:false,
 	style:'width: 100px; height: 15px;'
         }, "createMan");
 });
 
require(['custom/FilteringSelect',
         'dojo/store/Memory',
         'dojo/domReady!'
     ],function(FilteringSelect,Memory){
        var peopleStore = new Memory({
        data:[
 	<%  i = 0; %>
 	<c:forEach var="people" items="${settleMans}">
 	<% if (i != 0 ) { %> , <% } %>
        		{name:'${people.peopleName}',id:'${people.peopleC}',py:'${people.peoplePinyin}'} 
 	<% i++; %>
 	</c:forEach>
        ]
        });
         var peopleSelect = new FilteringSelect({
         id: "settleMan",
         name: "settleMan",
         value: '${people.peopleC}',
         store: peopleStore,
         searchAttr: 'py',
         labelAttr: 'id',
         displayValueAttr:'id',
         required: false,
         autoComplete:false,
 	style:'width: 100px; height: 15px;'
         }, "settleMan");
 });
 
require(['custom/FilteringSelect',
         'dojo/store/Memory',
         'dojo/domReady!'
     ],function(FilteringSelect,Memory){
        var peopleStore = new Memory({
        data:[
 	<% i = 0; %>
 	<c:forEach var="people" items="${disCurMans}">
 	<% if (i != 0 ) { %> , <% } %>
        		{name:'${people.peopleName}',id:'${people.peopleC}',py:'${people.peoplePinyin}'} 
 	<% i++; %>
 	</c:forEach>
        ]
        });
         var peopleSelect = new FilteringSelect({
         id: "disCurMan",
         name: "disCurMan",
         value: '${people.peopleC}',
         store: peopleStore,
         searchAttr: 'py',
         labelAttr: 'id',
         displayValueAttr:'id',
         required: false,
         autoComplete:false,
 	style:'width: 100px; height: 15px;'
         }, "disCurMan");
 });
 
</script>

<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2" align="center">
			
			<tr valign="middle">
				<td  colspan="7">
					<div align="left">
						<b>根据人员查询单据统计信息</b>
					</div>
				</td>
			</tr>

			<c:set var="now" value="<%=new java.util.Date()%>" />
			<tr valign="middle">
				<td>&nbsp;请选择开始日期：
					<input type="text" class="Wdate" id="startDate" name="startDate"
					value=" <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
				 	 onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:-1});}'})" />
				</td>

				<td>&nbsp;请选择结束日期：
					<input type="text" class="Wdate" id="endDate"	name="endDate"
					value=" <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
					 onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
				</td>

				<td colspan="2"><div align="center">
					<input type="button" onclick="exportXls();" value="导出EXCEL" /> 
					<input type="button" onclick="exportCsv();" value="导出文本" />
				</div>
				</td>
			</tr>
			
			<tr valign="middle">
			
			<td ><label>&nbsp;开台人: 
				<input	id="createMan" name="createMan" />
				</label>
			</td>

			<td ><label>&nbsp;结账人: 
				<input	id="settleMan" name="settleMan" />
				</label>
			</td>

			<td ><label>&nbsp;照单折扣人: 
				<input	id="disCurMan" name="disCurMan" />
				</label>
			</td>
			<td>
				<div align="center">
					<input type="submit" name="Submit" value="查询"/>
				</div>
			</td>
			</tr>
		</table>
	</s:form>

	<div id ="peopleBillResult">
	</div>

</body>
