<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<package name="worker_json" extends="json-default">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>后台</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="<%=basePath %>/css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="59" background="<%=basePath %>/images/top.gif"><table width="99%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="1%" align="center"><img src="<%=basePath %>/images/top.gif" width="557" height="59" border="0" /></td>
        <td width="64%" align="right" style="font-size:12px;vertical-align:bottom;"></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
