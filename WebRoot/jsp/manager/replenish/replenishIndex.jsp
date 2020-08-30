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
                <div class = "condQueryLabelDiv">平台订单：</div>
                <div class="formCtrlDiv">
                <input type ="text" class ="inputCommonSty" id="myTradeNo" name ="myTradeNo">
                </div>
                <div class = "condQueryLabelDiv">商家订单：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeNo" name ="outTradeNo">
                </div>
                <div class = "condQueryLabelDiv">渠道号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="channelId" name ="channelId">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
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
            <th width="150">平台订单</th>
            <th width="150">商家订单</th>
            <th width="100">订单金额</th>
            <th width="100">手续费</th>
            <th width="150">实际金额</th>
            <th width="150">请求状态</th>
            <th width="150">回传参数</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/replenish/replenish.js'></script>
</body>
</html>
