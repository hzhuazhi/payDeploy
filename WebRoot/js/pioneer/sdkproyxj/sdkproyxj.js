var sdkproyxj = {
	dataList:function(){
		this.querData();

	},
	//查询数据
	queryList:function(){
		this.querData();
	},
	querData:function(){
		idea("#showMessage").html("");
		var sdkNum=idea("#sdkNum").val();
		var operator=idea("#operator").val();
		
		 
		var data = {
				"sdkNum":sdkNum,
				"operator":operator
	 	};
		var url = basePath+"sdkproyxj/dataList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				 
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 	 	shtml +="<tr onmouseover='sdkproyxj.trMouseOver(this)' onmouseout='sdkproyxj.trMouseOut(this)' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].sdkNum+"</td>";
			 		if(dataList[i].operator==1){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>移动</td>";
			 	  	}else if(dataList[i].operator==2){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>联通</td>";
			 	  	}else if(dataList[i].operator==3){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>电信</td>";
			 	  	}else if(dataList[i].operator==4){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>其它</td>";
			 	  	}
			 		shtml +="<td style='text-align: center; white-space:nowrap'><input type='text' class ='inputCommonSty' name='optimizeValue_"+dataList[i].id+"' id='optimizeValue_"+dataList[i].id+"' size='15' value='"+dataList[i].optimizeValue+"'/></td>";
			 		if(dataList[i].optimizeStatus==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>初始化</td>";
			 	  	}else if(dataList[i].optimizeStatus==1){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>当天未部署</td>";
			 	  	}else if(dataList[i].optimizeStatus==2){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>sdk当天未部署</td>";
			 	  	}else if(dataList[i].optimizeStatus==3){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>优化成功</td>";
			 	  	}
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].optimizeTime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:sdkproyxj.optimize("+dataList[i].id+",\""+dataList[i].sdkNum+"\",\""+dataList[i].operator+"\")'>优化</a></td>";
			 	  	shtml +="</tr>"
			 	}
			 	idea("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
				idea("#showData").html(html2);
			}
		 
		});
	},
	
	optimize:function(id,sdkNum,operator){
		//强制影藏其它层
		idea("#showUpMsg").html("");
		var optimizeValue =  idea("#optimizeValue_"+id).val();
		if(window.confirm("确认：是否优化?")){
			var data = {
					"id":id,
					"sdkNum":sdkNum,
					"operator":operator,
					"optimizeValue":optimizeValue
		 	};
			var url = basePath+"sdkproyxj/optimize.do";
			idea.base.request(data,url,function(data){
				if(data.msg=="请重新登录~"){
					alert("页面超时，请重新登录");
					var url = basePath+"jsp/admin-login.jsp";
					window.location.href=url;
				}else if (data.msg=="优化成功~") {
					sdkproyxj.querData();
					idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
				}
			});
		}
	},
	
	//全部优化
	allOptimize:function(){
		//强制影藏其它层
		idea("#showUpMsg").html("");
		var tableData = idea("#tableData").serialize();
		if(window.confirm("确认：是否全部优化?")){
			var data = {
					"tableData":tableData
		 	};
			var url = basePath+"sdkproyxj/allOptimize.do";
			idea.base.request(data,url,function(data){
				if(data.msg=="请重新登录~"){
					alert("页面超时，请重新登录");
					var url = basePath+"jsp/admin-login.jsp";
					window.location.href=url;
				}else if (data.msg=="优化成功~") {
					sdkproyxj.querData();
					idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
				}
			});
//			this.querData();
		}
	},
	
	
	
	//公共方法-焦点获取-改变tr的背景颜色
	trMouseOver:function(el){
//		idea(el).css('background-color','#EEEEEE');
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
		this.querData();
	},
	//添加-显示框
	showAddDiv:function() {  
		idea("#show1").css('display','block'); 
	},
	//添加-影藏框
	hideAddDiv:function() { 
		idea("#show1").css('display','none');
		//查询最新数据
		this.querData();
	},
}
