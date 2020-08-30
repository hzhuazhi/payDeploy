$(function($){
	
	//收藏商品
	$(".collect").live("click",function(){
		 var id=$(this).closest(".product-item").attr('item-id');
		collectProduct(id);
	});
});

//收藏商品
function collectProduct(id){
		 $.ajax({
               url : ctx +"/user/collectProduct",
               data : {id:id}, 
               type: "post",
               dataType:"json",
               success : function(data){
            	   if(data.isCollected){
            		   art.alert("已经收藏过该宝贝了！");
            		   return;
            		   
            		 /*  art.dialog({
	           		        title : "提示",
	           		        content : "申请退后成功，请等待卖家处理",
	           		        lock: true,
	           		        cancel:false,
	           		        ok:function(){
	           		        	window.location.href = ctx+"/customer/go/list-order";
	           		        }
	           		   	});*/

            	   }
	               	if(!data.success){
	               		art.alert(data.info);
	               		return;
	               	} 
	               	art.alert("收藏宝贝成功！");
               },
               error : function(){
                   art.alert("收藏失败！");
               }
           }); 
}


//收藏店铺
function collectShop(id){
		 $.ajax({
               url : ctx +"/user/collectShop",
               data : {"id":id},
               type: "post",
               dataType:"json",
               success : function(data){
            	   if(data.isCollected){
            		   art.alert("已经收藏过该店铺了！");
            		   return;
            	   }
	               	if(data.success){
	               		art.alert("收藏店铺成功！");
	               		$("#collectNumber").text(parseInt($("#collectNumber").text())+1);
	               	}else{
	               		art.alert("请先登录再收藏！");
	               	}
               },
               error : function(){
                   art.alert("收藏失败！");
               }
           }); 
}