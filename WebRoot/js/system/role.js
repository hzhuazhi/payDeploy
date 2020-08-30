
var datatable;
var role = {
	//地址
	url:{
		list_url : ctx + '/system/role/list.do',
		dataList_url : ctx + "/system/role/dataList.do",
		add_url : ctx+ "/system/role/add.do",
		update_url : ctx+ "/system/role/update.do",
		queryId_url: ctx+ "/system/role/getId.do",
		delete_url: ctx+ "/system/role/delete.do"
	},	
	//添加修改验证参数
	validate:{
		rules:{
		    roleName:{
	 			required:true,
	 			maxlength:20
	 			},
	 		},
		messages: {
			roleName:{
	 			required : "角色名称不能为空",
	 			maxlength : "角色名称长度最多是20个汉字或字母"
	 		},
		},
		submitHandler : function() {
			var formData = $("#addSupplierForm").serialize();
			 $.ajax({
					url : ctx+ "/system/role/add",
					type : 'post',
					dataType : 'json',
					data :formData,
					success : function(data) {
						if (data.success) {
							promptMessage ('操作成功！','success',true);
				    		setTimeout("common.goList()",800);
						} else {
							art.alert(data.msg);
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
	      {"data":"roleName",},
          {"data":"roleType",
          	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
          		var html="";
          		if(oData.roleType==1){
          			html='<span>内部</span>';
          		}else if(oData.roleType==2){
          			html='<span>外部</span>';
          		}
          		$(nTd).html(html);
          	}
          },
          {"data":"remark",},
          {"data":"createTime",},
          {"data":"roleId",
          	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                  var html = '';
                  html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/system/role/update-role.do?roleId='+oData.roleId+'"> 编辑 </a>'    
                 		+' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.roleId + '" href = "javascript:void(0);">删除 </a>';
                 		$(nTd).html(html);
              }		
          }
	],
	// 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
	condJsonData : {
		roleName:null
	},
	//页面加载
	indexInit : function (){ 
		//url同步
		common.updateUrl(this.url);
		//添加
		$(".addbtn").live("click",function(){ 
			window.location.href = ctx + "/system/role/new-role.do";
		});
	 
		// 初始化列表数据
		common.showDatas(this.condJsonData,this.list);
		// 条件查询按钮事件
		$('#btnQuery').click(function() {
			role.condJsonData['roleName'] = $("#roleName").val(); 
			common.showDatas(role.condJsonData,role.list);
		});
	
		// 重置
		$("#butReset").click(function(){
			role.condJsonData['roleName'] = ""; 
			$("#roleName").val("");
			common.showDatas(role.condJsonData,role.list);
		}); 
		//删除
		$(".dataTableResetBtn").live("click",function(){
			var id = $(this).attr('directkey');
			var data = {
					roleId:id,
					isOk:'1'
			}
			common.updateStatus(data); 
		});
	}
	 
}

$(function(){
	role.indexInit();
})

$(function(){
	
	//展开与缩放
	$('.menu-op').toggle(function() {
		$(this).closest('div').find('.menu-child').slideUp();
		$(this).children('img').attr('src', ctx+'/images/jia_03b.png');
		$(this).attr('title', '展开'); 
	}, function() { 
		$(this).closest('div').find('.menu-child').slideDown();  
		$(this).children('img').attr('src', ctx+'/images/jian_03b.png');
		$(this).attr('title', '收起');
	});
	
	//选择复选框
	$('.menu-btn').click(function(event) {
		//全选按钮
		if($(this).hasClass('first-menu-btn')){
			if($(this).children(':checkbox').is(':checked')){
				$(this).closest('div').find(':checkbox').attr('checked', false); 
				return;  
			}
			$(this).closest('div').find(':checkbox').attr('checked', true);  
			return; 
		}
		//父级全选按钮
		if($(this).hasClass('second-menu-btn')){
			if($(this).children(':checkbox').is(':checked')){
				$(this).children(':checkbox').attr('checked', false);  
				$(this).next().find(':checkbox').attr('checked', false);  
				if ($('.second-menu').find(':checkbox:checked').length == 0) {
					$('.first-menu-btn[parentClass1="'+$(this).attr('parentClass1')+'"]').children(':checkbox').attr('checked', false); 
				}
				return;
			} 
			//子级选择
			$(this).children(':checkbox').attr('checked', true);  
			$(this).next().find(':checkbox').attr('checked', true);  
			$('.first-menu-btn[parentClass1="'+$(this).attr('parentClass1')+'"]').children(':checkbox').attr('checked', true); 
			return;
		}
		var secondMenu = $('.second-menu-btn[parentClass2="'+$(this).attr('parentClass2')+'"]');
		var firstMenu = $('.first-menu-btn[parentClass1="'+$(this).attr('parentClass1')+'"]');
		//子级单选按钮
		if($(this).hasClass('third-menu-btn')){
			if($(this).children(':checkbox').is(':checked')){ 
				$(this).children(':checkbox').attr('checked', false);  
				if ($(this).closest('div').find(':checkbox:checked').length == 0) {  
					$(secondMenu).children(':checkbox').attr('checked', false); 
				}
				if ($('.second-menu').find(':checkbox:checked').length == 0) {
					$(firstMenu).children(':checkbox').attr('checked', false); 
				}
				return;
			}   
			$(this).children(':checkbox').attr('checked', true);  
			if ($('.third-menu').find(':checkbox:checked').length >= 1) { 
				$(secondMenu).children(':checkbox').attr('checked', true); 
			}
			if ($('.second-menu').find(':checkbox:checked').length >= 1) { 
				$(firstMenu).children(':checkbox').attr('checked', true); 
			}
		}
	});
});


$(function(){
	 //numberInputBind();
	var tval=$("#type").val();
	if(tval=="0"){
		$(".admin_permissions").show();
		$(".studio_permissions").hide();
		$(".factory_permissions").hide();
	}else if(tval=="1"){
		$(".admin_permissions").hide();
		$(".studio_permissions").show();
		$(".factory_permissions").hide();
	}else if(tval=="2"){
		$(".admin_permissions").hide();
		$(".studio_permissions").hide();
		$(".factory_permissions").show();
	}
	$("#type").change(function(){
		var val=$(this).val();
		if(val=="0"){
			$(".admin_permissions").show();
			$(".studio_permissions").hide();
			$(".factory_permissions").hide();
		}else if(val=="1"){
			$(".admin_permissions").hide();
			$(".studio_permissions").show();
			$(".factory_permissions").hide();
		}else if(val=="2"){
			$(".admin_permissions").hide();
			$(".studio_permissions").hide();
			$(".factory_permissions").show();
		}
	});
});

  

	 
	 