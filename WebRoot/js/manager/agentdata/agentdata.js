
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/agentdata/list.do',
        dataList_url : ctx + "/agentdata/dataList.do",
        // add_url : ctx+ "/tpDataInfo/add.do",
        // update_url : ctx+ "/tpDataInfo/update.do",
        // queryId_url: ctx+ "/tpDataInfo/getId.do",
        // delete_url: ctx+ "/tpDataInfo/delete.do",
        manyOperation_url: ctx+ "/agentdata/manyOperation.do",
        exportData_url : ctx +  "/agentdata/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"totalAmount",},
        // {"data":"serviceCharge",},
        // {"data":"actualMoney",},
        {"data":"tradeStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.tradeStatus==0){
                    html='<span>初始化</span>';
                }else if(oData.tradeStatus==1){
                    html='<span>成功</span>';
                }else if(oData.tradeStatus==2){
                    html='<span><font color="red">失败</font></span>';
                }else if(oData.tradeStatus==3){
                    html='<span>其它</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"tradeTime",},
        {"data":"xyExtraReturnParam",}
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        outTradeNo:null,
        channelId:0,
        curdayStart:0,
        curdayEnd:0,
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        this.queryTpAll();
        this.queryTotal();
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['channelId'] = $("#channelId").val();
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
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
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
        var url = basePath + "agentdata/totalData.do";
        var myTradeNo = $("#myTradeNo").val();
        var outTradeNo = $("#outTradeNo").val();
        var channelId = $("#channelId").val();
        var curdayStart = $("#curdayStart").val();
        var curdayEnd = $("#curdayEnd").val();
        var data = {
            "myTradeNo":myTradeNo,
            "outTradeNo":outTradeNo,
            "channelId":channelId,
            "curdayStart":curdayStart,
            "curdayEnd":curdayEnd
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         订单金额：";
            shtml += "<font color='red'>" + data.totalMoney + "</font>";
            // shtml += "      手续费：";
            // shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
            // shtml += "      实际金额：";
            // shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
            shtml += "      收益：";
            shtml += "<font color='red'>" + data.totalRoyalty + "</font>";
            $("#totalDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有代理旗下的渠道-无分页-下拉框选项:
    queryTpAll:function(){
        var url = basePath + "agentchannel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].channelId+">"+dataList[i].channelName+"</option>";
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
        });
    },

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
