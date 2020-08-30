
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/replenish/list.do',
        dataList_url : ctx + "/replenish/dataList.do",
        // add_url : ctx+ "/tpDataInfo/add.do",
        // update_url : ctx+ "/tpDataInfo/update.do",
        // queryId_url: ctx+ "/tpDataInfo/getId.do",
        // delete_url: ctx+ "/tpDataInfo/delete.do",
        manyOperation_url: ctx+ "/replenish/manyOperation.do",
        exportData_url : ctx +  "/replenish/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"totalAmount",},
        {"data":"serviceCharge",},
        {"data":"actualMoney",},
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
        {"data":"extraReturnParam",},

        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/replenish/jumpUpdate.do?id='+oData.id+'"> 补单 </a>';
                $(nTd).html(html);
            }
        }



    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        outTradeNo:null,
        channelId:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);


        // common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['channelId'] = $("#channelId").val();
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
            // common.showDatas(account.condJsonData,account.list);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id
            }
            common.cf(data);
        });

    },



}


$(function(){
    account.indexInit();
})
