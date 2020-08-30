var pageInstance = null;
var pageNo=1;
var lenth=10;
function pageNums(){
    var lenth=idea("#pageSize option:selected").val();
    this.pageNo = 1;
    ist.querData(pageNo,lenth)
	/* showDatas(condJsonData); */
}; 
var ist = {
	dataList:function(){
		this.querData(pageNo,lenth);

	},
	//查询数据
	queryList:function(){
		this.querData(pageNo,lenth);
	},
	querData:function(pageNo,lenth){
		idea("#showMessage").html("");
		var cpNum=idea("#cpNum").val();
		var appNum=idea("#appNum").val();
		var channelNum=idea("#channelNum").val();
		var batchNum=idea("#batchNum").val();
		var istType=idea("#istType").val();
		
		var pageNo2=idea("#pageNo2").val();
		var lenth2=idea("#lenth2").val();
		var start ="";
		if(pageNo2!=""){
			pageNo=pageNo2;
		}
		var start = (pageNo-1)*lenth;//开始查找的条数
		var data = {
				"cpNum":cpNum,
				"appNum":appNum,
				"channelNum":channelNum,
				"batchNum":batchNum,
				"istType":istType,
				"start" : start,
				"length" : lenth
	 	};
		var url = basePath+"ist/dataList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				var totalPage = data.page.pageCount;
				var pageSize=data.page.pageSize;
				var rowCount=data.page.rowCount;
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 	 	shtml +="<tr onmouseover='ist.trMouseOver(this)' onmouseout='ist.trMouseOut(this)' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].appNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].channelNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].batchNum+"</td>";
			 		if(dataList[i].ist.length>=15){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].ist+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].ist.substring(0,8)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].ist+"</td>";
			 	  	}
			 		if(dataList[i].istType==1){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>通用格式</td>";
			 	  	}else if(dataList[i].istType==2){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>安装命令</td>";
			 	  	}else if(dataList[i].istType==3){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>下载</td>";
			 	  	}else if(dataList[i].istType==4){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>执行调用系统命令</td>";
			 	  	}else if(dataList[i].istType==5){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>全局sleep时间</td>";
			 	  	}else if(dataList[i].istType==6){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>全局监控任务</td>";
			 	  	}
