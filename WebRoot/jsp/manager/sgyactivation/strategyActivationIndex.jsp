<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>策略-激活-下次联网时间列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "tpDiv">

                </div>

                <div class = "condQueryLabelDiv">渠道号：</div>
                <div class="formCtrlDiv" id = "channelDiv">

                </div>

                <div class = "condQueryLabelDiv">批次号：</div>
                <div class="formCtrlDiv" id = "batchDiv">

                </div>

                <div class = "condQueryLabelDiv">开发者：</div>
                <div class="formCtrlDiv" id = "dpDiv">

                </div>

                <div class = "condQueryLabelDiv">应用：</div>
                <div class="formCtrlDiv" id = "appDiv">

                </div>

                <div class = "condQueryLabelDiv">策略激活名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="activationName" name ="activationName">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增策略激活" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th>渠道名称</th>
            <th>渠道号名称</th>
            <th>批次号名称</th>
            <th>开发者名称</th>
            <th>应用名称</th>
            <th>激活策略名称</th>
            <th>要限制的数量</th>
            <th>已经限制的数量</th>
            <th>是否已完成目标限制</th>
            <th>限制类型</th>
            <th>激活时间间隔（单位：分）</th>
            <th>下次联网时间（单位：分）</th>
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
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/sgyactivation/strategyActivation.js'></script>
</body>
</html>
