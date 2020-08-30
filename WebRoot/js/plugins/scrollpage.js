/**
 * 分页插件（主要用于手机wap端的滚动分页。）
 * 说明：该插件主要get方式查询的商品搜索。（例如 每选择或剔除一个条件时。都是一个访问服务器 (刷新页面) 的操作）
 * @author linweiqin
 * @createDate 2015-03-16
 * 例子：
   $(".prolist").initScrollPage({
 		url : ctx + '/mobile/product/search/page',
 		defaultCond : {
			pageSize:10,
			pageNumber:1
		},
		distanceBottom : 10, // 滚动条距离底部的长度。（可不填）
		htmlContent : function(data){ // 加载的数据每一个对象在页面展示的html代码。
			var html = '';
			for ( var i = 0; i < data.products.length; i++) {
				var p = data.products[i];
				html += '<div class="proitem">' +
							'<a><img src="' + ctx + '/file/' + p.pic1 + '" alt="" /></a>' +
							'<div class="protext">' +
								'<p class="proname">' + p.name + '</p>' +
								'<p class="proprice">￥' + p.price + '</p>' +
							'</div>' +
						'</div>';
			}
			return html;
		}
	});
 */
$.ajaxSetup({
    contentType: "application/x-www-form-urlencoded; charset=utf-8"
});
var DataDeal = {
//将从form中通过$('#form').serialize()获取的值转成json
           formToJson: function (data) {
               data=data.replace(/&/g,"\",\"");
               data=data.replace(/=/g,"\":\"");
               data="{\""+data+"\"}";
               return data;
            },
};

