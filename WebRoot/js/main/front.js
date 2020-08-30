
/* 前台js */

$(function(){
	
	//关闭dialog
	$('#cancel').click(function(){ 
		closeDialog('confirmAlert');
	});
	
	//左滚动
	$('.cart-box03 .left-p').click(function() {
		if (i <= 0) {
			i = 0;
			return;
		}
		i --;
		$('.cart-box03 .browse ul').stop(true, true).animate({ left : -(i * 1128) }, 400);
	});
	//右滚动
	$('.cart-box03 .right-p').click(function() {
		//滚动次数
		var n = 0;
		if ( (length % 6) > 0 ) {
			n = parseInt(length / 6) + 1;
		} else {
			n = parseInt(length / 6);
		}
		if (i >= (n - 1)) {
			return;
		}
		i ++;
		$('.cart-box03 .browse ul').stop(true, true).animate({ left : -(i * 1128) }, 400);
	}); 
	
	//促销
	$('.cu-promotion').live('mouseover', function() {
		$(this).find('.promotion-str').show().stop(true, true).animate({ height : 20}, 300);
	}).live('mouseout', function() {    
		$(this).find('.promotion-str').hide().stop(true, true).animate({ height : 20}, 300); 
	}); 
	
});

//滚动变量
var i = 0;
var length = 0;
function setUlWidth() {
	length = $('.cart-box03 .browse ul li').length;
	//设置ul宽度
	$('.cart-box03 .browse ul').width(length * 188); 
}

/**
 * 阻止 ajax 未响应反复提交
 * 
 * @param obj
 *            要阻止的控件名
 * @returns {Boolean}
 */
function ajaxFn(obj) {
	if ($(obj).hasClass("disable"))
		return true;
	$(obj).addClass("disable");
}

/**
 * alert 设置位置
 * @param width
 */
function initPosition(width){
	
	$('.d-content').css({'padding' : '0 25px', 'background-color' : '#fff'});
	$('.d-title').css({
		'text-indent' : 'none', 'font-size' : '12px', 'color' : '#4c5a5f', 'height' : '28px',
		'line-height' : '27px', 'background' : '#fff'}); 
	ieBrowser(width);
}
//设置最大宽度 （ie7 变形）
function ieBrowser(width, showAlert) {
	if(jQuery.browser.version=="6.0" || jQuery.browser.version=="7.0" || jQuery.browser.version=="8.0"){
		if (width > 0) {
			$('.d-outer').parent().css({'max-width' : width + 50 + 'px'});  
		} 
		if (showAlert) {
			$('.d-outer').parent().css({'max-width' : '300px'});
		}
		var bodyWidth = document.body.scrollWidth;
		var dailogWidth = $('.d-outer').width(); 
		var leftPosition = (bodyWidth - dailogWidth) / 2;   
		$('.d-outer').parent().css({left :  leftPosition}); 
	}
}

/**
 * zhangjunhao 
 * art.alert 选择提示框
 * @param fieldId  容器的id
 * @param title  提示title
 * @param msg  提示内容
 * @param btnId  确定按钮id
 * @param filed 
 * @returns
 */
function showConfirm(title, msg, btnId, index){

	$('.confirmDiv .title font').text(title);
	$('.confirmDiv .msg').text(msg);
	$('.confirmDiv .ok').attr('id', btnId).text('确定'); 
	$('.confirmDiv .ok').attr('index', index);  
	$('.confirmDiv .cancel').text('关闭');
	showDialog('confirmAlert', '');
	$('.hideDialogTr').hide();
	$('.d-content').css({'padding' : '12px 32px 25px 32px', 'background-color' : '#f3f1f2'}); 
	$('.d-title').css({ 
		'text-indent' : '45px', 'font-size' : '18px', 'color' : '#fff', 'height' : '41px', 
		'line-height' : '41px', 'background' : 'url("'+ctx+'/images/9_03.png") repeat-x'});
	//初始化位置   
	initPosition($('#confirmAlert').width()); 
}
//alert
function showConfirmAlert(fieldId, title, msg, btnId){
	alertContent = '<div id="'+fieldId+'" class="confirmDiv">'+
		'<p class="title"><B><font>'+title+'</font></B></p>'+ 
		'<p class="msg">'+msg+'</p>'+  
		'<p class="btn">'+
			'<span class="ok" id="'+btnId+'">确定</span>'+
			'<span class="cancel" id="cancelAlert">关闭</span>'+ 
		'</p>'+
	'</div>';  
	art.alert(alertContent);    
	//初始化位置   
	initPosition($('#confirmAlert').width()); 
}
/* <div class="alertDiv">
<p class="title"><B><font>填写制单人信息</font></B></p>
<p class="btn">
	<label for="alert-text">姓名：</label><input type="text" id="alert-text" value="" name="" /> 
	<span>确定</span>
</p>
</div> 
<div class="commonDiv">
<p class="title"><B><font>操作成功</font></B></p>
<p class="btn">
	<span class="ok" class="commonOk">确定</span>
</p>
</div> */
/**
 * 通用 alert 
 * @param title 提示信息 如：操作成功
 * @returns
 */
function showCommonAlert(title){
	alertContent = '<div class="commonDiv" id="commonDiv">'+
						'<p class="title"><B><font>'+title+'</font></B></p>'+
						'<p class="btn">'+
							'<span class="ok" id="commonOk">确定</span>'+
						'</p>'+
					'</div>';  
	//return alertContent;
	art.alert(alertContent);  
	//初始化位置  
	initPosition($('#commonDiv').width());
}

//固定text提示
function textPrompt(obj, text, promptMsg) { 
	if ($.trim($(obj).val()) == '') {
		$(text).show().html(promptMsg);
		return false; 
	}
	$(text).hide().html('');
	return true;
}

/* qq空间分享代码 */
function qzoneShare(url, desc, summary, title, site, pics) {
	var p = {
			url:document.location,/*分享地址*/
			showcount:'1',/*是否显示分享总数,显示：'1'，不显示：'0' */
			desc:desc,/*默认分享理由(可选)*/
			summary:summary,/*分享摘要(可选)*/
			title:title,/*分享标题(可选)*/
			site:site,/*分享来源 如：腾讯网(可选)*/
			pics:pics /*分享图片的路径(可选)*/
			/*style:'201', 
			width:113,
			height:39*/
	};
	var s = [];
	for(var i in p){
		if (p[i] == '' || !p[i])
			continue;
		s.push(i + '=' + encodeURIComponent(p[i]||'')); 
	}
	var url = "http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?"+s.join('&');
	//document.write(['<a version="1.0" class="qzOpenerDiv" href="',,'" target="_blank">分享</a>'].join(''));
	window.open(url);
}

