
numberInputBind();

$.fn.getPromotionProduct = function(){

    this.bind("click", function(e){
        chooseProduct();
    });
    
    //默认传递参数
    /*var defaults = {
    	orderType:"SPECIAL",	//订单类型：SPECIAL特价促销单，GIVING：满额赠送单
        dataTableId: "#product_table",//页面上的table
        dialogTableId: '#choose_pmpro_table', // dialog对应的出现结果 TABLE的ID
        artDialogReferId: "choose_pmpro_con"// 对应的弹出对话框的内容
    };*/
    
    //var options = $.extend(defaults, options);//如有参数将覆盖掉默认传递参数
    
    var dialogTableId = options.dialogTableId, 
    	dataTableId = options.dataTableId, 
    	artDialogReferId = options.artDialogReferId, 
    	dialogTbody = $(dialogTableId +">tbody"),
    	orderType = options.orderType, 
    	datatable = null;
   
    //初始化查询条件
	 var condJsonData = {
		type0Id:null,
		type1Id:null,
		type2Id:null,
		name:null,
		aoData:null
     };
	 
	 //选择框改变事件 
	 $("#btnQuery").click(function(){
		 condJsonData['name'] = $("#dsearchInput").val();
		 getProducts(condJsonData);
	 });
	 
	 //选择框改变事件 
	 $("#type0Id").change(function(){
			if($("#tycpe0Id").val()==0) {
				condJsonData['type0Id']=null;
			} else{
				condJsonData['type0Id'] = $("#type0Id").val();
			}
			condJsonData['type1Id']=null;
			condJsonData['type2Id']=null;
			condJsonData['name'] = $("#dsearchInput").val();
			//替换二级子类别
			$('#type1Id').html('<option value = "0">　请选择　</option>' ).append($('#select-type1 option[parentId="'+$(this).val()+'"]').clone());  
			$('#type2Id').html('<option value = "0">　请选择　</option>');
			getProducts(condJsonData);
	});
	 
	//选择框改变事件
	 $("#type1Id").change(function(){
			if($("#type1Id").val()==0)  {
				condJsonData['type1Id']=null;
			}
			else{ 
			condJsonData['type1Id'] = $("#type1Id").val();
			}
			condJsonData['type2Id']=null; 
			condJsonData['name'] = $("#dsearchInput").val();
			//替换二级子类别
			$('#type2Id').html('<option value = "0">　请选择　</option>').append($('#select-type2 option[parentId="'+$(this).val()+'"]').clone());  
			getProducts(condJsonData);
	});
	 
	//选择框改变事件
	 $("#type2Id").change(function(){
			if($("#type2Id").val()==0)
				{condJsonData['type2Id']=null;}
			else{
			condJsonData['type2Id'] = $("#type2Id").val();
			}
			condJsonData['name'] = $("#dsearchInput").val();
			getProducts(condJsonData);
	});
	 
	//重置按钮事件
	 $("#condForm").find("input:reset").click(function(){
		 condJsonData['name']=null;
		 condJsonData['type0Id']=null;
		 condJsonData['type1Id']=null;
		 condJsonData['type2Id']=null;
		 getProducts(condJsonData);
	 });
	 
    //弹出框
    var chooseProduct = function(){
        var dialog = art.dialog.get('promotion_products_dialog');
        if (dialog) {
            dialog.close();
        }
        getProducts(condJsonData);
        art.dialog({
            id: 'promotion_products_dialog',
            title: "产品选择",
            content: document.getElementById(artDialogReferId),
            lock: true,
            okValue: "确认提交",
            cancelValue: "取消",
            ok: function(){
            	if(orderType == 'SPECIAL'){
            		submitSpecial();
            	}
            	else if(orderType == 'GIVING'){
            		submitGiving();
            	}
            	return false;
            },
            cancel:function(){}
        });
    };
    // -----------------------------------------------------获取商品列表----------------------------------------------------------
    function getProducts(condJsonData){
		if(datatable){
			datatable.fnDestroy();
		}
    	datatable = $(dialogTableId).dataTable({
			"sDom": "<'dtTop'<'dtShowPer'l>><'dtTables't><'dtBottom'<'dtInfo'i><'dtPagination'p>>",
	   	    "sPaginationType": "full_numbers",
	   	    "fnInitComplete": function(){
	   	    	$(".dtFilter input").addClass("simple_field").css({
		   	    	"width": "auto",
		   	    	"margin-left": "15px"
	   	   		});
	   	    },
		    "aaSorting": [[0,'desc']], //默认按第2列排序
	    	"iDisplayLength":10, // 页面大小
	    	"bPaginate" : true,
	    	"bFilter" : false,
	    	"bSort" : false,
	    	"bRetrieve":true,
	    	"bServerSide" : true,
	    	"sAjaxSource" : ctx + '/admin/promotion/promotion-list-product',
	    	//根据对应列名显示值 
			"aoColumns": [
                {
					"sName" : "id",
					"fnRender" : function(oObj){
						var html = '<input type="checkbox" name="checks" value = "' + oObj.aData[0] + '">';
							html+='<input type="hidden" name="name" value = "' + oObj.aData[1] + '">';
							html+='<input type="hidden" name="fullTypeName" value = "' + oObj.aData[2] + '">';
							html+='<input type="hidden" name="price" value = "' + oObj.aData[3] + '">';
							html+='<input type="hidden" name="shopname" value = "' + oObj.aData[4] + '">';
		    	    	return html;
		    	    }
				},
	            {"sName" : "name"},
	            {"sName" : "fullTypeName"},
	            {"sName" : "price"},
	            {"sName" : "shopname"}
	         ],
			 "fnServerData" : function(sSource, aoData, fnCallback) {
				condJsonData['aoData'] = JSON.stringify(aoData);  
				 $.ajax({
						type : 'post',
						dataType: 'json',
						url : sSource,
						data : condJsonData, // 以json格式传递
						success : function(resp) {
							fnCallback(resp);
						}
				});
			 }
		});
    	$(dialogTableId).css('width','100%');
    }
    
    /**
     * 特价促销单提交选中
     */
    var submitSpecial = function(){
    	var checkInput = $(dialogTbody).find("tr input:checked");
    	var length = $(dialogTbody).find("tr input:checked").length;
    	for(var i = 0 ;i<length ;i++){
    		var content="";
    		var curTr = $(checkInput[i]).closest("tr");
    		var productId = $(curTr).find("input:checked").val();
    		if(!isExist(productId)){
    			var name = $(curTr).find("input[name='name']").val();
    			var fullTypeName = $(curTr).find("input[name='fullTypeName']").val();
    			var shopname = $(curTr).find("input[name='shopname']").val();
    			var price = $(curTr).find("input[name='price']").val();
    			content+='<tr>'
    				+'<td>'
    				+'<input type = "checkbox" class="checkboxs2">'
    				+'<input type="hidden" name = "productId" value="'+productId+'" class="checkboxs2 specialIndex" >'
    				+'<input type="hidden" name = "specialType" class = "specialType" value="1" >'
    				+'</td>'
    				+'<td>'
    				+'<span title="'+name+'" class="product-span">'+name+'</span>'
    				+'</td>'
    				+'<td><span title="'+fullTypeName+'" class="product-span" style="width:80px">'+fullTypeName+'</span></td>'
    				+'<td>'+shopname+'</td>'
    				+'<td>'+price+'元</td>' 
    				+'<td>'
    				+'<select class = "pType selectCommonSty specialIndex" name ="specialType">'
    				+'<option  value = "1">按特价</option>'
    				+'<option  value = "2">按折扣</option>'
    				+'</select>'
    				+'</td>'
    				+'<td>'
    				+'<input name = "promotionPri" class="protable-input promotionPri numberInput"  value="">'
    				+'</td>'
    				+'<td>'
    				+'<input disabled="disabled" name = "discount" class="protable-input discount numberInput"  value="">'
    				+'</td>'
    				+'<td>'
    				+'<input name = "beginQuantity" class="protable-input numberInput" value="">'
    				+'</td>'
    				+'<td>'
    				+'<input name = "limitQuantity"  class="protable-input numberInput" value="">'
    				+'</td>'
    				+'</tr>';
	    		}
    		}
    	$(dataTableId).append(content);
    	var dialog = art.dialog.get('promotion_products_dialog');
    	if (dialog) {
    		dialog.close();
    	}
    	bindEvent();
    };
    
    /**
     * 满额赠送提交选中
     */
    var submitGiving = function(){
    	var checkInput = $(dialogTbody).find("tr input:checked");
    	var length = $(dialogTbody).find("tr input:checked").length;
    	var content=""; 
    	for(var i = 0 ;i<length ;i++){
    		var curTr = $(checkInput[i]).closest("tr");
    		var productId = $(curTr).find("input:checked").val();
    		if(!isExist(productId)){
    			var name = $(curTr).find("input[name='name']").val();
    			var fullTypeName = $(curTr).find("input[name='fullTypeName']").val();
    			var shopname = $(curTr).find("input[name='shopname']").val();
    			var price = $(curTr).find("input[name='price']").val();
    			content+='<tr>'
    				+'<td>'
    				+'<input type = "checkbox" class="checkboxs2">'
    				+'<input type="hidden" name = "sendProductId" value="'+productId+'" class="checkboxs2 specialIndex" >'
    				+'<input type="hidden" name = "specialType" class = "specialType" value="1" >'
    				+'</td>'
    				+'<td>'
    				+'<span title="'+name+'" class="product-span">'+name+'</span>'
    				+'</td>'
    				+'<td><span title="'+fullTypeName+'" class="product-span" style="width:180px">'+fullTypeName+'</span></td>'
    				+'<td>'+shopname+'</td>'
    				+'<td>'+price+'元</td>'  
    				+'</tr>';
    		}
    	}
    	$(dataTableId).append(content);
    	var dialog = art.dialog.get('promotion_products_dialog');
    	if (dialog) {
    		dialog.close();
    	}
    };
    
//    绑定特价类型切换事件
    function bindEvent(){
    	$("select[name='specialType']").each(function(){
    		$(this).bind("propertychange change",function(){
        		var tr = $(this).closest("tr");
        		if($(this).val() == 1){
        			
        			tr.find("input.specialType").val(1);
        			tr.find("input.discount").attr("disabled",true);
        			tr.find("input.promotionPri").attr("disabled",false);
        		}
        		if($(this).val() == 2){
        			tr.find("input.specialType").val(2);
        			tr.find("input.discount").attr("disabled",false);
        			tr.find("input.promotionPri").attr("disabled",true);
        		}
        	});
    	});
    }
    //是否存在
    function isExist(productId){
    	var productIds = $(dataTableId + " tbody input[name='productId']");
    	for(var i = 0;i<productIds.length;i++){
    		if($(productIds[i]).val() == productId){
    			return true;
    		}
    	}
    	return false;
    }
};





