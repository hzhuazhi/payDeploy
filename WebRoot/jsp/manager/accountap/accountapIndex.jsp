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
                <div class = "condQueryLabelDiv">账号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountNum" name ="accountNum">
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <c:if test="${ACCOUNT.roleId==1}">
                    <div class = "searchdiv">
                        <input type="button" class = "buttonClass imginput addbtn" value="新增账号" style="margin-left: 30px;" >
                    </div>
                </c:if>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">账号名称</th>
            <th width="150">角色</th>
            <th width="150">账号昵称</th>
            <th width="150">账号联系人</th>
            <th width="150">联系电话</th>
            <th width="220">备注</th>
            <th width="80">状态</th>
            <th width="380">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/accountap/accountap.js'></script>
</body>
</html>
