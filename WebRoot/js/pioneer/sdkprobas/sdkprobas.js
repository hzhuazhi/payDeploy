var pageInstance = null;
var pageNo=1;
var lenth=10;
//document.write("<script src='"+basePath+"+js/idea.js' type='text/javascript'></script>");
function pageNums(){
    var lenth=idea("#pageSize option:selected").val();
    this.pageNo = 1;
    sdkprobas.querData(pageNo,lenth)
	/* showDatas(condJsonData); */
}; 
var sdkprobas = {
	dataList:function(){
		this.querData(pageNo,lenth);

	},
	//查询数据
	queryList:function(){
		this.querData(pageNo,lenth);
	},
	querData:function(pageNo,lenth){
		idea("#showMessage").html("");
		var dataDay=idea("#dataDay").val();
		
		var pageNo2=idea("#pageNo2").val();
		var lenth2=idea("#lenth2").val();
		var start ="";
		if(pageNo2!=""){
			pageNo=pageNo2;
		}
		var start = (pageNo-1)*lenth;//开始查找的条数
		var data = {
				"dataDay":dataDay,
				"start" : start,
				"length" : lenth
	 	};
		var url = basePath+"sdkprobas/dataList.do";
		idea.base.request(data,url,function(data){
			if(data.rows.length>0){
				var dataList=data.rows; 
				var totalPage = data.page.pageCount;
				var pageSize=data.page.pageSize;
				var rowCount=data.page.rowCount;
				var shtml="";
			 	for(var i=0;i<dataList.length>0;i++){
			 	 	shtml +="<tr onmouseover='sdkprobas.trMouseOver(this)' onmouseout='sdkprobas.trMouseOut(this)' >"
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+(i+1)+"</td>";
			 	 	shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].dataDay+"</td>";
			 	 	if(dataList[i].allOpProInfo.length>=20){
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].allOpProInfo+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].allOpProInfo.substring(0,11)+"。。</td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'>"+dataList[i].allOpProInfo+"</td>";
			 	  		
			 	  	}
			 	 	
			 	 	
			 	 	
		 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].ydProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a class='formBtn' onclick='sdkprobas.addTemplate("+1+",\""+dataList[i].dataDay+"\",\""+dataList[i].ydSdk+"\")'>移动</a></td>";
		 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].ltProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a class='formBtn' onclick='sdkprobas.addTemplate("+2+",\""+dataList[i].dataDay+"\",\""+dataList[i].ltSdk+"\")'>联通</a></td>";
		 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].dxProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a class='formBtn' onclick='sdkprobas.addTemplate("+3+",\""+dataList[i].dataDay+"\",\""+dataList[i].dxSdk+"\")'>电信</a></td>";
			 	 	
			 	 	
			 	 	/*if(dataList[i].ydProNum.length>=20){
//			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].ydProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a onclick='sdkprobas.addTemplate("+1+",\""+dataList[i].dataDay+"\",\""+dataList[i].ydSdk+"\")'>"+dataList[i].ydProNum.substring(0,20)+"。。</a></td>";
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].ydProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a class='formBtn' onclick='sdkprobas.addTemplate("+1+",\""+dataList[i].dataDay+"\",\""+dataList[i].ydSdk+"\")'>移动</a></td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'><a onclick='sdkprobas.addTemplate("+1+",\""+dataList[i].dataDay+"\",\""+dataList[i].ydSdk+"\")'>"+dataList[i].ydProNum+"</a></td>";
			 	  		
			 	  	}
			 	 	
			 	 	if(dataList[i].ltProNum.length>=20){
//			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].ltProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a onclick='sdkprobas.addTemplate("+2+",\""+dataList[i].dataDay+"\",\""+dataList[i].ltSdk+"\")'>"+dataList[i].ltProNum.substring(0,20)+"。。</a></td>";
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].ltProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a class='formBtn' onclick='sdkprobas.addTemplate("+2+",\""+dataList[i].dataDay+"\",\""+dataList[i].ltSdk+"\")'>联通</a></td>";
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'><a onclick='sdkprobas.addTemplate("+2+",\""+dataList[i].dataDay+"\",\""+dataList[i].ltSdk+"\")'>"+dataList[i].ltProNum+"</a></td>";
			 	  		
			 	  	}
			 	 	if(dataList[i].dxProNum.length>=20){
//			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].dxProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a onclick='sdkprobas.addTemplate("+3+",\""+dataList[i].dataDay+"\",\""+dataList[i].dxSdk+"\")'>"+dataList[i].dxProNum.substring(0,20)+"。。</a></td>";
			 	 		shtml +="<td style='text-align: center; vertical-align:middle; white-space:nowrap' onmouseover='tooltip.pop(this, \""+dataList[i].dxProNum+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> <a class='formBtn' onclick='sdkprobas.addTemplate("+3+",\""+dataList[i].dataDay+"\",\""+dataList[i].dxSdk+"\")'>电信</a></td>";
			 	 		
			 	  	}else{
			 	  		shtml +="<td style='white-space:nowrap; white-space:nowrap; overflow:hidden; text-overflow:ellipsis'><a onclick='sdkprobas.addTemplate("+3+",\""+dataList[i].dataDay+"\",\""+dataList[i].dxSdk+"\")'>"+dataList[i].dxProNum+"</a></td>";
			 	  		
			 	  	}*/
		 	 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].remarks+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createPeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].updatePeople+"</td>";
			 		shtml +="<td style='text-align: center; white-space:nowrap'>"+dataList[i].createtime+"</td>";
