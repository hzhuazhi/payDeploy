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
        <h2>新增策略-联网时间</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >渠道</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="tpId" name="tpId" onchange="jsMethod.queryChannelAll()">
                            <option value="">===请选择===</option>
                            <c:forEach items="${tpList}" var="dataList">
                                <option value="${dataList.id}">${dataList.acName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>渠道号名称</span>
                    </div>
                    <div class="formCtrlDiv" id = "channelDiv">
                        <select id="channelId" name="channelId" onchange="jsMethod.queryBatchAll()">
                            <option value="">===请选择===</option>
                            <c:forEach items="${chList}" var="dataList">
                                <option value="${dataList.id}">${dataList.channelName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>批次号名称</span>
                    </div>
                    <div class="formCtrlDiv" id = "batchDiv">
                        <select id="batchId" name="batchId">
                            <option value="">===请选择===</option>
                            <c:forEach items="${bchList}" var="dataList">
                                <option value="${dataList.id}">${dataList.batchName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >开发者</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="dpId" name="dpId" onchange="jsMethod.queryAppAll()">
                            <option value="">===请选择===</option>
                            <c:forEach items="${dpList}" var="dataList">
                                <option value="${dataList.id}">${dataList.acName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>应用名称</span>
                    </div>
                    <div class="formCtrlDiv" id = "appDiv">
                        <select id="appId" name="appId">
                            <option value="">===请选择===</option>
                            <c:forEach items="${appList}" var="dataList">
                                <option value="${dataList.id}">${dataList.appName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>策略激活名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="activationName" name="activationName" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >要限制的数量</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="limitNum" name="limitNum" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>激活时间间隔（单位：分钟）</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="activationTime" name="activationTime" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>下次联网时间（单位：分钟）</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="nextTime" name="nextTime" maxlength="240" />
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
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/sgyactivation/strategyActivation.js'></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                channelId:{
                    required:true
                },
                batchId:{
                    required:true
                },
                appId:{
                    required:true
                },
                activationName:{
                    required:true,
                    maxlength:80
                },
                limitNum:{
                    digits:true,
                    max:10000000
                },
                activationTime:{
                    required:true,
                    digits:true,
                    max:1000
                },
                nextTime:{
                    required:true,
                    digits:true,
                    max:1000
                },
            },
            messages: {
                channelId:{
                    required : "请选择渠道号名称!"
                },
                batchId:{
                    required : "请选择批次号名称!",
                },
                appId:{
                    required : "请选择应用名称!",
                },
                activationName:{
                    required:"策略激活名称不能为空!",
                    maxlength:"策略激活名称长度最多是80个字符!"
                },
                limitNum:{
                    digits:"必须输入正整数!",
                    max:"最大值不能超过10000000!"
                },
                activationTime:{
                    digits:"必须输入正整数!",
                    max:"最大值不能超过1000!"
                },
                nextTime:{
                    digits:"必须输入正整数!",
                    max:"最大值不能超过1000!"
                },
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/sgyactivation/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/sgyactivation/list.do";
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