<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>My JSP 'Loginerror.jsp' starting page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<script type="text/javascript">
		function to(){
			alert("没有权限访问，请重新登录");
			window.parent.location.href = "${pageContext.request.contextPath}/login.jsp"
		}
	</script>
  </head>
  
  <body onload="to();">
  	
  </body>
</html>
