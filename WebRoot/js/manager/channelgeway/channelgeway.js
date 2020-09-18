
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/channelgeway/list.do',
        dataList_url : ctx + "/channelgeway/dataList.do",
        add_url : ctx+ "/channelgeway/add.do",
        update_url : ctx+ "/channelgeway/update.do",
        queryId_url: ctx+ "/channelgeway/getId.do",
        delete_url: ctx+ "/channelgeway/delete.do",
        manyOperation_url: ctx+ "/channelgeway/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"linkName",},
        {"data":"channelName",},
        {"data":"gewayName",},
        {"data":"serviceCharge",},
        {"data":"deductRatio",},
        {"data":"profitType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.profitType==1){
                    html='<span>普通利益</span>';
                }else if(oData.profitType==2){
                    html='<span><font color="red">多人利益</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"remark",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channelgeway/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        linkName:null,
        channelId:0,
        gewayId:0

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        this.queryTpAll();
        this.queryGewayAll();
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/channelgeway/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['linkName'] = $("#linkName").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            account.condJsonData['gewayId'] = $("#gewayId").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['linkName'] = "";
            $("#linkName").val("");
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            account.condJsonData['gewayId'] = "0";
            $("#gewayId").val("0");
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

    },

    //下拉框数据填充
    //查询所有渠道-无分页-下拉框选项:
    queryTpAll:function(){
        var url = basePath + "accounttp/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].channelName+"</option>";
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有通道-无分页-下拉框选项:
    queryGewayAll:function(){
        var url = basePath + "geway/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='gewayId' name='gewayId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].gewayName+"</option>";
            }
            shtml +="</select>";
            $("#gewayDiv").html(shtml);
        });
    }

}

$(function(){
    account.indexInit();
})
