/**
 *  linweiqin

 *  pageBar进一步封装的方法，开发员在调用时，可以这样调用：
 * 	▓▓▓▓▓▓▓▓▓▓▓▓ 例子（demo）：
 	$(".dataContent").initPageBar({
		url : ctx + "/admin/product/list-product", // 数据加载所调用的url
		ctrlContent : $(".pageBar"), // 分页控件的容器对象，分页空间指的是：上一页，下一页，首页，尾页……等
		htmlContent : function(data){// 拼接返回的数据作为页面展示的html代码。
			var html = "";
			for ( var i = 0; i < data.products.length; i++) {
				var product = data.products[i];
				html += '<dl class = "productItem' + (i%2 == 0 ? ' oddProduct' : '') + '">' + 
							'<dd style = "width:60px;padding:0 7px;">' + 
								'<img src = "${ctx}/file/' + product.pic1 + '" style = "width:60px;height:60px;">' + 
							'</dd><dd style = "width:227px;">' + 
								'<a href = "#" class = "textOverflowElipsis" style = "width:198px;display:block;">' + product.name + '</a>' + 
							'</dd><dd style = "width:114px;">' + 
								'<span style = "color:#f40;font-weight:bold;">￥' + product.salePrice + '</span>' + 
							'</dd><dd style = "width:71px;">' + 
								product.quantity + 
							'</dd><dd style = "width:70px;">' + 
								product.saleQuantity + 
							'</dd><dd style = "width:80px;">' + 
								product.createDate + 
							'</dd><dd>' + 
								'<a class = "btn" href = "#">编辑</a><br/>' + 
								'<a class = "shelves" href = "javascript:void(0)">下架</a>' + 
								'<a class = "delete" href = "javascript:void(0)">删除</a>' + 
							'</dd>' + 
						'</dl>';
			}
				return html;
			}
		},
		{
			pageNumber : 1,
			pageSize : 5,
			orderColumn : 1,
			orderBy : 1
		});
		
	▓▓▓▓▓▓▓▓▓▓▓▓ 条件查询例子（Conditions query demo）：
	
	//以下方法需要满足条件：所有查询相关的表单控件是在form表单中的
	CtrlPageBarCondQuery两种写法，参数可不填，若要绑定跟默认值不同的事件才需要填，该参数默认值为：
	text : "blur",
	checkbox : "change",
	select : "change",
	radio : "change",
	button : "click"
	
	// 按钮事件
	$("#btnQuery").btnPageBarCondQuery();
	// 参数为json对象
		//此写法是属于直接默认的事件类型。例如，select默认是change事件，默认值参照46~51行
		1、$(":radio").CtrlPageBarCondQuery();
		
		//此写法属于页面上涉及到多种控件，$(":radio,select,:checkbox")该写法表示：页面上的radio，select，checkbox这3种控件都参与条件查询
		2、$(":radio,select,:checkbox").CtrlPageBarCondQuery(
				//下面这个json对象表示的是：默认的事件不符合需求，所以重新写，换成自己所需要的。
				{
					select : "click",
					text : "keyup"
				}
			);
 */