//			 		shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Update' class='dataTableBtn dataTableResetBtn'href='javascript:sdkprobas.updateData(1,"+dataList[i].id+",\""+pageNo+"\",\""+lenth+"\")'>编辑</a>";
//			 	  	shtml +="&nbsp;";
			 	  	shtml +="<td style='text-align: center; white-space:nowrap; vertical-align:middle; '><a title='Delete'class='dataTableBtn dataTableDeleteBtn' href='javascript:sdkprobas.updateData(2,"+dataList[i].dataDay+")'>删除</a></td>";
			 	  	shtml +="</tr>"
			 	}
			 	idea("#showData").html(shtml);
			}else{
				 var html2="";
			 	  	html2 +="<td class='dataTables_empty' valign='top' colspan='15'>暂无数据</td>"
				idea("#showData").html(html2);
			}
			sdkprobas.initPageBar(pageNo,totalPage,pageSize,rowCount);
			idea("#pageNo2").val('');
			idea("#lenth2").val('');
		});
	},
	
	//type=1，编辑要展现的数据
	//type=2，删除：业务逻辑删除
	updateData:function(type,dataDay,isOnline,pageNo2,lenth2){
		//强制影藏其它层
//		this.hideAddDiv();
//		this.hidediv();
		idea("#showUpMsg").html("");
		if(type==1){
			var url = basePath+"jsp/tvdeploy/sdk/sdkEdit.jsp?id="+id+"&pageNo2="+pageNo2+"&lenth2="+lenth2;
			window.location.href=url;
		}else if(type==2){
			if(window.confirm("确认：是否删除?")){
				doType=type;
				var data = {
						"dataDay":dataDay,
						"doType":doType,
						"yn":"1"
			 	};
				var url = basePath+"sdkprobas/doUpdate.do";
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
	editSdk:function(id){
		idea("#id").val(id);
		var url = basePath+"sdkprobas/showEdit.do";
		var data = {
				"id":id
	 	};
		idea.base.request(data,url,function(data){
				idea("#sdkNum").val(data.sdkNum);
				idea("#sdkName").val(data.sdkName);
				idea("#sdkVer").val(data.sdkVer);
				idea("#remarks").val(data.remarks);
		});
//		this.hidediv();
	},
	
	//执行修改
	doUpdate:function(){
		idea("#showMessage").html("");
		var sdkNum =  idea("#sdkNum").val();
        var sdkName =  idea("#sdkName").val();
        var sdkVer =  idea("#sdkVer").val();
        var remarks =  idea("#remarks").val();
        
        var id =  idea("#id").val();
        var pageNo2 = idea("#pageNo2").val();
        var lenth2=idea("#lenth2").val();
        if(!sdkNum){
			idea("#sdkNum").focus();
			idea("#showMessage").html("<font color='red'>请填写SDK编号！</font> ");
			return false;
		}
		if(!sdkName){
			idea("#sdkName").focus();
			idea("#showMessage").html("<font color='red'>请填写SDK名称！</font> ");
			return false;
		}
		var doType=1;
		var data = {
				"id":id,
				"sdkNum":sdkNum,
				"sdkName":sdkName,
				"sdkVer":sdkVer,
				"remarks":remarks,
				"doType":doType
	 	};
		var url = basePath+"sdk/doUpdate.do";
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="修改成功~"){
				var url = basePath+"jsp/tvdeploy/sdk/sdkIndex.jsp";
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
			var url = basePath+"jsp/tvdeploy/sdkprobas/sdkprobasAdd.jsp";
			window.location.href=url;
	},
	
	//新增数据
	add:function(){
		var url = basePath+"sdk/add.do";
		var sdkNum=idea("#sdkNum").val();
		var sdkName=idea("#sdkName").val();
		var sdkVer=idea("#sdkVer").val();
		var remarks=idea("#remarks").val();
		var data = {
				"sdkNum":sdkNum,
				"sdkName":sdkName,
				"sdkVer":sdkVer,
				"remarks":remarks
	 	};
		idea.base.request(data,url,function(data){
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				var url = basePath+"jsp/tvdeploy/sdk/sdkIndex.jsp";
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
	addDataTemplate:function(){
		//强制影藏其它层
		idea("#show1").css('display','none');
		idea("#showAddMsg").html("");
		idea("#addHtmlData").html("");
		
		var shtml="";
		var tempValue="";//用来把存放所有小类的type_value值
		shtml +="<form method='get' id='datatemplate'>";
		shtml +="<div class='d-titleBar'>";
		shtml +="<div class='d-title' style='display: block;'>";
		shtml +="<div align='center' ><font color='red'>省份数据录入</font></div>";
		shtml +="</div>";
		shtml +="<a class='d-close' onClick='sdkprobas.hideAddDivClose()' style='display: block;'>×</a>";
		shtml +="<table style='width:90%;margin:auto;' id='dataDayTable' name='dataDayTable'>";
		shtml +="<thead>";
		shtml +="<div class='formCtrlDiv' align='center'>";
		shtml +="<input type='text' class ='inputCommonSty' name='dataDay' id='dataDay' size='15' onClick='idea.IdeaDate({dateFmt:'yyyyMMdd'})' placeholder='请输入日期'/>";
		shtml +="</div>";
		shtml +="<tr>"
 	  	shtml +="<th style='width:20%'>sdk编号</th>";
 	  	shtml +="<th style='width:20%'>运营商</th>";
 	  	shtml +="<th style='width:40%'>省份";
 	  	shtml +="<th style='width:20%'>操作";
 	  	
  		shtml +="&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' value='新增' class='formBtn' onClick='sdkprobas.addTr(0)' /></th>";
 	  	shtml +="</th>"
	 	shtml +="</tr>"
 		shtml +="</thead>"; 
 	  	shtml +="</th>";
	 	shtml +="</tr>";
	 	shtml +="</table>";
	 	shtml +="<div class='content-box-header'>";
	 	shtml +=" <div align='center'>";
	 	shtml +="<input type='button' value='提交' class='formBtn' onClick='sdkprobas.doAddTemplate()'/>"; 
	 	shtml +="</div>";
		shtml +="</div>";
		shtml +="</form>";
	 	idea("#addHtmlData").html(shtml);
		
		
		idea("#show1").css('display','block'); 
		idea("#pagebar").css('display','none'); 
	},
	
	//添加省份优先级
	addTemplate:function(type,dataDay,sdkInfo){
		//强制影藏其它层
		idea("#show").css('display','none');
		idea("#showAddMsg").html("");
		idea("#addHtmlData").html("");
		var yys="";
		if (type==1) {
			yys="移动";
		}else if(type==2){
			yys="联通";
		}else if(type==3){
			yys="电信";
		}
		var dataValue=sdkInfo.replace(/!/g,"\r\n");
		var shtml="";
		shtml +="<form method='get' id='codetemplate'>";
		shtml +="<div class='d-titleBar'>";
		shtml +="<div class='d-title' style='display: block;'>";
		shtml +="日期《"+dataDay+"》，运营商 《"+yys+"》的具体具体sdk分布的省份";
		shtml +="</div>";
		shtml +="<a class='d-close' onClick='sdkprobas.hideAddDiv()' style='display: block;'>×</a>";
		shtml +="<table style='width:90%;margin:auto;' id='priority' name='priority'>";
		shtml +="<tr>"
 	    shtml +="<td> <label>sdk分布的省份<span style='color:red'>*</span></label></td>";
		shtml +="<td> <textarea name='' id='' style='form textarea' rows='10'>"+dataValue+"</textarea></td>";
	 	shtml +="</tr>"
	 	shtml +="</table>";
	 	shtml +="<a class='d-close' onClick='sdkprobas.hideAddDiv()' style='display: block;'>×</a>"; 
		shtml +="</form>";
	 	idea("#addHtmlData").html(shtml);
	 	
		idea("#show1").css('display','block'); 
		idea("#pagebar").css('display','none'); 
	},
	
	
	
	//正式添加类型数据
	doAddTemplate:function(){
		idea("#showAddMsg").html("");
		
		//获取codetemplate的值
		var tableRows =document.getElementById("dataDayTable");
		var rowss = tableRows.rows.length-1;
		var dataValue = idea("#datatemplate").serialize();
//		alert(dataValue);
//	    var row=rowss;
		var data = {
				"dataValue":dataValue
//				"row":row
	 	};
		var url = basePath+"sdkprobas/add.do";
		idea.base.request(data,url,function(data){
//			idea("#showAddMsg").html("<font color='red' style='font-size: 20' >"+data.msg+"</font> ");
			idea("#showMessage").html("<font color='red'>"+data.msg+"</font> ");
			if(data.msg=="保存成功~"){
				var url = basePath+"jsp/tvdeploy/sdkprobas/sdkprobasIndex.jsp";
				alert(data.msg);
				window.location.href=url;
			}else{
				alert("页面超时，请重新登录");
				var url = basePath+"jsp/admin-login.jsp";
				window.location.href=url;
			}
		});
		
		//获取动态tr的值-start
//		var val = "";
//		var ab=idea("#dataDayTable tr:gt(0)").each;
//		alert("ab:"+ab);
//		idea("#dataDayTable tr:gt(0)").each(function(){
//			var tds = idea(this).find("td");
//			for(var i=0;i<tds.length;i++){
//				if(idea(tds[i]).find("input").val()==""){
//					idea("#showAddMsg").html("<font color='red' style='font-size:20' >sdk编号不能为空！</font> ");
//					return;
//				}else if(idea(tds[i]).find("input").val() !=undefined){
//					val += idea(tds[i]).find("input").val()+"~";
//				}
//			}
//			
//		});
		
	},
	
	
	//添加tr列表
	addTr:function(index){
		idea("#showAddMsg").html("");
		var tbl = document.all.dataDayTable;
		var rows = tbl.rows.length;
		var tr = tbl.insertRow(rows);
		var rdo = tr.insertCell(0);
//		rdo.innerHTML = "<th><input name='sdkNum_"+(rows)+"' id='sdkNum_"+(rows)+"' type='text' class='text-input medium-input'  size='20'></th>";
//		rdo.innerHTML = "<th id='selectSdk'> <td id='selectSdk'><select name='sdkNum_"+(rows)+"' id='sdkNum_"+(rows)+"' class='text-input medium-input'> </select></td></th>";
		rdo.innerHTML = "<th id='selectSdk'> <div id='sdk_"+(rows)+"' name='sdk_"+(rows)+"'></div></th>";
		sdk.querySdkAllByIndex(0,rows);
		var sb = tr.insertCell(1);
	 
		sb.innerHTML = "<th><select name='operator_"+(rows)+"' id='text_"+(rows)+"'> <option value='0'>===请选择===</option><option value='1'>移动</option><option value='2'>联通</option><option value='3'>电信</option></select></th>";
//		sb.innerHTML = "<th><input type='text' class='text-input medium-input' name='remark' id='text_"+(rows+1)+"' size='40' value='无' onclick='sdkprobas.defaultValue(this)' onblur='sdkprobas.checkDefaultValue(this)' /></th>";
		
		var rdo = tr.insertCell(2);
		rdo.innerHTML = "<th><input name='province_"+(rows)+"' id='province_"+(rows)+"' type='text' class='text-input medium-input'  size='60'></th>";
		var del = tr.insertCell(3);
		var table=document.getElementById("dataDayTable");
		var tb_in=table.rows.length;
		del.innerHTML = "<th><a onclick='sdkprobas.delTr(this)' >删除</a></th>";
	},
	//删除tr列表
	delTr:function(btn){
		idea(btn).closest("tr").remove();
	},
	//在动态tr获得焦点-选中值
	defaultValue:function(textId){
		var id=idea(textId).attr("id");
		var st=idea("#"+id);
		st.select();
	},
	//在动态tr离开焦点，如果为空则默认无
	checkDefaultValue:function(textId){
		var id=idea(textId).attr("id");
		var st=idea("#"+id).val();
		if(!st){
			idea("#"+id).val("无");
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
//		this.querData(pageNo,lenth);
	},
	//添加-影藏框
	hideAddDivClose:function() { 
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
				sdkprobas.querData(pageIndex, pageSize2);
			}
		});

	},
	
}