$.fn.getAllProduct = function(options){

    this.bind("click", function(e){
        chooseProduct();
    });
    
    //默认传递参数
    /*var defaults = {
    	resultId: '#productId', // dialog对应的出现结果 TABLE的ID
    	resultName: '#product', // dialog对应的出现结果 TABLE的ID
        dialogTableId: '#choose_pmpro_table', // dialog对应的出现结果 TABLE的ID
        artDialogReferId: "choose_pmpro_con"// 对应的弹出对话框的内容
    };*/
    
    /*var options = $.extend(defaults, options);//如有参数将覆盖掉默认传递参数*/
    
    var dialogTableId = options.dialogTableId, 
    	artDialogReferId = options.artDialogReferId, 
    	resultId = options.resultId, 
    	resultName = options.resultName, 
    	dialogTbody = $(dialogTableId +">tbody"),
    	datatable = null;
   
    //初始化查询条件
	 var condJsonData = {
		type0Id:null,
		type1Id:null,
		type2Id:null,
		name:null,
		aoData:null
     };
	 
	 //选择框改变事件 
	 $("#btnQuery").click(function(){
		 condJsonData['name'] = $("#dsearchInput").val();
		 getProducts(condJsonData);
	 });
	 
	 $("#type0Id").change(function(){
			if($("#tycpe0Id").val()==0) {
				condJsonData['type0Id']=null;
			} else{
				condJsonData['type0Id'] = $("#type0Id").val();
			}
			condJsonData['type1Id']=null;
			condJsonData['type2Id']=null;
			condJsonData['name'] = $("#dsearchInput").val();
			//替换二级子类别
			$('#type1Id').html('<option value = "0">　请选择　</option>' ).append($('#select-type1 option[parentId="'+$(this).val()+'"]').clone());  
			$('#type2Id').html('<option value = "0">　请选择　</option>');
			getProducts(condJsonData);
	});
	
	//选择框改变事件
	 $("#type1Id").change(function(){
			if($("#type1Id").val()==0)  {
				condJsonData['type1Id']=null;
			}
			else{ 
			condJsonData['type1Id'] = $("#type1Id").val();
			}
			condJsonData['type2Id']=null; 
			condJsonData['name'] = $("#dsearchInput").val();
			//替换二级子类别
			$('#type2Id').html('<option value = "0">　请选择　</option>').append($('#select-type2 option[parentId="'+$(this).val()+'"]').clone());  
			getProducts(condJsonData);
	});
	 
	//选择框改变事件
	 $("#type2Id").change(function(){
			if($("#type2Id").val()==0)
				{condJsonData['type2Id']=null;}
			else{
			condJsonData['type2Id'] = $("#type2Id").val();
			}
			condJsonData['name'] = $("#dsearchInput").val();
			getProducts(condJsonData);
	});
	 
	//重置按钮事件
	 $("#condForm").find("input:reset").click(function(){
		 condJsonData['name']=null;
		 condJsonData['type0Id']=null;
		 condJsonData['type1Id']=null;
		 condJsonData['type2Id']=null;
		 getProducts(condJsonData);
	 });
	 
    //弹出框
    var chooseProduct = function(){
        var dialog = art.dialog.get('promotion_products_dialog');
        if (dialog) {
            dialog.close();
        }
        getProducts(condJsonData);
        art.dialog({
            id: 'promotion_products_dialog',
            title: "产品选择",
            content: document.getElementById(artDialogReferId),
            lock: true,
            okValue: "确认提交",
            cancelValue: "取消",
            ok: function(){
            	submit();
            	return false;
            },
            cancel:function(){}
        });
    };
    // -----------------------------------------------------获取商品列表----------------------------------------------------------
    function getProducts(condJsonData){
		if(datatable){
			datatable.fnDestroy();
		}
    	datatable = $(dialogTableId).dataTable({
			"sDom": "<'dtTop'<'dtShowPer'l>><'dtTables't><'dtBottom'<'dtInfo'i><'dtPagination'p>>",
	   	    "sPaginationType": "full_numbers",
	   	    "fnInitComplete": function(){
	   	    	$(".dtFilter input").addClass("simple_field").css({
		   	    	"width": "auto",
		   	    	"margin-left": "15px"
	   	   		});
	   	    },
		    "aaSorting": [[0,'desc']], //默认按第2列排序
	    	"iDisplayLength":10, // 页面大小
	    	"bPaginate" : true,
	    	"bFilter" : false,
	    	"bSort" : false,
	    	"bRetrieve" : true,
	    	"bServerSide" : true,
	    	"sAjaxSource" : ctx + '/admin/promotion/promotion-product',
	    	//根据对应列名显示值 
			"aoColumns": [
                {
					"sName" : "id",
					"fnRender" : function(oObj){
						var html = '<input type="radio" name="checks" value = "' + oObj.aData[0] + '">';
							html+='<input type="hidden" name="name" value = "' + oObj.aData[1] + '">';
							html+='<input type="hidden" name="fullTypeName" value = "' + oObj.aData[2] + '">';
							html+='<input type="hidden" name="price" value = "' + oObj.aData[3] + '">';
							html+='<input type="hidden" name="shopname" value = "' + oObj.aData[4] + '">';
		    	    	return html;
		    	    }
				},
	            {"sName" : "name"},
	            {"sName" : "fullTypeName"},
	            {"sName" : "price"},
	            {"sName" : "shopname"}
	         ],
			 "fnServerData" : function(sSource, aoData, fnCallback) {
				condJsonData['aoData'] = JSON.stringify(aoData);  
				 $.ajax({
						type : 'post',
						dataType: 'json',
						url : sSource,
						data : condJsonData, // 以json格式传递
						success : function(resp) {
							fnCallback(resp);
						}
				});
			 }
		});
    	$(dialogTableId).css('width','100%');
    }
};