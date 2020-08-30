/** linweiqin Start  (注意..本项目用的是jquery.js，也就是版本1.8以上的。像live。和ajax的写法都与之前有所差别) **/
window.autoCompleteResult = null;
$(function($){
	$("#loadingFull").html('loading . . .<br/><img src="' + ctx + '/admin/images/loading4.gif" />');
	$(document).ajaxStart(function(){
		 $("#loadingFull").css({"display":"block","left":$(window).width()/2 - 90 + "px" , "top":$(window).height()/2 - 25}); //通过获取window的height和width计算去让div居中
	}).ajaxStop(function(){
		 $("#loadingFull").css("display","none");
	});
	
	// 调整页面的header、footer 等 还有 table的宽度
	$("#header").css("width",parseInt(window.screen.width) - 21);
	$("#footer").css("width",parseInt(window.screen.width) - 21 - 295);
	$("#breadcrumb").css("width",parseInt(window.screen.width) - 21 - 261);
	$(".contentcontainer").css("width",parseInt(window.screen.width) - 21 - 270);
	
	
	$(".checkboxCommonClick").live('click',function(event){
		var ckInput = $(this).find("input:checkbox");
		var nodeName = event.srcElement.nodeName.toUpperCase();
		var inputType = event.srcElement.type;
		if(inputType){
			inputType = inputType.toUpperCase();
		}
		if(nodeName == 'A' || (nodeName == 'INPUT' && inputType == 'CHECKBOX')){
			return;
		}
		var ckbl = !$(ckInput).attr("checked");
		$(ckInput).attr("checked",ckbl).change();
	});
	
	
	//alert(${shopInfo.shopScore})

	//关于点击商品列表的排序-linlijun
	$("div#rankOpDiv .sort_b a").live("click",function(){
		$(this).siblings().removeClass("cur");
		$(this).siblings().find("span").removeClass("ico2");
		$(this).siblings().find("span").addClass("ico");
		
		$(this).addClass("cur");
		$(this).find("span").addClass("ico2");
		
	}); 
	
	//js日期格式化
	Date.prototype.Format = function(fmt)   
	{  
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}  
		
});
// 提示消息框。渐显渐隐 停留4秒 ；message : 要提示的信息；isClose：true或false，true表示不自动隐藏
function promptMessage (message,iconstr,isClose){
	var imgName = "";
	if(iconstr)
		imgName = iconstr + ".gif";
	$("#promptMessage")
	.html('<div class = "pmCdDiv"><img src = "' + ctx +'/adminfile/images/' + imgName + '" />&nbsp;&nbsp;' + message + '</div>')
	.css({"display":"block","left":$(window).width()/2 - 200 + "px" , "top":$(window).height()/2 - 40})
	.fadeIn(600);
	if(isClose){
		$("#promptMessage").append('<span class = "promptClose" title = "点击关闭">×</span>');
	}else{
		$("#promptMessage")
		.stop(true,true)
		.fadeIn()
		.delay(2000)
		.fadeOut(1000);
	}
}

$(".promptClose").live('click',function(){
	$(this).parents("#promptMessage").fadeOut(200);
});
// 跳出dialog  elementId:div层的id名称  ；dialogTitle:弹出来的dialog名称（主题）
function showDialog(elementId,dialogTitle,okFunction){
		dialog = art.dialog.get(elementId);
		if(!dialog){
			dialog = art.dialog.get(elementId);
		    if(dialog)
		        dialog.close();

		    opt = {
			        id : elementId,
			        title : dialogTitle,
			        content : document.getElementById(elementId),
			        cancel : true,
		            padding : 5, 
		            lock : true
			   	};
		    
            newOpt = ($.isPlainObject(okFunction) || !okFunction) ? $.extend(true,{},opt,okFunction) : $.extend({},opt);
			art.dialog(newOpt);
		}
		$(".d-content").css("padding","4px 18px");
}
// 关闭模式对话窗    elementId:div层的id名称
function closeDialog(elementId){
	dialog = art.dialog.get(elementId);
	dialog.close();
}

