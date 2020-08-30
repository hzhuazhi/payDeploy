
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/gewaytradetype/list.do',
        dataList_url : ctx + "/gewaytradetype/dataList.do",
        add_url : ctx+ "/gewaytradetype/add.do",
        update_url : ctx+ "/gewaytradetype/update.do",
        queryId_url: ctx+ "/gewaytradetype/getId.do",
        delete_url: ctx+ "/gewaytradetype/delete.do",
        manyOperation_url: ctx+ "/gewaytradetype/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"gewayName",},
        {"data":"myName",},
        {"data":"myTradeType",},
        {"data":"myServiceCharge",},
        {"data":"outName",},
        {"data":"outTradeType",},
        {"data":"serviceCharge",},
        {"data":"limitMoney",},
        {"data":"limitDay",},
        {"data":"limitMonth",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/gewaytradetype/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        gewayId:0,
        myTradeType:null,
        outTradeType:null

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        this.queryGewayAll();
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/gewaytradetype/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['gewayId'] = $("#gewayId").val();
            account.condJsonData['myTradeType'] = $("#myTradeType").val();
            account.condJsonData['outTradeType'] = $("#outTradeType").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['gewayId'] = "0";
            $("#gewayId").val("0");
            account.condJsonData['myTradeType'] = "";
            $("#myTradeType").val("");
            account.condJsonData['outTradeType'] = "";
            $("#outTradeType").val("");
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
    //查询所有银行卡-无分页-下拉框选项:
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
