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
                <div class = "condQueryLabelDiv">通道：</div>
                <div class="formCtrlDiv" id = "gewayDiv">
                </div>
                <div class = "condQueryLabelDiv">我方支付类型编码：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="myTradeType" name ="myTradeType">
                </div>
                <div class = "condQueryLabelDiv">通道支付类型编码：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeType" name ="outTradeType">
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="添加支付类型" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">归属通道</th>
            <th width="150">我方支付类型名称</th>
            <th width="150">我方支付类型编码</th>
            <th width="150">我方手续费</th>
            <th width="150">通道支付类型名称</th>
            <th width="150">通道支付类型编码</th>
            <th width="150">通道手续费</th>
            <th width="150">限定金额</th>
            <th width="150">限定金额/日</th>
            <th width="150">限定金额/月</th>
            <th width="200">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/gewaytradetype/gewaytradetype.js'></script>
</body>
</html>
