<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>模块列表</title>
<%@ include file="/jsp/manager/common/head-meta.jsp"%>
<%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
	<div class = "condQueryDiv">
		<form id = "condForm">
			<div class = "condQueryCtrl">
				<div class = "condQueryLabelDiv">模块名称：</div>
				<div class="formCtrlDiv">
				     <input type ="text" class ="inputCommonSty" id="name" name ="moduleName">
				</div>
				<div class = "condQueryLabelDiv">模块级别：</div>
				<div class="formCtrlDiv">
				     <select name="level" id="queryLevel" style="padding-top: 3px;padding-bottom: 4px;">
				     	<option value="">全部</option>
				    	<option value="1">一级</option>
				    	<option value="2">二级</option>
				    	<option value="3">三级</option>
				    </select>
				</div>
				<div class="btnBox">
					<div class="searchdiv">
					<input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
				</div>
				<div class = "searchdiv">
					<input type="button" class = "buttonClass imginput addbtn" value="新增模块" style="margin-left: 30px;" >
				</div>
				</div>
			</div>
		</form>
	</div>

	<table class="datatable tables">
	<thead>
		<tr>
			<th width="50">模块名称</th>
			<th width="30">模块类型</th>
			<th width="30">模块级别</th>
			<th width="50">上级模块</th>
			<th width="100">url</th>
			<th width="20">序号</th>
			<th width="20">可分配</th>
			<th >操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

</div>
	
	<div id="show" style="display:none;width:400px;">
		<div class="formHeadDiv">
			<h2>
				添加模块
			</h2>
		</div>
		<div class="formContentDiv" style="padding-right:0px">
			<form id="newFirstStoreForm">
			<input type="hidden" id="moduleId" name="moduleId" />
				<dl>
					<dd style="border-top: none;">
						<div class="formTextDiv" style="width: 100px;">
							<span class="require">*</span>模块名称
						</div>
						<div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
							<input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
								id="moduleName" name="moduleName" />
						</div>
					</dd>
					<dd style="border-top: none;">
						<div class="formTextDiv" style="width: 100px;">
							<span class="require">*</span>模块类型
						</div>
						<div class="formCtrlDiv">
							<select class="formInput" name="type" id="type" onchange="selectModule()">
								<option value="1">系统</option>
								<option value="2">后台</option>
								<%--<option value="3">报表</option>--%>
							</select>
						</div>
					</dd>
					<dd style="border-top: none;">
						<div class="formTextDiv" style="width: 100px;">
							<span class="require">*</span>模块级别
						</div>
						<div class="formCtrlDiv">
							<select class="formInput" name="level" id="level" onchange="selectModule()">
								<option value="1">一级（导航栏）</option>
								<option value="2">二级（主模块）</option>
								<option value="3">三级（子模块）</option>
							</select>
						</div>
					</dd>
					<dd style="border-top: none;">
						<div class="formTextDiv" style="width: 100px;">
							<span class="require">*</span>上级模块
						</div>
						<div class="formCtrlDiv">
							<select class="formInput" name="parentId" id="parentId">
								<option value="0">==请选择==</option>
							</select>
						</div>
					</dd>
					<dd style="border-top: none;">
						<div class="formTextDiv" style="width: 100px;">
							<span class="require">*</span>请求路径
						</div>
						<div class="formCtrlDiv">
							<input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
								id="actionUrl" name="actionUrl" />
						</div>
					</dd>
					<dd style="border-top: none;">
						<div class="formTextDiv" style="width: 100px;">
							<span class="require"></span>序号
						</div>
						<div class="formCtrlDiv">
							<input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
								id="sort" name="sort" />
						</div>
					</dd>
					<dd style="border-top: none;">
						<div class="formTextDiv" style="width: 100px;">
							<span class="require"></span>备注
						</div>
						<div class="formCtrlDiv">
							<input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
								id="remark" name="remark" />
						</div>
					</dd>
						<dd style="border-top: none;">
						<div class="formTextDiv" style="width: 100px;">
							<span class="require">*</span>是否可分配
						</div>
						<div class="formCtrlDiv">
							<select class="formInput" name="isAllowed" id="isAllowed">
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</div>
					</dd>
					<dd style="border-top: none;">
						<div class="formTextDiv"></div>
						<%--<div class="formCtrlDiv">
							-------------------------------------------------------------------------------
						</div>--%>
					</dd>
					<dd style=" height: 60px; line-height: 58px;">
						<div class="formCtrlDiv">
							<span style="margin-left: 100px;">
								<input type="submit" style="background-color: #767DC3" class="formBtn" value="保　存" />
								<input type="reset"  style="background-color: #42425E" class="formBtn" value="重　置" />
								<%--<input type="reset" onClick="javascript :closeDialog('show')" class="formBtn" value=" 返 回 " /> --%>
							</span>
						</div>
					</dd>
				</dl>
			</form>
		</div>
	</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/system/module.js'></script>
</body>
</html>
<style>
	.formContentDiv form .formCtrlDiv {
		margin-left: 10px;
	}
</style>