//跳出dialog  elementId:div层的id名称  ；dialogTitle:弹出来的dialog名称（主题）
function commonShowDialog(elementId,dialogTitle,functionName){
		dialog = art.dialog.get(elementId);
		if(!dialog){
			dialog = art.dialog.get(elementId);
		    if(dialog)
		        dialog.close();
			art.dialog({
		        id : elementId,
		        title : dialogTitle,
		        content : document.getElementById(elementId),
		        lock: true,
		        cancel:true,
		        ok:function(){
		        	functionName();
		        	return false;
		        }
		   	});
		}
}
function openDialog(elementId,dialogTitle){
	dialog = art.dialog.get(elementId);
	if(!dialog){
		dialog = art.dialog.get(elementId);
	    if(dialog)
	        dialog.close();

	    opt = {
		        id : elementId,
		        title : dialogTitle,
		        content : document.getElementById(elementId),
	            padding : 5, 
	            lock : true
		   	};
	    
        newOpt = $.extend({},opt);
		art.dialog(newOpt);
	}
	$(".d-content").css("padding","4px 18px");
}

function numberInputBind(){
	$(".numberInput").live("keydown",function (event) {/*禁用 Ctrl + V*/
		if(isNaN($(this).val())){
			$(this).val("");
		}
		if ( event.which == 17 || event.which == 86) {
			return false;
		}
		return true;
	}).live("keypress",function (event) {
		var curVal = $(this).val();
		if (event.which >= 48 && event.which <= 57 || event.which == 46 || event.which == 8) {
			if (curVal.indexOf(".") != -1 && event.which == 46) return false; /*控制只能输入一个小数点*/
			if (curVal == "0" && event.which != 46) { $(this).val(""); return true; } /*控制第一位是0时 输入数字后将0删掉*/
			if (curVal == "0" && event.which != 46) return false; /*控制第一位输入0其后必须是小数点*/
			if (curVal == "" && event.which == 46) $(this).val("0"); /*第一位输入小数点 将前一位补0*/
		} else return false;
	}).live("blur",function(){
		if($(this).attr("readonly")){
			return;
		}
		if(isNaN($(this).val()) || $.trim($(this).val()) == ''){
			$(this).val("1");
		}
	});
}

/**
 * 四舍五入
 * num：数值
 * n：小数保留的位数
 */
function numberFormat(num,n){
    var  dd=1;  
    var  tempnum;  
    for(var i=0;i<n;i++){
        dd*=10;  
    }  
    tempnum = num*dd;
    tempnum = Math.round(tempnum); 
    return tempnum / dd;
}
/**
 * 保留2位小數，可四捨五入
 * @param x
 * @returns
 */
function changeTwoDecimal_f(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        alert('function:changeTwoDecimal->parameter error');
        return false;
    }
    var f_x = Math.round(x * 100) / 100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
}

/**
 * 
 * element : 要使用autoComplete的元素
 * url : controller对应的requestMapping
 * hiddenElement : 隐藏的元素，为隐藏的input赋id
 * listName : controller传过来的result里面的集合对象名称，例如 : data.userInfos，这时,listName就是"userInfos"
 * returnItem : 要显示的字符串格式。例如 : '"<i>" + data.account + "</i><i>" + data.realName + "</i>" '
 * jsonExtraParams : 额外的参数，例如 : {accountType:2}这样的1个json对象
 * parsedItem : 字符串，返回结果显示的 字段名。例如 : "account"
 * **/
function autoComplete(element,url,hiddenElement,listName,returnItem,jsonExtraParams,datasField){
	$(element).autocomplete(ctx + url, {
		minChars: 0,
		matchContains: true,
		selectFirst: true,
		mustMatch : true,
		scroll: true,
		width : 166,
		isManyQuery :true,
		isNotResetInputValue:true,
		hideResultsIsLoad:true, 
		extraParams:jsonExtraParams,
		parse:function(data) {//解释返回的数据，把其存在数组里
			var datas = eval("data."+listName);
	        var parsed = [];
	        for (var i = 0; i < datas.length; i++) { 
	        	parsed[parsed.length] = {
	        			data: datas[i],
	        			value: datas[i][datasField],
	    	            result: datas[i][datasField]//返回的结果显示内容  
	    	    };  
	        }
	        return parsed;
	    },
	    formatItem: function(data, i, n, value) {
			return eval(returnItem);
		}
	}).result(function(event, data) {
		if(data){
			autoCompleteResult = data;
			$(element).val(data[datasField]);
			if($(hiddenElement))
				$(hiddenElement).val(data.id);
		}else{
			$(element).val("");
			if($(hiddenElement))
				$(hiddenElement).val("");
		}
	});
}
/** linweiqin End **/


