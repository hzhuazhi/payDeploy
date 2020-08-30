var ajaxMethod = {

    //异步提交
    ajaxGo:function(url,data,callback){
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

    }
}
