var pageInstance = null;
var pageNo=1;
var lenth=50;
function pageNums(){
    var lenth=idea("#pageSize option:selected").val();
    this.pageNo = 1;
    appsty.querData(pageNo,lenth)
	/* showDatas(condJsonData); */
}; 
var appsty = {
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
		var appNum=idea("#appNum").val();
		var cpNum=idea("#cpNum").val();
		
		var pageNo2=idea("#pageNo2").val();
		var lenth2=idea("#lenth2").val();
		var start ="";
		if(pageNo2!=""){
			pageNo=pageNo2;
		}
		var start = (pageNo-1)*lenth;//开始查找的条数
		var data = {
				"channelNum":channelNum,
				"appNum":appNum,
				"cpNum":cpNum,
				"start" : start,
				"length" : lenth
	 	};
		var url = basePath+"appsty/dataList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				var totalPage = data.page.pageCount;
				var pageSize=data.page.pageSize;
				var rowCount=data.page.rowCount;
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 		shtml +="<tr role='row' class='odd' onmouseover='appsty.trMouseOver(this)' onmouseout='appsty.trMouseOut(this)' >"

			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 	 	if(dataList[i].channelNum.length>=15){
//			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].channelNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].channelNum.substring(0,8)+"。。</td>";
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].channelNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'><a onclick='appsty.showChannelTemplate("+dataList[i].id+",\""+dataList[i].channelNum+"\")'>"+dataList[i].channelNum.substring(0,8)+"。。</a></td>";
			 	  	}else{
//			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].channelNum+"</td>";
			 	  		shtml +="<td style='text-align: center; white-space:nowrap'><a onclick='appsty.showChannelTemplate("+dataList[i].id+",\""+dataList[i].channelNum+"\")'>"+dataList[i].channelNum+"</a></td>";
			 	  	}
			 	 	
			 	 	
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].appNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].chargeId+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].chargeNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].chargeName+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].description+"</td>";
//			 		if(dataList[i].description.length>=15){
//			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].description+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].description.substring(0,8)+"。。</td>";
//			 	  	}else{
//			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].description+"</td>";
//			 	  	}
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].fee+"</td>";
			 		if(dataList[i].isopen==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>关</td>";
			 		}else{
			 			shtml +="<td style='text-align: center; white-space:nowrap'>开</td>";
			 		}
			 		if(dataList[i].status==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>诱导</td>";
			 		}else if(dataList[i].status==1){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>正规</td>";
			 		}else if(dataList[i].status==2){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>强制</td>";
			 		}
			 		if(dataList[i].ver==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>审核版本</td>";
			 		}else if(dataList[i].ver==1){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>运营版本</td>";
			 		}
			 		if(dataList[i].ischarge==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>收费</td>";
			 		}else if(dataList[i].ischarge==1){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>不收费</td>";
			 		}
			 		if(dataList[i].again==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>无需二次确认</td>";
			 		}else if(dataList[i].again==1){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>需要二次确认</td>";
			 		}
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:appsty.updateData(1,"+dataList[i].id+",\""+pageNo+"\",\""+lenth+"\")'>编辑</a>";
			 	  	shtml +="&nbsp;";
			 	  	shtml +="<a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='javascript:appsty.updateData(2,"+dataList[i].id+")'>删除</a>";
			 	  	shtml +="</tr>"
			 	}
			 	idea("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
				idea("#showData").html(html2);
			}
			appsty.initPageBar(pageNo,totalPage,pageSize,rowCount);
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
			var url = basePath+"jsp/tvdeploy/appsty/appstyEdit.jsp?id="+id+"&pageNo2="+pageNo2+"&lenth2="+lenth2;
			window.location.href=url;
		}else if(type==2){
			if(window.confirm("确认：是否删除?")){
				doType=type;
				var data = {
						"id":id,
						"doType":doType,
						"yn":"1"
			 	};
				var url = basePath+"appsty/doUpdate.do";
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
	editAppsty:function(id){
		idea("#id").val(id);
		var url = basePath+"appsty/showEdit.do";
		var data = {
				"id":id
	 	};
		idea.base.request(data,url,function(data){
				idea("#channelNum").val(data.channelNum);
				idea("#appNum").val(data.appNum);
				idea("#cpNum").val(data.cpNum);
				idea("#chargeId").val(data.chargeId);
				idea("#chargeNum").val(data.chargeNum);
				idea("#chargeName").val(data.chargeName);
				idea("#description").val(data.description);
				idea("#fee").val(data.fee);
				//计费点开关
				var shtml_isopen="";
				shtml_isopen += "<select name='select' id='isopen' class='text-input medium-input'>";
				shtml_isopen += "<option value='-1'>===请选择===</option>";
				if(data.isopen==0){
					shtml_isopen +="<option selected='selected' value='0'>关</option>";
					shtml_isopen +="<option value='1'>开</option>";
				}else if(data.isopen==1){
					shtml_isopen +="<option value='0'>关</option>";
					shtml_isopen +="<option selected='selected' value='1'>开</option>";
				}
				shtml_isopen +="</select>";
				idea("#selectisopen").html(shtml_isopen);
				
				//计费类型
				var shtml_status="";
				shtml_status += "<select name='select' id='status' class='text-input medium-input'>";
				shtml_status += "<option value='-1'>===请选择===</option>";
				if(data.status==0){
					shtml_status +="<option selected='selected' value='0'>诱导</option>";
					shtml_status +="<option value='1'>正规</option>";
					shtml_status +="<option value='2'>强制</option>";
				}else if(data.status==1){
					shtml_status +="<option value='0'>诱导</option>";
					shtml_status +="<option selected='selected' value='1'>正规</option>";
					shtml_status +="<option value='2'>强制</option>";
				}else if(data.status==2){
					shtml_status +="<option value='0'>诱导</option>";
					shtml_status +="<option value='1'>正规</option>";
					shtml_status +="<option selected='selected' value='2'>强制</option>";
				}
				shtml_isopen +="</select>";
				idea("#selectstatus").html(shtml_status);
				
				//启用版本
				var shtml_ver="";
				shtml_ver += "<select name='select' id='ver' class='text-input medium-input'>";
				shtml_ver += "<option value='-1'>===请选择===</option>";
				if(data.ver==0){
					shtml_ver +="<option selected='selected' value='0'>审核版本</option>";
					shtml_ver +="<option value='1'>运用版本</option>";
				}else if(data.ver==1){
					shtml_ver +="<option value='0'>审核版本</option>";
					shtml_ver +="<option selected='selected' value='1'>运用版本</option>";
				}
				shtml_ver +="</select>";
				idea("#selectver").html(shtml_ver);
				
				//是否收费
				var shtml_ischarge="";
				shtml_ischarge += "<select name='select' id='ischarge' class='text-input medium-input'>";
				shtml_ischarge += "<option value='-1'>===请选择===</option>";
				if(data.ischarge==0){
					shtml_ischarge +="<option selected='selected' value='0'>收费</option>";
					shtml_ischarge +="<option value='1'>不收费</option>";
				}else if(data.ischarge==1){
					shtml_ischarge +="<option value='0'>收费</option>";
					shtml_ischarge +="<option selected='selected' value='1'>不收费</option>";
				}
				shtml_ischarge +="</select>";
				idea("#selectischarge").html(shtml_ischarge);
				
				//是否二次确认
				var shtml_again="";
				shtml_again += "<select name='select' id='again' class='text-input medium-input'>";
				shtml_again += "<option value='-1'>===请选择===</option>";
				if(data.again==0){
					shtml_again +="<option selected='selected' value='0'>无需二次确认</option>";
					shtml_again +="<option value='1'>需要二次确认</option>";
				}else if(data.again==1){
					shtml_again +="<option value='0'>无需二次确认</option>";
					shtml_again +="<option selected='selected' value='1'>需要二次确认</option>";
				}
				shtml_again +="</select>";
				idea("#selectagain").html(shtml_again);
		});
//		this.hidediv();
	},
	
	//执行修改
	doUpdate:function(){
		idea("#showMessage").html("");
        var id =  idea("#id").val();
        var channelNum=idea("#channelNum").val();
		var appNum=idea("#appNum").val();
		var cpNum=idea("#cpNum").val();
		var chargeId=idea("#chargeId").val();
		var chargeNum=idea("#chargeNum").val();
		var chargeName=idea("#chargeName").val();
		var description=idea("#description").val();
		var fee=idea("#fee").val();
		var isopen=idea("#isopen").val();
		var status=idea("#status").val();
		var ver=idea("#ver").val();
		var ischarge=idea("#ischarge").val();
		var again=idea("#again").val();
        
        var pageNo2 = idea("#pageNo2").val();
        var lenth2=idea("#lenth2").val();
        
        if(!channelNum){
			idea("#channelNum").focus();
			idea("#showMessage").html("<font color='red'>请填写渠道编号！</font> ");
			return false;
		}
        if(!appNum){
			idea("#appNum").focus();
			idea("#showMessage").html("<font color='red'>请填写APP编号！</font> ");
			return false;
		}
        if(!cpNum){
			idea("#cpNum").focus();
			idea("#showMessage").html("<font color='red'>请填写CP编号！</font> ");
			return false;
		}
		var doType=1;
		var data = {
				"id":id,
				"channelNum":channelNum,
				"appNum":appNum,
				"cpNum":cpNum,
				"chargeId":chargeId,
				"chargeNum":chargeNum,
				"chargeName":chargeName,
				"description":description,
				"fee":fee,
				"isopen":isopen,
				"status":status,
				"ver":ver,
				"ischarge":ischarge,
				"again":again,
				"doType":doType
	 	};
		var url = basePath+"appsty/doUpdate.do";
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="修改成功~"){
				var url = basePath+"jsp/tvdeploy/appsty/appstyIndex.jsp";
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
			var url = basePath+"jsp/tvdeploy/appsty/appstyAdd.jsp";
			window.location.href=url;
	},
	
	//新增数据
	add:function(){
		var url = basePath+"appsty/add.do";
		var channelNum=idea("#channelNum").val();
		var appNum=idea("#appNum").val();
		var cpNum=idea("#cpNum").val();
		var chargeId=idea("#chargeId").val();
		var chargeNum=idea("#chargeNum").val();
		var chargeName=idea("#chargeName").val();
		var description=idea("#description").val();
		var fee=idea("#fee").val();
		var isopen=idea("#isopen").val();
		var status=idea("#status").val();
		var ver=idea("#ver").val();
		var ischarge=idea("#ischarge").val();
		var again=idea("#again").val();
		
		var data = {
				"channelNum":channelNum,
				"appNum":appNum,
				"cpNum":cpNum,
				"chargeId":chargeId,
				"chargeNum":chargeNum,
				"chargeName":chargeName,
				"description":description,
				"fee":fee,
				"isopen":isopen,
				"status":status,
				"ver":ver,
				"ischarge":ischarge,
				"again":again
	 	};
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				var url = basePath+"jsp/tvdeploy/appsty/appstyIndex.jsp";
				alert(data.msg);
				window.location.href=url;
			}else{
				alert("页面超时，请重新登录");
				var url = basePath+"jsp/admin-login.jsp";
				window.location.href=url;
			}
		});
	},
	
	
	
	//展现渠道号
	showChannelTemplate:function(id,channelNum){
		//强制影藏其它层
		idea("#show").css('display','none');
		idea("#showAddMsg").html("");
		idea("#addHtmlData").html("");
		
		var shtml="";
		shtml +="<form method='get' id='codetemplate'>";
		shtml +="<div class='d-titleBar'>";
		shtml +="<div class='d-title' style='display: block;'>";
		shtml +="渠道号编辑 ";
		shtml +="</div>";
		shtml +="<a class='d-close' onClick='appsty.hideAddDiv()' style='display: block;'>×</a>";
		shtml +="<table style='width:90%;margin:auto;' id='priority' name='priority'>";
		shtml +="<tr>"
 	    shtml +="<td> <label>渠道号<span style='color:red'>*</span></label></td>";
		shtml +="<td> <textarea name='channelNumStr' id='channelNumStr' style='form textarea' rows='10'>"+channelNum+"</textarea></td>";
	 	shtml +="</tr>"
 	  
 	  	shtml +="<input type='hidden' id='id' name='id' value="+id+">";
	 	shtml +="</table>";
	 	shtml +="<div class='content-box-header'>";
	 	shtml +=" <div align='center'>";
	 	shtml +="<input type='button' value='提交' class='formBtn' onClick='appsty.doChannelNumTemplate("+id+")'/>"; 
	 	shtml +="</div>";
		shtml +="</div>";
		shtml +="</form>";
	 	idea("#addHtmlData").html(shtml);
		
		
		idea("#show1").css('display','block'); 
		idea("#pagebar").css('display','none'); 
	},
	
	//新增or编辑渠道号数据
	doChannelNumTemplate:function(id){
		var url = basePath+"appsty/upchaNum.do";
		var id=idea("#id").val();
		var channelNumStr=idea("#channelNumStr").val();
		if(!channelNumStr){
			idea("#channelNumStr").focus();
			idea("#showAddMsg").html("<font color='red' style='font-size: 20' >渠道号不能为空！</font> ");
			return false;
		}
		var data = {
				"id":id,
				"channelNumStr":channelNumStr
	 	};
		idea.base.request(data,url,function(data){
			idea("#showAddMsg").html("<font color='red'>"+data.msg+"</font> ");
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
				appsty.querData(pageIndex, pageSize2);
			}
		});

	},
	
}
