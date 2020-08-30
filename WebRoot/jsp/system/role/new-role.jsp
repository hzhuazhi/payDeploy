<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>后台管理系统</title>
<%@ include file="/jsp/manager/common/head-meta.jsp"%>
<%@ include file="/jsp/manager/common/js-meta.jsp"%>
<script type='text/javascript' src='${ctxData}js/plugins/ajaxfileupload.js'></script>
<link rel="stylesheet" type="text/css" href="${ctxData}css/role.css?v=${version}">
<style type="text/css">
.manage-wrap{background-color: #E2E0DB;display: inline-block;vertical-align: top; font-size: 12px;padding: 0;width: 140px;height: 30px;line-height: 30px;margin: 0 20px 10px 0;}
.manage-wrap > input[type='checkbox']{margin: 0 10px;vertical-align: middle;-webkit-appearance: none;appearance: none;width: 13px;height: 13px;cursor: pointer;background: #fff;border: 1px solid B9BBBE;-webkit-border-radius: 1px;-moz-border-radius: 1px;border-radius: 1px;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;position: relative;}
.manage-wrap > input[type=checkbox]:active{border-color: #c6c6c6;background: #ebebeb;}
.manage-wrap > input[type=checkbox]:checked::after {content: url(${ctx}/images/checkmark.png);display: block;position: absolute;top: -5px;right: 0px;left: -5px}
.manage-wrap > input[type=checkbox]:focus {outline: none;border-color:#4d90fe;}
.borderBottom{border-bottom: 1px dashed #e0e0e0;margin-bottom: 10px;padding-bottom: 10px;}
</style>
</head>
<body>
<div class="col_main">
	<div class="formHeadDiv">
		<h2>新增角色</h2>
	</div>
	<div class="formContentDiv">
		<form id="addSupplierForm">
			<ul>
				<li style="border-top: none;">
					<div class="formTextDiv">
						<span class="require" ><font color="red">*</font>角色类型</span>
					</div>
					<div class="formCtrlDiv">
						<select id="type" name="roleType">
							<option value="1" selected="selected">内部</option>
							<option value="2">外部</option>
						</select>
					</div>
				</li>
				<li style="border-top: none;">
					<div class="formTextDiv">
						<span class="require" ><font color="red">*</font>角色名称</span>
					</div>
					<div class="formCtrlDiv">
						<input type="text" class="formInput" id="roleName" name="roleName"	maxlength="240" />
					</div>
				</li>
				<li style="border-top: none;">
					<div class="formTextDiv">
						<span class="require">备注</span>
					</div>
					<div class="formCtrlDiv">
						<input type="text" class="formInput" id="remark" name="remark"	maxlength="240" />
					</div>
				</li>
				<li style="border-top: none;">
					<div class="formTextDiv">
						<span class="require"><font color="red">*</font>权限设置</span>
					</div>
					<div class="formCtrlDiv" style="max-width: 700px;">
						<div class="formCtrlDiv admin_permissions role-menu" style="max-width: 750px;font-size: 0;">
							<!-- 后台权限 -->
							<c:forEach items="${adminMenu}" var="d1">
								<hr />
								<div class="first-menu menu-div">
									<a class="menu-op open" title="收起"><img src="${ctxData}images/jian_03b.png"/></a>
									<a class="menu-btn first-menu-btn" parentClass1="fmenu${d1.moduleId}" title="${d1.moduleName}">
										<input type="checkbox" class="menu-chk first-menu-chk" name="ids" value="${d1.moduleId}" /> ${d1.moduleName}
									</a>
									<div class="menu-child" style="display: inline-block;">
										<c:forEach items="${d1.children}" var="d2"> 
											<div class="second-menu menu-div">
												<a class="menu-btn second-menu-btn" parentClass1="fmenu${d1.moduleId}" title="${d2.moduleName}" parentClass2="fmenu${d2.moduleId}" >
													<input type="checkbox" class="menu-chk second-menu-chk" name="ids" value="${d2.moduleId}" /> ${d2.moduleName}
												</a>
												<div class="third-menu menu-div">
													<c:forEach items="${d2.children}" var="d3">
														<a class="menu-btn third-menu-btn" parentClass1="fmenu${d1.moduleId}" title="${d3.moduleName}" parentClass2="fmenu${d2.moduleId}">
															<input type="checkbox" class="menu-chk third-menu-chk" name="ids" value="${d3.moduleId}" /> ${d3.moduleName}
														</a>
													</c:forEach>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
				</li>
				<li>
					<div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
						<input type="submit" class="formBtn" value="添  加" /> <span>
						</span> <input type="reset" class="formBtn" value="重  置" />
						<input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " />
					</div>
				</li>
			</ul>
		</form>
	</div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/system/new-role.js'></script>
</body>
</html>