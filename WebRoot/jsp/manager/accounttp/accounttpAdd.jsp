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
    <div class="formHeadDiv">
        <h2>新增账号</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>账号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="accountNum" name="accountNum"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>账号密码</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="passWd" name="passWd"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>角色</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="type" name="roleId">
                            <option value="">渠道</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >归属代理</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="agentId" name="agentId" >
                            <option value="">===请选择===</option>
                            <c:forEach items="${agent}" var="dataList">
                                <option value="${dataList.id}">${dataList.agentName}===${dataList.accountNum}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>渠道名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="channelName" name="channelName"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>渠道号/商铺号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="channel" name="channel"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">公司名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="companyName" name="companyName"	maxlength="240" />
                    </div>
                </li>



                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">联系人</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="contacts" name="contacts"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">联系电话</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="phoneNum" name="phoneNum"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">同步地址</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="lowerUrl" name="lowerUrl"	maxlength="500" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">同步标识</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="lowerSuc" name="lowerSuc"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>是否同步</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="isSynchro" name="isSynchro">
                            <option value="1" selected>需要同步</option>
                            <option value="2" >无需同步</option>
                        </select>
                    </div>
                </li>

                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" value="添  加" style="background-color: #54D8FE;"/> <span>
						</span> <input type="reset" class="formBtn" value="重  置" style="background-color: #54D8FE;" />
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " style="background-color: #54D8FE;"/>
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                accountNum:{
                    required:true,
                    maxlength:20
                },
                passWd:{
                    required:true,
                    maxlength:20
                }
            },
            messages: {
                accountNum:{
                    required : "账号不能为空!",
                    maxlength : "账号长度最多是20个字符!"
                },
                passWd:{
                    required:"账号密码不能为空!",
                    number:"账号密码长度最多是20个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/accounttp/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/accounttp/list.do";
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