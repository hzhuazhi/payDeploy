
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/withdrawcheck/list.do',
        dataList_url : ctx + "/withdrawcheck/dataList.do",
        add_url : ctx+ "/withdrawcheck/add.do",
        update_url : ctx+ "/withdrawcheck/update.do",
        queryId_url: ctx+ "/withdrawcheck/getId.do",
        delete_url: ctx+ "/withdrawcheck/delete.do",
        manyOperation_url: ctx+ "/withdrawcheck/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"channelName",},
        {"data":"accountName",},
        {"data":"money",},
        {"data":"bankCard",},
        {"data":"remark",},
        {"data":"withdrawStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.withdrawStatus==1){
                    html='<span>提现中</span>';
                }else if(oData.withdrawStatus==2){
                    html='<span>提现失败</span>';
                }else if(oData.withdrawStatus==3){
                    html='<span>提现成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"withdrawExplain",},
        {"data":"createTime",},


        // {"data":"withdrawStatus",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html = '';
        //         if (oData.withdrawStatus == 1){
        //             html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdrawcheck/jumpUpdate.do?id='+oData.id+'"> 未审核 </a>'
        //         }else if (oData.withdrawStatus == 2){
        //             html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdrawcheck/jumpUpdate.do?id='+oData.id+'"> 未审核 </a>'
        //         }else if (oData.withdrawStatus == 3){
        //             html = '已审核';
        //         }
        //         $(nTd).html(html);
        //     }

        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';

                if (oData.withdrawStatus == 1){
                    html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdrawcheck/jumpUpdate.do?id='+oData.id+'"> 未审核 </a>'
                }else if (oData.withdrawStatus == 2){
                    html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdrawcheck/jumpUpdate.do?id='+oData.id+'"> 未审核 </a>'
                }else if (oData.withdrawStatus == 3){
                    html = '已审核';
                }
                // if (oData.withdrawStatus == 1){
                //     html = html =  '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdrawcheck/jumpUpdate.do?id='+oData.id+'"> 未审核 </a>'
                // }else if (oData.withdrawStatus == 2){
                //     html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdrawcheck/jumpUpdate.do?id='+oData.id+'"> 未审核 </a>'
                // }else if (oData.withdrawStatus == 3){
                //     html = html = '已审核';
                // }
                // alert(html);
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        accountName:null,
        bankCard:null,
        withdrawStatus:0,
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['withdrawStatus'] = $("#withdrawStatus").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['channelName'] = "";
            $("#channelName").val("");
            account.condJsonData['accountName'] = "";
            $("#accountName").val("");
            account.condJsonData['bankCard'] = "";
            $("#bankCard").val("");
            account.condJsonData['withdrawStatus'] = "0";
            $("#withdrawStatus").val("0");
            common.showDatas(account.condJsonData,account.list);
        });

    },

    //下拉框数据填充
    //查询所有银行卡-无分页-下拉框选项:
    queryBankAll:function(){
        var url = basePath + "bank/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='bankId' name='bankId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+","+dataList[i].bankName+">==="+dataList[i].subbranchName+"==="+dataList[i].accountName+"</option>";
            }
            shtml +="</select>";
            $("#bankDiv").html(shtml);
        });
    }

}

$(function(){
    account.indexInit();
})
