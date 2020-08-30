<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>账号列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">关联名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="linkName" name ="linkName">
                </div>
                <div class = "condQueryLabelDiv">代理：</div>
                <div class="formCtrlDiv" id = "agentDiv">
                </div>
                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "channelDiv">
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="添加关联关系" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="200">关联名称</th>
            <th width="150">代理名称</th>
            <th width="150">渠道名称</th>
            <th width="150">备注</th>
            <th width="250">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/agentchannel/agentchannel.js'></script>
</body>
</html>
