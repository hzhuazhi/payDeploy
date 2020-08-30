
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/tpgewaytradetype/list.do',
        dataList_url : ctx + "/tpgewaytradetype/dataList.do",
        add_url : ctx+ "/tpgewaytradetype/add.do",
        update_url : ctx+ "/tpgewaytradetype/update.do",
        queryId_url: ctx+ "/tpgewaytradetype/getId.do",
        delete_url: ctx+ "/tpgewaytradetype/delete.do",
        manyOperation_url: ctx+ "/tpgewaytradetype/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"myName",},
        {"data":"myTradeType",},
        {"data":"myServiceCharge",},
        {"data":"limitMoney",},
        {"data":"limitDay",},
        {"data":"limitMonth",}
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeType:null

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeType'] = $("#myTradeType").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeType'] = "";
            $("#myTradeType").val("");
            common.showDatas(account.condJsonData,account.list);
        });



    }



}

$(function(){
    account.indexInit();
})
