/* mall */

	var nowli;

$(function(){
	
	//选择切换背景 复选框
	$('.chk-label').live('click', function(event){
		//阻止默认事件 
		event.preventDefault();
		
		if($(this).hasClass('disable')) return;
		
		$(this).toggleClass('checked-label');  
		
		
		//全选按钮
		if($(this).hasClass('chkAll-label')){
			
			if($(this).hasClass('checked-label')){
				$('.chk-label').not('.disable').addClass('checked-label');
				$('.chk').not('.disable').attr('checked', true);
				//计算总价
				calTotal();
				return;
			}
			$('.chk-label').not('.disable').removeClass('checked-label');
			$('.chk').not('.disable').attr('checked', false);  
			//计算总价
			calTotal();
			return;
		}
		
		//父级全选按钮
		var parentClass = $(this).next().attr('parentClass');
		if(parentClass != '' && parentClass != 'undefined' && parentClass != null){
			if($(this).hasClass('checked-label')){
				$('.chk-label[childClass="'+parentClass+'"]').not('.disable').addClass('checked-label');
				$('.chk[childClass="'+parentClass+'"],.chk[parentClass="'+parentClass+'"]').not('.disable').attr('checked', true);
				seleclAll();
				//计算总价
				calTotal();
				return;
			} 
			//全选checked消失
			$('.chkAll-label').not('.disable').removeClass('checked-label');
			$('.chk-all').not('.disable').attr('checked', false);
			
			$('.chk-label[childClass="'+parentClass+'"]').not('.disable').removeClass('checked-label'); 
			$('.chk[childClass="'+parentClass+'"],.chk[parentClass="'+parentClass+'"]').not('.disable').attr('checked', false);  
			//计算总价
			calTotal();
			return;
		}
		
		//子级单选按钮
		var childClass = $(this).next().attr('childClass');
		if(childClass != '' && childClass != 'undefined'){
			if($(this).hasClass('checked-label')){   
				$(this).next().attr('checked', true);
				//父级 勾起
				if($('.chk-label[childClass="'+childClass+'"]').length == $('.chk[childClass="'+childClass+'"]:checked').length){
					$('.chk-label[parentClass="'+childClass+'"]').addClass('checked-label'); 
					$('.chk[parentClass="'+childClass+'"]').attr('checked', true);  
					seleclAll();
				}
				//计算总价
				calTotal();
				return;
			}   
			//全选失效
			$('.chkAll-label').removeClass('checked-label');
			$('.chk-all').attr('checked', false); 
			$('.chk-label[parentClass="'+childClass+'"]').removeClass('checked-label'); 
			$('.chk[parentClass="'+childClass+'"]').attr('checked', false);  
			
			$(this).next().attr('checked', false);  
			//计算总价
			calTotal();
		}
	});  
	
	//全选
	function seleclAll(){
		var labelChecked = $('.checked-label').length;
		var checkBox = $(':checked').length;  
		if(checkBox >= ($('.chk-label').length - 2) && labelChecked >= ($('.chk-label').length - 2)){
			//全选 勾起
			$('.chkAll-label').not('.disable').addClass('checked-label');
			$('.chk-all').not('.disable').attr('checked', true);
		}
	}
	
	//历史订单 -----------------------------------------------------------//
	//选择切换背景 复选框
	$('.history-chk-label').live('click', function(event){
		
		$(this).toggleClass('history-checked-label');  
		//阻止默认事件 
		event.preventDefault();
		
		//全选按钮
		if($(this).hasClass('history-chkAll-label')){
			
			if($(this).hasClass('history-checked-label')){
				$('.history-chk-label').addClass('history-checked-label');
				$('.history-chk').attr('checked', true);
				return;
			}
			$('.history-chk-label').removeClass('history-checked-label');
			$('.history-chk').attr('checked', false);  
			return;
		}
		
		//父级全选按钮
		var parentClass = $(this).next().attr('historyParentClass');
		if(parentClass != '' && parentClass != 'undefined' && parentClass != null){
			if($(this).hasClass('history-checked-label')){
				$('.history-chk-label[historyChildClass="'+parentClass+'"]').addClass('history-checked-label');
				$('.history-chk[historyChildClass="'+parentClass+'"],.history-chk[historyParentClass="'+parentClass+'"]').attr('checked', true);
				historySeleclAll();
				return;
			} 
			//全选checked消失
			$('.history-chkAll-label').removeClass('history-checked-label');
			$('.history-chk-all').attr('checked', false);
			
			$('.history-chk-label[historyChildClass="'+parentClass+'"]').removeClass('history-checked-label'); 
			$('.history-chk[historyChildClass="'+parentClass+'"],.history-chk[historyParentClass="'+parentClass+'"]').attr('checked', false);  
			return;
		}
		
		//子级单选按钮
		var childClass = $(this).next().attr('historyChildClass');
		if(childClass != '' && childClass != 'undefined'){
			if($(this).hasClass('history-checked-label')){   
				$(this).next().attr('checked', true);
				//父级 勾起
				if($('.history-chk-label[historyChildClass="'+childClass+'"]').length == $('.history-chk[historyChildClass="'+childClass+'"]:checked').length){
					$('.history-chk-label[historyParentClass="'+childClass+'"]').addClass('history-checked-label'); 
					$('.history-chk[historyParentClass="'+childClass+'"]').attr('checked', true);  
					historySeleclAll();
				}
				return;
			}   
			//全选失效
			$('.history-chkAll-label').removeClass('history-checked-label');
			$('.history-chk-all').attr('checked', false); 
			$('.history-chk-label[historyParentClass="'+childClass+'"]').removeClass('history-checked-label'); 
			$('.history-chk[historyParentClass="'+childClass+'"]').attr('checked', false);  
			
			$(this).next().attr('checked', false);  
		}
	});  
	
	//全选
	function historySeleclAll(){
		var labelChecked = $('.history-checked-label').length;
		var checkBox = $(':checked').length;  
		if(checkBox >= ($('.history-chk-label').length - 2) && labelChecked >= ($('.history-chk-label').length - 2)){
			//全选 勾起
			$('.history-chkAll-label').addClass('history-checked-label');
			$('.history-chk-all').attr('checked', true);
		}
	}
	
	
	//类别菜单蓝
	$('.goods-ul .first, .typehead').mouseover(function() {
		$('.nav-typeitem').show(); 
	});
	$('.nav-typeitem').mouseleave(function(event) {    
		$(this).hide();    
	});   
	
	$(".nav-typeitem ul li").hover(function(){
		//if(!nowli) 
			nowli=$(this);
		lefthoverInFun(nowli);  
		//$(this).find('.span-icon img').attr('src', _img.attr('hovericon')).css({'opacity' : ' 0.3', 'filter' : 'alpha(opacity=30)'});
		$(this).find('.type-icon').hide();
		$(this).find('.hover-icon').show(); 
		$(this).find('.litext').css('color', '#808080');
	},function(){
		//if(!nowli)
			nowli=$(this); 
		lefthoverOutFun(nowli);
		$(this).find('.type-icon').show();
		$(this).find('.hover-icon').hide();
		$(this).find('.litext').css('color', '#fff'); 
	});
	$('.nav-typesons').hover(function(){
		lefthoverInFun(nowli); 
	}, function(){
		lefthoverOutFun(nowli); 
	});
	
	/*鼠标悬浮在左侧商品分类*/
	function lefthoverInFun(nowli){
		$('.nav-typesons'+nowli.attr("type-id")).show();
		nowli.find('.litext').addClass('current-type-a');
		nowli.find('.lidiv').removeClass('li-lidiv');
		nowli.prev().find('.lidiv').removeClass('li-lidiv');
		nowli.addClass('currentli');
		nowli.find('.jiantou').hide();
	}
	function lefthoverOutFun(nowli){
		if(!nowli) return;
		nowli.find('.litext').removeClass('current-type-a');
		nowli.find('.lidiv').addClass('li-lidiv');
		nowli.prev().find('.lidiv').addClass('li-lidiv');
		nowli.removeClass('currentli');
		nowli.find('.jiantou').show(); 
		$('.nav-typesons'+nowli.attr("type-id")).hide(); 
		
	}
	
});
//按钮解除禁用
function relieveDisable() {
	if($('.goods-chk:checked').length <= 0 || $('.checked-label').length <= 0){
		$('#settle').addClass('btn-disabled');
		$('#planOrder').addClass('btn-disabled');
		return;
	}
	$('#settle').removeClass('btn-disabled');
	$('#planOrder').removeClass('btn-disabled');
}