/** 格式化时间 */
var decorateTime = function(dateNumber, friendly) {
    // friendly = true;
    var date = new Date(dateNumber);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();

    if (friendly) {
        var now = new Date();
        var mseconds = -(date.getTime() - now.getTime());
        var time_std = [ 1000, 60 * 1000, 60 * 60 * 1000, 24 * 60 * 60 * 1000 ];
        if (mseconds < time_std[3]) {
            if (mseconds > 0 && mseconds < time_std[1]) {
                return Math.floor(mseconds / time_std[0]).toString() + ' 秒前';
            }
            if (mseconds > time_std[1] && mseconds < time_std[2]) {
                return Math.floor(mseconds / time_std[1]).toString() + ' 分钟前';
            }
            if (mseconds > time_std[2]) {
                return Math.floor(mseconds / time_std[2]).toString() + ' 小时前';
            }
        }
    }

    hour = ((hour < 10) ? '0' : '') + hour;
    minute = ((minute < 10) ? '0' : '') + minute;
    second = ((second < 10) ? '0' : '') + second;

    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
};

//~~~~~~~~~~~~~~~~正则验证 begin~~~~~~~~~~~~~~~~/
var REG = {
      name  :  /^[a-zA-Z0-9_]+$/,
      fileName  :  /^[^\/\\|:?*<>\"]+$/
};


function isFileNameLegal(fileName) {
    var pFileName = /^[^\/\\|:?*<>"]+$/;
    var result = pFileName.test(fileName);
    if (!result) {
    	art.alert('文件名不能包含以下字符，/\|:?*<>"');
    }
    return result;
}


/**验证URL**/
function IsURL(str_url){
    var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
	    + "?(([0-9a-z_!~*'().&amp;=+$%-]+: )?[0-9a-z_!~*'().&amp;=+$%-]+@)?" //ftp的user@
	    + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
	    + "|" // 允许IP和DOMAIN（域名）
	    + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
	    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
	    + "[a-z]{2,6})" // first level domain- .com or .museum
	    + "(:[0-9]{1,4})?" // 端口- :80
	    + "((/?)|" // a slash isn't required if there is no file name
	    + "(/[0-9a-z_!~*'().;?:@&amp;=+$,%#-]+)+/?)$";
    var re=new RegExp(strRegex);
    //re.test()
    if (re.test(str_url)){
            return true;
    }else{
            return false;
    }
}

//~~~~~~~~~~~~~~~~正则验证 end~~~~~~~~~~~~~~~~/

function setCookieWithDays(c_name, value, expiredays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + expiredays);
	document.cookie = c_name + "=" + escape(value)
			+ ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
}
// 使用方法：setCookie('username','Darren',30)

function setCookie(name,value){
	setCookieWithDays(name,value,30);
}

function getCookie(c_name) {
	if (document.cookie.length > 0) {// 先查询cookie是否为空，为空就return""
		c_start = document.cookie.indexOf(c_name + "=");// 通过String对象的indexOf()来检查这个cookie是否存在，不存在就为-1
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1;// 最后这个+1其实就是表示"="号啦，这样就获取到了cookie值的开始位置
			c_end = document.cookie.indexOf(";", c_start);// 其实我刚看见indexOf()第二个参数的时候猛然有点晕，后来想起来表示指定的开始索引的位置...这句是为了得到值的结束位置。因为需要考虑是否是最后一项，所以通过";"号是否存在来判断
			if (c_end == -1)
				c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));// 通过substring()得到了值。想了解unescape()得先知道escape()是做什么的，都是很重要的基础，想了解的可以搜索下，在文章结尾处也会进行讲解cookie编码细节
		}
	}
	return "";
}

/**
 * 图片找不到链接时默认加载新图片
 */
(function($){
	$.fn.imgErrorReload=function(customImage){
		var defaultImage = customImage || ctx + "/file/default.jpg";
		var isChrome = window.navigator.userAgent.indexOf("Chrome") !== -1;
		if (isChrome) {
			this.each(function() {
				var error = false;
	            if (!this.complete) {
	                error = true;
	            }
	            if (typeof this.naturalWidth != "undefined" && this.naturalWidth == 0) {
	                error = true;
	            }
	            if(error){
	            	 $(this).bind('error.replaceSrc',function(){
	                     this.src = defaultImage;
	                     $(this).unbind('error.replaceSrc');
	                 }).trigger('load');
	            }
	
	    	});//each End
			return ;
		}
		
//		return this.each(function() {
//			var error = false;
//            if (!this.complete) {
//                error = true;
//            }
//            if (typeof this.naturalWidth != "undefined" && this.naturalWidth == 0) {
//                error = true;
//            }
//            if(error){
//            	$(this).attr("src", defaultImage); 
//            }
//
//    	});//each End
		$.preload(this, {//the first argument is a selector to the images
			placeholder:defaultImage,//this is the really important option
			notFound:defaultImage,//optional image if an image wasn't found
			threshold:1 //'1' is the default, how many at a time, to load.
		});
	};//imgError End

})(jQuery);

