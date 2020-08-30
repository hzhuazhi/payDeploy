
var datatable;
var parentId = 0;
var module = {
	//地址
	url:{
		list_url : ctx + '/system/module/list.do',
		dataList_url : ctx + "/system/module/dataList.do",
		add_url : ctx+ "/system/module/add.do",
		update_url : ctx+ "/system/module/update.do",
		queryId_url: ctx+ "/system/module/getId.do",
		delete_url: ctx+ "/system/module/delete.do"
	},	
	//添加修改验证参数
	validate:{
		rules:{
		    moduleName:{
	 			required:true,
	 			maxlength:20
	 			},
 			actionUrl:{
	 			required:true,
	 			maxlength:200
	 			},
 			sort:{
	 			required:true,
	 			number:true
	 			}
	 		},
		messages: {
 			moduleName:{
	 			required : "类型不能为空",
	 			maxlength : "类型名称长度最多是20个汉字或字母"
 				},
	 		actionUrl:{
 				required:"请求路径不能为空",
 				maxlength:"请求路径过长"
	 			},
			actionUrl:{
 				required:"请输入序号",
 				number:"序号只能是数字"
	 			}
		},
		submitHandler : function() {
			if($("#level").val() != "1" && $("#parentId").val() == '0'){
				promptMessage('请选择上级模块', 'warning', false);
				return false;
			}
			
			var id = $("#show input[type='hidden']").val();
			var url = "";
			if(id){
				url = module.url.update_url; 
			}else{
				url = module.url.add_url;
			}
			
			var formData = $("#newFirstStoreForm").serialize();
		     $.ajax({
		    	url : url,
				type : 'post',
				dataType : 'json',
				data :formData,
				success : function(data) {
			    	 if(data.success){
			    		 parentId = 0;
			    		 promptMessage ('保存成功！','success',true);
			    		 common.goList();
			    	 }else{
			    		 promptMessage(data.msg, 'warning', false);
			    	 }
		    		
				},
				error : function(data) {
					art.alert(data.info);
				}
			}); 
			return false;
			//阻止表单提交
		}
	},
	//列表显示参数
	list:[
	    {"data":"moduleName",},
	    {"data":"type",
        	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        		var html="";
        		html+='<span>';
        		if(oData.type==1){
        			html+="系统";
        		}else if(oData.type==2){
        			html+="后台";
        		}
        		html+='</span>';
        		$(nTd).html(html);
        	}
        },
        {"data":"level",
        	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        		var html="";
        		html+='<span>';
        		if(oData.level==1){
        			html+="一级";
        		}else if(oData.level==2){
        			html+="二级";
        		}else if(oData.level==3){
        			html+="三级";
        		}
        		html+='</span>';
        		$(nTd).html(html);
        	}
        },
        {"data":"parentName",},
        {"data":"actionUrl",},
        {"data":"sort",},
        {"data":"isAllowed",
        	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        	var html="";
    		html+='<span>';
    		if(oData.isAllowed==0){
    			html+="是";
    		}else if(oData.isAllowed==1){
    			html+="否";
    		}
    		html+='</span>';
    		$(nTd).html(html);
            }		
        },
        {"data":"moduleId",
        	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = '<a class = "dataTableBtn dataTableDeleteBtn" id = "edit" directkey="' + oData.moduleId + '" href = "javascript:void(0);"> 编辑 </a>'   
               		+' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.moduleId + '" href = "javascript:void(0);">删除 </a>';
               		$(nTd).html(html);
            }		
        }
	],
	// 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
	condJsonData : {
		moduleName:null,
		type:null,
		level:null
	},
	//页面加载
	indexInit : function (){ 
		//url同步
		common.updateUrl(this.url);
		//跳转添加页面
		$(".addbtn").live("click",function(){ 
			$("#moduleId").val("");
			common.addInit(module.validate);
			selectModule();
			openDialog("show","");
		});
		//编辑
		$("#edit").live("click",function(){ 
			var moduleId = $(this).attr('directkey'); 
			$.ajax({url : ctx+ "/system/module/getId.do",
				type : 'post',
				dataType : 'json',
				data :{
					moduleId:moduleId
				},
				success : function(data) {
					if (data.success) {
						var m = data.data;
						parentId= m.parentId;
						common.addInit(module.validate);
						$("#moduleName").val(m.moduleName);
						$("#moduleId").val(moduleId);
						$("#type").val(m.type);
						$("#level").val(m.level);
						$("#sort").val(m.sort);
						$("#remark").val(m.remark);
						$("#actionUrl").val(m.actionUrl);
						$("#isAllowed").val(m.isAllowed);
						selectModule();
						openDialog("show","");
					} else {
						art.alert(data.msg);
					}
				},
				error : function(data) {
					art.alert(data.info);
				}
			});
		});
		// 初始化列表数据
		common.showDatas(this.condJsonData,this.list);
		// 条件查询按钮事件
		$('#btnQuery').click(function() {
			module.condJsonData['moduleName'] = $("#name").val();
			module.condJsonData['level'] = $("#queryLevel").val(); 
			common.showDatas(module.condJsonData,module.list);
		});
	
		//删除
		$(".dataTableResetBtn").live("click",function(){
			var id = $(this).attr('directkey');
			var data = {
					moduleId:id,
					isOk:'1'
			}
			common.updateStatus(data); 
		});

	}
}

$(function(){
	module.indexInit();
})

function selectModule(){
	var level = $("#level").val();
	var type = $("#type").val();
	$.ajax({url : ctx+ "/system/module/parentModule.do",
		type : 'post',
		dataType : 'json',
		data :{
			level:level,
			type :type
		},
		success : function(data) {
			if (data.success) {
	    		appendOption(data.data.modules,"#parentId");
	    		$("#parentId").val(parentId);
			} else {
				art.alert(data.msg);
			}
		},
		error : function(data) {
			art.alert(data.info);
		}
	});
}

function appendOption(list,selectId){
	var html='<option value="0">==请选择==</option>';
	for(var i=0;i<list.length;i++){
		html+='<option value="'+list[i].moduleId+'">'+list[i].moduleName+'</option>';
	}
	$(selectId).html(html);
	
	
}
  

	 
	 