//计算总价
function calTotal(){
	var totalPrice = 0.00; 
	relieveDisable();
	//计算总价
	if($('.goods-chk:checked').length > 0){
		for(var i = 0; i < $('.goods-chk:checked').length; i++){ 
			var _this = $('.goods-chk:checked:eq('+i+')').parents('.tr02');
			var p = parseFloat($(_this).find('.subtotalHide').val());
			totalPrice += p; 
			$(_this).find('.goods-quantity').val($(_this).find('input[name="quantity"]').val());
		}
		$('.cart-box02').find('.totalPrice').text('¥' + totalPrice.toFixed(2));
	}else{
		$('.cart-box02').find('.totalPrice').text('¥00.00');
	}
	$('#selectQuantity').text($('.goods-chk:checked').length);
	
}

/**
 * 数量控制输入
 */
function dNumber(_this, minCount, maxCount, growthCount, stock) {
	var quantity = parseFloat($(_this).val());
	//小于起购数量
	if (quantity <= minCount && minCount > 0) {
		$(_this).val(minCount);  
		return;
	}
	if (quantity <= 1 && minCount <= 0) {
		$(_this).val(1);
		return;
	}
	//大于限购数量
	if (maxCount > 0 && quantity >= maxCount) {
		$(_this).val(maxCount);
		return;
	}
	//大于库存
	if(quantity >= stock){
		$(_this).val(stock);
		return;
	} 
}
//输入框失去焦点
function dominateNumber(_this, growthCount) {
	var quantity = parseFloat($(_this).val()); 
	//如果输入的数不符合增长量
	if ( growthCount > 0 && (quantity % growthCount) != 0) {
		//四舍五入 
		var num = parseFloat(numberFormat(quantity / growthCount, 0));
		$(_this).val(num * growthCount);
	}
}






