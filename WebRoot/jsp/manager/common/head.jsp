<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class = "head">
	<div class="head_box">
		<div class="inner wrp">
			<div class = "topNav">
			<!-- 
				<div class = "navActive"></div>
				<dl class = "topNavItem"> 
				</dl>
				 -->
			</div>
			<div class="account">
				<div id="accountArea" class="account_meta account_inbox account_meta_primary">
				</div>
				<div class="account_meta account_info account_meta_primary">
					<a class="nickname" href="#"> 欢迎,${ACCOUNT.accountNum}  </a>
					<%--<span class="type_wrp">
						<span class="type icon_service_label">${ADMIN.username}</span>
					</span>--%>
					<a href="">
						<img class="avatar" src="<%=basePath %>adminfile/images/usericon.png">
					</a>
				</div>
				
				<div class="account_meta account_logout account_meta_primary">
					<a id="logout" href="<%=basePath %>admin/logout.do">退出</a>
				</div>
			</div>
		</div>
	</div>
</div>