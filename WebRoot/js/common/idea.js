//  以idea代替 $
// var $ = "";
var idea = jQuery.noConflict();
// 时间插件
idea.IdeaDate = WdatePicker;
// 树形菜单栏

idea.base = {
	// 添加
	add : function(){
	
	},
	// 修改
	update : function (){
		
	},
	queryPage : function(){
		
	},
	
	// 请求提交
	request : function(data, url, callback) {
		idea.ajax( {
			type : "post",
			data : data,
			dataType : "json",
			url : url,
			success : function(data) {
				callback(data);
			}
		});
	},
	
	// from 参数 name：val
	paramByName : function(formid) {
		var param = {};
		var input = idea("#" + formid + " input[type='text']");
		for ( var i = 0; i < input.length; i++) {
			param[input[i].name] = input[i].value;
		}
		var select = idea("#" + formid + " select");
		for ( var i = 0; i < select.length; i++) {
			param[select[i].name] = select[i].value;
		}
		return param;
	},
	// from 参数 id：val
	paramById : function(fromid) {
		var param = {};
		var input = idea("#" + fromid + " input");
		for ( var i = 0; i < input.length; i++) {
			param[input[i].id] = input[i].value;
		}
		var select = idea("#" + fromid + " select");
		for ( var i = 0; i < select.length; i++) {
			param[select[i].id] = select[i].value;
		}
		return param;

	}
}