<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>数据列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">开始日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="startCurday" id="startCurday" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" />
                </div>
                <div class = "condQueryLabelDiv">截止日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="endCurday" id="endCurday" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" />
                </div>
                <div class = "condQueryLabelDiv">产品名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="productName" name ="productName">
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
            <th>日期</th>
            <th>产品名称</th>
            <th>单价（单位：元）</th>
            <th>激活数</th>
            <th>收益（单位：元）</th>

        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/sj/yzhao.js'></script>
</body>
</html>
