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
                <%--<div class = "condQueryLabelDiv">平台订单：</div>--%>
                <%--<div class="formCtrlDiv">--%>
                <%--<input type ="text" class ="inputCommonSty" id="myTradeNo" name ="myTradeNo">--%>
                <%--</div>--%>
                <div class = "condQueryLabelDiv">商家订单：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeNo" name ="outTradeNo">
                </div>

                <div class = "condQueryLabelDiv">交易状态：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="tradeStatus" name="tradeStatus" class='text-input medium-input'>
                        <option value="-1" selected="selected">=请选择=</option>
                        <option value="0">初始化</option>
                        <option value="1">成功</option>
                        <option value="2">失败</option>
                        <option value="3">其它</option>
                    </select>
                </div>
                <%--<div class = "condQueryLabelDiv">同步状态：</div>--%>
                <%--<div class="formCtrlDiv" id = "runStatusDiv">--%>
                <%--<select id="runStatus" name="runStatus" class='text-input medium-input'>--%>
                <%--<option value="" selected="selected">=请选择=</option>--%>
                <%--<option value="0">初始化</option>--%>
                <%--<option value="1">锁定</option>--%>
                <%--<option value="2">失败</option>--%>
                <%--<option value="3">成功</option>--%>
                <%--</select>--%>
                <%--</div>--%>

                <div class = "condQueryLabelDiv">开始日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayStart" id="curdayStart" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>
                <div class = "condQueryLabelDiv">截止日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayEnd" id="curdayEnd" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayEnd}" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butExcelExport" class = "buttonClass imginput" value = "Excel导出" />
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">

        <thead>
        <div class="formCtrlDiv" id = "totalDiv">

        </div>
        <%--<c:set var="total" value="${total}"/>--%>
        <%--汇总：${total.totalMoney}---${total.totalServiceCharge}---${total.totalActualMoney}--%>
        <tr>
            <th width="150">平台订单</th>
            <th width="150">商家订单</th>
            <th width="100">订单金额</th>
            <th width="100">手续费</th>
            <th width="150">实际金额</th>
            <th width="150">实际支付金额</th>
            <th width="150">请求状态</th>
            <th width="150">交易状态</th>
            <th width="300">交易时间</th>
            <th width="150">回传参数</th>
            <th width="150">补单类型</th>
            <th width="150">同步状态</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/tpDataInfo/tpDataInfo.js'></script>
</body>
</html>
