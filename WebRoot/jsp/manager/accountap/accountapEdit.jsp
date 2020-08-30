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
        .manage-wrap > input[type=checkbox]:checked::after {content: url(${ctxData}images/checkmark.png);display: block;position: absolute;top: -5px;right: 0px;left: -5px}
        .manage-wrap > input[type=checkbox]:focus {outline: none;border-color:#4d90fe;}
        .borderBottom{border-bottom: 1px dashed #e0e0e0;margin-bottom: 10px;padding-bottom: 10px;}
    </style>
</head>
<body>
<div class="col_main">
    <c:if test="${op==2}">
        <div class="formHeadDiv">
            <h2>重置密码</h2>
        </div>
        <div class="formContentDiv">
            <form id="addSupplierForm">
                <ul>
                    <c:set var="dl" value="${account}"/>
                    <input type="hidden" id="id" name="id" value="${dl.id}">
                    <input type="hidden" id="op" name="op" value="2">
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>新密码</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="passWd" name="passWd" value=""	maxlength="240" />
                            <span id="msg"></span>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>确认密码</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="passWd1" name="passWd1" value="" onkeyup="validate()"	maxlength="240" />
                            <span id="msg1"></span>
                        </div>
                    </li>
                    <li>
                        <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                            <input type="submit" class="formBtn" value="修 改" /> <span>
						</span> <input type="reset" class="formBtn" value="重  置" />
                            <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " />
                        </div>
                    </li>
                </ul>
            </form>
        </div>
    </c:if>
    <c:if test="${op==1}">
        <div class="formHeadDiv">
            <h2>编辑账号</h2>
        </div>
        <div class="formContentDiv">
            <form id="addSupplierForm">
                <ul>
                    <c:set var="dl" value="${account}"/>
                    <input type="hidden" id="id" name="id" value="${dl.id}">
                    <input type="hidden" id="op" name="op" value="1">
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>账号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="accountNum" name="accountNum" value="${dl.accountNum}"	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>角色</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="type" name="roleId">
                                <option value="">广告主</option>
                            </select>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">账号昵称</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="acName" name="acName" value="${dl.acName}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">账号联系人</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="acContacts" name="acContacts" value="${dl.acContacts}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">联系电话</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="acPhone" name="acPhone" value="${dl.acPhone}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">备注</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="remark" name="remark" value="${dl.remark}" 	maxlength="240" />
                        </div>
                    </li>
                    <li>
                        <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                            <input type="submit" class="formBtn" value="修 改" /> <span>
						</span> <input type="reset" class="formBtn" value="重  置" />
                            <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " />
                        </div>
                    </li>
                </ul>
            </form>
        </div>
    </c:if>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                accountNum:{
                    required:true,
                    maxlength:20
                },
                passWd:{
                    required:true,
                    maxlength:20
                },
                passWd1:{
                    required:true,
                    maxlength:20,
                    equalTo: "#passWd"
                },
            },
            messages: {
                accountNum:{
                    required : "账号不能为空!",
                    maxlength : "账号长度最多是20个字符!"
                },
                passWd:{
                    required : "新密码不能为空!",
                    maxlength : "新密码长度最多是20个字符!"
                },
                passWd1:{
                    required : "确认密码不能为空!",
                    maxlength : "新密码长度最多是20个字符!",
                    equalTo: "两次密码输入不一致!"
                },
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/accountap/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/accountap/list.do";
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