(function($) {
	var defaultItems = {
		url : 'notUrl', // 数据加载所调用的url
		ctrlContent : $(".pageBar"),  // 分页控件的容器对象，分页空间指的是：上一页，下一页，首页，尾页……等
		htmlContent : function(data){ // 加载的数据每一个对象在页面展示的html代码。
			return "<div>initPageBar时，未给htmlContent方法赋值</div>";
		},
		isLoadData : true
	};
	var defaultCond = {
		pageNumber : 1,
		pageSize : 6
	};
	var pageCount = 1;
	
	var CtrlCondJsonConfig = {
		text : "blur",
		checkbox : "change",
		select : "change",
		radio : "change",
		button : "click"
	};
	/**
	 * 调用例子请看当前文件中，第1 ~ 44 行的注释。
	 */
	$.fn.initPageBar = function(opt,queryCond) {
		var items = ($.isPlainObject(opt) || !opt) ? $.extend(true,{},defaultItems,opt) : $.extend({},defaultItems);
		items["dataContainer"] = $(this);
		defaultItems = items;
		var dataCond =  ($.isPlainObject(queryCond) || !queryCond) ? $.extend(true,{},defaultCond,queryCond) : $.extend({},defaultCond);
		defaultCond = dataCond;
		// 页面数据加载。如不需要，则在initPageBar时，将isLoadData的值赋为false
		if(items.isLoadData){
			$.ajax({
				async:false,
				url : items.url,
				dataType :"json",
				type : "post",
				data:dataCond,
				success : function(data){
					if(!data.success){
						art.alert(data.info);
						return;
					}
					items.dataContainer.html(items.htmlContent(data));
					pageCount = parseInt(data.totalResults % dataCond.pageSize == 0 ? data.totalResults / dataCond.pageSize : data.totalResults / dataCond.pageSize + 1);
				},
				error : function(){
					art.alert("数据初始化时错误");
				}
			});
		}
		jpagebar(items.dataContainer,defaultCond);
	};
	/**
	 * 参数：
	 * 1. jsonConfig 例如：{text:"blur",checkbox:"change",radio:"change",select:"change"}
	 */
	$.fn.CtrlPageBarCondQuery = function(jsonConfig){
		$(this).parents("form").attr("onkeydown","return formKeyDown(this)");
		var config = ($.isPlainObject(jsonConfig) || !jsonConfig) ? $.extend(true,{},CtrlCondJsonConfig,jsonConfig) : $.extend({},CtrlCondJsonConfig);
		CtrlCondJsonConfig = config;
		for ( var i = 0; i < $(this).length; i++) {
			var obj = $($(this)[i]);
			for ( var key in CtrlCondJsonConfig) {
				if(key != 'select' && obj.is(":" + key)){
					obj.live(CtrlCondJsonConfig[key],function(){
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
	
	$.fn.btnPageBarCondQuery = function(){
		var btnObj = $(this);
		$(this).parents("form").keydown(function(event){
			var keyNum = event.which || event.keyCode;
			if(keyNum == 13){
				btnObj.click();
				return false;
			}
			return true;
		});
		$(this).live("click",function(){
			condCommonMethod(this);
		});
		$(".btn_reset").click(function(){
			$(this).parents("form")[0].reset();
		});
	};
	/**
	 * 方法调用例子  $.reloadPageBarData($("#btnQuery"));
	 * 相当于调用查询区中的按钮进行再一次查询
	 * （也可以是查询区中的其他控件。例如：$.reloadPageBarData($("#queryForm input:eq(0)")); ）
	 */
	$.reloadPageBarData = function(obj){
		condCommonMethod(obj);
	};
	
	
	function condCommonMethod(obj){
		if($(obj).parents("form") == 0){
			alert("致开发人员：条件查询区域未找到form表单，必须由form表单包住整个查询区");
			return; 
		}
		var dataCond = '';
		var condStr = $(obj).parents("form").serialize();
		if(condStr && condStr.length > 0){
			var pageSizeIndex = condStr.indexOf("pageSize=");
			if(pageSizeIndex > -1){
				var pageSizeStr = condStr.substring(pageSizeIndex);
				var pageSize = parseInt(pageSizeStr.split("&")[0].split("=")[1]);
				defaultCond.pageSize = pageSize;
			}
			for ( var key in defaultCond) {
				var keyIndex = condStr.indexOf(key + "=");
				if(keyIndex > -1){
					continue;
				}
				dataCond += "&" + key + "=" + defaultCond[key];
			}
			dataCond = condStr + dataCond;
		}
		else{
			for ( var key in defaultCond) {
				var keyIndex = condStr.indexOf(key + "=");
				if(keyIndex > -1){
					continue;
				}
				dataCond += "&" + key + "=" + defaultCond[key];
			}
			if(dataCond.length > 0){
				dataCond = dataCond.substring(1);
			}
		}

		if(ajaxFn(defaultItems.dataContainer)) return;
		$.ajax({
			async:false,
			url : defaultItems.url,
			dataType :"json",
			type : "post",
			data:dataCond,
			success : function(data){
				if(!data.success){
					art.alert(data.info);
					$(defaultItems.dataContainer).removeClass("disabled");
					return;
				}
				$(defaultItems.dataContainer).html(defaultItems.htmlContent(data));
				pageCount = parseInt(data.totalResults % defaultCond.pageSize == 0 ? data.totalResults / defaultCond.pageSize : data.totalResults / defaultCond.pageSize + 1);
				if(pageCount == 0){
					pageCount = 1;
				}
				$(defaultItems.dataContainer).removeClass("disabled");
			},
			error : function(data){
				$(defaultItems.dataContainer).removeClass("disabled");
				art.alert("数据初始化时错误");
			}
		});

		jpagebar($(defaultItems.dataContainer),dataCond);
	}
	
	function jpagebar(dataContainer,queryCond){
		$.fn.jpagebar({
			renderTo : defaultItems.ctrlContent,
			totalpage : pageCount,
			currentPage : 1,
			/*pagebarCssName : 'pagebarCss',*/ 
			pageNameCssName : 'pageName', 
			onClickPage : function(pageNumber) {
				$.fn.setCurrentPage(this,pageNumber);
				if(typeof(queryCond) == "string"){
					queryCond = queryCond.substring(0,queryCond.indexOf("&pageNumber")) + "&pageNumber=" + pageNumber;
				}else{
					defaultCond.pageNumber = pageNumber;
				}
				if(ajaxFn(dataContainer)) return;
				$.ajax({
					url : defaultItems.url,
					dataType : "json",
					type : "post",
					data :queryCond,
					success : function(data){
						if(!data.success){
							art.alert(data.info);
							$(dataContainer).removeClass("disabled");
							return;
						}
						dataContainer.html(defaultItems.htmlContent(data));
						$(dataContainer).removeClass("disabled");
					},
					error : function(data){
						art.alert("分页数据加载错误");
						$(dataContainer).removeClass("disabled");
					}
				});
			}
		});
	}
	
	function ajaxFn(obj){
		if($(obj).hasClass("disabled")) 
			return true; 
		$(obj).addClass("disabled");
	}

})(jQuery);

function formKeyDown(_this){
	var keyNum = event.which || event.keyCode;
	return keyNum==13 ? false : true;
}