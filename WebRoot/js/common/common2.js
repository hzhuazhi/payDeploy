var common = {
	//地址	
	url:{
		list_url : "",
		dataList_url :"",
		add_url :"",
		update_url : "",
		delete_url : "",
        manyOperation_url : "",
	}, 
	indexInit:function(){
		//输入控制
		numberInputBind();
	},
	//添加页面加载
	addInit:function(param){ 
		/** 添加详细参数end*** */	
		$("#newFirstStoreForm").validate({
			rules : param.rules,
			messages : param.messages,
			submitHandler :  param.submitHandler || common.submitHandler,
		}); 
	},
	//地址
	updateUrl:function(url){
		 this.url = url;
	},
	//添加和修改保存
	submitHandler:function(){
		// 暂时禁用提交按钮，等ajax结束之后再启用
		var formData = $("#newFirstStoreForm").serialize();
		var id = $("#show input[type='hidden']").val();
		var url = "";
		if(id){
			url = common.url.update_url; 
		}else{
			url = common.url.add_url;
		}
		common.ajax(url,formData,function(data){
			if(!data.success){
				promptMessage(data.msg, 'warning', false);
				return false;
			}
			promptMessage ('操作成功！','success',true);
		 
    		setTimeout("common.goList()",800);
		});
	     
		return false;
		// 阻止表单提交
	},
	//页面跳转
	goList:function(){
		location.href = this.url.list_url;
	},
	//删除
	updateStatus: function(data){
		if(!confirm("确认要删除吗？")){
			return;
		}
		this.ajax(this.url.delete_url,data,function(data){
			if (data.success) {
	    		promptMessage ('删除成功！','success',false);
	    		common.goList();
			} else {
				art.alert(data.msg);
			}
		});
		
	},

    //启用/禁用
    manyOperation: function(data){
		var showMsg = '';
		if (data.isEnable == 1){
            if(!confirm("确认要禁用吗？")){
                return;
            }
            showMsg = '禁用成功!';
		}else if(data.isEnable == 2){
            if(!confirm("确认要启用吗？")){
                return;
            }
            showMsg = '启用成功!';
		}
        this.ajax(this.url.manyOperation_url,data,function(data){
            if (data.success) {
                promptMessage (showMsg,'success',false);
                common.goList();
            } else {
                art.alert(data.msg);
            }
        });

    },

    //重发
    cf: function(data){
        var showMsg = '';
        this.ajax(this.url.manyOperation_url,data,function(data){
            if (data.success) {
                promptMessage (showMsg,'success',false);
                common.goList();
            } else {
                art.alert(data.msg);
            }
        });

    },

	//导出Excel
    dataExportExcel:function (dom) {
        $(dom).attr({"method":"POST","action":this.url.exportData_url});
        $(dom).submit();
    },

	//表格加载
	showDatas:function (condJsonData,datalist){
		if(datatable){
			datatable.destroy();
		}
    	datatable = $('.datatable').DataTable({
			"ajax" : {
				"async" : true,
				"url" : this.url.dataList_url,
				"type" : "post",
				"data" : condJsonData
			},
			"columns":datalist 
		});
    },
    //异步提交
	ajax:function(url,data,callback){
    	$.ajax({
    		url : url,
			type : 'post',
			dataType : 'json',
			data :data,
			success : function(data) {
    			callback(data);
			},
			error : function(data) {
				art.alert(data.info);
			}
		});
    	
    },
    getCity:function(parentName,id){
    	 var data = {
    			length:100,
    			parentName:parentName,
    		}
    	 
    	this.ajax(ctx + '/zone/dataList.do',data,function(data){
			if(data.data){
				var option = "";
				var list = data.data;
				for(var i=0;i<list.length;i++){
					if(list[i].name){
						option+="<option value='"+list[i].name+"'>"+list[i].name+"</option>";
					} 
				}
				$("#"+id).html(option);
				switch(id)
				{
					case "province":
						common.getCity(list[0].name,"city");
					  break;
					case "city":
						common.getCity(list[0].name,"district");
					  break;
					default:
					  break;
				}
			}
			
		}); 
    } 
}
