var menuList;

$(function(){
		getMenuList();
	});
	
	
	var sto;
	function timer(){			
		var dds = $(".menu_item");
		for ( var i = 0; i < dds.length; i++) {
			if($(dds[i]).hasClass("selected")){
				$(dds[i]).prevAll(".menu_title:first").click();
				break;
			}
		}
		if($(".titleOpen").length == 0){
			sto = setTimeout("timer()",100);
		}else{
			clearTimeout(sto);
		}
	};
	
	window.onload = function(){
		sto = timer();
	};
	
function getMenuList(){
	var accountId = $("#accountId").val();
	var roleId = $("#roleId").val();
	$.ajax({
		url : ctx+ "/system/module/menuList.do",
		type : 'post',
		dataType : 'json',
		data :{
			accountId:accountId,
			roleId:roleId
		},
		success : function(data) {
			if (data.success) {
				menuList = data.data.menuList;
				initMenuList(menuList);
			} else {
				art.alert('ccc'+data.msg);
			}
		},
		error : function(data) {
			art.alert('ddd'+data.info);
		}
	});
}

function initMenuList(menuList){
	var menuOne = menuList;
	var html='';
	//初始化导航栏
	initNaviBar(menuOne);
	//初始化左侧菜单栏
	var menuTwo = menuOne[menuOne.length-1].children;
	
	for(var j=0;j<menuTwo.length;j++){
		html+='<dt class="menu_title" id="usermanager" menucode="wangzhan-guanliyuan">';
		html+='<span class = "usermanager_span_websitenotice">&nbsp;</span>'+ menuTwo[j].moduleName;
			html+= '<span class = "openCloseIcon"></span></dt>'
		var menuThree = menuTwo[j].children;
			for(var k=0;k<menuThree.length;k++){
				html +='<dd class="menu_item" id="listUser" menucode="wangzhan-guanliyuan-liebiao" onclick="getSelected()">'
				html +='<a href="'+ctx + menuThree[k].actionUrl+'" target="mainFrame" >'+menuThree[k].moduleName+'</a></dd>';
			}
	}
	
	$("#menuBar").html(html);
	
	initClick();

	// 若没访问任何模块，默认left展开第一项
	var menuId = '.menu' + menuList[menuList.length-1].moduleId 
	$(menuId).click();
}
	

	
function showMenu(id){
	var html = '';
	var temp = null;
	var menuOne = menuList;
	for(var i =0; i<menuOne.length;i++){
		if(menuOne[i].moduleId == id){
			var menuTwo = menuOne[i].children;
			temp = menuTwo;
			for(var j=0;j<menuTwo.length;j++){
				html+='<dt class="menu_title" id="usermanager" menucode="wangzhan-guanliyuan">';
				html+='<span class = "usermanager_span_websitenotice">&nbsp;</span>'+ menuTwo[j].moduleName;
					html+= '<span class = "openCloseIcon"></span></dt>'
				var menuThree = menuTwo[j].children;
					for(var k=0;k<menuThree.length;k++){
						html +='<dd class="menu_item" id="listUser" menucode="wangzhan-guanliyuan-liebiao">'
						html +='<a href="'+ctx + menuThree[k].actionUrl+'" target="mainFrame">'+menuThree[k].moduleName+'</a></dd>';
					}
			}
			
		}
	}
	$("#menuBar").html(html);
	initClick();
	var tempNavi = ".menu"+temp.moduleId;
	$(".tempNavActive").removeClass('tempNavActive');
	$(tempNavi).addClass('tempNavActive');
}

