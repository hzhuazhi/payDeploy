
function goSearchProduct(isPageIndex){
	if(isPageIndex!=1){//如果不是通过点击页码跳进来的
		$("#pageIndex").val(1);
	}

	//pin链接
	//url从表单获取参数
	var url = '';
	var searchType = $.trim($("#searchProductBtn").prevAll("a:last").text());
	if(searchType == '商品'){
		url = ctx + '/go/product-list';
		var params='';
		var keyWordUrl= $.trim($("#keyWord").val());
		var minPriceUrl=parseFloat($("#minPrice").val());
		var maxPriceUrl=parseFloat($("#maxPrice").val());
		var productTypeUrl=parseInt($("#productType").val());
		var processWayUrl=$("#processWay").val();
		var isZiyingUrl=parseInt($("#isZiying").val());
		var isStoreUrl=parseInt($("#isStore").val());
		var pageIndexUrl=parseInt($("#pageIndex").val());
		var hasNumberUrl=parseInt($("#hasNumber").val());
		var sortTypeUrl=parseInt($("#sortType").val());
		var paramsAboutUrl=$("#paramsAbout").val();
		
		if(paramsAboutUrl!=''){
			params+='&paramsAbout=' + encodeURIComponent(paramsAboutUrl);
			
		}
		if(minPriceUrl!=0){
			params+='&minPrice=' + encodeURIComponent(minPriceUrl);
			
		}
		if(maxPriceUrl!=0){
			params+='&maxPrice=' + encodeURIComponent(maxPriceUrl);
			
		}
		if(productTypeUrl!=0){
			params+='&productType=' + encodeURIComponent(productTypeUrl);
			
		}
		if(processWayUrl!=''){
			params+='&processWay=' + encodeURIComponent(processWayUrl);
			
		}
		if(isZiyingUrl!=0){
			params+='&isZiying=' + encodeURIComponent(isZiyingUrl);
			
		}
		if(isStoreUrl!=0){
			params+='&isStore=' + encodeURIComponent(isStoreUrl);
			
		}
		if(pageIndexUrl!=1){
			params+='&pageIndex=' + encodeURIComponent(pageIndexUrl);
			
		}
		if(hasNumberUrl!=0){
			params+='&hasNumber=' + encodeURIComponent(hasNumberUrl);
			
		}
		if(sortTypeUrl!=0){
			params+='&sortType=' + encodeURIComponent(sortTypeUrl);
			
		}
		if(params!='' || keyWordUrl != ''){
			url = url+'?keyWord=' + keyWordUrl + params;
		}
	}else if(searchType == '店铺'){
		url = ctx + '/shop/search?keyWord=' + encodeURIComponent($("#keyWord").val());
	}
	//如果点击商品参数选项
//	/url+=url+"&"
	window.location.href = url;//刷新页面
}

