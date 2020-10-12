
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/channelreplenish/list.do',
        dataList_url : ctx + "/channelreplenish/dataList.do",
        add_url : ctx+ "/channelreplenish/add.do",
        update_url : ctx+ "/channelreplenish/update.do",
        queryId_url: ctx+ "/channelreplenish/getId.do",
        delete_url: ctx+ "/channelreplenish/delete.do",
        manyOperation_url: ctx+ "/channelreplenish/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"channelName",},
        {"data":"totalAmount",},
        {"data":"checkStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.checkStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.checkStatus==2){
                    html='<span>失败</span>';
                }else if(oData.checkStatus==3){
                    // html='<span>成功</span>';
                    html='<span><font color="red">成功</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channelreplenish/jumpInfo.do?id='+oData.id+'"> 详情 </a>'
                    +' <a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channelreplenish/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        outTradeNo:null,
        channelName:null,
        checkStatus:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/channelreplenish/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['checkStatus'] = $("#checkStatus").val();

            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            $("#myTradeNo").val("");
            account.condJsonData['outTradeNo'] = "";
            $("#outTradeNo").val("");
            account.condJsonData['channelName'] = "";
            $("#channelName").val("");
            account.condJsonData['checkStatus'] = "0";
            $("#checkStatus").val("0");
            common.showDatas(account.condJsonData,account.list);
        });
        //删除
        $(".dataTableResetBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isEnable = $(this).attr('directValue');
            var data = {
                id:id,
                isEnable:isEnable
            }
            common.manyOperation(data);
        });
    }

}

$(function(){
    account.indexInit();
})
