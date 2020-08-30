
$("#addSupplierForm").validate({
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
				url : ctx+ "/system/role/add.do",
				type : 'post',
				dataType : 'json',
				data :formData,
				success : function(data) {
					if (data.success) {
						promptMessage ('操作成功！','success',true);
			    		setTimeout(goList(),1000);
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
}),


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
	if(tval=="1"){
		$(".admin_permissions").show();
		$(".studio_permissions").hide();
		$(".factory_permissions").hide();
	}else if(tval=="2"){
        $(".admin_permissions").show();
        $(".studio_permissions").hide();
        $(".factory_permissions").hide();
		// $(".admin_permissions").hide();
		// $(".studio_permissions").show();
		// $(".factory_permissions").hide();
	}
	// else if(tval=="2"){
	// 	$(".admin_permissions").hide();
	// 	$(".studio_permissions").hide();
	// 	$(".factory_permissions").show();
	// }
	$("#type").change(function(){
		var val=$(this).val();
		if(val=="1"){
			$(".admin_permissions").show();
			$(".studio_permissions").hide();
			$(".factory_permissions").hide();
		}else if(val=="2"){
            $(".admin_permissions").show();
            $(".studio_permissions").hide();
            $(".factory_permissions").hide();
			// $(".admin_permissions").hide();
			// $(".studio_permissions").show();
			// $(".factory_permissions").hide();
		}
		// else if(val=="2"){
		// 	$(".admin_permissions").hide();
		// 	$(".studio_permissions").hide();
		// 	$(".factory_permissions").show();
		// }
	});
});

function goList(){
	location.href = ctx + '/system/role/list.do';
}

  

	 
	 