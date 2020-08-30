$(function($){
	/* $(".activity-detail .activity-img img").height($(".activity-detail .activity-img img").width()*3/5); */
	$(".whole-imgdiv img").height($(".whole-imgdiv img").width()*3/5);
	
	//点击城市选择
	$("a.city-choose").click(function(){
		if($(".city-choose-div").css("display")=="block"){
			$(".city-choose-div").slideUp(150);
		}else{
			$(".city-choose-div").slideDown(150);
		}
	});
	
	


	
	
	
	
	
	
	
});
/**
 * 返回绝对路径中文件的后缀名
 * @param absolutePath 文件的绝对路劲
 */
function getSuffix(absolutePath){
	var index = absolutePath.lastIndexOf(".");
	var suffix = absolutePath.substring(index);
	return suffix;
}
/*//根据城市\关键词获取活动列表
function getActivitiesByCity(){
	$(".activity-list").html("活动搜索中...");
	$.ajax({
        url : ctx +"/searchActivity",
        data : {
        			cityName:$(".currentcity").text(),
        			keyWord:$("#searchInput").val()
        		}, 
        type: "post",
        dataType:"json",
        success : function(data){
           	if(data.success){
           		var html='';
           		if(data.searchActivities.length<1){
           			html="木有您要找的活动~";
           		}else{
           			$(".activity-list").html('');
           		}
           		for ( var i = 0; i < data.searchActivities.length; i++) {
           			var obj= data.searchActivities[i];
           			html+='<div class="activity-item" data-id="'+obj.id+'">'
           			+'<div class="whole-imgdiv">'
           			+'<a><img src="'+ctx+'/file/'+obj.picture+'"></a>'
           			+'<div class="activity-icon2 resignnumbericon"><span class="resign-numbers">'+obj.resignNum+'</span></div>'
           			+'</div>'
           			+'<div class="acitivityfirstline">'
           			+'<p class="acitivitytitle">'+obj.title+'</p>'
           			+'<p class="activitymoney"><span class="rmb">￥</span>'+obj.cost+'</p>'
           			+'</div>'
           			+'<div class="activitysecondline">'
           			+'<p class="activitysite "><span class="activity-icon2 activitysiteicon"></span>'+obj.shopName+'</p>'
           			+'<p class="begintime"><span class="activity-icon2 clockicon"></span>'+obj.beginTimeStr+'</p>'
           			+'</div>'
           			+'</div>';
           		}
    			$(".activity-list").html(html);
    			$(".whole-imgdiv img").height($(".whole-imgdiv img").width()*3/5);
    			//点击进入活动详情
    			$(".activity-item").click(function(){
    				var id=$(this).attr("data-id");
    				window.location.href=ctx+"/go/activity-detail/"+id;
    			});
           		
           	} 
        },
        error : function(){
           alert("搜索活动失败！");
        }
    }); 
}*/

//显示弹出层及其文字
function showWorn(text,icon,second){
	$("#prompWorn").find("span").removeClass("wornicon");
	$("#prompWorn").find("span").removeClass("gouicon");
	if(typeof(second)=="undefined"){
		second=2000;
	}
	if(typeof(icon)=="undefined"){
		icon="wornicon";
	}
	$("#prompText").text(text);
	$("#prompWorn").find("span").addClass(icon);
	$("#prompWorn").css("opacity","0.5");
	$("#prompWorn").show();
	setTimeout(function(){
		$("#prompWorn").animate({opacity:"0"},1000);
		//$("#prompWorn").hide();
	},second);
}

/*
 * MAP对象，实现MAP功能
 *
 * 接口：
 * size()     获取MAP元素个数
 * isEmpty()    判断MAP是否为空
 * clear()     删除MAP所有元素
 * put(key, value)   向MAP中增加元素（key, value) 
 * remove(key)    删除指定KEY的元素，成功返回True，失败返回False
 * get(key)    获取指定KEY的元素值VALUE，失败返回NULL
 * element(index)   获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
 * containsKey(key)  判断MAP中是否含有指定KEY的元素
 * containsValue(value) 判断MAP中是否含有指定VALUE的元素
 * values()    获取MAP中所有VALUE的数组（ARRAY）
 * keys()     获取MAP中所有KEY的数组（ARRAY）
 *
 * 例子：
 * var map = new Map();
 *
 * map.put("key", "value");
 * var val = map.get("key")
 * ……
 *
 */
function Map() {
	this.elements = new Array();

	//获取MAP元素个数
	this.size = function() {
		return this.elements.length;
	}

	//判断MAP是否为空
	this.isEmpty = function() {
		return (this.elements.length < 1);
	}

	//删除MAP所有元素
	this.clear = function() {
		this.elements = new Array();
	}

	//向MAP中增加元素（key, value) 
	this.put = function(_key, _value) {
		this.elements.push( {
			key : _key,
			value : _value
		});
	}

	//删除指定KEY的元素，成功返回True，失败返回False
	this.remove = function(_key) {
		var bln = false;
		try {
			for (var i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	//获取指定KEY的元素值VALUE，失败返回NULL
	this.get = function(_key) {
		try {
			for (var i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					return this.elements[i].value;
				}
			}
		} catch (e) {
			return null;
		}
	}

	//获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	this.element = function(_index) {
		if (_index < 0 || _index >= this.elements.length) {
			return null;
		}
		return this.elements[_index];
	}

	//判断MAP中是否含有指定KEY的元素
	this.containsKey = function(_key) {
		var bln = false;
		try {
			for (var i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	//判断MAP中是否含有指定VALUE的元素
	this.containsValue = function(_value) {
		var bln = false;
		try {
			for (var i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	//获取MAP中所有VALUE的数组（ARRAY）
	this.values = function() {
		var arr = new Array();
		for (var i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].value);
		}
		return arr;
	}

	//获取MAP中所有KEY的数组（ARRAY）
	this.keys = function() {
		var arr = new Array();
		for (var i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].key);
		}
		return arr;
	}
}
/**map   end**/

