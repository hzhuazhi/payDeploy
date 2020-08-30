var pageInstance = null;
var pageNo=1;
var lenth=10;
function pageNums(){
    var lenth=idea("#pageSize option:selected").val();
    this.pageNo = 1;
    fl.querData(pageNo,lenth)
	/* showDatas(condJsonData); */
}; 
var fl = {
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
		var fileName=idea("#fileName").val();
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
				"fileName":fileName,
				"start" : start,
				"length" : lenth
	 	};
		var url = basePath+"fl/dataList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				var totalPage = data.page.pageCount;
				var pageSize=data.page.pageSize;
				var rowCount=data.page.rowCount;
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 	 	shtml +="<tr onmouseover='fl.trMouseOver(this)' onmouseout='fl.trMouseOut(this)' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].cpNum+"</td>";
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].appNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].channelNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].batchNum+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].fileName+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].filePkgName+"</td>";
			 		if(dataList[i].filePath.length>=15){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].filePath+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].filePath.substring(0,8)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].filePath+"</td>";
			 	  	}
			 		if(dataList[i].downUrl.length>=15){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].downUrl+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].downUrl.substring(0,8)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].downUrl+"</td>";
			 	  	}
			 		if(dataList[i].fileType==1){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>root文件</td>";
			 		}else if(dataList[i].fileType==2){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>app文件</td>";
			 		}
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].ver+"</td>";
			 		if(dataList[i].downLimit==0){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>没有限制</td>";
			 		}else if(dataList[i].downLimit==1){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>省份限制</td>";
			 		}else if(dataList[i].downLimit==2){
			 			shtml +="<td style='text-align: center; white-space:nowrap'>其它限制</td>";
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
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remarks+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:fl.updateData(1,"+dataList[i].id+",\""+pageNo+"\",\""+lenth+"\")'>编辑</a>";
			 	  	shtml +="&nbsp;";
			 	  	shtml +="<a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='javascript:fl.updateData(2,"+dataList[i].id+")'>删除</a>";
			 	  	shtml +="&nbsp;";
			 	  	shtml +="<a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='"+dataList[i].downUrl+"'>下载</a>";
			 	  	shtml +="</tr>"
			 	}
			 	idea("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
				idea("#showData").html(html2);
			}
			fl.initPageBar(pageNo,totalPage,pageSize,rowCount);
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
			var url = basePath+"jsp/tvdeploy/fl/flEdit.jsp?id="+id+"&pageNo2="+pageNo2+"&lenth2="+lenth2;
			window.location.href=url;
		}else if(type==2){
			if(window.confirm("确认：是否删除?")){
				doType=type;
				var data = {
						"id":id,
						"doType":doType,
						"yn":"1"
			 	};
				var url = basePath+"fl/doUpdate.do";
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
	editFl:function(id){
		idea("#id").val(id);
		var url = basePath+"fl/showEdit.do";
		var data = {
				"id":id
	 	};
		idea.base.request(data,url,function(data){
				idea("#cpNum").val(data.cpNum);
				idea("#appNum").val(data.appNum);
				idea("#channelNum").val(data.channelNum);
				idea("#batchNum").val(data.batchNum);
				idea("#filePkgName").val(data.filePkgName);
				//文件类型
				var shtml_fileType="";
				shtml_fileType += "<select name='fileType' id='fileType' class='text-input medium-input'>";
				shtml_fileType += "<option value='0'>===请选择===</option>";
				if(data.fileType==1){
					shtml_fileType +="<option selected='selected' value='1'>root文件</option>";
					shtml_fileType +="<option value='2'>app文件</option>";
				}else if(data.fileType==2){
					shtml_fileType +="<option value='1'>root文件</option>";
					shtml_fileType +="<option selected='selected' value='2'>app文件</option>";
				}
				shtml_fileType +="</select>";
				idea("#selectfileType").html(shtml_fileType);
				idea("#ver").val(data.ver);
				
				//下载限制
				var shtml_downLimit="";
				shtml_downLimit += "<select name='downLimit' id='downLimit' class='text-input medium-input'>";
				shtml_downLimit += "<option value='0'>===请选择===</option>";
				if(data.downLimit==1){
					shtml_downLimit +="<option selected='selected' value='1'>省份限制</option>";
					shtml_downLimit +="<option value='2'>其它限制</option>";
				}else if(data.downLimit==2){
					shtml_downLimit +="<option value='1'>省份限制</option>";
					shtml_downLimit +="<option selected='selected' value='2'>其它限制</option>";
				}
				shtml_downLimit +="</select>";
				idea("#selectdownLimit").html(shtml_downLimit);
				idea("#province").val(data.province);
				idea("#city").val(data.city);
				idea("#downUrl").val(data.downUrl);
				
				idea("#remarks").val(data.remarks);
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
		var filePkgName=idea("#filePkgName").val();
		var fileType=idea("#fileType").val();
		var ver=idea("#ver").val();
		var downLimit=idea("#downLimit").val();
		var province=idea("#province").val();
		var city=idea("#city").val();
		var downUrl=idea("#downUrl").val();
		var remarks=idea("#remarks").val();
		
        var id =  idea("#id").val();
        var pageNo2 = idea("#pageNo2").val();
        var lenth2=idea("#lenth2").val();
        if(!cpNum){
			idea("#cpData").focus();
			idea("#showMessage").html("<font color='red'>请填写CP编号！</font> ");
			return false;
		}
		if(!appNum){
			idea("#appNum").focus();
			idea("#showMessage").html("<font color='red'>请填写APP编号！</font> ");
			return false;
		}
		var doType=1;
		var data = {
				"id":id,
				"cpNum":cpNum,
				"appNum":appNum,
				"channelNum":channelNum,
				"batchNum":batchNum,
				"filePkgName":filePkgName,
				"fileType":fileType,
				"ver":ver,
				"downLimit":downLimit,
				"province":province,
				"city":city,
//				"file":file,
				"downUrl":downUrl,
				"remarks":remarks,
				"doType":doType
	 	};
		var url = basePath+"fl/doUpdate.do";
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="修改成功~"){
				alert(data.msg);
				var url = basePath+"jsp/tvdeploy/fl/flIndex.jsp";
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
			var url = basePath+"jsp/tvdeploy/fl/flAdd.jsp";
			window.location.href=url;
	},
	
	//新增数据
	add:function(){
		var url = basePath+"fl/add.do";
		var cpNum=idea("#cpNum").val();
		var appNum=idea("#appNum").val();
		var channelNum=idea("#channelNum").val();
		var batchNum=idea("#batchNum").val();
		var filePkgName=idea("#filePkgName").val();
		var fileType=idea("#fileType").val();
		var ver=idea("#ver").val();
		var downLimit=idea("#downLimit").val();
		var province=idea("#province").val();
		var city=idea("#city").val();
//		var file=idea("#file").val();
		var remarks=idea("#remarks").val();
		var data = {
				"cpNum":cpNum,
				"appNum":appNum,
				"channelNum":channelNum,
				"batchNum":batchNum,
				"filePkgName":filePkgName,
				"fileType":fileType,
				"ver":ver,
				"downLimit":downLimit,
				"province":province,
				"city":city,
//				"file":file,
				"remarks":remarks
	 	};
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				alert(data.msg);
				var url = basePath+"jsp/tvdeploy/fl/flIndex.jsp";
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
				fl.querData(pageIndex, pageSize2);
			}
		});

	},
	
}
