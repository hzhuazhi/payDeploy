
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/agentchannel/list.do',
        dataList_url : ctx + "/agentchannel/dataList.do",
        add_url : ctx+ "/agentchannel/add.do",
        update_url : ctx+ "/agentchannel/update.do",
        queryId_url: ctx+ "/agentchannel/getId.do",
        delete_url: ctx+ "/agentchannel/delete.do",
        manyOperation_url: ctx+ "/agentchannel/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"linkName",},
        {"data":"agentName",},
        {"data":"channelName",},
        {"data":"channelGewayLinkName",},
        {"data":"serviceCharge",},
        {"data":"remark",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/agentchannel/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        linkName:null,
        agentId:0,
        channelId:0

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        this.queryTpAll();
        this.queryAgentAll();
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/agentchannel/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['linkName'] = $("#linkName").val();
            account.condJsonData['agentId'] = $("#agentId").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['linkName'] = "";
            $("#linkName").val("");
            account.condJsonData['agentId'] = "0";
            $("#agentId").val("0");
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
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
    //查询所有代理-无分页-下拉框选项:
    queryAgentAll:function(){
        var url = basePath + "accountagent/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='agentId' name='agentId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].agentName+"</option>";
            }
            shtml +="</select>";
            $("#agentDiv").html(shtml);
        });
    }

}

$(function(){
    account.indexInit();
})
