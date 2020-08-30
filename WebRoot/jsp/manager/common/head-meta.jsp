<%@ page import="org.springframework.web.util.WebUtils" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="version" value="2" />
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<%
    String ctx_path = request.getContextPath();
    String ctx_basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctx_path+"/";
    String ctx_basePath_ = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ctx_path;
    String accountNum = (String) WebUtils.getSessionAttribute(request, "accountNum");
%>
<c:set var="ctxData" value="<%=ctx_basePath %>"></c:set>
<c:set var="ctxDataGd" value="<%=ctx_basePath_ %>"></c:set>
<link id="cssStyle" href="${ctxData}adminfile/css/demo_page.css?v=${version}" rel="stylesheet" type="text/css" />
<link id="cssStyle" href="${ctxData}adminfile/css/demo_table.css?v=${version}" rel="stylesheet" type="text/css" />
<link id="cssStyle" href="${ctxData}adminfile/css/layout.css?v=${version}" rel="stylesheet" type="text/css" />
<link id="cssStyle" href="${ctxData}adminfile/css/wysiwyg.css?v=${version}" rel="stylesheet" type="text/css" />
<link id="cssStyle" href="${ctxData}adminfile/icons/iconfont.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${ctxData}css/pagebar.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctxData}adminfile/css/icon_style.css?v=${version}">
<link rel="stylesheet" type="text/css" href="${ctxData}adminfile/css/base.css?v=${version}">
<link rel="stylesheet" type="text/css" href="${ctxData}adminfile/css/pagination_style.css?v=${version}">
<link rel="stylesheet" type="text/css" href="${ctxData}adminfile/css/lb7e_style.css?v=${version}">
<link rel="stylesheet" type="text/css" href="${ctxData}adminfile/css/page_user_style.css?v=${version}">
<link rel="stylesheet" type="text/css" href="${ctxData}css/plugins/jquery.dataTables.css?v=${version}">
<link rel="stylesheet" type="text/css" href="${ctxData}css/tooltip.css?v=${version}">


<!--[if lt IE 8]>
<link rel="stylesheet" type="text/css" href="${ctxData}adminfile/css/ie7common.css">
<![endif]-->
<link rel="stylesheet" type="text/css" href="${ctxData}css/plugins/artDialog.css">
<link href="${ctxData}css/plugins/jquery.datetimepicker.css?v=${version}" rel="stylesheet" type="text/css" />
<script> var ctx = '${ctxDataGd}';</script>
<script language="JavaScript"  type="text/javascript">
    var accountNumStr = <%=accountNum %>;
    if(accountNumStr == "" || accountNumStr == null){
        alert("Login timeout, please login again!");
        top.location.href= '<%=ctx_basePath %>';
    }
</script>
<input type="hidden" id="excDataHid" value="${ctxData}" />
