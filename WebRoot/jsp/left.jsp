<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	background-image: url(../images/left.gif);
	 
}

-->
</style>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" href="<%=basePath %>css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>css/invalid.css" type="text/css" media="screen" />
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/facebox.js"></script>
</head>
<script language="JavaScript"  type="text/javascript" >
	if('<s:property value="#session.accountNum"/>'==""||'<s:property value="#session.accountNum"/>'==null){
	   alert("登入时间过长，请重新登入");
	   top.location.href='<%=basePath%>';
	}

function list(idstr,length){
	 var objectobj = $("#subtree"+idstr);
	 
	if($(objectobj).css("display") == "none"){
		$("#div"+idstr).attr("class","nav-top-item current");
		$(objectobj).show();
		for(var i=0;i<length;i++){
			if(i!=idstr){
				$("#div"+i).attr("class","nav-top-item");
		 		$("#div"+i).css("background-image","url('../resources/images/bg-menu-item-green.gif')");
				$("#subtree"+i).hide();	
			}
		}
	}else{
		$("#div"+idstr).attr("class","nav-top-item");
		 $("#div"+idstr).css("background-image","url('../resources/images/bg-menu-item-green.gif')");
		$(objectobj).hide();
	}
}

</SCRIPT>
	
<body>
  		<div id="sidebar">
  			<div id="sidebar-wrapper">
		      <h1 id="sidebar-title"><a href="#">Simpla Admin</a></h1>
		      <a href="#"><img id="logo" src="<%=basePath %>resources/images/logo.png" alt="Simpla Admin logo" /></a>
		      <div id="profile-links"> 您好,<s:property value="#session.accountNum"/><br />
		        <br />
       		  <a href="<%=basePath %>manage/login$backLogin.action" title="Sign Out">退出</a> </div>
		      
		        <br />
		      
		      <ul id="main-nav">
		      	<s:iterator value="modellist" var="p" status = "st">
			        <li  style="DISPLAY: ">
			        	<a href="#" class="nav-top-item" id="div${st.index}" style="padding-right: 15px;" target="mainFrame" onClick="list(${st.index},<s:property value="modellist.size" />);">
			          ${p.pname }    </a>
			          </li>
			        <ul style="display: none;" id="subtree${st.index }">
			         <s:iterator value="cmodellist" var="c">
					 <s:if test="#p.pid == #c.pid">
		            	<li><a href="#" onclick="parent.mainFrame.location.href='<%=basePath %><s:property value="#c.acturl" />'"><s:property value="#c.name" /></a></li>
		            </s:if>
		            </s:iterator>
		          </ul>
			     </s:iterator>
		      </ul>
		    </div>
		</div>
</body>
</html>
