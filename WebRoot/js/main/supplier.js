//品牌
function selectProductsHtml() {
	var html = "";
	console.log(selectMap.values());
	for ( var i = 0; i < selectMap.keys().length; i++) {
		var id = selectMap.keys()[i];
		var isError=0;
		$("input[name='combineId']").each(function(){
			if($(this).val()==id){
				isError = 1;
			}
		});
		if(isError != 0){
			alert("存在重复的品牌，请重新选择");
			return false;
		}
		var name = selectMap.get(id);
		html += '<div class = "conn_product textOverflowElipsis" style="height:60px;" >'
				+'<input type = "hidden" class="combineId" name = "combineId" value = "' + id + '">'+ name
				+'<span onclick = "selectProRemove(this)" title = "移除">×</span></div>';
	}
	selectMap.clear();
	$("input[name='checkProduct']").attr("checked", false);
	$("#conn_product_content").append(html);
	
}

function clickTrChecked(_this){
	var ckbox = $(_this).children("td:first").find(":checkbox");
	var nodeName = event.srcElement.nodeName.toUpperCase();
	var inputType = event.srcElement.type;
	if(inputType){
		inputType = inputType.toUpperCase();
	}
	if(nodeName == 'INPUT' && inputType == 'CHECKBOX'){
		return;
	}
	ckbox.attr("checked",!ckbox.attr("checked")).change();
}
function checkProduct(obj) {
	var iccArr = $(obj).val().split("#[]#");
	if (obj.checked) {
		if (selectMap.values().length > 9) {
			art.alert("最多选择10个");
			return;
		}
		console.log(iccArr[0]+"||"+iccArr[1]);
		selectMap.put(iccArr[0], iccArr[1]);
	} else {
		//selectMap.removeByKey(iccArr[0]);
		selectMap.remove(iccArr[0]);
	}
}
function selectProRemove(spanObj) {
	$(spanObj).parent().remove();
	/*var connProducts = $(".conn_product");
	for ( var i = 0; i < connProducts.length; i++) {
		var cpInp = $(connProducts[i]).children("input[type='hidden']");
		cpInp.attr("name", "connProducts[" + i + "].connProId");
	}*/
	if($("#conn_product_content input[name='combineId']").length>0){
		
		var j=0;
		var array=[];
		$("#conn_product_content input[name='combineId']").each(function(){
			array[j]=$(this).val();
			j++;
		});
		setMaterial(materialData,array);
	}
	var brandId=$(spanObj).siblings("input").val();
	$("#conn_material_content input[name='materialIds']").each(function(){
		var array=$(this).val().split("@-@");
		if(array[1]==brandId){
			$(this).parent().remove();
		}
	});
}
//材料
function selectMaterialsHtml() {
	var html = "";
	console.log(selectMap.values());
	for ( var i = 0; i < selectMap.keys().length; i++) {
		var id = selectMap.keys()[i];
		var isError=0;
		$("input[name='materialIds']").each(function(){
			if($(this).val()==id){
				isError = 1;
			}
		});
		if(isError != 0){
			alert("存在重复的材料，请重新选择");
			return false;
		}
		var nameArray = selectMap.get(id).split("^-^");
		html += '<div class = "conn_material textOverflowElipsis" style="height:60px;" >'
				+'<input type = "hidden" class="materialId" name = "materialIds" value = "' + id + '">'+ nameArray[0]
				+'<br/>'+nameArray[1]
				+'<span onclick = "selectMatRemove(this)" title = "移除">×</span></div>';
	}
	selectMap.clear();
	$("input[name='checkMaterial']").attr("checked", false);
	$("#conn_material_content").append(html);
	$("#materialDiv .pagebar").attr("class","pagebar2");
}

