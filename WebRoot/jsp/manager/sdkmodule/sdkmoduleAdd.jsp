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
        <h2>新增sdk模块</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>sdk显示名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="moduleShowName" name="moduleShowName" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>sdk名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="moduleName" name="moduleName" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>模块版本号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="moduleVer" name="moduleVer" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>模块类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="moduleType" name="moduleType" class='text-input medium-input'>
                            <option value="" selected="selected">===请选择===</option>
                            <option value="1">任务管理器</option>
                            <option value="2">更新模块</option>
                            <option value="3">广告模块</option>
                            <option value="4">文件操作</option>
                            <option value="5">获取域名</option>
                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>模块上传</span>
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
                            模块下载地址：<input type="text" class="formInput" id="textFileUrl" name="moduleUrl" maxlength="480" />
                        </div>

                        <div class="formCtrlDiv">
                            文件大小：<input type="text" class="formInput" id="textFileSize" name="fileSize"  maxlength="480" />
                        </div>
                        <p>&nbsp;</p>
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
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/base64.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctxData}js/oss-upload/upload.js"></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                moduleShowName:{
                    required:true,
                    maxlength:80
                },
                moduleName:{
                    required:true,
                    maxlength:30
                },
                moduleVer:{
                    required:true,
                    maxlength:10
                },
                moduleType:{
                    required:true
                },
                moduleUrl:{
                    required:true,
                    maxlength:255
                },
                fileSize:{
                    required:true,
                    maxlength:8
                },
            },
            messages: {
                moduleShowName:{
                    required : "sdk显示名称不能为空!",
                    maxlength : "sdk显示名称长度最多是80个字符!"
                },
                moduleName:{
                    required : "sdk名称不能为空!",
                    maxlength : "sdk名称长度最多是30个字符!"
                },
                moduleVer:{
                    required:"模块版本号不能为空!",
                    maxlength:"模块版本号长度最多是10个字符!"
                },
                moduleType:{
                    required:"请选择模块类型!"
                },
                moduleUrl:{
                    required:"模块下载地址不能为空!",
                    maxlength:"模块下载地址长度最多是255个字符!"
                },
                fileSize:{
                    required:"文件大小不能为空!",
                    maxlength:"文件大小长度最多是8个字符!"
                },
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/sdkmodule/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/sdkmodule/list.do";
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