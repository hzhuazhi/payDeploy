var pageInstance = null;
var pageNo=1;
var lenth=10;
function pageNums(){
    var lenth=idea("#pageSize option:selected").val();
    this.pageNo = 1;
    stypro.querData(pageNo,lenth)
	/* showDatas(condJsonData); */
}; 
var stypro = {
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
		var limitType=idea("#limitType").val();
		
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
//				"limitType":limitType,
				"start" : start,
				"length" : lenth
	 	};
		var url = basePath+"stypro/dataList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				var totalPage = data.page.pageCount;
				var pageSize=data.page.pageSize;
				var rowCount=data.page.rowCount;
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 	 	shtml +="<tr onmouseover='stypro.trMouseOver(this)' onmouseout='stypro.trMouseOut(this)' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].appNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].channelNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].batchNum+"</td>";
			 		if(dataList[i].limitType==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>全局限制</td>";
			 	  	}else if(dataList[i].limitType==1){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>省份限制</td>";
			 	  	}
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].limitNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].isLimitNum+"</td>";
			 		if(dataList[i].isLimitOk==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>未完成</td>";
			 	  	}else if(dataList[i].isLimitOk==1){
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'>完成</td>";
			 	  	}
			 		if(dataList[i].province.length>=15){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].province+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].province.substring(0,8)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].province+"</td>";
			 	  	}
			 		if(dataList[i].city.length>=15){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].city+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].city.substring(0,8)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].city+"</td>";
			 	  	}
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:stypro.updateData(1,"+dataList[i].id+",\""+pageNo+"\",\""+lenth+"\")'>编辑</a>";
			 	  	shtml +="&nbsp;";
			 	  	shtml +="<a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='javascript:stypro.updateData(2,"+dataList[i].id+")'>删除</a>";
			 	  	shtml +="</tr>"
			 	}
			 	idea("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
				idea("#showData").html(html2);
			}
			stypro.initPageBar(pageNo,totalPage,pageSize,rowCount);
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
			var url = basePath+"jsp/tvdeploy/stypro/styproEdit.jsp?id="+id+"&pageNo2="+pageNo2+"&lenth2="+lenth2;
			window.location.href=url;
		}else if(type==2){
			if(window.confirm("确认：是否删除?")){
				doType=type;
				var data = {
						"id":id,
						"doType":doType,
						"yn":"1"
			 	};
				var url = basePath+"stypro/doUpdate.do";
				idea.base.request(data,url,function(data){
					if(data.msg=="请重新登录~"){
						alert("页面超时，请重新登录");
						var url = basePath+"jsp/admin-login.jsp";
						window.location.href=url;
					}else if (data.msg=="删除成功~") {
						idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
					}
				});
				stypro.querData(pageNo,lenth);
			}
		}
	},
	
	
	//加载编辑数据
	editStypro:function(id){
		idea("#id").val(id);
		var url = basePath+"stypro/showEdit.do";
		var data = {
				"id":id
	 	};
		idea.base.request(data,url,function(data){
				idea("#cpNum").val(data.cpNum);
				idea("#appNum").val(data.appNum);
				idea("#channelNum").val(data.channelNum);
				idea("#batchNum").val(data.batchNum);
				//限制类型
				var shtml_limitType="";
				shtml_limitType += "<select name='select' id='limitType' class='text-input medium-input'>";
				shtml_limitType += "<option value='-1'>===请选择===</option>";
				if(data.limitType==0){
					shtml_limitType +="<option selected='selected' value='0'>全局限制</option>";
					shtml_limitType +="<option value='1'>省份限制</option>";
				}else if(data.limitType==1){
					shtml_limitType +="<option value='0'>全局限制</option>";
					shtml_limitType +="<option selected='selected' value='1'>省份限制</option>";
				}
				shtml_limitType +="</select>";
				idea("#selectlimitType").html(shtml_limitType);
				
				idea("#limitNum").val(data.limitNum);
				idea("#isLimitNum").val(data.isLimitNum);
				//限制类型
				var shtml_isLimitOk="";
				shtml_isLimitOk += "<select name='select' id='isLimitOk' class='text-input medium-input'>";
				shtml_isLimitOk += "<option value='-1'>===请选择===</option>";
				if(data.isLimitOk==0){
					shtml_isLimitOk +="<option selected='selected' value='0'>全局限制</option>";
					shtml_isLimitOk +="<option value='1'>省份限制</option>";
				}else if(data.isLimitOk==1){
					shtml_isLimitOk +="<option value='0'>全局限制</option>";
					shtml_isLimitOk +="<option selected='selected' value='1'>省份限制</option>";
				}
				shtml_isLimitOk +="</select>";
				idea("#selectisLimitOk").html(shtml_isLimitOk);
				idea("#province").val(data.province);
				idea("#city").val(data.city);
				
		});
//		this.hidediv();
	},
	
	//执行修改
	doUpdate:function(){
		idea("#showMessage").html("");
		var cpNum=idea("#cpNum").val();
		var appNum=idea("#appNum").val();
		var channelNum=idea("#channelNum").val();
		var batchNum=idea("#batchNum").val();
		var limitType=idea("#limitType").val();
		var limitNum=idea("#limitNum").val();
		var isLimitNum=idea("#isLimitNum").val();
		var isLimitOk=idea("#isLimitOk").val();
		var province=idea("#province").val();
		var city=idea("#city").val();
		
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
				"limitType":limitType,
				"limitNum":limitNum,
				"isLimitNum":isLimitNum,
				"isLimitOk":isLimitOk,
				"province":province,
				"city":city,
				"doType":doType
	 	};
		var url = basePath+"stypro/doUpdate.do";
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="修改成功~"){
				var url = basePath+"jsp/tvdeploy/stypro/styproIndex.jsp";
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
			var url = basePath+"jsp/tvdeploy/stypro/styproAdd.jsp";
			window.location.href=url;
	},
	//新增数据
	add:function(){
		var url = basePath+"stypro/add.do";
		var cpNum=idea("#cpNum").val();
		var appNum=idea("#appNum").val();
		var channelNum=idea("#channelNum").val();
		var batchNum=idea("#batchNum").val();
		var limitType=idea("#limitType").val();
		var limitNum=idea("#limitNum").val();
		var isLimitNum=idea("#isLimitNum").val();
		var isLimitOk=idea("#isLimitOk").val();
		var province=idea("#province").val();
		var city=idea("#city").val();
		
		var data = {
				"cpNum":cpNum,
				"appNum":appNum,
				"channelNum":channelNum,
				"batchNum":batchNum,
				"limitType":limitType,
				"limitNum":limitNum,
				"isLimitNum":isLimitNum,
				"isLimitOk":isLimitOk,
				"province":province,
				"city":city
	 	};
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				var url = basePath+"jsp/tvdeploy/stypro/styproIndex.jsp";
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
				stypro.querData(pageIndex, pageSize2);
			}
		});

	},
	
}