//var html = '';
//	html += '<div class="backstage_title" ><div class="backstage_title2"><img src="${ctx }/adminfile/images/admenu_sale.png" class="admin_index_xszx"><span class="admin_index_label">销售中心</span></div>'
//	html += '<div class="backstage_menu">'
//	html += '<div class="menu_title2" id="usermanager">'
//	html += '<span class = "usermanager_span_journalmanage">&nbsp;</span><a id="change" href="#">订单列表</a>'
//	html+='</div></div>'
//	html+= '<div class="backstage_menu">'
//	html+='<div class="menu_title2" id="usermanager">'
//	html+='<span class = "usermanager_span_journalmanage">&nbsp;</span><a href="${ctx }/admin/product-type/go/list-product-type">产品类型列表</a>'
//	html+=' </div></div>'
//	html+='<div class="backstage_menu">'
//	html+='<div class="menu_title2" id="usermanager">'
//	html+='	    <span class = "usermanager_span_journalmanage">&nbsp;</span><a href="${ctx}/admin/product/go/list-product">产品列表</a>'
//	html+= '</div></div>'
//	html+='</div>'

function initClick(){
		$(".menu_box .menu_item").hide();
		$(".menu_title").click(function(){
			if($(this).hasClass("titleOpen")){
				return;
			}
			var currIndex = $(this).index();
			var currDtIndex = $(this).prevAll(".menu_title").length;
			var nextDtIndex = $(this).nextAll("dt:eq(0)").index();
			var nextAll = $(this).nextAll();
			$(".menu_box .menu_item").stop(true,true).slideUp(200);
			$(".menu_title .openCloseIcon").css("background-image","url(" + ctx + "/adminfile/images/open_icon.png)");
			for ( var i = 0; i < nextAll.length; i++) {
				if($(".titleOpen").length == 0 && $(nextAll[i]).index() > currIndex && $(nextAll[i]).index() < nextDtIndex || currDtIndex == $(".menu_title").length - 1){
					$(nextAll[i]).show();
					continue;
				}
				if($(nextAll[i]).index() > currIndex && $(nextAll[i]).index() < nextDtIndex || currDtIndex == $(".menu_title").length - 1){
					$(nextAll[i]).stop(true,true).slideDown(200);
				}
			}
			$(this).children(".openCloseIcon").css("background-image","url(" + ctx + "/adminfile/images/close_icon.png)");
			$(".menu_title").removeClass("titleOpen");
			$(this).addClass("titleOpen");
		});
		
		$(".menu_item").click(function(){
			if($(this).hasClass("selected")){
				return;
			}
			$(".menu_item").removeClass("selected");
			$(this).addClass("selected");
		});
	}

function initNaviBar(menuOne){
	var html = ''
	for(var i=0;i<menuOne.length;i++){
		var module = menuOne[i];
		if(module.moduleName == "系统管理"){
			html += '<dd id = "wzgl" class = "menu'+module.moduleId+'"><a href = "#" onclick="showMenu('+module.moduleId+')">'+module.moduleName+'</a></dd>'
		}else if(module.moduleName == "模版管理"){
			html += '<dd id = "shgl" class = "menu'+module.moduleId+'"><a href = "#" onclick="showMenu('+module.moduleId+')">'+module.moduleName+'</a></dd>'
		}else if(module.moduleName == "主题知识"){
			html += '<dd id = "hdgl" class = "menu'+module.moduleId+'"><a href = "#" onclick="showMenu('+module.moduleId+')">'+module.moduleName+'</a></dd>'
		}else if(module.moduleName == "用户中心"){
			html += '<dd id = "hyzx" class = "menu'+module.moduleId+'"><a href = "#" onclick="showMenu('+module.moduleId+')">'+module.moduleName+'</a></dd>'
		}else if(module.moduleName == "销售中心"){
			html += '<dd id = "xszx" class = ""><a href = "#" onclick="showMenu('+module.moduleId+')">'+module.moduleName+'</a></dd>'
		}
	}
	var tempNavi = ".menu"+menuOne[0].moduleId;
	$(".topNavItem").html(html);
	$(".menu2").addClass('tempNavActive');
}

function getSelected(){
	$(this).addClass('selected');
}
	
	