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
        <h2>新增提现</h2>
    </div>
    <div class="formContentDiv">
        <h3><font color="red">手续费收取规则：小于2万手续费2元；大于2万手续费5元</font></h3>
        <form id="addSupplierForm">
            <c:set var="tp" value="${tp}"/>
            <li style="border-top: none;">
                <div class="formTextDiv">
                    <c:if test="${tp.roleId == 2}">
                        <span class="require" >渠道名称：<font color="red">${tp.channelName}</font></span>
                    </c:if>
                    <c:if test="${tp.roleId == 3}">
                        <span class="require" >代理名称：<font color="red">${tp.agentName}</font></span>
                    </c:if>

                </div>
                <div class="formTextDiv">
                    <span class="require" >可提现余额：<font color="red">${tp.balance}</font></span>
                    <input type="hidden" class="formInput" id="balance" name="balance" maxlength="240" value="${tp.balance}"/>
                </div>
            </li>



            <ul>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>提现金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="money" name="money" maxlength="240" onblur="countServiceCharge()"/>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >手续费</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="serviceCharge" name="serviceCharge" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>提现到的银行卡</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="bankId" name="bankId" >
                            <option value="">===请选择===</option>
                            <c:forEach items="${bank}" var="dataList">
                                <option value="${dataList.id}">${dataList.bankName}===${dataList.subbranchName}===${dataList.accountName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <%--<input type="text" class="formInput" id="remark" name="remark"	maxlength="240" />--%>
                        <textarea class="formInput" id="remark" name="remark"></textarea>
                    </div>
                </li>


                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="buttonClass imginput" value="添  加" style="background-color: #54D8FE;"/> <span>
						</span> <input type="reset" class="buttonClass imginput" value="重  置" style="background-color: #54D8FE;" />
                        <input type="button" onClick="javascript :history.back(-1);" class="buttonClass imginput" value=" 返 回 " style="background-color: #54D8FE;"/>
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/withdraw/withdraw.js'></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                money:{
                    required:true,
                    maxlength:80
                },
                bankId:{
                    required:true,
                    maxlength:80
                },
            },
            messages: {
                money:{
                    required : "提现金额不能为空!",
                    maxlength : "提现金额长度最多是10个字符!"
                },
                bankId:{
                    required:"提现到的银行卡不能为空!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/withdraw/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/withdraw/list.do";
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