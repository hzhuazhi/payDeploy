<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>数据查询系统</title>
    <%--<%@ include file="/jsp/manager/common/head-meta.jsp"%>--%>
    <script type='text/javascript' src='<%=basePath %>js/common/jquery.js'></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>adminfile/css/login.css">
    <script type="text/javascript">
        if (self != top) {
            top.location = self.location;
        };

    </script>
</head>
<style type="text/css">
    body {
        text-align: center;
        background-image: url(<%=basePath %>adminfile/images/admin-login-bg.jpg);
    }
    #login_log div {
        float: left;
        width: 400px;
    }
</style>
<body>

<div id="login_pan">
    <div class="imgBox"></div>
    <form id="login_form" method="post" action="<%=basePath %>admin/login.do">
        <div class="formBox">
            <p>数据查询系统</p>
            <p>Background management system</p>
            <div id="login_error">${MSG }</div>
            <div class="account_text">
                <input class="login_text" id="accountNum" type="text" name="accountNum"  placeholder="账户" autocomplete="off">
            </div>
            <div class="account_text password_text">
                <input class="login_text" id="passWd" type="password" name="passWd"  placeholder="密码" autocomplete="off">
            </div>
            <div class="login_check">
                <!-- <input type="checkbox" id="check" checked="checked" name="loginRole">&nbsp;<span>记住密码</span>

                <a href="#"><span>忘记密码？</span></a> -->
            </div>
            <input type="button" id="login_btn" value="登     录">
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    $(function() {
        $("#account,#password").keyup(function(event){
            var keycode = event.keyCode;
            if(keycode == 13){
                $("#login_btn").click();
            }
        });
        $("#login_btn").click(function() {
            var $loginform=$("#login_form");
            if($("#accountNum").val()==""){
                $("#login_error").html("用户名不能空");
                return;
            }
            if($("#passWd").val()==""){
                $("#login_error").html("密码不能空");
                return;
            }
            $loginform.submit();
        });
    });
</script>
</html>