var pageInstance = null;
var pageNo=1;
var lenth=10;
function pageNums(){
    var lenth=idea("#pageSize option:selected").val();
    this.pageNo = 1;
    sdktpt.querData(pageNo,lenth)
	/* showDatas(condJsonData); */
}; 
var sdktpt = {
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
		var sdkNum=idea("#sdkNum").val();
		
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
				"sdkNum":sdkNum,
				"start" : start,
				"length" : lenth
	 	};
		var url = basePath+"sdktpt/dataList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				var totalPage = data.page.pageCount;
				var pageSize=data.page.pageSize;
				var rowCount=data.page.rowCount;
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 	 	shtml +="<tr onmouseover='sdktpt.trMouseOver(this)' onmouseout='sdktpt.trMouseOut(this)' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
//			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpNum+"</td>";
			 	 	if(dataList[i].cpNum.length>=15){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].cpNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].cpNum.substring(0,8)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].cpNum+"</td>";
			 	  	}
//			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].appNum+"</td>";
			 	 	if(dataList[i].appNum.length>=15){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].appNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].appNum.substring(0,8)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].appNum+"</td>";
			 	  	}
			 		if(dataList[i].channelNum.length>=15){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].channelNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].channelNum.substring(0,8)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].channelNum+"</td>";
			 	  	}
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].sdkNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].fieldNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field1+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark1+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field2+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark2+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field3+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark3+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field4+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark4+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field5+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark5+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field6+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark6+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field7+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark7+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field8+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark8+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field9+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark9+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].field10+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remark10+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:sdktpt.updateData(1,"+dataList[i].id+",\""+pageNo+"\",\""+lenth+"\")'>编辑</a>";
			 	  	shtml +="&nbsp;";
			 	  	shtml +="<a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='javascript:sdktpt.updateData(2,"+dataList[i].id+")'>删除</a>";
			 	  	shtml +="</tr>"
			 	}
			 	idea("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
				idea("#showData").html(html2);
			}
			sdktpt.initPageBar(pageNo,totalPage,pageSize,rowCount);
			idea("#pageNo2").val('');
			idea("#lenth2").val('');
		});
	},
	
	//type=1，编辑要展现的数据
	//type=2，删除：业务逻辑删除
	updateData:function(type,id,isOnline,pageNo2,lenth2){
		//强制影藏其它层
//		this.hideAddDiv();
//		this.hidediv();
		idea("#showUpMsg").html("");
		if(type==1){
			var url = basePath+"jsp/tvdeploy/sdktpt/sdktptEdit.jsp?id="+id+"&pageNo2="+pageNo2+"&lenth2="+lenth2;
			window.location.href=url;
		}else if(type==2){
			if(window.confirm("确认：是否删除?")){
				doType=type;
				var data = {
						"id":id,
						"doType":doType,
						"yn":"1"
			 	};
				var url = basePath+"sdktpt/doUpdate.do";
				idea.base.request(data,url,function(data){
					if(data.msg=="请重新登录~"){
						alert("页面超时，请重新登录");
						var url = basePath+"jsp/admin-login.jsp";
						window.location.href=url;
					}else if (data.msg=="删除成功~") {
						idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
					}
				});
				this.querData(pageNo,lenth);
			}
		}
	},
	
	
	//加载编辑数据
	editSdktpt:function(id){
		idea("#id").val(id);
		var url = basePath+"sdktpt/showEdit.do";
		var data = {
				"id":id
	 	};
		idea.base.request(data,url,function(data){
				idea("#cpNum").val(data.cpNum);
				idea("#appNum").val(data.appNum);
				idea("#channelNum").val(data.channelNum);
				idea("#sdkNum").val(data.sdkNum);
				idea("#fieldNum").val(data.fieldNum);
				idea("#field1").val(data.field1);
				idea("#remark1").val(data.remark1);
				idea("#field2").val(data.field2);
				idea("#remark2").val(data.remark2);
				idea("#field3").val(data.field3);
				idea("#remark3").val(data.remark3);
				idea("#field4").val(data.field4);
				idea("#remark4").val(data.remark4);
				idea("#field5").val(data.field5);
				idea("#remark5").val(data.remark5);
				idea("#field6").val(data.field6);
				idea("#remark6").val(data.remark6);
				idea("#field7").val(data.field7);
				idea("#remark7").val(data.remark7);
				idea("#field8").val(data.field8);
				idea("#remark8").val(data.remark8);
				idea("#field9").val(data.field9);
				idea("#remark9").val(data.remark9);
				idea("#field10").val(data.field10);
				idea("#remark10").val(data.remark10);
		});
//		this.hidediv();
	},
	
	//执行修改
	doUpdate:function(){
		idea("#showMessage").html("");
		var cpNum =  idea("#cpNum").val();
		var appNum =  idea("#appNum").val();
		var channelNum =  idea("#channelNum").val();
		var sdkNum =  idea("#sdkNum").val();
		var fieldNum =  idea("#fieldNum").val();
        var field1 =  idea("#field1").val();
        var remark1 =  idea("#remark1").val();
        var field2 =  idea("#field2").val();
        var remark2 =  idea("#remark2").val();
        var field3 =  idea("#field3").val();
        var remark3 =  idea("#remark3").val();
        var field4 =  idea("#field4").val();
        var remark4 =  idea("#remark4").val();
        var field5 =  idea("#field5").val();
        var remark5 =  idea("#remark5").val();
        var field6 =  idea("#field6").val();
        var remark6 =  idea("#remark6").val();
        var field7 =  idea("#field7").val();
        var remark7 =  idea("#remark7").val();
        var field8 =  idea("#field8").val();
        var remark8 =  idea("#remark8").val();
        var field9 =  idea("#field9").val();
        var remark9 =  idea("#remark9").val();
        var field10 =  idea("#field10").val();
        var remark10 =  idea("#remark10").val();
        
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
			idea("#showMessage").html("<font color='red'>请填写APP编号！</font> ");
			return false;
		}
		if(!channelNum){
			idea("#channelNum").focus();
			idea("#showMessage").html("<font color='red'>请填写渠道编号！</font> ");
			return false;
		}
		if(!sdkNum){
			idea("#sdkNum").focus();
			idea("#showMessage").html("<font color='red'>请填写SDK编号！</font> ");
			return false;
		}
		var doType=1;
		var data = {
				"id":id,
				"cpNum":cpNum,
				"appNum":appNum,
				"channelNum":channelNum,
				"sdkNum":sdkNum,
				"fieldNum":fieldNum,
				"field1":field1,
				"remark1":remark1,
				"field2":field2,
				"remark2":remark2,
				"field3":field3,
				"remark3":remark3,
				"field4":field4,
				"remark4":remark4,
				"field5":field5,
				"remark5":remark5,
				"field6":field6,
				"remark6":remark6,
				"field7":field7,
				"remark7":remark7,
				"field8":field8,
				"remark8":remark8,
				"field9":field9,
				"remark9":remark9,
				"field10":field10,
				"remark10":remark10,
				"doType":doType
	 	};
		var url = basePath+"sdktpt/doUpdate.do";
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="修改成功~"){
				var url = basePath+"jsp/tvdeploy/sdktpt/sdktptIndex.jsp";
				alert(data.msg);
				window.location.href=url;
			}else{
				alert("页面超时，请重新登录");
				var url = basePath+"jsp/admin-login.jsp";
				window.location.href=url;
			}
		});
	},
	
	//导入文件
	addExcel:function(){
		   var a = idea("#upload").val(); 
	       var d=a.split(".");
        if(a==""){
            alert("请选择路径，再进行上传！");
            return;
        }
        var url = basePath+"sdktpt/addExcel.do";
		var queryFrm= document.getElementById("queryFrm");
		document.getElementById("queryFrm").action=url;
	    document.getElementById("queryFrm").submit();
	},
	
	//跳转到新增页面
	addUrl:function(){
			var url = basePath+"jsp/tvdeploy/sdktpt/sdktptAdd.jsp";
			window.location.href=url;
	},
	
	//新增数据
	add:function(){
		var url = basePath+"sdktpt/add.do";
		var cpNum =  idea("#cpNum").val();
		var appNum =  idea("#appNum").val();
		var channelNum =  idea("#channelNum").val();
		var sdkNum =  idea("#sdkNum").val();
		var fieldNum =  idea("#fieldNum").val();
        var field1 =  idea("#field1").val();
        var remark1 =  idea("#remark1").val();
        var field2 =  idea("#field2").val();
        var remark2 =  idea("#remark2").val();
        var field3 =  idea("#field3").val();
        var remark3 =  idea("#remark3").val();
        var field4 =  idea("#field4").val();
        var remark4 =  idea("#remark4").val();
        var field5 =  idea("#field5").val();
        var remark5 =  idea("#remark5").val();
        var field6 =  idea("#field6").val();
        var remark6 =  idea("#remark6").val();
        var field7 =  idea("#field7").val();
        var remark7 =  idea("#remark7").val();
        var field8 =  idea("#field8").val();
        var remark8 =  idea("#remark8").val();
        var field9 =  idea("#field9").val();
        var remark9 =  idea("#remark9").val();
        var field10 =  idea("#field10").val();
        var remark10 =  idea("#remark10").val();
        
		var data = {
				"cpNum":cpNum,
				"appNum":appNum,
				"channelNum":channelNum,
				"sdkNum":sdkNum,
				"fieldNum":fieldNum,
				"field1":field1,
				"remark1":remark1,
				"field2":field2,
				"remark2":remark2,
				"field3":field3,
				"remark3":remark3,
				"field4":field4,
				"remark4":remark4,
				"field5":field5,
				"remark5":remark5,
				"field6":field6,
				"remark6":remark6,
				"field7":field7,
				"remark7":remark7,
				"field8":field8,
				"remark8":remark8,
				"field9":field9,
				"remark9":remark9,
				"field10":field10,
				"remark10":remark10
	 	};
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				var url = basePath+"jsp/tvdeploy/sdktpt/sdktptIndex.jsp";
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
				sdktpt.querData(pageIndex, pageSize2);
			}
		});

	},
	
	
}
