var pageInstance = null;
var pageNo=1;
var lenth=10;
function pageNums(){
    var lenth=idea("#pageSize option:selected").val();
    this.pageNo = 1;
    cp.querData(pageNo,lenth)
	/* showDatas(condJsonData); */
}; 
var cp = {
	dataList:function(){
		this.querData(pageNo,lenth);

	},
	//查询数据
	queryList:function(){
		this.querData(pageNo,lenth);
	},
	querData:function(pageNo,lenth){
		$("#showMessage").html("");
		var cpNum=$("#cpNum").val();
		var cpName=$("#cpName").val();
		var cpContacts=$("#cpContacts").val();
		
		var pageNo2=$("#pageNo2").val();
		var lenth2=$("#lenth2").val();
		var start ="";
		if(pageNo2!=""){
			pageNo=pageNo2;
		}
		var start = (pageNo-1)*lenth;//开始查找的条数
		var data = {
				"cpNum":cpNum,
				"cpName":cpName,
				"cpContacts":cpContacts,
				"start" : start,
				"length" : lenth
	 	};
		var url = basePath+"cp/dataList.do";
		idea.base.request(data,url,function(data){

			if(data.rows.length>0){
				var dataList=data.rows; 
				var totalPage = data.page.pageCount;
				var pageSize=data.page.pageSize;
				var rowCount=data.page.rowCount;
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 	 	shtml +="<tr onmouseover='cp.trMouseOver(this)' onmouseout='cp.trMouseOut(this)' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpName+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpContacts+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpPhone+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:cp.updateData(1,"+dataList[i].id+",\""+pageNo+"\",\""+lenth+"\")'>编辑</a>";
			 	  	shtml +="&nbsp;";
			 	  	shtml +="<a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='javascript:cp.updateData(2,"+dataList[i].id+")'>删除</a>";
			 	  	shtml +="</tr>"
			 	}
                $("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
                $("#showData").html(html2);
			}
			cp.initPageBar(pageNo,totalPage,pageSize,rowCount);
            $("#pageNo2").val('');
            $("#lenth2").val('');
		});
	},
	
	//type=1，编辑要展现的数据
	//type=2，删除：业务逻辑删除
	updateData:function(type,id,isOnline,pageNo2,lenth2){
		//强制影藏其它层
//		this.hideAddDiv();
//		this.hidediv();
        $("#showUpMsg").html("");
		if(type==1){
			var url = basePath+"jsp/tvdeploy/cp/cpEdit.jsp?id="+id+"&pageNo2="+pageNo2+"&lenth2="+lenth2;
			window.location.href=url;
		}else if(type==2){
			if(window.confirm("确认：是否删除?")){
				doType=type;
				var data = {
						"id":id,
						"doType":doType,
						"yn":"1"
			 	};
				var url = basePath+"cp/doUpdate.do";
				idea.base.request(data,url,function(data){
					if(data.msg=="请重新登录~"){
						alert("页面超时，请重新登录");
						var url = basePath+"jsp/admin-login.jsp";
						window.location.href=url;
					}else if (data.msg=="删除成功~") {
                        $("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
					}
				});
				this.querData(pageNo,lenth);
			}
		}
	},
	
	
	//加载编辑数据
	editCp:function(id){
        $("#id").val(id);
		var url = basePath+"cp/showEdit.do";
		var data = {
				"id":id
	 	};
		idea.base.request(data,url,function(data){
            $("#cpNum").val(data.cpNum);
            $("#cpName").val(data.cpName);
            $("#cpContacts").val(data.cpContacts);
            $("#cpPhone").val(data.cpPhone);
		});
//		this.hidediv();
	},
	
	//执行修改
	doUpdate:function(){
        $("#showMessage").html("");
		var cpNum =  $("#cpNum").val();
        var cpName = $("#cpName").val();
        
        var id =  $("#id").val();
        var pageNo2 = $("#pageNo2").val();
        var lenth2=$("#lenth2").val();
        if(!cpNum){
            $("#cpNum").focus();
            $("#showMessage").html("<font color='red'>请填写CP编号！</font> ");
			return false;
		}
		if(!cpName){
            $("#cpName").focus();
            $("#showMessage").html("<font color='red'>请填写CP名称！</font> ");
			return false;
		}
		var cpContacts=$("#cpContacts").val();
		var cpPhone=$("#cpPhone").val();
		var doType=1;
		var data = {
				"id":id,
				"cpNum":cpNum,
				"cpName":cpName,
				"cpContacts":cpContacts,
				"cpPhone":cpPhone,
				"doType":doType
	 	};
		var url = basePath+"cp/doUpdate.do";
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="修改成功~"){
				var url = basePath+"jsp/tvdeploy/cp/cpIndex.jsp";
				alert(data.msg);
				window.location.href=url;
			}else{
				alert("页面超时，请重新登录");
				var url = basePath+"jsp/admin-login.jsp";
				window.location.href=url;
			}
		});
	},
	
	
	
	//跳转到新增页面
	addUrl:function(){
			var url = basePath+"jsp/tvdeploy/cp/cpAdd.jsp";
			window.location.href=url;
	},
	
	//新增数据
	add:function(){
		var url = basePath+"cp/add.do";
		var cpNum=$("#cpNum").val();
		var cpName=$("#cpName").val();
		var cpContacts=$("#cpContacts").val();
		var cpPhone=$("#cpPhone").val();
		var data = {
				"cpNum":cpNum,
				"cpName":cpName,
				"cpContacts":cpContacts,
				"cpPhone":cpPhone
	 	};
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				var url = basePath+"jsp/tvdeploy/cp/cpIndex.jsp";
				alert(data.msg);
				window.location.href=url;
			}else{
				alert("页面超时，请重新登录");
				var url = basePath+"jsp/admin-login.jsp";
				window.location.href=url;
			}
		});
	},
	
	
	//查询所有CP-无分页-下拉框选项:传入参数：默认的cp编号
	queryCpAll:function(selectCpNum){
		var url = basePath+"cp/dataAllList.do";
		var data = {
	 	};
		idea.base.request(data,url,function(data){
				var dataList=data.rows;
				var shtml="";
				shtml += "<select name='select' id='cpData' class='text-input medium-input'>";
				if(selectCpNum==0){
					shtml +="<option value='0,0'>===请选择===</option>";
				}
				for (var i=0;i<dataList.length>0;i++) {
					if(selectCpNum!=0){
						if(dataList[i].cpNum==selectCpNum){
							shtml +="<option  selected = 'selected' value="+dataList[i].id+","+dataList[i].cpNum+">==="+dataList[i].cpNum+"==="+dataList[i].cpName+"</option>";
						}else{
							shtml +="<option value="+dataList[i].id+","+dataList[i].cpNum+">==="+dataList[i].cpNum+"==="+dataList[i].cpName+"</option>";
						}
					}else{
						shtml +="<option value="+dataList[i].id+","+dataList[i].cpNum+">==="+dataList[i].cpNum+"==="+dataList[i].cpName+"</option>";
					}
				}
				shtml +="</select>";
            	$("#selectCp").html(shtml);
		});
	},
	
	
	//新增数据
	addIst:function(){
		var url = basePath+"ist/add.do";
		var cpNum=$("#cpNum").val();
		var appNum=$("#appNum").val();
		var channelNum=$("#channelNum").val();
		var batchNum=$("#batchNum").val();
		var ist=$("#ist").val();
		var istType=$("#istType").val();
		var ver =$("#ver").val();
		
		var data = {
				"cpNum":cpNum,
				"appNum":appNum,
				"channelNum":channelNum,
				"batchNum":batchNum,
				"ist":ist,
				"istType":istType,
				"ver":ver
	 	};
		idea.base.request(data,url,function(data){
            $("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				var url = basePath+"jsp/tvdeploy/ist/istIndex.jsp";
				alert(data.msg);
				window.location.href=url;
			}else{
				alert("页面超时，请重新登录");
				var url = basePath+"jsp/admin-login.jsp";
				window.location.href=url;
			}
		});
	},
	
	//执行修改-指令
	doUpdateIst:function(){
        $("#showMessage").html("");
		var cpNum=$("#cpNum").val();
		var appNum=$("#appNum").val();
		var channelNum=$("#channelNum").val();
		var batchNum=$("#batchNum").val();
		var ist=$("#ist").val();
		var istType=$("#istType").val();
		var ver =  $("#ver").val();
        
        var id =  $("#id").val();
        var pageNo2 = $("#pageNo2").val();
        var lenth2=$("#lenth2").val();
        if(!cpNum){
            $("#cpNum").focus();
            $("#showMessage").html("<font color='red'>请填写CP编号！</font> ");
			return false;
		}
		if(!appNum){
            $("#appNum").focus();
            $("#showMessage").html("<font color='red'>请填写APP名称！</font> ");
			return false;
		}
		var doType=1;
		var data = {
				"id":id,
				"cpNum":cpNum,
				"appNum":appNum,
				"channelNum":channelNum,
				"batchNum":batchNum,
				"ist":ist,
				"istType":istType,
				"ver":ver,
				"doType":doType
	 	};
		var url = basePath+"ist/doUpdate.do";
		idea.base.request(data,url,function(data){
            $("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="修改成功~"){
				var url = basePath+"jsp/tvdeploy/ist/istIndex.jsp";
				alert(data.msg);
				window.location.href=url;
			}else{
				alert("页面超时，请重新登录");
				var url = basePath+"jsp/admin-login.jsp";
				window.location.href=url;
			}
		});
	},
	
	
	//公共方法-焦点获取-改变tr的背景颜色
	trMouseOver:function(el){
        $(el).css('background-color','#CCCCCC');
	},
	//公共方法-焦点离开-改变tr的背景颜色
	trMouseOut:function(el){
        $(el).css('background-color','');
	},
	//编辑-显示编辑框
	showdiv:function() {
        $("#show").css('display','block');
		this.hideAddDiv();//怕多层出现实现干扰，所以强制影藏其它层
	},
	//编辑-影藏编辑框
	hidediv:function() {
        $("#show").css('display','none');
		//查询最新数据
		this.querData(pageNo,lenth);
	},
	//添加-显示框
	showAddDiv:function() {
        $("#show1").css('display','block');
	},
	//添加-影藏框
	hideAddDiv:function() {
        $("#show1").css('display','none');
		//查询最新数据
		this.querData(pageNo,lenth);
	},
	initPageBar: function(pageNo, totalPage,pageSize,rowCount) {
		if (pageInstance != null) {
			//处理分页
			idea.fn.setTotalPage(pageInstance,pageNo,totalPage,pageSize,rowCount);
			return;
		}
		pageInstance = idea.fn.jpagebar({
			renderTo : $(".pagebar"),
			totalpage : totalPage,
			currentPage : pageNo,
			pageSize : pageSize,
			rowCount : rowCount,
			pageNameCssName : 'pageName',
			onClickPage : function(pageIndex,pageSize) {
				idea.fn.setCurrentPage(this, pageIndex,pageSize);
				pageNo = pageIndex;
				pageSize2=pageSize;
				cp.querData(pageIndex, pageSize2);
			}
		});

	},
	
}
