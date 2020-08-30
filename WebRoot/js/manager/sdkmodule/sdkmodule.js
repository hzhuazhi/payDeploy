
var datatable;
var basePath = $("#excDataHid").val();
var jsMethod = {
    //地址
    url:{
        list_url : ctx + '/sdkmodule/list.do',
        dataList_url : ctx + "/sdkmodule/dataList.do",
        add_url : ctx+ "/sdkmodule/add.do",
        update_url : ctx+ "/sdkmodule/update.do",
        queryId_url: ctx+ "/sdkmodule/getId.do",
        delete_url: ctx+ "/sdkmodule/delete.do",
        manyOperation_url: ctx+ "/sdkmodule/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"moduleShowName",},
        {"data":"moduleName",},
        {"data":"moduleVer",},
        {"data":"moduleType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.moduleType==1){
                    html='<span>任务管理器</span>';
                }else if(oData.moduleType==2){
                    html='<span>更新模块</span>';
                }else if(oData.moduleType==3){
                    html='<span>广告模块</span>';
                }else if(oData.moduleType==4){
                    html='<span>文件操作</span>';
                }else if(oData.moduleType==5){
                    html='<span>获取域名</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"fileSize",},
        {"data":"onlyValue",},
        {"data":"moduleUrl",},
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
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/sdkmodule/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
                    +isEnableHtml
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        moduleType:0,
        moduleShowName:null,
        moduleName:null,
        moduleVer:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/sdkmodule/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            jsMethod.condJsonData['moduleType'] = $("#moduleType").val();
            jsMethod.condJsonData['moduleShowName'] = $("#moduleShowName").val();
            jsMethod.condJsonData['moduleName'] = $("#moduleName").val();
            jsMethod.condJsonData['moduleVer'] = $("#moduleVer").val();
            common.showDatas(jsMethod.condJsonData,jsMethod.list);
        });

        // 重置
        $("#butReset").click(function(){
            jsMethod.condJsonData['moduleType'] = "0";
            $("#moduleType").val("0");
            jsMethod.condJsonData['moduleShowName'] = "";
            $("#moduleShowName").val("");
            jsMethod.condJsonData['moduleName'] = "";
            $("#moduleName").val("");
            jsMethod.condJsonData['moduleVer'] = "";
            $("#moduleVer").val("");
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


}

$(function(){
    jsMethod.indexInit();
})
