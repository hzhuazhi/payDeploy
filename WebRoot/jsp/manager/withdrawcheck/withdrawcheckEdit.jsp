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
            <h2>审核提现</h2>
        </div>
        <div class="formContentDiv">
            <form id="addSupplierForm">
                <ul>
                    <c:set var="dl" value="${account}"/>
                    <input type="hidden" id="id" name="id" value="${dl.id}">
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >渠道名称</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="channelName" name="channelName" value="${dl.channelName}" disabled maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">开户名</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="subbranchName" name="subbranchName" value="${dl.accountName}" disabled maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">提现金额</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="money" name="money" value="${dl.money}" disabled maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">银行卡号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="bankCard" name="bankCard" value="${dl.bankCard}" disabled 	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">备注</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="remark" name="remark" value="${dl.remark}"  disabled maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>审核状态</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="withdrawStatus" name="withdrawStatus">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.withdrawStatus == 1}">
                                    <option value="1" selected="selected">提现中</option>
                                    <option value="2">提现失败</option>
                                    <option value="3">提现成功</option>
                                </c:if>
                                <c:if test="${dl.withdrawStatus == 2}">
                                    <option value="1">提现中</option>
                                    <option value="2" selected="selected">提现失败</option>
                                    <option value="3">提现成功</option>
                                </c:if>
                                <c:if test="${dl.withdrawStatus == 3}">
                                    <option value="1">提现中</option>
                                    <option value="2">提现失败</option>
                                    <option value="3" selected="selected">提现成功</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">审核说明</span>
                        </div>
                        <div class="formCtrlDiv">
                            <%--<input type="text" class="formInput" id="remark" name="remark"	maxlength="240" />--%>
                            <textarea class="formInput" id="withdrawExplain" name="withdrawExplain"></textarea>
                        </div>
                    </li>

                    <li>
                        <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                            <input type="submit" class="buttonClass imginput" value="修 改" /> <span>
						</span> <input type="reset" class="buttonClass imginput" value="重  置" />
                            <input type="button" onClick="javascript :history.back(-1);" class="buttonClass imginput" value=" 返 回 " />
                        </div>
                    </li>
                </ul>
            </form>
        </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                withdrawStatus:{
                    required:true,
                    maxlength:80
                }
            },
            messages: {
                withdrawStatus:{
                    required : "审核状态不能为空!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/withdrawcheck/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("审核成功！");
                            window.location.href = ctx + "/withdrawcheck/list.do";
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