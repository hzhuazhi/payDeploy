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

                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="ch" name="ch" class='text-input medium-input'>
                        <option value="-1" selected="selected">=请选择=</option>
                        <option value="0" selected>hrqp</option>
                        <option value="1">成功</option>
                        <option value="2">失败</option>
                        <option value="3">其它</option>
                    </select>
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
            </div>
        </form>
    </div>

    <table class="datatable tables">

        <thead >
        <div class="dataTables_length" id="DataTables_Table_0_length">
            成功率：<span><font color="red">74.3%</font></span>  掉单率：<span><font color="red">2.23%</font></span>
            <label style="float:right;" >显示 <select name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class=""><option value="10">10</option><option value="25" selected>25</option><option value="50">50</option><option value="100">100</option></select> 项结果</label>
        </div>
        <tr>
            <th width="150">平台订单</th>
            <th width="150">商家订单</th>
            <th width="100">订单金额</th>
            <th width="100">手续费</th>
            <th width="150">实际金额</th>
            <th width="150">请求状态</th>
            <th width="150">交易状态</th>
            <th width="300">交易时间</th>
            <th width="150">回传参数</th>
            <th width="150">同步状态</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr role="row" class="odd">
            <td>2020061523595100001</td><td>a550d9a5b8104debb5b5da3c52fe2aa2</td><td>80.00</td><td>0.042</td><td>76.64</td><td><span><font color="red">失败</font></span></td><td><span>初始化</span></td><td>2020-06-15 23:59:51.0</td><td></td><td><span>初始化</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523593200002</td><td>29c051e85f7f4d1a90f390970ac2c196</td><td>200.00</td><td>0.042</td><td>191.6</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 23:59:32.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523582900003</td><td>b2d8f00d02f74453afb2fca47067d220</td><td>100.00</td><td>0.042</td><td>95.8</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 23:58:29.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523580200001</td><td>6c0ec5c954164c4789e251843b5c0342</td><td>80.00</td><td>0.042</td><td>76.64</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:58:02.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523575100001</td><td>910641ef6fcd4e43935794b2595530d5</td><td>50.00</td><td>0.042</td><td>47.9</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:57:51.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523572400001</td><td>7611253da2294812bd91ce7dda5e4fca</td><td>200.00</td><td>0.042</td><td>191.6</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:57:24.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523564600001</td><td>f7e7865e30434c199745cb91a9fe1272</td><td>400.00</td><td>0.042</td><td>383.2</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:56:46.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523560300001</td><td>2a3227edd2f2406fbe11641893d4aa4b</td><td>80.00</td><td>0.042</td><td>76.64</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:56:03.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523552900002</td><td>1583d556eb134678b1f36f8d8eb9ed86</td><td>500.00</td><td>0.042</td><td>479</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 23:55:29.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523545200001</td><td>8ee33a44f0e54457983b579930185348</td><td>500.00</td><td>0.042</td><td>479</td><td><span><font color="red">失败</font></span></td><td><span>初始化</span></td><td>2020-06-15 23:54:52.0</td><td></td><td><span>初始化</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523531300001</td><td>707d1c063cc24768befc2d2ff81c0a6b</td><td>100.00</td><td>0.042</td><td>95.8</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:53:13.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523515100001</td><td>14d4b0a6f7e24143bfad8a59ff510689</td><td>80.00</td><td>0.042</td><td>76.64</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 23:51:51.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523504500001</td><td>7b5919f87f4542869a2641d6fcae486e</td><td>2000.00</td><td>0.042</td><td>1916</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:50:45.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523500500001</td><td>255a77726e4148c4a3d1c92ecdacb4e8</td><td>100.00</td><td>0.042</td><td>95.8</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:50:05.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523495300001</td><td>80ae1d8779b8416b9d98dc876dffa580</td><td>300.00</td><td>0.042</td><td>287.4</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:49:53.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523492400001</td><td>3372ae020a3249ee852e8f3daaf56d6e</td><td>80.00</td><td>0.042</td><td>76.64</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:49:24.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523481800001</td><td>ed730333051e488895bf57344fb92824</td><td>100.00</td><td>0.042</td><td>479</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 23:48:18.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523475100001</td><td>248b315886ef4df19677de8261cb3c57</td><td>50.00</td><td>0.042</td><td>47.9</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 21:59:11.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523471600001</td><td>4dc2c30733f448aea13d6e9f9678da93</td><td>500.00</td><td>0.042</td><td>479</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 21:59:06.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523463300001</td><td>42784dc8472b4ceaa303a5d88a3f6106</td><td>300.00</td><td>0.042</td><td>287.4</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 21:59:03.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523455600001</td><td>1b72bfd629c841a38ecf97f87c2d6933</td><td>100.00</td><td>0.042</td><td>95.8</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 21:58:56.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523445300001</td><td>b3989114b8584e29b8ddaf7a6da06a31</td><td>500.00</td><td>0.042</td><td>479</td><td>成功</td><td><span>成功</span></td><td>2020-06-15 21:58:50.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523424300001</td><td>b44fbd302c134c1592f43a6463f6394d</td><td>300.00</td><td>0.042</td><td>287.4</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 21:58:44.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="even">
            <td>2020061523413500001</td><td>f4369e92c2e6435fa6bdcb483c8ab21d</td><td>50.00</td><td>0.042</td><td>47.9</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 21:58:35.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
        <tr role="row" class="odd">
            <td>2020061523413300001</td><td>fcd8743df3f046dfa0f3aca0cb627fbc</td><td>200.00</td><td>0.042</td><td>191.6</td><td><span>成功</span></td><td><span>成功</span></td><td>2020-06-15 21:58:33.0</td><td></td><td><span>成功</span></td><td>正常</td>
        </tr>
    </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<%--<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/tpDataInfo/tpDataInfo.js'></script>--%>
</body>
</html>
