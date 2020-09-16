
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/tpDataInfo/list.do',
        dataList_url : ctx + "/tpDataInfo/dataList.do",
        // add_url : ctx+ "/tpDataInfo/add.do",
        // update_url : ctx+ "/tpDataInfo/update.do",
        // queryId_url: ctx+ "/tpDataInfo/getId.do",
        // delete_url: ctx+ "/tpDataInfo/delete.do",
        manyOperation_url: ctx+ "/tpDataInfo/manyOperation.do",
        exportData_url : ctx +  "/tpDataInfo/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"totalAmount",},
        {"data":"serviceCharge",},
        {"data":"actualMoney",},
        {"data":"payAmount",},
        {"data":"sendOk",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.sendOk==1){
                    html='<span>成功</span>';
                }else if(oData.sendOk==2){
                    // html='<span><font color="red">失败</font></span>';
                    html='<span>放弃</span>';
                }
                $(nTd).html(html);
            }
        },

        {"data":"tradeStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.tradeStatus==0){
                    html='<span>初始化</span>';
                }else if(oData.tradeStatus==1){
                    html='<span>成功</span>';
                }else if(oData.tradeStatus==2){
                    // html='<span><font color="red">失败</font></span>';
                    html='<span>放弃</span>';
                }else if(oData.tradeStatus==3){
                    html='<span>其它</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"tradeTime",},
        {"data":"extraReturnParam",},
        {"data":"replenishType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.replenishType==0){
                    html='<span>不是补单</span>';
                }else if(oData.replenishType==1){
                    html='<span>不是补单</span>';
                }else if(oData.replenishType==2){
                    html='<span><font color="red">是补单</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"runStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.runStatus==0){
                    html='<span>初始化</span>';
                }else if(oData.runStatus==1){
                    html='<span>锁定</span>';
                }else if(oData.runStatus==2){
                    html='<span><font color="red">失败</font></span>';
                }else if(oData.runStatus==3){
                    html='<span>成功</span>';
                }
                $(nTd).html(html);
            }
        },



        {"data":"dataCoreId",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                if (oData.runStatus == 2){
                    isEnableHtml = '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.dataCoreId+'"  directValue="2" href = "javascript:void(0);">重发 </a>';
                }else{
                    isEnableHtml = '正常';
                }
                html = isEnableHtml;
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        outTradeNo:null,
        replenishType:0,
        sendOk:0,
        tradeStatus:-1,
        runStatus:0,
        curdayStart:0,
        curdayEnd:0,
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        this.queryTotal();
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['replenishType'] = $("#replenishType").val();
            account.condJsonData['sendOk'] = $("#sendOk").val();
            account.condJsonData['tradeStatus'] = $("#tradeStatus").val();
            account.condJsonData['runStatus'] = $("#runStatus").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            $("#myTradeNo").val("");
            account.condJsonData['outTradeNo'] = "";
            $("#outTradeNo").val("");
            account.condJsonData['replenishType'] = "0";
            $("#replenishType").val("0");
            account.condJsonData['sendOk'] = "-1";
            $("#sendOk").val("-1");
            account.condJsonData['tradeStatus'] = "0";
            $("#tradeStatus").val("0");
            account.condJsonData['runStatus'] = "0";
            $("#runStatus").val("0");
            account.condJsonData['curdayStart'] = "";
            $("#curdayStart").val("");
            account.condJsonData['curdayEnd'] = "";
            $("#curdayEnd").val("");
            common.showDatas(account.condJsonData,account.list);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id
            }
            common.cf(data);
        });

        // 数据按照Excel格式导出
        $("#butExcelExport").click(function () {
            common.dataExportExcel($("#condForm"));
        });
    },

    // //汇总数据填充
    // //查询所有订单汇总数据
    // queryTotal:function(){
    //     var url = basePath + "tpdata/totalData.do";
    //     var myTradeNo = $("#myTradeNo").val();
    //     var outTradeNo = $("#outTradeNo").val();
    //     var tradeStatus = $("#tradeStatus").val();
    //     var runStatus = $("#runStatus").val();
    //     var curdayStart = $("#curdayStart").val();
    //     var curdayEnd = $("#curdayEnd").val();
    //     var data = {
    //         "myTradeNo":myTradeNo,
    //         "outTradeNo":outTradeNo,
    //         "tradeStatus":tradeStatus,
    //         "runStatus":runStatus,
    //         "curdayStart":curdayStart,
    //         "curdayEnd":curdayEnd
    //     };
    //     common.ajax(url,data,function(data){
    //         var data=data;
    //         var shtml="";
    //         shtml += "汇总：         订单金额：";
    //         shtml += "<font color='red'>" + data.totalMoney + "</font>";
    //         shtml += "      手续费：";
    //         shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
    //         shtml += "      实际金额：";
    //         shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
    //         $("#totalDiv").html(shtml);
    //     });
    // }

    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "tpDataInfo/totalData.do";
        var myTradeNo = $("#myTradeNo").val();
        var outTradeNo = $("#outTradeNo").val();
        var replenishType = $("#replenishType").val();
        var sendOk = $("#sendOk").val();
        var tradeStatus = $("#tradeStatus").val();
        var runStatus = $("#runStatus").val();
        var curdayStart = $("#curdayStart").val();
        var curdayEnd = $("#curdayEnd").val();
        var data = {
            "myTradeNo":myTradeNo,
            "outTradeNo":outTradeNo,
            "replenishType":replenishType,
            "sendOk":sendOk,
            "tradeStatus":tradeStatus,
            "runStatus":runStatus,
            "curdayStart":curdayStart,
            "curdayEnd":curdayEnd
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         订单金额：";
            shtml += "<font color='red'>" + data.totalMoney + "</font>";
            shtml += "      手续费：";
            shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
            shtml += "      实际金额：";
            shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
            shtml += "      实际支付金额：";
            shtml += "<font color='red'>" + data.totalPayAmount + "</font>";
            shtml += "      实际支付后扣手续之后的金额：";
            shtml += "<font color='red'>" + data.totalPayActualMoney + "</font>";
            $("#totalDiv").html(shtml);
        });
    }

}

// function outQueryTotal(){
//     var url = basePath + "tpdata/totalData.do";
//     var myTradeNo = $("#myTradeNo").val();
//     var outTradeNo = $("#outTradeNo").val();
//     var tradeStatus = $("#tradeStatus").val();
//     var runStatus = $("#runStatus").val();
//     var curdayStart = $("#curdayStart").val();
//     var curdayEnd = $("#curdayEnd").val();
//     var data = {
//         "myTradeNo":myTradeNo,
//         "outTradeNo":outTradeNo,
//         "tradeStatus":tradeStatus,
//         "runStatus":runStatus,
//         "curdayStart":curdayStart,
//         "curdayEnd":curdayEnd
//     };
//     common.ajax(url,data,function(data){
//         var data=data;
//         var shtml="";
//         shtml += "汇总：         订单金额：";
//         shtml += "<font color='red'>" + data.totalMoney + "</font>";
//         shtml += "      手续费：";
//         shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
//         shtml += "      实际金额：";
//         shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
//         $("#totalDiv").html(shtml);
//     });
// }

$(function(){
    account.indexInit();
})