//~===============判断对象是否为空=====================================
function isNullObj(obj){
    for(var i in obj){
        if(obj.hasOwnProperty(i)){
            return false;
        }
    }
    return true;
}

var initCountySelector = {
	searchFlag:false,
    initCountys:[],
    initCountyId : 0,
    initTownId : 0,
    initCountys:function(countySelectorId,townSelectorId){
        if(!countySelectorId || !townSelectorId){
            art.alert("参数错误，请刷新页面重试");
            return ;
        }
        var countySelector = $("#" + countySelectorId);
        var townSelector = $("#" + townSelectorId);
        $.ajax({
            url : ctx+"/common/countys",
            type : 'post',
            dataType : 'json',
            data : {searchFlag : initCountySelector.searchFlag},
            success:function(data){
                if(data.success){
                    initCountySelector.initCountys = data.countys;
                    var content = '';
                    if(data.countys.length == 1){
                    	var county = initCountySelector.initCountys[0];
                        content += '<option value="'+county.id+'" selected>'+county.name+'</option>';
                        countySelector.html(content);
                        if(initCountySelector.searchFlag){
                        	$("#selCountyId").val(county.id);
                        	searchTable.init();
                        	initCountySelector.searchFlag = false;
                        	map.centerAndZoom(county.name);  
                        }
                    }else{
                    	if(initCountySelector.searchFlag){
                        	content = '<option value="0" selected>全部</option>';
                        	searchTable.init();
                        	initCountySelector.searchFlag = false;
                        }
                        for(var i = 0; i < initCountySelector.initCountys.length; i++){
                            var county = initCountySelector.initCountys[i];
                            content += '<option value="'+county.id+'">'+county.name+'</option>';
                        }
                    }
                    countySelector.html(content);
                    if(initCountySelector.initCountyId && initCountySelector.initCountyId != 0){
                        countySelector.val(initCountySelector.initCountyId);
                    }
                    initCountySelector.initTowns(townSelector,countySelector.val());
                    countySelector.change(function(){
                    	if(map){
                    		map.centerAndZoom($(this).find("option:selected").text());  
                    	}
                        initCountySelector.initTowns(townSelector,$(this).val());
                    });
                }
            },
            error: function(){
                art.alert("获取数据操作失败,请刷新页面重试");
            }
        });
    },
    initTowns:function(townSelector, countyId){
        townSelector.html('');
        var county;
        for(var i = 0; i < initCountySelector.initCountys.length; i++){
            if(countyId ==  initCountySelector.initCountys[i].id){
                county = initCountySelector.initCountys[i];
            }
        }
        if(!county){
            return;
        }
        var content = "<option value='0'>本区</option>";
        for(var i = 0; i < county.towns.length; i++){
            var town = county.towns[i];
            content += '<option value="'+town.id+'" data-log="'+town.longitude+'" data-lat="'+town.latitude+'">'+town.name+'</option>';
        }
        townSelector.html(content);
        if(initCountySelector.initTownId && initCountySelector.initTownId != 0){
            townSelector.val(initCountySelector.initTownId);
        }
        
    }
};

//处理Placeholder
$(document).ready(function(){var doc=document,inputs=doc.getElementsByTagName('input'),supportPlaceholder='placeholder'in doc.createElement('input'),placeholder=function(input){var text=input.getAttribute('placeholder'),defaultValue=input.defaultValue;if(defaultValue==''){input.value=text}input.onfocus=function(){if(input.value===text){this.value=''}};input.onblur=function(){if(input.value===''){this.value=text}}};if(!supportPlaceholder){for(var i=0,len=inputs.length;i<len;i++){var input=inputs[i],text=input.getAttribute('placeholder');if(input.type==='text'&&text){placeholder(input)}}}});

