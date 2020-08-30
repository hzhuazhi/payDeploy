<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fnn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col_side">
	<h3 style="text-align: center;margin-top: 30px;margin-bottom: 20px;color: white">数据查询系统</h3>
	<div class="menu_box" id="menuBar">
	<c:forEach var="menuOne" items = "${menuList}">
		<c:forEach var="menuTwo" items="${menuOne.children}" varStatus="status" >
			<dt class="menu_title" id="usermanager" menucode="wangzhan-guanliyuan">
				<span class = "usermanager_span_websitenotice">&nbsp;</span> 用户信息
				<span class = "openCloseIcon"></span>
			</dt>
			<c:forEach var ="menuThree" items="${menuTwo.children}" varStatus = "status">
				<dd class="menu_item" id="listUser" menucode="wangzhan-guanliyuan-liebiao">
					<a href="${ctx}${menuThree.actionUrl}" target="mainFrame">${menuThree.moduleName}</a>
				</dd>
			</c:forEach>
		</c:forEach>
	</c:forEach>
	</div>
</div>


