<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
    <script type='text/javascript' src='${ctxData}js/plugins/ajaxfileupload.js'></script>
    <link rel="stylesheet" type="text/css" href="${ctxData}css/role.css?v=${version}">
    <link rel="stylesheet" type="text/css" href="${ctxData}js/oss-upload/style.css"/>
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
        <h2>编辑应用</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dataModel" value="${appModel}"/>
                <input type="hidden" id="id" name="id" value="${dataModel.id}">
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>开发者</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="dpId" name="dpId">
                            <option value="">===请选择===</option>
                            <c:forEach items="${dpList}" var="dataList">
                                <c:choose>
                                    <c:when test="${dataModel.dpId == dataList.id}">
                                        <option selected="selected" value="${dataList.id}">${dataList.acName}</option>
                                    </c:when>
                                    <c:when test="${dataModel.dpId != dataList.id}">
                                        <option value="${dataList.id}">${dataList.acName}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>appKey</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="appKey" name="appKey" value="${dataModel.appKey}" disabled="disabled" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>应用名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="appName" name="appName" value="${dataModel.appName}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>应用编号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="appNum" name="appNum" value="${dataModel.appNum}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>应用版本号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="appVer" name="appVer" value="${dataModel.appVer}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>应用包名</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="appPackage" name="appPackage" value="${dataModel.appPackage}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">启动界面</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="startBoundary" name="startBoundary" value="${dataModel.startBoundary}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">应用上传</span>
                    </div>
                    <div class="formCtrlDiv">
                        <form name=theform>
                            <input type="radio" name="myradio" value="local_name" checked=true/> 上传文件名字保持本地文件名字
                            <input type="radio" name="myradio" value="random_name" /> 上传文件名字是随机文件名
                            <br/>
                            上传到指定目录:<input type="text" id='dirname' placeholder="如果不填，默认是上传到根目录" size=50 autocomplete="off">
                        </form>
                        <h4>您所选择的文件列表：</h4>
                        <div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>

                        <br/>
                        <p id="fileUrl"></p>
                        <div id="container">
                            <a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
                            <a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
                        </div>

                        <pre id="console"></pre>
                        <div class="formCtrlDiv">
                            应用下载地址：<input type="text" class="formInput" id="textFileUrl" name="appUrl"  value="${dataModel.appUrl}" maxlength="480" />
                        </div>
                        <p>&nbsp;</p>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="remark" name="remark" value="${dataModel.remark}" 	maxlength="240" />
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
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/base64.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/upload.js"></script>
<script type="text/javascript">
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                dpId:{
                    required:true,
                    maxlength:20
                },
                appName:{
                    required:true,
                    maxlength:20
                },
                appNum:{
                    required:true,
                    maxlength:20
                },
                appVer:{
                    required:true,
                    maxlength:20
                },
                appPackage:{
                    required:true,
                    maxlength:80
                },
            },
            messages: {
                dpId:{
                    required : "请选择开发者!"
                },
                appName:{
                    required : "应用名称不能为空!",
                    maxlength : "应用名称长度最多是20个字符!"
                },
                appNum:{
                    required:"应用编号不能为空!",
                    number:"应用编号长度最多是20个字符!"
                },
                appVer:{
                    required:"应用版本号不能为空!",
                    number:"应用版本号长度最多是20个字符!"
                },
                appPackage:{
                    required:"应用包名不能为空!",
                    number:"应用包名长度最多是80个字符!"
                },
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/app/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/app/list.do";
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