function selectMatRemove(spanObj) {
	$(spanObj).parent().remove();
/*	var connProducts = $(".conn_material");
	for ( var i = 0; i < connProducts.length; i++) {
		var cpInp = $(connProducts[i]).children("input[type='hidden']");
		cpInp.attr("name", "connProducts[" + i + "].connProId");
	}*/
}
//加载品牌数据
function showbrand(brandData){
	if(branddatatable){
		branddatatable.fnDestroy();
	}
	branddatatable = $('.branddatatable').dataTable({
		
		
		"bFilter" : false,
		"serverSide" : true,
		"processing" : true,
		"pagingType":   "full_numbers",
		"ajax" : {
			"async" : true,
			"url" :  ctx + '/admin/studio/list-studio',
			"type" : "post",
			"data" : brandData
		},
    	//国际化
	    "language": {
  		 	"sProcessing": "处理中...",
  			"sLengthMenu": "显示 _MENU_ 项结果",
  		 	"sZeroRecords": "没有匹配结果",
  		 	"sInfo": "显示第 _START_ 至 第_END_ 项结果，共 _TOTAL_ 项",
  			"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
  		 	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "暂无数据",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
               "sFirst": "首页",
               "sPrevious": "上页",
               "sNext": "下页",
               "sLast": "末页"
  		 	},
  		 	"oAria": {
               "sSortAscending": ": 以升序排列此列",
               "sSortDescending": ": 以降序排列此列"
        	}
     	},
     	"ordering": false,
		"columns":[
            {"data" : "name"},
            {"data":"type",
            	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
            		var html="";
            		if(oData.type==0){
            			html+='<span>总店</span>';
            		}else{
            			html+='<span>分店</span>';
            		}
            		$(nTd).html(html);
            	}
            },
            {"data":"studioName",
            	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
            		var html="";
            		if(oData.studioName==null){
            			html+='<span>-</span>';
            		}else{
            			html+='<span>'+oData.studioName+'</span>';
            		}
            		$(nTd).html(html);
            	}

            },
            {"data" : "addressStr",
            	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var html = '';
                    html ='<span title="'+oData.address+'">'+oData.addressStr+'</span>'
                   		$(nTd).html(html);
            		
            	}},
            {"data" : "id",
            	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var html = '';
                    html =
                    	'<input type = "checkbox" name = "checkProduct" onchange = "checkProduct(this)" value = "' + oData.id + '#[]#' + oData.name + '">'
                   		$(nTd).html(html);
            		
            	}
            },
         ]
	});
	
}
//加载材料数据
function setMaterial(materialData,array){
	if(materialdatatable){
		materialdatatable.fnDestroy();
	}
	materialdatatable = $('.materialdatatable').dataTable({
		
		
		"bFilter" : false,
		"serverSide" : true,
		"processing" : true,
		"pagingType":   "full_numbers",
		"ajax" : {
			"async" : true,
			"url" :  ctx + '/admin/material/list-materialpagebar?brandIds='+array,
			"type" : "post",
			"data" : materialData
		},
    	//国际化
	    "language": {
  		 	"sProcessing": "处理中...",
  			"sLengthMenu": "显示 _MENU_ 项结果",
  		 	"sZeroRecords": "没有匹配结果",
  		 	"sInfo": "显示第 _START_ 至 第_END_ 项结果，共 _TOTAL_ 项",
  			"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
  		 	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "暂无数据",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
               "sFirst": "首页",
               "sPrevious": "上页",
               "sNext": "下页",
               "sLast": "末页"
  		 	},
  		 	"oAria": {
               "sSortAscending": ": 以升序排列此列",
               "sSortDescending": ": 以降序排列此列"
        	}
     	},
     	"ordering": false,
		"columns":[
            {"data" : "id",
            	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var html = '';
                    html =
                    	'<input type = "checkbox" name = "checkMaterial" onchange = "checkProduct(this)" value = "' + oData.id +'@-@'+ oData.brandId+'#[]#' +oData.brandName+'^-^'+ oData.name + '">'
                   		$(nTd).html(html);
            		
            	}
            },
            {"data" : "name"},
            {"data" : "brandName"},
            {"data" : "type"},
            {"data" : "remark"},
            {"data" : "createtime"}
            
         ]
	});
	
}

function setaddress(){
	$("#addressDiv table tbody").initPageBar(
			{
				url : ctx + "/admin/area/list-areaPageBar", // 数据加载所调用的url
				ctrlContent : $(".pagebar"), // 分页控件的容器对象，分页空间指的是：上一页，下一页，首页，尾页……等
				htmlContent : function(data) {// 拼接返回的数据作为页面展示的html代码。
					var html = "";
					for ( var i = 0; i < data.address.length; i++) {
						var address = data.address[i];
						html += '<tr' + (i == data.address.length - 1 ? 'style = "border-bottom:none;"' : '') + '>'  
								+'<td><input type = "checkbox" name = "checkMaterial" onchange = "checkProduct(this)" value = "' + address.id +'"></td>'
								+'<td><input type = "radio" name = "radio" onclick="clickRadio(this)" value = "' + address.id +'"></td>'
								+'<td style = "width:200px;" >'+address.title+'</td>'
								+'<td style = "width:200px;" >'+address.address+'</td>' + '</tr>';
					}
					if(data.address.length == 0){
						html = '<tr><td colspan = "5" style = "text-align:center;font-size:16px;font-family:microsoft yahei;">您未发布过商品</td></tr>';
					}
					return html;
				}
			}, {
				pageNumber : 1,
				pageSize : 6,
				orderColumn : 1,// 0：价格（销售价salePrice） 1：总销量 2：库存 3：发布时间 
				orderBy : 1,
			});

}