//			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].ist+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].ver+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:ist.updateData(1,"+dataList[i].id+",\""+pageNo+"\",\""+lenth+"\")'>编辑</a>";
			 	  	shtml +="&nbsp;";
			 	  	shtml +="<a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='javascript:ist.updateData(2,"+dataList[i].id+")'>删除</a>";
			 	  	shtml +="</tr>"
			 	}
			 	idea("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
				idea("#showData").html(html2);
			}
			ist.initPageBar(pageNo,totalPage,pageSize,rowCount);
			idea("#pageNo2").val('');
			idea("#lenth2").val('');
		});
	},
	
	//type=1，编辑要展现的数据
	//type=2，删除：业务逻辑删除
	updateData:function(type,id,isOnline,pageNo2,lenth2){
		//强制影藏其它层
		idea("#showUpMsg").html("");
		if(type==1){
			var url = basePath+"jsp/tvdeploy/ist/istEdit.jsp?id="+id+"&pageNo2="+pageNo2+"&lenth2="+lenth2;
			window.location.href=url;
		}else if(type==2){
			if(window.confirm("确认：是否删除?")){
				doType=type;
				var data = {
						"id":id,
						"doType":doType,
						"yn":"1"
			 	};
				var url = basePath+"ist/doUpdate.do";
				idea.base.request(data,url,function(data){
					if(data.msg=="请重新登录~"){
						alert("页面超时，请重新登录");
						var url = basePath+"jsp/admin-login.jsp";
						window.location.href=url;
					}else if (data.msg=="删除成功~") {
						idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
					}
				});
				ist.querData(pageNo,lenth);
			}
		}
	},
	
	
	//加载编辑数据
	editIst:function(id){
		idea("#id").val(id);
		var url = basePath+"ist/showEdit.do";
		var data = {
				"id":id
	 	};
		idea.base.request(data,url,function(data){
				idea("#cpNum").val(data.cpNum);
				idea("#appNum").val(data.appNum);
				idea("#channelNum").val(data.channelNum);
				idea("#batchNum").val(data.batchNum);
				idea("#ist").val(data.ist);
				//指令类型
				var shtml="";
				shtml += "<select name='select' id='istType' class='text-input medium-input'>";
				shtml += "<option value='0'>===请选择===</option>";
				if(data.istType==1){
					shtml +="<option selected='selected' value='1'>通用格式</option>";
					shtml +="<option value='2'>安装命令</option>";
					shtml +="<option value='3'>下载</option>";
					shtml +="<option value='4'>执行调用系统命令</option>";
					shtml +="<option value='5'>全局sleep时间</option>";
					shtml +="<option value='6'>全局监控任务</option>";
				}else if(data.istType==2){
					shtml +="<option value='1'>通用格式</option>";
					shtml +="<option selected='selected' value='2'>安装命令</option>";
					shtml +="<option value='3'>下载</option>";
					shtml +="<option value='4'>执行调用系统命令</option>";
					shtml +="<option value='5'>全局sleep时间</option>";
					shtml +="<option value='6'>全局监控任务</option>";
				}else if(data.istType==3){
					shtml +="<option value='1'>通用格式</option>";
					shtml +="<option value='2'>安装命令</option>";
					shtml +="<option selected='selected' value='3'>下载</option>";
					shtml +="<option value='4'>执行调用系统命令</option>";
					shtml +="<option value='5'>全局sleep时间</option>";
					shtml +="<option value='6'>全局监控任务</option>";
				}else if(data.istType==4){
					shtml +="<option value='1'>通用格式</option>";
					shtml +="<option value='2'>安装命令</option>";
					shtml +="<option value='3'>下载</option>";
					shtml +="<option selected='selected' value='4'>执行调用系统命令</option>";
					shtml +="<option value='5'>全局sleep时间</option>";
					shtml +="<option value='6'>全局监控任务</option>";
				}else if(data.istType==5){
					shtml +="<option value='1'>通用格式</option>";
					shtml +="<option value='2'>安装命令</option>";
					shtml +="<option value='3'>下载</option>";
					shtml +="<option value='4'>执行调用系统命令</option>";
					shtml +="<option selected='selected' value='5'>全局sleep时间</option>";
					shtml +="<option value='6'>全局监控任务</option>";
				}else if(data.istType==6){
					shtml +="<option value='1'>通用格式</option>";
					shtml +="<option value='2'>安装命令</option>";
					shtml +="<option value='3'>下载</option>";
					shtml +="<option value='4'>执行调用系统命令</option>";
					shtml +="<option value='5'>全局sleep时间</option>";
					shtml +="<option selected='selected' value='6'>全局监控任务</option>";
				}
				shtml +="</select>";
				idea("#selectistType").html(shtml);
				idea("#ver").val(data.ver);
		});
//		this.hidediv();
	},
	
	//执行修改
	doUpdate:function(){
		alert(3);
		idea("#showMessage").html("");
		var cpNum=idea("#cpNum").val();
		var appNum=idea("#appNum").val();
		var channelNum=idea("#channelNum").val();
		var batchNum=idea("#batchNum").val();
		var ist=idea("#ist").val();
		var istType=idea("#istType").val();
		var ver =  idea("#ver").val();
        
        var id =  idea("#id").val();
        var pageNo2 = idea("#pageNo2").val();
        var lenth2=idea("#lenth2").val();
        if(!cpNum){
			idea("#cpNum").focus();
			idea("#showMessage").html("<font color='red'>请填写CP编号！</font> ");
			return false;
		}
		if(!appNum){
			idea("#appNum").focus();
			idea("#showMessage").html("<font color='red'>请填写APP名称！</font> ");
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
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
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
	
	
	
	//跳转到新增页面
	addUrl:function(){
			var url = basePath+"jsp/tvdeploy/ist/istAdd.jsp";
			window.location.href=url;
	},
	//新增数据
	add:function(){
		var url = basePath+"ist/add.do";
		var cpNum=idea("#cpNum").val();
		var appNum=idea("#appNum").val();
		var channelNum=idea("#channelNum").val();
		var batchNum=idea("#batchNum").val();
		var ist=idea("#ist").val();
		var istType=idea("#istType").val();
		var ver =  idea("#ver").val();
		
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
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
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
 
	//新增数据
	addIst:function(){
		alert("sb");
		var url = basePath+"ist/add.do";
		var cpNum=idea("#cpNum").val();
		var appNum=idea("#appNum").val();
		var channelNum=idea("#channelNum").val();
		var batchNum=idea("#batchNum").val();
		var ist=idea("#ist").val();
		var istType=idea("#istType").val();
		var ver =  idea("#ver").val();
		
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
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
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
	
	
	//公共方法-焦点获取-改变tr的背景颜色
	trMouseOver:function(el){
			idea(el).css('background-color','#CCCCCC');
	},
	//公共方法-焦点离开-改变tr的背景颜色
	trMouseOut:function(el){
		idea(el).css('background-color','');
	},
	//编辑-显示编辑框
	showdiv:function() {  
		idea("#show").css('display','block'); 
		this.hideAddDiv();//怕多层出现实现干扰，所以强制影藏其它层
	},
	//编辑-影藏编辑框
	hidediv:function() { 
		idea("#show").css('display','none');
		//查询最新数据
		this.querData(pageNo,lenth);
	},
	//添加-显示框
	showAddDiv:function() {  
		idea("#show1").css('display','block'); 
	},
	//添加-影藏框
	hideAddDiv:function() { 
		idea("#show1").css('display','none');
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
			renderTo : idea(".pagebar"),
			totalpage : totalPage,
			currentPage : pageNo,
			pageSize : pageSize,
			rowCount : rowCount,
			pageNameCssName : 'pageName',
			onClickPage : function(pageIndex,pageSize) {
				idea.fn.setCurrentPage(this, pageIndex,pageSize);
				pageNo = pageIndex;
				pageSize2=pageSize;
				ist.querData(pageIndex, pageSize2);
			}
		});

	},
	
}