//搜索商品的共用方法 begin
var pagerInstance=null;
function searchProductFun(pageIndexSure,id){
	
	
	if(typeof(pageIndexSure)=='undefined'){//如果不是通过输入跳转到的页数，点击确定，则将以下初始化
		$("#pageIndex").val(pageIndexUrl);
		$("#fowardPage").val(1);
	}
	var formData=$("#searchProductForm").serialize();
	$.ajax({
		type: "POST",
		url: ctx+"/product/search",
		data:formData,
		dataType:'json',
		success: doinit,
		error: doError
	});
	function doinit(json){
		var totalPage = json.totalPage;
		//显示商品总数
		$("#totalProductNum").text(json.totalCount);
		//显示商品总页数
		$("#totalNumber").text(totalPage);
		
		 $("#product-listall").html('<p style="padding:20px;">数据查找中&nbsp;<img src="'+ctx+'/images/loading1.gif"></p>');
		 if(!json.success){
  		 	$('#product-listall').html('查询出现错误，请刷新页面重试'); 
    		return;
  		}
		var content = produce(json);
		$('#product-listall').html(content);
		// linweiqin。默认选中对比框中的商品 Start ****************************************************************************************************
		var parityPriceProducts = $("#price_parity_content .product_item");
		for ( var i = 0; i < parityPriceProducts.length; i++) {
			var ppp = $(parityPriceProducts[i]);
			$("#product-listall #" + ppp.attr("id")).attr("checked",true).parent().css("background-position","-194px -28px");
		}
		// linweiqin。默认选中对比框中的商品 End ****************************************************************************************************
		
		//总页数小于2就不显示分页控件了，也不显示共几页。。。
		if(totalPage<2){	
			$('#jpagebar').hide();
			$(".pageinfo").hide();
		}
		//if(totalPage>=2){	
			$(function(){
				$.fn.jpagebar({
	                renderTo:$("DIV [name=jpagebar]"),	         			
					totalpage: totalPage,					
					currentPage: typeof(pageIndexSure)=='undefined' ? pageIndexUrl:parseInt($("#fowardPage").val()),						
					onClickPage : function(pageIndex){
						$.fn.setCurrentPage(this,pageIndex);			
						if(pagerInstance == null)
						   pagerInstance=this;
						//将当前页设为pageIndex,然后表单序列化
						$("#pageIndex").val(pageIndex);
						
						goSearchProduct(1);
						
						
			 		}			
				});
			});
	//	}
	}

	function acceptData(json){
	    
		$('#product-listall').html(""); 
	    if(json!=null&&json.success){
	        $.fn.setTotalPage(pagerInstance,json.totalPage);
	        var content=produce(json);
		  	$('#product-listall').html(content);
	    }
	}

	function produce(json){
		if(json.productlist==null){
			$('#jpagebar').hide();
			$(".pageinfo").hide();
		     var content='<div class="product-item">未查找到任何相关数据</div>';
		     return content;
		}
		var jsonCount=json.productlist.length;
		var productlist=json.productlist;
		var content ="";
				
		if(jsonCount!=0){	       
			for(var i=0; i<jsonCount; i++){
				content+='<div class="product-item" onselectstart="return false;" item-id='+productlist[i].id+'>';
				content+='<div class="product-content">';
				content+='<div class="product-img">';
				content+='<a target="_blank" href="'+ctx+'/product/'+productlist[i].id+'"><img alt="" style="width:210px;height:210px;" src='+file_ctx+'/file/'+productlist[i].pic1+' />' + (productlist[i].shopId == 0 ? '<img class = "ziying_icon_img" src = "' + ctx + '/images/ziying.png" />' : "")  + '</a>';
				content+='</div>';
				content+='<div class="product-about">';
				content+='<div class="pro-price">';
				content+='<span class="cuxiao">¥'+productlist[i].salePrice+'</span>';
				content+='<span class="origin">¥'+productlist[i].price+'</span>';
				content+='</div>';
				//content+='<div class="pro-num" stock="'+productlist[i].stock+'"  is-decimal="'+productlist[i].isDecimal+'" growth-count="'+productlist[i].growthCount+'" lower-limit="'+productlist[i].lowerLimit+'">';
				//content+='<input class="numberinput" type="text" value="'+productlist[i].lowerLimit+'" onkeyup="this.value=this.value.replace(/\D/g,\'\')" onafterpaste="this.value=this.value.replace(/\D/g,\'\')">';
				//content+='<div class="change-num">';
				//content+='<span class="addnum"></span>';
				//content+='<span class="cutnum"></span>';
				//content+='</div>';
				//content+='</div>';
				//content+='<div class="pro-cart">';
				//content+='<span class="cart"></span>';
				//content+='</div>';
				content+='<div class="pro-title">';
				content+='<span>'+productlist[i].name+'</span>';
				content+='</div>';
				content+='<div class="pro-bottom">';
				content+='<p class="productStatus">';
				content+='<span class="comment">';
				content+='<span class="comment-num">';
				content+='<span class="com-pic"></span>';
				content+='<em>'+productlist[i].proCommentNums+'</em>';
				content+='</span>';
				content+='</span>';
				content+='<span class="col-com">';
				content+='<span class="collect"></span>';
				content+='<span class="compare" onclick = "parityClick(this)"><input type = "checkbox" onchange = "parityCkChange(this)" style = "display:none;" id = "p' + productlist[i].id + '" pname = "' + productlist[i].name + '" ppic = "' + productlist[i].pic1 + '" pprice = "' + productlist[i].salePrice + '"></span>';
				content+='</span>';
				content+='</p>';
				content+='</div>';
				content+='</div>';
				content+='</div>';
				content+='</div>';
			}
			//显示商品类别
			
			
			$('#jpagebar').show();
			$(".pageinfo").show();
		}
		else{
			$('#jpagebar').hide();
			$(".pageinfo").hide();
		     content+='<div class="product-item">未查找到任何相关数据</div>';
		}
		return content;
	}

	function doError(){
	   $('#product-listall').html("请求出现错误，请重试");
	}	
}
//搜索商品的共用方法--end	
