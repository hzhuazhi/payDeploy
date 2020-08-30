<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台</title>
<link rel="stylesheet" href="${ctxData}resources/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctxData}resources/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctxData}resources/css/invalid.css" type="text/css" media="screen" />
<STYLE type="text/css">
.milky {
  font-family:"经典圆体繁", "microsoft yahei", "Arial Rounded MT Bold", "Helvetica Rounded", Arial, sans-serif;;
  display: block;
  font-size: 80px;
  color: #f1ebe5;
  text-shadow: 0 8px 9px #c4b59d, 0px -2px 1px #fff;
  font-weight: bold;
  letter-spacing: 0px;
  text-align: center;
  position: absolute;
  padding: 100px 50px;
  top: 30%;
  left: 50%;
  transform: translate(-50%,-50%);
  border-radius: 20px;
}
</STYLE>
</head>

<body>
	<div class="milky">
               欢迎使用
    </div>
</body>
</html>
