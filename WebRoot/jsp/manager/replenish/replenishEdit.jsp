<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>补单</title>
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
        <h2>补单</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dl" value="${account}"/>
                <input type="hidden" id="id" name="id" value="${dl.id}">

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>补单类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="dataType" name="dataType">
                            <option value="0">===请选择===</option>
                            <option value="1">===补下发===</option>
                            <option value="2">===补全套===</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>平台订单</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="gewayName" name="myTradeNo" value="${dl.myTradeNo}"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>商家订单号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outTradeNo" name="outTradeNo" value="${dl.outTradeNo}"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>渠道ID</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="channelId" name="channelId" value="${dl.channelId}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>通道ID</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="gewayId" name="gewayId" value="${dl.gewayId}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>渠道号/商品号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="channel" name="channel" value="${dl.channel}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>交易类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="tradeType" name="tradeType" value="${dl.tradeType}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>商家订单金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="totalAmount" name="totalAmount" value="${dl.totalAmount}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>手续费</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="serviceCharge" name="serviceCharge" value="${dl.serviceCharge}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>实际金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="actualMoney" name="actualMoney" value="${dl.actualMoney}"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>异步通知地址</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="notifyUrl" name="notifyUrl" value="${dl.notifyUrl}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>我方异步通知地址</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="myNotifyUrl" name="myNotifyUrl" value="${dl.myNotifyUrl}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>接口版本</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="interfaceVer" name="interfaceVer" value="${dl.interfaceVer}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>页面跳转同步通知地址</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="returnUrl" name="returnUrl" value="${dl.returnUrl}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>商家回传参数</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="extraReturnParam" name="extraReturnParam" value="${dl.extraReturnParam}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>客户端IP地址</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="clientIp" name="clientIp" value="${dl.clientIp}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>sign签名</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="sign" name="sign" value="${dl.sign}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>提交时间</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="subTime" name="subTime" value="${dl.subTime}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>商品名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="productName" name="productName" value="${dl.productName}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>商品码</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="productCode" name="productCode" value="${dl.productCode}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>实际支付金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="payAmount" name="payAmount" value="" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>订单金额是否与支付金额一致</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="moneyFitType" name="moneyFitType">
                            <option value="0">===请选择===</option>
                            <option value="2">===少了===</option>
                            <option value="3">===多了===</option>
                            <option value="4">===一致===</option>
                        </select>
                    </div>
                </li>

                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="buttonClass imginput" value="补 单" /> <span>
						</span>
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
                dataType:{
                    required:true
                }
            },
            messages: {
                dataType:{
                    required : "请选择补单类型!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/replenish/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("补单成功！！");
                            window.location.href = ctx + "/replenish/list.do";
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