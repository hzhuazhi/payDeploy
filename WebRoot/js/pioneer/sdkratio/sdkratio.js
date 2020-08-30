var sdkratio = {
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
		var sdkName=idea("#sdkName").val();
		var data = {
				"sdkNum":sdkNum,
				"sdkName":sdkName
	 	};
		var url = basePath+"sdkratio/dataAllList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 	 	shtml +="<tr onmouseover='sdkratio.trMouseOver(this)' onmouseout='sdkratio.trMouseOut(this)' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].sdkNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].sdkName+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'><input type='text' class ='inputCommonSty' name='actualMoney_"+dataList[i].id+"' id='actualMoney_"+dataList[i].id+"' size='15' value='"+dataList[i].actualMoney+"'/></td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].theoryMoney+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].ratio+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].moneyCurday+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].ratioTime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].ratioPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:sdkratio.calculation("+dataList[i].id+")'>计算比例</a></td>";
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
	
	//执行计算比例值
	calculation:function(id){
		//强制影藏其它层
		idea("#showUpMsg").html("");
		var actualMoney =  idea("#actualMoney_"+id).val();
		if(window.confirm("确认：是否执行?")){
			var data = {
					"id":id,
					"actualMoney":actualMoney
		 	};
			var url = basePath+"sdkratio/calculation.do";
			idea.base.request(data,url,function(data){
				if(data.msg=="请重新登录~"){
					alert("页面超时，请重新登录");
					var url = basePath+"jsp/admin-login.jsp";
					window.location.href=url;
				}else if (data.msg=="计算成功~") {
					sdkratio.querData();
					idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
				}
			});
		}
	},
	
	//全部计算比例
	allcalculation:function(){
		//强制影藏其它层
		idea("#showUpMsg").html("");
		var tableData = idea("#tableData").serialize();
		if(window.confirm("确认：是否全部计算?")){
			var data = {
					"tableData":tableData
		 	};
			var url = basePath+"sdkratio/allcalculation.do";
			idea.base.request(data,url,function(data){
				if(data.msg=="请重新登录~"){
					alert("页面超时，请重新登录");
					var url = basePath+"jsp/admin-login.jsp";
					window.location.href=url;
				}else if (data.msg=="计算成功~") {
					sdkratio.querData();
					idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
				}
			});
//			this.querData();
		}
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
	
}