/** linweiqin  【start】  文本框验证  obj：文本框对象。isNumber：控件的值是否是数字。msg：错误提示信息。isBr:提示消息是否换行  为空则返回false     **/
// 非空验证
function errorPrompt(obj,msg,isNumber,isBr,level){
	 $(obj).val($.trim($(obj).val()));
	 removePrompt(obj);
	 if(isNumber){
		 if(isNaN($(obj).val())){
			 (level == 2?eval("$(obj).parent().parent()"):eval("$(obj).parent()")).append('<span id="tempLbMsg" class = "error">' + (isBr?'<br/>':'') +msg+'</span>'); 
			 return false;
		 }
	 }else{
		 if($(obj).val() == ''){//如果文本框的value是空字符串，就返回true
			 (level == 2?eval("$(obj).parent().parent()"):eval("$(obj).parent()")).append('<span id="tempLbMsg" class = "error">' + (isBr?'<br/>':'') +msg+'</span>'); 
			 return false;
		 }
	 }
	 removePrompt(obj);
	 return true;
}
// 联合验证  
function joinPrompt(jqueryObjs,msg){
	$(jqueryObjs).each(function(){
		$(this).bind("keyup",function(){
			if($.trim($(this).val()) != ""){
				$(this).parent().nextAll(".joinPromptTempDiv").remove();
			}
		});
	});
	var jpflag = false;
	for ( var i = 0; i < jqueryObjs.length; i++) {
		if($.trim($(jqueryObjs[i]).val()) == ""){
			var cssStyle = 'position:absolute;width:400px;font-size:16px;font-family:microsoft yahei;font-weight:bold;background-color:#f3f3f3;border-left:1px solid #a2a2a2;border-right:1px solid #a2a2a2;border-bottom:1px solid #a2a2a2;right:-1px;top:47px;height:50px;line-height:45px;color:#ff782f;z-index:10;'
			$(jqueryObjs[i]).parent().parent().append('<div class = "joinPromptTempDiv" style="'+cssStyle+'">　' + msg + '</div>');
			jpflag = true;
		}
	}
	if(jpflag){
		return false;
	}else{
		return true;
	}
}

//通用方法
function removePrompt(obj){
	 $(obj).nextAll("#tempLbMsg").remove();
}
/** linweiqin  【end】  文本框验证  obj：文本框对象。msg：错误提示信息。 **/
// 必然的提示
function fixedPrompt(obj,msg,isBr){
	removePrompt(obj);
	$(obj).parent().css("position","relative").append('<span id="tempLbMsg" class = "error" style = "min-width:176px;">' + (isBr?'<br/>':'') + ' &nbsp;&nbsp;'+msg+'</span>');
}

/**
 * 文本框完整的一套邮箱验证
 * @param obj
 */
function bindEmailInput(inputName,msg){
	var flag = true;
    var tel = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
    $(".lvTempLbMsg").remove();
	$('input[name="' + inputName + '"]').each(function(){
		if(!tel.test($.trim($(this).val()))){
			$(this).parent().append('<i class="lvTempLbMsg" style="color:red;font-weight:bold;margin-top:">&nbsp;&nbsp;'+msg+'</i>');
			flag = flag && false;
		}else{
			flag = flag && true;
		}
	});
	return flag;
}
//正則驗證
function checkEmail(obj){
	var reg_email = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/; //郵箱驗證正則
	if(!reg_email.test($.trim($(obj).val()))){
		fixedPrompt($(obj),"請輸入合法郵箱",false);
		return true;
	}
}
			//验证手机号码
function checkPhone(obj){
	if($.trim($(obj).val()) == ''){  
		return false;
	}
	var reg_phone = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;//電話號碼驗證
	if(!reg_phone.test($.trim($(obj).val()))){
		//fixedPrompt($(obj),"请输入正确联系电话",false);
		return false;
	}
	return true;
}
//前台验证
//邮编验证
function checkZip(obj){
	var reg_zip = /^[1-9]\d{5}$/;
	if(!reg_zip.test($.trim($(obj).val()))){
		return false;
	}
	return true;
}
//只能输入数字
function checkNum(obj){
	var reg_num = /^[0-9]*$/;
	if(!reg_num.test($.trim($(obj).val()))){
		return true;
	}
}
function frontCheckEmail(obj){
	var reg_email = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/; //郵箱驗證正則
	if(!reg_email.test($.trim($(obj).val()))){
		return true;
	}
}
/**
 * 返回绝对路径中文件的后缀名
 * @param absolutePath 文件的绝对路劲
 */
function getSuffix(absolutePath){
	var index = absolutePath.lastIndexOf(".");
	var suffix = absolutePath.substring(index);
	return suffix;
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



