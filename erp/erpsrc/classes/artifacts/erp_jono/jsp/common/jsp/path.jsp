<%@ page language="java" pageEncoding="GBK" import="java.sql.*,java.io.*,java.util.*,java.util.Date,java.text.SimpleDateFormat,java.text.*"%> 

<%-- <%=request.getContextPath()%>�ǽ�����·�������⣬�ɷ���վ��ĸ�·�� --%>

<%
	String appRoot = request.getContextPath();
	String dojoBase = appRoot + "/jsp/common/lib";
	long  currenttime = (new Date()).getTime(); 
%>
<script type="text/javascript">
    var appRoot = "<%=appRoot%>";
    var tabId = "${tabId}";
    var currenttime = "<%=currenttime%>";
    var parentTabId = "${parentTabId}";
	var g_companyName = '${companyName}';
</script>