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
                <div class = "condQueryLabelDiv">渠道名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="channelName" name ="channelName">
                </div>
                <div class = "condQueryLabelDiv">开户名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountName" name ="accountName">
                </div>
                <div class = "condQueryLabelDiv">银行卡：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankCard" name ="bankCard">
                </div>
                <div class = "condQueryLabelDiv">提现状态：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="withdrawStatus" name="withdrawStatus" class='text-input medium-input'>
                        <option value="0" selected="selected">===请选择===</option>
                        <option value="1">提现中</option>
                        <option value="2">提现失败</option>
                        <option value="3">提现成功</option>
                    </select>
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">渠道名称</th>
            <th width="150">开户名</th>
            <th width="200">提现金额</th>
            <th width="250">银行卡号</th>
            <th width="150">备注</th>
            <th width="150">提现状态</th>
            <th width="200">说明</th>
            <th width="200">创建时间</th>
            <th width="200">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/withdrawcheck/withdrawcheck.js'></script>
</body>
</html>