(function($){
	var boxMap = {};
	var totalResultsMap = {};
	// 默认配置
	var defaultConfig = {
			url : null,
			defaultCond : {
				pageSize:5,
				pageNumber:1
			},
			distanceBottom : 10, // 滚动条距离底部的长度。 非必填
			htmlContent : function(data){ // 加载的数据每一个对象在页面展示的html代码。
				return "<div>调用initScrollPage()时，未给htmlContent方法赋值</div>";
			},
			queryFormId : null, // 查询区域的form表单id ，可不填。
			dataContainer : null // 容器对象，可不填。
	};
	
	var CtrlCondJsonConfig = {
		text : "blur",
		checkbox : "change",
		select : "change",
		radio : "change",
		button : "click"
	};
	
	$.fn.initScrollPage = function(config){
		var newConfig = boxMap[$(this).selector];
		if(!newConfig){
			newConfig = ($.isPlainObject(config) || !config) ? $.extend(true,{},defaultConfig,config) : $.extend({},defaultConfig);
			if(!newConfig.dataContainer){
				newConfig.dataContainer = $(this);
			}
			boxMap[$(this).selector] = newConfig;
		}
		// 初始加载数据
		loadData(newConfig);
	    $(window).scroll(function(){
	    	
	        //$(window).scrollTop()这个方法是当前滚动条滚动的距离
	        //$(window).height()获取当前窗体的高度
	        //$(document).height()获取当前文档的高度
	        var bot = newConfig.distanceBottom; //bot是距离底部的高度
	        if (( bot + $(window).scrollTop() ) >= ( $(document).height() - $(window).height() )) {
	           //当底部基本距离  + 滚动的高度  >= 文档的高度 - 窗体的高度时
	            //就需要去加载数据了 
	        	loadData(newConfig);
	        }
	    });
	};
	/**
	 * 参数：
	 * 1. jsonConfig 例如：{text:"blur",checkbox:"change",radio:"change",select:"change"}
	 */
	$.fn.CtrlScrollPageCondQuery = function(jsonConfig){
		var config = ($.isPlainObject(jsonConfig) || !jsonConfig) ? $.extend(true,{},CtrlCondJsonConfig,jsonConfig) : $.extend({},CtrlCondJsonConfig);
		CtrlCondJsonConfig = config;
		for ( var i = 0; i < $(this).length; i++) {
			var obj = $($(this)[i]);
			for ( var key in CtrlCondJsonConfig) {
				if(key != 'select' && obj.is(":" + key)){
					obj.bind(CtrlCondJsonConfig[key],function(){
						condCommonMethod(this);
					});
					break;
				}else if(obj.is(key)){
					obj.live(CtrlCondJsonConfig[key],function(){
						condCommonMethod(this);
					});
					break;
				}
			}
		}
		
	};
	
	$.fn.btnScrollPageCondQuery = function(){
		$(this).bind("click",function(){
			condCommonMethod(this);
		});
		$(".btn_reset").click(function(){
			$(this).parents("form")[0].reset();
		});
	};
	
	function condCommonMethod(obj){
		if($(obj).parents("form") == 0){
			alert("致开发人员：条件查询区域未找到form表单");
			return;
		}
		var config = null;
		for ( var key in boxMap) {
			if($(obj).parents(boxMap[key])){
				config = boxMap[key];
				break;
			}
		}
		var dataCond = null;
		var condStr = $(obj).parents("form").serialize();
		if(condStr && condStr.length > 0)
			dataCond = condStr + "&pageNumber=1&pageSize=" + config.defaultCond.pageSize;
		else{
			dataCond = "pageNumber=1&pageSize=" + config.defaultCond.pageSize;
		}
		if(ajaxFn(config.dataContainer)) return;
		isShowLoading(config.dataContainer,true);
		console.log(dataCond)
		$.ajax({
			async:false,
			url : config.url,
			dataType :"json",
			type : "post",
			data:dataCond,
			success : function(data){
				if(!data.success){
					art.alert(data.info);
					$(config.dataContainer).removeClass("disabled");
					isShowLoading(config.dataContainer,false);
					return;
				}
				var tempHtmlObjs = $(config.htmlContent(data));
				for ( var i = 0; i < tempHtmlObjs.length; i++) {
					$(tempHtmlObjs[i]).addClass("lw_scroll_page_q_dt");
				}
				$(config.dataContainer).html($("<div></div>").append(tempHtmlObjs).html());
				totalResultsMap[config.dataContainer.selector] = data.totalResults;
				// 重置页码
				config.defaultCond.pageNumber = 1;
				$(config.dataContainer).removeClass("disabled");
				isShowLoading(config.dataContainer,false);
			},
			error : function(data){
				$(config.dataContainer).removeClass("disabled");
				isShowLoading(config.dataContainer,false);
				art.alert("数据初始化时错误");
			}
		});
	}
	function loadData(config){
		
		var totalResults = totalResultsMap[$(config.dataContainer).selector];
		if(totalResults <= $(".lw_scroll_page_q_dt").length){
			return;
		}
		var condStr = $("#" + config.defaultCond.queryFormId).serialize();
		var formDataJson = DataDeal.formToJson(condStr);
		config.defaultCond = $.extend(true,{},config.defaultCond,formDataJson);
		if(ajaxFn(config.dataContainer)) return;
		isShowLoading(config.dataContainer,true);

        $.ajax({
        	url : config.url,
        	type : 'post',
        	dataType : 'json',
        	data : config.defaultCond,
        	success : function(data){
				if(!data.success){
					art.alert(data.info);
					$(config.dataContainer).removeClass("disabled");
					isShowLoading(config.dataContainer,false);
					return;
				}
				config.defaultCond.pageNumber += 1;
				totalResultsMap[config.dataContainer.selector] = data.totalResults;
				var tempHtmlObjs = $(config.htmlContent(data));
				for ( var i = 0; i < tempHtmlObjs.length; i++) {
					$(tempHtmlObjs[i]).addClass("lw_scroll_page_q_dt");
				}
				config.dataContainer.append($("<div></div>").append(tempHtmlObjs).html());
				$(config.dataContainer).removeClass("disabled");
				isShowLoading(config.dataContainer,false);
			},
			error : function(data){
				art.alert("数据初始化时错误");
				$(config.dataContainer).removeClass("disabled");
				isShowLoading(config.dataContainer,false);
			}
        });
	}
	
	function isShowLoading(boxObj,bool){
		if(bool){
			$(boxObj).append('<div class = "temp_loading_cls" style = "background-color:#f2f2f2;text-align:center;padding:10px 10px;"><img src = "' + ctx + '/images/loading2.gif" style = "height:20px;vertical-align:bottom;">&nbsp;加载中请稍候…</div>');
		}else{
			$(".temp_loading_cls").remove();
		}
	}
	
	function ajaxFn(obj){
		if($(obj).hasClass("disabled")) 
			return true; 
		$(obj).addClass("disabled");
	}

})(jQuery);