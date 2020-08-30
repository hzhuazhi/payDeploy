<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>后台管理系统</title>
<%@ include file="/jsp/manager/common/head-meta.jsp"%>
<%@ include file="/jsp/manager/common/js-meta.jsp"%>
<script type='text/javascript' src='${ctxData}js/plugins/ajaxfileupload.js'></script>
  
<script type='text/javascript'>
$(function(){
	//展开与缩放
	$('.menu-op').toggle(function() {
		$(this).closest('div').find('.menu-child').slideUp();
		$(this).children('img').attr('src', '${ctxData}images/jia_03b.png');
		$(this).attr('title', '展开'); 
	}, function() { 
		$(this).closest('div').find('.menu-child').slideDown();  
		$(this).children('img').attr('src', '${ctxData}images/jian_03b.png');
		$(this).attr('title', '收起');
	});
	
	//选择复选框
	$('.menu-btn').click(function(event) {
		//全选按钮
		if($(this).hasClass('first-menu-btn')){
			if($(this).children(':checkbox').is(':checked')){
				$(this).closest('div').find(':checkbox').attr('checked', false); 
				return;  
			}
			$(this).closest('div').find(':checkbox').attr('checked', true);  
			return; 
		}
		//父级全选按钮
		if($(this).hasClass('second-menu-btn')){
			if($(this).children(':checkbox').is(':checked')){
				$(this).children(':checkbox').attr('checked', false);
				$(this).next().find(':checkbox').attr('checked', false);  
				if ($('.second-menu').find(':checkbox:checked').length == 0) {
					$('.first-menu-btn[parentClass1="'+$(this).attr('parentClass1')+'"]').children(':checkbox').attr('checked', false);
				}
				return;
			} 
			//子级选择
			$(this).children(':checkbox').attr('checked', true);
			$(this).next().find(':checkbox').attr('checked', true);  
			$('.first-menu-btn[parentClass1="'+$(this).attr('parentClass1')+'"]').children(':checkbox').attr('checked', true); 
			return;
		}
		var secondMenu = $('.second-menu-btn[parentClass2="'+$(this).attr('parentClass2')+'"]');
		var firstMenu = $('.first-menu-btn[parentClass1="'+$(this).attr('parentClass1')+'"]');
		//子级单选按钮
		if($(this).hasClass('third-menu-btn')){
			if($(this).children(':checkbox').is(':checked')){
				$(this).children(':checkbox').attr('checked', false);
				if ($(this).closest('div').find(':checkbox:checked').length == 0) {
					$(secondMenu).children(':checkbox').attr('checked', false);
				}
				if ($('.second-menu').find(':checkbox:checked').length == 0) {
					$(firstMenu).children(':checkbox').attr('checked', false);
				}
				return;
			}
//			$(this).children(':checkbox').attr('checked', true);
			if ($('.third-menu').find(':checkbox:checked').length >= 1) {
				$(secondMenu).children(':checkbox').attr('checked', true);
			}
			if ($('.second-menu').find(':checkbox:checked').length >= 1) {
				$(firstMenu).children(':checkbox').attr('checked', true);
			}
		}
	});
});
</script>
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
					<h2>编辑角色</h2>
					<!-- <ul class="fhdNav">
						<li><a class="liSelected" href="#">添加</a></li>
					</ul> -->
				</div>
				<div class="formContentDiv">
					<form id="addSupplierForm">
						<ul>
							<li style="border-top: none;">
								<div class="formTextDiv">
									<span class="require" ><font color="red">*</font>角色类型</span>
								</div>
								<div class="formCtrlDiv">
								    <input type="hidden" id="roleId" name="roleId" value="${role.roleId }">
									<select id="roleType" name="roleType">
										<option value="1" ${role.roleType ==1 ? 'selected': ''}>内部</option>
										<option value="2" ${role.roleType ==2 ? 'selected': ''}>外部</option>
									</select>
								</div>
							</li>
							<li style="border-top: none;">
								<div class="formTextDiv">
									<span class="require" ><font color="red">*</font>角色名称</span>
								</div>
								<div class="formCtrlDiv">
									<input type="text" class="formInput" id="roleName" name="roleName"	maxlength="240" value="${role.roleName }"/>
								</div>
							</li>
							<li style="border-top: none;">
								<div class="formTextDiv">
									<span class="require">备注</span>
								</div>
								<div class="formCtrlDiv">
									<input type="text" class="formInput" id="remark" name="remark"	maxlength="240" value="${role.remark }"/>
								</div>
							</li>
							<li style="border-top: none;">
								<div class="formTextDiv">
									<span class="require"><font color="red">*</font>权限设置</span>
								</div>
								<div class="formCtrlDiv" style="max-width: 700px;">
									<div class="formCtrlDiv  admin_permissions role-menu" style="max-width: 750px;font-size: 0;">
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
																<input type="checkbox" class="menu-chk second-menu-chk ${d2.check ? 'isParentRole' : ''}" sb1="${d2.check}" name="ids" value="${d2.moduleId}" /> ${d2.moduleName}
															</a>
															<div class="third-menu menu-div">
																<c:forEach items="${d2.children}" var="d3">
																	<a class="menu-btn third-menu-btn" parentClass1="fmenu${d1.moduleId}" title="${d3.moduleName}" parentClass2="fmenu${d2.moduleId}">
																		<input type="checkbox" class="menu-chk third-menu-chk ${d3.check ? 'isrole' : ''}" name="ids" value="${d3.moduleId}" /> ${d3.moduleName}
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
								<div class="" style="margin-bottom: 20px; margin-top: 20px;text-align:center;">
									<input type="submit" class="formBtn" value="修 改" /> <span>
									</span> <input type="reset" class="formBtn" value="重  置" />
									<input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " />
								</div>
							</li>
						</ul>
					</form>
				</div>
			</div>
	<script type="text/javascript">
	
	$(document).ready(function(){
		$(".isrole").click();
		var tval=$("#roleType").val();
		if(tval=="1"){
			$(".admin_permissions").show();
			$(".studio_permissions").hide();
			$(".factory_permissions").hide();
		}else if(tval=="2"){
            $(".admin_permissions").show();
            $(".studio_permissions").hide();
            $(".factory_permissions").hide();
//			$(".admin_permissions").hide();
//			$(".studio_permissions").show();
//			$(".factory_permissions").hide();
		}
//		else if(tval=="2"){
//			$(".admin_permissions").hide();
//			$(".studio_permissions").hide();
//			$(".factory_permissions").show();
//		}
		$("#roleType").change(function(){
			var val=$(this).val();
			if(val=="1"){
				$(".admin_permissions").show();
				$(".studio_permissions").hide();
				$(".factory_permissions").hide();
			}else if(val=="2"){
                $(".admin_permissions").show();
                $(".studio_permissions").hide();
                $(".factory_permissions").hide();
//				$(".admin_permissions").hide();
//				$(".studio_permissions").show();
//				$(".factory_permissions").hide();
			}
//			else if(val=="2"){
//				$(".admin_permissions").hide();
//				$(".studio_permissions").hide();
//				$(".factory_permissions").show();
//			}
		});
	});
	
	$(function(){
		
		 // 初始化所有 class 为 numberInput 的表单控件
		 //numberInputBind();
		 $("#addSupplierForm").validate({
			 rules:{
				    roleName:{
			 			required:true,
			 			maxlength:20
			 		},
			 		
			 	},
				messages: {
					roleName:{
			 			required : "角色名称不能为空",
			 			maxlength : "角色名称长度最多是20个汉字或字母"
			 		},
				},
				
				submitHandler : function() {
					var formData = $("#addSupplierForm").serialize();
					 $.ajax({
							url : ctx+ "/system/role/update.do",
							type : 'post',
							dataType : 'json',
							data :formData,
							success : function(data) {
								if (data.success) {
									alert("修改成功！");
									 window.location.href = ctx + "/system/role/list.do"; 
								} else {
									art.alert(data.msg);
								}
							},
							error : function(data) {
								art.alert(data.info);
							}
						}); 
					return false;
					//阻止表单提交
				}
			});
	});
	</script>
</body>
</html>