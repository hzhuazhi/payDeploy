
var datatable;
var basePath = $("#excDataHid").val();
var jsMethod = {
    //地址
    url:{
        list_url : ctx + '/sj-yzhao/list.do',
        dataList_url : ctx + "/sj-yzhao/dataList.do",
        add_url : ctx+ "/app/add.do",
        update_url : ctx+ "/app/update.do",
        queryId_url: ctx+ "/app/getId.do",
        delete_url: ctx+ "/app/delete.do",
        manyOperation_url: ctx+ "/app/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"dataDay",},
        {"data":"productName",},
        {"data":"unitPrice",},
        {"data":"activationNumber",},
        {"data":"profit",}

    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        startCurday:0,
        endCurday:0,
        productName:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/app/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            jsMethod.condJsonData['startCurday'] = $("#startCurday").val();
            jsMethod.condJsonData['endCurday'] = $("#endCurday").val();
            jsMethod.condJsonData['productName'] = $("#productName").val();
            common.showDatas(jsMethod.condJsonData,jsMethod.list);
        });

        // 重置
        $("#butReset").click(function(){
            jsMethod.condJsonData['startCurday'] = "";
            $("#startCurday").val("");
            jsMethod.condJsonData['endCurday'] = "";
            $("#endCurday").val("");
            jsMethod.condJsonData['productName'] = "";
            $("#productName").val("");
            common.showDatas(jsMethod.condJsonData,jsMethod.list);
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
    jsMethod.indexInit();
})
