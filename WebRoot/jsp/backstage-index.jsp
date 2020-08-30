<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fnn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>数据查询系统</title>
<%@ include file="/jsp/manager/common/head-meta.jsp"%>
<%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>



<div id="body" class="body page_user">
	<input id="accountId" type="hidden" value="${ACCOUNT.id}"/>
	<input  id="roleId" type="hidden" value="${ACCOUNT.roleId}"/>
	<div id="main_box" class="main_box cell_layout side_l">
		<%@ include file="/jsp/manager/common/left.jsp"%>
		
		<div class="col_main">
			<%@ include file="/jsp/manager/common/head.jsp"%>
			<iframe id="frame" name="mainFrame" src=""  scrolling="yes" frameborder="0" width="100%" height="90%" background-color="#F1F1F1"></iframe>
		</div>
	</div>
</div>

<script type='text/javascript' charset="utf-8" src='${ctxData}js/system/backstage-index.js'></script>

</body>
</html>