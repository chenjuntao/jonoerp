<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>user manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/test/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/setting/user/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
	<table class="hovertable" width="100%">
		<tr>
			<td class="label_right" style="width: 100px;">数据数：</td>
			<td class="text_left" style="width: 180px;"><input type="text"
				id="numGoodsBill" name="numGoodsBill" style="width: 80px;" />
			</td>
			<td class="label_right" style="width: 100px;">出错pos：</td>
			<td class="text_left" style="width: 180px;"><input type="text"
				id="numEGoodsBill" name="numEGoodsBill" style="width: 80px;" />
			</td>
			<td class="text_left"><input type="button"
				onclick="doTestGoodsBill();" value="要货单测试" style="margin-left: 8px;" />
			</td>
			<td class="text_left"><input type="button"
				onclick="doDeleteGoodsBill();" value="删除要货单测试数据" style="margin-left: 8px;" />
			</td>
			<td class="text_left"><input type="button"
				onclick="timerBack();" value="定时备份" style="margin-left: 8px;" />
			</td>
		</tr>
		<tr>
			<td class="text_left"><input type="button"
				onclick="doSaveMenu();" value="提取菜单信息到配置文件" style="margin-left: 8px;"/></td>
			<td class="text_left"><input type="button"
				onclick="doTestLoss();" value="报损单测试" style="margin-left: 8px;" />
			</td>
		</tr>
		<tr>
		<td class="label_right" style="width: 80px;">选择日期：</td>
			<td class="text_left"  style="width: 300px;">
						<input type="text" class="Wdate" id="startDate" value="${startDate}" style="width: 120px;"	onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
						<span style="padding: 0 5px;">至</span> 
						<input	type="text" class="Wdate" id="endDate" value="${endDate}"	style="width: 120px;" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
				</td>
				
				<td class="label_right" style="width: 100px;">门店：</td>
					<td class="text_left" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"  style="width: 150px;" id="branchId" headerKey="-1" headerValue="--请选择--" ></s:select>
				</td>
				
			<td class="text_left"><input type="button"
				onclick="doBack();" value="数据恢复" style="margin-left: 8px;" />
			</td>
			
			<td class="text_left"><input type="button"
				onclick="timerBack();" value="定时备份" style="margin-left: 8px;" />
			</td>
			
			<td class="text_left"><input type="button"
				onclick="doTest();" value="测试按钮" style="margin-left: 8px;" />
			</td>
		</tr>
		<tr>
			<td class="text_left"><input type="button"
				onclick="click1();" value="日结测试1" style="margin-left: 8px;" />
			</td><td class="text_left"><input type="button"
				onclick="click2();" value="日结测试2" style="margin-left: 8px;" />
			</td><td class="text_left"><input type="button"
				onclick="click3();" value="日结测试3" style="margin-left: 8px;" />
			</td><td class="text_left"><input type="button"
				onclick="click4();" value="日结测试4" style="margin-left: 8px;" />
			</td><td class="text_left"><input type="button"
				onclick="click5();" value="日结测试5" style="margin-left: 8px;" />
			</td><td class="text_left"><input type="button"
				onclick="click6();" value="日结测试6" style="margin-left: 8px;" />
<!-- 			</td><td class="text_left"><input type="button" -->
<!-- 				onclick="click7();" value="日结测试7" style="margin-left: 8px;" /> -->
			</td><td class="text_left"><input type="button"
				onclick="click8();" value="日结测试8" style="margin-left: 8px;" />
<!-- 			</td><td class="text_left"><input type="button" -->
<!-- 				onclick="click9();" value="日结测试9" style="margin-left: 8px;" /> -->
<!-- 			</td><td class="text_left"><input type="button" -->
<!-- 				onclick="click10();" value="日结测试10" style="margin-left: 8px;" /> -->
			</td>
		</tr>
		
		<tr>
		<td colspan="3">
		<textarea  id="contents" style="width: 580px;" />
		
		</textarea>
		</td>
		</tr>
	</table>
		
</body>

</html>
