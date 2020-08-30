<%@ page language="java" pageEncoding="utf-8"%> 
<input type ="hidden" name="info.totalCount" id="totalCount" value="${info.totalCount }">
<input type ="hidden" name="info.pageSize" id="pageSize" value="${info.pageSize }">
<input type ="hidden" name="info.curPageNum"  id="curPageNum" value="${info.curPageNum}">
<div id="info_pager">

</div> 
<script language="JavaScript"  type="text/javascript" >
$(function() {
	var data = {
		"totalCount":$("#totalCount").val(),
		"pageSize":$("#pageSize").val(),
		"curPageNum":$("#curPageNum").val()
	}; 
	common.pager("pager","info",data);
});
function pager(num){
	if(num==0){
		num=$("#pagers").val();
		if(!num){
			return;
		} 
		if(parseInt(num)>common.totalCount){
			return ;
		}
	}
	$("#curPageNum").val(num);
	$("#pfrm").submit();
}
</script>