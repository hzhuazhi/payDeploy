var pageInstance = null;
var pageNo=1;
var lenth=10;
function pageNums(){
    var lenth=idea("#pageSize option:selected").val();
    this.pageNo = 1;
    ch.querData(pageNo,lenth)
	/* showDatas(condJsonData); */
}; 
var ch = {
	dataList:function(){
		this.querData(pageNo,lenth);

	},
	//查询数据
	queryList:function(){
		this.querData(pageNo,lenth);
	},
	querData:function(pageNo,lenth){
		idea("#showMessage").html("");
		var channelNum=idea("#channelNum").val();
		var channelName=idea("#channelName").val();
		var channelContacts=idea("#channelContacts").val();
		
		var pageNo2=idea("#pageNo2").val();
		var lenth2=idea("#lenth2").val();
		var start ="";
		if(pageNo2!=""){
			pageNo=pageNo2;
		}
		var start = (pageNo-1)*lenth;//开始查找的条数
		var data = {
				"channelNum":channelNum,
				"channelName":channelName,
				"channelContacts":channelContacts,
				"start" : start,
				"length" : lenth
	 	};
		var url = basePath+"ch/dataList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				var totalPage = data.page.pageCount;
				var pageSize=data.page.pageSize;
				var rowCount=data.page.rowCount;
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 		shtml +="<tr onmouseover='app.trMouseOver(this);' onmouseout='app.trMouseOut(this);' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].channelNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].channelName+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].channelContacts+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].chPhone+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].channelType+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remarks+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:ch.updateData(1,"+dataList[i].id+",\""+pageNo+"\",\""+lenth+"\")'>编辑</a>";
			 	  	shtml +="&nbsp;";
			 	  	shtml +="<a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='javascript:ch.updateData(2,"+dataList[i].id+")'>删除</a>";
			 	  	shtml +="</tr>"
			 	}
			 	idea("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
				idea("#showData").html(html2);
			}
			ch.initPageBar(pageNo,totalPage,pageSize,rowCount);
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
			var url = basePath+"jsp/tvdeploy/ch/chEdit.jsp?id="+id+"&pageNo2="+pageNo2+"&lenth2="+lenth2;
			window.location.href=url;
		}else if(type==2){
			if(window.confirm("确认：是否删除?")){
				doType=type;
				var data = {
						"id":id,
						"doType":doType,
						"yn":"1"
			 	};
				var url = basePath+"ch/doUpdate.do";
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
	editCh:function(id){
		idea("#id").val(id);
		var url = basePath+"ch/showEdit.do";
		var data = {
				"id":id
	 	};
		idea.base.request(data,url,function(data){
				idea("#channelNum").val(data.channelNum);
				idea("#channelName").val(data.channelName);
				idea("#channelContacts").val(data.channelContacts);
				idea("#chPhone").val(data.chPhone);
				idea("#remarks").val(data.remarks);
		});
//		this.hidediv();
	},
	
	//执行修改
	doUpdate:function(){
		idea("#showMessage").html("");
		var channelNum =  idea("#channelNum").val();
        var channelName =  idea("#channelName").val();
        var channelContacts =  idea("#channelContacts").val();
        var chPhone =  idea("#chPhone").val();
        var remarks =  idea("#remarks").val();
        
        var id =  idea("#id").val();
        var pageNo2 = idea("#pageNo2").val();
        var lenth2=idea("#lenth2").val();
        if(!channelNum){
			idea("#channelNum").focus();
			idea("#showMessage").html("<font color='red'>请填写渠道编号！</font> ");
			return false;
		}
		if(!channelName){
			idea("#channelName").focus();
			idea("#showMessage").html("<font color='red'>请填写渠道名称！</font> ");
			return false;
		}
		var doType=1;
		var data = {
				"id":id,
				"channelNum":channelNum,
				"channelName":channelName,
				"channelContacts":channelContacts,
				"chPhone":chPhone,
				"remarks":remarks,
				"doType":doType
	 	};
		var url = basePath+"ch/doUpdate.do";
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="修改成功~"){
				var url = basePath+"jsp/tvdeploy/ch/chIndex.jsp";
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
			var url = basePath+"jsp/tvdeploy/ch/chAdd.jsp";
			window.location.href=url;
	},
	
	//新增数据
	add:function(){
		var url = basePath+"ch/add.do";
		var channelNum =  idea("#channelNum").val();
        var channelName =  idea("#channelName").val();
        var channelContacts =  idea("#channelContacts").val();
        var chPhone =  idea("#chPhone").val();
        var remarks =  idea("#remarks").val();
		var data = {
				"channelNum":channelNum,
				"channelName":channelName,
				"channelContacts":channelContacts,
				"chPhone":chPhone,
				"remarks":remarks
	 	};
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				var url = basePath+"jsp/tvdeploy/ch/chIndex.jsp";
				alert(data.msg);
				window.location.href=url;
			}else{
				alert("页面超时，请重新登录");
				var url = basePath+"jsp/admin-login.jsp";
				window.location.href=url;
			}
		});
	},
	
	
	//查询所有渠道-无分页-下拉框选项:传入参数：默认的cp编号
	queryChAll:function(selectCpNum){
		var url = basePath+"ch/dataAllList.do";
		var data = {
	 	};
		idea.base.request(data,url,function(data){
				var dataList=data.rows;
				var shtml="";
				shtml += "<select name='select' id='chData' class='text-input medium-input'>";
				if(selectCpNum==0){
					shtml +="<option value='0,0'>===请选择===</option>";
				}
				for (var i=0;i<dataList.length>0;i++) {
					if(selectCpNum!=0){
						if(dataList[i].cpNum==selectCpNum){
							shtml +="<option  selected = 'selected' value="+dataList[i].id+","+dataList[i].channelNum+">==="+dataList[i].channelNum+"==="+dataList[i].channelName+"</option>";
						}else{
							shtml +="<option value="+dataList[i].id+","+dataList[i].cpNum+">==="+dataList[i].channelNum+"==="+dataList[i].channelName+"</option>";
						}
					}else{
						shtml +="<option value="+dataList[i].id+","+dataList[i].cpNum+">==="+dataList[i].channelNum+"==="+dataList[i].channelName+"</option>";
					}
				}
				shtml +="</select>";
				idea("#selectCp").html(shtml);
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
				ch.querData(pageIndex, pageSize2);
			}
		});

	},
	
}
