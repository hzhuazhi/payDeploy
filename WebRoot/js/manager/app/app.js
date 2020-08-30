
var datatable;
var basePath = $("#excDataHid").val();
var jsMethod = {
    //地址
    url:{
        list_url : ctx + '/app/list.do',
        dataList_url : ctx + "/app/dataList.do",
        add_url : ctx+ "/app/add.do",
        update_url : ctx+ "/app/update.do",
        queryId_url: ctx+ "/app/getId.do",
        delete_url: ctx+ "/app/delete.do",
        manyOperation_url: ctx+ "/app/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"appName",},
        {"data":"appNum",},
        {"data":"appKey",},
        {"data":"acName",},
        {"data":"appVer",},
        {"data":"appUrl",},
        {"data":"appPackage",},
        {"data":"startBoundary",},
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
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/app/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
                    +isEnableHtml
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        appName:null,
        appNum:null,
        dpId:0
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
        this.queryDpAll();
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            jsMethod.condJsonData['appNum'] = $("#appNum").val();
            jsMethod.condJsonData['appName'] = $("#appName").val();
            jsMethod.condJsonData['dpId'] = $("#dpId").val();
            common.showDatas(jsMethod.condJsonData,jsMethod.list);
        });

        // 重置
        $("#butReset").click(function(){
            jsMethod.condJsonData['appNum'] = "";
            $("#appNum").val("");
            jsMethod.condJsonData['appName'] = "";
            $("#appName").val("");
            jsMethod.condJsonData['dpId'] = "0";
            $("#dpId").val("0");
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
    queryDpAll:function(){
        var url = basePath + "accountdp/dataAllList.do";
        // alert("url:"+url);
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select name='dpId' id='dpId' class='text-input medium-input'>";
            shtml +="<option value='0'>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].acName+"</option>";
            }
            shtml +="</select>";

            $("#dpDiv").html(shtml);
        });
    }



}

$(function(){
    jsMethod.indexInit();
})
