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
                <div class = "condQueryLabelDiv">应用名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="appName" name ="appName">
                </div>
                <div class = "condQueryLabelDiv">应用编号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="appNum" name ="appNum">
                </div>
                <div class = "condQueryLabelDiv">开发者：</div>
                <div class="formCtrlDiv" id = "dpDiv">

                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增应用" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th>应用名称</th>
            <th>应用编号</th>
            <th>appKey</th>
            <th>开发者名称</th>
            <th>应用版本号</th>
            <th>应用下载地址</th>
            <th>应用包名</th>
            <th>启动界面</th>
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
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/app/app.js'></script>
</body>
</html>
