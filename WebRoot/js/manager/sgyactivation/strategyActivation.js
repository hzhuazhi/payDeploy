
var datatable;
var basePath = $("#excDataHid").val();
var jsMethod = {
    //地址
    url:{
        list_url : ctx + '/sgyactivation/list.do',
        dataList_url : ctx + "/sgyactivation/dataList.do",
        add_url : ctx+ "/sgyactivation/add.do",
        update_url : ctx+ "/sgyactivation/update.do",
        queryId_url: ctx+ "/sgyactivation/getId.do",
        delete_url: ctx+ "/sgyactivation/delete.do",
        manyOperation_url: ctx+ "/sgyactivation/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"tpAcName",},
        {"data":"channelName",},
        {"data":"batchName",},
        {"data":"dpAcName",},
        {"data":"appName",},
        {"data":"activationName",},
        {"data":"limitNum",},
        {"data":"isLimitNum",},
        {"data":"isLimitOk",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isLimitOk==1){
                    html='<span>未完成</span>';
                }else if(oData.isLimitOk==2){
                    html='<span>完成</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"limitType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.limitType==1){
                    html='<span>全局限制</span>';
                }else if(oData.limitType==2){
                    html='<span>省份限制</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"activationTime",},
        {"data":"nextTime",},
        {"data":"remark",},
        {"data":"isEnable",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isEnable==2){
                    html='<span>启用</span>';
                }else if(oData.isEnable==1){
                    html='<span>禁用</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                if (oData.isEnable == 1){
                    isEnableHtml = '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);">启用 </a>';
                }else if(oData.isEnable == 2){
                    isEnableHtml = '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);">禁用 </a>';
                }
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/sgyactivation/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
                    +isEnableHtml
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        tpId:0,
        channelId:0,
        batchId:0,
        dpId:0,
        appId:0,
        activationName:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/sgyactivation/jumpAdd.do";
        });

        // 初始化列表数据
        this.queryTpAll();
        this.queryChannelAll();
        this.queryBatchAll();
        this.queryDpAll();
        this.queryAppAll();
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            jsMethod.condJsonData['tpId'] = $("#tpId").val();
            jsMethod.condJsonData['channelId'] = $("#channelId").val();
            jsMethod.condJsonData['batchId'] = $("#batchId").val();
            jsMethod.condJsonData['dpId'] = $("#dpId").val();
            jsMethod.condJsonData['appId'] = $("#appId").val();
            jsMethod.condJsonData['activationName'] = $("#activationName").val();
            common.showDatas(jsMethod.condJsonData,jsMethod.list);
        });

        // 重置
        $("#butReset").click(function(){
            jsMethod.condJsonData['tpId'] = "0";
            $("#tpId").val("0");
            jsMethod.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            jsMethod.condJsonData['batchId'] = "0";
            $("#batchId").val("0");
            jsMethod.condJsonData['dpId'] = "0";
            $("#dpId").val("0");
            jsMethod.condJsonData['appId'] = "0";
            $("#appId").val("0");
            jsMethod.condJsonData['activationName'] = "";
            $("#activationName").val("");
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


    },

    //下拉框数据填充
    //查询所有开发者数据-无分页-下拉框选项:
    queryTpAll:function(){
        var url = basePath + "accounttp/dataAllList.do";
        // alert("url:"+url);
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='tpId' name='tpId'  class='text-input medium-input' onchange='jsMethod.queryChannelAll()'>";
            shtml +="<option value='0'>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].acName+"</option>";
            }
            shtml +="</select>";
            $("#tpDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有渠道号数据-无分页-下拉框选项:
    queryChannelAll:function(){
        var url = basePath + "ch/dataAllList.do";
        var tpId = $("#tpId").val();
        var data = {
            "tpId":tpId
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input' onchange='jsMethod.queryBatchAll()'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].channelName+"</option>";
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有批次号数据-无分页-下拉框选项:
    queryBatchAll:function(){
        var url = basePath + "batch/dataAllList.do";
        var channelId = $("#channelId").val();
        var data = {
            "channelId":channelId
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='batchId' name='batchId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].batchName+"</option>";
            }
            shtml +="</select>";
            $("#batchDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有开发者数据-无分页-下拉框选项:
    queryDpAll:function(){
        var url = basePath + "accountdp/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select name='dpId' id='dpId' class='text-input medium-input' onchange='jsMethod.queryAppAll()'>";
            shtml +="<option value='0'>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].acName+"</option>";
            }
            shtml +="</select>";

            $("#dpDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有应用数据-无分页-下拉框选项:
    queryAppAll:function(){
        var url = basePath + "app/dataAllList.do";
        var dpId = $("#dpId").val();
        var data = {
            "dpId":dpId
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='appId' name='appId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].appName+"</option>";
            }
            shtml +="</select>";
            $("#appDiv").html(shtml);
        });
    },



}

$(function(){
    jsMethod.indexInit();
})
