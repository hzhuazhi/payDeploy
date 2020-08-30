<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>应用列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">sdk模块类型：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="moduleType" name="moduleType" class='text-input medium-input'>
                        <option value="0" selected="selected">===请选择===</option>
                        <option value="1">任务管理器</option>
                        <option value="2">更新模块</option>
                        <option value="3">广告模块</option>
                        <option value="4">文件操作</option>
                        <option value="5">获取域名</option>
                    </select>
                </div>
                <div class = "condQueryLabelDiv">sdk显示名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="moduleShowName" name ="moduleShowName">
                </div>
                <div class = "condQueryLabelDiv">sdk名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="moduleName" name ="moduleName">
                </div>
                <div class = "condQueryLabelDiv">版本号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="moduleVer" name ="moduleVer">
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增sdk模块" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th>sdk显示名称</th>
            <th>sdk名称</th>
            <th>版本号</th>
            <th>模块类型</th>
            <th>文件大小（单位：KB）</th>
            <th>唯一值</th>
            <th>下载地址</th>
            <th>备注</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/sdkmodule/sdkmodule.js'></script>
</body>
</html>
