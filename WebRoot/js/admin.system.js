//$(function(){
//	//-----------------------后台管理head.jsp页面导航栏效果   【Start】 -------------------------------------------
//	var initNavIndex = $(".tempNavActive").index(); // 当前选中项的索引位置
//	var ddWidth = $(".tempNavActive").width(); // dd标签的宽度
//	if(initNavIndex == -1){
//		$(".navActive").hide();
//		ddWidth = $(".topNavItem dd:eq(0)").width();
//	}
//	$(".navActive").css("left",ddWidth * initNavIndex);
//	$(".topNavItem dd").mouseover(function(){
//		var ddIndex = $(this).index();
//		var leftValue = ddWidth * ddIndex; // 位移增量
//		$(".navActive").stop(false,false).animate({
//			left : leftValue
//		},300);
//		if(initNavIndex == -1){
//			$(".topNavItem dd").removeClass("tempNavActive");
//			$(this).addClass("tempNavActive");
//			if(!$(".navActive").is(":visible")){
//				$(".navActive").stop(false,true).show();
//			}
//			return;
//		}
//		$(".tempNavActive").removeClass("tempNavActive");
//	}).mouseout(function(){
//		if(initNavIndex == -1){
//			return;
//		}
//		var leftValue = ddWidth * initNavIndex;
//		$(".navActive").stop(false,false).animate({
//			left : leftValue
//		},300);
//		$(".topNavItem dd:eq(" + initNavIndex + ")").addClass("tempNavActive");
//	});
//	//-----------------------后台管理head.jsp页面导航栏效果   【End】 -------------------------------------------
//	
//	
//	//-------------------------- 商品类别添加/修改 js --------------------------
//	//上传图片
//	 $('#uploadAppIcon').live('change', function() {    
//		var id = this.id;
//		//if(ajaxFn($(this))) return;
//		var suffix = getSuffix($(this).val()).toLowerCase();
//		if(suffix != '.jpg' && suffix != '.jpeg' && suffix != '.png' && suffix != '.gif'){
//			art.alert('只能上传后缀为：jpg、jpeg、png、gif 的图片');
//			return;
//		}
//		//执行上传操作....
//		$.ajaxFileUpload({
//	         url : ctx + '/ajaxfileupload',       //需要链接到服务器的地址
//	         ecureuri : false,
//	         fileElementId : id,  //文件选择框的id属性
//	         dataType : 'json',            //服务器返回的格式
//	         type : 'post',
//	         success: function (data){     
//	           	  if(!data.success){
//	           		  art.alert("上传失败，请重试");
//	           		  return;
//	           	  }
//	           	//处理后的文件名
//	           	$('#typeAppIcon').attr('src', ctx + '/file/' + data.saveName);
//	           	$('#appIcon').val(data.saveName);   
//	         },
//	         error: function (data, e) {
//	             art.alert("网络连接异常，上传文件失败，请稍后重试");
//	         }
//	         
//	     });
//	});
//	
//});
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