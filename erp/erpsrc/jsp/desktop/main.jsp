<%@ page contentType="text/html;charset=utf-8" language="java"%>
<HTML>
	<HEAD>
		<TITLE>连锁餐饮ERP系统</TITLE>
		<%@ include file="/jsp/common/jsp/path.jsp"%>
		<script type="text/javascript">
		function browserRedirect() {
			var sUserAgent = navigator.userAgent.toLowerCase();
			var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
			var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
			var bIsMidp = sUserAgent.match(/midp/i) == "midp";
			var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
			var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
			var bIsAndroid = sUserAgent.match(/android/i) == "android";
			var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
			var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
			if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid
					|| bIsCE || bIsWM) {
				console.log(sUserAgent);
				console.log('bIsIpad', bIsIpad);
				console.log('bIsIphoneOs', bIsIphoneOs);
				console.log('bIsMidp', bIsMidp);
				console.log('bIsUc7', bIsUc7);
				console.log('bIsUc', bIsUc);
				console.log('bIsAndroid', bIsAndroid);
				console.log('bIsCE', bIsCE);
				console.log('bIsWM', bIsWM);
				window.location.href = 'mobile/index.jsp';
			}
		}
		//browserRedirect();
		</script>
	</HEAD>
	
	<frameset rows="76,*"  id="frmstTop" frameborder="no" BORDER="0" framespacing="0" border="0">  
	   	<frame src="<%=appRoot %>/desktop/topView.action" name="topFrame" scrolling="no" marginheight="0" marginwidth="1" >
		           <!--24% ,76%-->
		<frameset rows="*" cols="216,*" id="frmstOuter" frameborder="1" framespacing="1" bordercolor="#003300">
			<frame src="<%=appRoot %>/jsp/desktop/nav.jsp" name="navigation" title="menu"  frameborder="0" marginheight="0" marginwidth="1" >
<%-- 			<frame src="<%=appRoot %>/jsp/tab.html" name="tab" frameborder="0" scrolling="auto" marginheight="0" marginwidth="1"> --%>
			<frame src="<%=appRoot %>/jsp/welcome.jsp" name="tab" frameborder="0" scrolling="auto" marginheight="0" marginwidth="1">
		</frameset>
	</frameset>
	
	<noframes></noframes>	
</HTML>

