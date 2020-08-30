<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>批次列表</title>
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
                <div class = "condQueryLabelDiv">批次号名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="batchName" name ="batchName">
                </div>
                <div class = "condQueryLabelDiv">批次号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="batchNum" name ="batchNum">
                </div>
                <div class = "condQueryLabelDiv">批次类型：</div>
                <div class="formCtrlDiv">
                    <select id="batchType" name="batchType" class='text-input medium-input'>
                        <option value="0" selected="selected">===请选择===</option>
                        <option value="1">自动生成</option>
                        <option value="2">手动录入</option>
                    </select>
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增批次号" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th>批次号名称</th>
            <th>批次号</th>
            <th>所属渠道名称</th>
            <th>所属渠道号名称</th>
            <th>批次号类型</th>
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
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/batch/batch.js'></script>
</body>
</html>
