idea.common = {
	totalCount:0,
	
	
	 // 当前时间
	GetDateStr:function(AddDayCount) {
	    var dd = new Date();
	    dd.setDate(dd.getDate()+AddDayCount);// 获取AddDayCount天后的日期
	    var y = dd.getFullYear();
	    var m = dd.getMonth()+1;// 获取当前月份的日期
	    var d = dd.getDate();
	    return y+"-"+m+"-"+d;
	},
	// 显示隐藏
	infoshow : function(li,val,length,cs){
		for(var i=1;i<=length;i++){
			if(val==i){
				$("#"+li+i).attr("class",cs);
				$("#"+li+"show"+i).attr("style","display: block;");
			}else{
				$("#"+li+i).attr("class","");
				$("#"+li+"show"+i).attr("style","display: none;");
			}
		}
	},
	// 分页
	pager : function(url, type, data) {
		var pager = "<ul>";
		var curPageNum = parseInt(data.curPageNum);
		var totalCount = parseInt(data.totalCount);
		var pageSize = parseInt(data.pageSize);
		var pageNum = (totalCount - totalCount % pageSize) / pageSize + 1;
		common.totalCount=totalCount;
		if (curPageNum == 1) {
			pager += '<li class="cancelCursor">首页</li>';
			pager += '<li class="cancelCursor">上一页</li>';

		} else {
			pager += "<li onclick=\""+url+"('1');\">首页</li>";
			pager += "<li onclick=\""+url+"('" + (curPageNum - 1) + "');\">上页</li>";
		}
		if (curPageNum <= 3) {
			var num = 5;
			if (pageNum < num) {
				num = pageNum;
			}
			for ( var i = 1; i <= num; i++) {
				if (i == curPageNum) {
					pager += '<li class="currentPage cancelCursor" >' + i + '</li>';
				} else {
					pager += "<li onclick=\""+url+"('" + i + "');\" >" + i + "</li>";
				}
			}
		} else if (curPageNum >= (pageNum - 3)) {
			var num = 0;
			if (pageNum > 4) {
				num = pageNum - 4;
			}
			for ( var i = num; i <= pageNum; i++) {
				if (i == curPageNum) {
					pager += '<li class="currentPage cancelCursor" >' + i + '</li>';
				} else {
					pager += "<li onclick=\""+url+"('" + i + "');\" >" + i + "</li>";
				}
			}
		} else {
			var startnum = curPageNum - 2;
			var endnum = curPageNum + 2;
			for ( var i = startnum; i <= endnum; i++) {
				if (i == curPageNum) {
					pager += '<li class="currentPage cancelCursor" >' + i + '</li>';
				} else {
					pager += "<li onclick=\""+url+"('" + i + "');\" >" + i + "</li>";
				}
			}
		}
		if (pageNum == curPageNum) {
			pager += '<li class="cancelCursor">下页</li>';
			pager += '<li class="cancelCursor">尾页</li>';
		} else {
			pager += "<li onclick=\""+url+"('" + (parseInt(curPageNum) + 1) + "');\">下页</li>";
			pager += "<li onclick=\""+url+"('" + pageNum + "');\">尾页</li>";
		}
		pager += '<li class="textInput"><div><input id="pagers" onkeyup="javascript:this.value=this.value.replace(/\D/g,\'\');" value="" class="MInputTT W50" type="text"></div></li>';
		pager += "<li onclick=\""+url+"(0);\">跳转</li>";
		pager += '<li class="cancelCursor">共' + pageNum + '页</li>';
		pager += '<li class="cancelCursor">共' + totalCount + '条记录</li>';
		idea("#" + type + "_pager").html(pager);

	} 

}
 
function confirmDel(msg, url) {
	if (confirm(msg)) {
		document.location.href = url;
	}
}
function checkInputs(names, msgs, iftrim) {
	var obj = null;
	var name = names.split(",");
	var msg = msgs.split(",");
	for(var i=0; i<name.length; i++) {
		var ret = showMI(name[i], msg[i], iftrim);
		if(obj == null)
			obj = ret;
	}
	if(obj != null) {
		obj.focus();
		return false;
	}
	return true;
}
function showMI(name, msg, iftrim) {
	var obj = getObj(name);
	if(!obj)
		return null;
	
	var val = iftrim ? trim(obj.value) : obj.value;
	
	obj.parentNode.innerHTML = obj.outerHTML;
	obj = getObj(name);
	obj.value = val;
	
	if(val.length < 1) {
		obj.outerHTML += "<span class='altmsg'>" + msg + "</span>";
		return getObj(name);
	}
	return null;
}
function addShowMI(name, msg) {
	var obj = getObj(name);
	if(!obj)
		return null;
	
	var val = obj.value;
	obj.parentNode.innerHTML = obj.outerHTML;
	obj = getObj(name);
	obj.outerHTML += "<span class='altmsg'>" + msg + "</span>";
	var obj2 = getObj(name);
	obj2.value = val;
	return obj2;
}
function getObj(name) {
	if(document.getElementById(name))
		return document.getElementById(name);
	if(document.getElementsByName(name))
		return document.getElementsByName(name)[0];
	return null;
}
function trim(str) { 
    if (str != null) {
        var i; 
        for (i=0; i<str.length; i++) {
            if (str.charAt(i)!=" ") {
                str=str.substring(i,str.length); 
                break;
            } 
        } 
    
        for (i=str.length-1; i>=0; i--) {
            if (str.charAt(i)!=" ") {
                str=str.substring(0,i+1); 
                break;
            } 
        } 
        
        if (str.charAt(0)==" ") {
            return ""; 
        } else {
            return str; 
        }
    }
} 
function selectAll(bns, ch) {
	var objs = document.getElementsByName(bns);
	for(var i=0; i<objs.length; i++) {
		objs[i].checked = ch;
	}
}
function clearMainBox(bxn) {
	document.getElementById(bxn).checked = false;
}
function validateCommon(str){
	var reg=/^([0-9a-zA-Z_\s]+)+$/;
 	if(!reg.test(str)){		
 		return false;
 	}
	return true;
}
function validateIdCard(str){
	var reg=/^([0-9]{15,18}[XY]?)+$/;
	if(!reg.test(str)){		
		return false;
	}
	return true;
}
// 小数和整数
function validateIntDec(str){
	var reg=/^([1-9]\d{0,11})(\.\d+)?$/;
	if(!reg.test(str)){
		return false;
	}
		return true;
}
function validateInt2Dec(str){
	var reg=/^[1-9]\d{0,11}$/;
	if(!reg.test(str)){
		return false;
	}
		return true;
}
function validateFloatDec(str){
	var reg=/^(\d+)(\.\d+)?$/;
	if(!reg.test(str)){
		return false;
	}
		return true;
}

