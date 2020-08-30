<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String codeId = request.getParameter("id");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<title>文件上传</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/oss-upload/style.css"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body>

<h2>文件上传</h2>
<hr style="border-color: #f1f1f1"size="1">
<br>
<form name=theform>
<input type="radio" name="myradio" value="local_name" checked=true/> 上传文件名字保持本地文件名字
<input type="radio" name="myradio" value="random_name" /> 上传文件名字是随机文件名
<br/>
上传到指定目录:<input type="text" id='dirname' placeholder="如果不填，默认是上传到根目录" size=50 autocomplete="off">
</form>

<h4>您所选择的文件列表：</h4>
<div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>

<br/>
<p id="fileUrl"></p>
<div id="container">
	<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
	<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
</div>

<pre id="console"></pre>

<p>&nbsp;</p>

</body>
<script type="text/javascript" src="<%=basePath%>js/oss-upload/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="<%=basePath%>js/oss-upload/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="<%=basePath%>js/oss-upload/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/oss-upload/lib/base64.js"></script>
<script type="text/javascript" src="<%=basePath%>js/oss-upload/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/oss-upload/upload.js"></script>
</html>
